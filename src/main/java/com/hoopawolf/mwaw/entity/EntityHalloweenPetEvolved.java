package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class EntityHalloweenPetEvolved extends EntityTameable {

    public int TimerEvolve = 500;
    public int TimerControl = 200;
    public int TimerTeleport = 50;
    public boolean Control = false;

    public EntityHalloweenPetEvolved(World par1World) {
        super(par1World);
        setSize(0.7F, 2.9F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.setTamed(false);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
    }

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

        if (!this.isTamed())
            this.setDead();

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("halloween_normal", this);
        }

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1) {
            this.setHealth(this.getHealth() + 1);
        }

        if (!worldObj.isRemote) {
            if (TimerEvolve != 0) {
                --TimerEvolve;
            }

            if (TimerTeleport != 0) {
                --TimerTeleport;
            }

            if (TimerControl != 0) {
                --TimerControl;
            }
        }

        if (TimerControl == 0)
            Control = true;

        if (this.getAttackTarget() != null) {

            if (TimerTeleport == 0) {
                this.teleportToEntity(this.getAttackTarget());
                TimerTeleport = 10;
            }

            if (TimerControl == 0 && Control) {

                List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(7D, 7D, 7D));

                for (int i = 0; i < list.size(); i++) {
                    Entity entity1 = (Entity) list.get(i);

                    if (entity1 instanceof EntityZombie || entity1 instanceof EntityCreeper || entity1 instanceof EntitySkeleton || entity1 instanceof EntitySpider) {

                        ((EntityMob) entity1).setAttackTarget(getAttackTarget());

                        if (i == list.size()) {
                            Control = false;
                            TimerControl = 50;
                        }
                    }
                }
            }
        }

        if (TimerEvolve == 0) {

            final EntityHalloweenPet mob = new EntityHalloweenPet(worldObj);
            mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setTamed(true);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setAlwaysRenderNameTag(true);

            this.setDead();
        }

        super.onLivingUpdate();
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        if (!worldObj.isDaytime()) {
            return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
        } else
            return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
    }


    protected void fall(float p_70069_1_) {
    }

    public EntityHalloweenPet createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityHalloweenPetEvolved) {
                EntityHalloweenPetEvolved entitywolf = (EntityHalloweenPetEvolved) p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse) p_142018_1_).isTame();
        } else {
            return false;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("TimerDevolve", TimerEvolve);
        compund.setInteger("TimerControl", TimerControl);
        compund.setInteger("TimerTeleport", TimerTeleport);
        compund.setBoolean("ControlBoolean", Control);
    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        TimerEvolve = compund.getInteger("TimerDevolve");
        TimerControl = compund.getInteger("TimerControl");
        TimerTeleport = compund.getInteger("TimerTeleport");
        Control = compund.getBoolean("ControlBoolean");

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

}
