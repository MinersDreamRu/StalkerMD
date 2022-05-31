package ru.minersdream.stalker.events;

import javax.swing.text.Position;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.modularwarfare.common.guns.ItemAmmo;
import com.modularwarfare.common.guns.ItemGun;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerFactionData;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.PlayerStats;

public class RenderHud {
	@SideOnly(Side.CLIENT)
	private static Minecraft mc = Minecraft.getMinecraft();
	public static final ResourceLocation HOTBAR = new ResourceLocation(STALKERMain.MODID, "textures/gui/hotbar.png");
	public static final ResourceLocation WIDGETS = new ResourceLocation("minecraft", "textures/gui/widgets.png");
	public static final int i1 = 66;
	public static int increment = 0;

	private FontRenderer fontRenderer = mc.fontRenderer;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent // (priority = EventPriority.LOW)
	public void render(RenderGameOverlayEvent.Pre event) {

		EntityPlayer player = (EntityPlayer) mc.getRenderViewEntity();
		// data.loadNBTData(arg0);
		if (!player.isCreative()&&!player.isSpectator()) {
			RenderGameOverlayEvent.ElementType type = event.getType();
			Entity renderViewEntity = mc.getRenderViewEntity();
			if (!(renderViewEntity instanceof EntityPlayer))
				return;
			int scaledWidth = event.getResolution().getScaledWidth();
			int scaledHeight = event.getResolution().getScaledHeight();
			float xStart = scaledWidth - 256;
			float yStart = scaledHeight - 64;
			switch (type) {
			case ALL:

				break;
			case ARMOR: {
				event.setCanceled(true);
				break;
			}
			case HEALTH: {
				event.setCanceled(true);
				break;
			}
			case HOTBAR: {
				event.setCanceled(true);
				
				GlStateManager.pushMatrix();
				GlStateManager.enableBlend();
				// draw background
				mc.getTextureManager().bindTexture(HOTBAR);
				GlStateManager.disableDepth();
				drawTexturedModalRect(xStart + 115, yStart + 36, 116, 0, 140, 27);
				GlStateManager.enableDepth();
				// draw icons
				drawIconAnomaly(player, scaledWidth, scaledHeight);
				drawIcon(player, scaledWidth, scaledHeight);

				mc.getTextureManager().bindTexture(HOTBAR);
				double health = player.getHealth();
				double maxHealth = player.getMaxHealth();
				double healthPercentage = 110 / maxHealth * health;
				// System.out.println(health+":"+maxHealth+":"+healthPercentage);

				drawTexturedModalRect(xStart + 143, yStart + 39, 144, 32, (int) healthPercentage, 5);
				drawItems();
				if (mc.player.inventory.getCurrentItem().getItem() != Items.AIR)
					drawStringOnHUD(mc.player.inventory.getCurrentItem().getDisplayName(),
							xStart + 250 - fontRenderer.getStringWidth(mc.player.inventory.getCurrentItem().getDisplayName()),
							yStart + 48, 0xFFFFFF);

				GlStateManager.popMatrix();

				// draw MainItem
				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				RenderHelper.enableGUIStandardItemLighting();

				GlStateManager.translate(scaledWidth - 135 + 4, scaledHeight - 24 + 12, 0);
				GlStateManager.scale(1.6, 1.6, 2);
				GlStateManager.translate(-(scaledWidth - 24 + 6), -(scaledHeight - 42 + 10), 0);
				mc.getRenderItem().renderItemAndEffectIntoGUI(mc.player.inventory.getCurrentItem(), scaledWidth - 24,
						scaledHeight - 42);

				RenderHelper.disableStandardItemLighting();
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();



				// GlStateManager.pushMatrix();
				// GL11.glColor3f(0.8f, 0, 0);
				// GL11.glColor3b((byte)0, (byte)0, (byte)50);

				// Выброс
				final long worldTime = player.getEntityWorld().getTotalWorldTime();
				int countDay = (int) (worldTime / 24000);
				double timeHour = (double) ((worldTime % 24000)) / 1000;
				final Block block = player.world.getBlockState(player.getPosition()).getBlock();
				if (countDay % 7 == 0 && timeHour >= 10 && timeHour <= 13.5) {
					if (10 <= timeHour && timeHour <= 10.5) {

					} else if (10.5 < timeHour && timeHour <= 11.5) {

					} else if (11.5 < timeHour && timeHour <= 12.5) {

					} else if (12.5 < timeHour && timeHour <= 13.0) {

					} else if (13.0 < timeHour && timeHour <= 13.1) {

					}

				}
				// GlStateManager.popMatrix();
				RenderPlayerAmmo(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());

			}
			case FOOD: {
				event.setCanceled(true);
				break;
			}
			case EXPERIENCE: {
				event.setCanceled(true);
				break;
			}
			}

		}
	}

	private void drawItems() {
		// Draw items on hotbar
		GlStateManager.enableRescaleNormal();
		RenderHelper.enableGUIStandardItemLighting();
		for (int i = 0; i < 9; i++) {

			int itemX = getXForSlot(i);
			int itemY = getYForSlot(i) + 4;
			ItemStack item = mc.player.inventory.getStackInSlot(i);
			mc.getTextureManager().bindTexture(HOTBAR);

			if (i < 3)
				drawTexturedModalRect(itemX - 2, itemY - 3, 0, 0, 20, 20);
			else
				drawTexturedModalRect(itemX - 2, itemY - 3, 0, 0, 20, 20);
			if (!item.isEmpty()) {

				float pickupAnimation = item.getAnimationsToGo() - 1;
				if (pickupAnimation > 0.0F) {
					GlStateManager.pushMatrix();
					float scale = 1 + pickupAnimation / 5;
					GlStateManager.translate(itemX + 8, itemY + 12, 0);
					GlStateManager.scale(1 / scale, scale + 1 / 2f, 1);
					GlStateManager.translate(-(itemX + 8), -(itemY + 12), 0);
				}
				mc.getRenderItem().renderItemAndEffectIntoGUI(item, itemX, itemY);
				if (pickupAnimation > 0.0F)
					GlStateManager.popMatrix();
				mc.getRenderItem().renderItemOverlays(mc.fontRenderer, item, itemX, itemY);
			}
		}
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
	}

	private static void drawIcon(EntityPlayer player, int scaledWidth, int scaledHeight) {
		//double maxWeight = WeightRegistry.getMaxPlayerWeight(player) + WeightRegistry.getPlayerWeightBonus(player);
		double maxWeight = PlayerStats.getMaxPlayerWeight(player) + PlayerStats.protectiveProperties(player)[7];
		double playerWeight = PlayerStats.getPlayerWeight(player);

		mc.getTextureManager().bindTexture(HOTBAR);

		if (playerWeight > maxWeight) {
			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 40, 128, 224, 32, 32);
		} else if (playerWeight > maxWeight - 10) {
			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 40, 128, 192, 32, 32);
		} else if (playerWeight > maxWeight - 15) {
			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 40, 128, 160, 32, 32);
		}

		double hunger = player.getFoodStats().getFoodLevel();
		if (hunger < 5) {

			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 80, 160, 224, 32, 32);
		} else if (hunger < 10) {

			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 80, 160, 192, 32, 32);
		} else if (hunger < 15) {
			drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4 + 80, 160, 160, 32, 32);
		}

	}

	private static void drawIconAnomaly(EntityPlayer player, int scaledWidth, int scaledHeight) {
		short posX = 0;
		short posY = 0;
		short id = 0;
		short level = 0;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++) {

					BlockPos pos = new BlockPos((int) player.getPosition().getX() - 1 + x,
							(int) player.getPosition().getY() - 1 + y, (int) player.getPosition().getZ() - 1 + z);
					Block block = player.world.getBlockState(pos).getBlock();
					if (block.equals(InitBlocks.PSI3LVL)) {
						id = 1;
						level = 2;
					} else if (block.equals(InitBlocks.HEAT3LVL) && level <= 2) {
						id = 2;
						level = 2;
					} else if (block.equals(InitBlocks.RADIATION3LVL) && level <= 2) {
						id = 3;
						level = 2;
					} else if (block.equals(InitBlocks.CHEM3LVL) && level <= 2) {
						id = 4;
						level = 2;
					} else if (block.equals(InitBlocks.PSI2LVL) && level <= 1) {
						id = 1;
						level = 1;

					} else if (block.equals(InitBlocks.HEAT2LVL) && level <= 1) {
						id = 2;
						level = 1;
					} else if (block.equals(InitBlocks.RADIATION2LVL) && level <= 1) {
						id = 3;
						level = 1;
					} else if (block.equals(InitBlocks.CHEM2LVL) && level <= 1) {
						id = 4;
						level = 1;

					} else if (block.equals(InitBlocks.PSI1LVL) && level <= 0) {
						id = 1;
						level = 0;
					} else if (block.equals(InitBlocks.HEAT1LVL) && level <= 0) {
						id = 2;
						level = 0;
					} else if (block.equals(InitBlocks.RADIATION1LVL) && level <= 0) {
						id = 3;
						level = 0;
					} else if (block.equals(InitBlocks.CHEM1LVL) && level <= 0) {
						id = 4;
						level = 0;
					}

				}
			}
		}
		if (id == 0)
			return;
		switch (id) {
		default:

			break;
		case 1:
			posX = 0;
			posY = 160;
			break;
		case 2:
			posX = 32;
			posY = 160;
			break;
		case 3:
			posX = 64;
			posY = 160;
			break;
		case 4:
			posX = 96;
			posY = 160;
			break;
		case 5:
			posX = 128;
			posY = 160;
			break;
		case 6:
			posX = 160;
			posY = 160;
			break;
		case 7:
			posX = 192;
			posY = 160;
			break;
		case 8:
			posX = 224;
			posY = 160;
			break;

		}
		posY += 32 * level;
		mc.getTextureManager().bindTexture(HOTBAR);
		drawTexturedModalRect(scaledWidth - 32, scaledHeight / 4, posX, posY, 32, 32);
	}

	public static void drawTexturedModalRect(float x, float y, int textureX, int textureY, int width, int height) {
		Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(x, y, textureX, textureY, width, height);
	}

	private int[] getHotbarCoords(int slot) {
		Minecraft minecraft = Minecraft.getMinecraft();
		ScaledResolution scaledResolution = new ScaledResolution(minecraft);
		int[] coords = new int[2];

		coords[0] = 69- i1 + 20 * (slot);
		coords[1] = scaledResolution.getScaledHeight()-21;
		return coords;
	}

	private int getXForSlot(int slot) {
		int[] coords = getHotbarCoords(slot);
		return coords[0];
	}

	private int getYForSlot(int slot) {
		int[] coords = getHotbarCoords(slot);
		return coords[1];
	}

	public void drawStringOnHUD(String string, float xOffset, float yOffset, int color) {
		fontRenderer.drawString(string, 2 + xOffset, 2 + yOffset, color, true);
	}
	
	private void RenderPlayerAmmo(int i, int j) {
		ItemStack stack = mc.player.getHeldItem(EnumHand.MAIN_HAND);
		if (stack != null && stack.getItem() instanceof ItemGun) {
			int currentAmmoCount = ItemGun.getMagazineBullets(stack);


			if (stack.getTagCompound() != null) {
				ItemStack ammoStack = new ItemStack(stack.getTagCompound().getCompoundTag("ammo"));
				if (ammoStack.getTagCompound() != null) {
					ItemAmmo itemAmmo = (ItemAmmo) ammoStack.getItem();
					int x = 0;
					final int top = j - 38;
					final int left = 2;
					final int right = Math.min(left + 66, i / 2 - 60);
					final int bottom = top + 22;
					//Gui.drawRect(left, top, right, bottom, color);
					//Gui.drawRect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
					//Gui.drawRect(left + right-3, top, right * 2 - 18, bottom, Integer.MIN_VALUE);

					String color = TextFormatting.WHITE+"";
					if(currentAmmoCount < itemAmmo.type.ammoCapacity/6){
						color = TextFormatting.RED+"";
						mc.fontRenderer.drawStringWithShadow(String.valueOf(TextFormatting.YELLOW+ "[R]" +TextFormatting.WHITE+" Reload"), left + 120, j - 30, 0xffffff);
					}

					RenderHelper.enableGUIStandardItemLighting();
					GL11.glEnable(GL12.GL_RESCALE_NORMAL);
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
					//drawSlotInventory(mc.fontRenderer, ammoStack, left + 67, j - 35);
					GL11.glDisable(GL12.GL_RESCALE_NORMAL);
					RenderHelper.disableStandardItemLighting();
					String s = String.valueOf(color+currentAmmoCount) + "/" + itemAmmo.type.ammoCapacity;

					mc.fontRenderer.drawStringWithShadow(String.valueOf(s), i-100, j - 14, 0xffffff);
					x += 16 + mc.fontRenderer.getStringWidth(s);
				}
			}
		}
	}
}
