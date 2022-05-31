package ru.minersdream.stalker.main.tablist;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AmmoTab extends CreativeTabs {

	public AmmoTab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		ResourceLocation resourcelocation = new ResourceLocation("modularwarfare:stalker.7_62x54");
		return new ItemStack(Item.REGISTRY.getObject(resourcelocation));
	}

}
