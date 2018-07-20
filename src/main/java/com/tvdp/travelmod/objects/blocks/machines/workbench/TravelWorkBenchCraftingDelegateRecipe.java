package com.tvdp.travelmod.objects.blocks.machines.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TravelWorkBenchCraftingDelegateRecipe implements TravelWorkBenchRecipe
{
	private IRecipe delegate;
	
	public TravelWorkBenchCraftingDelegateRecipe(IRecipe delegate)
	{
		this.delegate = delegate;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn, BlockPos table, EntityPlayer player)
	{
		return this.delegate.matches(inv, worldIn);
	}
	
	@Override
	public ItemStack result(InventoryCrafting inv, World worldIn, BlockPos table, EntityPlayer player)
	{
		return this.delegate.getCraftingResult(inv);
	}
	
	@Override
	public NonNullList<ItemStack> remaining(InventoryCrafting inv, World WorldIn, BlockPos table, EntityPlayer player)
	{
		return this.delegate.getRemainingItems(inv);
	}
}
