package ru.minersdream.stalker.main.tablist;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ru.minersdream.stalker.main.InitBlocks;

public class DecoTab extends CreativeTabs {

	public DecoTab(String label)
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(InitBlocks.SATCHEL);
	}
}
