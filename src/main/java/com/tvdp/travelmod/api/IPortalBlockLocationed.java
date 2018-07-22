package com.tvdp.travelmod.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Implementing this interface designates a TileEntity as being password-protected.
 * Implementing this allows you to use {@link GuiSetPassword} and {@link GuiCheckPassword} to easily set your block's password.
 *
 * @author Geforce
 */
public interface IPortalBlockLocationed {

	/**
	 * Return your TileEntity's password variable here.
	 * If the password is empty or not set yet, return null.
	 *
	 * @return The password.
	 */
	public String getLocation();

	/**
	 * Save newly created passwords to your TileEntity here.
	 * You are responsible for reading and writing the password
	 * to your NBTTagCompound in writeToNBT() and readFromNBT().
	 *
	 * @param password The new password to be saved.
	 */
	public void setLocation(String location);

}