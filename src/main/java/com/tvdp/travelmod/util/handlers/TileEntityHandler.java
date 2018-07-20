package com.tvdp.travelmod.util.handlers;

import com.tvdp.travelmod.objects.blocks.machines.furnace.TileEntityTravelFurnace;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityTravelFurnace.class, "travel_furnace");
	}
}
