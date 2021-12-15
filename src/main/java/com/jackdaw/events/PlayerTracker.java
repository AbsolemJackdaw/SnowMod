package com.jackdaw.events;

import com.jackdaw.capability.Bladder;
import com.jackdaw.mod.SnowMod;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTracker {

    @SubscribeEvent
    public static void onUpdate(TickEvent.PlayerTickEvent event) {

        Bladder.get(event.player).ifPresent(data -> {
            if (event.player.level.isClientSide()) {

                if (event.player.isUsingItem() && event.player.getUseItem().getUseAnimation().equals(UseAnim.DRINK)) {
                    data.fill(300);
                }

                if (KeyRegistry.peekey.isDown() && data.getCapacity() > 0) {
                    data.empty(2);
                    double x = event.player.xo;
                    double y = event.player.yo + 0.7d;
                    double z = event.player.zo;

                    double deltaX = Math.toRadians(event.player.yHeadRot + 90.0d);

                    double pwr = Math.min(2, -1 * (event.player.getXRot() - 90.0d) / 45.0d);

                    System.out.println(pwr);
                    event.player.level.addParticle(ParticleRegistry.YELLOW_PARTICLE.get(), x, y, z, Math.cos(deltaX) * pwr, 0.0d, Math.sin(deltaX) * pwr);
                }
            }
        });
    }
}
