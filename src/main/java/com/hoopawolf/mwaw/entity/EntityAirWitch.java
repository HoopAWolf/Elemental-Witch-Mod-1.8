package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityWindShoot;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityAirClone;
import com.hoopawolf.mwaw.skills.EntityMiniTornado;
import com.hoopawolf.mwaw.skills.EntityTornado;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityAirWitch extends EntityMob implements IRangedAttackMob {

    boolean summonTornado = false;
    int summonMiniTornado = 600;
    int summonWindClone = 300;

    public EntityAirWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        if (summonMiniTornado != 0 && this.getAttackTarget() != null) {
            --summonMiniTornado;
        }

        if (summonWindClone != 0 && this.getAttackTarget() != null) {
            --summonWindClone;
        }


        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        if (worldObj.isRemote) {
            for (int i = 0; i < 1; i++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double) (rand.nextFloat()
                                * width * 2.0F))
                                - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2);
            }
        }

        if (this.getAttackTarget() != null) {

            if (summonMiniTornado == 0) {

                final EntityMiniTornado entitywitherskull1 = new EntityMiniTornado(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                entitywitherskull1.mountEntity(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                summonMiniTornado = 600;

            }

            if (summonWindClone == 0) {

                final EntityAirClone entitywitherskull1 = new EntityAirClone(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                final EntityAirClone entitywitherskull2 = new EntityAirClone(worldObj);
                entitywitherskull2.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);

                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                entitywitherskull1.setHealth(this.getHealth());
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                entitywitherskull2.setAttackTarget(this.getAttackTarget());
                entitywitherskull2.setHealth(this.getHealth());
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);

                summonWindClone = 300;

            }

            if (this.getHealth() <= 30) {

                if (summonTornado == false) {

                    final EntityTornado entitywitherskull1 = new EntityTornado(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                    entitywitherskull1.mountEntity(this);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                    summonTornado = true;

                }

            }


        }

        super.onLivingUpdate();
    }

    public boolean shouldDismountInWater(Entity rider) {
        if (rider instanceof EntityTornado) {

            return false;

        }

        return true;
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

        final Random rand = new Random();
        final int randomNumber = rand.nextInt(4);


        final double d3 = this.func_82214_u(par1);
        final double d4 = this.func_82208_v(par1);
        final double d5 = this.func_82213_w(par1);
        final double d6 = par2 - d3;
        final double d7 = par4 - d4;
        final double d8 = par6 - d5;
        final EntityWindShoot entitywitherskull = new EntityWindShoot(
                this.worldObj, this, d6, d7, d8, randomNumber);

        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 20D, 20D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityTornado) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityMiniTornado) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setBoolean("WitchSummonTornado", summonTornado);
        compund.setInteger("WitchSummonMiniTornado", summonMiniTornado);
        compund.setInteger("WitchSummonWindClone", summonWindClone);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        summonTornado = compund.getBoolean("WitchSummonTornado");
        summonMiniTornado = compund.getInteger("WitchSummonMiniTornado");
        summonWindClone = compund.getInteger("WitchSummonWindClone");

    }

    protected Item getDropItem() {
        return MWAWItemRegistry.airshard;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityAirClone || source.getEntity() instanceof EntityMiniTornado || source.getEntity() instanceof EntityWindShoot) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
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

                        final EntityAirEgg mob = new EntityAirEgg(worldObj);
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
