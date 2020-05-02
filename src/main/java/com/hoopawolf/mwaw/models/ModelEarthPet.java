package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelEarthPet extends ModelBase {
    //fields
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer arm3;
    ModelRenderer arm4;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer cape1;

    public ModelEarthPet() {
        textureWidth = 64;
        textureHeight = 32;

        body1 = new ModelRenderer(this, 13, 0);
        body1.addBox(0F, 0F, 0F, 5, 2, 4);
        body1.setRotationPoint(0F, 9F, 0F);
        body1.setTextureSize(64, 32);
        body1.mirror = true;
        setRotation(body1, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 32, 0);
        body2.addBox(0F, 0F, 0F, 3, 2, 3);
        body2.setRotationPoint(1F, 11F, 0.5F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        body3 = new ModelRenderer(this, 0, 6);
        body3.addBox(0F, 0F, 0F, 1, 1, 1);
        body3.setRotationPoint(2F, 13F, 1.5F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        body4 = new ModelRenderer(this, 5, 6);
        body4.addBox(0F, 0F, 0F, 1, 2, 2);
        body4.setRotationPoint(-1.1F, 8.9F, -0.1F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, 0.2268928F, 0F, 0F);
        body5 = new ModelRenderer(this, 5, 6);
        body5.addBox(0F, 0F, 0F, 1, 2, 2);
        body5.setRotationPoint(5.1F, 8.9F, -0.1F);
        body5.setTextureSize(64, 32);
        body5.mirror = true;
        setRotation(body5, 0.2268928F, 0F, 0F);
        arm1 = new ModelRenderer(this, 0, 9);
        arm1.addBox(0F, 0F, 0F, 1, 1, 1);
        arm1.setRotationPoint(-2F, 9F, -1F);
        arm1.setTextureSize(64, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, 0F);
        arm2 = new ModelRenderer(this, 0, 9);
        arm2.addBox(0F, 0F, 0F, 1, 1, 1);
        arm2.setRotationPoint(6F, 9F, -1F);
        arm2.setTextureSize(64, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0F);
        arm3 = new ModelRenderer(this, 12, 8);
        arm3.addBox(0F, 0F, 0F, 1, 1, 2);
        arm3.setRotationPoint(7F, 9.5F, -2.5F);
        arm3.setTextureSize(64, 32);
        arm3.mirror = true;
        setRotation(arm3, 0.3316126F, 0F, 0F);
        arm4 = new ModelRenderer(this, 12, 8);
        arm4.addBox(0F, 0F, 0F, 1, 1, 2);
        arm4.setRotationPoint(-3F, 9.5F, -2.5F);
        arm4.setTextureSize(64, 32);
        arm4.mirror = true;
        setRotation(arm4, 0.3316126F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 21);
        head1.addBox(-3F, -2F, -2F, 7, 5, 5);
        head1.setRotationPoint(2F, 6F, 1.5F);
        head1.setTextureSize(64, 32);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 0);
        head2.addBox(-0.5F, -3F, -0.5F, 2, 2, 3);
        head2.setRotationPoint(2F, 6F, 1.5F);
        head2.setTextureSize(64, 32);
        head2.mirror = true;
        convertToChild(head1, head2);
        setRotation(head2, 0F, 0F, 0F);
        head3 = new ModelRenderer(this, 19, 7);
        head3.addBox(-3.1F, -2.5F, 0.5F, 1, 1, 2);
        head3.setRotationPoint(2F, 6F, 1.5F);
        head3.setTextureSize(64, 32);
        head3.mirror = true;
        convertToChild(head1, head3);
        setRotation(head3, 0F, 0F, 0F);
        head4 = new ModelRenderer(this, 19, 7);
        head4.addBox(3.1F, -2.5F, 0.5F, 1, 1, 2);
        head4.setRotationPoint(2F, 6F, 1.5F);
        head4.setTextureSize(64, 32);
        head4.mirror = true;
        convertToChild(head1, head4);
        setRotation(head4, 0F, 0F, 0F);
        cape1 = new ModelRenderer(this, 0, 12);
        cape1.addBox(-2F, 0F, 0F, 5, 1, 7);
        cape1.setRotationPoint(2F, 7F, 4F);
        cape1.setTextureSize(64, 32);
        cape1.mirror = true;
        setRotation(cape1, -1.570796F, 0F, 0F);
    }

    protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
        // move child rotation point to be relative to parent
        parChild.rotationPointX -= parParent.rotationPointX;
        parChild.rotationPointY -= parParent.rotationPointY;
        parChild.rotationPointZ -= parParent.rotationPointZ;
        // make rotations relative to parent
        parChild.rotateAngleX -= parParent.rotateAngleX;
        parChild.rotateAngleY -= parParent.rotateAngleY;
        parChild.rotateAngleZ -= parParent.rotateAngleZ;
        // create relationship
        parParent.addChild(parChild);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        GL11.glScalef(1.5F, 1.5F, 1.5F);
        GL11.glTranslatef(-0.3F, -0.1F, -0.3F);

        body1.render(f5);
        body2.render(f5);
        body3.render(f5);
        body4.render(f5);
        body5.render(f5);
        arm1.render(f5);
        arm2.render(f5);
        arm3.render(f5);
        arm4.render(f5);
        head1.render(f5);
        cape1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

////////////////////////////////HEAD///////////////////////////////////////
        this.head1.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.head1.rotateAngleY = f3 / (180F / (float) Math.PI);
    }

}

