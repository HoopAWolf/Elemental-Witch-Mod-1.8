package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderDarkPet3 extends RenderLiving {

    private static final ResourceLocation DarkPet_Texture = new ResourceLocation(
            Reference.MOD_ID + ":" + "textures/entity/darkpet3.png");

    public RenderDarkPet3(RenderManager p_i46149_1_, final ModelBase par1ModelBase, final float par2) {
        super(p_i46149_1_, par1ModelBase, par2);

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.doRender((EntityLiving) par1EntityLiving, par2, par4, par6, par8, par9);
        GL11.glDisable(GL11.GL_BLEND);

    }

    protected void renderEquippedItems(EntityDarkPet3 p_77029_1_, float p_77029_2_) {
        super.renderEquippedItems(p_77029_1_, p_77029_2_);
        Tessellator tessellator = Tessellator.instance;

        if (p_77029_1_.deathTicks > 0) {
            RenderHelper.disableStandardItemLighting();
            float f1 = ((float) p_77029_1_.deathTicks + p_77029_2_) / 200.0F;
            float f2 = 0.0F;

            if (f1 > 0.8F) {
                f2 = (f1 - 0.8F) / 0.2F;
            }

            Random random = new Random(432L);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDepthMask(false);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);

            for (int i = 0; (float) i < (f1 + f1 * f1) / 2.0F * 60.0F; ++i) {
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
                tessellator.startDrawing(6);
                float f3 = random.nextFloat() * 10.0F + 5.0F + f2 * 10.0F;
                float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
                tessellator.setColorRGBA(75, 0, 130, (int) (255.0F * (1.0F - f2)));
                tessellator.addVertex(0.0D, 0.0D, 0.0D);
                tessellator.setColorRGBA(75, 0, 130, 0);
                tessellator.addVertex(-0.866D * (double) f4, (double) f3, (double) (-0.5F * f4));
                tessellator.addVertex(0.866D * (double) f4, (double) f3, (double) (-0.5F * f4));
                tessellator.addVertex(0.0D, (double) f3, (double) (1.0F * f4));
                tessellator.addVertex(-0.866D * (double) f4, (double) f3, (double) (-0.5F * f4));
                tessellator.draw();
            }

            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            RenderHelper.enableStandardItemLighting();
        }
    }

    protected int getColorMultiplier(EntityDarkPet3 p_77030_1_, float p_77030_2_, float p_77030_3_) {
        int f2 = p_77030_1_.deathTicks;

        return 10 << f2 + 225;
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return DarkPet_Texture;

    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
        this.renderEquippedItems((EntityDarkPet3) p_77029_1_, p_77029_2_);
    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        return this.getColorMultiplier((EntityDarkPet3) p_77030_1_, p_77030_2_, p_77030_3_);
    }

}


