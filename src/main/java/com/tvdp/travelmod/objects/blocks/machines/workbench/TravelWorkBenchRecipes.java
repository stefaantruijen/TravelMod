package com.tvdp.travelmod.objects.blocks.machines.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TravelWorkBenchRecipes implements IRecipe
{
	public ItemStack input1 = ItemStack.EMPTY;
	public ItemStack input2 = ItemStack.EMPTY;
	public ItemStack input3 = ItemStack.EMPTY;
	public ItemStack input4 = ItemStack.EMPTY;
	public ItemStack input5 = ItemStack.EMPTY;
	public ItemStack input6 = ItemStack.EMPTY;
	public ItemStack input7 = ItemStack.EMPTY;
	public ItemStack input8 = ItemStack.EMPTY;
	public ItemStack input9 = ItemStack.EMPTY;
	public ItemStack input10 = ItemStack.EMPTY;
	public ItemStack result = ItemStack.EMPTY;
	
	public TravelWorkBenchRecipes(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack input7, ItemStack input8, ItemStack input9, ItemStack input10, ItemStack result)
	{
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.input4 = input4;
		this.input5 = input5;
		this.input6 = input6;
		this.input7 = input7;
		this.input8 = input8;
		this.input9 = input9;
		this.input10 = input10;
		this.result = result;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		Item input1 = inv.getStackInSlot(0).getItem();
		Item input2 = inv.getStackInSlot(1).getItem();
		Item input3 = inv.getStackInSlot(2).getItem();
		Item input4 = inv.getStackInSlot(3).getItem();
		Item input5 = inv.getStackInSlot(4).getItem();
		Item input6 = inv.getStackInSlot(5).getItem();
		Item input7 = inv.getStackInSlot(6).getItem();
		Item input8 = inv.getStackInSlot(7).getItem();
		Item input9 = inv.getStackInSlot(8).getItem();
		Item input10 = inv.getStackInSlot(9).getItem();
		
		return input1 == this.input1.getItem() && input2 == this.input2.getItem() && input3 == this.input3.getItem() && input4 == this.input4.getItem() && input5 == this.input5.getItem() && input6 == this.input6.getItem() && input7 == this.input7.getItem() && input8 == this.input8.getItem() && input9 == this.input9.getItem() && input10 == this.input10.getItem();
	}
	
	public int getRecipeSize()
	{
		return 1;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		return this.getRecipeOutput();
	}
	
	@Override
	public ItemStack getRecipeOutput()
	{
		return this.result;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
	{
		return NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getRegistryName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<IRecipe> getRegistryType()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canFit(int width, int height)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
