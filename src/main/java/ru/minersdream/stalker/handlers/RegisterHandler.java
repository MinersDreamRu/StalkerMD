package ru.minersdream.stalker.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.minersdream.stalker.main.*;
import ru.minersdream.stalker.interfaces.IHasModel;
import scala.concurrent.duration.Duration.Infinite;

@EventBusSubscriber
public class RegisterHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(InitBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {

		for (Item item : InitItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}

		for (Block block : InitBlocks.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
			}
		}

	}

	@SubscribeEvent
	public static void regSounds(RegistryEvent.Register<SoundEvent> e) {
		ForgeRegistries.SOUND_EVENTS.registerAll(InitSounds.SOUNDS.toArray(new SoundEvent[0]));
	}

}
