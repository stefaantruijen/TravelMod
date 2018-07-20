package com.tvdp.travelmod.objects.blocks.machines.furnace.slots;

import com.tvdp.travelmod.objects.blocks.machines.furnace.TileEntityTravelFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTravelFurnaceFuel extends Slot
{
	public SlotTravelFurnaceFuel(IInventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return TileEntityTravelFurnace.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return super.getItemStackLimit(stack);
	}
}
