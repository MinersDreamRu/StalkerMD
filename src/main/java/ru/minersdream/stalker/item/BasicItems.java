package ru.minersdream.stalker.item;

import javax.swing.plaf.IconUIResource;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;

public class BasicItems extends Item implements IHasModel
{
	EnumRarity rarity;
	public BasicItems(String name, CreativeTabs tab, int MaxStackSize,EnumRarity rarity) {
		this.rarity=rarity;
		setMaxStackSize(MaxStackSize);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(tab);		
		InitItems.ITEMS.add(this);
	}
	
	
	public EnumRarity getRarity(ItemStack stack) {
	    return rarity; // Созданная нами группа редкости
	}
	
	
	
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}

