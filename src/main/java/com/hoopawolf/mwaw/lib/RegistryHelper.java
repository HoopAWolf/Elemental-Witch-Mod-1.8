package com.hoopawolf.mwaw.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHelper {

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, Reference.MOD_ID + "_"
                + block.getUnlocalizedName().substring(5));
    }

    public static void registerItem(Item item) {
        GameRegistry.registerItem(item, Reference.MOD_ID + "_"
                + item.getUnlocalizedName().substring(5));
    }
}