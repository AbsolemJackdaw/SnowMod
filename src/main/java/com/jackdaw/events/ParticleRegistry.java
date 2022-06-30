package com.jackdaw.events;

import com.jackdaw.mod.SnowMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SnowMod.MODID);
    public static final RegistryObject<SimpleParticleType> YELLOW_PARTICLE = PARTICLES.register("yellow_particle", () -> new SimpleParticleType(true));

}
