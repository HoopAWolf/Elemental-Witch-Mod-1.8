package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class ModelEgg extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;

    public ModelEgg() {
        textureWidth = 128;
        textureHeight = 128;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 7, 1, 7);
        Shape1.setRotationPoint(-3F, 10F, -3F);
        Shape1.setTextureSize(128, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 9, 1, 9);
        Shape2.setRotationPoint(-4F, 11F, -4F);
        Shape2.setTextureSize(128, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 10, 10, 10);
        Shape3.setRotationPoint(-4.5F, 12F, -4.5F);
        Shape3.setTextureSize(128, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 9, 1, 9);
        Shape4.setRotationPoint(-4F, 22F, -4F);
        Shape4.setTextureSize(128, 128);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 7, 1, 7);
        Shape5.setRotationPoint(-3F, 23F, -3F);
        Shape5.setTextureSize(128, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase) {

            GL11.glTranslatef(0F, 1.2F, 0F);

        } else {

            GL11.glTranslatef(0F, 0F, 0F);

        }

        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

