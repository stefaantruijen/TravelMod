package com.tvdp.travelmod.objects.blocks.machines.workbench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface TravelWorkBenchRecipe
{
	boolean matches(InventoryCrafting inv, World worldIn, BlockPos table, EntityPlayer player);
	
	ItemStack result(InventoryCrafting inv, World worldIn, BlockPos table, EntityPlayer player);
	
	NonNullList<ItemStack> remaining(InventoryCrafting inv, World WorldIn, BlockPos table, EntityPlayer player);
}
