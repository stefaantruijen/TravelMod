package com.tvdp.travelmod.objects.item.tool;

import com.tvdp.travelmod.Main;
import com.tvdp.travelmod.init.ItemInit;
import com.tvdp.travelmod.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class TravelPickAxe extends ItemPickaxe implements IHasModel
{
	public TravelPickAxe(String name, ToolMaterial material)
	{
		this(name, Main.TRAVELMODMAINTAB, material);
	}
	
	public TravelPickAxe(String name, CreativeTabs tab, ToolMaterial material)
	{
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack item = playerIn.getHeldItem(handIn);
		
		item.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
}
