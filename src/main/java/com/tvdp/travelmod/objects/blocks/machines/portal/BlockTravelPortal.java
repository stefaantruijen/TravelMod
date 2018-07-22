package com.tvdp.travelmod.objects.blocks.machines.portal;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.objects.blocks.BlockBase;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTravelPortal extends BlockBase implements ITileEntityProvider
{
	public BlockTravelPortal(String name, Material material)
	{
		super(name, material);
		
		setHarvestLevel("pickaxe", 4);
		setHardness(10f);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_TRAVEL_PORTAL, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityTravelPortal();
	}
}
