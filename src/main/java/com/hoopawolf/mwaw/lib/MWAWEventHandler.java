package com.hoopawolf.mwaw.lib;

import com.hoopawolf.mwaw.entity.EntityAirPhoenix3;
import com.hoopawolf.mwaw.entity.EntityAirWitch;
import com.hoopawolf.mwaw.entity.EntityHalloweenEgg;
import com.hoopawolf.mwaw.particle.EntitySmallWindFX;
import com.hoopawolf.mwaw.projectile.EntityWindShoot;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import com.hoopawolf.mwaw.skills.EntityAirClone;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class MWAWEventHandler {

    int timer = 18;

    @SubscribeEvent
    public void EntityJoinWorldEvent(final EntityJoinWorldEvent event) {

        String NBT_KEY_PET = "firstjoinpet";
        String NBT_KEY_ITEM = "firstjoinitem";

        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;

            NBTTagCompound data = event.entity.getEntityData();
            NBTTagCompound persistent;
            if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
                data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persistent = new NBTTagCompound()));
            } else {
                persistent = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
            }

            if (!persistent.hasKey(NBT_KEY_PET)) {

                if (player.getDisplayName().equals("HoopaWolf") |
                        player.getDisplayName().equals("DarkSoul260") |
                        player.getDisplayName().equals("AlexWei4") |
                        player.getDisplayName().equals("CruaverVoidDrake") |
                        player.getDisplayName().equals("TOMMYZILLA2003") |
                        player.getDisplayName().equals("SlipperySkater") |
                        player.getDisplayName().equals("jbyoda") |
                        player.getDisplayName().equals("Soul_Mask_Master") |
                        player.getDisplayName().equals("Dragonlord7479") |
                        player.getDisplayName().equals("aydanprice") |
                        player.getDisplayName().equals("Darkraivoid") |
                        player.getDisplayName().equals("Noremac_TC")) {

                    persistent.setBoolean(NBT_KEY_PET, true);

                    final EntityHalloweenEgg mob = new EntityHalloweenEgg(event.world);
                    mob.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0);
                    MWAWEntityUtil.spawnInWorld(event.world, mob);
                    mob.func_152115_b(player.getUniqueID().toString());
                    mob.setCustomNameTag("Owner: " + player.getDisplayName());
                    mob.setAlwaysRenderNameTag(true);

                }
            }

            if (!persistent.hasKey(NBT_KEY_ITEM)) {

                if (player.getDisplayName().equals("HoopaWolf") |
                        player.getDisplayName().equals("Nyctodarkmatter") |
                        player.getDisplayName().equals("LuridGlow") |
                        player.getDisplayName().equals("Darkraivoid") |
                        player.getDisplayName().equals("TeoTRM") |
                        player.getDisplayName().equals("SlipperySkater") |
                        player.getDisplayName().equals("Gookuin") |
                        player.getDisplayName().equals("jbyoda") |
                        player.getDisplayName().equals("AlexWei4") |
                        player.getDisplayName().equals("alxndr11") |
                        player.getDisplayName().equals("snowielove2") |
                        player.getDisplayName().equals("Xfur") |
                        player.getDisplayName().equals("XxJuggeRxX")) {

                    persistent.setBoolean(NBT_KEY_ITEM, true);

                    player.inventory.addItemStackToInventory(new ItemStack(MWAWItemRegistry.christmasstaff));

                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdateEvent(final LivingEvent.LivingUpdateEvent event) {

        NBTTagCompound compund = new NBTTagCompound();

        compund.setInteger("EventTimer", timer);

        if (event.entityLiving.isPotionActive(MWAWPotionRegistry.paralyzePotion)) {

            if (event.entityLiving instanceof EntityMob) {
                ((EntityMob) event.entityLiving).setAttackTarget(null);
            }

            if (event.entityLiving instanceof EntityCreeper) {
                ((EntityCreeper) event.entityLiving).setAttackTarget(null);
                ((EntityCreeper) event.entityLiving).setCreeperState(0);
            }
        }


        if (event.entityLiving.isPotionActive(MWAWPotionRegistry.enderSkinPotion)) {
            if (event.entityLiving.isWet())
                event.entityLiving.attackEntityFrom(DamageSource.magic, 1.0F);
        }

        if (event.entityLiving.isPotionActive(MWAWPotionRegistry.earthstancePotion)) {

            for (int i = 0; i < 1; i++) {
                double d = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d1 = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d2 = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                event.entityLiving.worldObj.spawnParticle("blockcrack_3_0", (event.entityLiving.posX + (double) (event.entityLiving.worldObj.rand.nextFloat()
                                * event.entityLiving.width * 2.0F))
                                - (double) event.entityLiving.width - d * d3,
                        (event.entityLiving.posY + (double) (event.entityLiving.worldObj.rand.nextFloat() * event.entityLiving.height)) - d1 * d3,
                        (event.entityLiving.posZ + (double) (event.entityLiving.worldObj.rand.nextFloat() * event.entityLiving.width * 2.0F))
                                - (double) event.entityLiving.width - d2 * d3, d, d1, d2);
            }

            if (event.entityLiving.worldObj.getBlock((int) event.entityLiving.posX, (int) event.entityLiving.posY - 1, (int) event.entityLiving.posZ) == Blocks.dirt ||
                    event.entityLiving.worldObj.getBlock((int) event.entityLiving.posX, (int) event.entityLiving.posY - 1, (int) event.entityLiving.posZ) == Blocks.stone ||
                    event.entityLiving.worldObj.getBlock((int) event.entityLiving.posX, (int) event.entityLiving.posY - 1, (int) event.entityLiving.posZ) == Blocks.grass) {

                if (event.entityLiving.ticksExisted % 10 == 0 && event.entityLiving.getHealth() != event.entityLiving.getMaxHealth()) {
                    event.entityLiving.heal(1);
                }

            }

        }

        if (event.entityLiving.isPotionActive(MWAWPotionRegistry.airstancePotion)) {

            for (int i = 0; i < 1; i++) {
                double d = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d1 = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d2 = event.entityLiving.worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmallWindFX(event.entityLiving.worldObj, event.entityLiving.posX + (double) (event.entityLiving.worldObj.rand.nextFloat()
                        * event.entityLiving.width * 2.0F)
                        - (double) event.entityLiving.width - d * d3,
                        (event.entityLiving.posY + (double) (event.entityLiving.worldObj.rand.nextFloat() * event.entityLiving.height)) - d1 * d3,
                        (event.entityLiving.posZ + (double) (event.entityLiving.worldObj.rand.nextFloat() * event.entityLiving.width * 2.0F))
                                - (double) event.entityLiving.width - d2 * d3, d, d1, d2));
            }

            if (event.entityLiving.getHealth() <= 3) {

                List list = event.entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(
                        event.entityLiving,
                        event.entityLiving.boundingBox.expand(5D, 5D, 5D));
                for (int j = 0; j < list.size(); j++) {
                    Entity entity1 = (Entity) list.get(j);
                    if (entity1 instanceof EntityAirWitch || entity1 instanceof EntityWindShoot || entity1 instanceof EntityAirClone || entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityAirPhoenix3) {

                        ;

                    } else {

                        double xx = entity1.posX - event.entityLiving.posX;
                        double zz = entity1.posZ - event.entityLiving.posZ;

                        entity1.motionX = (xx / (9.0F * entity1.getDistanceToEntity(event.entityLiving)));
                        entity1.motionZ = (zz / (9.0F * entity1.getDistanceToEntity(event.entityLiving)));

                    }
                }
            }

            event.entityLiving.fallDistance = 0;

            if (!event.entityLiving.onGround && event.entityLiving.motionY < 0.0D)
                event.entityLiving.motionY *= 0.6D;

            if (event.entityLiving.isBurning())
                event.entityLiving.extinguish();

        }

        if (event.entityLiving.isPotionActive(MWAWPotionRegistry.paranoidPotion) && event.entityLiving instanceof EntityPlayer) {

            timer = compund.getInteger("EventTimer");

            if (timer != 0)
                --timer;

            if (timer <= 0) {
                String sound;
                switch (((EntityPlayer) event.entityLiving).worldObj.rand.nextInt(5)) {
                    case (0):
                        sound = (event.entityLiving.worldObj.provider.getDimensionId() == -1 ? "mob.ghast.moan" : "mob.zombie.say");
                        break;
                    case (1):
                        sound = "creeper.primed";
                        break;
                    case (2):
                        sound = "mob.spider.say";
                        break;
                    case (3):
                        sound = "mob.endermen.scream";
                        break;
                    case (4):
                        sound = "mwaw:whisper";
                        break;
                    default:
                        sound = "mob.endermen.stare";
                }
                event.entityLiving.worldObj.playSoundAtEntity(event.entityLiving, sound, 2.0F, .6F);
                timer = 18;
            }
        }
    }
}