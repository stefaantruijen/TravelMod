package com.tvdp.travelmod.advancements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.Lists;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Triggers
{
	private static final List<BasicTrigger> TRIGGERS = Lists.<BasicTrigger>newArrayList();

    public static final BasicTrigger TRAVEL_DIMENSION_ENTER_TRIGGER = register("travel_dimension_entered");
    
    private static BasicTrigger register(String name)
    {
        BasicTrigger trigger = new BasicTrigger(new ResourceLocation(Reference.MODID, name));
        TRIGGERS.add(trigger);
        return trigger;
    }
    
    public static void trigger(IModTrigger trigger, EntityPlayer player)
    {
        if(player instanceof EntityPlayerMP)
        {
            trigger.trigger((EntityPlayerMP) player);
        }
    }

    public static void init()
    {
        Method method;
        try
        {
            method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);
            method.setAccessible(true);
            for(int i = 0; i < TRIGGERS.size(); i++)
            {
                method.invoke(null, TRIGGERS.get(i));
            }
        }
        catch(SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
        	System.out.println("Failed to register triggers !");
			e.printStackTrace();
        }
    }
}
