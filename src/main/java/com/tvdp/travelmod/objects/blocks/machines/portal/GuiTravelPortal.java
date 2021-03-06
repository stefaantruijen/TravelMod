package com.tvdp.travelmod.objects.blocks.machines.portal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.tvdp.travelmod.world.WorldLocationSave;

import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.math.BlockPos;

public class GuiTravelPortal extends GuiScreen
{
	/** Text field containing the command block's command. */
    private GuiTextField idTextField;
    private final TravelPortalTileEntity commandBlock;
    /** "Done" button for the GUI. */
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton travelBtn;
    private Map<BlockPos, String> locations = new HashMap<BlockPos, String>();

    public GuiTravelPortal(TravelPortalTileEntity commandBlockIn)
    {
    	WorldLocationSave save = (WorldLocationSave)this.mc.world.loadData(WorldLocationSave.class, "TRAVEL_LOCATIONS");
    	for (int i = 0; i < save.x.length && i < save.y.length && i < save.z.length && i < save.id.length; ++i)
    	{
    		locations.put(new BlockPos(save.x[i], save.y[i], save.z[i]), save.id[i]);
    	}
        this.commandBlock = commandBlockIn;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.idTextField.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        final TravelPortalBaseLogic commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 4 - 100, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.done")));
        this.cancelBtn = this.addButton(new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.cancel")));
        this.travelBtn = this.addButton(new GuiButton(2, this.width / 2 + 4 + 100, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.travel")));
        this.idTextField = new GuiTextField(2, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
        this.idTextField.setMaxStringLength(32500);
        this.idTextField.setFocused(true);
        this.doneBtn.enabled = false;
        this.travelBtn.enabled = false;
    }

    public void updateGui()
    {
        TravelPortalBaseLogic commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
        this.idTextField.setText(commandblockbaselogic.getCommand());
        this.doneBtn.enabled = true;
        this.travelBtn.enabled = true;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            TravelPortalBaseLogic commandblockbaselogic = this.commandBlock.getCommandBlockLogic();

            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 0)
            {
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                commandblockbaselogic.fillInInfo(packetbuffer);
                packetbuffer.writeString(this.idTextField.getText());
                this.mc.getConnection().sendPacket(new CPacketCustomPayload("MC|AutoCmd", packetbuffer));

                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 2)
            {
                commandblockbaselogic.trigger(this.mc.world);
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.idTextField.textboxKeyTyped(typedChar, keyCode);

        if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 1)
            {
                this.actionPerformed(this.cancelBtn);
            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.idTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("travel.setID"), this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, I18n.format("travel.id"), this.width / 2 - 150, 40, 10526880);
        this.idTextField.drawTextBox();
        int i = 75;
        int j = 0;

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
