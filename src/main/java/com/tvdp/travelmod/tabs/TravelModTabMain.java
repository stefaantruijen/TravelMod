package com.tvdp.travelmod.tabs;

import com.tvdp.travelmod.init.BlockInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TravelModTabMain extends CreativeTabs
{
	public TravelModTabMain(String label) {
		super("travelmodmaintab");
		
		this.setBackgroundImageName("travelmodmain.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(BlockInit.ANDAMANTIUM_BLOCK));
	}
	
	@Override
	public boolean hasSearchBar()
	{
		return true;
	}
	
}
