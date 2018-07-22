package com.tvdp.travelmod.advancements;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.tvdp.travelmod.init.DimensionInit;
import com.tvdp.travelmod.util.Reference;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class TravelDimensionEnterTrigger implements ICriterionTrigger<TravelDimensionEnterTrigger.Instance> {
	private static final ResourceLocation ID = new ResourceLocation(Reference.MODID + "travel_dimension_entered");
	private final Map<PlayerAdvancements, TravelDimensionEnterTrigger.Listeners> listeners = Maps.<PlayerAdvancements, TravelDimensionEnterTrigger.Listeners>newHashMap();

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener) {
		TravelDimensionEnterTrigger.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners == null) {
			consumeitemtrigger$listeners = new TravelDimensionEnterTrigger.Listeners(playerAdvancementsIn);
			this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
		}

		consumeitemtrigger$listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener) {
		TravelDimensionEnterTrigger.Listeners consumeitemtrigger$listeners = this.listeners.get(playerAdvancementsIn);

		if (consumeitemtrigger$listeners != null) {
			consumeitemtrigger$listeners.remove(listener);

			if (consumeitemtrigger$listeners.isEmpty()) {
				this.listeners.remove(playerAdvancementsIn);
			}
		}
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
		this.listeners.remove(playerAdvancementsIn);
	}

	@Override
	public TravelDimensionEnterTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new TravelDimensionEnterTrigger.Instance();
	}

	public static class Instance extends AbstractCriterionInstance {
		public Instance() {
			super(TravelDimensionEnterTrigger.ID);
		}

		public boolean test(EntityPlayer player) {
			if (player.getEntityWorld().provider.getDimensionType() == DimensionInit.TRAVEL)
			{
				return true;
			} else {
				return false;
			}
		}
	}

	public void trigger(EntityPlayerMP player) {
		TravelDimensionEnterTrigger.Listeners enterblocktrigger$listeners = this.listeners.get(player.getAdvancements());

		if (enterblocktrigger$listeners != null) {
			enterblocktrigger$listeners.trigger((EntityPlayer) player);
		}
	}

	static class Listeners {
		private final PlayerAdvancements playerAdvancements;
		private final Set<ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance>>newHashSet();

		public Listeners(PlayerAdvancements playerAdvancementsIn) {
			this.playerAdvancements = playerAdvancementsIn;
		}

		public boolean isEmpty() {
			return this.listeners.isEmpty();
		}

		public void add(ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener) {
			this.listeners.add(listener);
		}

		public void remove(ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener) {
			this.listeners.remove(listener);
		}

		public void trigger(EntityPlayer player) {
			List<ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance>> list = null;

			for (ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener : this.listeners) {
				if (((TravelDimensionEnterTrigger.Instance) listener.getCriterionInstance()).test(player)) {
					if (list == null) {
						list = Lists.<ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance>>newArrayList();
					}

					list.add(listener);
				}
			}

			if (list != null) {
				for (ICriterionTrigger.Listener<TravelDimensionEnterTrigger.Instance> listener1 : list) {
					listener1.grantCriterion(this.playerAdvancements);
				}
			}
		}
	}
}
