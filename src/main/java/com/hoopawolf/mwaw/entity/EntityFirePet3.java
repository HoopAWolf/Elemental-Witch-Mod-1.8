package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityFirePet3 extends EntityTameable {

    public static final float[][] rainbowColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    public int deathTicks;
    int skillTimer = 100;
    int skill2Timer = 300;

    public EntityFirePet3(World par1World) {
        super(par1World);
        setSize(3.7F, 3.4F);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.4D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.setTamed(true);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
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

    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            p_70612_1_ = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5F;
            p_70612_2_ = ((EntityLivingBase) this.riddenByEntity).moveForward;

            if (p_70612_2_ <= 0.0F) {
                p_70612_2_ *= 0.25F;
            }

            if (!this.worldObj.isRemote) {
                this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            this.stepHeight = 2.0F;
            this.jumpMovementFactor = 0.02F;
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (skillTimer != 0 && this.getAttackTarget() != null)
            --skillTimer;

        if (skill2Timer != 0 && this.getAttackTarget() != null)
            --skill2Timer;

        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);

                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                entitywitherskull1.motionY = -5;

                skillTimer = 100;


            }

            if (skill2Timer == 0) {

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

                        skill2Timer = 300;
                    }
                }

            }

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

                this.worldObj.spawnParticle(EnumParticleTypes.LAVA, (posX + (double) (rand.nextFloat()
                                * width * 2.0F))
                                - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2);
            }
        }
        if (worldObj.isRemote) {

            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 3.5, posZ, 0, 0, 0);

            }
        }

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }


        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(20D, 10D, 20D));

        if (this.deathTicks >= 0 && this.deathTicks <= 140) {
            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("fire_tornado_big", this);
                MoWitchAndWizard.proxy.spawnParticles("fire_tornado_big", this);
            }

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityFirePet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityFirePet2 || entity1 instanceof EntityFirePet) {

                    ;

                } else {

                    entity1.setFire(1000);

                }
            }
        }


        if (this.deathTicks == 140) {
            final EntityFireEgg mob = new EntityFireEgg(worldObj);
            mob.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityFirePet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityFirePet2 || entity1 instanceof EntityFirePet) {

                    ;

                } else {

                    if (!worldObj.isRemote)
                        this.worldObj.newExplosion(this, entity1.posX, entity1.posY - 0.5, entity1.posZ, 2, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));

                }
            }

            this.setDead();
        }

    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        Random rand = new Random();
        int form = rand.nextInt(100);

        if (form <= 18) {

            p_70652_1_.worldObj.newExplosion(this, this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 1, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
            return true;

        }
        p_70652_1_.setFire(10);
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
    }


    public EntityFirePet3 createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityTNTPrimed) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityFirePet3) {
                EntityFirePet3 entitywolf = (EntityFirePet3) p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse) p_142018_1_).isTame();
        } else {
            return false;
        }
    }

    public boolean interact(EntityPlayer p_70085_1_) {
        if (p_70085_1_.isSneaking()) {
            if (!this.isSitting()) {
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase) null);
                this.aiSit.setSitting(true);
            } else if (this.isSitting()) {
                this.aiSit.setSitting(false);
            }
        } else {

            if (this.isTamed()) {

                if (!this.isRiding()) {
                    p_70085_1_.mountEntity(this);

                }
            }

        }

        return super.interact(p_70085_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSkillTimerFire", skillTimer);
        compund.setInteger("PetSkill2TimerFire", skill2Timer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillTimerFire");
        skill2Timer = compund.getInteger("PetSkill2TimerFire");
        deathTicks = compund.getInteger("PetDeathTicks");

    }


}

