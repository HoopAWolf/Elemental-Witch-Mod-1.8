package com.hoopawolf.mwaw.projectile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPumpkinSkull extends EntityMWAWProjectileHandler {

    public EntityPumpkinSkull(World p_i1793_1_) {
        super(p_i1793_1_);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityPumpkinSkull(World p_i1794_1_, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_) {
        super(p_i1794_1_, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
        this.setSize(0.3125F, 0.3125F);
    }

    @SideOnly(Side.CLIENT)
    public EntityPumpkinSkull(World p_i1795_1_, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_) {
        super(p_i1795_1_, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor() {
        return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning() {
        return false;
    }

    public float getExplosionResistance(Explosion p_180428_1_, World worldIn, BlockPos p_180428_3_, IBlockState p_180428_4_) {
        float f = super.getExplosionResistance(p_180428_1_, worldIn, p_180428_3_, p_180428_4_);

        if (this.isInvulnerable() && p_180428_4_.getBlock() != Blocks.bedrock && p_180428_4_.getBlock() != Blocks.end_portal && p_180428_4_.getBlock() != Blocks.end_portal_frame && p_180428_4_.getBlock() != Blocks.command_block) {
            f = Math.min(0.8F, f);
        }

        return f;
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
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70227_1_) {
        if (!this.worldObj.isRemote) {
            if (p_70227_1_.entityHit != null) {
                if (this.shootingEntity != null) {
                    if (p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F) && !p_70227_1_.entityHit.isEntityAlive()) {
                        this.shootingEntity.heal(3.0F);
                    }
                } else {
                    p_70227_1_.entityHit.attackEntityFrom(DamageSource.magic, 3.0F);
                }

                if (p_70227_1_.entityHit instanceof EntityLivingBase) {
                    byte b0 = 0;

                    if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
                        b0 = 10;
                    } else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
                        b0 = 15;
                    }

                    if (b0 > 0) {
                        ((EntityLivingBase) p_70227_1_.entityHit).addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * b0, 1));
                    }
                }
            } else {
                boolean flag = true;
                if (this.shootingEntity != null && this.shootingEntity instanceof EntityLiving) {
                    flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
                }

                if (flag) {
                    BlockPos blockpos = p_70227_1_.getBlockPos().offset(p_70227_1_.sideHit);

                    if (this.worldObj.isAirBlock(blockpos)) {
                        this.worldObj.setBlockState(blockpos, Blocks.fire.getDefaultState());
                    }
                }
            }
            this.setDead();
        }

        if (worldObj.isRemote)
            this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);

    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        return false;
    }

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
    public void setInvulnerable(boolean p_82343_1_) {
        this.dataWatcher.updateObject(10, Byte.valueOf((byte) (p_82343_1_ ? 1 : 0)));
    }
}
