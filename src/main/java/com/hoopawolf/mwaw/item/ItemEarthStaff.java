package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityEarthMinionGood;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEarthStaff extends Item {

    public ItemEarthStaff() {
        super();
        maxStackSize = 1;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("earthstaff");
        setTextureName(Reference.MOD_ID + ":" + "earthstaff");
        setMaxDamage(500);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (par3EntityPlayer.getFoodStats().getFoodLevel() > 1) {

            final EntityEarthMinionGood entity = new EntityEarthMinionGood(par2World);
            entity.setLocationAndAngles(par3EntityPlayer.posX + 1.5,
                    par3EntityPlayer.posY, par3EntityPlayer.posZ,
                    0, 0);
            MWAWEntityUtil.spawnInWorld(par2World, entity);

            final EntityEarthMinionGood entity1 = new EntityEarthMinionGood(par2World);
            entity1.setLocationAndAngles(par3EntityPlayer.posX - 1.5,
                    par3EntityPlayer.posY, par3EntityPlayer.posZ,
                    0, 0);
            MWAWEntityUtil.spawnInWorld(par2World, entity1);

            if (!par3EntityPlayer.capabilities.isCreativeMode) {
                par3EntityPlayer.getFoodStats().setFoodLevel(par3EntityPlayer.getFoodStats().getFoodLevel() - 4);
                par1ItemStack.damageItem(1, par3EntityPlayer);
            }
        }

        return par1ItemStack;

    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

}