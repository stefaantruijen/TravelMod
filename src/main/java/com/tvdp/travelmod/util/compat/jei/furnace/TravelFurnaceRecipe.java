package com.tvdp.travelmod.util.compat.jei.furnace;

import java.util.List;

import com.tvdp.travelmod.objects.blocks.machines.furnace.TravelFurnaceRecipes;
import com.tvdp.travelmod.util.compat.jei.JEICompat;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class TravelFurnaceRecipe implements IRecipeWrapper
{
	private final List<ItemStack> inputs;
	private final List<ItemStack> outputs;
	
	public TravelFurnaceRecipe(List<ItemStack> inputs, List<ItemStack> outputs)
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
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
		TravelFurnaceRecipes recipes = TravelFurnaceRecipes.getInstance();
		float experience = recipes.getTravelExperience(outputs.get(0));
		
		if (experience > 0)
		{
			String experienceString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer renderer = minecraft.fontRenderer;
			int stringWidth = renderer.getStringWidth(experienceString);
			renderer.drawString(experienceString, recipeWidth - stringWidth + 1, 2, 65);
		}
	}
}
