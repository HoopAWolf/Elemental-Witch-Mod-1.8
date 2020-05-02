package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.entity.EntityWaterWitch;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntitySquidInkBomb;
import com.hoopawolf.mwaw.projectile.EntityWaterShoot;
import com.hoopawolf.mwaw.registry.MWAWPotionRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityGiantSquid extends EntityMob implements IRangedAttackMob {
    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    /**
     * appears to be rotation in radians; we already have pitch & yaw, so this completes the triumvirate.
     */
    public float squidRotation;
    /**
     * previous squidRotation in radians
     */
    public float prevSquidRotation;
    /**
     * angle of the tentacles in radians
     */
    public float tentacleAngle;
    /**
     * the last calculated angle of the tentacles in radians
     */
    public float lastTentacleAngle;
    public short squidAttack = 70;
    public short squidBomb = 150;
    public short squidWater = 200;
    /**
     * change in squidRotation in radians.
     */
    private float rotationVelocity;

    public EntityGiantSquid(World p_i1693_1_) {
        super(p_i1693_1_);
        this.setSize(8.95F, 19.95F);
        this.ignoreFrustumCheck = true;
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(100.0D);
    }

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
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return null;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume() {
        return 0.4F;
    }

    protected Item getDropItem() {
        return Item.getItemById(0);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return false;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        int j = this.rand.nextInt(300 + p_70628_2_) + 1;

        for (int k = 0; k < j; ++k) {
            this.entityDropItem(new ItemStack(Items.dye, 1, 0), 0.0F);
        }
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (squidAttack != 0)
            --squidAttack;

        if (squidBomb != 0)
            --squidBomb;

        if (squidWater != 0)
            --squidWater;

        if (!this.worldObj.isRaining())
            worldObj.getWorldInfo().setRaining(true);

        if (squidAttack == 0) {

            List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(10D, 20D, 10D));

            for (int i = 0; i < targetList.size(); i++) {
                Entity entitytargetcheck = (Entity) targetList.get(i);
                if (entitytargetcheck instanceof EntityLivingBase) {
                    if (entitytargetcheck instanceof EntityWaterWitch || entitytargetcheck instanceof EntitySquid) {

                    } else {

                        ((EntityLivingBase) entitytargetcheck).addPotionEffect(new PotionEffect(
                                MWAWPotionRegistry.enderSkinPotion.id, 20 * 200, 1));

                    }
                }
            }
            squidAttack = 70;
        }

        if (squidBomb == 0) {

            for (int i = 0; i <= 30; i++) {
                final EntitySquidInkBomb entitywitherskull1 = new EntitySquidInkBomb(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX + this.rand.nextDouble() * 30.0D, this.posY + 60, this.posZ + this.rand.nextDouble() * 30.0D, 0, 0);

                final EntitySquidInkBomb entitywitherskull2 = new EntitySquidInkBomb(worldObj);
                entitywitherskull2.setLocationAndAngles(this.posX - this.rand.nextDouble() * 30.0D, this.posY + 60, this.posZ + this.rand.nextDouble() * 30.0D, 0, 0);

                final EntitySquidInkBomb entitywitherskull3 = new EntitySquidInkBomb(worldObj);
                entitywitherskull3.setLocationAndAngles(this.posX + this.rand.nextDouble() * 30.0D, this.posY + 60, this.posZ - this.rand.nextDouble() * 30.0D, 0, 0);

                final EntitySquidInkBomb entitywitherskull4 = new EntitySquidInkBomb(worldObj);
                entitywitherskull4.setLocationAndAngles(this.posX - this.rand.nextDouble() * 30.0D, this.posY + 60, this.posZ - this.rand.nextDouble() * 30.0D, 0, 0);

                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull2);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull3);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull4);
            }
            squidBomb = 150;
        }

        if (squidWater == 0) {

            List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(10D, 20D, 10D));

            for (int i = 0; i < targetList.size(); i++) {
                Entity entitytargetcheck = (Entity) targetList.get(i);
                if (entitytargetcheck instanceof EntityLivingBase) {
                    if (entitytargetcheck instanceof EntityWaterWitch) {

                    } else {
                        if (worldObj.getBlockState(new BlockPos((int) entitytargetcheck.posX, (int) entitytargetcheck.posY, (int) entitytargetcheck.posZ)).getBlock() == Blocks.air) {
                            worldObj.setBlockState(new BlockPos((int) entitytargetcheck.posX, (int) entitytargetcheck.posY, (int) entitytargetcheck.posZ), Blocks.flowing_water.getDefaultState());
                        }
                    }
                }
            }

            squidWater = 200;
        }

        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if (this.squidRotation > ((float) Math.PI * 2F)) {
            this.squidRotation -= ((float) Math.PI * 2F);

            if (this.rand.nextInt(10) == 0) {
                this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
            }
        }


        this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;

        if (!this.worldObj.isRemote) {
            this.motionX = 0.0D;
            this.motionY -= 0.08D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ = 0.0D;
        }

        if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
            int i = MathHelper.floor_double(this.posY);
            int i1 = MathHelper.floor_double(this.posX);
            int j1 = MathHelper.floor_double(this.posZ);
            boolean flag = false;

            for (int l1 = -10; l1 <= 10; ++l1) {
                for (int i2 = -10; i2 <= 10; ++i2) {
                    for (int j = 0; j <= 20; ++j) {
                        int j2 = i1 + l1;
                        int k = i + j;
                        int l = j1 + i2;
                        Block block = this.worldObj.getBlockState(new BlockPos(j2, k, l)).getBlock();

                        if (!block.isAir(worldObj, new BlockPos(j2, k, l)) && block != Blocks.bedrock && block != Blocks.obsidian && block != Blocks.water && block != Blocks.flowing_water) {
                            flag = this.worldObj.destroyBlock(new BlockPos(j2, k, l), true) || flag;
                        }
                    }
                }
            }

            if (flag) {
                this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1012, new BlockPos(this), 0);
            }
        }

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityWaterShoot | source.getEntity() instanceof EntitySquidInkBomb) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);
        compund.setShort("SquidAttack", squidAttack);
        compund.setShort("SquidBomb", squidBomb);
        compund.setShort("SquidHead", squidWater);
    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);
        squidAttack = compund.getShort("SquidAttack");
        squidBomb = compund.getShort("SquidBomb");
        squidWater = compund.getShort("SquidHead");
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
        final EntityWaterShoot entitywitherskull = new EntityWaterShoot(
                this.worldObj, this, d6, d7, d8);

        entitywitherskull.posY = d4 + 0.5D;
        entitywitherskull.posX = d3;
        entitywitherskull.posZ = d5;
        MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull);

    }
}
