package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityChristmasShoot;
import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.skills.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityChristmasWitch extends EntityMob implements IRangedAttackMob {

    int summonChristmas = 600;
    int summonChristmasToy = 300;
    int summonPresent = 400;
    boolean deathSummon = false;
    boolean summoned = false;

    public EntityChristmasWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
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

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (summonChristmas != 0 && this.getAttackTarget() != null) {
            --summonChristmas;
        }

        if (summonChristmasToy != 0 && this.getAttackTarget() != null) {
            --summonChristmasToy;
        }

        if (summonPresent != 0) {
            --summonPresent;
        }

        if (this.getHealth() < 20 && !deathSummon) {

            this.deathSummon = true;

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("christmas_normal", this);
        }

        if (summonPresent == 0) {
            if (worldObj.getBlockState(new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ)).getBlock() == Blocks.air) {
                EntityBlock block = new EntityBlock(worldObj, 0, 0, 0, MWAWBlockRegistry.present.getDefaultState());
                block.setPosition(posX, posY, posZ);

                MWAWEntityUtil.spawnInWorld(worldObj, block);

                summonPresent = 400;
            }
        }

        if (this.getAttackTarget() != null) {

            if (deathSummon && !summoned) {

                EntityMinecartEmpty cart = new EntityMinecartEmpty(worldObj);
                cart.setPosition(posX, posY, posZ);


                EntityBat bat = new EntityBat(worldObj);
                bat.setPosition(posX, posY, posZ);
                bat.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 100 * 200, 1));
                bat.setAbsorptionAmount(1000);
                bat.setCustomNameTag("Merry Christmas");

                for (int i = 1; i <= 3; i++) {
                    EntityBigSnowGolem golem = new EntityBigSnowGolem(worldObj);
                    golem.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                    golem.setAttackTarget(this.getAttackTarget());
                    MWAWEntityUtil.spawnInWorld(worldObj, golem);
                }

                MWAWEntityUtil.spawnInWorld(worldObj, cart);
                MWAWEntityUtil.spawnInWorld(worldObj, bat);

                this.mountEntity(cart);
                cart.mountEntity(bat);

                summoned = true;
            }

            if (summonChristmasToy == 0) {


                EntitySword sword = new EntitySword(worldObj);
                sword.setPosition(posX, posY, posZ);
                sword.setAttackTarget(this.getAttackTarget());

                EntityRifle rifle = new EntityRifle(worldObj);
                rifle.setPosition(posX, posY, posZ);
                rifle.setAttackTarget(this.getAttackTarget());

                MWAWEntityUtil.spawnInWorld(worldObj, sword);
                MWAWEntityUtil.spawnInWorld(worldObj, rifle);


                summonChristmasToy = 300;
            }

            if (summonChristmas == 0 && this.getHealth() > 20) {

                EntityBigSnowGolem golem = new EntityBigSnowGolem(worldObj);
                golem.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                golem.setAttackTarget(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, golem);

                this.mountEntity(golem);

                summonChristmas = 600;

            }

        }

        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        super.onLivingUpdate();
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

        EntityChristmasShoot entitywitherskull = new EntityChristmasShoot(
                this.worldObj, this, d6, d7, d8);
        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);


    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 100D, 20D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityBat) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityMinecartEmpty) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntitySword) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityRifle) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityBigSnowGolem) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityIcicle || source.getEntity() instanceof EntityTNTPrimed || source.getEntity() instanceof EntityRifle || source.getEntity() instanceof EntityBlock) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchSummonChristmas", summonChristmas);
        compund.setInteger("WitchSummonChristmasToy", summonChristmasToy);
        compund.setInteger("WitchSummonPresent", summonPresent);
        compund.setBoolean("WitchDeathSummon", deathSummon);
        compund.setBoolean("WitchDeathSummoned", summoned);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        summonChristmas = compund.getInteger("WitchSummonChristmas");
        summonChristmasToy = compund.getInteger("WitchSummonChristmasToy");
        summonPresent = compund.getInteger("WitchSummonPresent");
        deathSummon = compund.getBoolean("WitchDeathSummon");
        summoned = compund.getBoolean("WitchDeathSummoned");

    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

}
