package com.tvdp.travelmod.util.compat.jei.furnace;

import com.tvdp.travelmod.util.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractTravelFurnaceRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>
{
	protected final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/travel_furnace.png");
	
	protected final int input1 = 0;
	protected final int input2 = 1;
	protected final int fuel = 2;
	protected final int output = 3;
	protected final int rest = 4;
	
	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;
	protected final IDrawableAnimated animatedArrow;
	
	public AbstractTravelFurnaceRecipeCategory(IGuiHelper helper)
	{
		staticFlame = helper.createDrawable(TEXTURES, 176, 0, 14, 14);
		animatedFlame = helper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURES, 176, 14, 24, 17);
		animatedArrow = helper.createAnimatedDrawable(staticArrow, 100, IDrawableAnimated.StartDirection.LEFT, false);
	}
}
