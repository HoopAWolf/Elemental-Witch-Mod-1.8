package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.projectile.EntityPumpkinSkull;
import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import com.hoopawolf.mwaw.skills.EntityPumpkinAirStrike;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class EntityHalloweenWitch extends EntityMob implements IRangedAttackMob {

    int summonTeleport = 50;
    int summonPumkinAirStrike = 400;
    int summonSkeleton = 300;
    boolean deathSummon = false;
    boolean summoned = false;

    public EntityHalloweenWitch(World p_i1744_1_) {
        super(p_i1744_1_);
        this.isImmuneToFire = true;
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

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(20D, 20D, 20D));

        for (int i = 0; i < list.size(); i++) {
            Entity entity1 = (Entity) list.get(i);

            if (entity1 instanceof EntityZombie || entity1 instanceof EntitySpider || entity1 instanceof EntityCaveSpider || entity1 instanceof EntitySkeleton || entity1 instanceof EntityCreeper) {

                ((EntityMob) entity1).setAttackTarget(this.getAttackTarget());
            }
        }

        if (summonTeleport != 0 && this.getAttackTarget() != null) {
            --summonTeleport;
        }

        if (summonSkeleton != 0 && this.getAttackTarget() != null) {
            --summonSkeleton;
            worldObj.setWorldTime(18000);
        }

        if (summonPumkinAirStrike != 0 && this.getAttackTarget() != null) {
            --summonPumkinAirStrike;
        }

        if (this.getHealth() <= 20 && !deathSummon)
            deathSummon = true;

        if (this.getAttackTarget() != null) {

            if (summonTeleport == 0) {
                this.teleportToEntity(this.getAttackTarget());
                summonTeleport = 50;
            }

            if (deathSummon && !summoned) {

                for (int i = 0; i < 5; i++) {

                    final EntitySpider entitywitherskull1 = new EntitySpider(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + rand.nextInt(4), this.getAttackTarget().posY, this.getAttackTarget().posZ + rand.nextInt(4), this.rotationYaw, 0);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    entitywitherskull1.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), 50 * 100, 2));
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                    if (i == 4) {

                        summoned = true;

                    }

                }

            }

            if (summonSkeleton == 0) {

                for (int i = 0; i < 5; i++) {

                    final EntitySkeleton entitywitherskull1 = new EntitySkeleton(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX + rand.nextInt(4), this.posY, this.posZ + rand.nextInt(4), this.rotationYaw, 0);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    entitywitherskull1.setCurrentItemOrArmor(4, new ItemStack(Blocks.pumpkin));
                    entitywitherskull1.setCustomNameTag(EnumChatFormatting.YELLOW + "Pumpkin Minion");
                    entitywitherskull1.setAlwaysRenderNameTag(true);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                    if (i == 4) {

                        summonSkeleton = 300;

                    }

                }

            }

            if (summonPumkinAirStrike == 0) {

                for (int i = 0; i < 10; i++) {

                    final EntityPumpkinAirStrike entitywitherskull1 = new EntityPumpkinAirStrike(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.getAttackTarget().posX + rand.nextInt(10), this.getAttackTarget().posY + 25, this.getAttackTarget().posZ + rand.nextInt(10), this.rotationYaw, 0);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);

                    if (i == 9) {

                        summonPumkinAirStrike = 200;

                    }

                }

            }

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("halloween_normal_4", this);

            for (int i = 0; i < 1; i++) {
                this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        this.posY + this.rand.nextDouble() * (double) this.height - 0.25D,
                        this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width,
                        (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
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

        this.func_82216_a(0, p_82196_1_);
        this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1009, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);

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
        final EntityPumpkinSkull entitywitherskull = new EntityPumpkinSkull(
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
            if (entitytargetcheck instanceof EntityPumpkinAirStrike) {

                entitytargetcheck.setDead();

            }
        }

        super.setDead();
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

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("WitchTeleport", summonTeleport);
        compund.setInteger("WitchSummonSkeleton", summonSkeleton);
        compund.setInteger("WitchPumkinAirStrike", summonPumkinAirStrike);
        compund.setBoolean("WitchDeathSummon", deathSummon);
        compund.setBoolean("WitchDeathSummoned", summoned);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        summonTeleport = compund.getInteger("WitchTeleport");
        summonPumkinAirStrike = compund.getInteger("WitchPumkinAirStrike");
        summonSkeleton = compund.getInteger("WitchSummonSkeleton");
        deathSummon = compund.getBoolean("WitchDeathSummon");
        summoned = compund.getBoolean("WitchDeathSummoned");

    }

    protected boolean teleportToEntity(Entity p_70816_1_) {
        Vec3 vec3 = new Vec3(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double) (this.height / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
        double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
        return this.teleportTo(d1, d2, d3);
    }

    /**
     * Teleport the enderman
     */
    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = event.targetX;
        this.posY = event.targetY;
        this.posZ = event.targetZ;
        boolean flag = false;
        BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);

        if (this.worldObj.isBlockLoaded(blockpos)) {
            boolean flag1 = false;

            while (!flag1 && blockpos.getY() > 0) {
                BlockPos blockpos1 = blockpos.down();
                Block block = this.worldObj.getBlockState(blockpos1).getBlock();

                if (block.getMaterial().blocksMovement()) {
                    flag1 = true;
                } else {
                    --this.posY;
                    blockpos = blockpos1;
                }
            }

            if (flag1) {
                super.setPositionAndUpdate(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox())) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (int i = 0; i < short1; ++i) {
                double d9 = (double) i / ((double) short1 - 1.0D);
                float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
                double d6 = d3 + (this.posX - d3) * d9 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
                double d7 = d4 + (this.posY - d4) * d9 + this.rand.nextDouble() * (double) this.height;
                double d8 = d5 + (this.posZ - d5) * d9 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
                this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, d6, d7, d8, (double) f, (double) f1, (double) f2, new int[0]);
            }

            this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.playSound("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getEntity() instanceof EntityPumpkinAirStrike || source.getEntity() instanceof EntitySkeleton || source.getEntity() instanceof EntitySpider) {
            return false;
        }

        return super.attackEntityFrom(source, damage);
    }

}

