package com.tvdp.travelmod.advancements;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class BasicTrigger implements ICriterionTrigger<BasicTriggerCriterionInstance>, IModTrigger {
	private final ResourceLocation id;
	private final Map<PlayerAdvancements, BasicTriggerListeners> listeners = new HashMap<>();

	public BasicTrigger(String id) {
		this.id = new ResourceLocation(id);
	}

	public BasicTrigger(ResourceLocation id) {
		this.id = id;
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<BasicTriggerCriterionInstance> listener) {
		BasicTriggerListeners tameAnimalTriggerListeners = this.listeners.get(playerAdvancementsIn);

		if (tameAnimalTriggerListeners == null) {
			tameAnimalTriggerListeners = new BasicTriggerListeners(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, tameAnimalTriggerListeners);
		}

		tameAnimalTriggerListeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn,
			Listener<BasicTriggerCriterionInstance> listener) {
		BasicTriggerListeners tameAnimalTriggerListeners = this.listeners.get(playerAdvancementsIn);

		if (tameAnimalTriggerListeners != null) {
			tameAnimalTriggerListeners.remove(listener);

			if (tameAnimalTriggerListeners.isEmpty()) {
				this.listeners.remove(playerAdvancementsIn);
			}
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
		this.listeners.remove(playerAdvancementsIn);
	}

	/**
	 * Trigger.
	 *
	 * @param parPlayer the player
	 */
	public void trigger(EntityPlayerMP player) {
		BasicTriggerListeners listeners = this.listeners.get(player.getAdvancements());

		if (listeners != null) {
			listeners.trigger(player);
		}
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 *
	 * @param json    the json
	 * @param context the context
	 * @return the tame bird trigger. instance
	 */
	@Override
	public BasicTriggerCriterionInstance deserializeInstance(JsonObject arg0, JsonDeserializationContext arg1) {
		return new BasicTriggerCriterionInstance(this.getId());
	}

}
