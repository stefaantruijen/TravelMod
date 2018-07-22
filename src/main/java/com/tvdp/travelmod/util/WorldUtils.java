package com.tvdp.travelmod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WorldUtils
{
	public static void addScheduledTask(World worldIn, Runnable r)
	{
		if (worldIn.isRemote)
		{
			Minecraft.getMinecraft().addScheduledTask(r);
		} else {
			((WorldServer)worldIn).addScheduledTask(r);
		}
	}
}
