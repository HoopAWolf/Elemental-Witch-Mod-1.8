package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityAirEgg;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAirEgg extends Item {

    public ItemAirEgg() {
        super();
        maxStackSize = 1;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("airegg");
        setTextureName(Reference.MOD_ID + ":" + "airegg");
    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

        final EntityAirEgg mob = new EntityAirEgg(par2World);
        mob.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, par3EntityPlayer.rotationYaw, 0);
        MWAWEntityUtil.spawnInWorld(par2World, mob);
        mob.func_152115_b(par3EntityPlayer.getUniqueID().toString());
        mob.setCustomNameTag("Owner: " + par3EntityPlayer.getDisplayName());
        mob.setAlwaysRenderNameTag(true);

        if (!par3EntityPlayer.capabilities.isCreativeMode)
            --par1ItemStack.stackSize;

        return par1ItemStack;

    }

}
