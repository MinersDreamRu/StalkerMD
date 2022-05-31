package ru.minersdream.stalker.item;

import javax.swing.plaf.IconUIResource;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;

public class ItemGeigerCounter extends Item implements IHasModel
{
	public ItemGeigerCounter(String name, CreativeTabs tab, int MaxStackSize) {
		setMaxStackSize(MaxStackSize);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(tab);		
		InitItems.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
            long radiated = playerIn.getEntityData().getLong("radiation");
            double prc = STALKERMain.radiationMax;
            int countDown = 0;
            TextComponentTranslation descTranslated = new TextComponentTranslation("stalker.radiation", radiated, (int)prc);
            playerIn.sendStatusMessage(descTranslated, true);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}

