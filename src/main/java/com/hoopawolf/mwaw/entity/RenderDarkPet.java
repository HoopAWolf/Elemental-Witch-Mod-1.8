package com.hoopawolf.mwaw.entity;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderDarkPet extends RenderLiving {
    private static final ResourceLocation DarkPet_Texture = new ResourceLocation(Reference.MOD_ID + ":" + "textures/entity/darkpet.png");

    public RenderDarkPet(RenderManager p_i46149_1_, final ModelBase par1ModelBase, final float par2) {
        super(p_i46149_1_, par1ModelBase, par2);

    }

    @Override
    public void doRender(final EntityLiving par1EntityLiving,
                         final double par2, final double par4, final double par6,
                         final float par8, final float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);

    }

    protected int getColorMultiplier(EntityDarkPet p_77030_1_, float p_77030_2_, float p_77030_3_) {
        int f2 = p_77030_1_.deathTicks;

        return 10 << f2 + 225;
    }

    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {

        return DarkPet_Texture;

    }

    protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        return this.getColorMultiplier((EntityDarkPet) p_77030_1_, p_77030_2_, p_77030_3_);
    }
}


