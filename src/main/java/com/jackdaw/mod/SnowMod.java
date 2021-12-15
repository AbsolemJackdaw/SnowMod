package com.jackdaw.mod;

import com.jackdaw.events.ParticleRegistry;
import com.jackdaw.network.NetworkHandler;
import com.jackdaw.registry.BlockRegistry;
import com.jackdaw.registry.ItemRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = SnowMod.MODID)
public class SnowMod {
    public static final String MODID = "snowmod";

    public SnowMod() {
        ParticleRegistry.PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        NetworkHandler.register();
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}