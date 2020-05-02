package com.hoopawolf.mwaw.item;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.skills.EntityBigSnowGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemChristmasStaff extends Item {

    public ItemChristmasStaff() {
        super();
        maxStackSize = 1;
        setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        setUnlocalizedName("christmasstaff");
        setTextureName(Reference.MOD_ID + ":" + "christmasstaff");
        setMaxDamage(500);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par3EntityPlayer.isRiding()) {

            if (par3EntityPlayer.getUniqueID().toString().equals("b83dc2b2-a65f-3c9a-a343-7c899ce3d64c") |
                    par3EntityPlayer.getUniqueID().toString().equals("df411d88-f3ea-3309-9e7d-2bdefccd8c27") |
                    par3EntityPlayer.getUniqueID().toString().equals("ff1e5a97-c5f2-3af1-a3b0-351e777c004d") |
                    par3EntityPlayer.getUniqueID().toString().equals("ef63644d-04bf-3544-94af-856b621864b3") |
                    par3EntityPlayer.getUniqueID().toString().equals("0f7b67db-d9e2-3f45-8e2f-03c41d7c9d9c") |
                    par3EntityPlayer.getUniqueID().toString().equals("1785f3c1-d85b-3d75-8759-a354b044e82a") |
                    par3EntityPlayer.getUniqueID().toString().equals("8328a8f0-1b2c-3e5b-8cc0-bcd175a0ffbd") |
                    par3EntityPlayer.getUniqueID().toString().equals("4eaa9eb4-98b0-3896-a29f-4946cf66e667") |
                    par3EntityPlayer.getUniqueID().toString().equals("5f8a5cd2-b019-3b69-843a-6f43b134e141") |
                    par3EntityPlayer.getUniqueID().toString().equals("47730a82-ea9e-39f0-b228-1403ebfa5dfb") |
                    par3EntityPlayer.getUniqueID().toString().equals("a5ee21bc-65f0-30bb-8283-fc03b9461c5e") |
                    par3EntityPlayer.getUniqueID().toString().equals("65d1f340-4b4d-335d-8df2-5927070ac698") |
                    par3EntityPlayer.getUniqueID().toString().equals("2f1ed741-ad1d-3040-b6f5-1586477208bf") |
                    par3EntityPlayer.getUniqueID().toString().equals("90674980-3de3-3544-9bf2-a6ba8434457a")) {

                if (par3EntityPlayer.getFoodStats().getFoodLevel() > 1) {

                    EntityBigSnowGolem golem = new EntityBigSnowGolem(par2World);
                    golem.setPositionAndUpdate(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);

                    EntitySnowman snowman1 = new EntitySnowman(par2World);
                    snowman1.setPositionAndUpdate(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);

                    EntitySnowman snowman2 = new EntitySnowman(par2World);
                    snowman2.setPositionAndUpdate(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);

                    MWAWEntityUtil.spawnInWorld(par2World, golem);
                    MWAWEntityUtil.spawnInWorld(par2World, snowman1);
                    MWAWEntityUtil.spawnInWorld(par2World, snowman2);

                    par3EntityPlayer.mountEntity(golem);
                    snowman1.setLeashedToEntity(golem, true);
                    snowman2.setLeashedToEntity(golem, true);

                    if (!par3EntityPlayer.capabilities.isCreativeMode) {
                        par3EntityPlayer.getFoodStats().setFoodLevel(par3EntityPlayer.getFoodStats().getFoodLevel() - 4);
                        par1ItemStack.damageItem(1, par3EntityPlayer);
                    }
                }
            } else {

                if (!par2World.isRemote) {
                    par3EntityPlayer.addChatComponentMessage((new ChatComponentTranslation(
                            "chat.mwaw.nopower")));
                }

            }
        }
        return par1ItemStack;

    }

    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

}
