package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityDarkWitch;
import com.hoopawolf.mwaw.entity.EntityLightWitch;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

public class EntityDarkMark extends EntityMob {

    int timer = 10;

    public EntityDarkMark(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(2.0F, 2.0F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLightWitch.class, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
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

    protected String getLivingSound() {
        return "mwaw:laugh";
    }

    protected String getHurtSound() {
        return null;
    }

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (timer != 0 && this.getAttackTarget() != null)
            --timer;

        if (this.ticksExisted >= 400)
            this.setDead();

        if (this.motionY <= -0.0D)
            this.motionY = +0.0D;


        if (this.getHealth() <= 0)
            setDead();

        if (this.ticksExisted % 20 == 0) {

            List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(5D, 100D, 5D));
            for (int i = 0; i < targetList.size(); i++) {
                Entity entitytargetcheck = (Entity) targetList.get(i);
                if (entitytargetcheck instanceof EntityDarkWitch) {

                    ((EntityDarkWitch) entitytargetcheck).setHealth(((EntityDarkWitch) entitytargetcheck).getHealth() + 3);

                }
            }

            if (!this.worldObj.isRemote) {
                List targetList2 = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(5D, 100D, 5D));
                List targetList3 = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 20D, 20D));

                for (int j = 0; j < targetList2.size(); j++) {
                    Entity entity1 = (Entity) targetList2.get(j);
                    if (entity1 instanceof EntityDarkWitch) {

                        this.setPositionAndUpdate(entity1.posX, entity1.posY + 4, entity1.posZ);

                    }
                }

                for (int k = 0; k < targetList3.size(); k++) {
                    Entity entity2 = (Entity) targetList3.get(k);
                    if (entity2 instanceof EntityPlayer) {

                        ((EntityPlayer) entity2).addPotionEffect(new PotionEffect(
                                MWAWPotionRegistry.paranoidPotion.id, 20 * 200, 1));

                    }
                }
            }

            if (worldObj.isRemote) {

                MoWitchAndWizard.proxy.spawnParticles("dark_normal", this);

                for (int i = 0; i < 5; i++) {

                    double d = rand.nextGaussian() * 0.02D;
                    double d1 = rand.nextGaussian() * 0.02D;
                    double d2 = rand.nextGaussian() * 0.02D;
                    double d3 = 10D;


                    this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (posX + (double) (rand.nextFloat()
                                    * width * 2.0F))
                                    - (double) width - d * d3,
                            (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                            (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                    - (double) width - d2 * d3, d, d1, d2);

                    this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double) (rand.nextFloat()
                                    * width * 2.0F))
                                    - (double) width - d * d3,
                            (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                            (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                    - (double) width - d2 * d3, d, d1, d2);

                    this.worldObj.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, (posX + (double) (rand.nextFloat()
                                    * width * 2.0F))
                                    - (double) width - d * d3,
                            (posY + (double) (rand.nextFloat() * height)) - d1 * d3,
                            (posZ + (double) (rand.nextFloat() * width * 2.0F))
                                    - (double) width - d2 * d3, d, d1, d2);
                }
            }

            if (this.getAttackTarget() != null) {
                if (this.getAttackTarget() instanceof EntityMob) {
                    ((EntityMob) this.getAttackTarget()).setAttackTarget(this);
                }
            }

        }

        super.onLivingUpdate();
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);
    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        return super.attackEntityFrom(source, damage);
    }

}
