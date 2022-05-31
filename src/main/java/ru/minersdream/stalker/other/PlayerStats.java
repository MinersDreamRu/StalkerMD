package ru.minersdream.stalker.other;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.minersdream.stalker.main.InitItems;

public class PlayerStats {
	public static int getBalance(EntityPlayer player) {
        int money=0;
		for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);

            if (itemstack.getItem()==InitItems.BANKNOTE10RUBLES)
                money+=10*itemstack.getCount();
            else
            if (itemstack.getItem()==InitItems.BANKNOTE50RUBLES)
            	money+=50*itemstack.getCount();
            else
            if (itemstack.getItem()==InitItems.BANKNOTE100RUBLES)
            	money+=100*itemstack.getCount();
            else
            if (itemstack.getItem()==InitItems.BANKNOTE500RUBLES)
            	money+=500*itemstack.getCount();     
            else
            if (itemstack.getItem()==InitItems.BANKNOTE1000RUBLES)
            	money+=1000*itemstack.getCount();           
            else
            if (itemstack.getItem()==InitItems.BANKNOTE5000RUBLES)
            	money+=5000*itemstack.getCount();            
        }
        
		  return money;
	}
	public static int[] getArmorStats(String name){

        /*
         Защита(1) 
         Ожог(2)
         Электрошок(3)
         Хим ожог(4)
         Разрыв(5)
         Пси-защита(6)
         Радио-защита(7)
         Бонус-переносимого веса(8)
         */
		
		switch (name) {
		case "item.stalker.aegisarmor1":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor2":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor3":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor4":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor5":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor.dolg":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.aegisarmor.svoboda":
			return new int[] {0,9,6,4,10,0,3,0};
		case "item.stalker.berill":
			return new int[] {0,7,3,3,18,0,1,0};
		case "item.stalker.berill.mon":
			return new int[] {0,7,3,3,18,0,1,0};
		case "item.stalker.berill.dolg":
			return new int[] {0,7,3,3,18,0,1,0};
		case "item.stalker.berill.bandit":
			return new int[] {0,7,3,3,18,0,1,0};
		case "item.stalker.bylat":
			return new int[] {0,13,9,6,21,0,3,0};
		case "item.stalker.bylat.dolg":
			return new int[] {0,12,8,5,22,0,3,0};
		case "item.stalker.bylat.merc":
			return new int[] {0,12,8,5,22,0,3,0};
		case "item.stalker.backpack":
			return new int[] {0,0,0,0,0,0,0,25};
		case "item.stalker.exoskelet":
			return new int[] {0,0,0,0,0,0,0,80};
		case "item.stalker.heavysuit":
			return new int[] {0,11,11,11,14,15,4,0};
		case "item.stalker.heavysuit.bandit":
			return new int[] {0,9,9,9,12,12,3,0};
		case "item.stalker.heavysuit.cn":
			return new int[] {0,12,12,12,14,15,5,0};
		case "item.stalker.heavysuit.dolg":
			return new int[] {0,10,10,10,14,15,4,0};
		case "item.stalker.heavysuit.svoboda":
			return new int[] {0,13,13,13,14,15,4,0};
		case "item.stalker.heavysuit.merc":
			return new int[] {0,10,10,10,15,16,5,0};
		case "item.stalker.heavysuit.monolit":
			return new int[] {0,13,13,13,14,16,5,3};
		case "item.stalker.kyrtka":
			return new int[] {0,3,2,2,7,0,0,0};
		case "item.stalker.kyrtka.bandit":
			return new int[] {0,3,2,2,7,0,0,0};
		case "item.stalker.kyrtka.cn":
			return new int[] {6,3,2,2,7,0,0,0};
		case "item.stalker.kyrtka.svoboda":
			return new int[] {6,3,2,2,7,0,0,0};	
		case "item.stalker.plash":
			return new int[] {5,3,0,1,6,0,0,0};
		case "item.stalker.plash.greh":
			return new int[] {5,3,0,1,6,0,0,0};
		case "item.stalker.plash.stal":
			return new int[] {5,3,0,1,6,0,0,0};
		case "item.stalker.seva":
			return new int[] {5,13,15,15,13,18,8,0};
		case "item.stalker.ps39m":
			return new int[] {0,9,9,9,12,13,3,0};
		case "item.stalker.ps39m.dolg":
			return new int[] {0,9,9,9,12,13,3,0};
		case "item.stalker.ps39m.voen":
			return new int[] {0,9,9,9,12,13,3,0};
		case "item.stalker.ps39m.merc":
			return new int[] {0,9,9,9,12,13,3,0};
		case "item.stalker.zarya":
			return new int[] {5,10,7,5,9,0,3,0};
		case "item.stalker.zarya.mon":
			return new int[] {5,10,7,5,9,2,3,5};
		case "item.stalker.zarya.dolg":
			return new int[] {5,12,8,6,10,0,4,-5};
		case "item.stalker.zarya.merc":
			return new int[] {5,12,8,6,10,0,4,5};
		case "item.stalker.zarya.bandit":
			return new int[] {5,9,6,5,9,0,3,0};
		case "item.stalker.zarya.svoboda":
			return new int[] {5,10,7,5,9,0,3,0};
			
		default:
			return new int[] {0,0,0,0,0,0,0,0};
		}
	}
	
	
    public static boolean isArmor(final String name) {
    	int[] stats=getArmorStats(name);
    	for (int i = 0; i < stats.length; i++) {
    		if(stats[i]!=0) return true;
		}
        return false;
    }
    
    public static int[] protectiveProperties(EntityPlayer player) {
		int[] properties = new int[8];
		int[] artBonuses=player.getEntityData().getIntArray("bonuses");
		int protection=0;
		int burnProtection=0; 
		int electroshockProtection=0; 
		int chemicalBurnProtection=0; 
		int explosionProtection=0; 
		int psiProtection=0; 
		int radiationProtection=0; 
		int weightBonus=0; 
		String helmetName=player.inventory.armorInventory.get(3).getItem().getUnlocalizedName();
		String chestplateName=player.inventory.armorInventory.get(2).getItem().getUnlocalizedName();
		String leggingsName=player.inventory.armorInventory.get(1).getItem().getUnlocalizedName();
		String bootsName=player.inventory.armorInventory.get(0).getItem().getUnlocalizedName();
		
		protection+=getArmorStats(helmetName)[0]+getArmorStats(chestplateName)[0]+getArmorStats(leggingsName)[0]+getArmorStats(bootsName)[0];
		burnProtection+=getArmorStats(helmetName)[1]+getArmorStats(chestplateName)[1]+getArmorStats(leggingsName)[1]+getArmorStats(bootsName)[1];
		electroshockProtection+=getArmorStats(helmetName)[2]+getArmorStats(chestplateName)[2]+getArmorStats(leggingsName)[2]+getArmorStats(bootsName)[2];
		chemicalBurnProtection+=getArmorStats(helmetName)[3]+getArmorStats(chestplateName)[3]+getArmorStats(leggingsName)[3]+getArmorStats(bootsName)[3];
		explosionProtection+=getArmorStats(helmetName)[4]+getArmorStats(chestplateName)[4]+getArmorStats(leggingsName)[4]+getArmorStats(bootsName)[4];
		psiProtection+=getArmorStats(helmetName)[5]+getArmorStats(chestplateName)[5]+getArmorStats(leggingsName)[5]+getArmorStats(bootsName)[5];
		radiationProtection+=getArmorStats(helmetName)[6]+getArmorStats(chestplateName)[6]+getArmorStats(leggingsName)[6]+getArmorStats(bootsName)[6];
		weightBonus+=getArmorStats(helmetName)[7]+getArmorStats(chestplateName)[7]+getArmorStats(leggingsName)[7]+getArmorStats(bootsName)[7];
		if(artBonuses.length==7) {
			burnProtection+=artBonuses[2];
			electroshockProtection+=artBonuses[4];
			chemicalBurnProtection+=artBonuses[3];
			explosionProtection+=artBonuses[5];
			psiProtection+=artBonuses[1];
			radiationProtection+=artBonuses[0];
			weightBonus+=artBonuses[6];
		}
		/*if(InitItems.ITEMARMOR.contains(player.inventory.armorInventory.get(3).getItem().getUnlocalizedName())) {
			BaseArmor item=(BaseArmor) player.inventory.armorInventory.get(3).getItem();
			protection+=item.getProtection();
			burnProtection+=item.getBurnProtection();
			electroshockProtection+=item.getElectroshockProtection();
			chemicalBurnProtection+=item.getChemicalBurnProtection();
			explosionProtection+=item.getExplosionProtection();
			psiProtection+=item.getPsiProtection();
			radiationProtection+=item.getRadiationProtection();
			weightBonus+=item.getWeightBonus();
		}
		if(InitItems.ITEMARMOR.contains(player.inventory.armorInventory.get(2).getItem().getUnlocalizedName())) {
			BaseArmor item=(BaseArmor) player.inventory.armorInventory.get(2).getItem();
			protection+=item.getProtection();
			burnProtection+=item.getBurnProtection();
			electroshockProtection+=item.getElectroshockProtection();
			chemicalBurnProtection+=item.getChemicalBurnProtection();
			explosionProtection+=item.getExplosionProtection();
			psiProtection+=item.getPsiProtection();
			radiationProtection+=item.getRadiationProtection();
			weightBonus+=item.getWeightBonus();
		}
		if(InitItems.ITEMARMOR.contains(player.inventory.armorInventory.get(1).getItem().getUnlocalizedName())) {
			BaseArmor item=(BaseArmor) player.inventory.armorInventory.get(1).getItem();
			protection+=item.getProtection();
			burnProtection+=item.getBurnProtection();
			electroshockProtection+=item.getElectroshockProtection();
			chemicalBurnProtection+=item.getChemicalBurnProtection();
			explosionProtection+=item.getExplosionProtection();
			psiProtection+=item.getPsiProtection();
			radiationProtection+=item.getRadiationProtection();
			weightBonus+=item.getWeightBonus();
		}
		if(InitItems.ITEMARMOR.contains(player.inventory.armorInventory.get(0).getItem().getUnlocalizedName())) {
			BaseArmor item=(BaseArmor) player.inventory.armorInventory.get(0).getItem();
			protection+=item.getProtection();
			burnProtection+=item.getBurnProtection();
			electroshockProtection+=item.getElectroshockProtection();
			chemicalBurnProtection+=item.getChemicalBurnProtection();
			explosionProtection+=item.getExplosionProtection();
			psiProtection+=item.getPsiProtection();
			radiationProtection+=item.getRadiationProtection();
			weightBonus+=item.getWeightBonus();
		}*/
		properties[0]=protection;
		properties[1]=burnProtection;
		properties[2]=electroshockProtection;
		properties[3]=chemicalBurnProtection;
		properties[4]=explosionProtection;
		properties[5]=psiProtection;
		properties[6]=radiationProtection;
		properties[7]=weightBonus;
		return properties;
	}
    

    public static double getPlayerWeight(EntityPlayer player) {
    	double playerWeight=0.0;
    	for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            playerWeight+=OtherMethods.getItemWeight(itemstack.getItem().getRegistryName().toString()+":"+itemstack.getItem().getDamage(itemstack))*itemstack.getCount();
            
        }
		return playerWeight;
    }
	public static int getMaxPlayerWeight(EntityPlayer player) {
    	int maxWeight=30;
    	maxWeight+=protectiveProperties(player)[7];
		return maxWeight;
	}
	
	
}
