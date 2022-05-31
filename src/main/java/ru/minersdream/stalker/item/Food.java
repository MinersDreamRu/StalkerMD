package ru.minersdream.stalker.item;

import ru.minersdream.stalker.interfaces.*;
import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.stats.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.entity.player.*;
import net.minecraft.advancements.*;
import ru.minersdream.stalker.main.*;

public class Food extends ItemFood implements IHasModel
{
    final int type;
    
    public Food(final String name, final CreativeTabs tab, final int MaxStackSize, final int amount, final int type) {
        super(amount, (float)amount, false);
        this.type = type;
        this.setMaxStackSize(MaxStackSize);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(tab);
        InitItems.ITEMS.add((Item)this);
    }
    
    @Override
    public ItemStack onItemUseFinish(final ItemStack stack, final World worldIn, final EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            final EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats((ItemFood)this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5f, worldIn.rand.nextFloat() * 0.1f + 0.9f);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats((Item)this));
            if (this.type == 1) {
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 1));
            }
            if (entityplayer instanceof EntityPlayerMP) {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }
        stack.shrink(1);
        return stack;
    }
    
    @Override
    public void registerModels() {
        STALKERMain.proxy.registerItemRenderer((Item)this, 0, "inventory");
    }
}
