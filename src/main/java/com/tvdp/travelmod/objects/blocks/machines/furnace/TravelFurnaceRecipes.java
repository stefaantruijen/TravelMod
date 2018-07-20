package com.tvdp.travelmod.objects.blocks.machines.furnace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.tvdp.travelmod.init.BlockInit;
import com.tvdp.travelmod.init.ItemInit;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TravelFurnaceRecipes
{
	private static final TravelFurnaceRecipes INSTANCE = new TravelFurnaceRecipes();
	private final Table<ItemStack, ItemStack, Map<ItemStack, ItemStack>> smeltingList = HashBasedTable.<ItemStack, ItemStack, Map<ItemStack, ItemStack>>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static TravelFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private TravelFurnaceRecipes() 
	{
		addTravelRecipe(new ItemStack(BlockInit.TRAVEL_BLOCK), new ItemStack(BlockInit.COPPER_BLOCK), new ItemStack(BlockInit.TRAVEL_BLOCK_DECORATION), new ItemStack(ItemInit.TRAVEL_DUST, 1), 4.0F);
		addTravelRecipe(new ItemStack(Blocks.GOLD_BLOCK), new ItemStack(ItemInit.ANDAMANTIUM_INGOT), new ItemStack(ItemInit.TRAVEL_STAR), new ItemStack(ItemInit.TRAVEL_DUST, 3), 6.0f);
		addTravelRecipe(new ItemStack(BlockInit.ANDAMANTIUM_ORE), new ItemStack(ItemInit.TRAVEL_REFINER, 2), new ItemStack(ItemInit.ANDAMANTIUM_INGOT, 2), new ItemStack(ItemInit.TRAVEL_DUST, 1), 3.0F);
		addTravelRecipe(new ItemStack(BlockInit.COPPER_ORE), new ItemStack(ItemInit.TRAVEL_REFINER, 2), new ItemStack(ItemInit.COPPER_INGOT, 2), new ItemStack(ItemInit.TRAVEL_DUST, 1), 3.0F);
		addTravelRecipe(new ItemStack(BlockInit.COPPER_ANDAMANTIUM_COMPRESSED), new ItemStack(ItemInit.TRAVEL_REFINER), new ItemStack(BlockInit.ANDAMANTIUM_BLOCK, 5), new ItemStack(BlockInit.COPPER_BLOCK, 5), 1.0f);
		
		for (Entry<ItemStack, ItemStack> ent : FurnaceRecipes.instance().getSmeltingList().entrySet())
		{
			addTravelRecipe(ent.getKey(), ItemStack.EMPTY, ent.getValue(), new ItemStack(ItemInit.TRAVEL_DUST, 1), FurnaceRecipes.instance().getSmeltingExperience(ent.getValue()));
		}
	}

	
	public void addTravelRecipe(ItemStack input1, ItemStack input2, ItemStack result, ItemStack rest, float experience) 
	{
		if(getTravelResult(input1, input2) != null) return;
		
		Map<ItemStack, ItemStack> passStack = new HashMap<ItemStack, ItemStack>();
		passStack.put(result, rest);
		this.smeltingList.put(input2, input1, passStack);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public List<ItemStack> getTravelResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> entry : this.smeltingList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, Map<ItemStack, ItemStack>> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						for (Entry<ItemStack, ItemStack> ent1 : ent.getValue().entrySet())
						{
							List<ItemStack> result = Lists.newArrayList();
							result.add(ent1.getKey());
							result.add(ent1.getValue());
							return result;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	public void shrinkInputs(ItemStack input1, ItemStack input2)
	{
		for (Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> entry : this.smeltingList.columnMap().entrySet())
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, Map<ItemStack, ItemStack>> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						input1.shrink(entry.getKey().getCount());
						input2.shrink(ent.getKey().getCount());
					}
				}
			}
		}
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return (stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata())) && stack1.getCount() >= stack2.getCount();
	}
	
	public Table<ItemStack, ItemStack, Map<ItemStack, ItemStack>> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getTravelExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
