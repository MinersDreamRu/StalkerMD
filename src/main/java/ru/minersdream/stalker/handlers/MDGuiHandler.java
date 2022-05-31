package ru.minersdream.stalker.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.minersdream.stalker.gui.GuiPDA;

public class MDGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//if (ID == 0)
			//return new NewGuiInventory(player);
		//if (ID == 0)
			//return new GuiMarket(player);
		switch (ID) {
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			return new GuiPDA();

		default:
			return null;
		}
	}

}