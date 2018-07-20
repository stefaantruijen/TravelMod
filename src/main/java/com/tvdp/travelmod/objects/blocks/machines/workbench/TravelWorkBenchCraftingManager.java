package com.tvdp.travelmod.objects.blocks.machines.workbench;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.init.ItemInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TravelWorkBenchCraftingManager
{
	private static List<TravelWorkBenchRecipe> registeredRecipes = Collections.synchronizedList(new ArrayList<TravelWorkBenchRecipe>());
	private static List<TravelWorkBenchRecipes> listOfRecipes = Collections.synchronizedList(new ArrayList<TravelWorkBenchRecipes>());
	
	public static void init()
	{
		TravelWorkBenchCraftingManager.listOfRecipes.add(new TravelWorkBenchRecipes(new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(ItemInit.TRAVEL_DUST), new ItemStack(ItemInit.TRAVEL_REFINER), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(Blocks.COAL_BLOCK), new ItemStack(Blocks.COAL_BLOCK), new ItemStack(BlockInit.TRAVEL_GLASS_PANE), new ItemStack(BlockInit.TRAVEL_GLASS_PANE), new ItemStack(Item.getItemFromBlock(BlockInit.TRAVEL_FURNACE).setMaxStackSize(1))));
		TravelWorkBenchCraftingManager.listOfRecipes.add(new TravelWorkBenchRecipes(new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK), new ItemStack(BlockInit.COPPER_ANDAMANTIUM_COMPRESSED)));
	}
	
	public static List<TravelWorkBenchRecipe> getRecipes()
	{
		return TravelWorkBenchCraftingManager.registeredRecipes;
	}
	
	public static List<TravelWorkBenchRecipes> getListOfRecipes()
	{
		return TravelWorkBenchCraftingManager.listOfRecipes;
	}
	
	public static void register(TravelWorkBenchRecipe recipe)
	{
		TravelWorkBenchCraftingManager.registeredRecipes.add(recipe);
	}
	
	public static TravelWorkBenchRecipe findMatching(InventoryCrafting inv, World worldIn, BlockPos table, EntityPlayer player)
	{
		for(TravelWorkBenchRecipe recipe : TravelWorkBenchCraftingManager.registeredRecipes)
		{
			if (recipe.matches(inv, worldIn, table, player))
			{
				return recipe;
			}
		}
		
		return null;
	}
}
