package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMWAWEgg extends Item {

    public ItemMWAWEgg() {
        super();
        maxStackSize = 1;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("MWAWegg");
        setTextureName(Reference.MOD_ID + ":" + "MWAWegg");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemstack,
                               final EntityPlayer player, final List datalist, final boolean bool) {
        datalist.add(EnumChatFormatting.ITALIC
                + "Right-Click on a witch when her health is below 13 ");
        datalist.add(EnumChatFormatting.ITALIC
                + "to capture her power in the egg!");


    }

}