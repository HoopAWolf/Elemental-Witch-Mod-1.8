package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public class EntityMiniTornado extends EntityMob {

    public EntityMiniTornado(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.1F, 0.1F);
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

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(1.0D, 1.0D, 1.0D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityLivingBase) {

                entitytargetcheck.attackEntityFrom(DamageSource.magic, 0.5F);

            }
        }

        if (ticksExisted >= 200) {

            this.setDead();

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("air_tornado_small", this);
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
