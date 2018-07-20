package com.tvdp.travelmod.util.compat.jei.workbench;

import com.tvdp.travelmod.util.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractTravelWorkBenchRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T>
{
	protected final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/travel_workbench.png");
	protected final ResourceLocation ICON = new ResourceLocation(Reference.MODID + ":textures/gui/travel_workbench_icon.png");
	
	protected final int input1 = 0;
	protected final int input2 = 1;
	protected final int input3 = 2;
	protected final int input4 = 3;
	protected final int input5 = 4;
	protected final int input6 = 5;
	protected final int input7 = 6;
	protected final int input8 = 7;
	protected final int input9 = 8;
	protected final int input10 = 9;
	protected final int output = 10;
	
	public AbstractTravelWorkBenchRecipeCategory(IGuiHelper helper)
	{
		//
	}
}
