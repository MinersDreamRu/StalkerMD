package ru.minersdream.stalker.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;
import ru.minersdream.stalker.item.BasicItems;
import ru.minersdream.stalker.item.FirstAidKit;
import ru.minersdream.stalker.item.Food;
import ru.minersdream.stalker.item.ItemArtefact;
import ru.minersdream.stalker.item.ItemGeigerCounter;
import ru.minersdream.stalker.item.StalkerFood;

public class InitItems
{
    
    public static final List<Item> ITEMS = new ArrayList<Item>();
    //public static final Map<Item, ModelBiped> ARMOR_MODELS = new HashMap<>();
    public static final List<String> ITEMARMOR = new ArrayList<String>();
    
    public static final EnumRarity RARITY_COMMON = EnumHelper.addRarity("RARITY_COMMON", TextFormatting.WHITE, "Common");
    public static final EnumRarity RARITY_UNCOMMON = EnumHelper.addRarity("RARITY_UNCOMMON", TextFormatting.GREEN, "Uncommon");
    public static final EnumRarity RARITY_RARE = EnumHelper.addRarity("RARITY_RARE", TextFormatting.BLUE, "Rare");
    public static final EnumRarity RARITY_EPIC = EnumHelper.addRarity("RARITY_EPIC", TextFormatting.RED, "Epic");
    public static final EnumRarity RARITY_MYTHICAL = EnumHelper.addRarity("RARITY_MYTHICAL", TextFormatting.DARK_PURPLE, "Mythical");
    public static final EnumRarity RARITY_LEGENDARY = EnumHelper.addRarity("RARITY_LEGENDARY", TextFormatting.GOLD, "Legendary");
    public static final EnumRarity RARITY_UNIQUE = EnumHelper.addRarity("RARITY_UNIQUE", TextFormatting.GOLD, "Unique");

    //Имя, Вкладка, Максимальный стак, Раритетность, Звук, Тошнота, Здоровье, Радиация
    public static final Item 		FIRSTAIDKIT = new FirstAidKit("firstaidkit", STALKERMain.OtherTab, 8, RARITY_COMMON, InitSounds.MEDKIT_USE, false, 30, 250);
    public static final Item 		ARMYFIRSTAIDKIT = new FirstAidKit("armyfirstaidkit", STALKERMain.OtherTab, 8, RARITY_UNCOMMON, InitSounds.MEDKIT_USE, false, 60, 500);
    public static final Item 		SCIENTIFICFIRSTAIDKIT = new FirstAidKit("scrientificfirstaidkit", STALKERMain.OtherTab, 8, RARITY_RARE, InitSounds.MEDKIT_USE, false, 96, 1250);
    public static final Item 		ANTIRAD = new FirstAidKit("antirad", STALKERMain.OtherTab, 8, RARITY_UNCOMMON, InitSounds.ANTIRAD_USE, false, 3, 1000);
    public static final Item 		BANDAGE = new FirstAidKit("bandage", STALKERMain.OtherTab, 8, RARITY_COMMON, InitSounds.BANDAGE_USE, false, 4, 0);
    public static final Item 		VODKA = new FirstAidKit("vodka", STALKERMain.OtherTab, 8, RARITY_COMMON, InitSounds.VODKA_USE, true, 4, 200);
    
    public static final Item 		SAUSAGE = new StalkerFood("sausage", STALKERMain.OtherTab, 12, RARITY_COMMON, InitSounds.FOOD_USE, 5, 0, 1);
    public static final Item 		CANNEDFOOD = new StalkerFood("cannedfood", STALKERMain.OtherTab,12, RARITY_COMMON, InitSounds.FOOD_USE, 10, 0,2);
    public static final Item 		BREAD = new StalkerFood("bread", STALKERMain.OtherTab,12, RARITY_COMMON, InitSounds.FOOD_USE, 10, 0,2);
    public static final Item 		ENERGYDRINK = new StalkerFood("energydrink", STALKERMain.OtherTab,12, RARITY_COMMON, InitSounds.DRINK_USE, 2, 0, 3);
    public static final Item 		BANKNOTE10RUBLES = new BasicItems("banknote10rubles", STALKERMain.QuestTab, 64, RARITY_COMMON);
    public static final Item    	BANKNOTE50RUBLES = new BasicItems("banknote50rubles", STALKERMain.QuestTab, 64, RARITY_COMMON);
    public static final Item     	BANKNOTE100RUBLES = new BasicItems("banknote100rubles", STALKERMain.QuestTab, 64, RARITY_COMMON);
    public static final Item     	BANKNOTE500RUBLES = new BasicItems("banknote500rubles", STALKERMain.QuestTab, 64, RARITY_UNCOMMON);
    public static final Item     	BANKNOTE1000RUBLES = new BasicItems("banknote1000rubles", STALKERMain.QuestTab, 64, RARITY_UNCOMMON);
    public static final Item     	BANKNOTE5000RUBLES = new BasicItems("banknote5000rubles",  STALKERMain.QuestTab, 64, RARITY_UNCOMMON);
    public static final Item     	GEIGERCOUNTER = new ItemGeigerCounter("geigercounter",  STALKERMain.QuestTab, 1);
    

    public static final Item     	DETECTORRESPONSE = new BasicItems("detectorresponse",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON);
    public static final Item     	DETECTORBEAR = new BasicItems("detectorbear",  STALKERMain.OtherTab, 1, RARITY_RARE);
    public static final Item     	DETECTORVELES = new BasicItems("detectorveles",  STALKERMain.OtherTab, 1, RARITY_EPIC);
    public static final Item     	DETECTORSVAROG = new BasicItems("prototypedetectorsvarog",  STALKERMain.OtherTab, 1, RARITY_MYTHICAL);
    
	/*
	 * [0] Радиация
	 * [1] Метаболизм
	 * [2] Защита от радиации
	 * [3] Скорость
	 * [4] Здоровье
	 * [5] Пси-излучение
	 * [6] Жароустойчивость
	 * [7] Хим. устойчивость
	 * [8] Электро-устойчивость
	 * [9] Разрыво-устойчивость
	 * [10]Вес
	 */
    public static final Item     	JELLYFISH = new ItemArtefact("jellyfish",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON, new int[] {-30,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	STONEFLOWER = new ItemArtefact("stoneflower",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON, new int[] {10,0,0,0,0,3,0,0,0,0,0});
    public static final Item     	CRYSTAL = new ItemArtefact("crystal",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON, new int[] {10,0,0,0,0,0,3,0,0,0,0});
    public static final Item     	BLOODOFSTONE = new ItemArtefact("bloodofstone",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON, new int[] {10,0,0,0,0,0,0,3,0,0,0});
    public static final Item     	SPARKLER = new ItemArtefact("sparkler",  STALKERMain.OtherTab, 1, RARITY_UNCOMMON, new int[] {10,0,0,0,0,0,0,0,3,0,0});
    public static final Item     	BATTERY = new ItemArtefact("battery",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {10,0,0,2,0,0,0,0,0,0,0});
    public static final Item     	SOUL = new ItemArtefact("soul",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {20,0,0,0,4,0,0,0,0,0,0});
    public static final Item     	MOMSBEADS = new ItemArtefact("momsbeads",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {0,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	NIGHTSTAR = new ItemArtefact("nightstar",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {10,0,0,0,0,0,0,0,0,0,4});
    public static final Item     	TWIST = new ItemArtefact("twist",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {-40,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	MOONLIGHT = new ItemArtefact("moonlight",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {20,0,0,0,0,6,0,0,0,0,0});
    public static final Item     	FIREBALL = new ItemArtefact("fireball",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {20,0,0,0,0,0,6,0,0,0,0});
    public static final Item     	CHUNKOFMEAT = new ItemArtefact("chunkofmeat",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {20,0,0,0,0,0,0,6,0,0,0});
    public static final Item     	FLASH = new ItemArtefact("flash",  STALKERMain.OtherTab, 1, RARITY_RARE, new int[] {20,0,0,0,0,0,0,0,6,0,0});
    public static final Item     	DUMMY = new ItemArtefact("dummy",  STALKERMain.OtherTab, 1, RARITY_EPIC, new int[] {20,0,0,4,0,0,0,0,0,0,0});
    public static final Item     	GINGERBREADMAN = new ItemArtefact("gingerbreadman",  STALKERMain.OtherTab, 1, RARITY_EPIC, new int[] {20,0,0,0,4,0,0,0,0,0,0});
    public static final Item     	EYE = new ItemArtefact("eye",  STALKERMain.OtherTab, 1, RARITY_EPIC, new int[] {20,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	GRAVI = new ItemArtefact("gravi",  STALKERMain.OtherTab, 1, RARITY_EPIC, new int[] {30,0,0,0,0,0,0,0,0,0,8});
    public static final Item     	BUBBLE = new ItemArtefact("bubble",  STALKERMain.OtherTab, 1, RARITY_EPIC, new int[] {-60,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	SNOWFLAKE = new ItemArtefact("snowflake",  STALKERMain.OtherTab, 1, RARITY_MYTHICAL, new int[] {30,0,6,0,0,0,0,0,0,0,0});
    public static final Item     	FIREFLY = new ItemArtefact("firefly",  STALKERMain.OtherTab, 1, RARITY_MYTHICAL, new int[] {30,0,0,6,0,0,0,0,0,0,0});
    public static final Item     	FLAME = new ItemArtefact("flame",  STALKERMain.OtherTab, 1, RARITY_MYTHICAL, new int[] {0,0,0,0,0,0,0,0,0,0,0});
    public static final Item     	GOLDFISH = new ItemArtefact("goldfish",  STALKERMain.OtherTab, 1, RARITY_MYTHICAL, new int[] {30,0,0,0,0,0,0,0,0,0,12});

    public static final Item     	ARTCOMPASS = new ItemArtefact("artcompass",  STALKERMain.OtherTab, 1, RARITY_LEGENDARY, new int[] {40,0,0,2,0,1,1,1,3,0,0});
    public static final Item     	HEARTOASIS = new ItemArtefact("heartoasis",  STALKERMain.OtherTab, 1, RARITY_LEGENDARY, new int[] {40,-1,0,1,1,0,0,0,0,0,0});
}
