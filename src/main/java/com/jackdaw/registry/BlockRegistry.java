package com.jackdaw.registry;

import com.jackdaw.mod.SnowMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SnowMod.MODID);
    public static final RegistryObject<Block> YELLOW_SNOW = BLOCKS.register("yellow_snow", () -> new SnowLayerBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((x, y, z) -> x.getValue(SnowLayerBlock.LAYERS) >= 8)));
    public static final RegistryObject<Block> YELLOW_SNOW_BLOCK = BLOCKS.register("yellow_snowblock", () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).requiresCorrectToolForDrops().strength(0.2F).sound(SoundType.SNOW)));

}
