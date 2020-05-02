package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.entity.EntityWaterWitch;
import com.hoopawolf.mwaw.projectile.EntityWaterShoot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

public class EntityWaterSpout extends EntityMob {

    public EntityWaterSpout(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.0F, 0.0F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Integer.MAX_VALUE);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(100.0D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (this.motionY <= 0.0D) {

            this.motionY = 0.0D;

        }

        if (this.ticksExisted >= 400)
            this.setDead();


        if (worldObj.isRemote) {
            worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, posX, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, posX + 0.2, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, posX - 0.2, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, posX, posY, posZ + 0.2, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, posX + 0.2, posY, posZ - 0.2, 0, 0.5, 0);

            worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX + 0.2, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX - 0.2, posY, posZ, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX, posY, posZ + 0.2, 0, 0.5, 0);
            worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, posX + 0.2, posY, posZ - 0.2, 0, 0.5, 0);
        }

        List list1 = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(1D, 4D, 1D));
        for (int k = 0; k < list1.size(); k++) {
            Entity entity = (Entity) list1.get(k);
            if (entity instanceof EntityWaterWitch || entity instanceof EntityWaterShoot || entity instanceof EntityWaterMinion || entity instanceof EntityGiantSquid || entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode) {

                ;

            } else {

                entity.motionY = 1;

            }

        }

        super.onLivingUpdate();
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        return false;
    }

}

