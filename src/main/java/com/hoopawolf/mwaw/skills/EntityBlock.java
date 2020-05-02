package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.entity.EntityChristmasWitch;
import com.hoopawolf.mwaw.entity.EntityEarthMinion;
import com.hoopawolf.mwaw.entity.EntityEarthWitch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class EntityBlock extends EntityFallingBlock {

    private IBlockState fallTile;

    public EntityBlock(World world) {
        super(world);

    }

    public EntityBlock(World world, double x, double y, double z, IBlockState fallingBlockState) {
        super(world, x, y, z, fallingBlockState);
        this.fallTile = fallingBlockState;
    }

    @Override
    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        fallTime++;
        motionY -= 0.15D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.9D;
        motionY *= 0.9D;
        motionZ *= 0.9D;

        prevRotationPitch = rotationPitch;
        prevRotationYaw = rotationYaw;


        List targetList = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(1.0D, 1.0D, 1.0D));

        for (int i = 0; i < targetList.size(); i++) {
            Entity entitytargetcheck = (Entity) targetList.get(i);

            if (entitytargetcheck instanceof EntityLivingBase) {

                if (entitytargetcheck instanceof EntityEarthWitch || entitytargetcheck instanceof EntityEarthMinion || entitytargetcheck instanceof EntityBlock || entitytargetcheck instanceof EntityChristmasWitch || entitytargetcheck instanceof EntityBat || entitytargetcheck instanceof EntityMinecart || entitytargetcheck instanceof EntityRifle || entitytargetcheck instanceof EntitySword || entitytargetcheck instanceof EntityBigSnowGolem) {


                } else {

                    int k = MathHelper.ceiling_float_int(2 - 1F);

                    entitytargetcheck.attackEntityFrom(DamageSource.fallingBlock, Math.min(MathHelper.floor_float(k * 2F), 60));

                    ((EntityLivingBase) entitytargetcheck).addPotionEffect(new PotionEffect(
                            Potion.moveSlowdown.id, 30, 1));

                }
            }
        }

        int ix = (int) this.posX,
                iy = (int) this.posY,
                iz = (int) this.posZ;

        if (fallTime == 1 && worldObj.getBlockState(new BlockPos(ix, iy, iz)) == getBlock())
            worldObj.setBlockToAir(new BlockPos(ix, iy, iz));

        if (onGround) {
            motionX *= 0.7D;
            motionZ *= 0.7D;
            motionY *= -0.5D;

            if (fallTime > 5 && worldObj.getBlockState(new BlockPos(ix, iy, iz)) != Blocks.piston_extension && worldObj.getBlockState(new BlockPos(ix, iy - 1, iz)).getBlock() != Blocks.air) {
                if (worldObj.setBlockState(new BlockPos(ix, iy, iz), getBlock())) setDead();
            }
        } else if (!worldObj.isRemote && ((fallTime > 100 && (iy < 1 || iy > 256)) || fallTime > 600)) {
            dropItem(Item.getItemFromBlock(getBlock().getBlock()), 1);
            setDead();
        }

        if (ticksExisted >= 30) {
            worldObj.setBlockState(new BlockPos(ix, iy, iz), getBlock());
            setDead();

        }

    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        int i = MathHelper.ceiling_float_int(distance - 1F);

        if (i > 0) {
            boolean hurt = false;

            for (Object o : worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox())) {
                ((Entity) o).attackEntityFrom(DamageSource.fallingBlock, Math.min(MathHelper.floor_float(i * 3F), 60));

                if (!hurt) {
                    hurt = true;
                }
            }
        }
    }

    @Override
    public IBlockState getBlock() {
        return this.fallTile;
    }
}
