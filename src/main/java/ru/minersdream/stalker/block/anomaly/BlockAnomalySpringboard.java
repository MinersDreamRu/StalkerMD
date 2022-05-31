package ru.minersdream.stalker.block.anomaly;

import net.minecraft.block.material.*;
import net.minecraft.block.properties.*;
import ru.minersdream.stalker.interfaces.IHasModel;
import ru.minersdream.stalker.main.*;
import ru.minersdream.stalker.other.AnomalyDamager;

import javax.annotation.*;

import org.apache.logging.log4j.core.config.Scheduled;

import ibxm.Player;
import net.minecraft.world.*;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces.Entrance;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import net.minecraft.client.audio.Sound;

public class BlockAnomalySpringboard extends Block implements IHasModel {
	public static final PropertyInteger ANOMALYSTAGE = PropertyInteger.create("anomalystage", 0, 15);
	protected static final AxisAlignedBB DEFAULTAABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0, 1.0);
	final int damage, timeregen, stagespeed, stagespeedfulldischarged;

	public BlockAnomalySpringboard(String name, int lighting, int damage, int timeregen, int stagespeed, int stagespeedfulldischarged) {
		super(Material.GLASS);
		this.damage = damage;
		this.timeregen = timeregen;
		this.stagespeed = stagespeed;
		this.stagespeedfulldischarged = stagespeedfulldischarged;
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(100000.0f);
		setResistance(100000.0f);
		setSoundType(SoundType.STONE);
		setLightLevel(lighting);

		setHarvestLevel("none", 10000);
		setCreativeTab(STALKERMain.AnomaliesTab);
		InitBlocks.BLOCKS.add((Block) this);
		InitItems.ITEMS.add((Item) new ItemBlock((Block) this).setRegistryName(getRegistryName()));

		setDefaultState(blockState.getBaseState().withProperty((IProperty) this.ANOMALYSTAGE, 0));

	}
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		
		if (stateIn.getValue(this.ANOMALYSTAGE) == 0)
			if (rand.nextInt(100) < 20)
				worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (double) pos.getX() + 0.5,
						(double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);

		
        /*List entitiesAround = worldIn.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(10, 10, 10).expand(-10, -10, -10));
		for (Object entityliving : entitiesAround) {
			if (entityliving instanceof EntityLivingBase) {
				EntityLivingBase entitylivingbase= (EntityLivingBase) entityliving;
				entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 10 * 20, 1));
				entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 15 * 20, 1));
				entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 4 * 20, 1));
				entitylivingbase.setFire(5);
			}
		}*/
	}
	

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (state.getValue(this.ANOMALYSTAGE) == 0) {
			worldIn.setBlockState(pos, state.withProperty(ANOMALYSTAGE, 1), 1);
			worldIn.scheduleUpdate(pos, this, 1);
			if (!(entityIn instanceof EntityLivingBase)) {
				entityIn.setDead();

			} else {
				EntityLivingBase entitylivingbase = (EntityLivingBase) entityIn;
				if (entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					AnomalyDamager.DamagePlayer(player, this.damage, this.getUnlocalizedName());
					player.motionY+=1;
				
				}
			}
			worldIn.playSound((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(),
						InitSounds.ANOMALY_ACTIVATED_SPRINGBOARD, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
			for (int i = 0; i < 25; i++)
				worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (double) pos.getX() + 0.5, (double) pos.getY() + 1, (double) pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
		}
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn,
			final BlockPos pos) {
		return this.NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(final IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(final World worldIn, final BlockPos pos, final EnumFacing side) {
		return canPlaceBlock(worldIn, pos, side);
	}

	@Override
	public boolean canPlaceBlockAt(final World worldIn, final BlockPos pos) {
		for (final EnumFacing enumfacing : EnumFacing.values()) {
			if (canPlaceBlock(worldIn, pos, enumfacing)) {
				return true;
			}
		}
		return false;
	}

	protected static boolean canPlaceBlock(final World worldIn, final BlockPos pos, final EnumFacing direction) {
		final BlockPos blockpos = pos.offset(direction.getOpposite());
		final IBlockState iblockstate = worldIn.getBlockState(blockpos);
		final boolean flag = iblockstate.getBlockFaceShape((IBlockAccess) worldIn, blockpos,
				direction) == BlockFaceShape.SOLID;
		final Block block = iblockstate.getBlock();
		if (direction == EnumFacing.UP) {
			return iblockstate.isTopSolid() || (!isExceptionBlockForAttaching(block) && flag);
		}
		return !isExceptBlockForAttachWithPiston(block) && flag;
	}

	@Override
	public IBlockState getStateForPlacement(final World worldIn, final BlockPos pos, final EnumFacing facing,
			final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer) {
		return this.getDefaultState().withProperty((IProperty) this.ANOMALYSTAGE, 0);
	}

	private boolean checkForDrop(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (this.canPlaceBlockAt(worldIn, pos)) {
			return true;
		}
		this.dropBlockAsItem(worldIn, pos, state, 0);
		worldIn.setBlockToAir(pos);
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return this.DEFAULTAABB;
	}

	@Override
	public void randomTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random random) {
	
	}

	@Override
	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		int anomalyStage= state.getValue(this.ANOMALYSTAGE);
        
		if (!worldIn.isRemote) {
			int currentStage = state.getValue(this.ANOMALYSTAGE);
			if (currentStage == 0)
				return;
			if (currentStage + 2 <= timeregen) {
				worldIn.setBlockState(pos, state.withProperty(ANOMALYSTAGE, currentStage + 1));
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
				worldIn.scheduleUpdate(pos, this, stagespeed);
			} else if (currentStage + 1 <= timeregen) {
				worldIn.setBlockState(pos, state.withProperty(ANOMALYSTAGE, currentStage + 1));
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
				worldIn.scheduleUpdate(pos, this, stagespeedfulldischarged);
			} else {

				worldIn.setBlockState(pos, state.withProperty(ANOMALYSTAGE, 0));
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
			}
			// worldIn.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed,
			// zSpeed, parameters);
		}
		
		double range = 0.5+0.25*state.getValue(this.ANOMALYSTAGE);
        AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range);
        List entities = worldIn.getEntitiesWithinAABB(Entity.class, bb);
        for (Object entityObject : entities) {
            if (entityObject instanceof Entity) {
                Entity entity = (Entity) entityObject;
                if (entity instanceof Entity) {
                	double EntityPosX=entity.posX;
                	double EntityPosY=entity.posY;
                	double EntityPosZ=entity.posZ;
                	double moveX=0;
                	double moveY=0;
                	double moveZ=0;
                	if(pos.getX()<EntityPosX){
                		moveX=-0.1;
                	} else if(pos.getX()>EntityPosX){
                		moveX=0.1;
                	}

                	if(pos.getY()<EntityPosY){
                		moveY=-0.1;
                	} else if(pos.getY()>EntityPosY){
                		moveY=0.1;
                	}

                	if(pos.getZ()<EntityPosZ){
                		moveZ=-0.1;
                	} else if(pos.getZ()>EntityPosZ){
                		moveZ=0.1;
                	}

                	entity.motionX+=moveX;
                	entity.motionY+=moveY;
                	entity.motionZ+=moveZ;
                }
            }
        }

	}

	private void checkPressed(final IBlockState state, final World worldIn, final BlockPos pos) {
		final List<? extends Entity> list = (List<? extends Entity>) worldIn.getEntitiesWithinAABB(
				(Class) EntityArrow.class, state.getBoundingBox((IBlockAccess) worldIn, pos).offset(pos));
		final boolean flag = !list.isEmpty();
		final boolean flag2 = (boolean) state.getValue((IProperty) this.ANOMALYSTAGE);
		if (flag && !flag2) {
			worldIn.setBlockState(pos, state.withProperty((IProperty) this.ANOMALYSTAGE, (Comparable) true));
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
		}
		if (!flag && flag2) {
			worldIn.setBlockState(pos, state.withProperty((IProperty) this.ANOMALYSTAGE, (Comparable) false));
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
		}
		if (flag) {
			worldIn.scheduleUpdate(new BlockPos((Vec3i) pos), (Block) this, this.tickRate(worldIn));
		}
	}

	private void notifyNeighbors(final World worldIn, final BlockPos pos, final EnumFacing facing) {
		worldIn.notifyNeighborsOfStateChange(pos, (Block) this, false);
		worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), (Block) this, false);
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {

		return this.getDefaultState().withProperty((IProperty) this.ANOMALYSTAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ANOMALYSTAGE).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, new IProperty[] { (IProperty) this.ANOMALYSTAGE });
	}

	@Override
	public BlockFaceShape getBlockFaceShape(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos,
			final EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public void registerModels() {
		STALKERMain.proxy.registerItemRenderer(Item.getItemFromBlock((Block) this), 0, "inventory");
	}
}
