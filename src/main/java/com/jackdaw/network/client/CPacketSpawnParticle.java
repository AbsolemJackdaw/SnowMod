package com.jackdaw.network.client;

import com.jackdaw.network.IPacketBase;
import com.jackdaw.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CPacketSpawnParticle implements IPacketBase {

    private UUID sender;

    public CPacketSpawnParticle() {
    }

    public CPacketSpawnParticle(UUID sender) {
        this.sender = sender;
    }

    public CPacketSpawnParticle(FriendlyByteBuf buf) {
        decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(sender);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        sender = buf.readUUID();
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> context) {
        HandleClientPacket.particle(context, sender);
    }

    @Override
    public void register(int id) {
        NetworkHandler.NETWORK.registerMessage(id, CPacketSpawnParticle.class, CPacketSpawnParticle::encode, CPacketSpawnParticle::new, CPacketSpawnParticle::handle);
    }
}
