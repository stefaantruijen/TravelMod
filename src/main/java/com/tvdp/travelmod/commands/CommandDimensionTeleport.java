package com.tvdp.travelmod.commands;

import java.util.List;

import com.google.common.collect.Lists;
import com.tvdp.travelmod.commands.util.Teleport;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandDimensionTeleport extends CommandBase
{
	private final List<String> aliases = Lists.newArrayList(Reference.MODID, "travel", "traveldimension", "trav", "travdim");

	@Override
	public String getName()
	{
		return "travel";
	}
	
	@Override
	public List<String> getAliases()
	{
		return aliases;
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "travel";
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{	
		int dimensionID = 2;
		
		if (sender instanceof EntityPlayer)
		{
			Teleport.teleportToDimension((EntityPlayer)sender, dimensionID, sender.getPosition().getX(), sender.getPosition().getY(), sender.getPosition().getZ());
		}
		
	}
	
}
