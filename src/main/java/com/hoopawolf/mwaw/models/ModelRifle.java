package com.hoopawolf.mwaw.models;

import com.hoopawolf.mwaw.skills.EntityRifle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRifle extends ModelBase {
    //fields
    ModelRenderer Shape1;

    public ModelRifle() {
        textureWidth = 128;
        textureHeight = 128;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, -6F, -8F, 0, 11, 17);
        Shape1.setRotationPoint(0F, 9F, 0F);
        Shape1.setTextureSize(128, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        EntityRifle rifle = (EntityRifle) entity;

        Shape1.rotationPointY = rifle.getFloatingRotation();

        Shape1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ////////////////////////////////PARTS///////////////////////////////////////
        this.Shape1.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Shape1.rotateAngleY = f3 / (180F / (float) Math.PI);
    }

}
