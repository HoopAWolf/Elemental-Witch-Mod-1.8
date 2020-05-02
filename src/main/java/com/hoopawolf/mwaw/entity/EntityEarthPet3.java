package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityEarthPet3 extends EntityTameable {

    public static final float[][] rainbowColorTable = new float[][]{{1.0F, 1.0F, 1.0F}, {0.85F, 0.5F, 0.2F}, {0.7F, 0.3F, 0.85F}, {0.4F, 0.6F, 0.85F}, {0.9F, 0.9F, 0.2F}, {0.5F, 0.8F, 0.1F}, {0.95F, 0.5F, 0.65F}, {0.3F, 0.3F, 0.3F}, {0.6F, 0.6F, 0.6F}, {0.3F, 0.5F, 0.6F}, {0.5F, 0.25F, 0.7F}, {0.2F, 0.3F, 0.7F}, {0.4F, 0.3F, 0.2F}, {0.4F, 0.5F, 0.2F}, {0.6F, 0.2F, 0.2F}, {0.1F, 0.1F, 0.1F}};
    public int deathTicks;
    int skillTimer = 100;
    int skill2Timer = 300;

    public EntityEarthPet3(World par1World) {
        super(par1World);
        setSize(2.6F, 3.6F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.3D));
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

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (skillTimer != 0 && this.getAttackTarget() != null)
            --skillTimer;

        if (skill2Timer != 0 && this.getAttackTarget() != null)
            --skill2Timer;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }
        if (worldObj.isRemote) {
            if (this.isSitting()) {

                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 4.5, posZ, 0, 0, 0);

            }
        }

        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                final EntityEarthMinionGood entity = new EntityEarthMinionGood(worldObj);
                entity.setLocationAndAngles(this.getAttackTarget().posX + 1.5,
                        this.getAttackTarget().posY, this.getAttackTarget().posZ,
                        0, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entity);

                skillTimer = 100;
            }

            if (skill2Timer == 0) {

                if (this.getOwner() != null) {
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(MWAWPotionRegistry.earthstancePotion.getId(), 20 * 100, 1));
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.resistance.getId(), 20 * 100, 1));
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.absorption.getId(), 20 * 100, 1));
                    this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.healthBoost.getId(), 20 * 100, 1));
                    skill2Timer = 300;
                }
            }
        }

        if (worldObj.isRemote) {
            for (int i = 0; i < 1; i++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, (posX + (double) (rand.nextFloat()
                                * width * 2.0F))
                                - (double) width - d * d3,
                        (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                        (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                - (double) width - d2 * d3, d, d1, d2);
            }
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
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
                MoWitchAndWizard.proxy.spawnParticles("earth_big", this);
            }

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityEarthPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityEarthPet2 || entity1 instanceof EntityEarthPet) {

                    ;

                } else {
                    if (entity1 instanceof EntityLivingBase) {

                        ((EntityLivingBase) entity1).addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(), 2000 * 100, 1));
                        ((EntityLivingBase) entity1).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 2000 * 100, 1));
                        ((EntityLivingBase) entity1).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 2000 * 100, 1));
                        ((EntityLivingBase) entity1).addPotionEffect(new PotionEffect(Potion.poison.getId(), 2000 * 100, 1));

                    }
                }
            }
        }

        if (this.deathTicks == 20) {

            worldObj.setBlockState(new BlockPos((int) posX, (int) posY - 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY - 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY - 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY - 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY - 1, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY - 1, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY - 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY - 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY - 1, (int) posZ - 1), Blocks.stone.getDefaultState());

        }

        if (this.deathTicks == 60) {

            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY, (int) posZ - 1), Blocks.stone.getDefaultState());

        }

        if (this.deathTicks == 130) {

            worldObj.setBlockState(new BlockPos((int) posX, (int) posY + 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY + 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY + 1, (int) posZ), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY + 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX, (int) posY + 1, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY + 1, (int) posZ - 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY + 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX + 1, (int) posY + 1, (int) posZ + 1), Blocks.stone.getDefaultState());
            worldObj.setBlockState(new BlockPos((int) posX - 1, (int) posY + 1, (int) posZ - 1), Blocks.stone.getDefaultState());

        }

        if (this.deathTicks == 140) {
            final EntityEarthEgg mob = new EntityEarthEgg(worldObj);
            mob.setLocationAndAngles((int) posX, (int) posY, (int) posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityEarthPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityEarthPet2 || entity1 instanceof EntityEarthPet) {

                    ;

                } else {

                    if (entity1 instanceof EntityLivingBase) {

                        final EntityEarthMinionGood entity = new EntityEarthMinionGood(worldObj);

                        entity.setLocationAndAngles(entity1.posX,
                                entity1.posY, entity1.posZ,
                                0, 0);
                        entity.setAttackTarget((EntityLivingBase) entity1);
                        MWAWEntityUtil.spawnInWorld(worldObj, entity);

                    }
                }
            }

            if (this.getOwner() != null) {
                this.getOwnerEntity().addPotionEffect(new PotionEffect(MWAWPotionRegistry.earthstancePotion.getId(), 20 * 100, 1));
                this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.resistance.getId(), 20 * 100, 1));
                this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.absorption.getId(), 20 * 100, 1));
                this.getOwnerEntity().addPotionEffect(new PotionEffect(Potion.healthBoost.getId(), 20 * 100, 1));
            }

            this.setDead();
        }

    }

    public boolean canBePushed() {

        if (deathTicks > 0)
            return false;

        return true;

    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean isPushedByWater() {

        if (deathTicks > 0)
            return false;

        return true;
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        Random rand = new Random();
        int form = rand.nextInt(50);

        if (form <= 24) {

            ((EntityLivingBase) p_70652_1_).addPotionEffect(new PotionEffect(
                    Potion.moveSlowdown.id, 30, 1));
            p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
            return true;

        }
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.equals(DamageSource.inWall) || source.equals(DamageSource.fallingBlock)) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    public int getTotalArmorValue() {
        return 15;
    }

    public EntityEarthPet createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityEarthPet3) {
                EntityEarthPet3 entitywolf = (EntityEarthPet3) p_142018_1_;

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
        if (!p_70085_1_.isSneaking()) {
            if (!this.isSitting()) {
                this.navigator.clearPathEntity();
                this.setAttackTarget((EntityLivingBase) null);
                this.aiSit.setSitting(true);
            } else if (this.isSitting()) {
                this.aiSit.setSitting(false);
            }
        }

        return super.interact(p_70085_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSkillEarth", skillTimer);
        compund.setInteger("PetSkill2Earth", skill2Timer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillEarth");
        skill2Timer = compund.getInteger("PetSkill2Earth");
        deathTicks = compund.getInteger("PetDeathTicks");

    }

}
