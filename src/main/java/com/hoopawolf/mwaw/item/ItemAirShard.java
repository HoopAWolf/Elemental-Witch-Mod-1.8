package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.structure.StructureAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAirShard extends Item {

    public ItemAirShard() {
        super();
        maxStackSize = 16;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("airshard");
        setTextureName(Reference.MOD_ID + ":" + "airshard");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (player.capabilities.isCreativeMode) {

            StructureAir.getInstance().generate(world, world.rand,
                    posX, posY, posZ);
            return true;

        }

        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.EPIC;
    }
}


