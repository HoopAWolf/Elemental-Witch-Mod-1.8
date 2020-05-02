package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.models.ModelMWAWWitch;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEarthWitch extends RenderLiving {

    private static final ResourceLocation EarthWitch_Texture = new ResourceLocation(
            Reference.MOD_ID + ":" + "textures/entity/earthwitch.png");
    private static final ResourceLocation armoredblitsuneTextures = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/earth_armor.png");

    private ModelBase witchModel = new ModelMWAWWitch(0.5F);

    public RenderEarthWitch() {
        super(new ModelMWAWWitch(0.5F), 0.5F);

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);

    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return EarthWitch_Texture;

    }

    protected int shouldRenderPass(EntityEarthWitch p_77032_1_, int p_77032_2_, float p_77032_3_) {

        if (p_77032_1_.isArmored()) {

            if (p_77032_1_.isInvisible()) {
                GL11.glDepthMask(false);
            } else {
                GL11.glDepthMask(true);
            }

            if (p_77032_2_ == 1) {
                float f1 = (float) p_77032_1_.ticksExisted + p_77032_3_;
                this.bindTexture(armoredblitsuneTextures);
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = f1 * 0.01F;
                float f3 = f1 * 0.01F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.setRenderPassModel(witchModel);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float f4 = 0.5F;
                GL11.glColor4f(f4, f4, f4, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                return 1;
            }

            if (p_77032_2_ == 2) {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);


            }
        }

        return -1;
    }


    protected int inheritRenderPass(EntityEarthWitch p_77035_1_, int p_77035_2_, float p_77035_3_) {
        return -1;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((EntityEarthWitch) p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_) {
        return this.inheritRenderPass((EntityEarthWitch) p_77035_1_, p_77035_2_, p_77035_3_);
    }

}


