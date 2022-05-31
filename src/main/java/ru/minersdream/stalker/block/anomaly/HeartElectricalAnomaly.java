package ru.minersdream.stalker.block.anomaly;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.minersdream.stalker.block.base.BlockTileEntity;
import ru.minersdream.stalker.block.tile.TileEntityElectricalAnomaly;
import ru.minersdream.stalker.main.STALKERMain;
import ru.minersdream.stalker.other.OtherMethods;

public class HeartElectricalAnomaly  extends BlockTileEntity<TileEntityElectricalAnomaly> {

    public HeartElectricalAnomaly(String name, Material material, float hardness, float resistanse, SoundType soundType) {
    	
        super(name, material, hardness, resistanse, soundType);
        
		this.setCreativeTab(STALKERMain.AnomaliesTab);
        this.setHarvestLevel("pickaxe", 3);
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    } 
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {	
     	 return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.01D, 1.0D);
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
    		TileEntityElectricalAnomaly tileEntity = getTileEntity(world, pos);
        	
    	}
    }

    @Override
    public Class<TileEntityElectricalAnomaly> getTileEntityClass() {

        return TileEntityElectricalAnomaly.class;
    }

    @Override
    public TileEntityElectricalAnomaly createTileEntity(World world, IBlockState blockState) {

        return new TileEntityElectricalAnomaly();
    }
   
    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
    	if(!(entity instanceof EntityPlayer))
    		return;
    	
    	EntityPlayer player = (EntityPlayer) entity;
        if (!world.isRemote) {
        	TileEntityElectricalAnomaly tileEntity = getTileEntity(world, pos);
        	if((tileEntity.getLastLootTime()+STALKERMain.fillingTime*20<world.getTotalWorldTime())) {
                Random rand = new Random();

                int artefactCount=1;
                String temp=player.getHeldItemMainhand().getItem().getRegistryName().toString();
                switch (temp) {
         		case "stalker:detectorresponse":
         			if(rand.nextInt(100)<=5)
         				artefactCount++;
         			break;
         		case "stalker:detectorbear":
         			if(rand.nextInt(100)<=15)
         				artefactCount++;
         			break;
         		case "stalker:detectorveles":
         			if(rand.nextInt(100)<=35)
         				artefactCount++;
         			break;
         		case "stalker:prototypedetectorsvarog":
         			if(rand.nextInt(100)<=70)
         				artefactCount++;
         			if(rand.nextInt(100)<=10)
         				artefactCount++;
         			break;

         		default:
         			return;
         		}
            	
            	TextComponentTranslation descTranslated = new TextComponentTranslation("anomaly.message.isnotempty");
                player.sendStatusMessage(descTranslated, true);
                
                
                
                for (int i = 0; i <= artefactCount; i++) {
                    int x=pos.getX();
                	int y= pos.getY();
                	int z = pos.getZ();             
                	InventoryHelper.spawnItemStack(world, x,y, z, OtherMethods.returnLoot(STALKERMain.itemsElectricalAnomaly)[rand.nextInt(OtherMethods.returnLoot(STALKERMain.itemsElectricalAnomaly).length)]);
				}
            	tileEntity.setLastLootTime(world.getTotalWorldTime());
            }
            else {
            	TextComponentTranslation descTranslated = new TextComponentTranslation("anomaly.message.isempty");
                player.sendStatusMessage(descTranslated, true);
            }
        }
    }
}
