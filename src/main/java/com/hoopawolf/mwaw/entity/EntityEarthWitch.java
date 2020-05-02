package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityEarthShoot;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityEarthWitch extends EntityMob implements IRangedAttackMob {

    public int blockSuffo = 300;
    public int summonGolem = 600;
    public boolean healContainer = false;

    public EntityEarthWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
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

        if (summonGolem != 0 && this.getAttackTarget() != null) {
            --summonGolem;
        }

        if (blockSuffo != 0 && this.getAttackTarget() != null) {
            --blockSuffo;
        }

        if (this.getAttackTarget() != null) {

            if (!worldObj.isRemote) {

                if (this.getHealth() <= 30) {

                    if (this.ticksExisted % 20 == 0) {
                        this.heal(4.0F);
                    }


                    if (healContainer == false) {

                        motionZ += 1;

                        Block block = Blocks.stone;
                        int xx = (int) this.posX;
                        int yy = (int) this.posY;
                        int zz = (int) this.posZ;

                        //top
                        worldObj.setBlockState(new BlockPos(xx, yy + 3, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy + 3, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy + 3, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz + 1), block.getDefaultState());

                        //bottom
                        worldObj.setBlockState(new BlockPos(xx, yy - 1, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy - 1, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy - 1, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz + 1), block.getDefaultState());

                        //side top
                        worldObj.setBlockState(new BlockPos(xx, yy + 2, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy + 2, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 2, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 2, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 2, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 2, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz + 1), block.getDefaultState());

                        //side middle
                        worldObj.setBlockState(new BlockPos(xx, yy + 1, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy + 1, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 1, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 1, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy + 1, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy + 1, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz + 1), block.getDefaultState());

                        //side bottom
                        worldObj.setBlockState(new BlockPos(xx, yy, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx, yy, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy, zz), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy, zz + 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 1, yy, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 1, yy, zz - 2), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx + 2, yy, zz + 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy, zz - 1), block.getDefaultState());
                        worldObj.setBlockState(new BlockPos(xx - 2, yy, zz + 1), block.getDefaultState());

                        for (int i = 0; i < 20; i++) {

                            final Random rand = new Random();
                            final int randomNumber = rand.nextInt(5);
                            final EntityEarthMinion entity = new EntityEarthMinion(worldObj);

                            if (randomNumber == 1) {

                                entity.setLocationAndAngles(this.posX + 4,
                                        this.posY, this.posZ - 4,
                                        0, 0);
                                entity.setAttackTarget(this.getAttackTarget());
                                MWAWEntityUtil.spawnInWorld(worldObj, entity);

                            } else if (randomNumber == 2) {

                                entity.setLocationAndAngles(this.posX - 5,
                                        this.posY, this.posZ + 4,
                                        0, 0);
                                entity.setAttackTarget(this.getAttackTarget());
                                MWAWEntityUtil.spawnInWorld(worldObj, entity);

                            } else if (randomNumber == 3) {

                                entity.setLocationAndAngles(this.posX + 5,
                                        this.posY, this.posZ - 5,
                                        0, 0);
                                entity.setAttackTarget(this.getAttackTarget());
                                MWAWEntityUtil.spawnInWorld(worldObj, entity);

                            } else if (randomNumber == 4) {

                                entity.setLocationAndAngles(this.posX - 4,
                                        this.posY, this.posZ + 5,
                                        0, 0);
                                entity.setAttackTarget(this.getAttackTarget());
                                MWAWEntityUtil.spawnInWorld(worldObj, entity);

                            }
                        }

                        healContainer = true;
                    }
                }

                if (summonGolem == 0) {

                    motionZ = 2;
                    Block block = Blocks.stone;
                    int xx = (int) this.getAttackTarget().posX;
                    int yy = (int) this.getAttackTarget().posY;
                    int zz = (int) this.getAttackTarget().posZ;

                    //top
                    worldObj.setBlockState(new BlockPos(xx, yy + 3, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy + 3, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy + 3, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 3, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 3, zz + 1), block.getDefaultState());

                    //bottom
                    worldObj.setBlockState(new BlockPos(xx, yy - 1, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy - 1, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy - 1, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy - 1, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy - 1, zz + 1), block.getDefaultState());

                    final EntityEarthMinion entitywitherskull1 = new EntityEarthMinion(worldObj);
                    entitywitherskull1.setLocationAndAngles(xx, yy, zz, this.rotationYaw, 0);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    final EntityEarthMinion entitywitherskull3 = new EntityEarthMinion(worldObj);
                    entitywitherskull3.setLocationAndAngles(xx, yy, zz, this.rotationYaw, 0);
                    entitywitherskull3.setAttackTarget(this.getAttackTarget());

                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);

                    //side top
                    worldObj.setBlockState(new BlockPos(xx, yy + 2, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy + 2, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 2, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 2, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 2, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 2, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 2, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 2, zz + 1), block.getDefaultState());

                    //side middle
                    worldObj.setBlockState(new BlockPos(xx, yy + 1, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy + 1, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 1, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 1, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy + 1, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy + 1, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy + 1, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy + 1, zz + 1), block.getDefaultState());

                    //side bottom
                    worldObj.setBlockState(new BlockPos(xx, yy, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx, yy, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy, zz), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy, zz + 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 1, yy, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 1, yy, zz - 2), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx + 2, yy, zz + 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy, zz - 1), block.getDefaultState());
                    worldObj.setBlockState(new BlockPos(xx - 2, yy, zz + 1), block.getDefaultState());

                    summonGolem = 600;

                }

                if (blockSuffo == 0) {

                    int xx = (int) Math.floor(this.getAttackTarget().posX);
                    int yy = (int) Math.floor(this.getAttackTarget().posY);
                    int zz = (int) Math.floor(this.getAttackTarget().posZ);

                    final EntityBlock entitywitherskull = new EntityBlock(
                            this.worldObj, xx, yy + 3, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull1 = new EntityBlock(
                            this.worldObj, xx, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull2 = new EntityBlock(
                            this.worldObj, xx, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull3 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 3, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul4 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 3, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul5 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul6 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul7 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 3, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul8 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 3, zz - 1, Blocks.stone.getDefaultState());
                    this.worldObj.spawnEntityInWorld(entitywitherskull);
                    this.worldObj.spawnEntityInWorld(entitywitherskull1);
                    this.worldObj.spawnEntityInWorld(entitywitherskull2);
                    this.worldObj.spawnEntityInWorld(entitywitherskull3);
                    this.worldObj.spawnEntityInWorld(entitywitherskul4);
                    this.worldObj.spawnEntityInWorld(entitywitherskul5);
                    this.worldObj.spawnEntityInWorld(entitywitherskul6);
                    this.worldObj.spawnEntityInWorld(entitywitherskul7);
                    this.worldObj.spawnEntityInWorld(entitywitherskul8);

                    final EntityBlock entitywitherskull9 = new EntityBlock(
                            this.worldObj, xx, yy + 4, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull10 = new EntityBlock(
                            this.worldObj, xx, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull11 = new EntityBlock(
                            this.worldObj, xx, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull12 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 4, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul13 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 4, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul14 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul15 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul16 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 4, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul17 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 4, zz - 1, Blocks.stone.getDefaultState());
                    this.worldObj.spawnEntityInWorld(entitywitherskull9);
                    this.worldObj.spawnEntityInWorld(entitywitherskull10);
                    this.worldObj.spawnEntityInWorld(entitywitherskull11);
                    this.worldObj.spawnEntityInWorld(entitywitherskull12);
                    this.worldObj.spawnEntityInWorld(entitywitherskul13);
                    this.worldObj.spawnEntityInWorld(entitywitherskul14);
                    this.worldObj.spawnEntityInWorld(entitywitherskul15);
                    this.worldObj.spawnEntityInWorld(entitywitherskul16);
                    this.worldObj.spawnEntityInWorld(entitywitherskul17);

                    final EntityBlock entitywitherskull18 = new EntityBlock(
                            this.worldObj, xx, yy + 5, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull19 = new EntityBlock(
                            this.worldObj, xx, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull20 = new EntityBlock(
                            this.worldObj, xx, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskull21 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 5, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul22 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 5, zz, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul23 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul24 = new EntityBlock(
                            this.worldObj, xx - 1, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul25 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 5, zz + 1, Blocks.stone.getDefaultState());
                    final EntityBlock entitywitherskul26 = new EntityBlock(
                            this.worldObj, xx + 1, yy + 5, zz - 1, Blocks.stone.getDefaultState());
                    this.worldObj.spawnEntityInWorld(entitywitherskull18);
                    this.worldObj.spawnEntityInWorld(entitywitherskull19);
                    this.worldObj.spawnEntityInWorld(entitywitherskull20);
                    this.worldObj.spawnEntityInWorld(entitywitherskull21);
                    this.worldObj.spawnEntityInWorld(entitywitherskul22);
                    this.worldObj.spawnEntityInWorld(entitywitherskul23);
                    this.worldObj.spawnEntityInWorld(entitywitherskul24);
                    this.worldObj.spawnEntityInWorld(entitywitherskul25);
                    this.worldObj.spawnEntityInWorld(entitywitherskul26);


                    blockSuffo = 300;
                }
            }
        }

        if (this.rand.nextFloat() < 7.5E-4F) {
            this.worldObj.setEntityState(this, (byte) 15);
        }

        super.onLivingUpdate();
    }

    public boolean isArmored() {
        return this.getHealth() <= 50;
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
        final EntityEarthShoot entitywitherskull = new EntityEarthShoot(
                this.worldObj, this, d6, d7, d8);

        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityFallingBlock || source.getEntity() instanceof EntityBlock || source.equals(DamageSource.inWall) || source.equals(DamageSource.fallingBlock) || source.getEntity() instanceof EntityEarthShoot) {
            return false;
        }

        Entity entity;

        if (this.isArmored()) {
            entity = source.getSourceOfDamage();

            if (entity instanceof EntityArrow) {
                return false;
            }
        }


        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void setDead() {

        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 100D, 20D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityEarthMinion) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityBlock) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
    }


    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchBlockSuffo", blockSuffo);
        compund.setInteger("WitchMinionSummon", summonGolem);
        compund.setBoolean("WitchHealContainer", healContainer);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        blockSuffo = compund.getInteger("WitchBlockSuffo");
        summonGolem = compund.getInteger("WitchMinionSummon");
        healContainer = compund.getBoolean("WitchHealContainer");

    }

    protected Item getDropItem() {
        return MWAWItemRegistry.earthshard;
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

                        final EntityEarthEgg mob = new EntityEarthEgg(worldObj);
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