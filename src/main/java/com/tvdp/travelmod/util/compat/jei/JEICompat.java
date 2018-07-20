package com.tvdp.travelmod.util.compat.jei;

import java.util.IllegalFormatException;

import com.tvdp.travelmod.objects.blocks.machines.furnace.ContainerTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.workbench.ContainerTravelWorkBench;
import com.tvdp.travelmod.util.compat.jei.furnace.TravelFurnaceRecipeCategory;
import com.tvdp.travelmod.util.compat.jei.furnace.TravelFurnaceRecipeMaker;
import com.tvdp.travelmod.util.compat.jei.workbench.TravelWorkBenchRecipeCategory;
import com.tvdp.travelmod.util.compat.jei.workbench.TravelWorkBenchRecipeMaker;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.runtime.JeiHelpers;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin
{
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new TravelFurnaceRecipeCategory(gui));
		registry.addRecipeCategories(new TravelWorkBenchRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry registry)
	{
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(TravelFurnaceRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.TRAVEL_FURNACE);
		recipeTransfer.addRecipeTransferHandler(ContainerTravelFurnace.class, RecipeCategories.TRAVEL_FURNACE, 0, 3, 5, 36);
		
		registry.addRecipes(TravelWorkBenchRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.TRAVEL_WORKBENCH);
		recipeTransfer.addRecipeTransferHandler(ContainerTravelWorkBench.class, RecipeCategories.TRAVEL_WORKBENCH, 1, 10, 11, 36);
	}
	
	public static String translateToLocal(String key)
	{
		if (I18n.canTranslate(key)) return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format)
	{
		String s = translateToLocal(key);
		try
		{
			return String.format(s, format);
		} catch (IllegalFormatException e)
		{
			return "Format error: " + s;
		}
	}
}
