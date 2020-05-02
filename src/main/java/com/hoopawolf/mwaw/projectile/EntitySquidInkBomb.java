package com.hoopawolf.mwaw.projectile;

import com.hoopawolf.mwaw.entity.EntityWaterWitch;
import com.hoopawolf.mwaw.skills.EntityGiantSquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

public class EntitySquidInkBomb extends EntityThrowable {

    public EntitySquidInkBomb(World p_i1773_1_) {
        super(p_i1773_1_);
    }

    public EntitySquidInkBomb(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntitySquidInkBomb(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
        ignoreFrustumCheck = true;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70184_1_) {
        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(2D, 2D, 2D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);
            if (entitytargetcheck instanceof EntityLivingBase) {
                if (entitytargetcheck instanceof EntityWaterWitch | entitytargetcheck instanceof EntityGiantSquid) {

                } else {
                    entitytargetcheck.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 0.5);
                    this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 0, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
                    ((EntityLivingBase) entitytargetcheck).addPotionEffect(new PotionEffect(
                            Potion.blindness.id, 20 * 2, 1));

                }
            }
        }


        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}