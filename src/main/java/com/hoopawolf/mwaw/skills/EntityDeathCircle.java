package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityDarkWitch;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityDeathCircle extends EntityMob {

    public EntityDeathCircle(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.1F, 0.1F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Integer.MAX_VALUE);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(100.0D);
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
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity p_82167_1_) {
    }

    protected void collideWithNearbyEntities() {
    }

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("dark_circle", this);
        }

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(8.0D, 1.0D, 8.0D));

        for (int i = 0; i < targetList.size(); i++) {

            Entity entitytargetcheck = (Entity) targetList.get(i);

            if (entitytargetcheck instanceof EntityLivingBase) {

                if (entitytargetcheck instanceof EntityDarkWitch) {
                    ;
                } else {

                    entitytargetcheck.attackEntityFrom(DamageSource.magic, Math.min(MathHelper.floor_float(2F), 60));

                    ((EntityLivingBase) entitytargetcheck).addPotionEffect(new PotionEffect(
                            Potion.moveSlowdown.id, 20 * 2, 1));

                    ((EntityLivingBase) entitytargetcheck).addPotionEffect(new PotionEffect(
                            MWAWPotionRegistry.paranoidPotion.id, 20 * 200, 1));

                    if (rand.nextInt(50) <= 20) {
                        if (rand.nextInt(10) == 1)
                            entitytargetcheck.playSound("mob.enderdragon.growl", 2.0F, .6F);
                        if (rand.nextInt(10) == 2)
                            entitytargetcheck.playSound("mob.spider.say", 2.0F, .6F);
                        if (rand.nextInt(10) == 3)
                            entitytargetcheck.playSound("mob.spider.death", 2.0F, .6F);
                        if (rand.nextInt(10) == 4)
                            entitytargetcheck.playSound("mob.zombie.say", 2.0F, .6F);
                        if (rand.nextInt(10) == 5)
                            entitytargetcheck.playSound("mob.zombie.hurt", 2.0F, .6F);
                        if (rand.nextInt(10) == 6)
                            entitytargetcheck.playSound("mob.zombie.death", 2.0F, .6F);
                        if (rand.nextInt(10) == 7)
                            entitytargetcheck.playSound("creeper.primed", 2.0F, .6F);
                    }
                }
            }
        }

        super.onLivingUpdate();
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        return false;
    }

}
