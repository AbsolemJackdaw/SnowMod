package com.jackdaw.network.server;

import com.jackdaw.network.IPacketBase;
import com.jackdaw.network.NetworkHandler;
import com.jackdaw.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SPacketParticleBlock implements IPacketBase {

    private BlockPos pos;

    public SPacketParticleBlock() {
    }

    public SPacketParticleBlock(BlockPos pos) {
        this.pos = pos;
    }

    public SPacketParticleBlock(FriendlyByteBuf buf) {
        decode(buf);
    }


    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            Level level = player.level;
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            BlockState replace = null;
            int layer = 0;

            if (level.getBlockState(pos).hasProperty(SnowLayerBlock.LAYERS))
                layer = level.getBlockState(pos).getValue(SnowLayerBlock.LAYERS);

            if (block.equals(Blocks.SNOW)) {
                replace = BlockRegistry.YELLOW_SNOW.get().defaultBlockState();
            } else if (block.equals(Blocks.SNOW_BLOCK))
                replace = BlockRegistry.YELLOW_SNOW_BLOCK.get().defaultBlockState();
            else if (state.is(BlockTags.ICE))
                replace = Blocks.WATER.defaultBlockState();
            else if (state.is(BlockTags.FIRE))
                replace = Blocks.AIR.defaultBlockState();
            else if (state.is(BlockTags.CAMPFIRES) && block instanceof CampfireBlock campfireBlock) {
                CampfireBlock.dowse(null, level, pos, state);
                replace = block.defaultBlockState().setValue(CampfireBlock.LIT, Boolean.FALSE);
            }

            if (replace != null) {

                if (layer > 0) { // value is bigger then 0 when the block is a snowlayer. checking for snowlayer is redundant here
                    replace = replace.setValue(SnowLayerBlock.LAYERS, layer);
                }
                level.setBlockAndUpdate(pos, replace);
            }

        });
        context.get().setPacketHandled(true);
    }

    @Override
    public void register(int id) {
        NetworkHandler.NETWORK.registerMessage(id, SPacketParticleBlock.class, SPacketParticleBlock::encode, SPacketParticleBlock::new, SPacketParticleBlock::handle);

    }
}
