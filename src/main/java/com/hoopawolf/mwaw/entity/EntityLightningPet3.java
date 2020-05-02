package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityLightningPet3 extends EntityTameable {

    public static final float[][] rainbowColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    public int deathTicks;
    int skillTimer = 100;
    int skill2Timer = 300;

    public EntityLightningPet3(World par1World) {
        super(par1World);
        setSize(3.7F, 3.4F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.isImmuneToFire = true;
        this.setTamed(true);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            p_70612_1_ = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
            p_70612_2_ = ((EntityLivingBase) this.riddenByEntity).moveForward;

            if (p_70612_2_ <= 0.0F) {
                p_70612_2_ *= 0.25F;
            }

            if (!this.worldObj.isRemote) {
                this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            this.stepHeight = 2.0F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.9D;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }

        if (worldObj.isRemote) {
            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 3.5, posZ, 0, 0, 0);

            }
        }

        if (skillTimer != 0 && this.getAttackTarget() != null)
            --skillTimer;

        if (skill2Timer != 0 && this.getAttackTarget() != null)
            --skill2Timer;

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("lightning_normal", this);
        }

        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                this.getAttackTarget().addPotionEffect(new PotionEffect(
                        MWAWPotionRegistry.paralyzePotion.id, 20 * 2, 1));

                this.getAttackTarget().playSound("mwaw:lightningsound", 2.0F, .6F);

                skillTimer = 300;

            }

            if (skill2Timer == 0) {

                final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull2.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull3.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull4.setLocationAndAngles(this.getAttackTarget().posX - 2, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull5.setLocationAndAngles(this.getAttackTarget().posX + 2, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull6.setLocationAndAngles(this.getAttackTarget().posX - 3, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull7.setLocationAndAngles(this.getAttackTarget().posX + 3, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull35 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull35.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull36 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull36.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);

                final EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull8.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull9.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull10.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull11.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull12.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 2, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull13 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull13.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 3, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull14 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull14.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 3, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull33 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull33.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull34 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull34.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);

                final EntityLightningBolt entitywitherskull15 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull15.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull16 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull16.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull17 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull17.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull18 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull18.setLocationAndAngles(this.getAttackTarget().posX + 2, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull19 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull19.setLocationAndAngles(this.getAttackTarget().posX - 2, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull20 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull20.setLocationAndAngles(this.getAttackTarget().posX + 3, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull21 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull21.setLocationAndAngles(this.getAttackTarget().posX - 3, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull22 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull22.setLocationAndAngles(this.getAttackTarget().posX + 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull23 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull23.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);

                final EntityLightningBolt entitywitherskull24 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull24.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull25 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull25.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull26 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull26.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull27 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull27.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull28 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull28.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 2, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull29 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull29.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 3, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull30 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull30.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 3, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull31 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull31.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ - 4, this.rotationYaw, 0);
                final EntityLightningBolt entitywitherskull32 = new EntityLightningBolt(worldObj, 1, 1, 1);
                entitywitherskull32.setLocationAndAngles(this.getAttackTarget().posX - 4, this.getAttackTarget().posY, this.getAttackTarget().posZ + 4, this.rotationYaw, 0);

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

                skill2Timer = 300;
            }

        }

        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(20D, 10D, 20D));

        if (this.deathTicks == 1) {
            worldObj.getWorldInfo().setRaining(true);
            worldObj.getWorldInfo().setThundering(true);
        }


        if (this.deathTicks >= 0 && this.deathTicks <= 140) {

            for (int i = 0; i <= 60; i++) {
                if (i % 30 == 0) {
                    final EntityLightningBolt entitywitherskull = new EntityLightningBolt(worldObj, 0, 0, 0);
                    entitywitherskull.setLocationAndAngles(this.posX + 5 + this.rand.nextDouble() * 20.0D - 15.0D, this.posY, this.posZ + 5 + this.rand.nextDouble() * 20.0D - 15.0D, 0, 0);
                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull);
                }
            }

            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("lgihtning_circle_small", this);
                MoWitchAndWizard.proxy.spawnParticles("lgihtning_circle_small", this);
            }

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityLightningPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityLightningPet2 || entity1 instanceof EntityLightningPet) {

                    ;

                } else {

                    if (entity1 instanceof EntityLivingBase) {

                        ((EntityLivingBase) entity1).addPotionEffect(new PotionEffect(MWAWPotionRegistry.paralyzePotion.getId(), 2000 * 100, 1));

                    }

                }
            }
        }


        if (this.deathTicks == 140) {
            final EntityLightningEgg mob = new EntityLightningEgg(worldObj);
            mob.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityLightningPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityLightningPet2 || entity1 instanceof EntityLightningPet) {

                    ;

                } else {

                    double xx = entity1.posX;
                    double yy = entity1.posY;
                    double zz = entity1.posZ;

                    final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                    entitywitherskull1.setLocationAndAngles(xx, yy, zz, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);

                }
            }

            this.setDead();
        }

    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        Random rand = new Random();
        int form = rand.nextInt(100);

        if (form <= 18) {

            final EntityLightningBolt entity = new EntityLightningBolt(worldObj, 1, 1, 1);
            entity.setLocationAndAngles(this.getAttackTarget().posX,
                    this.getAttackTarget().posY, this.getAttackTarget().posZ,
                    0, 0);
            MWAWEntityUtil.spawnWeather(worldObj, entity);
            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
            return true;

        }
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityLightningBolt) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    protected void fall(float p_70069_1_) {
    }

    public EntityLightningPet3 createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityLightningPet3) {
                EntityLightningPet3 entitywolf = (EntityLightningPet3) p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse) p_142018_1_).isTame();
        } else {
            return false;
        }
    }

    public boolean interact(EntityPlayer p_70085_1_) {

        if (p_70085_1_.isSneaking()) {
            if (!this.isSitting()) {
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase) null);
                this.aiSit.setSitting(true);
            } else if (this.isSitting()) {
                this.aiSit.setSitting(false);
            }
        } else {

            if (this.isTamed()) {

                if (!this.isRiding()) {
                    p_70085_1_.mountEntity(this);

                }
            }

        }

        return super.interact(p_70085_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSkillTimerLightning", skillTimer);
        compund.setInteger("PetSkill2TimerLightning", skill2Timer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillTimerLightning");
        skill2Timer = compund.getInteger("PetSkill2TimerLightning");
        deathTicks = compund.getInteger("PetDeathTicks");

    }

}
