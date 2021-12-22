package com.jackdaw.network.server;

import com.jackdaw.network.IPacketBase;
import com.jackdaw.network.NetworkHandler;
import com.jackdaw.network.client.CPacketSpawnParticle;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class SPacketSpawnParticle implements IPacketBase {

    public SPacketSpawnParticle() {
    }

    public SPacketSpawnParticle(FriendlyByteBuf buf) {
        decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {

    }

    @Override
    public void decode(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> context) {

        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                NetworkHandler.NETWORK.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new CPacketSpawnParticle(player.getUUID()));
            }
        });
        context.get().setPacketHandled(true);
    }

    @Override
    public void register(int id) {
        NetworkHandler.NETWORK.registerMessage(id, SPacketSpawnParticle.class, SPacketSpawnParticle::encode, SPacketSpawnParticle::new, SPacketSpawnParticle::handle);
    }
}
