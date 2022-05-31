package ru.minersdream.stalker.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;

public class BlockSatchel extends BlockDirectional implements IHasModel {
    protected static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.75D, 0.2D, 0.8D, 0.25D, 0.0D, 0.2D);
    protected static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.8D, 0.2D, 0.75D, 0.2D, 0.0D, 0.25D);


	public BlockSatchel(String name) {

		super(Material.WOOD);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setHardness(10000.0f);
		this.setResistance(10000.0f);
		this.setHarvestLevel("none", 10000);
		this.setCreativeTab(STALKERMain.DecoTab);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        switch (enumfacing)
        {
            case EAST:
            case WEST:
                return AABB_EW;
            case SOUTH:
            case NORTH:
            default:
                return AABB_NS;
        }
    }
	
	public int getMetaFromState(IBlockState state)
    {
        int i;

        switch ((EnumFacing)state.getValue(FACING))
        {
            case EAST:
                i = 1;
                break;
            case WEST:
                i = 2;
                break;
            case SOUTH:
                i = 3;
                break;
            case NORTH:
            default:
                i = 4;
                break;
        }
        return i;
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;

        switch (meta & 7)
        {
            case 1:
                enumfacing = EnumFacing.EAST;
                break;
            case 2:
                enumfacing = EnumFacing.WEST;
                break;
            case 3:
                enumfacing = EnumFacing.SOUTH;
                break;
            case 4:
            default:
                enumfacing = EnumFacing.NORTH;
                break;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
	
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	  return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    /*protected static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;
        Block block = iblockstate.getBlock();
        return !isExceptBlockForAttachWithPiston(block) && flag;
    }*/
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

	}

}
