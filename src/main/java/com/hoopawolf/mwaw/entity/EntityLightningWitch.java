package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.particle.EntityLightningFX;
import com.hoopawolf.mwaw.projectile.EntityLightningBall;
import com.hoopawolf.mwaw.projectile.EntityLightningShoot;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityLightningGolem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityLightningWitch extends EntityMob implements IRangedAttackMob {

    int timer = 52;
    int summonGolem = 300;
    int summonLightning = 600;
    int summonLightningBall = 200;

    public EntityLightningWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        if (MWAWConfigHandler.ElementalsVSWitches) {
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWitch.class, true));
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.witch.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.witch.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.witch.death";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (summonGolem != 0 && this.getAttackTarget() != null) {
            --summonGolem;
        }

        if (summonLightning != 0 && this.getAttackTarget() != null) {
            --summonLightning;
        }

        if (summonLightningBall != 0 && this.getAttackTarget() != null) {
            --summonLightningBall;
        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("lightning_normal", this);
        }
        if (this.getAttackTarget() != null) {

            if (summonGolem == 0) {

                final EntityLightningGolem entitywitherskull1 = new EntityLightningGolem(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                final EntityLightningGolem entitywitherskull3 = new EntityLightningGolem(worldObj);
                entitywitherskull3.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);
                entitywitherskull3.setAttackTarget(this.getAttackTarget());

                final EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull4.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull5.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);

                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);
                MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull4);
                MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull5);

                summonGolem = 300;

            }

            if (summonLightning == 0) {

                double xx = this.getAttackTarget().posX;
                double yy = this.getAttackTarget().posY;
                double zz = this.getAttackTarget().posZ;

                final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull1.setLocationAndAngles(xx, yy + 1, zz, this.rotationYaw, 0);

                MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);

                summonLightning = 600;
            }

            if (!worldObj.isThundering()) {
                worldObj.getWorldInfo().setRaining(true);
                worldObj.getWorldInfo().setThundering(true);
            }

            if (this.getHealth() <= 30) {

                --timer;

                for (int i = 0; i < 10; i++) {
                    if (worldObj.isRemote) {
                        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, this.posX + this.rand.nextGaussian() * 8.0D * 0.5D, this.posY, this.posZ + this.rand.nextGaussian() * 8.0D * 0.5D, 0.0D, 0.5D, 0.0D));
                    }
                }

                if (timer == 50) {

                    final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull2.setLocationAndAngles(this.posX - 1, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull3.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 2, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull4.setLocationAndAngles(this.posX + 2, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull5.setLocationAndAngles(this.posX + 2, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull6.setLocationAndAngles(this.posX + 2, this.posY, this.posZ + 1, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull2.setLocationAndAngles(this.posX - 1, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull3.setLocationAndAngles(this.posX + 1, this.posY, this.posZ - 2, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull10.setLocationAndAngles(this.posX - 2, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull11.setLocationAndAngles(this.posX - 2, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull12.setLocationAndAngles(this.posX - 2, this.posY, this.posZ + 1, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull2);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull3);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull4);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull5);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull6);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull7);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull8);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull9);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull10);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull11);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull12);

                } else if (timer == 25) {

                    final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull2.setLocationAndAngles(this.posX - 1, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull3.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull4.setLocationAndAngles(this.posX - 2, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull5.setLocationAndAngles(this.posX + 2, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull6.setLocationAndAngles(this.posX - 3, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull7.setLocationAndAngles(this.posX + 3, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull35 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull35.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull36 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull36.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 4, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull8.setLocationAndAngles(this.posX + 4, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull9.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull10.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull11.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull12.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull13 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull13.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull14 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull14.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull33 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull33.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull34 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull34.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 4, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull15 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull15.setLocationAndAngles(this.posX, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull16 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull16.setLocationAndAngles(this.posX - 1, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull17 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull17.setLocationAndAngles(this.posX + 1, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull18 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull18.setLocationAndAngles(this.posX + 2, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull19 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull19.setLocationAndAngles(this.posX - 2, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull20 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull20.setLocationAndAngles(this.posX + 3, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull21 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull21.setLocationAndAngles(this.posX - 3, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull22 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull22.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull23 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull23.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 4, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull24 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull24.setLocationAndAngles(this.posX - 4, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull25 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull25.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull26 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull26.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull27 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull27.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull28 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull28.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull29 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull29.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull30 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull30.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull31 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull31.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull32 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull32.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 4, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull2);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull3);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull4);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull5);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull6);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull7);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull8);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull9);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull10);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull11);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull12);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull13);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull14);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull15);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull16);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull17);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull18);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull19);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull20);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull21);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull22);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull23);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull24);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull25);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull26);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull27);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull28);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull29);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull30);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull31);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull32);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull33);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull34);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull35);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull36);

                } else if (timer == 0) {

                    final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull2.setLocationAndAngles(this.posX - 1, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull3.setLocationAndAngles(this.posX + 1, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull4.setLocationAndAngles(this.posX - 2, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull5.setLocationAndAngles(this.posX + 2, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull6.setLocationAndAngles(this.posX - 3, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull7.setLocationAndAngles(this.posX + 3, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull8.setLocationAndAngles(this.posX - 4, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull9.setLocationAndAngles(this.posX + 4, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull10.setLocationAndAngles(this.posX - 5, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull11.setLocationAndAngles(this.posX + 5, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull45 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull45.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull46 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull46.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 6, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull12.setLocationAndAngles(this.posX + 6, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull13 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull13.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull14 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull14.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull15 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull15.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull16 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull16.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull17 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull17.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull18 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull18.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull19 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull19.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull20 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull20.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull21 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull21.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 5, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull22 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull22.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 5, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull47 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull47.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull48 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull48.setLocationAndAngles(this.posX + 6, this.posY, this.posZ + 6, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull23 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull23.setLocationAndAngles(this.posX, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull24 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull24.setLocationAndAngles(this.posX - 1, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull25 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull25.setLocationAndAngles(this.posX + 1, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull26 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull26.setLocationAndAngles(this.posX - 2, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull27 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull27.setLocationAndAngles(this.posX + 2, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull28 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull28.setLocationAndAngles(this.posX - 3, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull29 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull29.setLocationAndAngles(this.posX + 3, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull30 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull30.setLocationAndAngles(this.posX - 4, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull31 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull31.setLocationAndAngles(this.posX + 4, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull32 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull32.setLocationAndAngles(this.posX - 5, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull33 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull33.setLocationAndAngles(this.posX + 5, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull49 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull49.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull50 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull50.setLocationAndAngles(this.posX + 6, this.posY, this.posZ - 6, this.rotationYaw, 0);

                    final EntityLightningBolt entitywitherskull34 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull34.setLocationAndAngles(this.posX - 6, this.posY, this.posZ, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull35 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull35.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull36 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull36.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 1, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull37 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull37.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull38 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull38.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 2, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull39 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull39.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull40 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull40.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 3, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull41 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull41.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull42 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull42.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 4, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull43 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull43.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 5, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull44 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull44.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 5, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull51 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull51.setLocationAndAngles(this.posX - 6, this.posY, this.posZ - 6, this.rotationYaw, 0);
                    final EntityLightningBolt entitywitherskull52 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull52.setLocationAndAngles(this.posX - 6, this.posY, this.posZ + 6, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull2);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull3);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull4);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull5);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull6);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull7);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull8);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull9);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull10);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull11);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull12);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull13);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull14);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull15);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull16);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull17);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull18);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull19);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull20);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull21);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull22);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull23);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull24);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull25);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull26);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull27);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull28);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull29);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull30);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull31);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull32);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull33);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull35);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull36);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull37);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull38);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull39);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull41);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull42);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull43);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull44);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull45);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull46);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull47);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull48);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull49);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull50);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull51);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull52);

                    timer = 52;

                }

            }

        }

        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        super.onLivingUpdate();
    }

    private double func_82214_u(final int par1) {
        if (par1 <= 0) {
            return this.posX;
        } else {
            final float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F
                    * (float) Math.PI;
            final float f1 = MathHelper.cos(f);
            return this.posX + f1 * 1.3D;
        }
    }

    private double func_82208_v(final int par1) {
        return par1 <= 0 ? this.posY + 0.5 : this.posY + 0.5;
    }

    private double func_82213_w(final int par1) {
        if (par1 <= 0) {
            return this.posZ;
        } else {
            final float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F
                    * (float) Math.PI;
            final float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }


    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {

        if (summonLightningBall != 0) {
            for (int i = 0; i < 10; i++) {

                this.func_82216_a(0, p_82196_1_);

            }
        } else {

            this.func_82216_a(0, p_82196_1_);
            summonLightningBall = 300;
        }
    }

    private void func_82216_a(final int par1,
                              final EntityLivingBase par2EntityLivingBase) {
        this.func_82209_a(par1, par2EntityLivingBase.posX,
                par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight()
                        * 0.5D, par2EntityLivingBase.posZ, par1 == 0
                        && this.rand.nextFloat() < 0.001F);
    }

    private void func_82209_a(final int par1, final double par2,
                              final double par4, final double par6, final boolean par8) {

        final double d3 = this.func_82214_u(par1);
        final double d4 = this.func_82208_v(par1);
        final double d5 = this.func_82213_w(par1);
        final double d6 = par2 - d3;
        final double d7 = par4 - d4;
        final double d8 = par6 - d5;

        if (summonLightningBall != 0) {
            final EntityLightningShoot entitywitherskull = new EntityLightningShoot(
                    this.worldObj, this, d6, d7, d8);

            entitywitherskull.posY = d4 + 0.5D;
            entitywitherskull.posX = d3;
            entitywitherskull.posZ = d5;
            MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

        } else {

            final EntityLightningBall entitywitherskull = new EntityLightningBall(
                    this.worldObj, this, d6, d7, d8);

            entitywitherskull.posY = d4 + 0.5D;
            entitywitherskull.posX = d3;
            entitywitherskull.posZ = d5;
            MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

        }

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityLightningBolt || source.getEntity() instanceof EntityLightningBall || source.getEntity() instanceof EntityLightningShoot) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchLightningTimer", timer);
        compund.setInteger("WitchLightningSummonGolem", summonGolem);
        compund.setInteger("WitchSummonLighting", summonLightning);
        compund.setInteger("WitchSummonLightningBall", summonLightningBall);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        timer = compund.getInteger("WitchLightningTimer");
        summonGolem = compund.getInteger("WitchLightningSummonGolem");
        summonLightning = compund.getInteger("WitchSummonLighting");
        summonLightningBall = compund.getInteger("WitchSummonLightningBall");

    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 100D, 20D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityLightningGolem) {

                entitytargetcheck.setDead();

            }

            super.setDead();
        }
    }

    protected Item getDropItem() {
        return MWAWItemRegistry.lightningshard;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean interact(EntityPlayer p_70085_1_) {

        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null) {

            if (itemstack != null) {

                if (itemstack.getItem() == MWAWItemRegistry.emptyegg) {

                    if (this.getHealth() <= 13 && rand.nextInt(1000) <= 10) {
                        itemstack.stackSize--;

                        final EntityLightningEgg mob = new EntityLightningEgg(worldObj);
                        mob.setLocationAndAngles(posX, posY, posZ, p_70085_1_.rotationYaw, 0);
                        MWAWEntityUtil.spawnInWorld(worldObj, mob);
                        mob.setOwnerId(p_70085_1_.getUniqueID().toString());
                        mob.setCustomNameTag("Owner: " + p_70085_1_.getDisplayName());
                        mob.setAlwaysRenderNameTag(true);

                        this.setDead();
                    }
                }
            }
        }

        return super.interact(p_70085_1_);
    }


}
