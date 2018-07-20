package com.tvdp.travelmod.objects.blocks;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.util.IHasModel;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class TravelGlass extends BlockGlass implements IHasModel
{
	public TravelGlass(String name, Material material)
	{
		super(material, true);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(Main.TRAVELMODMAINTAB);
		setSoundType(SoundType.GLASS);
		setHarvestLevel("pickaxe", 1);
		setHardness(3);
		setResistance(2f);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
