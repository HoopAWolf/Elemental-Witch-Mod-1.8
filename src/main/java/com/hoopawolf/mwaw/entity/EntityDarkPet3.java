package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;

public class EntityDarkPet3 extends EntityTameable {

    public int skillTimer = 100;
    public int skill2Timer = 300;
    public float floatingRotation;
    public int deathTicks;

    public EntityDarkPet3(World par1World) {
        super(par1World);
        setSize(3.7F, 3.9F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 0.4D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.setTamed(true);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
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

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
    }

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        floatingRotation += 0.02F;

        if (!this.onGround && this.motionY < 0.0D)
            this.motionY *= 0.9D;

        if (this.getAttackTarget() != null && skillTimer != 0)
            --skillTimer;

        if (this.getAttackTarget() != null && skill2Timer != 0)
            --skill2Timer;

        if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth() && this.getHealth() >= 1)
            this.setHealth(this.getHealth() + 1);

        if (worldObj.isRemote) {
            if (this.isSitting())
                worldObj.spawnParticle(EnumParticleTypes.REDSTONE, posX, posY + 4.2, posZ, 0, 0, 0);
        }
        if (this.getAttackTarget() != null) {

            if (skillTimer == 0) {

                final EntityPigZombie entitywitherskull1 = new EntityPigZombie(worldObj);
                entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                entitywitherskull1.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
                entitywitherskull1.setCustomNameTag(EnumChatFormatting.BLACK + "Dark Minion");
                entitywitherskull1.setAlwaysRenderNameTag(true);
                MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                entitywitherskull1.setAttackTarget(this.getAttackTarget());
                entitywitherskull1.addPotionEffect(new PotionEffect(Potion.wither.getId(), 20 * 30, 2));

                skillTimer = 100;
            }

            if (skill2Timer == 0) {

                List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(7D, 7D, 7D));

                for (int i = 0; i < list.size(); i++) {
                    Entity entity1 = (Entity) list.get(i);

                    if (entity1 instanceof EntityZombie || entity1 instanceof EntityCreeper || entity1 instanceof EntitySkeleton || entity1 instanceof EntitySpider) {

                        ((EntityMob) entity1).setAttackTarget(getAttackTarget());

                        skill2Timer = 300;

                    }
                }
            }

        }

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("dark_normal", this);
        }

        super.onLivingUpdate();
    }

    protected void onDeathUpdate() {
        ++this.deathTicks;

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().expand(20D, 10D, 20D));

        if (this.deathTicks == 1)
            worldObj.setWorldTime(18000);

        if (this.deathTicks >= 0 && this.deathTicks <= 140) {
            if (worldObj.isRemote) {
                MoWitchAndWizard.proxy.spawnParticles("dark_tornado", this);
                MoWitchAndWizard.proxy.spawnParticles("dark_tornado", this);
            }

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityDarkPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityDarkPet2 || entity1 instanceof EntityDarkPet) {

                    ;

                } else {


                }
            }
        }


        if (this.deathTicks == 140) {
            final EntityDarkEgg mob = new EntityDarkEgg(worldObj);
            mob.setLocationAndAngles(posX, posY, posZ, this.rotationYaw, 0);
            MWAWEntityUtil.spawnInWorld(worldObj, mob);
            mob.setOwnerId(this.getOwnerId());
            mob.setCustomNameTag(this.getCustomNameTag());
            mob.setAlwaysRenderNameTag(true);

            for (int j = 0; j < list.size(); j++) {
                Entity entity1 = (Entity) list.get(j);
                if (entity1 instanceof EntityPlayer && ((EntityPlayer) entity1).capabilities.isCreativeMode || entity1 instanceof EntityDarkPet3 || entity1 instanceof EntityPlayer && entity1 == this.getOwner() || entity1 instanceof EntityDarkPet2 || entity1 instanceof EntityDarkPet) {

                    ;

                } else {


                }
            }

            this.setDead();
        }

    }

    public EntityDarkPet3 createChild(EntityAgeable p_90011_1_) {
        return null;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
            if (p_142018_1_ instanceof EntityDarkPet3) {
                EntityDarkPet3 entitywolf = (EntityDarkPet3) p_142018_1_;

                if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer) p_142018_2_).canAttackPlayer((EntityPlayer) p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse) p_142018_1_).isTame();
        } else {
            return false;
        }
    }

    public boolean interact(EntityPlayer p_70085_1_) {

        if (!this.isSitting()) {
            this.navigator.clearPathEntity();
            this.setAttackTarget((EntityLivingBase) null);
            this.aiSit.setSitting(true);
        } else if (this.isSitting()) {
            this.aiSit.setSitting(false);
        }

        return super.interact(p_70085_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("PetSkillDark", skillTimer);
        compund.setInteger("PetSkill2Dark", skill2Timer);
        compund.setInteger("PetDeathTicks", deathTicks);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        skillTimer = compund.getInteger("PetSkillDark");
        skill2Timer = compund.getInteger("PetSkill2Dark");
        deathTicks = compund.getInteger("PetDeathTicks");

    }

    public float getFloatingRotation() {
        return -16 - Math.abs((float) Math.sin(floatingRotation) * 1.5F);
    }

}

