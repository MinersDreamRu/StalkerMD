package ru.minersdream.stalker.main;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.audio.SoundList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InitSounds {

	public static final List<SoundEvent> SOUNDS = new ArrayList<SoundEvent>();
	
	public static final SoundEvent ANOMALY_ACTIVATED_SPRINGBOARD = reg("anomaly.activate.springboard");
	public static final SoundEvent ANOMALY_ACTIVATED_FUNNEL= reg("anomaly.activate.funnel");
	public static final SoundEvent 	ANOMALY_ACTIVATED_BORDER = reg("anomaly.activate.border");
	public static final SoundEvent 	ANOMALY_ACTIVATED_FRYING= reg("anomaly.activate.frying");
	public static final SoundEvent 	ANOMALY_ACTIVATED_STEAM = reg("anomaly.activate.steam");
	public static final SoundEvent 	ANOMALY_ACTIVATED_ELECTRA= reg("anomaly.activate.electra");
	public static final SoundEvent 	ANOMALY_ACTIVATED_JELLY= reg("anomaly.activate.jelly");
	public static final SoundEvent 	ANOMALY_ACTIVATED_SODA= reg("anomaly.activate.soda");
	public static final SoundEvent 	MEDKIT_USE = reg("medkit.player.use");
	public static final SoundEvent 	VODKA_USE= reg("vodka.player.use");
	public static final SoundEvent 	ANTIRAD_USE= reg("antirad.player.use");
	public static final SoundEvent 	BANDAGE_USE = reg("bandage.player.use");
	public static final SoundEvent 	FOOD_USE = reg("food.player.use");
	public static final SoundEvent 	DRINK_USE= reg("drink.player.use");
	public static final SoundEvent  STALKER_AN94_SHOOT= reg("stalker.an94.shoot");
	public static final SoundEvent  STALKER_AN94_SHOOT_SUP= reg("stalker.an94.shoot.sup");
	public static final SoundEvent  STALKER_AN94_RELOAD= reg("stalker.an94.reload");
	public static final SoundEvent  STALKER_PM_SHOOT= reg("stalker.pm.shoot");
	public static final SoundEvent  STALKER_PM_RELOAD= reg("stalker.pm.reload");
	public static final SoundEvent  STALKER_PB_SHOOT= reg("stalker.pb.shoot");
	public static final SoundEvent  STALKER_PB_RELOAD= reg("stalker.pb.reload");
	public static final SoundEvent  STALKER_BERETTA_SHOOT= reg("stalker.beretta.shoot");
	public static final SoundEvent  STALKER_BERETTA_SHOOT_SUP= reg("stalker.beretta.shoot.sup");
	public static final SoundEvent  STALKER_BERETTA_RELOAD= reg("stalker.beretta.reload");
	public static final SoundEvent  STALKER_USP_SHOOT= reg("stalker.usp.shoot");
	public static final SoundEvent  STALKER_USP_SHOOT_SUP= reg("stalker.usp.shoot.sup");
	public static final SoundEvent  STALKER_USP_RELOAD= reg("stalker.usp.reload");
	public static final SoundEvent  STALKER_COLT_SHOOT= reg("stalker.colt.shoot");
	public static final SoundEvent  STALKER_COLT_SHOOT_SUP= reg("stalker.colt.shoot.sup");
	public static final SoundEvent  STALKER_COLT_RELOAD= reg("stalker.colt.reload");
	public static final SoundEvent  STALKER_WALTER_SHOOT= reg("stalker.walter.shoot");
	public static final SoundEvent  STALKER_WALTER_RELOAD= reg("stalker.walter.reload");
	public static final SoundEvent  STALKER_WALTER_SHOOT_SUP= reg("stalker.walter.shoot.sup");
	public static final SoundEvent  STALKER_AK74_SHOOT= reg("stalker.ak74.shoot");
	public static final SoundEvent  STALKER_AK74_SHOOT_SUP= reg("stalker.ak74.shoot.sup");
	public static final SoundEvent  STALKER_AK74_RELOAD= reg("stalker.ak74.reload");
	public static final SoundEvent  STALKER_AK74_SHOOT762X54= reg("stalker.ak74.shoot762x54");
	public static final SoundEvent  STALKER_AK74U_SHOT= reg("stalker.ak74u.shoot");
	public static final SoundEvent  STALKER_AK74U_SHOOT_SUP= reg("stalker.ak74u.shoot.sup");
	public static final SoundEvent  STALKER_AK74U_RELOAD= reg("stalker.ak74u.reload");
	public static final SoundEvent  STALKER_LR300_SHOOT= reg("stalker.lr300.shoot");
	public static final SoundEvent  STALKER_LR300_SHOOT_SUP= reg("stalker.lr300.shoot.sup");
	public static final SoundEvent  STALKER_LR300_RELOAD= reg("stalker.lr300.reload");
	public static final SoundEvent  STALKER_SVU_SHOOT= reg("stalker.svu.shoot");
	public static final SoundEvent  STALKER_SVU_RELOAD= reg("stalker.svu.reload");
	public static final SoundEvent  STALKER_SVD_SHOOT= reg("stalker.svd.shoot");
	public static final SoundEvent  STALKER_SVD_RELOAD= reg("stalker.svd.reload");
	public static final SoundEvent  STALKER_SVD_SHOOT_SUP= reg("stalker.svd.shoot.sup");
	public static final SoundEvent  STALKER_SVT_SHOOT= reg("stalker.svt.shoot");
	public static final SoundEvent  STALKER_SVT_RELOAD= reg("stalker.svt.reload");
	public static final SoundEvent  STALKER_GAUSS_SHOOT= reg("stalker.gauss.shoot");
	public static final SoundEvent  STALKER_GAUSS_RELOAD= reg("stalker.gauss.reload");
	public static final SoundEvent  STALKER_VSS_SHOOT= reg("stalker.vss.shoot");
	public static final SoundEvent  STALKER_VSS_RELOAD= reg("stalker.vss.reload");
	public static final SoundEvent  STALKER_ASVAL_SHOOT= reg("stalker.asval.shoot");
	public static final SoundEvent  STALKER_ASVAL_RELOAD= reg("stalker.asval.reload");
	public static final SoundEvent  STALKER_MP5SD_SHOOT= reg("stalker.mp5sd.shoot");
	public static final SoundEvent  STALKER_MP5SD_RELOAD= reg("stalker.mp5sd.reload");
	public static final SoundEvent  STALKER_DESERTEAGLE_SHOOT= reg("stalker.deserteagle.shoot");
	public static final SoundEvent  STALKER_DESERTEAGLE_RELOAD= reg("stalker.deserteagle.reload");
	public static final SoundEvent  STALKER_SIG550_SHOOT= reg("stalker.sig550.shoot");
	public static final SoundEvent  STALKER_SIG550_SHOOT_SUP= reg("stalker.sig550.shoot.sup");
	public static final SoundEvent  STALKER_SIG550_RELOAD= reg("stalker.sig550.reload");
	public static final SoundEvent  STALKER_SPAS12_PUMP= reg("stalker.spas12.pump");
	public static final SoundEvent  STALKER_SPAS12_CHAMBER= reg("stalker.spas12.chamber");
	public static final SoundEvent  STALKER_SPAS12_SHOOT= reg("stalker.spas12.shoot");
	public static final SoundEvent  STALKER_TOZ34_SHOOT= reg("stalker.toz34.shoot");
	public static final SoundEvent  STALKER_L85_SHOOT= reg("stalker.l85.shoot");
	public static final SoundEvent  STALKER_L85_SHOOT_SUP= reg("stalker.l85.shoot.sup");
	public static final SoundEvent  STALKER_L85_RELOAD= reg("stalker.l85.reload");
	
	private static SoundEvent reg(String name) {
		ResourceLocation location = new ResourceLocation(STALKERMain.MODID, name);
		SoundEvent result = new SoundEvent(location).setRegistryName(location);
		SOUNDS.add(result);
		return result;
	}
	
}
