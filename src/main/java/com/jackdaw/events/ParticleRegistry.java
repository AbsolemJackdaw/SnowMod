package com.jackdaw.events;

import com.jackdaw.mod.SnowMod;
import com.jackdaw.particle.YellowParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SnowMod.MODID);
    public static final RegistryObject<SimpleParticleType> YELLOW_PARTICLE = PARTICLES.register("yellow_particle", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registry(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(YELLOW_PARTICLE.get(), YellowParticle.Factory::new);

    }
}
