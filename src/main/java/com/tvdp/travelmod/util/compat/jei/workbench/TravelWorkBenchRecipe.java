package com.tvdp.travelmod.util.compat.jei.workbench;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class TravelWorkBenchRecipe implements IRecipeWrapper
{
	private final List<ItemStack> inputs;
	private final List<ItemStack> outputs;
	
	public TravelWorkBenchRecipe(List<ItemStack> inputs, List<ItemStack> outputs)
	{
		this.inputs = inputs;
		this.outputs = outputs;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients)
	{
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, outputs);
	}
}
