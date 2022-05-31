package ru.minersdream.stalker.item;

import ru.minersdream.stalker.interfaces.*;
import net.minecraft.client.audio.Sound;
import net.minecraft.creativetab.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import ru.minersdream.stalker.main.*;

public class FirstAidKit extends BasicItems implements IHasModel {
	private final int healthRestored;
	private final int radiationRestored;
	private final boolean appliesNausea;
	private final SoundEvent sound;

	public FirstAidKit(String name, CreativeTabs tab, int maxStackSize,EnumRarity rarity, SoundEvent sound, boolean appliesNausea, int health, int rad) {
		super(name, tab, maxStackSize, rarity);
		this.healthRestored = health;
		this.radiationRestored = rad;
		this.appliesNausea = appliesNausea;
		this.sound=sound;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		itemstack.shrink(1);
		playerIn.getCooldownTracker().setCooldown(this, 40);
		if (!worldIn.isRemote) {
			long radiated = playerIn.getEntityData().getLong("radiation");
			playerIn.getEntityData().setLong("radiation", Math.max(0, radiated-radiationRestored));
		}
		if (this.appliesNausea) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2400, 0));
		}
		playerIn.setHealth(playerIn.getHealth() + this.healthRestored);
		worldIn.playSound(playerIn, playerIn.getPosition(), sound, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
		return new ActionResult(EnumActionResult.SUCCESS, itemstack);
	}
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
