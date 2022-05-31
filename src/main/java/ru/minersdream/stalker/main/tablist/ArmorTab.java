package ru.minersdream.stalker.main.tablist;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.ExternalItemsAdd;

public class ArmorTab extends CreativeTabs {

	public ArmorTab(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{

		ResourceLocation resourcelocation = new ResourceLocation("modularwarfare:stalker.exoskelet");
		
		return new ItemStack(Item.REGISTRY.getObject(resourcelocation));
	}
	
}
