package ru.minersdream.stalker.item;

import ru.minersdream.stalker.interfaces.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import ru.minersdream.stalker.main.*;

public class StalkerFood extends BasicItems implements IHasModel
{
    final int food;
    final int rad;
    final int id;
    final SoundEvent sound;
    public StalkerFood(final String name, final CreativeTabs tab, final int MaxStackSize,EnumRarity rarityUncommon, SoundEvent sound, final int food, final int rad, final int id) {
        super(name, tab, MaxStackSize, rarityUncommon);
        this.food = food;
        this.rad = rad;
        this.id=id;
        this.sound=sound;

    }
    
    
        public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
        	
        	final ItemStack itemstack = playerIn.getHeldItem(handIn);
        	itemstack.shrink(1);
        	playerIn.getFoodStats().addStats(this.food, 1.0f);
        	playerIn.getCooldownTracker().setCooldown((Item)this, 20);
        	long radiated = playerIn.getEntityData().getLong("radiation");
        	if (radiated - this.rad > 0L) {
                radiated -= this.rad;
                playerIn.getEntityData().setLong("radiation", radiated);
            }
            else {
                playerIn.getEntityData().setLong("radiation", 0L);
            }
            if (playerIn instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, itemstack);
            }
        	switch (this.id) {
			case 1:
				playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
				break;
			case 2:
				playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
				break;
			case 3:
				playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3000, 1));
				break;
			default:
				break;
			}
        	worldIn.playSound(playerIn, playerIn.getPosition(), sound, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)itemstack);
        }
        	/*
        final ResourceLocation location = new ResourceLocation("stalker", "open_chest");
        final SoundEvent event = new SoundEvent(location);
        final ItemStack itemstack = playerIn.getHeldItem(handIn);
        itemstack.shrink(1);
        playerIn.getCooldownTracker().setCooldown((Item)this, 20);
        if (!worldIn.isRemote) {
            long radiated = playerIn.getEntityData().getLong("radiation");
            System.out.println(radiated);
            if (radiated - this.rad > 0L) {
                radiated -= this.rad;
                playerIn.getEntityData().setLong("radiation", radiated);
            }
            else {
                playerIn.getEntityData().setLong("radiation", 0L);
            }
        }
        if (this.type == 1) {
            playerIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
        }
        if (playerIn.getHealth() < this.maxhealth) {
            playerIn.setHealth(playerIn.getHealth() + this.health);
        }
        return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)itemstack);
        */
    
    
    public void registerModels() {
        STALKERMain.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
