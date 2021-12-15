package com.jackdaw.registry;

import com.jackdaw.mod.SnowMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SnowballItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SnowMod.MODID);
    public static final RegistryObject<Item> SNOW_LAYER_ITEM = ITEMS.register("yellow_snow", () ->
            new BlockItem(BlockRegistry.YELLOW_SNOW.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<Item> SNOW_BLOCK_ITEM = ITEMS.register("yellow_snowblock", () ->
            new BlockItem(BlockRegistry.YELLOW_SNOW_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    public static final RegistryObject<Item> YELLOW_SNOWBALL = ITEMS.register("yellow_snowball", () ->
            new SnowballItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}