package ru.minersdream.stalker.item;

import ru.minersdream.stalker.interfaces.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.stats.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;

import java.util.List;

import com.modularwarfare.common.type.BaseItem;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.advancements.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import ru.minersdream.stalker.main.*;

public class ItemArtefact extends BasicItems implements IHasModel,IBauble
{
	int[] properties;
	/*
	 * [0] Радиация -
	 * [1] Метаболизм -
	 * [2] Скорость - 
	 * [3] Здоровье -
	 * [4] Защита от радиации (0)
	 * [5] Пси-устойчивость (1)
	 * [6] Жароустойчивость (2)
	 * [7] Хим. устойчивость (3)
	 * [8] Электро-устойчивость (4)
	 * [9] Разрыво-устойчивость (5)
	 * [10]Вес (6)
	 */
	
	
    public ItemArtefact(String name, CreativeTabs tab, int MaxStackSize,EnumRarity rarityUncommon, int[] properties) {
        super(name, tab, MaxStackSize, rarityUncommon);
        this.properties=properties;
    }
    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {			
    	if(this.properties[4]!=0) {
    		applyBonus((EntityPlayer) player, 0, -this.properties[4]);
    	}
    	if(this.properties[5]!=0) {
    		applyBonus((EntityPlayer) player, 1, -this.properties[5]);
    	}
    	if(this.properties[6]!=0) {
    		applyBonus((EntityPlayer) player, 2, -this.properties[6]);
    	}
    	if(this.properties[7]!=0) {
    		applyBonus((EntityPlayer) player, 3, -this.properties[7]);
    	}
    	if(this.properties[8]!=0) {
    		applyBonus((EntityPlayer) player, 4, -this.properties[8]);
    	}
    	if(this.properties[9]!=0) {
    		applyBonus((EntityPlayer) player, 5, -this.properties[9]);
    	}
    	if(this.properties[10]!=0) {
    		applyBonus((EntityPlayer) player, 6, -this.properties[10]);
    	}
    	//IBauble.super.onUnequipped(itemstack, player);
    }
    
    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    	if(this.properties[4]!=0) {
    		applyBonus((EntityPlayer) player, 0, this.properties[4]);
    	}
    	if(this.properties[5]!=0) {
    		applyBonus((EntityPlayer) player, 1, this.properties[5]);
    	}
    	if(this.properties[6]!=0) {
    		applyBonus((EntityPlayer) player, 2, this.properties[6]);
    	}
    	if(this.properties[7]!=0) {
    		applyBonus((EntityPlayer) player, 3, this.properties[7]);
    	}
    	if(this.properties[8]!=0) {
    		applyBonus((EntityPlayer) player, 4, this.properties[8]);
    	}
    	if(this.properties[9]!=0) {
    		applyBonus((EntityPlayer) player, 5, this.properties[9]);
    	}
    	if(this.properties[10]!=0) {
    		applyBonus((EntityPlayer) player, 6, this.properties[10]);
    	}
    	//IBauble.super.onEquipped(itemstack, player);
    }
    
    @Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		
		final long totalWorldTime = player.getEntityWorld().getTotalWorldTime();
			if(this.properties[0]!=0) {
				if(totalWorldTime%20==0) {
					long radiationPlayer = player.getEntityData().getLong("radiation");
					if(radiationPlayer+this.properties[0]>0)
					radiationPlayer+=this.properties[0];
					else
					radiationPlayer=0;
					player.getEntityData().setLong("radiation", radiationPlayer);
				}
			}
			if(this.properties[1]!=0) {
				if(this.properties[1]<0)
					player.addPotionEffect(new PotionEffect(MobEffects.SATURATION,40,0,true,false));
				else
					player.addPotionEffect(new PotionEffect(MobEffects.HUNGER,40,this.properties[1]-1,true,false));
			}
			if(this.properties[2]!=0) {
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED,40,this.properties[2]-1,true,false));
			}
			if(this.properties[3]!=0) {
				if(totalWorldTime%40==0) {
					player.setHealth(player.getHealth()+1);
				}
			}
		
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		
		return BaubleType.TRINKET;
	}
	
	private void applyBonus(EntityPlayer player, int id, int lvl) {
		int[] bonuses = player.getEntityData().getIntArray("bonuses");
		bonuses[id]+=lvl;
		player.getEntityData().setIntArray("bonuses", bonuses);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("artefact.characteristics"));
		
		if(this.properties[0]!=0)
			if(this.properties[0]<0)tooltip.add(I18n.format("artefact.radiation")+TextFormatting.GREEN+" "+this.properties[0]);
			else tooltip.add(I18n.format("artefact.radiation")+TextFormatting.RED+" "+this.properties[0]);
		
		if(this.properties[1]!=0)
			if(this.properties[1]<0)tooltip.add(I18n.format("artefact.metabolism")+TextFormatting.GREEN+" "+this.properties[1]);
			else tooltip.add(I18n.format("artefact.metabolism")+TextFormatting.RED+" "+this.properties[1]);
		
		if(this.properties[2]!=0)
			if(this.properties[2]>0) tooltip.add(I18n.format("artefact.speed")+TextFormatting.GREEN+" "+this.properties[2]);
			else tooltip.add(I18n.format("artefact.speed")+TextFormatting.RED+" "+this.properties[2]);
		
		if(this.properties[3]!=0)
			if(this.properties[3]>0) tooltip.add(I18n.format("artefact.health")+TextFormatting.GREEN+" "+this.properties[3]);
			else tooltip.add(I18n.format("artefact.health")+TextFormatting.GREEN+" "+this.properties[3]);
		
		if(this.properties[4]!=0)
			if(this.properties[4]>0) tooltip.add(I18n.format("artefact.radiationprotection")+TextFormatting.GREEN+" "+this.properties[4]);
			else tooltip.add(I18n.format("artefact.radiationprotection")+TextFormatting.RED+" "+this.properties[4]);
		
		if(this.properties[5]!=0)
			if(this.properties[5]>0)tooltip.add(I18n.format("artefact.psiresilience")+TextFormatting.GREEN+" "+this.properties[5]);
			else tooltip.add(I18n.format("artefact.psiresilience")+TextFormatting.RED+" "+this.properties[5]);
		
		if(this.properties[6]!=0)
			if(this.properties[6]>0) tooltip.add(I18n.format("artefact.heatresistance")+TextFormatting.GREEN+" "+this.properties[6]);
			else tooltip.add(I18n.format("artefact.heatresistance")+TextFormatting.RED+" "+this.properties[6]);
		
		if(this.properties[7]!=0)
			if(this.properties[7]>0) tooltip.add(I18n.format("artefact.chemsteadiness")+TextFormatting.GREEN+" "+this.properties[7]);
			else tooltip.add(I18n.format("artefact.chemsteadiness")+TextFormatting.RED+" "+this.properties[7]);
		
		if(this.properties[8]!=0)
			if(this.properties[8]>0) tooltip.add(I18n.format("artefact.electricalresistance")+TextFormatting.GREEN+" "+this.properties[8]);
			else tooltip.add(I18n.format("artefact.electricalresistance")+TextFormatting.RED+" "+this.properties[8]);
	
		if(this.properties[9]!=0)
			if(this.properties[9]>0)tooltip.add(I18n.format("artefact.tensilestrength")+TextFormatting.GREEN+" "+this.properties[9]);
			else tooltip.add(I18n.format("artefact.tensilestrength")+TextFormatting.RED+" "+this.properties[9]);
		
		if(this.properties[10]!=0)
		if(this.properties[10]>0)tooltip.add(I18n.format("artefact.weightbonus")+TextFormatting.GREEN+" "+this.properties[10]);
			else tooltip.add(I18n.format("artefact.weightbonus")+TextFormatting.RED+" "+this.properties[10]);
		
	}
	
	public void registerModels() {
        STALKERMain.proxy.registerItemRenderer((Item)this, 0, "inventory");
	}
	/*
		if(this==InitItems.JELLYFISH) {
		
	}else if(this==InitItems.STONEFLOWER) {
		
	}else if(this==InitItems.CRYSTAL) {
		
	}else if(this==InitItems.BLOODOFSTONE) {
		
	}else if(this==InitItems.SPARKLER) {
		
	}else if(this==InitItems.BATTERY) {
		
	}else if(this==InitItems.SOUL) {
		
	}else if(this==InitItems.MOMSBEADS) {
		
	}else if(this==InitItems.NIGHTSTAR) {
		
	}else if(this==InitItems.TWIST) {
		
	}else if(this==InitItems.MOONLIGHT) {
		
	}else if(this==InitItems.FIREBALL) {
		
	}else if(this==InitItems.CHUNKOFMEAT) {
		
	}else if(this==InitItems.FLASH) {
		
	}else if(this==InitItems.DUMMY) {
		
	}else if(this==InitItems.GINGERBREADMAN) {
		
	}else if(this==InitItems.EYE) {
		
	}else if(this==InitItems.GRAVI) {
		
	}else if(this==InitItems.BUBBLE) {
		
	}else if(this==InitItems.SNOWFLAKE) {
		
	}else if(this==InitItems.FIREFLY) {
		
	}else if(this==InitItems.FLAME) {
		
	}else if(this==InitItems.GOLDFISH) {
		
	}
*/  
}
