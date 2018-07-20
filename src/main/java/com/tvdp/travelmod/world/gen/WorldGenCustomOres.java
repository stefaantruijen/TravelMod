package com.tvdp.travelmod.world.gen;

import java.util.Random;

import com.tvdp.travelmod.init.BlockInit;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator
{
	private WorldGenerator andamantium_ore, copper_ore, travel_ore;
	
	public WorldGenCustomOres()
	{
		copper_ore = new WorldGenMinable(BlockInit.COPPER_ORE.getDefaultState(), 12, BlockMatcher.forBlock(Blocks.STONE));
		andamantium_ore = new WorldGenMinable(BlockInit.ANDAMANTIUM_ORE.getDefaultState(), 12, BlockMatcher.forBlock(Blocks.STONE));
		travel_ore = new WorldGenMinable(BlockInit.TRAVEL_ORE.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimension())
		{
		case -1:
			//the nether
			break;
		case 0:
			//the overworld
			runGenerator(copper_ore, world, random, chunkX, chunkZ, 45, 10, 150);
			runGenerator(andamantium_ore, world, random, chunkX, chunkZ, 40, 10, 150);
			runGenerator(travel_ore, world, random, chunkX, chunkZ, 35, 10, 60);
			break;
		case 1:
			//the end
			break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
	{
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds");
		
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}
