package com.tvdp.travelmod.objects.blocks.machines.workbench;

import java.util.Random;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.objects.blocks.BlockBase;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TravelWorkBenchBlock extends BlockBase
{
	public static final AxisAlignedBB TRAVEL_WORKBENCH_AABB = new AxisAlignedBB(0d, 0d, 0d, 1d, 1d, 1d);
	
	public TravelWorkBenchBlock(String name)
	{
		super(name, Material.IRON, Main.TRAVELMODMAINTAB);
		setSoundType(SoundType.WOOD);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.5f);
		setResistance(5.0f);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return TRAVEL_WORKBENCH_AABB;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(BlockInit.TRAVEL_WORKBENCH).setMaxStackSize(1);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(BlockInit.TRAVEL_WORKBENCH).setMaxStackSize(1));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(Main.instance, Reference.GUI_TRAVEL_WORKBENCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	public static void setState(World worldIn, BlockPos pos)
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if (tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		return this.getDefaultState();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, this.getDefaultState());
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
}
