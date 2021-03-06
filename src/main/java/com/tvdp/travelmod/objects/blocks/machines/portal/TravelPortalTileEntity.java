package com.tvdp.travelmod.objects.blocks.machines.portal;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandResultStats;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TravelPortalTileEntity extends TileEntity
{
    private boolean sendToClient;
    private final TravelPortalBaseLogic commandBlockLogic = new TravelPortalBaseLogic()
    {
        /**
         * Get the position in the world. <b>{@code null} is not allowed!</b> If you are not an entity in the world,
         * return the coordinates 0, 0, 0
         */
        public BlockPos getPosition()
        {
            return TravelPortalTileEntity.this.pos;
        }
        /**
         * Get the position vector. <b>{@code null} is not allowed!</b> If you are not an entity in the world, return
         * 0.0D, 0.0D, 0.0D
         */
        public Vec3d getPositionVector()
        {
            return new Vec3d((double)TravelPortalTileEntity.this.pos.getX() + 0.5D, (double)TravelPortalTileEntity.this.pos.getY() + 0.5D, (double)TravelPortalTileEntity.this.pos.getZ() + 0.5D);
        }
        /**
         * Get the world, if available. <b>{@code null} is not allowed!</b> If you are not an entity in the world,
         * return the overworld
         */
        public World getEntityWorld()
        {
            return TravelPortalTileEntity.this.getWorld();
        }
        /**
         * Sets the command.
         */
        public void setCommand(String command)
        {
            super.setCommand(command);
            TravelPortalTileEntity.this.markDirty();
        }
        public void updateCommand()
        {
            IBlockState iblockstate = TravelPortalTileEntity.this.world.getBlockState(TravelPortalTileEntity.this.pos);
            TravelPortalTileEntity.this.getWorld().notifyBlockUpdate(TravelPortalTileEntity.this.pos, iblockstate, iblockstate, 3);
        }
        
        /**
         * Fills in information about the command block for the packet. entityId for the minecart version, and X/Y/Z for
         * the traditional version
         */
        @SideOnly(Side.CLIENT)
        public void fillInInfo(ByteBuf buf)
        {
            buf.writeInt(TravelPortalTileEntity.this.pos.getX());
            buf.writeInt(TravelPortalTileEntity.this.pos.getY());
            buf.writeInt(TravelPortalTileEntity.this.pos.getZ());
        }
        /**
         * Get the Minecraft server instance
         */
        public MinecraftServer getServer()
        {
            return TravelPortalTileEntity.this.world.getMinecraftServer();
        }
    };

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
            NBTTagCompound nbttagcompound = this.writeToNBT(new NBTTagCompound());
            return new SPacketUpdateTileEntity(this.pos, 2, nbttagcompound);
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

    public TravelPortalBaseLogic getCommandBlockLogic()
    {
        return this.commandBlockLogic;
    }

    public boolean isSendToClient()
    {
        return this.sendToClient;
    }

    public void setSendToClient(boolean p_184252_1_)
    {
        this.sendToClient = p_184252_1_;
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
