package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.item.Item;

public class ItemLightningBall extends Item {

    public ItemLightningBall() {
        super();
        maxStackSize = 1;
        setUnlocalizedName("lightning");
        setTextureName(Reference.MOD_ID + ":" + "electricball");
    }

}