package com.tvdp.travelmod.commands.util;

import com.tvdp.travelmod.advancements.Triggers;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.world.dimension.travel.DimensionTravel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Teleport extends Teleporter
{
	private final WorldServer world;
	private double x, y, z;
	
	public Teleport(WorldServer world, double x, double y, double z)
	{
		super(world);
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw)
	{
		this.world.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z));
		entityIn.setPosition(x, y, z);
		entityIn.motionX = 0F;
		entityIn.motionY = 0F;
		entityIn.motionZ = 0F;
	}
	
	public static void prepareSpawnPlace(World world)
	{
		for(int i = 64; i < 67; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				for (int k = -1; k < 2; k++)
				{
					world.setBlockState(new BlockPos(j, i, k), Blocks.AIR.getDefaultState());
					if (j != 0 && k != 0)
					{
						world.setBlockState(new BlockPos(j, 63, k), BlockInit.TRAVEL_BLOCK_DECORATION.setBlockUnbreakable().getDefaultState());
					}
				}
			}
		}
		world.setBlockState(new BlockPos(0, 63, 0), BlockInit.TRAVEL_BLOCK_PORTAL.setBlockUnbreakable().getDefaultState());
	}
	
	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z)
	{
		int oldDimension = player.getEntityWorld().provider.getDimension();
		int newDimension = dimension;
		
		
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		DimensionTravel dimensionWorld = (DimensionTravel)worldServer.provider;
		
		if (worldServer == null || server == null)
		{
			throw new IllegalArgumentException("Dimension: " + dimension + " doesn't exist");
		}
		
		if (!dimensionWorld.landedAlready)
		{
			prepareSpawnPlace((World)worldServer);
			dimensionWorld.landedAlready = true;
		}
		
		if (oldDimension == 2)
		{
			newDimension = 0;
			double newX = dimensionWorld.playerOldX;
			double newY = dimensionWorld.playerOldY;
			double newZ = dimensionWorld.playerOldZ;
			
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, newDimension, new Teleport(worldServer, newX, newY, newZ));
			player.setPositionAndUpdate(newX, newY, newZ);
		} else
		{
			double newX = 0.5d;
			double newY = 64d;
			double newZ = 0.5d;
			
			dimensionWorld.playerOldX = x;
			dimensionWorld.playerOldY = y;
			dimensionWorld.playerOldZ = z;
			
			Triggers.TRAVEL_DIMENSION_ENTER_TRIGGER.trigger(entityPlayerMP);
			System.out.println("test");
			worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, newDimension, new Teleport(worldServer, newX, newY, newZ));
			player.setPositionAndUpdate(newX, newY, newZ);
		}
	}
}
