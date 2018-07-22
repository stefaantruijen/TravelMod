package com.tvdp.travelmod.advancements;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.advancements.ICriterionTrigger.Listener;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;

public class BasicTriggerListeners {
	private final PlayerAdvancements playerAdvancements;
	private final Set<Listener<BasicTriggerCriterionInstance>> listeners = new HashSet<>();

	/**
	 * Instantiates a new listeners.
	 *
	 * @param playerAdvancementsIn the player advancements in
	 */
	public BasicTriggerListeners(PlayerAdvancements playerAdvancementsIn) {
		this.playerAdvancements = playerAdvancementsIn;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return this.listeners.isEmpty();
	}

	/**
	 * Adds the.
	 *
	 * @param listener the listener
	 */
	public void add(Listener<BasicTriggerCriterionInstance> listener) {
		this.listeners.add(listener);
	}

	/**
	 * Removes the.
	 *
	 * @param listener the listener
	 */
	public void remove(Listener<BasicTriggerCriterionInstance> listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Trigger.
	 *
	 * @param player the player
	 */
	public void trigger(EntityPlayerMP player) {
		List<Listener<BasicTriggerCriterionInstance>> list = null;

		for (Listener<BasicTriggerCriterionInstance> listener : this.listeners) {
			if (((BasicTriggerCriterionInstance) listener.getCriterionInstance()).test()) {
				if (list == null) {
					list = Collections.emptyList();
				}

				list.add(listener);
			}
		}

		if (list != null) {
			for (Listener<BasicTriggerCriterionInstance> listener1 : list) {
				listener1.grantCriterion(this.playerAdvancements);
			}
		}
	}
}