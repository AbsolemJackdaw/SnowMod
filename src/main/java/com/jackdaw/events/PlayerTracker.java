package com.jackdaw.events;

import com.jackdaw.capability.Bladder;
import com.jackdaw.mod.SnowMod;
import com.jackdaw.network.NetworkHandler;
import com.jackdaw.network.client.HandleClientPacket;
import com.jackdaw.network.server.SPacketSpawnParticle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTracker {

    @SubscribeEvent
    public static void usedEvent(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntityLiving() instanceof Player player) {
            Bladder.get(player).ifPresent(data -> {
                if (player.level.isClientSide()) {

                    if (event.getItem().getUseAnimation().equals(UseAnim.DRINK)) {
                        data.fill(event.getItem().getUseDuration() * 3);
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onUpdate(TickEvent.PlayerTickEvent event) {

        Bladder.get(event.player).ifPresent(data -> {
            if (event.player.level.isClientSide()) {

                if (KeyRegistry.peekey.isDown() && data.getCapacity() > 0) {
                    data.empty(2);

                    HandleClientPacket.doClientParticle(event.player);

                    //send around
                    NetworkHandler.NETWORK.sendToServer(new SPacketSpawnParticle());
                }
            }
        });
    }
}
