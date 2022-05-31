package ru.minersdream.stalker.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.minersdream.stalker.main.InitBlocks;
import ru.minersdream.stalker.main.InitItems;
import ru.minersdream.stalker.main.STALKERMain;

public class BlockFenceObj extends BlockDirectional{ 
	
    //public static final PropertyDirection FACING = PropertyDirection.create("facing");

    private boolean isAngle;
    private boolean isTechBlock;
    public BlockFenceObj(String name, boolean isAngle, boolean isTechBlock) {
		super(Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		this.isAngle=isAngle;
    	this.isTechBlock=isTechBlock;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		if(!isTechBlock)
		this.setCreativeTab(STALKERMain.DecoTab);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));	
		
    }
    
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
    	if(isTechBlock)
    		return EnumBlockRenderType.INVISIBLE;
    	else
    		return EnumBlockRenderType.MODEL;
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
    	// TODO Auto-generated method stub
    	return false;
    }
    
    
    
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
    	
    	if(isAngle) {
        	BlockPos newPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
        	worldIn.setBlockState(newPos, InitBlocks.FENCEANGLENULL.getDefaultState().withProperty(FACING, state.getValue(FACING)));
    	} else {
        	BlockPos newPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
        	worldIn.setBlockState(newPos, InitBlocks.FENCENULL.getDefaultState().withProperty(FACING, state.getValue(FACING)));
    	}
    }
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
    		List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
    	
    	if(this.isAngle) {
    		switch ((EnumFacing)state.getValue(FACING))
            {
            	case NORTH:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 0.1D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 0.62D, 1.0D));
            		}
                    break;
            	case SOUTH:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 0.62D, 1.0D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 0.62D, 1.0D));
            		}

                    break;
            	case WEST:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 0.62D, 1.0D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 0.62D, 1.0D));
            		}
                    break;
            	case EAST:
            	default:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 0.1D));
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 0.62D, 1.0D));
            		}
                    break;
                
            }
    		
            //addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.62D, 0.1D));
            //addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.62D, 1.0D));
    	}
    	else {
    		//addCollisionBoxToList(pos, entityBox, collidingBoxes, getAxisAlignedBB((EnumFacing)state.getValue(FACING)));
    		switch ((EnumFacing)state.getValue(FACING))
            {
            	case NORTH:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 0.1D));
            		}
                    break;
            	case SOUTH:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 0.62D, 1.0D));
            		}

                    break;
            	case WEST:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 0.62D, 1.0D));
            		}
                    break;
            	case EAST:
            	default:
            		if(!isTechBlock) {
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 1.0D, 1.0D));
            		}
            		else
            		{
                        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1.0D, 0.0D, 0.0D, 0.9D, 0.62D, 1.0D));
            		}
                    break;
                
            }
    	}
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {	
    	return getAxisAlignedBB((EnumFacing)state.getValue(FACING));
    }
    
    private AxisAlignedBB getAxisAlignedBB(EnumFacing enumFacing) {
    	if(!isAngle)
            switch (enumFacing)
            {
            	case NORTH:
            		if(!isTechBlock) 
                		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
            		else
                		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 0.1D);
            	case SOUTH:
            		if(!isTechBlock) 
                		return new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
            		else
                		return new AxisAlignedBB(0.0D, 0.0D, 0.9D, 1.0D, 0.62D, 1.0D);
            	case WEST:
            		if(!isTechBlock) 
                		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
            		else
                		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1D, 0.62D, 1.0D);
            	case EAST:
            	default:
            		if(!isTechBlock) 
                		return new AxisAlignedBB(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            		else
                		return new AxisAlignedBB(0.9D, 0.0D, 0.0D, 1.0D, 0.62D, 1.0D);
                
            }
    	else
        switch (enumFacing)
        {
        	case NORTH:
        		if(!isTechBlock) 
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        		else
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 1.0D);
        	case SOUTH:
        		if(!isTechBlock) 
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        		else
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 1.0D);
        	case WEST:
        		if(!isTechBlock) 
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        		else
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 1.0D);
        	case EAST:
        	default:
        		if(!isTechBlock) 
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        		else
            		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.62D, 1.0D);
            
        }
    	
		//return new AxisAlignedBB(-0.05D, 0.0D, 0.185D, 1.05D, 0.49D, 0.81D);
    	
    }
    
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing;

        switch (meta & 7)
        {
            case 0:
                enumfacing = EnumFacing.DOWN;
                break;
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
                enumfacing = EnumFacing.NORTH;
                break;
            case 5:
            default:
                enumfacing = EnumFacing.UP;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    	BlockPos newPos;
    	if(this.isTechBlock)
    		newPos = new BlockPos(pos.getX(), pos.getY()-1, pos.getZ());
    	else
    		newPos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
    	worldIn.setBlockState(newPos, Blocks.AIR.getDefaultState());
    }
    
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
}
    