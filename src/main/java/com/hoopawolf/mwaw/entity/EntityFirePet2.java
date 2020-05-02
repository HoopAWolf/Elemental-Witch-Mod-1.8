package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

public class EntityFirePet2 extends EntityTameable {

    public int deathTicks;
    int skillTimer = 100;
    int involveTimer = 0;

    public EntityFirePet2(World par1World) {
        super(par1World);
        setSize(1.7F, 2.4F);
        this.isImmuneToFire = true;
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

        if (skillTimer != 0 && this.getAttackTarget() != null)
            --skillTimer;

        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);

                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                entitywitherskull1.motionY = -5;

                skillTimer = 100;


            }

        }

        if (worldObj.isRemote) {
            for (int i = 0; i < 1; i++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                this.worldObj.spawnParticle(EnumParticleTypes.FLAME, (posX + (double) (rand.nextFloat()
                                * width * 2.0F))
                                - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2);
            }
        }
        if (worldObj.isRemote) {

            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 2.5, posZ, 0, 0, 0);

            }
        }

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }


        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        if (this.deathTicks >= 0 && this.deathTicks <= 140) {
            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("fire_tornado_big", this);
                MoWitchAndWizard.proxy.spawnParticles("fire_tornado_big", this);
            }
        }


        if (this.deathTicks == 140) {
            final EntityFireEgg mob = new EntityFireEgg(worldObj);
            mob.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            this.setDead();
        }

    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        Random rand = new Random();
        int form = rand.nextInt(100);

        if (form <= 18) {

            p_70652_1_.worldObj.newExplosion(this, this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 1, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
            return true;

        }
        p_70652_1_.setFire(10);
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
    }


    public EntityFirePet createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityTNTPrimed) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityFirePet2) {
                EntityFirePet2 entitywolf = (EntityFirePet2) p_142018_1_;

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


            if (p_70085_1_.getHeldItem() != null && p_70085_1_.getHeldItem().getItem() == MWAWItemRegistry.fireshard) {

                if (involveTimer != 10) {

                    involveTimer++;

                    if (!p_70085_1_.capabilities.isCreativeMode)
                        --p_70085_1_.getHeldItem().stackSize;

                } else {

                    final EntityFirePet3 mob = new EntityFirePet3(worldObj);
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

        compund.setInteger("PetSkillTimerFire", skillTimer);
        compund.setInteger("PetInvolveTimerFire", involveTimer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillTimerFire");
        involveTimer = compund.getInteger("PetInvolveTimerFire");
        deathTicks = compund.getInteger("PetDeathTicks");

    }


}

