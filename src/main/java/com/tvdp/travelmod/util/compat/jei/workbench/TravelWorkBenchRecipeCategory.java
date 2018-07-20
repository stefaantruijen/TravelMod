package com.tvdp.travelmod.util.compat.jei.workbench;

import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.util.Reference;
import com.tvdp.travelmod.util.compat.jei.RecipeCategories;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TravelWorkBenchRecipeCategory extends AbstractTravelWorkBenchRecipeCategory<TravelWorkBenchRecipe>
{
	private final IDrawable background;
	private final String name;
	
	public TravelWorkBenchRecipeCategory(IGuiHelper helper)
	{
		super(helper);
		this.background = helper.createDrawable(TEXTURES, 7, 9, 158, 70);
		this.name = "Travel WorkBench";
	}
	
	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft)
	{
		//
	}
	
	@Override
	public String getTitle()
	{
		return name;
	}
	
	/*@Override
	public IDrawable getIcon()
	{
		return null;
	}*/
	
	@Override
	public String getModName()
	{
		return Reference.NAME;
	}
	
	@Override
	public String getUid()
	{
		return RecipeCategories.TRAVEL_WORKBENCH;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TravelWorkBenchRecipe recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 4, 39);
		stacks.init(input2, true, 4, 17);
		stacks.init(input3, true, 27, 4);
		stacks.init(input4, true, 51, 4);
		stacks.init(input5, true, 74, 17);
		stacks.init(input6, true, 74, 39);
		stacks.init(input7, true, 51, 48);
		stacks.init(input8, true, 27, 48);
		stacks.init(input9, true, 27, 26);
		stacks.init(input10, true, 51, 26);
		stacks.init(output, false, 131, 24);
		stacks.set(ingredients);
	}
}
