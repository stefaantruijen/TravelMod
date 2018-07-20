package com.tvdp.travelmod.init;

import java.util.ArrayList;
import java.util.List;

import com.tvdp.travelmod.objects.item.ItemBase;
import com.tvdp.travelmod.objects.item.tool.TravelPickAxe;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//materials
	public static final ToolMaterial TRAVEL_TOOL = EnumHelper.addToolMaterial("travel_tool", 4, 750, 7, 1, 0);
	
	//items
	public static final Item ANDAMANTIUM_INGOT = new ItemBase("andamantium_ingot");
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");
	public static final Item TRAVEL_STAR = new ItemBase("travel_star");
	public static final Item TRAVEL_DUST = new ItemBase("travel_dust");
	public static final Item TRAVEL_REFINER = new ItemBase("travel_refiner");
	
	//tools
	public static final Item TRAVEL_PICKAXE = new TravelPickAxe("travel_pickaxe", TRAVEL_TOOL);
}
