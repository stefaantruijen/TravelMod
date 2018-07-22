package com.tvdp.travelmod.objects.blocks.machines.portal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTravelPortal extends TileEntity
{
	private String location;
	
	public TileEntityTravelPortal()
	{
		this.setLocation("Test");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.location = compound.getString("Location");
		System.out.println("Location: " + this.getLocation());
		System.out.println("Loading from NBT");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setString("Location", this.getLocation());
		System.out.println("Location: " + this.getLocation());
		System.out.println("writing to NBT");
		return compound;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	public void setLocation(String newLocation)
	{
		this.location = newLocation;
		this.markDirty();
	}
	
	public String getLocation()
	{
		return this.location;
	}
}
