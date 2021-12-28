package com.jackdaw.events;

import com.jackdaw.mod.SnowMod;
import com.jackdaw.particle.YellowParticle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientParticleRegistry {

    @SubscribeEvent
    public static void registry(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.YELLOW_PARTICLE.get(), YellowParticle.Factory::new);

    }
}
