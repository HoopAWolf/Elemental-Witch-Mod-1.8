package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityLightArrow;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class EntitySandWitch extends EntityMob {

    int summon600 = 150;
    int summon200 = 70;
    int summon300 = 120;

    public EntitySandWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
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

        if (summon600 != 0 && this.getAttackTarget() != null) {
            --summon600;
        }

        if (summon300 != 0 && this.getAttackTarget() != null) {
            --summon300;
        }

        if (summon200 != 0 && this.getAttackTarget() != null) {
            --summon200;
        }

        if (this.getAttackTarget() != null) {

            if (summon300 == 0) {

                /////////////////////////FIRE/////////////////////////////
                final EntityFireBat firebat1 = new EntityFireBat(worldObj);
                firebat1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                firebat1.setAttackTarget(this.getAttackTarget());
                final EntityFireBat firebat2 = new EntityFireBat(worldObj);
                firebat2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                firebat2.setAttackTarget(this.getAttackTarget());
                final EntityFireBat firebat3 = new EntityFireBat(worldObj);
                firebat3.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);
                firebat3.setAttackTarget(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, firebat1);
                MWAWEntityUtil.spawnInWorld(worldObj, firebat2);
                MWAWEntityUtil.spawnInWorld(worldObj, firebat3);


                /////////////////////////EARTH/////////////////////////////
                int xx = (int) Math.floor(this.getAttackTarget().posX);
                int yy = (int) Math.floor(this.getAttackTarget().posY);
                int zz = (int) Math.floor(this.getAttackTarget().posZ);

                final EntityBlock block1 = new EntityBlock(
                        this.worldObj, xx, yy + 3, zz, Blocks.stone.getDefaultState());
                final EntityBlock block2 = new EntityBlock(
                        this.worldObj, xx, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block3 = new EntityBlock(
                        this.worldObj, xx, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block4 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 3, zz, Blocks.stone.getDefaultState());
                final EntityBlock block5 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 3, zz, Blocks.stone.getDefaultState());
                final EntityBlock block6 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block7 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block8 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block9 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                this.worldObj.spawnEntityInWorld(block1);
                this.worldObj.spawnEntityInWorld(block2);
                this.worldObj.spawnEntityInWorld(block3);
                this.worldObj.spawnEntityInWorld(block4);
                this.worldObj.spawnEntityInWorld(block5);
                this.worldObj.spawnEntityInWorld(block6);
                this.worldObj.spawnEntityInWorld(block7);
                this.worldObj.spawnEntityInWorld(block8);
                this.worldObj.spawnEntityInWorld(block9);

                final EntityBlock block10 = new EntityBlock(
                        this.worldObj, xx, yy + 4, zz, Blocks.stone.getDefaultState());
                final EntityBlock block11 = new EntityBlock(
                        this.worldObj, xx, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block12 = new EntityBlock(
                        this.worldObj, xx, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block13 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 4, zz, Blocks.stone.getDefaultState());
                final EntityBlock block14 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 4, zz, Blocks.stone.getDefaultState());
                final EntityBlock block15 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block16 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block17 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block18 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                this.worldObj.spawnEntityInWorld(block10);
                this.worldObj.spawnEntityInWorld(block11);
                this.worldObj.spawnEntityInWorld(block12);
                this.worldObj.spawnEntityInWorld(block13);
                this.worldObj.spawnEntityInWorld(block14);
                this.worldObj.spawnEntityInWorld(block15);
                this.worldObj.spawnEntityInWorld(block16);
                this.worldObj.spawnEntityInWorld(block17);
                this.worldObj.spawnEntityInWorld(block18);

                final EntityBlock block19 = new EntityBlock(
                        this.worldObj, xx, yy + 5, zz, Blocks.stone.getDefaultState());
                final EntityBlock block20 = new EntityBlock(
                        this.worldObj, xx, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block21 = new EntityBlock(
                        this.worldObj, xx, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block22 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 5, zz, Blocks.stone.getDefaultState());
                final EntityBlock block23 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 5, zz, Blocks.stone.getDefaultState());
                final EntityBlock block24 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block25 = new EntityBlock(
                        this.worldObj, xx - 1, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                final EntityBlock block26 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                final EntityBlock block27 = new EntityBlock(
                        this.worldObj, xx + 1, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                this.worldObj.spawnEntityInWorld(block19);
                this.worldObj.spawnEntityInWorld(block20);
                this.worldObj.spawnEntityInWorld(block21);
                this.worldObj.spawnEntityInWorld(block22);
                this.worldObj.spawnEntityInWorld(block23);
                this.worldObj.spawnEntityInWorld(block24);
                this.worldObj.spawnEntityInWorld(block25);
                this.worldObj.spawnEntityInWorld(block26);
                this.worldObj.spawnEntityInWorld(block27);


                ///////////////////////////////WIND////////////////////////////
                final EntityAirClone airClone1 = new EntityAirClone(worldObj);
                airClone1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                final EntityAirClone airClone2 = new EntityAirClone(worldObj);
                airClone2.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);

                airClone1.setAttackTarget(this.getAttackTarget());
                airClone1.setHealth(this.getHealth());
                MWAWEntityUtil.spawnInWorld(worldObj, airClone1);

                airClone2.setAttackTarget(this.getAttackTarget());
                airClone2.setHealth(this.getHealth());
                MWAWEntityUtil.spawnInWorld(worldObj, airClone2);


                ///////////////////////////////LIGHTNING//////////////////////////////////
                final EntityLightningGolem lightningGolem1 = new EntityLightningGolem(worldObj);
                lightningGolem1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                lightningGolem1.setAttackTarget(this.getAttackTarget());
                final EntityLightningGolem lightningGolem2 = new EntityLightningGolem(worldObj);
                lightningGolem2.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);
                lightningGolem2.setAttackTarget(this.getAttackTarget());

                final EntityLightningBolt lightningBolt1 = new EntityLightningBolt(worldObj, 1, 1, 1);
                lightningBolt1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1, this.rotationYaw, 0);
                final EntityLightningBolt lightningBolt2 = new EntityLightningBolt(worldObj, 1, 1, 1);
                lightningBolt2.setLocationAndAngles(this.posX, this.posY, this.posZ - 1, this.rotationYaw, 0);

                MWAWEntityUtil.spawnInWorld(worldObj, lightningGolem1);
                MWAWEntityUtil.spawnInWorld(worldObj, lightningGolem2);
                MWAWEntityUtil.spawnWeather(worldObj, lightningBolt1);
                MWAWEntityUtil.spawnWeather(worldObj, lightningBolt2);


                ////////////////////////////LIGHT/////////////////////////
                for (int i = 0; i <= 60; i++) {
                    final EntityLightArrow entitywitherskull1 = new EntityLightArrow(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + 5 + this.rand.nextDouble() * 15.0D - 10.0D, this.posY + 40.0D, this.getAttackTarget().posZ + 5 + this.rand.nextDouble() * 15.0D - 10.0D, 0, 0);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    entitywitherskull1.motionY -= 1;
                }

                summon300 = 120;

            }

            if (summon200 == 0) {

                ////////////////////////////////FIRE//////////////////////////////////
                final EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(worldObj);
                final EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(worldObj);
                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull2.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull3.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                entitywitherskull4.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull5.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull6.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull7.setLocationAndAngles(this.getAttackTarget().posX - 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);
                entitywitherskull8.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ + 1, this.rotationYaw, 0);
                entitywitherskull9.setLocationAndAngles(this.getAttackTarget().posX + 1, this.getAttackTarget().posY + 20, this.getAttackTarget().posZ - 1, this.rotationYaw, 0);

                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull4);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull5);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull6);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull7);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull8);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull9);

                entitywitherskull1.motionY = -5;
                entitywitherskull2.motionY = -5;
                entitywitherskull3.motionY = -5;
                entitywitherskull4.motionY = -5;
                entitywitherskull5.motionY = -5;
                entitywitherskull6.motionY = -5;
                entitywitherskull7.motionY = -5;
                entitywitherskull8.motionY = -5;
                entitywitherskull9.motionY = -5;


                /////////////////////////////DARK///////////////////////////
                final EntityDarkSummoner darkSummoner = new EntityDarkSummoner(worldObj);
                darkSummoner.setLocationAndAngles(this.posX + rand.nextInt(5), this.posY, this.posZ + rand.nextInt(5), this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, darkSummoner);
                darkSummoner.setAttackTarget(this.getAttackTarget());

                summon200 = 70;
            }
            if (summon600 == 0) {

                //////////////////////////////////////FIRE////////////////////////////////////////
                if (this.getAttackTarget() instanceof EntityLivingBase) {

                    if (worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.air && worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.lava && worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.flowing_lava) {

                        if (!this.getAttackTarget().isInWater()) {
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX + 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ - 1), Blocks.lava.getDefaultState());
                            worldObj.setBlockState(new BlockPos((int) this.getAttackTarget().posX - 1, (int) this.getAttackTarget().posY, (int) this.getAttackTarget().posZ + 1), Blocks.lava.getDefaultState());

                        }
                    }
                }


                ////////////////////////////EARTH//////////////////////////////////
                final EntityEarthMinion entitywitherskull1 = new EntityEarthMinion(worldObj);
                entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                final EntityEarthMinion entitywitherskull3 = new EntityEarthMinion(worldObj);
                entitywitherskull3.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                entitywitherskull3.setAttackTarget(this.getAttackTarget());

                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);


                ////////////////////////////WIND////////////////////////
                final EntityMiniTornado miniTornado1 = new EntityMiniTornado(worldObj);
                miniTornado1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                miniTornado1.mountEntity(this.getAttackTarget());
                MWAWEntityUtil.spawnInWorld(worldObj, miniTornado1);


                ////////////////////////////DARK///////////////////////////
                final EntityDarkMark darkMark = new EntityDarkMark(worldObj);
                darkMark.setLocationAndAngles(this.posX, this.posY + 3, this.posZ, this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, darkMark);
                darkMark.setAttackTarget(this.getAttackTarget());


                /////////////////////////////LIGHTNING/////////////////////////////
                double xx = this.getAttackTarget().posX;
                double yy = this.getAttackTarget().posY;
                double zz = this.getAttackTarget().posZ;

                final EntityLightningBolt lightningBolt = new EntityLightningBolt(worldObj, 1, 1, 1);
                lightningBolt.setLocationAndAngles(xx, yy + 1, zz, this.rotationYaw, 0);

                MWAWEntityUtil.spawnWeather(worldObj, lightningBolt);


                ///////////////////////////WATER////////////////////////////////
                if (worldObj.getBlockState(new BlockPos((int) this.getAttackTarget().posX, (int) this.getAttackTarget().posY - 1, (int) this.getAttackTarget().posZ)).getBlock() != Blocks.air) {

                    final EntityWaterSpout waterSpout1 = new EntityWaterSpout(worldObj);
                    waterSpout1.setLocationAndAngles(this.getAttackTarget().posX + 2, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout2 = new EntityWaterSpout(worldObj);
                    waterSpout2.setLocationAndAngles(this.getAttackTarget().posX - 2, this.getAttackTarget().posY, this.getAttackTarget().posZ + 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout3 = new EntityWaterSpout(worldObj);
                    waterSpout3.setLocationAndAngles(this.getAttackTarget().posX + 2, this.getAttackTarget().posY, this.getAttackTarget().posZ + 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout4 = new EntityWaterSpout(worldObj);
                    waterSpout3.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ + 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout5 = new EntityWaterSpout(worldObj);
                    waterSpout5.setLocationAndAngles(this.getAttackTarget().posX + 2, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout6 = new EntityWaterSpout(worldObj);
                    waterSpout6.setLocationAndAngles(this.getAttackTarget().posX - 2, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout7 = new EntityWaterSpout(worldObj);
                    waterSpout7.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout8 = new EntityWaterSpout(worldObj);
                    waterSpout8.setLocationAndAngles(this.getAttackTarget().posX - 2, this.getAttackTarget().posY, this.getAttackTarget().posZ, this.rotationYaw, 0);
                    final EntityWaterSpout waterSpout9 = new EntityWaterSpout(worldObj);
                    waterSpout9.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ - 2, this.rotationYaw, 0);

                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout1);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout2);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout3);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout4);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout5);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout6);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout7);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout8);
                    MWAWEntityUtil.spawnInWorld(worldObj, waterSpout9);
                }

                summon600 = 150;
            }
        }

        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        super.onLivingUpdate();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityPlayer) {
            if (((EntityPlayer) source.getEntity()).getCurrentEquippedItem() != null
                    && ((EntityPlayer) source.getEntity()).getHeldItem().getItem() instanceof ItemSpade) {

                return super.attackEntityFrom(source, damage);

            }
        }
        return super.attackEntityFrom(source, 0);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchSummon600", summon600);
        compund.setInteger("WitchSummon300", summon300);
        compund.setInteger("WitchSummon200", summon200);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        summon600 = compund.getInteger("WitchSummon600");
        summon200 = compund.getInteger("WitchSummon200");
        summon300 = compund.getInteger("WitchSummon300");

    }

    protected Item getDropItem() {
        return MWAWItemRegistry.sandshard;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

}
