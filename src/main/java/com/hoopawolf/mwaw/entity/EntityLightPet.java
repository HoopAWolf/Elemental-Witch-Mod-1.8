package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
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
import net.minecraft.world.World;

public class EntityLightPet extends EntityTameable {

    public int skillTimer = 200;
    public float floatingRotation;
    int involveTimer = 0;

    public EntityLightPet(World par1World) {
        super(par1World);
        setSize(0.7F, 1.0F);
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

        floatingRotation += 0.02F;

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.9D;

        if (this.getAttackTarget() != null && skillTimer != 0)
            --skillTimer;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1)
            this.setHealth(this.getHealth() + 1);

        if (worldObj.isRemote) {
            if (this.isSitting())
                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 1.5, posZ, 0, 0, 0);
        }

        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 30 * 3, 1));
                skillTimer = 200;
            }

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("light_normal", this);
        }

        super.onLivingUpdate();
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
    }

    public boolean isPushedByWater() {
        return false;
    }

    public EntityLightPet createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityLightPet) {
                EntityLightPet entitywolf = (EntityLightPet) p_142018_1_;

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

            if (p_70085_1_.getHeldItem() != null && p_70085_1_.getHeldItem().getItem() == MWAWItemRegistry.lightshard) {

                if (involveTimer != 5) {

                    involveTimer++;

                    if (!p_70085_1_.capabilities.isCreativeMode)
                        --p_70085_1_.getHeldItem().stackSize;

                } else {

                    final EntityLightPet2 mob = new EntityLightPet2(worldObj);
                    mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                    mob.setOwnerId(this.getOwnerId());
                    mob.setCustomNameTag(this.getCustomNameTag());
                    mob.setTamed(true);
                    MWAWEntityUtil.spawnInWorld(worldObj, mob);
                    mob.setAlwaysRenderNameTag(true);
                    p_70085_1_.playSound("random.levelup", 2.0F, .6F);

                    this.setDead();

                }

            }
        }


        if (!this.isSitting()) {
            this.navigator.clearPathEntity();
            this.setAttackTarget((EntityLivingBase) null);
            this.aiSit.setSitting(true);
        } else if (this.isSitting()) {
            this.aiSit.setSitting(false);
        }

        return super.interact(p_70085_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSkillLight", skillTimer);
        compund.setInteger("PetInvolveTimerLight", involveTimer);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillLight");
        involveTimer = compund.getInteger("PetInvolveTimerLight");

    }

    public float getFloatingRotation() {
        return 15 - Math.abs((float) Math.sin(floatingRotation) * 3.5F);
    }

}

