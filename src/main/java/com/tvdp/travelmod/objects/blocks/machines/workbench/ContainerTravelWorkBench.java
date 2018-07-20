package com.tvdp.travelmod.objects.blocks.machines.workbench;

import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.objects.blocks.machines.workbench.slots.TravelWorkBenchSlotCrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerTravelWorkBench extends Container
{
	private World world;
	private BlockPos table;
	private InventoryCrafting craftMatrix;
	private InventoryCraftResult craftResult;
	private EntityPlayer player;
	
	public ContainerTravelWorkBench(InventoryPlayer playerIn, World world, int x, int y, int z)
	{
		this.world = world;
		this.table = new BlockPos(x, y, z);
		this.craftMatrix = new InventoryCrafting(this, 5, 2);
		this.craftResult = new InventoryCraftResult();
		this.player = playerIn.player;
		
		this.addSlotToContainer(new TravelWorkBenchSlotCrafting(playerIn.player, this.craftMatrix, this.craftResult, 0, 139, 34, this.table));
		
		this.addSlotToContainer(new Slot(craftMatrix, 0, 12, 49));
		this.addSlotToContainer(new Slot(craftMatrix, 1, 12, 27));
		this.addSlotToContainer(new Slot(craftMatrix, 2, 35, 14));
		this.addSlotToContainer(new Slot(craftMatrix, 3, 59, 14));
		this.addSlotToContainer(new Slot(craftMatrix, 4, 82, 27));
		this.addSlotToContainer(new Slot(craftMatrix, 5, 82, 49));
		this.addSlotToContainer(new Slot(craftMatrix, 6, 59, 58));
		this.addSlotToContainer(new Slot(craftMatrix, 7, 35, 58));
		this.addSlotToContainer(new Slot(craftMatrix, 8, 35, 36));
		this.addSlotToContainer(new Slot(craftMatrix, 9, 59, 36));
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(playerIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(playerIn, i, 8 + i * 18, 142));
		}
		
		this.onCraftMatrixChanged(craftMatrix);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn)
	{
		TravelWorkBenchRecipe matching = TravelWorkBenchCraftingManager.findMatching(this.craftMatrix, this.world, this.table, this.player);
		if (matching != null)
		{
			this.craftResult.setInventorySlotContents(0, matching.result(this.craftMatrix, this.world, this.table, this.player));
		} else {
			this.craftResult.setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);
		
		if (!this.world.isRemote)
		{
			for (int i = 0; i < 10; ++i)
			{
				ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);
				
				if (!itemstack.isEmpty())
				{
					playerIn.dropItem(itemstack, false);
				}
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.world.getBlockState(this.table).getBlock().equals(BlockInit.TRAVEL_WORKBENCH) && playerIn.getDistanceSq(this.table) <= 8 * 8;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index == 0)
			{
				itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);
				
				if (!this.mergeItemStack(itemstack1, 11, 47, true))
				{
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 11 && index < 38)
			{
				if (!this.mergeItemStack(itemstack1, 38, 47, false))
				{
					return ItemStack.EMPTY;
				}
			} else if (index >= 38 && index < 47)
			{
				if (!this.mergeItemStack(itemstack1, 11, 38, false))
				{
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 11, 47, false))
			{
				return ItemStack.EMPTY;
			}
			
			if (itemstack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}
			
			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
			
			if (index == 0)
			{
				playerIn.dropItem(itemstack2, false);
			}
		}
		
		return itemstack;
	}
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
	{
		return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
	}

}
