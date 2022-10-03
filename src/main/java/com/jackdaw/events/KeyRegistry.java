package com.jackdaw.events;

import com.jackdaw.mod.SnowMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyRegistry {

    public static KeyMapping peekey;
    
    @SubscribeEvent
    public static void registerKeyBinding(RegisterKeyMappingsEvent e) {
        peekey = new KeyMapping("Pee key", GLFW.GLFW_KEY_P, "Pee");
        e.register(peekey);
    }
}
