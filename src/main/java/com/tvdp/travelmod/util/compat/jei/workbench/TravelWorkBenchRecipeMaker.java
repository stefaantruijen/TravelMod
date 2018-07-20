package com.tvdp.travelmod.util.compat.jei.workbench;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchCraftingManager;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchRecipes;
import com.tvdp.travelmod.util.compat.jei.furnace.TravelFurnaceRecipe;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class TravelWorkBenchRecipeMaker
{
	public static List<TravelWorkBenchRecipe> getRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		List<TravelWorkBenchRecipes> recipes = TravelWorkBenchCraftingManager.getListOfRecipes();
		
		List<TravelWorkBenchRecipe> jeiRecipes = Lists.newArrayList();
		
		for(TravelWorkBenchRecipes entry : recipes)
		{	
			List<ItemStack> inputs = Lists.newArrayList(entry.input1, entry.input2, entry.input3, entry.input4, entry.input5, entry.input6, entry.input7, entry.input8, entry.input9, entry.input10);
			List<ItemStack> outputs = Lists.newArrayList(entry.result);
			TravelWorkBenchRecipe recipe = new TravelWorkBenchRecipe(inputs, outputs);
			jeiRecipes.add(recipe);
		}
		
		return jeiRecipes;
	}
}
