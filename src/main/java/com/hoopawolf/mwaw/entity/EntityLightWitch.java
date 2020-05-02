package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityLightArrow;
import com.hoopawolf.mwaw.projectile.EntityLightShoot;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityLightHeal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityLightWitch extends EntityMob implements IRangedAttackMob {

    public int arrowTimer = 300;
    public int healTimer = 200;
    public int mirrorTimer = 600;
    public boolean lastStraw = false;
    public EntityLightHeal healingLight;

    public EntityLightWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        if (MWAWConfigHandler.DarkWitchVSLightWitch) {
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDarkWitch.class, true));
        }
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
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

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(10D, 20D, 10D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityLightHeal) {

                this.updateHealingCrystal();

            }
        }

        if (!worldObj.isRemote && this.getAttackTarget() != null && MWAWConfigHandler.LightWitchTurnDay)
            worldObj.setWorldTime(6000);


        if (this.rand.nextFloat() < 7.5E-4F)
            this.worldObj.setEntityState(this, (byte) 15);


        if (arrowTimer != 0 && this.getAttackTarget() != null)
            --arrowTimer;

        if (healTimer != 0 && this.getAttackTarget() != null)
            --healTimer;

        if (mirrorTimer != 0 && this.getAttackTarget() != null)
            --mirrorTimer;

        if (!lastStraw && this.getAttackTarget() != null && this.getHealth() <= 20)
            lastStraw = true;


        if (this.getAttackTarget() != null) {

            if (arrowTimer == 0) {

                for (int i = 0; i <= 60; i++) {

                    final EntityLightArrow entitywitherskull1 = new EntityLightArrow(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + 5 + this.rand.nextDouble() * 15.0D - 10.0D, this.posY + 40.0D, this.getAttackTarget().posZ + 5 + this.rand.nextDouble() * 15.0D - 10.0D, 0, 0);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    entitywitherskull1.motionY -= 1;

                    if (i >= 60)
                        arrowTimer = 300;
                }
            }

            if (healTimer == 0) {
                final EntityLightHeal entitywitherskull1 = new EntityLightHeal(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY + 6, this.posZ, 0, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                healTimer = 200;
            }

            if (lastStraw) {
                if (rand.nextInt(100) <= 40) {
                    for (int i = 0; i <= 60; i++) {
                        if (i / 10 == 0) {
                            final EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(worldObj, 0, 0, 0);
                            entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + 5 + this.rand.nextDouble() * 30.0D - 25.0D, this.getAttackTarget().posY, this.getAttackTarget().posZ + 5 + this.rand.nextDouble() * 30.0D - 25.0D, 0, 0);
                            MWAWEntityUtil.spawnWeather(worldObj, entitywitherskull1);

                        }
                        if (i / 6 == 0) {
                            final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);
                            entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + 5 + this.rand.nextDouble() * 30.0D - 25.0D, this.getAttackTarget().posY + 40.0D, this.getAttackTarget().posZ + 5 + this.rand.nextDouble() * 30.0D - 25.0D, 0, 0);
                            entitywitherskull1.motionY -= 4;
                            MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                        }
                    }
                }
            }

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("light_normal", this);
        }

        super.onLivingUpdate();
    }

    private void updateHealingCrystal() {
        if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 3.0F);
        }


        if (this.rand.nextInt(10) == 0) {
            float f = 10.0F;
            List list = this.worldObj.getEntitiesWithinAABB(EntityLightHeal.class, this.getBoundingBox().expand((double) f, (double) f, (double) f));
            EntityLightHeal entitylightheal = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLightHeal entitylightheal1 = (EntityLightHeal) iterator.next();
                double d1 = entitylightheal1.getDistanceSqToEntity(this);

                if (d1 < d0) {
                    d0 = d1;
                    entitylightheal = entitylightheal1;
                }
            }

            this.healingLight = entitylightheal;
        }
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

        final double d3 = this.func_82214_u(par1);
        final double d4 = this.func_82208_v(par1);
        final double d5 = this.func_82213_w(par1);
        final double d6 = par2 - d3;
        final double d7 = par4 - d4;
        final double d8 = par6 - d5;
        final EntityLightShoot entitywitherskull = new EntityLightShoot(
                this.worldObj, this, d6, d7, d8);

        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(2D, 2D, 2D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityLightHeal) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchLightHeal", healTimer);
        compund.setInteger("WitchLightArrow", arrowTimer);
        compund.setInteger("WitchLightMirror", mirrorTimer);
        compund.setBoolean("WitchLightLastStraw", lastStraw);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        healTimer = compund.getInteger("WitchLightHeal");
        arrowTimer = compund.getInteger("WitchLightArrow");
        mirrorTimer = compund.getInteger("WitchLightMirror");
        lastStraw = compund.getBoolean("WitchLightLastStraw");

    }

    protected void fall(float p_70069_1_) {
    }

    public void addPotionEffect(PotionEffect p_70690_1_) {
    }

    protected Item getDropItem() {
        return MWAWItemRegistry.lightshard;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source.getEntity() instanceof EntityLightShoot || source.getEntity() instanceof EntityLightArrow || source.getEntity() instanceof EntityLightningBolt || source.getEntity() instanceof EntityLargeFireball) {
            return false;
        }

        if (mirrorTimer == 0 && damage >= 0) {
            if (rand.nextInt(100) <= 50) {
                this.setHealth(getHealth() + damage);
            } else {
                this.getAttackTarget().setHealth(this.getAttackTarget().getHealth() - damage);
            }

            mirrorTimer = 600;
            return super.attackEntityFrom(source, 0);
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

                        final EntityLightEgg mob = new EntityLightEgg(worldObj);
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
