package ru.minersdream.stalker.other;

import java.util.regex.Matcher;

import com.ibm.icu.impl.TimeZoneGenericNames.Pattern;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;
import scala.actors.threadpool.Arrays;
import scala.reflect.internal.Trees.This;

public class OtherMethods {
	public static String[] items;
	public static double[] weightItems;
	
	public static void sortItems(String[] weightTable) {
		//Arrays.fill(OtherMethods.items, null);
		//Arrays.fill(OtherMethods.weightItems, (Double) null);
		items=new String[weightTable.length];
		weightItems=new double[weightTable.length];
		
		for (int i = 0; i < weightTable.length; i++) {

			items[i]=names(weightTable[i])[0]+":"+names(weightTable[i])[1]+":"+names(weightTable[i])[2];
			weightItems[i]=Double.parseDouble(names(weightTable[i])[3]);
			
		}
	}
	
	public static double getItemWeight(String item) {
		double itemWeight=0.0;
		for (int i = 0; i < items.length; i++) {
			if(items[i].equals(item))
				return weightItems[i];
		}
		return 0;
	}
	
	public static ItemStack[] returnLoot (String[] items){
		
		ItemStack[] itemStack = new ItemStack[items.length];
		for (int i = 0; i < itemStack.length; i++) {
			ResourceLocation resourcelocation = new ResourceLocation(names(items[i])[0]+":"+names(items[i])[1]);
			if(Item.REGISTRY.getObject(resourcelocation)!=null) {
				Item item =  Item.REGISTRY.getObject(resourcelocation);
				int amount = Integer.parseInt((names(items[i])[3]));
				int meta = Integer.parseInt(names(items[i])[2]);
				//new ItemStack(itemIn, amount, meta)
				itemStack[i]=new ItemStack(item ,amount,meta);
			}
		
		}
		

		
		return itemStack;
		
	}
	private static String[] names(String name) {
		String[] array = {"","","",""};
		
		int x=0;
		for (int i = 0; i < name.length(); i++) {
			if(name.charAt(i)!=':')
			array[x]+=name.charAt(i);
			else
			x++;
			
		}
	    return array;
	}
}

