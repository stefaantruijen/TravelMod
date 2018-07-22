package com.tvdp.travelmod.util.handlers;

import com.tvdp.travelmod.advancements.Triggers;
import com.tvdp.travelmod.commands.CommandDimensionTeleport;
import com.tvdp.travelmod.init.BiomeInit;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.init.DimensionInit;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.network.packets.PacketSSetTravelLocation;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchCraftingDelegateRecipe;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchCraftingManager;
import com.tvdp.travelmod.objects.blocks.machines.workbench.TravelWorkBenchRecipes;
import com.tvdp.travelmod.util.IHasModel;
import com.tvdp.travelmod.world.gen.WorldGenCustomOres;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
public class RegistryHandler
{	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if (block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event)
	{
		final IInventory craftMatrix = null;
		for(int i = 0; i < event.craftMatrix.getSizeInventory(); i++)
		{
			if (event.craftMatrix.getStackInSlot(i) != null)
			{
				ItemStack item0 = event.craftMatrix.getStackInSlot(i);
				if (item0 != null && item0.getItem() == Item.getItemFromBlock(BlockInit.TRAVEL_WORKBENCH))
				{
					ItemStack k = new ItemStack(BlockInit.TRAVEL_WORKBENCH, 1, (item0.getItemDamage() + 1));
					
					if (k.getItemDamage() >= k.getMaxDamage())
					{
						k.shrink(1);
					}
					
					event.craftMatrix.setInventorySlotContents(i, k);
				}
			}
		}
	}
	
	public static void registerPackets(SimpleNetworkWrapper network)
	{
		//network.registerMessage(PacketSSetTravelLocation.Handler.class, PacketSSetTravelLocation.class, 1, Side.SERVER);
	}
	
	public static void otherRegistries()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		BiomeInit.registerBiomes();
		DimensionInit.registerDimensions();
		Triggers.init();
	}
	
	public static void addRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(BlockInit.ANDAMANTIUM_ORE), new ItemStack(ItemInit.ANDAMANTIUM_INGOT), 0.1f);
		GameRegistry.addSmelting(new ItemStack(BlockInit.COPPER_ORE), new ItemStack(ItemInit.COPPER_INGOT), 0.1f);
		
		TravelWorkBenchCraftingManager.init();
		for (TravelWorkBenchRecipes recipe : TravelWorkBenchCraftingManager.getListOfRecipes())
		{
			TravelWorkBenchCraftingManager.register(new TravelWorkBenchCraftingDelegateRecipe(recipe));
		}
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandDimensionTeleport());
	}
}
