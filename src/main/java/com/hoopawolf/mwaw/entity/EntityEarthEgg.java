package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

public class EntityEarthEgg extends EntityTameable {

    public int TimerToHatch = 1000;

    public EntityEarthEgg(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.7F, 0.9F);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAILookIdle(this));
        this.setTamed(true);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
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
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (TimerToHatch == 0) {

            final EntityEarthPet mob = new EntityEarthPet(worldObj);
            mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setTamed(true);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setAlwaysRenderNameTag(true);

            this.setDead();
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
        if (!worldObj.isRemote) {
            if (this.ticksExisted % 20 == 0 && this.worldObj.getBlockState(new BlockPos((int) posX, (int) posY - 1, (int) posZ)).getBlock() == Blocks.hay_block) {

                --TimerToHatch;
                --TimerToHatch;
            } else if (this.ticksExisted % 20 == 0 && this.worldObj.getBlockState(new BlockPos((int) posX, (int) posY - 1, (int) posZ)).getBlock() != Blocks.hay_block) {
                --TimerToHatch;
            }
        }

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 20D, 20D));

        for (int i = 0; i < list.size(); i++) {
            Entity entity1 = (Entity) list.get(i);

            if (entity1 instanceof EntityZombie || entity1 instanceof EntitySpider || entity1 instanceof EntityCaveSpider || entity1 instanceof EntitySkeleton || entity1 instanceof EntityCreeper) {

                ((EntityMob) entity1).setAttackTarget(this);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("TimerToHatch", TimerToHatch);
    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        TimerToHatch = compund.getInteger("TimerToHatch");
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    protected void fall(float p_70069_1_) {
    }

    public EntityEarthEgg createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean interact(EntityPlayer p_70085_1_) {

        if (p_70085_1_.capabilities.isCreativeMode) {
            if (p_70085_1_.getHeldItem() != null && p_70085_1_.getHeldItem().getItem() == MWAWItemRegistry.earthshard)
                TimerToHatch = 3;
        }


        if (this.isTamed()) {

            if (!this.isRiding()) {
                this.mountEntity(p_70085_1_);

            } else if (this.isRiding()) {
                this.mountEntity(null);

            }
        }
        return super.interact(p_70085_1_);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.equals(DamageSource.inWall) || source.equals(DamageSource.fallingBlock)) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

}