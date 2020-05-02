package com.hoopawolf.mwaw.projectile;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMWAWCheckLight extends EntityMWAWProjectileHandler {
    public EntityMWAWCheckLight(final World par1World) {
        super(par1World);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityMWAWCheckLight(final World par1World,
                                final EntityLivingBase par2EntityLivingBase, final double par3,
                                final double par5, final double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public EntityMWAWCheckLight(final World par1World, final double par2,
                                final double par4, final double par6, final double par8,
                                final double par10, final double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor() {
        return 100.95F;
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire
     * effect on rendering.
     */
    @Override
    public boolean isBurning() {
        return false;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(final MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote) {
            if (par1MovingObjectPosition.entityHit != null) {
                if (this.shootingEntity != null) {

                    final EntityLightArrow entitywitherskull1 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull2 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull3 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull4 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull5 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull6 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull7 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull8 = new EntityLightArrow(worldObj);
                    final EntityLightArrow entitywitherskull9 = new EntityLightArrow(worldObj);
                    entitywitherskull1.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ, this.rotationYaw, 0);
                    entitywitherskull2.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ + 1, this.rotationYaw, 0);
                    entitywitherskull3.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ - 1, this.rotationYaw, 0);
                    entitywitherskull4.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX + 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ, this.rotationYaw, 0);
                    entitywitherskull5.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX - 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ, this.rotationYaw, 0);
                    entitywitherskull6.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX - 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ + 1, this.rotationYaw, 0);
                    entitywitherskull7.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX - 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ - 1, this.rotationYaw, 0);
                    entitywitherskull8.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX + 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ + 1, this.rotationYaw, 0);
                    entitywitherskull9.setLocationAndAngles(par1MovingObjectPosition.entityHit.posX + 1, par1MovingObjectPosition.entityHit.posY + 20, par1MovingObjectPosition.entityHit.posZ - 1, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull4);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull5);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull6);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull7);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull8);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull9);

                    entitywitherskull1.motionY = -1;
                    entitywitherskull2.motionY = -1;
                    entitywitherskull3.motionY = -1;
                    entitywitherskull4.motionY = -1;
                    entitywitherskull5.motionY = -1;
                    entitywitherskull6.motionY = -1;
                    entitywitherskull7.motionY = -1;
                    entitywitherskull8.motionY = -1;
                    entitywitherskull9.motionY = -1;

                }
            }

            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through
     * this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(final DamageSource par1DamageSource,
                                    final float par2) {
        return false;
    }


    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(10, Byte.valueOf((byte) 0));
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isInvulnerable() {
        return this.dataWatcher.getWatchableObjectByte(10) == 1;
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setInvulnerable(final boolean par1) {
        this.dataWatcher.updateObject(10, Byte.valueOf((byte) (par1 ? 1 : 0)));
    }

    @Override
    public void onUpdate() {

        if (this.ticksExisted >= 3) {

            this.setDead();

        }
        super.onUpdate();
    }

}