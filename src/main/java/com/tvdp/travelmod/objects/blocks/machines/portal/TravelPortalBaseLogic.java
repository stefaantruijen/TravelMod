package com.tvdp.travelmod.objects.blocks.machines.portal;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TravelPortalBaseLogic implements ICommandSender
{
    /** The command stored in the command block. */
    private String IdStored = "";
    /** The custom name of the command block. (defaults to "@") */
    private String customName = "TravelPortal";

    public NBTTagCompound writeToNBT(NBTTagCompound p_189510_1_)
    {
        p_189510_1_.setString("ID", this.IdStored);
        p_189510_1_.setString("CustomName", this.customName);
        return p_189510_1_;
    }

    /**
     * Reads NBT formatting and stored data into variables.
     */
    public void readDataFromNBT(NBTTagCompound nbt)
    {
        this.IdStored = nbt.getString("ID");

        if (nbt.hasKey("CustomName", 8))
        {
            this.customName = nbt.getString("CustomName");
        }
    }

    /**
     * Returns {@code true} if the CommandSender is allowed to execute the command, {@code false} if not
     */
    public boolean canUseCommand(int permLevel, String commandName)
    {
        return true;
    }

    /**
     * Sets the command.
     */
    public void setCommand(String id)
    {
        this.IdStored = id;
    }

    /**
     * Returns the command of the command block.
     */
    public String getCommand()
    {
        return this.IdStored;
    }

    public boolean trigger(World worldIn)
    {
        if (!worldIn.isRemote)
        {
            if ("Searge".equalsIgnoreCase(this.IdStored))
            {
                return true;
            }
            else
            {
                MinecraftServer minecraftserver = this.getServer();

                if (minecraftserver != null && minecraftserver.isAnvilFileSet() && minecraftserver.isCommandBlockEnabled())
                {
                    try
                    {
                        //change dimension
                    }
                    catch (Throwable throwable)
                    {
                        CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Executing command block");
                        CrashReportCategory crashreportcategory = crashreport.makeCategory("Command to be executed");
                        crashreportcategory.addDetail("Command", new ICrashReportDetail<String>()
                        {
                            public String call() throws Exception
                            {
                                return TravelPortalBaseLogic.this.getCommand();
                            }
                        });
                        crashreportcategory.addDetail("Name", new ICrashReportDetail<String>()
                        {
                            public String call() throws Exception
                            {
                                return TravelPortalBaseLogic.this.getName();
                            }
                        });
                        throw new ReportedException(crashreport);
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.customName;
    }

    public void setName(String name)
    {
        this.customName = name;
    }

    /**
     * Returns true if the command sender should be sent feedback about executed commands
     */
    public boolean sendCommandFeedback()
    {
        MinecraftServer minecraftserver = this.getServer();
        return minecraftserver == null || !minecraftserver.isAnvilFileSet() || minecraftserver.worlds[0].getGameRules().getBoolean("commandBlockOutput");
    }

    public abstract void updateCommand();

    public boolean tryOpenEditCommandBlock(EntityPlayer playerIn)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public abstract void fillInInfo(ByteBuf buf);
}
