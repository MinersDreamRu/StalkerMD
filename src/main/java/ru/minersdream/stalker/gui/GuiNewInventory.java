package ru.minersdream.stalker.gui;

import java.util.ArrayList;
import java.util.Random;

import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.constants.EnumPlayerPacket;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerFactionData;
import ru.minersdream.stalker.gui.container.ContainerNewInventory;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.PlayerStats;


public class GuiNewInventory extends GuiInventory {
	  EntityPlayer player;
	  private ArrayList<Faction> playerFactions;
	  protected int xSize = 256;
	  private static final ResourceLocation BACKGROUND = new ResourceLocation(STALKERMain.MODID, "textures/gui/inventory.png");
	  private static final ResourceLocation BACKGROUNDOVERLAY = new ResourceLocation(STALKERMain.MODID, "textures/gui/inventoryoverlay.png");

	  public GuiNewInventory(EntityPlayer player) {
	    super(player);
	    this.player=player;
	  }
	   
	  @Override
	  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
	    //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    this.mc.getTextureManager().bindTexture(BACKGROUND);
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	    
		

	    int i = this.guiLeft;
	    int j = this.guiTop;
	    drawEntityOnScreen(i + 51, j + 75, 30, (float) (i + 51) - mouseX, (float) (j + 75 - 50) - mouseY, Minecraft.getMinecraft().player);
	    

	  }
	  @Override
	  public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	      String uuid=player.getUniqueID().toString();

	      this.fontRenderer.drawString(I18n.format("armor.specifications.maxhealth")+player.getMaxHealth(), 175, 8, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.protection")+PlayerStats.protectiveProperties(player)[0], 175, 16, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.burnProtection")+PlayerStats.protectiveProperties(player)[1], 175, 24, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.electroshockProtection")+PlayerStats.protectiveProperties(player)[2], 175, 32, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.chemicalBurnProtection")+PlayerStats.protectiveProperties(player)[3], 175, 40, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.explosionProtection")+PlayerStats.protectiveProperties(player)[4], 175, 48, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.psiProtection")+PlayerStats.protectiveProperties(player)[5], 175, 56, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.radiationProtection")+PlayerStats.protectiveProperties(player)[6], 175, 64, 4210752);
	      this.fontRenderer.drawString(I18n.format("armor.specifications.weightBonus")+PlayerStats.protectiveProperties(player)[7], 175, 72, 4210752);
	      this.fontRenderer.drawString(I18n.format("player.balance"), 175, 134, 4210752);
	      this.fontRenderer.drawString(I18n.format("player.weight"), 175, 149, 4210752);
	      this.fontRenderer.drawString(player.getName(), 175, 119, 4210752);
	      this.fontRenderer.drawString(String.format("%.2f", PlayerStats.getPlayerWeight(player))+"/"+(int)PlayerStats.getMaxPlayerWeight(player)+I18n.format("weight.unit"), 208, 149, 4210752);
	      //this.fontRenderer.drawString(WeightRegistry.getPlayerWeight(player)+"/"+(int)(WeightRegistry.getMaxPlayerWeight(player)+WeightRegistry.getPlayerWeightBonus(player))+I18n.format("weight.unit"), 208, 149, 4210752);
	      this.fontRenderer.drawString(PlayerStats.getBalance(player)+" "+I18n.format("player.currency"), 208, 134, 4210752);
	      
	      //this.fontRenderer.drawString(balance+" "+I18n.format("player.currency"), 208, 134, 4210752);

	      /*
	      GlStateManager.enableRescaleNormal();
	      RenderHelper.enableGUIStandardItemLighting();
	      
	      RenderHelper.disableStandardItemLighting();
	      GlStateManager.disableRescaleNormal();*/
	  }
	 /* 
	  private int getProtectionLvL(int id) {
		  int protectionlevel=0;
		  if(player.inventory.armorInventory.get(3).getItem() instanceof ArmorStats) {
			  protectionlevel+=getProtectionLvL((BaseArmor) player.inventory.armorInventory.get(3).getItem(),id);
		  }
		  if(player.inventory.armorInventory.get(2).getItem() instanceof ArmorStats) {
			  protectionlevel+=getProtectionLvL((BaseArmor) player.inventory.armorInventory.get(2).getItem(),id);

		  }
		  if(player.inventory.armorInventory.get(1).getItem() instanceof ArmorStats) {
			  protectionlevel+=getProtectionLvL((BaseArmor) player.inventory.armorInventory.get(1).getItem(),id);

		  }
		  
		  return protectionlevel;  
	  }
	  private int getProtectionLvL(BaseArmor item, int id) {
		switch (id) {
		default:
			return item.getProtection();
		case 1:
			return item.getBurnProtection(); 
		case 2:
			
			return item.getElectroshockProtection(); 
		case 3:
			
			return item.getChemicalBurnProtection(); 
		case 4:
			
			return item.getExplosionProtection(); 
		case 5:
			
			return item.getPsiProtection(); 
		case 6:
			
			return item.getRadiationProtection(); 
		case 7:
			
			return item.getWeightBonus(); 
		}

	  }*/
}