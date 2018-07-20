package com.tvdp.travelmod.objects.item;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name)
	{
		this(name, Main.TRAVELMODMAINTAB);
	}
	
	public ItemBase(String name, CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
