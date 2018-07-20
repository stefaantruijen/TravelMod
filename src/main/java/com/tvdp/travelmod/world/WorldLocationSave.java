package com.tvdp.travelmod.world;

import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class WorldLocationSave extends WorldSavedData
{
	public int[] x;
	public int[] y;
	public int[] z;
	public String[] id;
	
	public WorldLocationSave(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		x = nbt.getIntArray("X");
		y = nbt.getIntArray("Y");
		z = nbt.getIntArray("Z");
		String locations = nbt.getString("Locations");
		id = locations.split("=x=_=x=");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setIntArray("X", x);
		compound.setIntArray("Y", y);
		compound.setIntArray("Z", z);
		compound.setString("Locations", String.join("=x=_=x=", id));
		return compound;
	}

}
