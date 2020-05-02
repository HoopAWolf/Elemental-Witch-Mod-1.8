package com.hoopawolf.mwaw.skills;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWaterMinion extends RenderLiving {
    private static final ResourceLocation WaterMinion_Texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/waterminion.png");

    public RenderWaterMinion(RenderManager p_i46149_1_, final ModelBase par1ModelBase, final float par2) {
        super(p_i46149_1_, par1ModelBase, par2);

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(0.9F, 0.9F, 0.9F, 0.7F);
        super.doRender((EntityLiving) par1EntityLiving, par2, par4, par6, par8, par9);
        GL11.glDisable(GL11.GL_BLEND);

    }


    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return WaterMinion_Texture;

    }

}
