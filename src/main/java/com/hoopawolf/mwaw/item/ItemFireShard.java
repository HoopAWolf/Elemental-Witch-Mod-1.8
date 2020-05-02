package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.structure.StructureFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFireShard extends Item {

    public ItemFireShard() {
        super();
        maxStackSize = 16;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("fireshard");
        setTextureName(Reference.MOD_ID + ":" + "fireshard");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        if (player.capabilities.isCreativeMode) {

            StructureFire.getInstance().generate(world, world.rand,
                    posX, posY, posZ);
            return true;

        }

        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.EPIC;
    }

}
