package com.tvdp.travelmod.util.handlers;

import com.tvdp.travelmod.objects.blocks.machines.furnace.TileEntityTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.portal.TileEntityTravelPortal;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityTravelFurnace.class, "travmod:travel_furnace");
		GameRegistry.registerTileEntity(TileEntityTravelPortal.class, "travmod:travel_portal");
	}
}
