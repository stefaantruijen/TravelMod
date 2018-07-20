package com.tvdp.travelmod;

import com.tvdp.travelmod.proxy.CommonProxy;
import com.tvdp.travelmod.tabs.TravelModTabMain;
import com.tvdp.travelmod.util.Reference;
import com.tvdp.travelmod.util.handlers.GuiHandler;
import com.tvdp.travelmod.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
	public static final CreativeTabs TRAVELMODMAINTAB = new TravelModTabMain("travelmodmaintab");
	
	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) { RegistryHandler.otherRegistries(); }
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		RegistryHandler.addRecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event) { RegistryHandler.serverRegistries(event); }
}
