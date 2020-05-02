package com.hoopawolf.mwaw.particle;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntitySmallWindFX extends EntityFX {

    public EntitySmallWindFX(World par1World, double par2, double par4, double par6, double par9, double par10, double par11) {
        super(par1World, par2, par4, par6, par9, par10, par11);

        this.particleTextureIndexX = 1;
        this.particleTextureIndexY = 0;
        particleAlpha = 0.5F;
        this.particleScale -= 1.20F;

        this.particleMaxAge = 3;//how soon the particle dies. You can use randomizer for this
        this.noClip = true;//does your particle collide with blocks?
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

    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {

        par1Tessellator.draw();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":" + "textures/particles/mwawparticles.png"));
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setBrightness(200);//make sure you have this!!
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
        par1Tessellator.draw();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/particle/particles.png"));
        par1Tessellator.startDrawingQuads();
    }

}




