package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityDarkWitch;
import com.hoopawolf.mwaw.projectile.EntityDarkShoot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityEnderHole extends EntityMob {
    /**
     * Used to create the rotation animation when rendering the crystal.
     */
    public int innerRotation;

    public EntityEnderHole(World p_i1738_1_) {
        super(p_i1738_1_);
        this.setSize(2.0F, 2.0F);
        this.ignoreFrustumCheck = true;
        this.innerRotation = this.rand.nextInt(100000);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
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

    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {

        if (!this.onGround && this.motionY <= 0.0D)
            motionY *= 0.0D;

        ++this.innerRotation;

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(10D, 10D, 10D));
        for (int j = 0; j < list.size(); j++) {
            Entity entity1 = (Entity) list.get(j);
            if (entity1 instanceof EntityDarkMark || entity1 instanceof EntityDarkWitch || entity1 instanceof EntityDarkShoot || entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityDarkSummoner || entity1 instanceof EntityEnderHole) {

                ;

            } else {

                double xx = entity1.posX - this.posX;
                double zz = entity1.posZ - this.posZ;

                entity1.motionX = (-xx / (16.0F * entity1.getDistanceToEntity(this)));
                entity1.motionZ = (-zz / (16.0F * entity1.getDistanceToEntity(this)));

            }
        }

        if (!this.worldObj.isRemote && ticksExisted >= 200) {
            this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 3, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            this.setDead();
        }

        if (worldObj.isRemote) {

            MoWitchAndWizard.proxy.spawnParticles("dark_normal", this);

            for (int i = 0; i < 10; i++) {

                this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        this.posY + this.rand.nextDouble() * (double) this.height - 0.25D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);

            }
        }

        super.onUpdate();
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
        return false;
    }

}
