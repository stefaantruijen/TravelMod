package com.tvdp.travelmod.util.handlers;

import com.tvdp.travelmod.objects.blocks.machines.furnace.ContainerTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.furnace.GuiTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.furnace.TileEntityTravelFurnace;
import com.tvdp.travelmod.objects.blocks.machines.portal.GuiTravelPortal;
import com.tvdp.travelmod.objects.blocks.machines.workbench.ContainerTravelWorkBench;
import com.tvdp.travelmod.objects.blocks.machines.workbench.GuiTravelWorkBench;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Reference.GUI_TRAVEL_FURNACE)return new ContainerTravelFurnace(player.inventory, (TileEntityTravelFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if (ID == Reference.GUI_TRAVEL_WORKBENCH)return new ContainerTravelWorkBench(player.inventory, world, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Reference.GUI_TRAVEL_FURNACE)return new GuiTravelFurnace(player.inventory, (TileEntityTravelFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		if (ID == Reference.GUI_TRAVEL_WORKBENCH)return new GuiTravelWorkBench(player.inventory, world, x, y, z);
		if (ID == Reference.GUI_TRAVEL_WORKBENCH)return new GuiTravelPortal(null);
		return null;
	}

}
