package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityLightningWitch;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLightningGolem extends EntityMob {


    public EntityLightningGolem(World par1World) {
        super(par1World);
        this.setSize(1.1F, 2.9F);
        this.isImmuneToFire = true;
        this.tasks.addTask(0, new EntityAISwimming(this));
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
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(5.0D);
    }


    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mwaw:lightningsound";
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityLightningBolt || source.getEntity() instanceof EntityLightningWitch) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    public void onLivingUpdate() {

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("lightning_normal", this);
        }

        super.onLivingUpdate();
    }


}