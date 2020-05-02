package com.hoopawolf.mwaw.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityLightProjectileFX extends EntityFX {

    public EntityLightProjectileFX(World par1World, double par2, double par4, double par6, double par9, double par10, double par11) {
        super(par1World, par2, par4, par6, par9, par10, par11);

        this.particleTextureIndexX = 4;
        this.particleTextureIndexY = 0;
        particleAlpha = 0.5F;
        this.particleScale -= 0.20F;

        this.particleMaxAge = 3;//how soon the particle dies. You can use randomizer for this
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();//make sure to have this
        }

        if (this.particleAge > this.particleMaxAge / 2) {
            this.setAlphaF(1.0F - ((float) this.particleAge - (float) (this.particleMaxAge / 2)) / (float) this.particleMaxAge);
        }

    }

    @Override
    public int getFXLayer() {

        return 1;

    }


    public int getBrightnessForRender(float p_70070_1_) {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_) {
        return 1.0F;
    }

}



