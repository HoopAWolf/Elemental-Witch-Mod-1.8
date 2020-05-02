package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.models.ModelMWAWWitch;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderLightWitch extends RenderLiving {
    private static final ResourceLocation LightWitch_Texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/lightwitch.png");
    private static final ResourceLocation LigtBeam_Texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/light_beam.png");

    public RenderLightWitch() {
        super(new ModelMWAWWitch(0.5F), 0.5F);

    }

    public void doRender(EntityLightWitch p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.doRender((EntityLiving) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);

        if (p_76986_1_.healingLight != null) {
            float f2 = (float) p_76986_1_.healingLight.innerRotation + p_76986_9_;
            float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
            f3 = (f3 * f3 + f3) * 0.2F;
            float f4 = (float) (p_76986_1_.healingLight.posX - p_76986_1_.posX - (p_76986_1_.prevPosX - p_76986_1_.posX) * (double) (1.0F - p_76986_9_));
            float f5 = (float) ((double) f3 + p_76986_1_.healingLight.posY - 1.0D - p_76986_1_.posY - (p_76986_1_.prevPosY - p_76986_1_.posY) * (double) (1.0F - p_76986_9_));
            float f6 = (float) (p_76986_1_.healingLight.posZ - p_76986_1_.posZ - (p_76986_1_.prevPosZ - p_76986_1_.posZ) * (double) (1.0F - p_76986_9_));
            float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
            float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
            GL11.glPushMatrix();
            GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_ + 2.0F, (float) p_76986_6_);
            GL11.glRotatef((float) (-Math.atan2((double) f6, (double) f4)) * 180.0F / (float) Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float) (-Math.atan2((double) f7, (double) f5)) * 180.0F / (float) Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
            Tessellator tessellator = Tessellator.instance;
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_CULL_FACE);
            this.bindTexture(LigtBeam_Texture);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            float f9 = 0.0F - ((float) p_76986_1_.ticksExisted + p_76986_9_) * 0.01F;
            float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float) p_76986_1_.ticksExisted + p_76986_9_) * 0.01F;
            tessellator.startDrawing(5);
            byte b0 = 8;

            for (int i = 0; i <= b0; ++i) {
                float f11 = MathHelper.sin((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.75F;
                float f12 = MathHelper.cos((float) (i % b0) * (float) Math.PI * 2.0F / (float) b0) * 0.75F;
                float f13 = (float) (i % b0) * 1.0F / (float) b0;
                tessellator.setColorOpaque_I(16777215);
                tessellator.addVertexWithUV((double) (f11 * 0.2F), (double) (f12 * 0.2F), 0.0D, (double) f13, (double) f10);
                tessellator.setColorOpaque_I(16777215);
                tessellator.addVertexWithUV((double) f11, (double) f12, (double) f8, (double) f13, (double) f9);
            }

            tessellator.draw();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glShadeModel(GL11.GL_FLAT);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
        }
    }


    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLightWitch) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }


    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return LightWitch_Texture;

    }


    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLightWitch) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLightWitch) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}



