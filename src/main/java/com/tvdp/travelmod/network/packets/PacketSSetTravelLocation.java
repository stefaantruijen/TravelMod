package com.tvdp.travelmod.network.packets;

import com.tvdp.travelmod.api.IPortalBlockLocationed;
import com.tvdp.travelmod.objects.blocks.machines.portal.TileEntityTravelPortal;
import com.tvdp.travelmod.util.BlockUtils;
import com.tvdp.travelmod.util.WorldUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSSetTravelLocation implements IMessage
{
	private String location;
	private int x, y, z;
	
	public PacketSSetTravelLocation()
	{
		
	}
	
	public PacketSSetTravelLocation(int x, int y, int z, String location)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.location = location;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		ByteBufUtils.writeUTF8String(buf, location);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.location = ByteBufUtils.readUTF8String(buf);
	}
	
	public static class Handler extends PacketHelper implements IMessageHandler<PacketSSetTravelLocation, IMessage>
	{
		@Override
		public IMessage onMessage(PacketSSetTravelLocation packet, MessageContext ctx)
		{
			WorldUtils.addScheduledTask(getWorld(ctx.getServerHandler().player), () -> {
				BlockPos blockPos = BlockUtils.toPos(packet.x, packet.y, packet.z);
				String location = packet.location;
				EntityPlayer player = ctx.getServerHandler().player;
				
				if (getWorld(player).getTileEntity(blockPos) != null)
				{
					((TileEntityTravelPortal)getWorld(player).getTileEntity(blockPos)).setLocation(location);
				}
			});
			
			return null;
		}
	}
}
