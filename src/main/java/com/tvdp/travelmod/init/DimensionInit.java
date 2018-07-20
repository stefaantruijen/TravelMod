package com.tvdp.travelmod.init;

import com.tvdp.travelmod.world.dimension.travel.DimensionTravel;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit
{
	public static final DimensionType TRAVEL = DimensionType.register("Travel", "_travel", 2, DimensionTravel.class, false);
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(2, TRAVEL);
	}
}
