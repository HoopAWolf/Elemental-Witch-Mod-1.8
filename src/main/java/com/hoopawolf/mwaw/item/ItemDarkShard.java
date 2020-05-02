package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.structure.StructureDark;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDarkShard extends Item {

    public ItemDarkShard() {
        super();
        maxStackSize = 16;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("darkshard");
        setTextureName(Reference.MOD_ID + ":" + "darkshard");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        player.addChatComponentMessage(new ChatComponentText(player.getUniqueID().toString()));
        if (player.capabilities.isCreativeMode) {

            StructureDark.getInstance().generate(world, world.rand,
                    posX, posY, posZ);
            return true;

        }

        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }

}


