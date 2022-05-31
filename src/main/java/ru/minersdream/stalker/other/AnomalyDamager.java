package ru.minersdream.stalker.other;

import java.beans.EventHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERDamageSource;

public class AnomalyDamager {
	public static void DamagePlayer(EntityPlayer player, int damage, String name) {			
		DamageSource damageSource = getDamageSource(name);
		player.attackEntityFrom(damageSource, damage);
		
	}
	
	private static DamageSource getDamageSource(String name) {
		switch (name) {
		case "tile.anomaly_springboard": 
			return STALKERDamageSource.FROM_EXPLANOMALIES;
		
		case "tile.anomaly_funnel": 
			return STALKERDamageSource.FROM_EXPLANOMALIES;
		
		case "tile.anomaly_frying": 
			return STALKERDamageSource.FROM_THERMANOMALIES;
		
		case "tile.anomaly_steam": 
			return STALKERDamageSource.FROM_THERMANOMALIES;
		
		case "tile.anomaly_electra": 
			return STALKERDamageSource.FROM_ELECANOMALIES;
		
		case "tile.anomaly_jelly": 
			return STALKERDamageSource.FROM_CHEMANOMALIES;
		
		
		}
	return STALKERDamageSource.FROM_RADZONE;
	}
	
}
