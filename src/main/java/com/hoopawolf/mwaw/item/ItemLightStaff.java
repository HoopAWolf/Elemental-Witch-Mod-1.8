package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.projectile.EntityMWAWCheckLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemLightStaff extends Item {

    public ItemLightStaff() {
        super();
        maxStackSize = 1;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("lightstaff");
        setTextureName(Reference.MOD_ID + ":" + "lightstaff");
        setMaxDamage(500);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        if (par3EntityPlayer.getFoodStats().getFoodLevel() > 1) {

            final Vec3 look = par3EntityPlayer.getLookVec();
            final EntityMWAWCheckLight fireball = new EntityMWAWCheckLight(par2World,
                    par3EntityPlayer, 1, 1, 1);
            fireball.setPosition(par3EntityPlayer.posX + look.xCoord * 1,
                    par3EntityPlayer.posY + look.yCoord * 1 + par3EntityPlayer.getEyeHeight(), par3EntityPlayer.posZ
                            + look.zCoord * 1);
            fireball.accelerationX = look.xCoord * 0.1;
            fireball.accelerationY = look.yCoord * 0.1;
            fireball.accelerationZ = look.zCoord * 0.1;
            MWAWEntityUtil.spawnInWorld(par2World, fireball);

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
