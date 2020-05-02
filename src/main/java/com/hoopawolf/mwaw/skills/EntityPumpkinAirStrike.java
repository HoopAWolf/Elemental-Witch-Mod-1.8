package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.particle.EntitySoulFX;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPumpkinAirStrike extends EntityMob {

    public EntityPumpkinAirStrike(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.5F, 0.5F);
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

        if (this.motionY <= -0.2D) {
            this.motionY = -0.5D;
        }

        if (this.onGround) {

            if (!worldObj.isRemote) {
                this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 2, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
                this.setDead();

            }
        }

        if (worldObj.isRemote) {
            for (int i = 0; i < 4; i++) {

                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySoulFX(worldObj, (posX + (double) (rand.nextFloat()
                        * width * 2.0F))
                        - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2));
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
        if (source.getEntity() instanceof EntityPumpkinAirStrike) {
            return true;
        }

        return false;
    }

}
