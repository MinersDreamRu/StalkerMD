package ru.minersdream.stalker.main;

import ru.minersdream.stalker.block.*;
import ru.minersdream.stalker.block.anomaly.BlockAnomalyFunnel;
import ru.minersdream.stalker.block.anomaly.BlockAnomalySpringboard;
import ru.minersdream.stalker.block.anomaly.HeartChemicalAnomaly;
import ru.minersdream.stalker.block.anomaly.HeartElectricalAnomaly;
import ru.minersdream.stalker.block.anomaly.HeartGravitationalAnomaly;
import ru.minersdream.stalker.block.anomaly.HeartThermicAnomaly;

import java.util.*;
import net.minecraft.block.material.*;
import net.minecraft.block.*;
import ru.minersdream.stalker.block.base.*;
import ru.minersdream.stalker.block.base.BaseObjBlock;

public class InitBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();;
    public static final List<String[]> ARMORCHARACTERS = new ArrayList<String[]>();;
    	
    public static final BlockAir SAFEZONE = new SafeZone("safezone");    
    public static final LocationTransition LOCATIONZONE = new LocationTransition("locationtransition", Material.BARRIER, 100000.0f, 100000.0f, SoundType.STONE);    
    public static final BlockAir RADIATION1LVL = new RadiationZone("radiation1lvl", 1);
    public static final BlockAir RADIATION2LVL = new RadiationZone("radiation2lvl", 2);
    public static final BlockAir RADIATION3LVL = new RadiationZone("radiation3lvl", 3);
    public static final BlockAir PSI1LVL  = new PsiZone("psi1lvl", 1);
    public static final BlockAir PSI2LVL  = new PsiZone("psi2lvl", 2);
    public static final BlockAir PSI3LVL  = new PsiZone("psi3lvl", 3);
    public static final BlockAir CHEM1LVL = new ChemZone("chem1lvl", 1);
    public static final BlockAir CHEM2LVL = new ChemZone("chem2lvl", 2);
    public static final BlockAir CHEM3LVL = new ChemZone("chem3lvl", 3);
    public static final BlockAir HEAT1LVL = new HeatZone("heat1lvl", 1);
    public static final BlockAir HEAT2LVL = new HeatZone("heat2lvl", 2);
    public static final BlockAir HEAT3LVL = new HeatZone("heat3lvl", 3);
    
    
    
    //(имя, lighting, hardness, resistanse, soundType, damage, время регенерации, скорость этапа, скорость последнего этапа)
    public static final BlockAnomalySpringboard ANOMALY_SPRINGBOARD = new BlockAnomalySpringboard("anomaly_springboard",0 , 30, 4, 1, 10);
    public static final BlockAnomalyFunnel ANOMALY_FUNNEL = new BlockAnomalyFunnel("anomaly_funnel",0 , 30, 4, 60, 10);
    public static final BlockBaseAnomaly ANOMALY_BORDER = new BlockBaseAnomaly("anomaly_border", 0, 100000.0f, 100000.0f, SoundType.STONE, 5000, 0, 1, 10, "gravitational");
    public static final BlockAnomalyFrying ANOMALY_FRYING = new BlockAnomalyFrying("anomaly_frying",16, 100000.0f, 100000.0f, SoundType.STONE, 30, 3, 1, 80, "thermal");
    public static final BlockAnomalySteam ANOMALY_STEAM = new BlockAnomalySteam("anomaly_steam", 0, 100000.0f, 100000.0f, SoundType.STONE, 30, 3, 1, 80, "thermal");
    public static final BlockAnomalyElectra ANOMALY_ELECTRA = new BlockAnomalyElectra("anomaly_electra", 16, 100000.0f, 100000.0f, SoundType.STONE, 30, 12, 1, 40, "electric");
    public static final BlockAnomalyJelly ANOMALY_JELLY = new BlockAnomalyJelly("anomaly_jelly", 16, 100000.0f, 100000.0f, SoundType.STONE, 30, 4, 1, 10, "chemical");
    
    public static final HeartGravitationalAnomaly HEARTGRAVITATIONALANOMALY = new HeartGravitationalAnomaly ("heartgravitationalanomaly", Material.ROCK, 100000.0f, 100000.0f, SoundType.STONE);
    public static final HeartThermicAnomaly HEARTTHERMICANOMALY = new HeartThermicAnomaly ("heartthermicanomaly", Material.ROCK, 100000.0f, 100000.0f, SoundType.STONE);
    public static final HeartChemicalAnomaly HEARTCHEMICALANOMALY = new HeartChemicalAnomaly ("heartchemicalanomaly", Material.ROCK, 100000.0f, 100000.0f, SoundType.STONE);
    public static final HeartElectricalAnomaly HEARTELECTRICALANOMALY = new HeartElectricalAnomaly("heartelectricalanomaly", Material.ROCK, 100000.0f, 100000.0f, SoundType.STONE);
    
    public static final BlockSatchel SATCHEL = new BlockSatchel("satchel");
    public static final BlockDocCase DOCCASE = new BlockDocCase("doccase");

    public static final BaseObjBlock BOX = new BaseObjBlock("box");
    public static final BlockBarrel BARRET1 = new BlockBarrel("barrel1");
    public static final BlockBarrel BARRET2 = new BlockBarrel("barrel2");
    public static final BlockBarrel BARRET3 = new BlockBarrel("barrel3");
    public static final BlockBarrel BARRET4 = new BlockBarrel("barrel4");
    public static final BlockTable TABLE = new BlockTable("table");
    public static final BlockTable TABLE2 = new BlockTable("table2");
    public static final BlockTable CONCRETEBLOCK1 = new BlockTable("concreteblock1");
    public static final BlockTable CONCRETEBLOCK2 = new BlockTable("concreteblock2");
    public static final BlockTable GAZPLITA = new BlockTable("gaz_plita");
    
    public static final BlockTable DOOR_MTL_1 = new BlockTable("door_mtl_1");
    public static final BlockTable DOOR_MTL_2 = new BlockTable("door_mtl_2");
    public static final BlockNonCollisible DOOR_MTL_3 = new BlockNonCollisible("door_mtl_3");
    public static final BlockTable DOOR_BUNKER_CLOSE = new BlockTable("door_bunker_close");
    public static final BlockNonCollisible DOOR_BUNKER_OPEN = new BlockNonCollisible("door_bunker_open");
    public static final BlockTable CODELOCK = new BlockTable("codelock");
    
    public static final BlockNonCollisible NOTEBOOK = new BlockNonCollisible("notebook");
    public static final BlockNonCollisible NOTEBOOK45 = new BlockNonCollisible("notebook45");
    
    
    
    public static final StashBlock STASH = new StashBlock("stash", Material.ROCK, 100000.0f, 100000.0f, SoundType.STONE);

    public static final BlockFenceObj FENCEANGLE = new BlockFenceObj("fenceangle",true, false);
    public static final BlockFenceObj FENCE1 = new BlockFenceObj("fence1",false, false);
    public static final BlockFenceObj FENCE2 = new BlockFenceObj("fence2",false, false);
    public static final BlockFenceObj FENCE3 = new BlockFenceObj("fence3",false, false);
    public static final BlockFenceObj FENCE4 = new BlockFenceObj("fence4",false, false);

    public static final BlockFenceObj FENCEANGLENULL = new BlockFenceObj("fenceanglenull",true, true);
    public static final BlockFenceObj FENCENULL = new BlockFenceObj("fence1null",false, true);

    public static final BlockRoadBlock ROADBLOCK = new BlockRoadBlock("roadblock", false);
    public static final BlockRoadBlock ROADBLOCKNULL = new BlockRoadBlock("roadblocknull", true);

}
