package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.MoWitchAndWizard;
import com.hoopawolf.mwaw.entity.EntityDarkWitch;
import com.hoopawolf.mwaw.entity.EntityLightWitch;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class EntityDarkSummoner extends EntityMob {

    int timer = 50;

    public EntityDarkSummoner(World p_i1744_1_) {
        super(p_i1744_1_);
        setSize(0.5F, 0.5F);
        this.ignoreFrustumCheck = true;
        this.isImmuneToFire = true;
        this.tasks.addTask(3, new EntityAILookIdle(this));
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

    protected String getLivingSound() {
        return "mwaw:whisper";
    }

    protected String getHurtSound() {
        return null;
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

    public boolean isPushedByWater() {
        return false;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {

        if (this.getAttackTarget() instanceof EntityDarkWitch)
            this.setAttackTarget(null);

        if (this.ticksExisted >= 400)
            this.setDead();

        if (this.motionY <= 0.0D) {

            this.motionY = 0.0D;

        }

        if (this.getHealth() <= 0)
            setDead();

        if (timer != 0 && this.getAttackTarget() != null)
            --timer;

        if (worldObj.isRemote) {
            MoWitchAndWizard.proxy.spawnParticles("dark_tornado", this);
        }

        if (this.getAttackTarget() != null) {

            if (timer == 0) {

                int random = rand.nextInt(5);

                if (random == 1) {

                    final EntityZombie entitywitherskull1 = new EntityZombie(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                    entitywitherskull1.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
                    entitywitherskull1.setCustomNameTag(EnumChatFormatting.BLACK + "Dark Minion");
                    entitywitherskull1.setAlwaysRenderNameTag(true);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    timer = 50;

                } else if (random == 2) {

                    final EntitySkeleton entitywitherskull1 = new EntitySkeleton(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                    entitywitherskull1.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
                    entitywitherskull1.setCustomNameTag(EnumChatFormatting.BLACK + "Dark Minion");
                    entitywitherskull1.setAlwaysRenderNameTag(true);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    timer = 50;

                } else if (random == 3) {

                    final EntityPigZombie entitywitherskull1 = new EntityPigZombie(worldObj);
                    entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0);
                    entitywitherskull1.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
                    entitywitherskull1.setCustomNameTag(EnumChatFormatting.BLACK + "Dark Minion");
                    entitywitherskull1.setAlwaysRenderNameTag(true);
                    MWAWEntityUtil.spawnInWorld(worldObj, entitywitherskull1);
                    entitywitherskull1.setAttackTarget(this.getAttackTarget());
                    timer = 50;

                }

            }

        }

        super.onLivingUpdate();
    }

    @Override
    public void writeToNBT(NBTTagCompound compund) {
        super.writeToNBT(compund);

        compund.setInteger("SummonerTimer", timer);

    }

    @Override
    public void readFromNBT(NBTTagCompound compund) {
        super.readFromNBT(compund);

        timer = compund.getInteger("SummonerTimer");
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

