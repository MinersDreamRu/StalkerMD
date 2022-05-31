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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
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
import ru.minersdream.stalker.block.tile.TileEntityStash;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;
import ru.minersdream.stalker.other.OtherMethods;
public class StashBlock extends BlockTileEntity<TileEntityStash> {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public StashBlock(String name, Material material, float hardness, float resistanse, SoundType soundType) {
    	
        super(name, material, hardness, resistanse, soundType);
        
		this.setCreativeTab(STALKERMain.DecoTab);
        this.setHarvestLevel("pickaxe", 3);
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
    		List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {

    	addCollisionBoxToList(pos, entityBox, collidingBoxes, getAxisAlignedBB((EnumFacing)state.getValue(FACING)));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
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
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
    		ItemStack stack) {
    	if (!world.isRemote) {
        	TileEntityStash tileEntity = getTileEntity(world, pos);
        	
    	}
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
        	TileEntityStash tileEntity = getTileEntity(world, pos);
        	
            if((tileEntity.getLastLootTime()+STALKERMain.fillingTime*20<world.getTotalWorldTime())||player.isCreative()) {
            	TextComponentTranslation descTranslated = new TextComponentTranslation("stash.message.isnotempty");
                player.sendStatusMessage(descTranslated, true);
                
                Random rand = new Random();
                
                for (int i = 0; i <= STALKERMain.minItemsCountStash+rand.nextInt(STALKERMain.maxItemsCountStash-STALKERMain.minItemsCountStash); i++) {
                    int x=pos.getX();
                	int y= pos.getY();
                	int z = pos.getZ();
                	boolean debug=true;
                	System.out.println((EnumFacing)blockState.getValue(FACING));
                	switch ((EnumFacing)blockState.getValue(FACING)) {
                	case NORTH:

                        z-=1;
                        break;
                    case SOUTH:
                    	z+=1;
                        break;
                    case WEST:
                    	x-=1;
                        break;
                    case EAST:
                    	x+=1;
                        break;
					default:
						break;
					}
                	
                	//System.out.println(x+" "+y+" "+z+" ");
                	InventoryHelper.spawnItemStack(world, x,y, z, OtherMethods.returnLoot(STALKERMain.itemStash)[rand.nextInt(OtherMethods.returnLoot(STALKERMain.itemStash).length)]);
				}
            	tileEntity.setLastLootTime(world.getTotalWorldTime());
            }
            else {
            	TextComponentTranslation descTranslated = new TextComponentTranslation("stash.message.isempty");
                player.sendStatusMessage(descTranslated, true);
            }
        }
        
        return true;
    }

    @Override
    public Class<TileEntityStash> getTileEntityClass() {

        return TileEntityStash.class;
    }

    @Override
    public TileEntityStash createTileEntity(World world, IBlockState blockState) {

        return new TileEntityStash();
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
                return new AxisAlignedBB(-0.05D, 0.0D, 0.185D, 1.05D, 0.49D, 0.81D);
            case SOUTH:
                return new AxisAlignedBB(-0.05D, 0.0D, 0.185D, 1.05D, 0.49D, 0.81D);
            case WEST:
                return new AxisAlignedBB(0.185D, 0.0D, -0.05D, 0.81D, 0.49D, 1.05D);
            case EAST:
            default:
                return new AxisAlignedBB(0.185D, 0.0D, -0.05D, 0.81D, 0.49D, 1.05D);
        }
    	
		//return new AxisAlignedBB(-0.05D, 0.0D, 0.185D, 1.05D, 0.49D, 0.81D);
    	
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