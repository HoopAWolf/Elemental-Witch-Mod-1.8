package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.layer.LayerDarkPet2;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.models.ModelDarkPet2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDarkPet2 extends RenderLiving {

    private static final ResourceLocation DarkPet_Texture = new ResourceLocation(
            Reference.MOD_ID + ":" + "textures/entity/darkpet2.png");

    public RenderDarkPet2(RenderManager p_i46186_1_) {
        super(p_i46186_1_, new ModelDarkPet2(), 0.5F);
        this.addLayer(new LayerDarkPet2(this));

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);

    }

    protected int getColorMultiplier(EntityDarkPet2 p_77030_1_, float p_77030_2_, float p_77030_3_) {
        int f2 = p_77030_1_.deathTicks;

        return 10 << f2 + 225;
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return DarkPet_Texture;

    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        return this.getColorMultiplier((EntityDarkPet2) p_77030_1_, p_77030_2_, p_77030_3_);
    }

}

