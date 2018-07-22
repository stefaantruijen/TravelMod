package com.tvdp.travelmod.world.dimension.travel;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.tvdp.travelmod.init.BiomeInit;
import com.tvdp.travelmod.init.BlockInit;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.ForgeEventFactory;

public class ChunkGeneratorTravel implements IChunkGenerator
{
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState TRAVEL_BLOCK = BlockInit.TRAVEL_BLOCK.getDefaultState();
	
	private final World world;
	private final boolean generateStructures;
	private final Random rand;
	
	public ChunkGeneratorTravel(World world, boolean generate, long seed)
	{
		this.world = world;
        this.generateStructures = generate;
        this.rand = new Random(seed);
        world.setSeaLevel(63);
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		buildSurfaces(x, z, chunkprimer);
		
		if (this.generateStructures)
        {
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(BiomeInit.TRAVEL);
        }

        chunk.resetRelightChecks();
        return chunk;
		
	}
	
	public void buildSurfaces(int x, int z, ChunkPrimer primer)
    {
        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        int i = this.world.getSeaLevel() + 1;

        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {

                for (int j1 = 127; j1 >= 0; --j1)
                {
                    if (j1 < 127 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5))
                    {
                        primer.setBlockState(k, j1, j, TRAVEL_BLOCK);
                    }
                    else
                    {
                        primer.setBlockState(k, j1, j, Blocks.BEDROCK.getDefaultState());
                    }
                }
            }
        }
    }

	@Override
	public void populate(int x, int z)
	{
		BlockFalling.fallInstantly = true;
        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        ChunkPos chunkpos = new ChunkPos(x, z);
        
        if(generateStructures)
        {
        }

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
        BlockFalling.fallInstantly = false;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
	}

	@Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
    	return null;
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        return false;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    	
    }

}
