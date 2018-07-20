package com.tvdp.travelmod.objects.blocks.machines.workbench;

import com.tvdp.travelmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class GuiTravelWorkBench extends GuiContainer
{
	private final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/travel_workbench.png");
	private final InventoryPlayer player;
	
	public GuiTravelWorkBench(InventoryPlayer playerIn, World world, int x, int y, int z)
	{
		super(new ContainerTravelWorkBench(playerIn, world, x, y, z));
		this.player = playerIn;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tilename = new TextComponentTranslation("container.travel_workbench").getUnformattedText();
		this.fontRenderer.drawString(tilename, (this.xSize / 2 - this.fontRenderer.getStringWidth(tilename) / 2), 4, 65);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 65);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
	}
}
