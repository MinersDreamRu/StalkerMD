package ru.minersdream.stalker.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;

public class BaseZone extends BlockAir implements IHasModel{
	public BaseZone(String name) {
	    this.setRegistryName(name);
	    this.setUnlocalizedName(name);
	    this.setHardness(100000);
	    this.setResistance(100000);
	    this.setHarvestLevel("admin", 100000);
	    this.setCreativeTab(STALKERMain.AnomaliesTab);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));	
	}
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
}
