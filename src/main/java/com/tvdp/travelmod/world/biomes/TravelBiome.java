package com.tvdp.travelmod.world.biomes;

import com.tvdp.travelmod.init.BlockInit;

import net.minecraft.world.biome.Biome;

public class TravelBiome extends Biome
{
	public TravelBiome()
	{
		super(new BiomeProperties("Travel").setBaseHeight(2F).setHeightVariation(0.0F).setTemperature(0.5F).setRainDisabled().setWaterColor(2451));
		
		topBlock = BlockInit.TRAVEL_BLOCK.getDefaultState();
		fillerBlock = BlockInit.TRAVEL_BLOCK.getDefaultState();
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		
	}
}
