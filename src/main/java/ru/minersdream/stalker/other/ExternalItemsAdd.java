package ru.minersdream.stalker.other;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandGive;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;

public class ExternalItemsAdd {
	private final static List<String> ITEMS_ARMOR = new ArrayList<String>();
	private final static List<String> ITEMS_ATTACHMENTS = new ArrayList<String>();
	private final static List<String> ITEMS_AMMO = new ArrayList<String>();
	private final static List<String> ITEMS_WEAPONS = new ArrayList<String>();

	public static void ExternalItemsList() {
		ITEMS_AMMO.clear();
		ITEMS_ARMOR.clear();
		ITEMS_ATTACHMENTS.clear();
		ITEMS_WEAPONS.clear();

		// Armor
		ITEMS_ARMOR.add("modularwarfare:stalker.exoskelet");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor.svoboda");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor1");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor2");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor3");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor4");
		ITEMS_ARMOR.add("modularwarfare:stalker.aegisarmor5");
		ITEMS_ARMOR.add("modularwarfare:stalker.berill");
		ITEMS_ARMOR.add("modularwarfare:stalker.berill.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.berill.mon");
		ITEMS_ARMOR.add("modularwarfare:stalker.berill.bandit");
		ITEMS_ARMOR.add("modularwarfare:stalker.bylat");
		ITEMS_ARMOR.add("modularwarfare:stalker.bylat.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.bylat.merc");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.bandit");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.cn");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.merc");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.monolit");
		ITEMS_ARMOR.add("modularwarfare:stalker.heavysuit.svoboda");
		ITEMS_ARMOR.add("modularwarfare:stalker.kyrtka");
		ITEMS_ARMOR.add("modularwarfare:stalker.kyrtka.bandit");
		ITEMS_ARMOR.add("modularwarfare:stalker.kyrtka.svoboda");
		ITEMS_ARMOR.add("modularwarfare:stalker.kyrtka.cn");
		ITEMS_ARMOR.add("modularwarfare:stalker.seva");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya.bandit");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya.merc");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya.mon");
		ITEMS_ARMOR.add("modularwarfare:stalker.zarya.svoboda");
		ITEMS_ARMOR.add("modularwarfare:stalker.ps39m");
		ITEMS_ARMOR.add("modularwarfare:stalker.ps39m.dolg");
		ITEMS_ARMOR.add("modularwarfare:stalker.ps39m.merc");
		ITEMS_ARMOR.add("modularwarfare:stalker.ps39m.voen");
		ITEMS_ARMOR.add("modularwarfare:stalker.plash");
		ITEMS_ARMOR.add("modularwarfare:stalker.plash.greh");
		ITEMS_ARMOR.add("modularwarfare:stalker.plash.stal");

		// Ammo
		ITEMS_AMMO.add("modularwarfare:stalker.gaussbullet");
		ITEMS_AMMO.add("modularwarfare:stalker.handigaussbullet");
		ITEMS_AMMO.add("modularwarfare:stalker.45acp");
		ITEMS_AMMO.add("modularwarfare:stalker.5_56x45");
		ITEMS_AMMO.add("modularwarfare:stalker.7_62x39");
		ITEMS_AMMO.add("modularwarfare:stalker.7_62x54");
		ITEMS_AMMO.add("modularwarfare:stalker.9x18");
		ITEMS_AMMO.add("modularwarfare:stalker.9x19");
		ITEMS_AMMO.add("modularwarfare:stalker.9x39");
		ITEMS_AMMO.add("modularwarfare:stalker.12gauge");
		ITEMS_AMMO.add("modularwarfare:stalker.12sluggauge");
		ITEMS_AMMO.add("modularwarfare:stalker.ak74ammo");
		ITEMS_AMMO.add("modularwarfare:stalker.ak74_762x54_ammo");
		ITEMS_AMMO.add("modularwarfare:stalker.ak74uammo");
		ITEMS_AMMO.add("modularwarfare:stalker.an94ammo");
		ITEMS_AMMO.add("modularwarfare:stalker.asvalammo");
		ITEMS_AMMO.add("modularwarfare:stalker.berettaammo");
		ITEMS_AMMO.add("modularwarfare:stalker.coltammo");
		ITEMS_AMMO.add("modularwarfare:stalker.deserteagleammo");
		ITEMS_AMMO.add("modularwarfare:stalker.lr300ammo");
		ITEMS_AMMO.add("modularwarfare:stalker.mp5sdammo");
		ITEMS_AMMO.add("modularwarfare:stalker.pmammo");
		ITEMS_AMMO.add("modularwarfare:stalker.sig550ammo");
		ITEMS_AMMO.add("modularwarfare:stalker.svdammo");
		ITEMS_AMMO.add("modularwarfare:stalker.svuammo");
		ITEMS_AMMO.add("modularwarfare:stalker.svtammo");
		ITEMS_AMMO.add("modularwarfare:stalker.uspammo");
		ITEMS_AMMO.add("modularwarfare:stalker.vssammo");
		ITEMS_AMMO.add("modularwarfare:stalker.walterammo");
		ITEMS_AMMO.add("modularwarfare:stalker.gaussammo");
		ITEMS_AMMO.add("modularwarfare:stalker.l85ammo");

		// Weapons
		ITEMS_WEAPONS.add("modularwarfare:stalker.ak74");
		ITEMS_WEAPONS.add("modularwarfare:stalker.ak74_762x54");
		ITEMS_WEAPONS.add("modularwarfare:stalker.ak74u");
		ITEMS_WEAPONS.add("modularwarfare:stalker.an94");
		ITEMS_WEAPONS.add("modularwarfare:stalker.asval");
		ITEMS_WEAPONS.add("modularwarfare:stalker.beretta");
		ITEMS_WEAPONS.add("modularwarfare:stalker.colt");
		ITEMS_WEAPONS.add("modularwarfare:stalker.deserteagle");
		ITEMS_WEAPONS.add("modularwarfare:stalker.fort");
		ITEMS_WEAPONS.add("modularwarfare:stalker.gauss");
		ITEMS_WEAPONS.add("modularwarfare:stalker.l85");
		ITEMS_WEAPONS.add("modularwarfare:stalker.lr300");
		ITEMS_WEAPONS.add("modularwarfare:stalker.mp5sd");
		ITEMS_WEAPONS.add("modularwarfare:stalker.obrez");
		ITEMS_WEAPONS.add("modularwarfare:stalker.pb");
		ITEMS_WEAPONS.add("modularwarfare:stalker.pm");
		ITEMS_WEAPONS.add("modularwarfare:stalker.sig550");
		ITEMS_WEAPONS.add("modularwarfare:stalker.spas12");
		ITEMS_WEAPONS.add("modularwarfare:stalker.svd");
		ITEMS_WEAPONS.add("modularwarfare:stalker.svu");
		ITEMS_WEAPONS.add("modularwarfare:stalker.svt");
		ITEMS_WEAPONS.add("modularwarfare:stalker.toz34");
		ITEMS_WEAPONS.add("modularwarfare:stalker.usp");
		ITEMS_WEAPONS.add("modularwarfare:stalker.vss");
		ITEMS_WEAPONS.add("modularwarfare:stalker.walter");

		// attachments
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.556_45_suppressor");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.762_39_suppressor");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.762_54_suppressor");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.9mm_suppressor");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.45acp_suppressor");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.ekp_scope");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.eotech_scope");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.pso_scope");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.susat_scope");
		ITEMS_ATTACHMENTS.add("modularwarfare:stalker.acog_scope");
		STALKERMain.logger().info("Items Added Successfully");
	}

	public static void ExternalItemsAdd() {
		for (String item : ITEMS_ARMOR) {
			ResourceLocation resourcelocation = new ResourceLocation(item);
			if (Item.REGISTRY.getObject(resourcelocation) != null)
				Item.REGISTRY.getObject(resourcelocation).setCreativeTab(STALKERMain.ArmorTab);
			else
				STALKERMain.logger().error("Item '" + item + "' doesn't exist.");
		}
		for (String item : ITEMS_AMMO) {
			ResourceLocation resourcelocation = new ResourceLocation(item);
			if (Item.REGISTRY.getObject(resourcelocation) != null)
				Item.REGISTRY.getObject(resourcelocation).setCreativeTab(STALKERMain.AmmoTab);
			else
				STALKERMain.logger().error("Item '" + item + "' doesn't exist.");
		}
		for (String item : ITEMS_ATTACHMENTS) {
			ResourceLocation resourcelocation = new ResourceLocation(item);
			if (Item.REGISTRY.getObject(resourcelocation) != null)
				Item.REGISTRY.getObject(resourcelocation).setCreativeTab(STALKERMain.AttachmentsTab);
			else
				STALKERMain.logger().error("Item '" + item + "' doesn't exist.");
		}
		for (String item : ITEMS_WEAPONS) {
			ResourceLocation resourcelocation = new ResourceLocation(item);
			if (Item.REGISTRY.getObject(resourcelocation) != null)
				Item.REGISTRY.getObject(resourcelocation).setCreativeTab(STALKERMain.WeaponsTab);
			else
				STALKERMain.logger().error("Item '" + item + "' doesn't exist.");
		}
	}
}
