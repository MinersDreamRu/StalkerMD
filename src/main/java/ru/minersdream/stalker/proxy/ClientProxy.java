package ru.minersdream.stalker.proxy;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.minersdream.stalker.events.RenderHud;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.KeybindsRegister;
import ru.minersdream.stalker.main.STALKERMain;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	@Override
	public void registerModel(Item item, int metadata) 
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
	}
	

	@Override
    public void init(FMLInitializationEvent event) {
		
	}
	
	
}
