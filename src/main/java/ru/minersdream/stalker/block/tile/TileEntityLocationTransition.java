package ru.minersdream.stalker.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3i;

public class TileEntityLocationTransition extends TileEntity {

	private Vec3i position = new Vec3i(0, 70, 0);
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("locationTransitionX", position.getX());
        tagCompound.setInteger("locationTransitionY", position.getY());
        tagCompound.setInteger("locationTransitionZ", position.getZ());
        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        int x = tagCompound.getInteger("locationTransitionX");
        int y = tagCompound.getInteger("locationTransitionY");
        int z = tagCompound.getInteger("locationTransitionZ");
        position = new Vec3i(x, y, z);
        super.readFromNBT(tagCompound);
    }
    
    public Vec3i getLocationTransition() {
    	return position;
    }
    public void setLocationTransition(int locationTransitionX,int locationTransitionY,int locationTransitionZ) {
        position = new Vec3i(locationTransitionX, locationTransitionY, locationTransitionZ);
    }
}