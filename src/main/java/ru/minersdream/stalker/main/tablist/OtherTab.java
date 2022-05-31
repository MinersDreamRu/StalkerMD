package ru.minersdream.stalker.main.tablist;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.InitItems;

public class OtherTab extends CreativeTabs {

	public OtherTab(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(InitItems.VODKA);
	}
}
