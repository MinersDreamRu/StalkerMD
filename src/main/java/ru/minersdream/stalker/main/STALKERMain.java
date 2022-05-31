package ru.minersdream.stalker.main;

import java.io.File;
import java.rmi.registry.RegistryHandler;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.apache.logging.log4j.Logger;

import com.mjr.mjrlegendslib.client.model.OBJLoaderCustom;
import com.modularwarfare.client.patch.customnpc.CustomNPCListener;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.CustomNPCsException;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.event.CustomNPCsEvent;
import noppes.npcs.util.CustomNPCsScheduler;
import ru.minersdream.stalker.main.tablist.*;
import ru.minersdream.stalker.other.ExternalItemsAdd;
import ru.minersdream.stalker.other.PlayerStats;
import ru.minersdream.stalker.other.OtherMethods;
import ru.minersdream.stalker.proxy.CommonProxy;
import ru.minersdream.stalker.block.*;
import ru.minersdream.stalker.block.tile.TileEntityCounter;
import ru.minersdream.stalker.block.tile.TileEntityElectricalAnomaly;
import ru.minersdream.stalker.block.tile.TileEntityHeartChemicalAnomaly;
import ru.minersdream.stalker.block.tile.TileEntityHeartGravitationalAnomaly;
import ru.minersdream.stalker.block.tile.TileEntityHeartThermicAnomaly;
import ru.minersdream.stalker.block.tile.TileEntityLocationTransition;
import ru.minersdream.stalker.block.tile.TileEntityStash;
import ru.minersdream.stalker.events.CommandReloadConfig;
import ru.minersdream.stalker.events.RenderHud;
import ru.minersdream.stalker.events.RenderPlayer;
import ru.minersdream.stalker.events.EventsHandler;
import ru.minersdream.stalker.events.NPCEventsHandler;
import ru.minersdream.stalker.handlers.MDGuiHandler;

@Mod(modid = STALKERMain.MODID, name = STALKERMain.NAME, version = STALKERMain.VERSION, dependencies = STALKERMain.DEPENDENCIES)
//@Mod(modid = STALKERMain.MODID, name = STALKERMain.NAME, version = STALKERMain.VERSION)
public class STALKERMain {
	
	public static final String MODID = "stalker", NAME = "StalkerMinersDream", VERSION = "1.2", DEPENDENCIES="required-after:modularwarfare";
	
	@Instance(STALKERMain.MODID)
	public static STALKERMain instance;

	public Configuration config;
	
	private Logger logger;

	@SidedProxy(clientSide = "ru.minersdream.stalker.proxy.ClientProxy", serverSide = "ru.minersdream.stalker.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static final CreativeTabs AnomaliesTab = new AnomaliesTab("STALKER_ANOMALIES");
	public static final CreativeTabs BlockTab = new BlockTab("STALKER_BLOCK");
	public static final CreativeTabs DecoTab = new DecoTab("STALKER_DECO");
	public static final CreativeTabs QuestTab = new QuestTab("STALKER_QUEST");
	public static final CreativeTabs OtherTab = new OtherTab("STALKER_OTHER");
	public static final CreativeTabs ArmorTab = new ArmorTab("STALKER_ARMOR");
	public static final CreativeTabs WeaponsTab = new WeaponsTab("STALKER_WEAPONS");
	public static final CreativeTabs AmmoTab = new AmmoTab("STALKER_AMMO");
	public static final CreativeTabs AttachmentsTab = new AttachmentsTab("STALKER_ATTACHMENTS");

	public static int radiation1LvLPower,radiation2LvLPower,radiation3LvLPower;
	public static int radiation1LvL,radiation2LvL,radiation3LvL, radiationMax = 1000;
	public static short radiation1LvLDamage,radiation2LvLDamage,radiation3LvLDamage;

	public static int psi1LvLPower,psi2LvLPower,psi3LvLPower;
	public static int psi1LvL,psi2LvL,psi3LvL, psiMax = 1000;
	public static short psi1LvLDamage,psi2LvLDamage,psi3LvLDamage;
	
	public static String[] itemStash;
	public static String[] itemsThermicAnomaly;
	public static String[] itemsGravitationalAnomaly;
	public static String[] itemsChemicalAnomaly;
	public static String[] itemsElectricalAnomaly;
	public static String[] weightTable;
	public static int minItemsCountStash;
	public static int maxItemsCountStash;
	public static int fillingTime;
	
    public void loadConfig() {
        config.load();
        radiation1LvL = config.getInt("Radiation1LvL", "Radiation", 400, 0, 10000000,"What is the value of radiation must accumulate in the player, so that he would have 1 stage of infection.");
        radiation2LvL = config.getInt("Radiation2LvL", "Radiation", 600, 0, 10000000,"What is the value of radiation must accumulate in the player, so that he would have 2 stage of infection.");
        radiation3LvL = config.getInt("Radiation3LvL", "Radiation", 800, 0, 10000000,"What is the value of radiation must accumulate in the player, so that he would have 3 stage of infection.");
        radiation1LvLPower = config.getInt("Radiation1LvLPower", "Radiation", 20, 0, 10000000,"How much radiation is given in the level 1 zone.");
        radiation2LvLPower = config.getInt("Radiation2LvLPower", "Radiation", 40, 0, 10000000,"How much radiation is given in the level 2 zone.");
        radiation3LvLPower = config.getInt("Radiation3LvLPower", "Radiation", 80, 0, 10000000,"How much radiation is given in the level 3 zone.");
        radiation1LvLDamage = (short) config.getInt("Radiation1LvLDamage", "Radiation", 1, 0, 30000,"What damage does the player receive when they get stage 1 infection.");
        radiation2LvLDamage = (short) config.getInt("Radiation2LvLDamage", "Radiation", 3, 0, 30000,"What damage does the player receive when they get stage 2 infection.");
        radiation3LvLDamage = (short) config.getInt("Radiation3LvLDamage", "Radiation", 10, 0, 30000,"What damage does the player receive when they get stage 3 infection.");

        psi1LvL = config.getInt("Psi1LvL", "Psi", 400, 0, 10000000,"What is the value of Psi must accumulate in the player, so that he would have 1 stage of infection.");
        psi2LvL = config.getInt("Psi2LvL", "Psi", 600, 0, 10000000,"What is the value of Psi must accumulate in the player, so that he would have 2 stage of infection.");
        psi3LvL = config.getInt("Psi3LvL", "Psi", 800, 0, 10000000,"What is the value of Psi must accumulate in the player, so that he would have 3 stage of infection.");
        psi1LvLPower = config.getInt("Psi1LvLPower", "Psi", 20, 0, 10000000,"How much Psi is given in the level 1 zone.");
        psi2LvLPower = config.getInt("Psi2LvLPower", "Psi", 40, 0, 10000000,"How much Psi is given in the level 2 zone.");
        psi3LvLPower = config.getInt("Psi3LvLPower", "Psi", 80, 0, 10000000,"How much Psi is given in the level 3 zone.");
        psi1LvLDamage = (short) config.getInt("Psi1LvLDamage", "Psi", 1, 0, 30000,"What damage does the player receive when they get stage 1 infection.");
        psi2LvLDamage = (short) config.getInt("Psi2LvLDamage", "Psi", 3, 0, 30000,"What damage does the player receive when they get stage 2 infection.");
        psi3LvLDamage = (short) config.getInt("Psi3LvLDamage", "Psi", 10, 0, 30000,"What damage does the player receive when they get stage 3 infection.");

        itemsThermicAnomaly=config.getStringList("ThermicAnomaly", "AnomalyHeart", new String[]{
        		"stalker:crystal:0:1",
        		"stalker:crystal:0:1",
        		"stalker:crystal:0:1",
        		"stalker:crystal:0:1",
        		"stalker:momsbeads:0:1",
        		"stalker:momsbeads:0:1",
        		"stalker:momsbeads:0:1",
        		"stalker:fireball:0:1",
        		"stalker:fireball:0:1",
        		"stalker:fireball:0:1",
        		"stalker:eye:0:1",
        		"stalker:eye:0:1",
        		"stalker:flame:0:1"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");
        itemsGravitationalAnomaly=config.getStringList("GravitationalAnomaly", "AnomalyHeart", new String[]{
        		"stalker:jellyfish:0:1",
        		"stalker:jellyfish:0:1",
        		"stalker:jellyfish:0:1",
        		"stalker:jellyfish:0:1",
        		"stalker:stoneflower:0:1",
        		"stalker:stoneflower:0:1",
        		"stalker:stoneflower:0:1",
        		"stalker:stoneflower:0:1",
        		"stalker:nightstar:0:1",
        		"stalker:nightstar:0:1",
        		"stalker:nightstar:0:1",
        		"stalker:twist:0:1",
        		"stalker:twist:0:1",
        		"stalker:twist:0:1",
        		"stalker:gravi:0:1",
        		"stalker:gravi:0:1",
        		"stalker:goldfish:0:1"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");        
        itemsChemicalAnomaly=config.getStringList("ChemicalAnomaly", "AnomalyHeart", new String[]{
        		"stalker:bloodofstone:0:1",
        		"stalker:bloodofstone:0:1",
        		"stalker:bloodofstone:0:1",
        		"stalker:bloodofstone:0:1",
        		"stalker:soul:0:1",
        		"stalker:soul:0:1",
        		"stalker:soul:0:1",
        		"stalker:chunkofmeat:0:1",
        		"stalker:chunkofmeat:0:1",
        		"stalker:chunkofmeat:0:1",
        		"stalker:gingerbreadman:0:1",
        		"stalker:gingerbreadman:0:1",
        		"stalker:bubble:0:1",
        		"stalker:bubble:0:1",
        		"stalker:firefly:0:1"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");     
        itemsElectricalAnomaly=config.getStringList("ElectricalAnomaly", "AnomalyHeart", new String[]{
        		"stalker:sparkler:0:1",
        		"stalker:sparkler:0:1",
        		"stalker:sparkler:0:1",
        		"stalker:sparkler:0:1",
        		"stalker:battery:0:1",
        		"stalker:battery:0:1",
        		"stalker:battery:0:1",
        		"stalker:moonlight:0:1",
        		"stalker:moonlight:0:1",
        		"stalker:moonlight:0:1",
        		"stalker:flash:0:1",
        		"stalker:flash:0:1",
        		"stalker:flash:0:1",
        		"stalker:dummy:0:1",
        		"stalker:dummy:0:1",
        		"stalker:snowflake:0:1"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");
        itemStash=config.getStringList("ItemStash", "LootStash", new String[]{
        		"stalker:banknote1000rubles:0:1",
        		"stalker:banknote500rubles:0:1",
        		"stalker:banknote100rubles:0:1",
        		"stalker:banknote50rubles:0:1",
        		"stalker:firstaidkit:0:1",
        		"stalker:armyfirstaidkit:0:1",
        		"stalker:scientificfirstaidkit:0:1",
        		"stalker:antirad:0:1",
        		"stalker:bandage:0:1",
        		"stalker:bread:0:1",
        		"stalker:sausage:0:1",
        		"stalker:cannedfood:0:1",
        		"stalker:vodka:0:1",
        		"modularwarfare:stalker.7_62x39:0:30",
        		"modularwarfare:stalker.5_56x45:0:30",
        		"modularwarfare:stalker.9x19:0:30",
        		"modularwarfare:stalker.9x18:0:30",
        		"modularwarfare:stalker.12gauge:0:30",
        		"modularwarfare:stalker.12sluggauge:0:30",
        		"modularwarfare:stalker.7_62x39:0:45",
        		"modularwarfare:stalker.5_56x45:0:45",
        		"modularwarfare:stalker.9x19:0:45",
        		"modularwarfare:stalker.9x18:0:45",
        		"modularwarfare:stalker.12gauge:0:45",
        		"modularwarfare:stalker.12sluggauge:0:45",
        		"modularwarfare:stalker.pmammo:0:1",
        		"modularwarfare:stalker.ak74:0:1",
        		"modularwarfare:stalker.ak74ammo:0:1",
        		"modularwarfare:stalker.pm:0:1",
        		"modularwarfare:stalker.pmammo:0:1",
        		"modularwarfare:stalker.toz34:0:1"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");
        
        weightTable=config.getStringList("ItemsWeights", "Weight", new String[]{
        		"modularwarfare:stalker.heavysuit:0:18",
        		"modularwarfare:stalker.heavysuit.bandit:0:22",
        		"modularwarfare:stalker.heavysuit.cn:0:19",
        		"modularwarfare:stalker.heavysuit.dolg:0:24",
        		"modularwarfare:stalker.heavysuit.merc:0:15",
        		"modularwarfare:stalker.heavysuit.monolit:0:20",
        		"modularwarfare:stalker.heavysuit.svoboda:0:16",
        		"modularwarfare:stalker.aegisarmor1:0:5",
        		"modularwarfare:stalker.aegisarmor2:0:5",
        		"modularwarfare:stalker.aegisarmor3:0:5",
        		"modularwarfare:stalker.aegisarmor4:0:5",
        		"modularwarfare:stalker.aegisarmor5:0:5",
        		"modularwarfare:stalker.aegisarmor.dolg:0:5",
        		"modularwarfare:stalker.aegisarmor.svoboda:0:5",
        		"modularwarfare:stalker.berill:0:7",
        		"modularwarfare:stalker.berill.dolg:0:7",
        		"modularwarfare:stalker.berill.mon:0:7",
        		"modularwarfare:stalker.berill.bandit:0:7",
        		"modularwarfare:stalker.bylat:0:12",
        		"modularwarfare:stalker.bylat.dolg:0:12",
        		"modularwarfare:stalker.bylat.merc:0:12",
        		"modularwarfare:stalker.kyrtka:0:3",
        		"modularwarfare:stalker.kyrtka.bandit:0:3",
        		"modularwarfare:stalker.kyrtka.svoboda:0:3",
        		"modularwarfare:stalker.kyrtka.cn:0:3",
        		"modularwarfare:stalker.seva:0:9",
        		"modularwarfare:stalker.zarya:0:5",
        		"modularwarfare:stalker.zarya.bandit:0:5",
        		"modularwarfare:stalker.zarya.dolg:0:5",
        		"modularwarfare:stalker.zarya.merc:0:5",
        		"modularwarfare:stalker.zarya.mon:0:5",
        		"modularwarfare:stalker.zarya.svoboda:0:5",
        		"modularwarfare:stalker.ps39m:0:9",
        		"modularwarfare:stalker.ps39m.dolg:0:9",
        		"modularwarfare:stalker.ps39m.merc:0:9",
        		"modularwarfare:stalker.ps39m.voen:0:9",
        		"modularwarfare:stalker.backpack:0:5",
        		"modularwarfare:stalker.exoskelet:0:20",
        		"modularwarfare:stalker.ak74:0:3.63",
        		"modularwarfare:stalker.ak74_762x54:0:3.66",
        		"modularwarfare:stalker.ak74u:0:2.71",
        		"modularwarfare:stalker.an94:0:3.85",
        		"modularwarfare:stalker.asval:0:2.5",
        		"modularwarfare:stalker.beretta:0:0.98",
        		"modularwarfare:stalker.colt:0:1.12",
        		"modularwarfare:stalker.deserteagle:0:1.7",
        		"modularwarfare:stalker.fort:0:0.920",
        		"modularwarfare:stalker.gauss:0:5.5",
        		"modularwarfare:stalker.lr300:0:3.06",
        		"modularwarfare:stalker.mp5sd:0:2.8",
        		"modularwarfare:stalker.pb:0:0.827",
        		"modularwarfare:stalker.pm:0:0.73",
        		"modularwarfare:stalker.sig550:0:4.1",
        		"modularwarfare:stalker.spas12:0:4.4",
        		"modularwarfare:stalker.svd:0:4.5",
        		"modularwarfare:stalker.usp:0:0.79",
        		"modularwarfare:stalker.vss:0:2.6",
        		"modularwarfare:stalker.walter:0:0.88",
        		"modularwarfare:stalker.toz34:0:3.2",
        		"modularwarfare:stalker.obrez:0:2.1",
        		"modularwarfare:stalker.svt:0:3.85",
        		"stalker:detectorresponse:0:0.25",
        		"stalker:detectorbear:0:0.35",
        		"stalker:detectorveles:0:0.45",
        		"stalker:prototypedetectorsvarog:0:0.75",
        		"stalker:firstaidkit:0:0.1",
        		"stalker:armyfirstaidkit:0:0.1",
        		"stalker:scrientificfirstaidkit:0:0.1",
        		"stalker:antirad:0:0.05",
        		"stalker:bandage:0:0.05",
        		"stalker:vodka:0:0.6",
        		"stalker:sausage:0:0.5",
        		"stalker:cannedfood:0:0.5",
        		"stalker:bread:0:0.5",
        		"stalker:energydrink:0:0.5",
        		"stalker:banknote500rubles:0:0.5",
        		"stalker:jellyfish:0:0.5",
        		"stalker:stoneflower:0:0.5",
        		"stalker:crystal:0:0.5",
        		"stalker:bloodofstone:0:0.5",
        		"stalker:sparkler:0:0.5",
        		"stalker:battery:0:0.5",
        		"stalker:soul:0:0.5",
        		"stalker:momsbeads:0:0.5",
        		"stalker:nightstar:0:0.5",
        		"stalker:twist:0:0.5",
        		"stalker:moonlight:0:0.5",
        		"stalker:fireball:0:0.5",
        		"stalker:chunkofmeat:0:0.5",
        		"stalker:flash:0:0.5",
        		"stalker:dummy:0:0.5",
        		"stalker:gingerbreadman:0:0.5",
        		"stalker:eye:0:0.5",
        		"stalker:gravi:0:0.5",
        		"stalker:bubble:0:0.5",
        		"stalker:snowflake:0:0.5",
        		"stalker:firefly:0:0.5",
        		"stalker:flame:0:0.5",
        		"stalker:goldfish:0:0.5"},
        "A list of random sets of items that can drop out to the player in the cache. modid:item:meta:count ");
        
        
        
        
        minItemsCountStash = config.getInt("MinItemsCountStash", "LootStash", 3, 0, 36,"The minimum number of items can be dropped by the player.");
        maxItemsCountStash = config.getInt("MaxItemsCountStash", "LootStash", 6, 0, 36,"The maximum number of items can be dropped by the player.");
        fillingTime = config.getInt("FillingTime", "LootStash", 1500, 0, 10000000,"Time to fill items in the cache in seconds.");
        
        /* enableNausea = config.getBoolean("enableNausea", "Radiation", enableNausea, "Enable Nausea effect");
        nauseaThreshold = config.getInt("nauseaThreshold", "Radiation", nauseaThreshold, 0, 10000000,"Nausea effect starts at radiation level");
        enableHunger = config.getBoolean("enableHunger", "Radiation", enableHunger, "Enable Hunger effect");
        hungerThreshold = config.getInt("hungerThreshold", "Radiation", hungerThreshold, 0, 10000000, "Hunger effect starts at radiation level");
        enableBlindness = config.getBoolean("enableBlindness", "Radiation", enableBlindness, "Enable Blindness effect");
        blindnessThreshold = config.getInt("blindnessThreshold", "Radiation", blindnessThreshold, 0, 10000000, "Blindness effect starts at radiation level");
        enablePoison = config.getBoolean("enablePoison", "Radiation", enablePoison, "Enable Poison effect");
        poisonThreshold = config.getInt("poisonThreshold", "Radiation", poisonThreshold, 0, 10000000, "Poison effect starts at radiation level");
        radiationStrength = config.getInt("radiationStrength", "Radiation", radiationStrength, 0, 1000, "Amount of radiation player gains in 20 ticks");
        radiationDelay = config.getInt("radiationDelay", "Radiation", radiationDelay, 0, 10000000, "Radiation in world appears after (in ticks, 1 minecraft day equals 24000 ticks)");
        maximumRadiation = Math.max(Math.max(Math.max(nauseaThreshold, hungerThreshold), blindnessThreshold), poisonThreshold);
        minimumRadiation = Math.min(Math.min(Math.min(nauseaThreshold, hungerThreshold), blindnessThreshold), poisonThreshold);
        isBiomesWhiteList = config.getBoolean("isBiomesWhiteList", "Radiation", isBiomesWhiteList, "Biomes in the list are Whitelisted?");
        Collections.addAll(biomesList, config.getStringList("biomesList", "Radiation", new String[]{"BiomeWasteland"}, "List of Biomes to (not) apply radiation in"));
        blocksToIsolateRadiation = config.getInt("blocksToIsolateRadiation", "Radiation", blocksToIsolateRadiation, 1, 100, "Blocks above players head to prevent radiation accumulation");
        radPillsHealFactor = config.getInt("radPillsHealFactor", "Radiation", radPillsHealFactor, 1, 1000, "Multiply factor (times radiationStrength) for RAD pills effectiveness");
        */
        ExternalItemsAdd.ExternalItemsList();
        ExternalItemsAdd.ExternalItemsAdd();
        OtherMethods.sortItems(weightTable);
        config.save();
    }
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		config = new Configuration(event.getSuggestedConfigurationFile());
        loadConfig();
        
        GameRegistry.registerTileEntity(TileEntityStash.class, new ResourceLocation(STALKERMain.MODID, "stash"));
		GameRegistry.registerTileEntity(TileEntityLocationTransition.class, new ResourceLocation(STALKERMain.MODID, "locationtransition"));
		GameRegistry.registerTileEntity(TileEntityHeartGravitationalAnomaly.class, new ResourceLocation(STALKERMain.MODID, "heartgravitationalanomaly"));
		GameRegistry.registerTileEntity(TileEntityHeartThermicAnomaly.class, new ResourceLocation(STALKERMain.MODID, "heartthermicanomaly"));
		GameRegistry.registerTileEntity(TileEntityHeartChemicalAnomaly.class, new ResourceLocation(STALKERMain.MODID, "heartchemicalanomaly"));
		GameRegistry.registerTileEntity(TileEntityElectricalAnomaly.class, new ResourceLocation(STALKERMain.MODID, "heartelectricalanomaly"));
		
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler
	public void preInitClient(FMLPreInitializationEvent event) {
		OBJLoaderCustom.instance.addDomain(STALKERMain.MODID);
        //OBJLoader.INSTANCE.addDomain(STALKERMain.MODID);
		
		KeybindsRegister.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
        ExternalItemsAdd.ExternalItemsList();
        ExternalItemsAdd.ExternalItemsAdd();
        
        NpcAPI.Instance().events().register(new NPCEventsHandler());
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
        MinecraftForge.EVENT_BUS.register(RenderPlayer.class);
        
        NetworkRegistry.INSTANCE.registerGuiHandler(STALKERMain.instance, new MDGuiHandler());
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler
	public void initClient(FMLInitializationEvent event) {
		//ArmorStats.armorAdd();
		MinecraftForge.EVENT_BUS.register(new RenderHud());

	}

	public static Logger logger() {

		return instance.logger;
	}
	
	/*
	@EventHandler
	public void onPreLoad(FMLPreInitializationEvent evt)
	{
		instance = this;
		MinecraftForge.EVENT_BUS.register(PlayerHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ClientHandler.INSTANCE);
		configFolder = new File(evt.getModConfigurationDirectory(), "playerWeight");
		config = new Configuration(new File(configFolder, "config.cfg"));
		loadUI = config.get("general", "loadHelperUI", false).getBoolean();
		ender = config.get("general", "Include EnderChest", false, "Includes the EnderChest into the WeightCalculation").getBoolean();
		xOffset = config.get("general", "xOffset", 0, "Offsets the Weight Hud horizontally").getInt();
		yOffset = config.get("general", "yOffset", 0, "Offsets the Weight Hud vertically").getInt();
		String[] names = config.get("general", "weightCategories", new String[]{"T", "Kg", "g", "mg"}, "Defines the Weight Definetions, has to be exactly 4 entries").getStringList();
		if(names != null && names.length == 4)
		{
			weightNames = names;
		}
		reloadConfigs(false);
		ApplyAdvancementIncrease.register();
		ApplyPotionEffects.register();
		ApplyExhaustion.register();
		ApplyDamageEffect.register();
		ApplyAttributeEffect.register();
		ApplyPotionIncrease.register();
		ApplyKillRidden.register();
		WeightRegistry.INSTANCE.registerItemHandler(new Function<ItemStack, IItemHandler>(){
			@Override
			public IItemHandler apply(ItemStack t)
			{
				return new ShulkerBoxHandler(t);
			}
		}, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
		if(ender)
		{
			WeightRegistry.INSTANCE.registerExtraPlayerInventory(new Function<EntityPlayer, IItemHandler>(){
				@Override
				public IItemHandler apply(EntityPlayer t)
				{
					return new InvWrapper(t.getInventoryEnderChest());
				}
			});
		}
		for(ASMData data : evt.getAsmData().getAll(WeightPluginListener.class.getCanonicalName()))
		{
			try
			{
				Class clz = Class.forName(data.getClassName());
				if(clz != null)
				{
					WeightPluginListener plug = (WeightPluginListener)clz.getAnnotation(WeightPluginListener.class);
					FMLLog.log.info("Test: "+plug);
					FMLLog.log.info("Loading: ["+plug.name()+", Version="+plug.version()+"]");
					IWeightPlugin modul = (IWeightPlugin)clz.newInstance();
					if(modul != null && modul.canLoad())
					{
						plugins.add(modul);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}*/
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
        event.registerServerCommand(new CommandReloadConfig());
	}
	

	/*
	public void reloadConfigs(boolean reload)
	{
		weightFiles.clear();
		effectFiles.clear();
		try
		{
			if(reload)
			{
				config.load();
			}
			MAX_WEIGHT = config.get("general", "max_weight", 100D, "").getDouble();
			String[] array = config.getStringList("WeightFiles", "general", new String[0], "Select the Files where the Weight Data should be loaded from");
			for(int i = 0;i<array.length;i++)
			{
				File entry = new File(configFolder, array[i]);
				if(entry.exists())
				{
					weightFiles.add(entry);
				}
			}
			array = config.getStringList("EffectFiles", "general", new String[0], "Select the Files where the Effect Data should be loaded from");
			for(int i = 0;i<array.length;i++)
			{
				File entry = new File(configFolder, array[i]);
				if(entry.exists())
				{
					effectFiles.add(entry);
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			config.save();
		}
		if(reload)
		{
			//reload();
		}
	}*/
}
