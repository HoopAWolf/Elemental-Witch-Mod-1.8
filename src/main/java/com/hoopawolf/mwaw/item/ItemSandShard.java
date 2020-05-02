package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntitySandWitch;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSandShard extends Item {

    public ItemSandShard() {
        super();
        maxStackSize = 16;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("sandshard");
        setTextureName(Reference.MOD_ID + ":" + "sandshard");
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        if (world.getBlock(posX, posY, posZ) == Blocks.sand && world.getBlock(posX, posY - 1, posZ) == Blocks.sand || world.getBlock(posX, posY, posZ) == Blocks.sand && world.getBlock(posX, posY + 1, posZ) == Blocks.sand) {

            final EntitySandWitch entity = new EntitySandWitch(world);
            entity.setLocationAndAngles(posX,
                    posY, posZ,
                    0, 0);
            MWAWEntityUtil.spawnInWorld(world, entity);

            world.setBlockToAir(posX, posY, posZ);
            world.setBlockToAir(posX, posY - 1, posZ);
            world.setBlockToAir(posX, posY + 1, posZ);
            return true;

        }

        return false;
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.epic;
    }
}

