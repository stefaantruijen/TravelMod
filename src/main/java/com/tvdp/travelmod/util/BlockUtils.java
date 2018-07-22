package com.tvdp.travelmod.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils
{
	public static BlockPos toPos(int x, int y, int z)
	{
		return new BlockPos(x, y, z);
	}
	
	public static Block getBlock(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock();
	}
}
