package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityDarkShoot;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.registry.MWAWItemRegistry;
import com.hoopawolf.mwaw.skills.EntityDarkMark;
import com.hoopawolf.mwaw.skills.EntityDarkSummoner;
import com.hoopawolf.mwaw.skills.EntityDeathCircle;
import com.hoopawolf.mwaw.skills.EntityEnderHole;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class EntityDarkWitch extends EntityMob implements IRangedAttackMob {

    public boolean deathCircle = false;
    public int summonSummoner = 200;
    public int summonDarkMark = 600;
    public int summonEnderHole = 300;
    public short detroyAllLight = 20;

    public EntityDarkWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        if (MWAWConfigHandler.DarkWitchVSLightWitch) {
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLightWitch.class, true));
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

        if (detroyAllLight != 0)
            --detroyAllLight;

        if (detroyAllLight == 0 && MWAWConfigHandler.DarkWitchDestroyLight) {
            destroyLight();
            detroyAllLight = 20;
        }

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 20D, 20D));

        for (int i = 0; i < list.size(); i++) {
            Entity entity1 = (Entity) list.get(i);

            if (entity1 instanceof EntityZombie || entity1 instanceof EntitySpider || entity1 instanceof EntityCaveSpider || entity1 instanceof EntitySkeleton || entity1 instanceof EntityCreeper) {

                ((EntityMob) entity1).setAttackTarget(this.getAttackTarget());
            }
        }

        if (!worldObj.isRemote && this.getAttackTarget() != null && MWAWConfigHandler.DarkWitchTurnNight)
            worldObj.setWorldTime(18000);


        if (summonSummoner != 0 && this.getAttackTarget() != null)
            --summonSummoner;

        if (summonDarkMark != 0 && this.getAttackTarget() != null)
            --summonDarkMark;

        if (summonEnderHole != 0 && this.getAttackTarget() != null)
            --summonEnderHole;

        if (this.rand.nextFloat() < 7.5E-4F)
            this.worldObj.setEntityState(this, (byte) 15);

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("dark_normal", this);
        }

        if (this.getAttackTarget() != null) {

            if (!deathCircle && this.getHealth() <= 20) {

                final EntityDeathCircle entitywitherskull1 = new EntityDeathCircle(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                entitywitherskull1.mountEntity(this);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                deathCircle = true;
            }

            if (summonSummoner == 0) {

                final EntityDarkSummoner entitywitherskull1 = new EntityDarkSummoner(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX + rand.nextInt(5), this.posY, this.posZ + rand.nextInt(5), this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                summonSummoner = 200;

            }

            if (summonDarkMark == 0) {

                final EntityDarkMark entitywitherskull1 = new EntityDarkMark(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY + 3, this.posZ, this.rotationYaw, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                summonDarkMark = 600;

            }

            if (summonEnderHole == 0) {

                EntityEnderHole entitysnowball = new EntityEnderHole(worldObj);
                entitysnowball.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY + 2, this.getAttackTarget().posZ, 0, 0);
                MWAWEntityUtil.spawnInWorld(worldObj, entitysnowball);
                summonEnderHole = 300;

            }

        }

        super.onLivingUpdate();
    }

    public void destroyLight() {
        int radius = 15;
        for (int x = -radius; x <= radius; x++)
            for (int y = -radius; y <= radius; y++)
                for (int z = -radius; z <= radius; z++) {
                    IBlockState id = worldObj.getBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z));
                    if (id != null && id.getBlock().getLightValue() > 0.5F) {
                        id.getBlock().dropBlockAsItem(worldObj, new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), worldObj.getBlockState(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z)), 0);
                        worldObj.setBlockToAir(new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z));
                        id.getBlock().onBlockDestroyedByExplosion(worldObj, new BlockPos((int) posX + x, (int) posY + y, (int) posZ + z), new Explosion(worldObj, this, 0.0D, 0.0D, 0.0D, 0.0F, false, false));
                    }
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
        final EntityDarkShoot entitywitherskull = new EntityDarkShoot(
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
            if (entitytargetcheck instanceof EntityDeathCircle) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityDarkSummoner) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityDarkMark) {

                entitytargetcheck.setDead();

            }

            if (entitytargetcheck instanceof EntityEnderHole) {

                entitytargetcheck.setDead();

            }
        }
        super.setDead();
    }

    protected void fall(float p_70069_1_) {
    }

    public void addPotionEffect(PotionEffect p_70690_1_) {
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setBoolean("WitchDeathCircle", deathCircle);
        compund.setInteger("WitchDarkMark", summonDarkMark);
        compund.setInteger("WitchDarkSummoner", summonSummoner);
        compund.setInteger("WitchEnderHole", summonEnderHole);
        compund.setShort("WitchDestoryLight", detroyAllLight);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        deathCircle = compund.getBoolean("WitchDeathCircle");
        summonDarkMark = compund.getInteger("WitchDarkMark");
        summonSummoner = compund.getInteger("WitchDarkSummoner");
        summonEnderHole = compund.getInteger("WitchEnderHole");
        detroyAllLight = compund.getShort("WitchDestoryLight");

    }

    protected Item getDropItem() {
        return MWAWItemRegistry.darkshard;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source.getEntity() instanceof EntityDarkShoot || source.getEntity() instanceof EntityDeathCircle || source.getEntity() instanceof EntityEnderHole) {
            return false;
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

                        final EntityDarkEgg mob = new EntityDarkEgg(worldObj);
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
