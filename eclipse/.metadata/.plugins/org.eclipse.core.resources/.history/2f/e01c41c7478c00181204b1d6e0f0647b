package com.tvdp.travelmod.world.dimension.travel;

import com.tvdp.travelmod.init.BiomeInit;
import com.tvdp.travelmod.init.DimensionInit;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionTravel extends WorldProvider
{
	public double playerOldX = 0;
	public double playerOldY = 0;
	public double playerOldZ = 0;
	
	public boolean landedAlready = false;
	
	public DimensionTravel() 
	{
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.TRAVEL);
	}
	
	@Override
	public DimensionType getDimensionType()
	{
		return DimensionInit.TRAVEL;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorTravel(world, true, world.getSeed());
	}
	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}
}
