package com.hoopawolf.mwaw.tab;

import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class MWAWTab extends CreativeTabs {
    public MWAWTab(final int par1, final String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return MWAWItemRegistry.halloweenstaff;

    }

    @Override
    public String getTranslatedTabLabel() {
        return "Elemental Witch Mod";
    }

    @SideOnly(Side.CLIENT)
    public String getBackgroundImageName() {
        return "elementalwitchmod.png";
    }

}
