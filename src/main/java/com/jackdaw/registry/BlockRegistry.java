package com.jackdaw.registry;

import com.jackdaw.blocks.SnowLayerYellow;
import com.jackdaw.mod.SnowMod;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnowMod.MODID);
    public static final RegistryObject<Block> YELLOW_SNOW = BLOCKS.register("yellow_snow",
            () -> new SnowLayerYellow(BlockBehaviour.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((x, y, z) -> x.getValue(SnowLayerBlock.LAYERS) >= 8)) {
                @Override
                public void randomTick(BlockState state, ServerLevel server, BlockPos pos, Random rand) {
                    super.randomTick(state, server, pos, rand);
                    replace(rand, server, pos, state, Blocks.SNOW.defaultBlockState());
                }
            });

    public static final RegistryObject<Block> YELLOW_SNOW_BLOCK = BLOCKS.register("yellow_snowblock",
            () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).requiresCorrectToolForDrops().strength(0.2F).sound(SoundType.SNOW)) {
                @Override
                public void randomTick(BlockState state, ServerLevel server, BlockPos pos, Random rand) {
                    super.randomTick(state, server, pos, rand);
                    replace(rand, server, pos, state, Blocks.SNOW_BLOCK.defaultBlockState());
                }
            });

    private static void replace(Random rand, ServerLevel server, BlockPos pos, BlockState currentState, BlockState toState) {
        if (rand.nextInt(100) == 0 && server.isRaining()) {
            if (currentState.hasProperty(SnowLayerBlock.LAYERS))
                toState = toState.setValue(SnowLayerBlock.LAYERS, currentState.getValue(SnowLayerBlock.LAYERS));
            server.setBlockAndUpdate(pos, toState);
        }
    }
}
