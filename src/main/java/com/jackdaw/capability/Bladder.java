package com.jackdaw.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public class Bladder {

    public static final int MAX = 1000;
    private int capacity = 0;

    public static LazyOptional<Bladder> get(Player player) {

        return player.getCapability(BladderCapability.CAPABILITY, null);
    }

    public Tag writeData() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("cap", capacity);
        return tag;
    }

    public void readData(Tag nbt) {

        CompoundTag tag = ((CompoundTag) nbt);
        this.capacity = tag.getInt("cap");
    }

    public void fill(int ammount) {
        capacity += ammount;
        if (capacity > MAX)
            capacity = MAX;
    }

    public void empty(int amount) {
        capacity -= amount;
        if (capacity < 0)
            capacity = 0;
    }

    public int getCapacity() {
        return capacity;
    }
}
