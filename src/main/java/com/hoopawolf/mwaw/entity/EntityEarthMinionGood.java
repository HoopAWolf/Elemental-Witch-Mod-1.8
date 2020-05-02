package com.hoopawolf.mwaw.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEarthMinionGood extends EntityCreature {


    public EntityEarthMinionGood(World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 0.2D));
        this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.2D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.2D, 32F));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityCreeper.class, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntitySlime.class, true));

    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "dig.stone";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.zombie.woodbreak";
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source.getEntity() instanceof EntityPlayer) {
            if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getHeldItem().getItem() instanceof ItemPickaxe) {

                return super.attackEntityFrom(source, damage);

            }
        }
        return false;
    }

    protected void collideWithEntity(Entity p_82167_1_) {
        if (p_82167_1_ instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((EntityLivingBase) p_82167_1_);
        }

        super.collideWithEntity(p_82167_1_);
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        this.worldObj.setEntityState(this, (byte) 4);
        boolean flag = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2);

        return flag;
    }

    public void onLivingUpdate() {

        if (this.getAttackTarget() instanceof EntityEarthMinionGood) {

            this.setAttackTarget(null);
        }

        if (this.ticksExisted >= 330) {
            this.setDead();
        }

        super.onLivingUpdate();
    }


}
