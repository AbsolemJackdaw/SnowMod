package com.jackdaw.network;

import com.jackdaw.mod.SnowMod;
import com.jackdaw.network.client.CPacketSpawnParticle;
import com.jackdaw.network.server.SPacketParticleBlock;
import com.jackdaw.network.server.SPacketSpawnParticle;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(new ResourceLocation(SnowMod.MODID, "pee_network"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register() {
        int messageId = 0;
        new SPacketParticleBlock().register(messageId++);
        new SPacketSpawnParticle().register(messageId++);
        new CPacketSpawnParticle().register(messageId++);
    }
}
