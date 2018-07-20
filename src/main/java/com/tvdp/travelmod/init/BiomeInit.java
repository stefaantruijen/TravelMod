package com.tvdp.travelmod.init;

import com.tvdp.travelmod.world.biomes.TravelBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
	public static final Biome TRAVEL = new TravelBiome();
	
	public static void registerBiomes()
	{
		
	}
	
	private static Biome initBiome(Biome biome, String name, BiomeType biometype, Type type)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.print("Biome registered");
		BiomeDictionary.addTypes(biome, type);
		BiomeManager.addBiome(biometype, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		return biome;
	}
}
