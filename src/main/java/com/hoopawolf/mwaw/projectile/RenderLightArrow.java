package com.hoopawolf.mwaw.projectile;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderLightArrow extends Render {

    private static final ResourceLocation LightArrow_Texture = new ResourceLocation(
            Reference.MOD_ID + ":" + "textures/entity/LightArrow.png");

    public RenderLightArrow(RenderManager p_i46193_1_) {
        super(p_i46193_1_);
    }

    public void renderLightArrow(EntityLightArrow par1EntityGoldArrow, double par2, double par4, double par6, float par8, float par9) {
        this.bindEntityTexture(par1EntityGoldArrow);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) par2, (float) par4, (float) par6);
        GlStateManager.rotate(par1EntityGoldArrow.prevRotationYaw + (par1EntityGoldArrow.rotationYaw - par1EntityGoldArrow.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(par1EntityGoldArrow.prevRotationPitch + (par1EntityGoldArrow.rotationPitch - par1EntityGoldArrow.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float) (0 + b0 * 10) / 32.0F;
        float f5 = (float) (5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float) (5 + b0 * 10) / 32.0F;
        float f9 = (float) (10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GlStateManager.enableRescaleNormal();
        float f11 = (float) par1EntityGoldArrow.arrowShake - par9;

        if (f11 > 0.0F) {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GlStateManager.rotate(f12, 0.0F, 0.0F, 1.0F);
        }

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(f10, f10, f10);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) f6, (double) f8);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) f7, (double) f8);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) f7, (double) f9);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) f6, (double) f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double) f6, (double) f8);
        worldrenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double) f7, (double) f8);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double) f7, (double) f9);
        worldrenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double) f6, (double) f9);
        tessellator.draw();

        for (int i = 0; i < 4; ++i) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            worldrenderer.startDrawingQuads();
            worldrenderer.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double) f2, (double) f4);
            worldrenderer.addVertexWithUV(8.0D, -2.0D, 0.0D, (double) f3, (double) f4);
            worldrenderer.addVertexWithUV(8.0D, 2.0D, 0.0D, (double) f3, (double) f5);
            worldrenderer.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double) f2, (double) f5);
            tessellator.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(par1EntityGoldArrow, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderLightArrow((EntityLightArrow) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return LightArrow_Texture;

    }

}

