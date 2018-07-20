package com.tvdp.travelmod.util.compat.jei.furnace;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.objects.blocks.machines.furnace.TravelFurnaceRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class TravelFurnaceRecipeMaker
{
	public static List<TravelFurnaceRecipe> getRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		TravelFurnaceRecipes instance = TravelFurnaceRecipes.getInstance();
		Table<ItemStack, ItemStack, Map<ItemStack, ItemStack>> recipes = instance.getDualSmeltingList();
		List<TravelFurnaceRecipe> jeiRecipes = Lists.newArrayList();
		
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> entry : recipes.columnMap().entrySet())
		{
			for(Entry<ItemStack, Map<ItemStack, ItemStack>> ent : entry.getValue().entrySet())
			{
				for (Entry<ItemStack, ItemStack> ent1 : ent.getValue().entrySet())
				{
					ItemStack input1 = entry.getKey();
					ItemStack input2 = ent.getKey();
					ItemStack fuel = new ItemStack(ItemInit.TRAVEL_DUST);
					ItemStack output = ent1.getKey();
					ItemStack rest = ent1.getValue();
					
					List<ItemStack> inputs = Lists.newArrayList(input1, input2, fuel);
					List<ItemStack> outputs = Lists.newArrayList(output, rest);
					TravelFurnaceRecipe recipe = new TravelFurnaceRecipe(inputs, outputs);
					jeiRecipes.add(recipe);
				}
			}
		}
		
		return jeiRecipes;
	}
}
