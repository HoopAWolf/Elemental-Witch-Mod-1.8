package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
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
import net.minecraft.world.World;

import java.util.Random;

public class EntityLightningPet2 extends EntityTameable {

    public int deathTicks;
    int skillTimer = 300;
    int involveTimer = 0;

    public EntityLightningPet2(World par1World) {
        super(par1World);
        setSize(1.7F, 2.4F);
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
    }

    public boolean isAIEnabled() {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.9D;

        if (skillTimer != 0 && this.getAttackTarget() != null)
            --skillTimer;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }

        if (worldObj.isRemote) {
            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 2.5, posZ, 0, 0, 0);

            }
        }

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


        }

        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        if (this.deathTicks >= 0 && this.deathTicks <= 140) {
            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("lgihtning_circle_small", this);
                MoWitchAndWizard.proxy.spawnParticles("lgihtning_circle_small", this);
                MoWitchAndWizard.proxy.spawnParticles("lgihtning_circle_small", this);
            }
        }


        if (this.deathTicks == 140) {
            final EntityLightningEgg mob = new EntityLightningEgg(worldObj);
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

            final EntityLightningBolt entity = new EntityLightningBolt(worldObj, 1, 1, 1);
            entity.setLocationAndAngles(this.getAttackTarget().posX,
                    this.getAttackTarget().posY, this.getAttackTarget().posZ,
                    0, 0);
            MWAWEntityUtil.spawnWeather(worldObj, entity);
            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
            return true;

        }
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
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

    public EntityLightningPet2 createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityLightningPet2) {
                EntityLightningPet2 entitywolf = (EntityLightningPet2) p_142018_1_;

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

            if (p_70085_1_.getHeldItem() != null && p_70085_1_.getHeldItem().getItem() == MWAWItemRegistry.lightningshard) {

                if (involveTimer != 10) {

                    involveTimer++;

                    if (!p_70085_1_.capabilities.isCreativeMode)
                        --p_70085_1_.getHeldItem().stackSize;

                } else {

                    final EntityLightningPet3 mob = new EntityLightningPet3(worldObj);
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

        compund.setInteger("PetSkillTimerLightning", skillTimer);
        compund.setInteger("PetInvolveTimerLightning", involveTimer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillTimerLightning");
        involveTimer = compund.getInteger("PetInvolveTimerLightning");
        deathTicks = compund.getInteger("PetDeathTicks");

    }

}