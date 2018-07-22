package com.tvdp.travelmod.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHelper
{
	public World getWorld(EntityPlayer playerIn)
	{
		return playerIn.getEntityWorld().getMinecraftServer().getWorld(playerIn.dimension);
	}
	
	public World getWorld(EntityPlayer playerIn, Side side)
	{
		return playerIn.getEntityWorld().getMinecraftServer().getWorld(playerIn.dimension);
	}
	
	@SideOnly(Side.CLIENT)
	public World getClientWorld(EntityPlayer playerIn)
	{
		return Minecraft.getMinecraft().world;
	}
}
