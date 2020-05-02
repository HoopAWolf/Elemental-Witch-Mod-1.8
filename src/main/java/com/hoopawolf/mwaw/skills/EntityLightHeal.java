package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityLightWitch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityLightHeal extends EntityMob {
    /**
     * Used to create the rotation animation when rendering the crystal.
     */
    public int innerRotation;

    public EntityLightHeal(World p_i1738_1_) {
        super(p_i1738_1_);
        this.setSize(2.0F, 2.0F);
        this.ignoreFrustumCheck = true;
        this.innerRotation = this.rand.nextInt(100000);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(100.0D);
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

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onLivingUpdate() {
        ++this.innerRotation;

        if (this.motionY <= -0.0D)
            this.motionY = +0.0D;

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("light_normal", this);
        }

        if (!this.worldObj.isRemote) {
            List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(5D, 100D, 5D));

            for (int j = 0; j < targetList.size(); j++) {
                Entity entity1 = (Entity) targetList.get(j);
                if (entity1 instanceof EntityLightWitch) {

                    this.setPositionAndUpdate(entity1.posX, entity1.posY + 8, entity1.posZ);

                }
            }
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    }

}