package com.hoopawolf.mwaw.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEarthMinion extends EntityMob {


    public EntityEarthMinion(World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
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
         /*   if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getCurrentEquippedItem().getItem() == Items.diamond_pickaxe) {

                return super.attackEntityFrom(DamageSource.generic, 8);

            } else if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getCurrentEquippedItem().getItem() == Items.golden_pickaxe) {

                return super.attackEntityFrom(DamageSource.generic, 6);

            } else if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getCurrentEquippedItem().getItem() == Items.iron_pickaxe) {

                return super.attackEntityFrom(DamageSource.generic, 4);

            } else if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getCurrentEquippedItem().getItem() == Items.stone_pickaxe) {

                return super.attackEntityFrom(DamageSource.generic, 2);

            } else*/
            if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getHeldItem().getItem() instanceof ItemPickaxe) {

                return super.attackEntityFrom(source, damage);

            }
        }

        return false;
    }


    public void onLivingUpdate() {

        super.onLivingUpdate();
    }


}
