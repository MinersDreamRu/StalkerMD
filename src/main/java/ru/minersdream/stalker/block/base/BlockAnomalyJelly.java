package ru.minersdream.stalker.block.base;

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

public class BlockAnomalyJelly extends Block implements IHasModel {
	public static final PropertyInteger ANOMALYSTAGE = PropertyInteger.create("anomalystage", 0, 15);
	protected static final AxisAlignedBB DEFAULTAABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0, 1.0);
	final int damage, timeregen, stagespeed, stagespeedfulldischarged;
	String typeOfAnomaly;

	public BlockAnomalyJelly(String name, int lighting, float hardness, float resistanse, SoundType soundType, int damage,
			int timeregen, int stagespeed, int stagespeedfulldischarged, String typeOfAnomaly) {
		super(Material.GLASS);
		this.damage = damage;
		this.timeregen = timeregen;
		this.stagespeed = stagespeed;
		this.stagespeedfulldischarged = stagespeedfulldischarged;
		this.typeOfAnomaly = typeOfAnomaly;
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(hardness);
		setResistance(resistanse);
		setSoundType(soundType);
		setLightLevel(lighting);
		
		setHarvestLevel("none", 10000);
		setCreativeTab(STALKERMain.AnomaliesTab);
		InitBlocks.BLOCKS.add((Block) this);
		InitItems.ITEMS.add((Item) new ItemBlock((Block) this).setRegistryName(getRegistryName()));

		setDefaultState(blockState.getBaseState().withProperty((IProperty) BlockAnomalyJelly.ANOMALYSTAGE, 0));

	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (state.getValue(BlockAnomalyJelly.ANOMALYSTAGE) == 0) {
			worldIn.setBlockState(pos, state.withProperty(ANOMALYSTAGE, 1), 1);
			worldIn.scheduleUpdate(pos, this, 1);
			if (!(entityIn instanceof EntityLivingBase)) {
				entityIn.setDead();

			} else {
				EntityLivingBase entitylivingbase = (EntityLivingBase) entityIn;
				if (entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 80, 3));
					AnomalyDamager.DamagePlayer(player, this.damage, this.getUnlocalizedName());
				}
			}

			
			worldIn.playSound((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(),
			InitSounds.ANOMALY_ACTIVATED_JELLY, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
		}
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(final IBlockState blockState, final IBlockAccess worldIn,
			final BlockPos pos) {
		return BlockAnomalyJelly.NULL_AABB;
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
		return this.getDefaultState().withProperty((IProperty) BlockAnomalyJelly.ANOMALYSTAGE, 0);
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
		return BlockAnomalyJelly.DEFAULTAABB;
	}

	@Override
	public void randomTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random random) {
	}

	@Override
	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		if (!worldIn.isRemote) {
			int currentStage = state.getValue(BlockAnomalyJelly.ANOMALYSTAGE);
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
	}

	private void checkPressed(final IBlockState state, final World worldIn, final BlockPos pos) {
		final List<? extends Entity> list = (List<? extends Entity>) worldIn.getEntitiesWithinAABB(
				(Class) EntityArrow.class, state.getBoundingBox((IBlockAccess) worldIn, pos).offset(pos));
		final boolean flag = !list.isEmpty();
		final boolean flag2 = (boolean) state.getValue((IProperty) BlockAnomalyJelly.ANOMALYSTAGE);
		if (flag && !flag2) {
			worldIn.setBlockState(pos, state.withProperty((IProperty) BlockAnomalyJelly.ANOMALYSTAGE, (Comparable) true));
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
		}
		if (!flag && flag2) {
			worldIn.setBlockState(pos, state.withProperty((IProperty) BlockAnomalyJelly.ANOMALYSTAGE, (Comparable) false));
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

		return this.getDefaultState().withProperty((IProperty) BlockAnomalyJelly.ANOMALYSTAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ANOMALYSTAGE).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, new IProperty[] { (IProperty) BlockAnomalyJelly.ANOMALYSTAGE });
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
