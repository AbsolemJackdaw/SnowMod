package com.jackdaw.events;

import com.jackdaw.capability.Bladder;
import com.jackdaw.mod.SnowMod;
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
                        data.fill(event.getItem().getUseDuration() * 2);
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
                    double x = event.player.xo;
                    double y = event.player.yo + 0.7d;
                    double z = event.player.zo;
                    double deltaX = Math.toRadians(event.player.yHeadRot + 90.0d);
                    double pwr = Math.min(2, -1 * (event.player.getXRot() - 90.0d) / 45.0d);
                    event.player.level.addParticle(ParticleRegistry.YELLOW_PARTICLE.get(), x, y, z, Math.cos(deltaX) * pwr, 0.0d, Math.sin(deltaX) * pwr);
                }
            }
        });
    }
}
