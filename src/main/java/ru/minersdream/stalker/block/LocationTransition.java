package ru.minersdream.stalker.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;
import javax.print.attribute.standard.MediaSize.Other;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandTP;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.minersdream.stalker.block.base.BlockTileEntity;
import ru.minersdream.stalker.block.tile.TileEntityLocationTransition;
import ru.minersdream.stalker.block.tile.TileEntityStash;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;
import ru.minersdream.stalker.other.OtherMethods;
public class LocationTransition extends BlockTileEntity<TileEntityLocationTransition> {

    public LocationTransition(String name, Material material, float hardness, float resistanse, SoundType soundType) {
    	
        super(name, material, hardness, resistanse, soundType);
        
		this.setCreativeTab(STALKERMain.DecoTab);
        this.setHarvestLevel("pickaxe", 3);
    }
    
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }
    
    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }


    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
    	return false;
    }
    
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    
    @Override
    public Class<TileEntityLocationTransition> getTileEntityClass() {

        return TileEntityLocationTransition.class;
    }

    @Override
    public TileEntityLocationTransition createTileEntity(World world, IBlockState blockState) {
    	
        return new TileEntityLocationTransition();
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	String name = stack.getDisplayName();
    	String[] position=name.split(" ");
    	TileEntityLocationTransition tileEntity = getTileEntity(worldIn, pos);
    	Boolean blockBreak=false;
    	if (position.length != 3) {
    		placer.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
    		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
    		return;
    	}
    	try {
    		int x = Integer.parseInt(position[0]);
    		int y = Integer.parseInt(position[1]);
    		int z = Integer.parseInt(position[2]);
    		tileEntity.setLocationTransition(x, y, z);
    	} catch(NumberFormatException e) {
    		placer.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
    		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());

    	}

    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entityIn) {
    	if(!world.isRemote&&entityIn instanceof EntityPlayer) {
    		EntityPlayer player=(EntityPlayer) entityIn;
        	TileEntityLocationTransition tileEntity = getTileEntity(world, pos);
        	Vec3i position=tileEntity.getLocationTransition();
    		player.setPositionAndUpdate(position.getX()+0.5, position.getY()+0.5, position.getZ()+0.5);
    	}
    }
    
}