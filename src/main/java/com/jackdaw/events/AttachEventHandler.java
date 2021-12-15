package com.jackdaw.events;

import com.jackdaw.capability.BladderCapability;
import com.jackdaw.mod.SnowMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AttachEventHandler {

    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player)
            event.addCapability(BladderCapability.KEY, new BladderCapability());
    }
}
