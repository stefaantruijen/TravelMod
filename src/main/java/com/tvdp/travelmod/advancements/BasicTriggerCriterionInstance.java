package com.tvdp.travelmod.advancements;

import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.util.ResourceLocation;

class BasicTriggerCriterionInstance extends AbstractCriterionInstance
{

    /**
     * Instantiates a new instance.
     */
    public BasicTriggerCriterionInstance(ResourceLocation parID)
    {
        super(parID);
    }

    /**
     * Test.
     *
     * @return true, if successful
     */
    public boolean test()
    {
        return true;
    }
}


