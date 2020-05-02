package com.hoopawolf.mwaw.entity;


import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityFireBat;
import com.hoopawolf.mwaw.skills.EntityMeteorite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityFireWitch extends EntityMob implements IRangedAttackMob {

    int summonLava = 600;
    int summonFireRain = 200;
    int summonFireBat = 300;
    boolean deathSummon = false;
    boolean summoned = false;

    public EntityFireWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        if (MWAWConfigHandler.FireWitchVSWaterWitch) {
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWaterWitch.class, true));
        }
        if (MWAWConfigHandler.ElementalsVSWitches) {
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWitch.class, true));
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.witch.idle";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.witch.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.witch.death";
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (summonLava != 0 && this.getAttackTarget() != null) {
            --summonLava;
        }

        if (summonFireBat != 0 && this.getAttackTarget() != null) {
            --summonFireBat;
        }

        if (summonFireRain != 0 && this.getAttackTarget() != null) {
            --summonFireRain;
        }

        if (this.getHealth() < 30) {

            this.deathSummon = true;

        }

        if (worldObj.isRemote) {
            for (int i = 0; i < 1; i++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                this.worldObj.spawnParticle(EnumParticleTypes.FLAME, (posX + (double) (rand.nextFloat()
                                * width * 2.0F))
                                - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2);
            }
        }

        if (this.getAttackTarget() != null) {

            if (summonFireBat == 0) {

                final EntityFireBat entitywitherskull1 = new EntityFireBat(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                final EntityFireBat entitywitherskull2 = new EntityFireBat(worldObj);
                entitywitherskull2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                entitywitherskull2.setAttackTarget(this.getAttackTarget());
                final EntityFireBat entitywitherskull3 = new EntityFireBat(worldObj);
                entitywitherskull3.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);
                entitywitherskull3.setAttackTarget(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);

                summonFireBat = 300;

            }

            if (deathSummon == true && summoned == false) {

                final EntityMeteorite entitywitherskull6 = new EntityMeteorite(worldObj);
                entitywitherskull6.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 99, this.getAttackTarget().posZ, this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull6);

                for (int i = 0; i < 10; i++) {

                    final EntityFireBat entitywitherskull1 = new EntityFireBat(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                }

                summoned = true;

            }

            if (summonFireRain == 0) {

                final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(worldObj);
                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull2.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull3.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                entitywitherskull4.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull5.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull6.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull7.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                entitywitherskull8.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull9.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);

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
                summonFireRain = 200;
            }
            if (summonLava == 0) {

                if (this.getAttackTarget() instanceof EntityLivingBase) {

                    if (worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.air && worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.lava && worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.flowing_lava) {

                        if (!this.getAttackTarget().isInWater()) {
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());

                        }
                    }
                }
                summonLava = 600;
            }
        }

        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        super.onLivingUpdate();
    }

    private double func_82214_u(final int par1) {
        if (par1 <= 0) {
            return this.posX;
        } else {
            final float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F
                    * (float) Math.PI;
            final float f1 = MathHelper.cos(f);
            return this.posX + f1 * 1.3D;
        }
    }

    private double func_82208_v(final int par1) {
        return par1 <= 0 ? this.posY + 0.5 : this.posY + 0.5;
    }

    private double func_82213_w(final int par1) {
        if (par1 <= 0) {
            return this.posZ;
        } else {
            final float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F
                    * (float) Math.PI;
            final float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }


    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
        for (int i = 0; i < 10; i++) {

            this.func_82216_a(0, p_82196_1_);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1009, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);

        }
    }

    private void func_82216_a(final int par1,
                              final EntityLivingBase par2EntityLivingBase) {
        this.func_82209_a(par1, par2EntityLivingBase.posX,
                par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight()
                        * 0.5D, par2EntityLivingBase.posZ, par1 == 0
                        && this.rand.nextFloat() < 0.001F);
    }

    private void func_82209_a(final int par1, final double par2,
                              final double par4, final double par6, final boolean par8) {

        final double d3 = this.func_82214_u(par1);
        final double d4 = this.func_82208_v(par1);
        final double d5 = this.func_82213_w(par1);
        final double d6 = par2 - d3;
        final double d7 = par4 - d4;
        final double d8 = par6 - d5;
        final EntitySmallFireball entitywitherskull = new EntitySmallFireball(
                this.worldObj, this, d6, d7, d8);

        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 100D, 20D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityMeteorite) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityFireBat) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityMeteorite || source.getEntity() instanceof EntitySmallFireball) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchSummonLava", summonLava);
        compund.setInteger("WitchSummonFireBat", summonFireBat);
        compund.setInteger("WitchSummonFireRain", summonFireRain);
        compund.setBoolean("WitchDeathSummonFire", deathSummon);
        compund.setBoolean("WitchDeathSummonedFire", summoned);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        summonLava = compund.getInteger("WitchSummonLava");
        summonFireRain = compund.getInteger("WitchSummonFireRain");
        summonFireBat = compund.getInteger("WitchSummonFireBat");
        deathSummon = compund.getBoolean("WitchDeathSummonFire");
        summoned = compund.getBoolean("WitchDeathSummonedFire");

    }

    protected Item getDropItem() {
        return MWAWItemRegistry.fireshard;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean interact(EntityPlayer p_70085_1_) {

        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (itemstack != null) {

            if (itemstack != null) {

                if (itemstack.getItem() == MWAWItemRegistry.emptyegg) {

                    if (this.getHealth() <= 13 && rand.nextInt(1000) <= 10) {
                        itemstack.stackSize--;

                        final EntityFireEgg mob = new EntityFireEgg(worldObj);
                        mob.setLocationAndAngles(posX, posY, posZ, p_70085_1_.rotationYaw, 0);
                        MWAWEntityUtil.spawnInWorld(worldObj, mob);
                        mob.setOwnerId(p_70085_1_.getUniqueID().toString());
                        mob.setCustomNameTag("Owner: " + p_70085_1_.getDisplayName());
                        mob.setAlwaysRenderNameTag(true);

                        this.setDead();
                    }
                }
            }
        }

        return super.interact(p_70085_1_);
    }

}
