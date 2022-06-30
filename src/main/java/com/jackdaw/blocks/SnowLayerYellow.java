package com.jackdaw.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SnowLayerYellow extends SnowLayerBlock {
    public SnowLayerYellow(Properties p_56585_) {
        super(p_56585_);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel server, BlockPos pos, RandomSource rand) {
        super.randomTick(state, server, pos, rand);

        if (rand.nextInt(100) == 0 && server.isRaining()) {
            BlockState replace = Blocks.SNOW.defaultBlockState();
            if (state.hasProperty(SnowLayerBlock.LAYERS))
                replace = replace.setValue(SnowLayerBlock.LAYERS, state.getValue(SnowLayerBlock.LAYERS));
            server.setBlockAndUpdate(pos, replace);
        }

    }
}
