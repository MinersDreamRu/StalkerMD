package ru.minersdream.stalker.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.model.obj.OBJLoader;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;
public class SafeZone extends BlockAir implements IHasModel{
	public SafeZone(String name) {
		
	    this.setRegistryName(name);
	    this.setUnlocalizedName(name);
	    this.setHardness(100000);
	    this.setResistance(100000);
	    this.setHarvestLevel("admin", 100000);
	    this.setCreativeTab(STALKERMain.AnomaliesTab);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));	
	}
	
	
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		
		if(!(entityIn instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entityIn;
		if(!player.getEntityData().getBoolean("inSafeZone")) {
        	player.sendStatusMessage(new TextComponentTranslation("stalker.blowout.safety"), true);

		player.getEntityData().setBoolean("inSafeZone",true);
		}
		
		
		
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		
		
	}
	
	
	
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}