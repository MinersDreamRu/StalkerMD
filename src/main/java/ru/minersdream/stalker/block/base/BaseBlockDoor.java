package ru.minersdream.stalker.block.base;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ru.minersdream.stalker.block.itemblock.ItemBlockDoor;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;

public class BaseBlockDoor extends BlockDoor implements IHasModel
{
	public BaseBlockDoor(String name, Material materialIn, CreativeTabs tab) 
	{
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		this.setHardness(500000.0F);
		this.setResistance(500000.0F);
		this.setHarvestLevel("admin", 10000);
		
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlockDoor(this).setRegistryName(name));
	}
	
	//@Override
	//public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	//	
	//	return false;
	//}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
	{
		return new ItemStack(this);
	}
	
	@Override
	public void registerModels() 
	{
		STALKERMain.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
}