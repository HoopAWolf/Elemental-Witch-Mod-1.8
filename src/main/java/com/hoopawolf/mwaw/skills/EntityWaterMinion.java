package com.hoopawolf.mwaw.skills;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWaterMinion extends EntityMob {


    public EntityWaterMinion(World par1World) {
        super(par1World);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 0.2D));
        this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.2D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.2D, 32F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source.isFireDamage()) {
            this.playSound("random.fizz", 1, 1);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX, posY, posZ, 0, 0.5, 0);
            this.setHealth(this.getHealth() - 1);
        }
        return false;
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        worldObj.setBlockState(new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), Blocks.water.getDefaultState());
        return true;
    }

    public void onLivingUpdate() {

        if (this.getHealth() <= 0)
            this.setDead();

        if (isBurning())
            this.extinguish();


        if (this.isWet())
            this.setHealth(this.getHealth() + 1);

        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getBiomeGenForCoords(new BlockPos(i, j, k)).getFloatTemperature(new BlockPos(i, j, k)) > 1.0F) {
            this.playSound("random.fizz", 1, 1);
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX, posY, posZ, 0, 0.5, 0);
            this.setHealth(this.getHealth() - 0.5F);
        }


        super.onLivingUpdate();
    }


}

