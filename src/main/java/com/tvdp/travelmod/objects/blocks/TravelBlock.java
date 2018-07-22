package com.tvdp.travelmod.objects.blocks;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;

public class TravelBlock extends Block implements IHasModel
{
	public TravelBlock(String name)
	{
		this(name, Main.TRAVELMODMAINTAB);
	}
	
	public TravelBlock(String name, CreativeTabs tab)
	{
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setHarvestLevel("pickaxe", 4);
		setHardness(3F);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn)
	{
		return false;
	}
	
	@Override
	public boolean isBurning(IBlockAccess world, BlockPos pos)
	{
		return false;
	}
}
