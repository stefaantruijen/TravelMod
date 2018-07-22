package com.tvdp.travelmod.objects.blocks.machines.portal;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

final class AbstractTravelPortalBaseLogicImpl extends AbstractTravelPortalBaseLogic {

	private final BlockPos pos;
	private final World world;
	private final TravelPortalTileEntity travelPortalTileEntity;

	AbstractTravelPortalBaseLogicImpl(final BlockPos pos, final World world,
			final TravelPortalTileEntity travelPortalTileEntity) {
		assert pos != null;
		assert world != null;

		this.pos = pos;
		this.world = world;
		this.travelPortalTileEntity = travelPortalTileEntity;
	}

	/**
	 * Get the position in the world. <b>{@code null} is not allowed!</b> If you are
	 * not an entity in the world, return the coordinates 0, 0, 0
	 */
	public BlockPos getPosition() {
		return this.pos;
	}

	/**
	 * Get the position vector. <b>{@code null} is not allowed!</b> If you are not
	 * an entity in the world, return 0.0D, 0.0D, 0.0D
	 */
	public Vec3d getPositionVector() {
		return new Vec3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
				(double) this.pos.getZ() + 0.5D);
	}

	/**
	 * Get the world, if available. <b>{@code null} is not allowed!</b> If you are
	 * not an entity in the world, return the overworld.
	 */
	public World getEntityWorld() {
		return this.world;
	}

	/**
	 * Sets the command.
	 */
	public void setCommand(final String command) {
		assert command != null;

		super.setCommand(command);
		travelPortalTileEntity.markDirty();
	}

	public void updateCommand() {
		IBlockState blockState = this.world.getBlockState(this.pos);
		this.world.notifyBlockUpdate(this.pos, blockState, blockState, 3);
	}

	/**
	 * Fills in information about the command block for the packet. entityId for the
	 * minecart version, and X/Y/Z for the traditional version
	 */
	@SideOnly(Side.CLIENT)
	public void fillInInfo(ByteBuf buf) {
		buf.writeInt(this.pos.getX());
		buf.writeInt(this.pos.getY());
		buf.writeInt(this.pos.getZ());
	}

	/**
	 * Get the Minecraft server instance
	 */
	public MinecraftServer getServer() {
		return this.world.getMinecraftServer();
	}
}