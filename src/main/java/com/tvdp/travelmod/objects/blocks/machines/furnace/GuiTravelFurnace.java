package com.tvdp.travelmod.objects.blocks.machines.furnace;

import java.util.List;

import com.tvdp.travelmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiTravelFurnace extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/travel_furnace.png");
	private final InventoryPlayer player;
	private final TileEntityTravelFurnace tileentity;
	
	public GuiTravelFurnace(InventoryPlayer player, TileEntityTravelFurnace tileentity)
	{
		super(new ContainerTravelFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tilename = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tilename, (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2), 5, 65);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 65);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileEntityTravelFurnace.isBurning(tileentity))
		{
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 26, this.guiTop + 55 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 35, 176, 14, l + 1, 16);
	}
	
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tileentity.getField(1);
		if(i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(2);
		int j = this.tileentity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
	}
}
