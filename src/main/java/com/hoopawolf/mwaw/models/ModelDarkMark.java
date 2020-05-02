package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelDarkMark extends ModelBase {
    //fields
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer horn3;
    ModelRenderer horn4;
    ModelRenderer horn5;
    ModelRenderer horn6;

    public ModelDarkMark() {
        textureWidth = 256;
        textureHeight = 256;

        head1 = new ModelRenderer(this, 0, 125);
        head1.addBox(7F, -9F, -5F, 3, 20, 15);
        head1.setRotationPoint(0F, 5F, -3F);
        head1.setTextureSize(256, 256);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 197);
        head2.addBox(-7F, -9F, -5F, 14, 3, 15);
        head2.setRotationPoint(0F, 5F, -3F);
        head2.setTextureSize(256, 256);
        head2.mirror = true;
        convertToChild(head1, head2);
        setRotation(head2, 0F, 0F, 0F);
        head3 = new ModelRenderer(this, 0, 168);
        head3.addBox(-10F, -9F, 10F, 20, 20, 3);
        head3.setRotationPoint(0F, 5F, -3F);
        head3.setTextureSize(256, 256);
        head3.mirror = true;
        convertToChild(head1, head3);
        setRotation(head3, 0F, 0F, 0F);
        head4 = new ModelRenderer(this, 0, 83);
        head4.addBox(-10F, -9F, -5F, 3, 20, 15);
        head4.setRotationPoint(0F, 5F, -3F);
        head4.setTextureSize(256, 256);
        head4.mirror = true;
        convertToChild(head1, head4);
        setRotation(head4, 0F, 0F, 0F);
        head5 = new ModelRenderer(this, 0, 48);
        head5.addBox(-10F, -9F, -8F, 20, 20, 3);
        head5.setRotationPoint(0F, 5F, -3F);
        head5.setTextureSize(256, 256);
        head5.mirror = true;
        convertToChild(head1, head5);
        setRotation(head5, 0F, 0F, 0F);
        horn1 = new ModelRenderer(this, 0, 0);
        horn1.addBox(-1F, -14F, 0F, 7, 5, 7);
        horn1.setRotationPoint(0F, 5F, -3F);
        horn1.setTextureSize(256, 256);
        horn1.mirror = true;
        convertToChild(head1, horn1);
        setRotation(horn1, 0F, 0F, 0.5061455F);
        horn2 = new ModelRenderer(this, 0, 0);
        horn2.addBox(0.5F, -18F, 0.5F, 6, 5, 6);
        horn2.setRotationPoint(0F, 5F, -3F);
        horn2.setTextureSize(256, 256);
        horn2.mirror = true;
        convertToChild(head1, horn2);
        setRotation(horn2, 0F, 0F, 0.4363323F);
        horn3 = new ModelRenderer(this, 0, 0);
        horn3.addBox(-6F, -14F, 0F, 7, 5, 7);
        horn3.setRotationPoint(0F, 5F, -3F);
        horn3.setTextureSize(256, 256);
        horn3.mirror = true;
        convertToChild(head1, horn3);
        setRotation(horn3, 0F, 0F, -0.5061455F);
        horn4 = new ModelRenderer(this, 0, 0);
        horn4.addBox(-9F, -25F, 2F, 3, 5, 3);
        horn4.setRotationPoint(0F, 5F, -3F);
        horn4.setTextureSize(256, 256);
        horn4.mirror = true;
        convertToChild(head1, horn4);
        setRotation(horn4, 0F, 0F, -0.2094395F);
        horn5 = new ModelRenderer(this, 0, 0);
        horn5.addBox(-6.5F, -18F, 0.5F, 6, 5, 6);
        horn5.setRotationPoint(0F, 5F, -3F);
        horn5.setTextureSize(256, 256);
        horn5.mirror = true;
        convertToChild(head1, horn5);
        setRotation(horn5, 0F, 0F, -0.4363323F);
        horn6 = new ModelRenderer(this, 0, 0);
        horn6.addBox(-8F, -22F, 1F, 5, 5, 5);
        horn6.setRotationPoint(0F, 5F, -3F);
        horn6.setTextureSize(256, 256);
        horn6.mirror = true;
        convertToChild(head1, horn6);
        setRotation(horn6, 0F, 0F, -0.3141593F);
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

        head1.render(f5);
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

