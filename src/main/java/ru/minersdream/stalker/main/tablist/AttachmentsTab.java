package ru.minersdream.stalker.main.tablist;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AttachmentsTab extends CreativeTabs {

	public AttachmentsTab(String string) {
		super(string);
	}

	@Override
	public ItemStack getTabIconItem() {
		ResourceLocation resourcelocation = new ResourceLocation("modularwarfare:stalker.pso_scope");
		return new ItemStack(Item.REGISTRY.getObject(resourcelocation));
	}

}
