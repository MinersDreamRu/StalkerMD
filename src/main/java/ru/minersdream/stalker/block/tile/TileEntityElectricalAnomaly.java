package ru.minersdream.stalker.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityElectricalAnomaly  extends TileEntity {

    private long lastLootTime;
    private ItemStack[] stack = new ItemStack[9];
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {

        //tagCompound.setInteger("count", this.count);
        tagCompound.setLong("lastLootTime", this.lastLootTime);

        return super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {

        //this.count = tagCompound.getInteger("count");
        this.lastLootTime = tagCompound.getInteger("lastLootTime");

        super.readFromNBT(tagCompound);
    }
    
    public long getLastLootTime() {
    	return lastLootTime;
    }
    
    public void setLastLootTime(long lastLootTime) {
        this.lastLootTime=lastLootTime;
        this.markDirty();
    }
    /*
    public int getCount() {

        return this.count;
    }

    public void incrementCount() {

        this.count++;

        this.markDirty();
    }

    public void decrementCount() {

        this.count--;

        this.markDirty();
    }*/
}