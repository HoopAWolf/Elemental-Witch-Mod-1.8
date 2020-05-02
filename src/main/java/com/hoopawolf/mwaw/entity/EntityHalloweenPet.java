package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityHalloweenPet extends EntityTameable {

    public EntityHalloweenPet(World par1World) {
        super(par1World);
        setSize(0.7F, 1.4F);
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
        this.setTamed(false);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
    }

    public boolean isAIEnabled() {
        return true;
    }

    public int getBrightnessForRender(float par1) {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1) {
        return 1.0F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (worldObj.isRemote) {
            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 1.5, posZ, 0, 0, 0);

            }
        }

        if (this.getOwner() != null) {

            if (this.getOwner().getUniqueID().toString().equals("b83dc2b2-a65f-3c9a-a343-7c899ce3d64c") |
                    this.getOwner().getUniqueID().toString().equals("9cab6703-4d88-3589-9e78-465c6c35e705") |
                    this.getOwner().getUniqueID().toString().equals("5f8a5cd2-b019-3b69-843a-6f43b134e141") |
                    this.getOwner().getUniqueID().toString().equals("3aa15cc8-0180-30e3-892e-5e2c1488407f") |
                    this.getOwner().getUniqueID().toString().equals("9b34c29b-73f4-38b6-9f81-c0e012691743") |
                    this.getOwner().getUniqueID().toString().equals("1785f3c1-d85b-3d75-8759-a354b044e82a") |
                    this.getOwner().getUniqueID().toString().equals("4eaa9eb4-98b0-3896-a29f-4946cf66e667") |
                    this.getOwner().getUniqueID().toString().equals("7d11d04e-0823-3c9f-a4e4-792eb8a4cddb") |
                    this.getOwner().getUniqueID().toString().equals("78c34fd5-513a-3b03-bc82-b6fbb89ab3aa") |
                    this.getOwner().getUniqueID().toString().equals("aece8ef4-92d2-39cb-80d4-2175e48464ec") |
                    this.getOwner().getUniqueID().toString().equals("ef63644d-04bf-3544-94af-856b621864b3") |
                    this.getOwner().getUniqueID().toString().equals("90674980-3de3-3544-9bf2-a6ba8434457a")) {
                ;
            } else {
                this.setDead();
            }

        }

        super.onLivingUpdate();
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
    }


    protected void fall(float p_70069_1_) {
    }

    public EntityHalloweenPet createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityHalloweenPet) {
                EntityHalloweenPet entitywolf = (EntityHalloweenPet) p_142018_1_;

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

        if (this.isTamed() && p_70085_1_.isSneaking()) {

            final EntityHalloweenPetEvolved mob = new EntityHalloweenPetEvolved(worldObj);
            mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setTamed(true);
            if (!p_70085_1_.capabilities.isCreativeMode) {
                p_70085_1_.setHealth(p_70085_1_.getHealth() - 6.5F);
            }
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setAlwaysRenderNameTag(true);
            p_70085_1_.playSound("mob.enderdragon.growl", 2.0F, .6F);

            this.setDead();
        }

        if (!p_70085_1_.isSneaking()) {
            if (!this.isSitting()) {
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase) null);
                this.aiSit.setSitting(true);
                return true;
            } else if (this.isSitting()) {
                this.aiSit.setSitting(false);
            }
        }

        return super.interact(p_70085_1_);
    }


}

