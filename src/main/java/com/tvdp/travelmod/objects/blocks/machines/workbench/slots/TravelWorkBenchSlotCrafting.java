package com.tvdp.travelmod.objects.blocks.machines.workbench.slots;

import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchCraftingManager;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchRecipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class TravelWorkBenchSlotCrafting extends Slot
{
	private final InventoryCrafting craftMatrix;
	private final EntityPlayer player;
	private int amountCrafted;
	private BlockPos table;
	
	public TravelWorkBenchSlotCrafting(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition, BlockPos table)
	{
		super(inventoryIn, slotIndex, xPosition, yPosition);
		this.player = player;
		this.craftMatrix = craftingInventory;
		this.table = table;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int amount)
	{
		if (this.getHasStack())
		{
			this.amountCrafted += Math.min(amount, this.getStack().getCount());
		}
		
		return super.decrStackSize(amount);
	}
	
	@Override
	protected void onCrafting(ItemStack stack, int amount)
	{
		this.amountCrafted += amount;
		this.onCrafting(stack);
	}
	
	@Override
	protected void onSwapCraft(int amount)
	{
		this.amountCrafted += amount;
	}
	
	@Override
	protected void onCrafting(ItemStack stack)
	{
		if (this.amountCrafted > 0)
		{
			stack.onCrafting(this.player.world, this.player, this.amountCrafted);
			net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerCraftingEvent(this.player, stack, craftMatrix);
		}
		
		this.amountCrafted = 0;
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
	{
		TravelWorkBenchRecipe recipe = TravelWorkBenchCraftingManager.findMatching(this.craftMatrix, thePlayer.world, this.table, thePlayer);
		if (recipe == null)
		{
			this.inventory.setInventorySlotContents(0, ItemStack.EMPTY);
			return ItemStack.EMPTY;
		} else {
			this.onCrafting(stack);
			net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
			NonNullList<ItemStack> nonnulllist = recipe.remaining(this.craftMatrix, thePlayer.world, this.table, thePlayer);
			net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);
			
			for (int i = 0; i < nonnulllist.size(); ++i)
			{
				ItemStack itemstack = this.craftMatrix.getStackInSlot(i);
				ItemStack itemstack1 = nonnulllist.get(i);
				
				if (!itemstack.isEmpty())
				{
					this.craftMatrix.decrStackSize(i, 1);
					itemstack = this.craftMatrix.getStackInSlot(i);
				}
				
				if (!itemstack1.isEmpty())
				{
					if(itemstack.isEmpty())
					{
						this.craftMatrix.setInventorySlotContents(i, itemstack1);
					} else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1))
					{
						itemstack1.grow(itemstack.getCount());
						this.craftMatrix.setInventorySlotContents(i, itemstack1);
					} else if (!this.player.inventory.addItemStackToInventory(itemstack1))
					{
						this.player.dropItem(itemstack1, false);
					}
				}
			}
			
			return stack;
		}
	}
}
