package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import com.hoopawolf.mwaw.skills.EntityMiniTornado;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityAirPhoenix3 extends EntityTameable {

    public static final float[][] rainbowColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    public int deathTicks;
    int tornadoTimer = 100;
    int skill2Timer = 300;

    public EntityAirPhoenix3(World par1World) {
        super(par1World);
        setSize(2.4F, 3.4F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.4D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
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


    public void onLivingUpdate() {

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.6D;

        if (tornadoTimer != 0 && this.getAttackTarget() != null)
            --tornadoTimer;

        if (skill2Timer != 0 && this.getAttackTarget() != null)
            --skill2Timer;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1)
            this.setHealth(this.getHealth() + 1);

        if (worldObj.isRemote) {
            if (this.isSitting())
                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 3.5, posZ, 0, 0, 0);
        }


        if (this.getAttackTarget() != null) {

            if (tornadoTimer == 0) {

                final EntityMiniTornado entitywitherskull1 = new EntityMiniTornado(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                entitywitherskull1.mountEntity(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                tornadoTimer = 300;

            }

            if (skill2Timer == 0) {

                if (this.getOwner() != null) {
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(MWAWPotionRegistry.airstancePotion.getId(), 20 * 100, 1));
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 20 * 100, 1));
                    skill2Timer = 300;
                }
            }

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("air_normal", this);
        }

        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(20D, 10D, 20D));

        if (this.deathTicks >= 0 && this.deathTicks <= 140) {
            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("air_tornado_small", this);
                MoWitchAndWizard.proxy.spawnParticles("air_tornado_small", this);
                MoWitchAndWizard.proxy.spawnParticles("air_tornado_small", this);
                MoWitchAndWizard.proxy.spawnParticles("air_tornado_small", this);
            }

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityAirPhoenix3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityAirPhoenix2 || entity1 instanceof EntityAirPhoenix) {

                    ;

                } else {

                    double xx = entity1.posX - this.posX;
                    double zz = entity1.posZ - this.posZ;

                    entity1.motionX = (-xx / (4.0F * entity1.getDistanceToEntity(this)));
                    entity1.motionZ = (-zz / (4.0F * entity1.getDistanceToEntity(this)));

                }
            }
        }


        if (this.deathTicks == 140) {
            final EntityAirEgg mob = new EntityAirEgg(worldObj);
            mob.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityAirPhoenix3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityAirPhoenix2 || entity1 instanceof EntityAirPhoenix) {

                    ;

                } else {

                    entity1.motionY = 2;

                }
            }

            if (this.getOwner() != null) {
                this.getOwnerEntity().addPotionEffect(new PotionEffect(MWAWPotionRegistry.airstancePotion.getId(), 20 * 100, 1));
                this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 20 * 100, 1));
            }

            this.setDead();
        }

    }

    public boolean canBePushed() {

        if (deathTicks > 0)
            return false;

        return true;

    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean isPushedByWater() {

        if (deathTicks > 0)
            return false;

        return true;
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityMiniTornado) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        Random rand = new Random();
        int form = rand.nextInt(4);

        if (form == 1) {

            float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

            if (f4 > 0.0F) {
                p_70652_1_.addVelocity(this.motionX * (double) 3 * 0.6000000238418579D / (double) f4, 0.1D, this.motionZ * (double) 3 * 0.6000000238418579D / (double) f4);
            }

            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
            return true;

        } else if (form == 2) {

            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
            p_70652_1_.motionY = 1;
            return true;

        } else if (form == 3) {

            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 7.0F);
            return true;

        }

        return false;
    }


    protected void fall(float p_70069_1_) {
    }

    public EntityAirPhoenix createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityAirPhoenix3) {
                EntityAirPhoenix3 entitywolf = (EntityAirPhoenix3) p_142018_1_;

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
        if (!p_70085_1_.isSneaking()) {
            if (!this.isSitting()) {
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase) null);
                this.aiSit.setSitting(true);
            } else if (this.isSitting()) {
                this.aiSit.setSitting(false);
            }
        }

        return super.interact(p_70085_1_);
    }


    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSummonMiniTornado", tornadoTimer);
        compund.setInteger("PetSkill2Timer", skill2Timer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        tornadoTimer = compund.getInteger("PetSummonMiniTornado");
        skill2Timer = compund.getInteger("PetSkill2Timer");
        deathTicks = compund.getInteger("PetDeathTicks");

    }

}


