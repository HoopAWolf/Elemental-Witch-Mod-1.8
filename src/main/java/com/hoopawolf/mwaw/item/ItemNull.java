package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.item.Item;

public class ItemNull extends Item {

    public ItemNull() {
        super();
        maxStackSize = 1;
        setUnlocalizedName("nothing");
        setTextureName(Reference.MOD_ID + ":" + "null");
    }

}