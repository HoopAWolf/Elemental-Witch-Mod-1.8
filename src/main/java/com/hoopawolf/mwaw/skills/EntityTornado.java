package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityAirWitch;
import com.hoopawolf.mwaw.projectile.EntityWindShoot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityTornado extends EntityMob {

    public EntityTornado(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.1F, 0.1F);
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

        if (this.motionY <= -0.2D) {

            this.motionY = -0.5D;

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("air_tornado_big", this);
        }

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(20D, 20D, 20D));
        for (int j = 0; j < list.size(); j++) {
            Entity entity1 = (Entity) list.get(j);
            if (entity1 instanceof EntityAirWitch || entity1 instanceof EntityWindShoot || entity1 instanceof EntityAirClone || entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode) {

                ;

            } else {

                double xx = entity1.posX - this.posX;
                double zz = entity1.posZ - this.posZ;

                entity1.motionX = (-xx / (9.0F * entity1.getDistanceToEntity(this)));
                entity1.motionZ = (-zz / (9.0F * entity1.getDistanceToEntity(this)));

            }

            List list1 = worldObj.getEntitiesWithinAABBExcludingEntity(
                    this,
                    this.getBoundingBox().expand(2D, 2D, 2D));
            for (int k = 0; k < list1.size(); k++) {
                Entity entity = (Entity) list1.get(k);
                if (entity instanceof EntityAirWitch || entity instanceof EntityWindShoot || entity instanceof EntityAirClone || entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode) {

                    ;

                } else {

                    entity.motionY = 3;

                }

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