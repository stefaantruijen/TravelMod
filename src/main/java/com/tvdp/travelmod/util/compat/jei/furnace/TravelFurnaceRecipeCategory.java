package com.tvdp.travelmod.util.compat.jei.furnace;

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

public class TravelFurnaceRecipeCategory extends AbstractTravelFurnaceRecipeCategory<TravelFurnaceRecipe>
{
	private final IDrawable background;
	private final String name;
	
	public TravelFurnaceRecipeCategory(IGuiHelper helper)
	{
		super(helper);
		background = helper.createDrawable(TEXTURES, 13, 7, 158, 71);
		name = "Travel Furnace";
	}
	
	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft)
	{
		animatedFlame.draw(minecraft, 13, 48);
		animatedArrow.draw(minecraft, 66, 28);
	}
	
	@Override
	public String getTitle()
	{
		return name;
	}
	
	/*@Override
	public IDrawable getIcon()
	{
		return (IDrawable)Item.getItemFromBlock(BlockInit.TRAVEL_BLOCK);
	}*/
	
	@Override
	public String getModName()
	{
		return Reference.NAME;
	}
	
	@Override
	public String getUid()
	{
		return RecipeCategories.TRAVEL_FURNACE;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TravelFurnaceRecipe recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, 36, 7);
		stacks.init(input2, true, 8, 23);
		stacks.init(fuel, true, 35, 45);
		stacks.init(output, false, 102, 27);
		stacks.init(rest, false, 132, 43);
		stacks.set(ingredients);
	}
}
