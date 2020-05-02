package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderFirePet3 extends RenderLiving {
    private static final ResourceLocation FirePet3_Texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/firepet3.png");

    public RenderFirePet3(final ModelBase par1ModelBase, final float par2) {
        super(par1ModelBase, par2);
        //this.setRenderPassModel(par1ModelBase);

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);

    }

    /*  protected int shouldRenderPass (EntityFirePet3 p_77032_1_, int p_77032_2_, float p_77032_3_) {
          if (p_77032_2_ == 0) {
              this.bindTexture(FirePet3_Texture);

              if (p_77032_1_.hasCustomNameTag() && "RAINBOW".equals(p_77032_1_.getCustomNameTag()) | "rainbow".equals(p_77032_1_.getCustomNameTag()) | "Rainbow".equals(p_77032_1_.getCustomNameTag())) {
                  boolean flag = true;
                  int k = p_77032_1_.ticksExisted / 25 + p_77032_1_.getEntityId();
                  int l = k % EntityFirePet3.rainbowColorTable.length;
                  int i1 = (k + 1) % EntityFirePet3.rainbowColorTable.length;
                  float f1 = ((float) (p_77032_1_.ticksExisted % 25) + p_77032_3_) / 25.0F;
                  GL11.glColor3f(EntityFirePet3.rainbowColorTable[l][0] * (1.0F - f1) + EntityFirePet3.rainbowColorTable[i1][0] * f1, EntityFirePet3.rainbowColorTable[l][1] * (1.0F - f1) + EntityFirePet3.rainbowColorTable[i1][1] * f1, EntityFirePet3.rainbowColorTable[l][2] * (1.0F - f1) + EntityFirePet3.rainbowColorTable[i1][2] * f1);
              }
              return 1;
          } else {
              return -1;
          }
      }
  */
    protected int shouldRenderPass(EntityFirePet3 p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_2_ == 0) {
            this.bindTexture(FirePet3_Texture);

            if (p_77032_1_.hasCustomNameTag() && "RAINBOW".equals(p_77032_1_.getCustomNameTag()) | "rainbow".equals(p_77032_1_.getCustomNameTag()) | "Rainbow".equals(p_77032_1_.getCustomNameTag())) {
                boolean flag = true;
                int k = p_77032_1_.ticksExisted / 25 + p_77032_1_.getEntityId();
                int l = k % EntityAirPhoenix3.rainbowColorTable.length;
                int i1 = (k + 1) % EntityAirPhoenix3.rainbowColorTable.length;
                float f1 = ((float) (p_77032_1_.ticksExisted % 25) + p_77032_3_) / 25.0F;
                GL11.glColor3f(EntityAirPhoenix3.rainbowColorTable[l][0] * (1.0F - f1) + EntityAirPhoenix3.rainbowColorTable[i1][0] * f1, EntityAirPhoenix3.rainbowColorTable[l][1] * (1.0F - f1) + EntityAirPhoenix3.rainbowColorTable[i1][1] * f1, EntityAirPhoenix3.rainbowColorTable[l][2] * (1.0F - f1) + EntityAirPhoenix3.rainbowColorTable[i1][2] * f1);
            }
            return 1;
        } else {
            return -1;
        }
    }

    protected void renderEquippedItems(EntityFirePet3 p_77029_1_, float p_77029_2_) {
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
                tessellator.setColorRGBA(255, 0, 0, (int) (255.0F * (1.0F - f2)));
                tessellator.addVertex(0.0D, 0.0D, 0.0D);
                tessellator.setColorRGBA(255, 0, 0, 0);
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

    protected int getColorMultiplier(EntityFirePet3 p_77030_1_, float p_77030_2_, float p_77030_3_) {
        int f2 = p_77030_1_.deathTicks;

        return 10 << f2 + 225;
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return FirePet3_Texture;

    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
        this.renderEquippedItems((EntityFirePet3) p_77029_1_, p_77029_2_);
    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        return this.getColorMultiplier((EntityFirePet3) p_77030_1_, p_77030_2_, p_77030_3_);
    }
}
/*
    protected int shouldRenderPass (EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((EntityFirePet3) p_77032_1_, p_77032_2_, p_77032_3_);
    }*/
