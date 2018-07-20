package com.tvdp.travelmod.init;

import java.util.ArrayList;
import java.util.List;

import com.tvdp.travelmod.objects.blocks.BlockBase;
import com.tvdp.travelmod.objects.blocks.CopperAndamantiumCompressed;
import com.tvdp.travelmod.objects.blocks.TravelBlock;
import com.tvdp.travelmod.objects.blocks.TravelGlass;
import com.tvdp.travelmod.objects.blocks.TravelGlassPane;
import com.tvdp.travelmod.objects.blocks.machines.furnace.BlockTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.portal.TravelBlockPortal;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON);
	public static final Block ANDAMANTIUM_BLOCK = new BlockBase("andamantium_block", Material.IRON);
	public static final Block COPPER_ANDAMANTIUM_COMPRESSED = new CopperAndamantiumCompressed("copper_andamantium_compressed");
	
	public static final Block COPPER_ORE = new BlockBase("copper_ore", Material.IRON);
	public static final Block ANDAMANTIUM_ORE = new BlockBase("andamantium_ore", Material.IRON);
	public static final Block TRAVEL_ORE = new BlockBase("travel_ore", Material.IRON);
	
	public static final Block TRAVEL_BLOCK = new TravelBlock("travel_block");
	public static final Block TRAVEL_BLOCK_DECORATION = new TravelBlock("travel_block_decoration");
	public static final Block TRAVEL_BLOCK_PORTAL = new TravelBlockPortal("travel_block_portal");
	public static final Block TRAVEL_GLASS = new TravelGlass("travel_glass", Material.GLASS);
	public static final Block TRAVEL_GLASS_PANE = new TravelGlassPane("travel_glass_pane", Material.GLASS);
	
	//machines
	public static final Block TRAVEL_FURNACE = new BlockTravelFurnace("travel_furnace");
	public static final Block TRAVEL_WORKBENCH = new TravelWorkBenchBlock("travel_workbench");
}
