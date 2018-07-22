package com.tvdp.travelmod.objects.blocks.machines.portal;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TravelPortalTileEntity extends TileEntity
{
    private boolean sendToClient;
	private final AbstractTravelPortalBaseLogicImpl commandBlockLogic;

	public TravelPortalTileEntity() {
		commandBlockLogic = new AbstractTravelPortalBaseLogicImpl(this.pos, this.world, this);
	}

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        this.commandBlockLogic.writeToNBT(compound);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.commandBlockLogic.readDataFromNBT(compound);
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        if (this.isSendToClient())
        {
            this.setSendToClient(false);
            NBTTagCompound nbtTagCompound = this.writeToNBT(new NBTTagCompound());
            return new SPacketUpdateTileEntity(this.pos, 2, nbtTagCompound);
        }
        else
        {
            return null;
        }
    }

    public boolean onlyOpsCanSetNbt()
    {
        return true;
    }

    public AbstractTravelPortalBaseLogic getCommandBlockLogic()
    {
        return this.commandBlockLogic;
    }

    public boolean isSendToClient()
    {
        return this.sendToClient;
    }

	public void setSendToClient(boolean sendToClient)
    {
        this.sendToClient = sendToClient;
    }

    /**
     * validates a tile entity
     */
    public void validate()
    {
        this.blockType = null;
        super.validate();
    }
}
