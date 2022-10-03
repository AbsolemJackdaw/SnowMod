package com.jackdaw.events;

import com.jackdaw.mod.SnowMod;
import com.jackdaw.particle.YellowParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientParticleRegistry {

    @SubscribeEvent
    public static void registry(RegisterParticleProvidersEvent event) {
        event.register(ParticleRegistry.YELLOW_PARTICLE.get(), YellowParticle.Factory::new);

    }
}
