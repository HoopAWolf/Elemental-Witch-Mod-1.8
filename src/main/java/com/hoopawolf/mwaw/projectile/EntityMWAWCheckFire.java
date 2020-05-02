package com.hoopawolf.mwaw.projectile;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMWAWCheckFire extends EntityMWAWProjectileHandler {
    public EntityMWAWCheckFire(final World par1World) {
        super(par1World);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityMWAWCheckFire(final World par1World,
                               final EntityLivingBase par2EntityLivingBase, final double par3,
                               final double par5, final double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public EntityMWAWCheckFire(final World par1World, final double par2,
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

                    final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(worldObj);
                    final EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(worldObj);
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

                    entitywitherskull1.motionY = -5;
                    entitywitherskull2.motionY = -5;
                    entitywitherskull3.motionY = -5;
                    entitywitherskull4.motionY = -5;
                    entitywitherskull5.motionY = -5;
                    entitywitherskull6.motionY = -5;
                    entitywitherskull7.motionY = -5;
                    entitywitherskull8.motionY = -5;
                    entitywitherskull9.motionY = -5;

                } else {
                    int i = par1MovingObjectPosition.entityHit.chunkCoordX;
                    int j = par1MovingObjectPosition.entityHit.chunkCoordY;
                    int k = par1MovingObjectPosition.entityHit.chunkCoordZ;

                    switch (par1MovingObjectPosition.sideHit.getIndex()) {
                        case 0:
                            --j;
                            break;
                        case 1:
                            ++j;
                            break;
                        case 2:
                            --k;
                            break;
                        case 3:
                            ++k;
                            break;
                        case 4:
                            --i;
                            break;
                        case 5:
                            ++i;
                    }

                    if (this.worldObj.isAirBlock(new BlockPos(i, j, k))) {
                        final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(worldObj);
                        final EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(worldObj);

                        entitywitherskull1.setLocationAndAngles(i, j + 20, k, this.rotationYaw, 0);
                        entitywitherskull2.setLocationAndAngles(i, j + 20, k + 1, this.rotationYaw, 0);
                        entitywitherskull3.setLocationAndAngles(i, j + 20, k - 1, this.rotationYaw, 0);
                        entitywitherskull4.setLocationAndAngles(i + 1, j + 20, k, this.rotationYaw, 0);
                        entitywitherskull5.setLocationAndAngles(i - 1, j + 20, k, this.rotationYaw, 0);
                        entitywitherskull6.setLocationAndAngles(i - 1, j + 20, k + 1, this.rotationYaw, 0);
                        entitywitherskull7.setLocationAndAngles(i - 1, j + 20, k - 1, this.rotationYaw, 0);
                        entitywitherskull8.setLocationAndAngles(i + 1, j + 20, k + 1, this.rotationYaw, 0);
                        entitywitherskull9.setLocationAndAngles(i + 1, j + 20, k - 1, this.rotationYaw, 0);

                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull4);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull5);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull6);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull7);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull8);
                        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull9);

                        entitywitherskull1.motionY = -5;
                        entitywitherskull2.motionY = -5;
                        entitywitherskull3.motionY = -5;
                        entitywitherskull4.motionY = -5;
                        entitywitherskull5.motionY = -5;
                        entitywitherskull6.motionY = -5;
                        entitywitherskull7.motionY = -5;
                        entitywitherskull8.motionY = -5;
                        entitywitherskull9.motionY = -5;
                    }
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