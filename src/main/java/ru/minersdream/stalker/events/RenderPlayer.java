package ru.minersdream.stalker.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderPlayer {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
		/*
		event.getRenderer().getMainModel().bipedHead.isHidden = false;
		event.getRenderer().getMainModel().bipedBody.isHidden = false;
		event.getRenderer().getMainModel().bipedLeftArm.isHidden = false;
		event.getRenderer().getMainModel().bipedRightArm.isHidden = false;
		event.getRenderer().getMainModel().bipedLeftLeg.isHidden = false;
		event.getRenderer().getMainModel().bipedRightLeg.isHidden = false;
	    */
		
		if(!(event.getEntity() instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.getEntity();
	    if(player.inventory.armorInventory.get(0).getItem() !=Items.AIR){
			
	    }
		event.getRenderer().getMainModel().textureWidth=0;
	}
}
