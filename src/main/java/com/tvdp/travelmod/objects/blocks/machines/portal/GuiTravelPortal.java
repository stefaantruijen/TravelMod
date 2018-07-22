package com.tvdp.travelmod.objects.blocks.machines.portal;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.api.IPortalBlockLocationed;
import com.tvdp.travelmod.network.packets.PacketSSetTravelLocation;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public class GuiTravelPortal extends GuiScreen
{
    private GuiTextField idTextField;
    private final TileEntityTravelPortal tileentity;
    private final EntityPlayer player;
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
    private GuiButton travelBtn;

    public GuiTravelPortal(TileEntityTravelPortal tileentity, EntityPlayer player)
    {
        this.tileentity = tileentity;
        this.player = player;
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
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 4 - 50 - 100, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.done")));
        this.cancelBtn = this.addButton(new GuiButton(1, this.width / 2 + 4 - 50, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.cancel")));
        this.travelBtn = this.addButton(new GuiButton(2, this.width / 2 + 12 + 50, this.height / 4 + 120 + 12, 100, 20, I18n.format("gui.travel")));
        this.idTextField = new GuiTextField(2, this.fontRenderer, this.width / 2 - 150, 70, 300, 20);
        this.idTextField.setMaxStringLength(32500);
        this.idTextField.setFocused(true);
        this.idTextField.setText(tileentity.getLocation());
        this.doneBtn.enabled = false;
        
        if (tileentity.getLocation().equals(""))
        {
        	this.travelBtn.enabled = false;
        }
    }

    public void updateGui()
    {
        this.idTextField.setText(tileentity.getLocation());
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
    
    @Override
    public boolean doesGuiPauseGame()
    {
    	return true;
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 0)
            {	
            	tileentity.setLocation(this.idTextField.getText().toString());
            	System.out.println("updated text");
            	//Main.network.sendToServer(new PacketSSetTravelLocation(tileentity.getPos().getX(), tileentity.getPos().getY(), tileentity.getPos().getZ(), idTextField.getText()));

                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 2)
            {
            	//travel!!
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.idTextField.textboxKeyTyped(typedChar, keyCode);

        doneBtn.enabled = true;
        
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
        this.drawString(this.fontRenderer, I18n.format("travel.id"), this.width / 2 - 150, 60, 10526880);
        this.idTextField.drawTextBox();
        int i = 75;
        int j = 0;

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
