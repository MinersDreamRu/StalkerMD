package ru.minersdream.stalker.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarrel extends BaseObjDirectionalBlock{ 
	
    //public static final PropertyDirection FACING = BlockHorizontal.FACING;


    public BlockBarrel(String name) {
		super(name);
		
    }
    
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {	
    	return getAxisAlignedBB((EnumFacing)state.getValue(FACING));
    }
    
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
    	return getAxisAlignedBB((EnumFacing)state.getValue(FACING));
    }
    
    private AxisAlignedBB getAxisAlignedBB(EnumFacing enumFacing) {
        switch (enumFacing)
        {
        	case NORTH:
        		return new AxisAlignedBB(0.13D, 0.0D, 0.13D, 0.87D, 1.0D, 0.87D);
        	case SOUTH:
        		return new AxisAlignedBB(0.13D, 0.0D, 0.13D, 0.87D, 1.0D, 0.87D);
        	case WEST:
        		return new AxisAlignedBB(0.13D, 0.0D, 0.13D, 0.87D, 1.0D, 0.87D);
        	case EAST:
        	default:
        		return new AxisAlignedBB(0.13D, 0.0D, 0.13D, 0.87D, 1.0D, 0.87D);
        }
    	
		//return new AxisAlignedBB(-0.05D, 0.0D, 0.185D, 1.05D, 0.49D, 0.81D);
    	
    }
    
    /*
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
    
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    */
    
}
