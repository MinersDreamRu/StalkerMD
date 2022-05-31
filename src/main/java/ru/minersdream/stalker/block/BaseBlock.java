package ru.minersdream.stalker.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.model.obj.OBJLoader;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;
public class BaseBlock extends Block implements IHasModel{
	Item ds;
	public BaseBlock(String name, Material material, float hardness, float resistanse, SoundType soundType, Item item, String toolClass, int level) {
		
		super(material);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setSoundType(soundType);
		this.setHarvestLevel(toolClass, level);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));	
		}
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
		
	}
}
