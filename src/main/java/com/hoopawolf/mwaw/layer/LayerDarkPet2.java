package com.hoopawolf.mwaw.layer;

import com.hoopawolf.mwaw.entity.EntityDarkPet2;
import com.hoopawolf.mwaw.entity.RenderDarkPet2;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.models.ModelDarkPet2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerDarkPet2 implements LayerRenderer {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/dark_body.png");
    private final RenderDarkPet2 creeperRenderer;
    private final ModelDarkPet2 creeperModel = new ModelDarkPet2();

    public LayerDarkPet2(RenderDarkPet2 p_i46121_1_) {
        this.creeperRenderer = p_i46121_1_;
    }

    public void doRenderLayer(EntityDarkPet2 p_177169_1_, float p_177169_2_, float p_177169_3_, float p_177169_4_, float p_177169_5_, float p_177169_6_, float p_177169_7_, float p_177169_8_) {
        GlStateManager.depthMask(!p_177169_1_.isInvisible());
        this.creeperRenderer.bindTexture(LIGHTNING_TEXTURE);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        float f7 = (float) p_177169_1_.ticksExisted + p_177169_4_;
        GlStateManager.translate(f7 * 0.01F, f7 * 0.01F, 0.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.enableBlend();
        float f8 = 1.5F;
        GlStateManager.color(f8, f8, f8, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(1, 1);
        this.creeperModel.setModelAttributes(this.creeperRenderer.getMainModel());
        this.creeperModel.render(p_177169_1_, p_177169_2_, p_177169_3_, p_177169_5_, p_177169_6_, p_177169_7_, p_177169_8_);
        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        this.doRenderLayer((EntityDarkPet2) p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}