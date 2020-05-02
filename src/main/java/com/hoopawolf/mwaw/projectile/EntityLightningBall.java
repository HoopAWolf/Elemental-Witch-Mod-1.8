package com.hoopawolf.mwaw.projectile;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityLightningWitch;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import com.hoopawolf.mwaw.skills.EntityLightningGolem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLightningBall extends EntityMWAWProjectileHandler {
    public EntityLightningBall(final World par1World) {
        super(par1World);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityLightningBall(final World par1World,
                               final EntityLivingBase par2EntityLivingBase, final double par3,
                               final double par5, final double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public EntityLightningBall(final World par1World, final double par2,
                               final double par4, final double par6, final double par8,
                               final double par10, final double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by
     * the original motion.
     */
    @Override
    protected float getMotionFactor() {
        return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
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

                    if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {

                        if (par1MovingObjectPosition.entityHit instanceof EntityLightningGolem || par1MovingObjectPosition.entityHit instanceof EntityLightningWitch) {


                        } else {

                            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.magic, 3.0F);
                            ((EntityLivingBase) par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MWAWPotionRegistry.paralyzePotion.id, 20 * 2, 1));
                            this.setDead();

                        }
                    }
                }
            }
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

        if (this.ticksExisted >= 100) {

            this.setDead();
        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("lightning_shoot", this);
        }

        super.onUpdate();
    }

}
