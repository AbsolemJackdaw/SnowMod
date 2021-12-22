package com.jackdaw.network.client;

import com.jackdaw.events.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class HandleClientPacket {
    public static void particle(Supplier<NetworkEvent.Context> context, UUID sender) {
        context.get().enqueueWork(() -> {
            AbstractClientPlayer receiver = Minecraft.getInstance().player;
            if (receiver != null) {
                ClientLevel level = receiver.clientLevel;
                Player player = level.getPlayerByUUID(sender);
                if (player != null)
                    doClientParticle(player);
            }
        });
        context.get().setPacketHandled(true);
    }

    public static void doClientParticle(Player player) {
        double x = player.xo;
        double y = player.yo + 0.7d;
        double z = player.zo;
        double deltaX = Math.toRadians(player.yHeadRot + 90.0d);
        double pwr = Math.min(2, -1 * (player.getXRot() - 90.0d) / 45.0d);
        player.level.addParticle(ParticleRegistry.YELLOW_PARTICLE.get(), x, y, z, Math.cos(deltaX) * pwr, 0.0d, Math.sin(deltaX) * pwr);
    }
}
