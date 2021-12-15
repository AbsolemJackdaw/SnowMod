package com.jackdaw.capability;

import com.jackdaw.mod.SnowMod;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class BladderCapability implements ICapabilitySerializable<CompoundTag> {

    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(SnowMod.MODID, "bladder_cap");
    /*
     * This field will contain the forge-allocated Capability class. This instance
     * will be initialized internally by Forge, upon calling register in FMLCommonSetupEvent.
     */
    public static Capability<Bladder> CAPABILITY = CapabilityManager.get(new CapabilityToken<Bladder>() {
    });
    final Bladder data = new Bladder();

    @Override
    public CompoundTag serializeNBT() {

        return (CompoundTag) data.writeData();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        data.readData(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

        if (cap == BladderCapability.CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(this::getImpl);

        return LazyOptional.empty();
    }

    private Bladder getImpl() {

        return data;
    }
}
