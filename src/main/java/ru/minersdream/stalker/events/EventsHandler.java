package ru.minersdream.stalker.events;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import noppes.npcs.api.event.QuestEvent;
import ru.minersdream.stalker.gui.GuiNewInventory;
import ru.minersdream.stalker.item.BasicItems;
import ru.minersdream.stalker.main.KeybindsRegister;
import ru.minersdream.stalker.main.STALKERDamageSource;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.OtherMethods;
import ru.minersdream.stalker.other.PlayerStats;

public class EventsHandler {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void playerTick(PlayerTickEvent event) {
		if(KeybindsRegister.KEY_PDA.isPressed()) {
			event.player.openGui(STALKERMain.instance, 0, event.player.world, 0, 0, 0);
		}
	}
	
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onItemTooltipRender(final ItemTooltipEvent e) {
    	ItemStack stack = e.getItemStack();

		if(stack.isEmpty())
		{
			return;
		}
        if (PlayerStats.isArmor(e.getItemStack().getUnlocalizedName())) {
        	int[] stats = PlayerStats.getArmorStats(e.getItemStack().getUnlocalizedName());
        	e.getToolTip().add(I18n.format("armor.specifications"));
            e.getToolTip().add(I18n.format("armor.specifications.protection")+" "+TextFormatting.GREEN+stats[0]);
            e.getToolTip().add(I18n.format("armor.specifications.burnProtection")+" "+TextFormatting.GREEN+stats[1]);
            e.getToolTip().add(I18n.format("armor.specifications.electroshockProtection")+" "+TextFormatting.GREEN+stats[2]);
            e.getToolTip().add(I18n.format("armor.specifications.chemicalBurnProtection")+" "+TextFormatting.GREEN+stats[3]);
            e.getToolTip().add(I18n.format("armor.specifications.explosionProtection")+" "+TextFormatting.GREEN+stats[4]);
            e.getToolTip().add(I18n.format("armor.specifications.psiProtection")+" "+TextFormatting.GREEN+stats[5]);
            e.getToolTip().add(I18n.format("armor.specifications.radiationProtection")+" "+TextFormatting.GREEN+stats[6]);
            e.getToolTip().add(I18n.format("armor.specifications.weightBonus")+" "+TextFormatting.GREEN+stats[7]);
            
            //e.getToolTip().add(TextFormatting.GREEN + Personal);
        }
        
        if(stack.getItem() instanceof BasicItems) {
        	
        	switch (stack.getItem().getRarity(stack).toString()) {
    		case "RARITY_UNCOMMON":
            	e.getToolTip().add(1,I18n.format("item.rarity")+TextFormatting.GREEN+I18n.format("item.rarity.uncommon"));
    			break;
    		case "RARITY_RARE":
            	e.getToolTip().add(1,I18n.format("item.rarity")+TextFormatting.BLUE+I18n.format("item.rarity.rare"));
    			break;
    		case "RARITY_EPIC":
            	e.getToolTip().add(1,I18n.format("item.rarity")+TextFormatting.RED+I18n.format("item.rarity.epic"));
    			break;
    		case "RARITY_MYTHICAL":
            	e.getToolTip().add(1,I18n.format("item.rarity")+TextFormatting.DARK_PURPLE+I18n.format("item.rarity.mythical"));
    			break;
    		case "RARITY_LEGENDARY":
            	e.getToolTip().add(1,I18n.format("item.rarity")+TextFormatting.GOLD+I18n.format("item.rarity.legendary"));
    			break;
    			
    		default:
    			break;
    		}
        	
        }
        
		//e.getToolTip().add((GuiScreen.isShiftKeyDown() ? I18n.format("stalker.stackweight") : I18n.format("stalker.itemweight")) + ClientHandler.createToolTip(PlayerHandler.calculateStack(e.getItemStack(), GuiScreen.isShiftKeyDown())));
        e.getToolTip().add(1,(GuiScreen.isShiftKeyDown() ? I18n.format("stalker.stackweight") : I18n.format("stalker.itemweight")) + (GuiScreen.isShiftKeyDown()?OtherMethods.getItemWeight(e.getItemStack().getItem().getRegistryName().toString()+":"+e.getItemStack().getItemDamage())*e.getItemStack().getCount():OtherMethods.getItemWeight(e.getItemStack().getItem().getRegistryName().toString()+":"+e.getItemStack().getItemDamage())));
        
        /*
        if (isNodropq(e.getItemStack())) {
            e.getToolTip().add(TextFormatting.GREEN + Nodrop);
        }
        if (isShop(e.getItemStack())) {
            e.getToolTip().add(TextFormatting.GREEN + Shop);
        }*/
    }
	

    
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		
		if (event.getGui() == null) {
			return;
		}
		GuiScreen gui = event.getGui();
		if (gui.getClass() == net.minecraft.client.gui.inventory.GuiInventory.class
				&& gui instanceof GuiNewInventory == false) {
			gui = new GuiNewInventory(Minecraft.getMinecraft().player);
			//gui = new GuiMarket(Minecraft.getMinecraft().player);
			
			event.setGui(gui);
		}
		//gui = new MarketScreen(Minecraft.getMinecraft().player.inventoryContainer);
		//event.setGui(gui);

	}

	@SubscribeEvent
	public void onLivingHurt(final LivingHurtEvent event) {
		/*
		 * final Random random = new Random(); final float damage = event.getAmount();
		 * final EntityLivingBase living = event.getEntityLiving(); if (!(living
		 * instanceof EntityPlayer)) { return; } if (random.nextBoolean() && damage >=
		 * 4.0f) { living.addPotionEffect(new PotionEffect(MobEffects.POISON, 80, 3)); }
		 */
	}
	
	
	
	@SubscribeEvent
	public void weightApplyEffects(final LivingEvent.LivingUpdateEvent event) {
		
		if(!(event.getEntity() instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.getEntity();
		double totalWeight=PlayerStats.getPlayerWeight(player);
		int maxWeight=PlayerStats.getMaxPlayerWeight(player);
		if(!player.isCreative())
		if(totalWeight>maxWeight) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 100));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10, -100));
		}else if(totalWeight>maxWeight-5) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 1));
		}else if(totalWeight>maxWeight-10) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 0));
		}
	}
	
	
	
	
	@SubscribeEvent
	public void LivingDamageEvent(LivingDamageEvent event) {
		if(!(event.getEntity() instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer) event.getEntity();
		
		Boolean debug=false;
		if(debug) {
			System.out.println(event.getAmount());
			System.out.println(event.getSource().damageType);
			
		}
		
		
		int percent=1;
		switch (event.getSource().getDamageType()) {
		case "fromThermAnomalies":
			percent=PlayerStats.protectiveProperties(player)[1];
			break;
		case "fromElectricalAnomalies":
			percent=PlayerStats.protectiveProperties(player)[2];
			break;
		case "fromChemAnomalies":
			percent=PlayerStats.protectiveProperties(player)[3];
			break;
		case "fromExplosionAnomalies":
			percent=PlayerStats.protectiveProperties(player)[4];
			break;

		default:
			percent=0;
			break;
		}
		
		
		double newDamage=event.getAmount()*((double)(20-percent)/20);
		
		event.setAmount((float) newDamage);
	}
	
	@SubscribeEvent
	public void LivingUpdateEvent(final LivingEvent.LivingUpdateEvent event) {
		
			
		
		final Entity entity = event.getEntity();
		final long totalWorldTime = entity.getEntityWorld().getTotalWorldTime();
		final long worldTime = entity.getEntityWorld().getWorldTime();
		final World world = entity.getEntityWorld();
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		
		final EntityPlayer player = (EntityPlayer) entity;
		
		/*
        player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 10, 1, false, false));
        ItemStack armorskin = player.inventory.armorInventory.get(0);
        
        if(armorskin.getItem()==Items.AIR&&armorskin.getItem()!=null) {
        	Random rand = new Random();
            ResourceLocation resourcelocation = new ResourceLocation("modularwarfare:stalker.steve"+rand.nextInt(6));
            Item item = Item.REGISTRY.getObject(resourcelocation);
            player.inventory.armorInventory.set(0, new ItemStack(item, 1, 0));
        }
        if(!armorskin.isItemEnchanted()) {
           	armorskin.addEnchantment(Enchantments.BINDING_CURSE, 1);
           	armorskin.addEnchantment(Enchantments.VANISHING_CURSE, 1);
        }
		*/
		
		
		//Первый заход
		Boolean firstSpawn=player.getEntityData().getBoolean("firstSpawn");
		if(!firstSpawn) {
			player.getEntityData().setBoolean("firstSpawn", true);
			player.getEntityData().setBoolean("inSafeZone", true);
			player.getEntityData().setIntArray("bonuses", new int[] {0,0,0,0,0,0,0});
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
			player.setHealth(100);
		}
		
		
		final String biome = player.world.getBiome(player.getPosition()).getBiomeClass().getSimpleName();
		final Block block = player.world.getBlockState(player.getPosition()).getBlock();
		int[] properties = PlayerStats.protectiveProperties(player);
		long radiationPlayer = player.getEntityData().getLong("radiation");
		long psiradiationPlayer = player.getEntityData().getLong("psiradiation");
		
		//Выброс
		int countDay = (int) (totalWorldTime/24000);
		double timeHour = (double)((totalWorldTime%24000))/1000;
		
		if(countDay%7==0&&timeHour>=10&&timeHour<=13.5) {
			short stage=0;
			Boolean safety=	player.getEntityData().getBoolean("inSafeZone");
			TextComponentTranslation descTranslated = new TextComponentTranslation("stalker.blowout.finish");
			
			if(timeHour==10)
				stage=-1;
			else
			if(timeHour<=10.5) {
				stage=1;
				descTranslated=new TextComponentTranslation("stalker.blowout.message1", stage);
			}
			else
			if(10.5<timeHour&&timeHour<=11.5) {
				stage=2;
				descTranslated=new TextComponentTranslation("stalker.blowout.message2", stage);
			}
			else
			if(11.5<timeHour&&timeHour<=12.5) {
				stage=3;
				descTranslated=new TextComponentTranslation("stalker.blowout.message3", stage);
			}
			else
			if(12.5<timeHour&&timeHour<=13.0) {
				stage=4;
				descTranslated=new TextComponentTranslation("stalker.blowout.message4", stage);
			}
			else
			if(13.0<timeHour&&timeHour<=13.1) {
				stage=5;
				descTranslated=new TextComponentTranslation("stalker.blowout.message5", stage);
			}

			if (!safety) {
				damageBlowout(player, totalWorldTime, stage);
			}
			
			if(player.getEntityData().getLong("sawStage")!=stage&&stage!=-1&&!world.isRemote)
				player.sendMessage(descTranslated);

			player.getEntityData().setBoolean("inSafeZone", false);
			player.getEntityData().setLong("sawStage", stage);

		}
		
		
		if (totalWorldTime % 20 == 0) {
			
			
			
			
			
			
			
		}
		
		if (!player.isCreative()) {
			short RadiationZone=player.getEntityData().getShort("RadiationZoneLevel");
			short PsiZone=player.getEntityData().getShort("PsiZoneLevel");
			short ChemZone=player.getEntityData().getShort("ChemZoneLevel");
			short HeatZone=player.getEntityData().getShort("HeatZoneLevel");

			if(RadiationZone!=0&&totalWorldTime % 20==0) {
				
				if (RadiationZone==3) {
					
					if (radiationPlayer + STALKERMain.radiation3LvLPower <= STALKERMain.radiationMax) {
						player.getEntityData().setLong("radiation", (long)(radiationPlayer + (STALKERMain.radiation3LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[6])/20))));
					} else {
						player.getEntityData().setLong("radiation", (long) STALKERMain.radiationMax);
					}
				}else if (RadiationZone==2) {
					if (radiationPlayer + STALKERMain.radiation2LvLPower <= STALKERMain.radiationMax) {
						player.getEntityData().setLong("radiation", (long)(radiationPlayer + (STALKERMain.radiation2LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[6])/20))));
					} else {
						player.getEntityData().setLong("radiation", (long) STALKERMain.radiationMax);
					}
				}else{
					if (radiationPlayer + STALKERMain.radiation1LvLPower <= STALKERMain.radiationMax) {
						player.getEntityData().setLong("radiation", (long)(radiationPlayer + (STALKERMain.radiation1LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[6])/20))));
					} else {
						player.getEntityData().setLong("radiation", (long) STALKERMain.radiationMax);
					}
				}
				player.getEntityData().setShort("RadiationZoneLevel",(short) 0);
			}
			if(PsiZone!=0&&totalWorldTime % 20==0) {

				if (PsiZone==3) {
					if (psiradiationPlayer + STALKERMain.radiation3LvLPower <= STALKERMain.radiationMax) {
						player.getEntityData().setLong("psiradiation", (long)(psiradiationPlayer + (STALKERMain.psi3LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[5])/20))));
					} else {
						player.getEntityData().setLong("psiradiation", (long) STALKERMain.psiMax);
					}
				} else if (PsiZone==2) {
					if (psiradiationPlayer + STALKERMain.radiation2LvLPower <= STALKERMain.radiationMax) {
						player.getEntityData().setLong("psiradiation", (long)(psiradiationPlayer + (STALKERMain.psi2LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[5])/20))));
					} else {
						player.getEntityData().setLong("psiradiation", (long) STALKERMain.psiMax);
					}
				} else {
					if (psiradiationPlayer + STALKERMain.radiation1LvLPower <= STALKERMain.psiMax) {
						player.getEntityData().setLong("psiradiation", (long)(psiradiationPlayer + (STALKERMain.psi1LvLPower*((double)(20-PlayerStats.protectiveProperties(player)[5])/20))));
					} else {
						player.getEntityData().setLong("psiradiation", (long) STALKERMain.radiationMax);
					}
				}
				player.getEntityData().setShort("PsiZoneLevel",(short) 0);
			}
			if(ChemZone!=0) {
				if (ChemZone==3&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[3]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_CHEMZONE, 8);
				}
				if (ChemZone==2&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[3]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_CHEMZONE, 4);
				}
				if (ChemZone==1&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[3]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_CHEMZONE, 2);
				}
				/*
				if (ChemZone==2) {

					if (properties[3] < 3 && totalWorldTime % 40 == 0) {
						player.attackEntityFrom(STALKERDamageSource.FROM_CHEMZONE, 2);
					} else if (properties[3] < 5 && totalWorldTime % 80 == 0) {
						player.setHealth(player.getHealth() - 2);
					}
				} else {
					if (properties[3] < 3 && totalWorldTime % 80 == 0) {
						player.attackEntityFrom(STALKERDamageSource.FROM_CHEMZONE, 2);
					}
				
				}
				*/
				player.getEntityData().setShort("ChemZoneLevel",(short) 0);
				
			}
			if(HeatZone!=0) {

				if (HeatZone==3&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[1]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_THERMZONE, 8);
				}
				if (HeatZone==2&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[1]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_THERMZONE, 4);
				}
				if (HeatZone==1&&totalWorldTime % (20+4*PlayerStats.protectiveProperties(player)[1]) == 0) {
					player.attackEntityFrom(STALKERDamageSource.FROM_THERMZONE, 2);
				}
				
				player.getEntityData().setShort("HeatZoneLevel",(short) 0);
			}
		}
		
		
		if (totalWorldTime % 20 == 0 && !player.isCreative()) {
			if (radiationPlayer >= STALKERMain.radiation3LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_RADZONE, STALKERMain.radiation3LvLDamage);
			}else
			if (radiationPlayer >= STALKERMain.radiation2LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_RADZONE, STALKERMain.radiation2LvLDamage);
			}else
			if (radiationPlayer >= STALKERMain.radiation1LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_RADZONE, STALKERMain.radiation1LvLDamage);
			}

			if (psiradiationPlayer >= STALKERMain.psi3LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_PSIZONE, STALKERMain.psi3LvLDamage);
			}else
			if (psiradiationPlayer >= STALKERMain.psi2LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_PSIZONE, STALKERMain.psi2LvLDamage);
			}else
			if (psiradiationPlayer >= STALKERMain.psi1LvL) {
				player.attackEntityFrom(STALKERDamageSource.FROM_PSIZONE, STALKERMain.psi1LvLDamage);
			}
		}
	}
	
	

	


	public void damageBlowout(EntityPlayer player, long totalWorldTime, int stage) {
		if(!player.isCreative())
		switch (stage) {
		case 2:
			if(totalWorldTime%40==1) {
				player.attackEntityFrom(STALKERDamageSource.FROM_BLOWOUT, 1);
			}
			
			
			
			
			break;
		case 3:
			if(totalWorldTime%80==1) {
				player.attackEntityFrom(STALKERDamageSource.FROM_BLOWOUT, 4);
			}
			
			
			
			
			break;
		case 4:
			if(totalWorldTime%20==1) {
				player.attackEntityFrom(STALKERDamageSource.FROM_BLOWOUT, 8);
			}
			
			
			
			
			
			break;
		case 5:
			player.attackEntityFrom(STALKERDamageSource.FROM_BLOWOUT, 10000000);
			break;
		default:
			break;
		}
	}
	

}
