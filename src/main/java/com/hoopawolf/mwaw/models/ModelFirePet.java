package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFirePet extends ModelBase {
    //fields
    ModelRenderer body1;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer rightlegup;
    ModelRenderer rightlegbackup;
    ModelRenderer leftlegup;
    ModelRenderer leftlegbackup;
    ModelRenderer rightlegdown;
    ModelRenderer rightlegbackdown;
    ModelRenderer leftlegdown;
    ModelRenderer leftlegbackdown;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head7;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;

    public ModelFirePet() {
        textureWidth = 128;
        textureHeight = 32;

        body1 = new ModelRenderer(this, 22, 0);
        body1.addBox(0F, 0F, 0F, 5, 4, 4);
        body1.setRotationPoint(0F, 10F, 0F);
        body1.setTextureSize(128, 32);
        body1.mirror = true;
        setRotation(body1, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 44, 0);
        body2.addBox(0F, 0F, 0F, 3, 4, 4);
        body2.setRotationPoint(1F, 10F, 3F);
        body2.setTextureSize(128, 32);
        body2.mirror = true;
        setRotation(body2, -0.122173F, 0F, 0F);
        body3 = new ModelRenderer(this, 0, 6);
        body3.addBox(0F, 0F, 0F, 2, 1, 3);
        body3.setRotationPoint(1.486667F, 9F, 1F);
        body3.setTextureSize(128, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        rightlegup = new ModelRenderer(this, 18, 11);
        rightlegup.addBox(0F, 0F, 0F, 1, 2, 2);
        rightlegup.setRotationPoint(5F, 12F, 0F);
        rightlegup.setTextureSize(128, 32);
        rightlegup.mirror = true;
        setRotation(rightlegup, 0F, 0F, 0F);
        rightlegbackup = new ModelRenderer(this, 18, 11);
        rightlegbackup.addBox(0F, 0F, 0F, 1, 2, 2);
        rightlegbackup.setRotationPoint(4F, 12F, 4.5F);
        rightlegbackup.setTextureSize(128, 32);
        rightlegbackup.mirror = true;
        setRotation(rightlegbackup, 0F, 0F, 0F);
        leftlegup = new ModelRenderer(this, 18, 11);
        leftlegup.addBox(0F, 0F, 0F, 1, 2, 2);
        leftlegup.setRotationPoint(-1F, 12F, 0F);
        leftlegup.setTextureSize(128, 32);
        leftlegup.mirror = true;
        setRotation(leftlegup, 0F, 0F, 0F);
        leftlegbackup = new ModelRenderer(this, 18, 11);
        leftlegbackup.addBox(0F, 0F, 0F, 1, 2, 2);
        leftlegbackup.setRotationPoint(0F, 12F, 4.5F);
        leftlegbackup.setTextureSize(128, 32);
        leftlegbackup.mirror = true;
        setRotation(leftlegbackup, 0F, 0F, 0F);
        rightlegdown = new ModelRenderer(this, 0, 27);
        rightlegdown.addBox(0F, 0F, 2F, 1, 2, 1);
        rightlegdown.setRotationPoint(5F, 12F, 0F);
        rightlegdown.setTextureSize(128, 32);
        rightlegdown.mirror = true;
        convertToChild(rightlegup, rightlegdown);
        setRotation(rightlegdown, -0.6806784F, 0F, 0F);
        rightlegbackdown = new ModelRenderer(this, 0, 27);
        rightlegbackdown.addBox(0F, 0F, 2F, 1, 2, 1);
        rightlegbackdown.setRotationPoint(4F, 12F, 4.5F);
        rightlegbackdown.setTextureSize(128, 32);
        rightlegbackdown.mirror = true;
        convertToChild(rightlegbackup, rightlegbackdown);
        setRotation(rightlegbackdown, -0.6806784F, 0F, 0F);
        leftlegdown = new ModelRenderer(this, 0, 27);
        leftlegdown.addBox(0F, 0F, 2F, 1, 2, 1);
        leftlegdown.setRotationPoint(-1F, 12F, 0F);
        leftlegdown.setTextureSize(128, 32);
        leftlegdown.mirror = true;
        convertToChild(leftlegup, leftlegdown);
        setRotation(leftlegdown, -0.6806784F, 0F, 0F);
        leftlegbackdown = new ModelRenderer(this, 0, 27);
        leftlegbackdown.addBox(0F, 0F, 2F, 1, 2, 1);
        leftlegbackdown.setRotationPoint(0F, 12F, 4.5F);
        leftlegbackdown.setTextureSize(128, 32);
        leftlegbackdown.mirror = true;
        convertToChild(leftlegbackup, leftlegbackdown);
        setRotation(leftlegbackdown, -0.6806784F, 0F, 0F);
        head1 = new ModelRenderer(this, 26, 10);
        head1.addBox(-3F, -2F, -2F, 6, 5, 4);
        head1.setRotationPoint(2.5F, 10F, -1F);
        head1.setTextureSize(128, 32);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 0, 12);
        head2.addBox(-0.5F, -2F, -3.5F, 4, 4, 4);
        head2.setRotationPoint(2.5F, 10F, -1F);
        head2.setTextureSize(128, 32);
        head2.mirror = true;
        convertToChild(head1, head2);
        setRotation(head2, 0.0523599F, 0.7853982F, 0.0523599F);
        head3 = new ModelRenderer(this, 0, 21);
        head3.addBox(-1F, 1F, -4F, 2, 1, 3);
        head3.setRotationPoint(2.5F, 10F, -1F);
        head3.setTextureSize(128, 32);
        head3.mirror = true;
        convertToChild(head1, head3);
        setRotation(head3, 0.1570796F, 0F, 0F);
        head4 = new ModelRenderer(this, 10, 27);
        head4.addBox(2.5F, -1F, 2F, 1, 1, 3);
        head4.setRotationPoint(2.5F, 10F, -1F);
        head4.setTextureSize(128, 32);
        head4.mirror = true;
        convertToChild(head1, head4);
        setRotation(head4, 0.4712389F, 0F, 0F);
        head5 = new ModelRenderer(this, 10, 27);
        head5.addBox(-3.5F, -1F, 2F, 1, 1, 3);
        head5.setRotationPoint(2.5F, 10F, -1F);
        head5.setTextureSize(128, 32);
        head5.mirror = true;
        convertToChild(head1, head5);
        setRotation(head5, 0.4712389F, 0F, 0F);
        head6 = new ModelRenderer(this, 11, 0);
        head6.addBox(-1.5F, -3F, 1F, 3, 2, 2);
        head6.setRotationPoint(2.5F, 10F, -1F);
        head6.setTextureSize(128, 32);
        head6.mirror = true;
        convertToChild(head1, head6);
        setRotation(head6, 0F, 0F, 0F);
        head7 = new ModelRenderer(this, 11, 6);
        head7.addBox(-0.5F, -3F, 3F, 1, 1, 2);
        head7.setRotationPoint(2.5F, 10F, -1F);
        head7.setTextureSize(128, 32);
        head7.mirror = true;
        convertToChild(head1, head7);
        setRotation(head7, 0.2094395F, 0F, 0F);
        tail1 = new ModelRenderer(this, 61, 0);
        tail1.addBox(0F, 0F, 0F, 1, 1, 4);
        tail1.setRotationPoint(2F, 11F, 6F);
        tail1.setTextureSize(128, 32);
        tail1.mirror = true;
        setRotation(tail1, 0F, 0F, 0F);
        tail2 = new ModelRenderer(this, 15, 22);
        tail2.addBox(0F, 1F, 3F, 1, 1, 2);
        tail2.setRotationPoint(2F, 11F, 6F);
        tail2.setTextureSize(128, 32);
        tail2.mirror = true;
        convertToChild(tail1, tail2);
        setRotation(tail2, 0.2443461F, 0F, 0F);
        tail3 = new ModelRenderer(this, 26, 23);
        tail3.addBox(0F, -1F, 5F, 1, 2, 2);
        tail3.setRotationPoint(2F, 11F, 6F);
        tail3.setTextureSize(128, 32);
        tail3.mirror = true;
        convertToChild(tail1, tail3);
        setRotation(tail3, 0F, 0F, 0F);
        tail4 = new ModelRenderer(this, 34, 22);
        tail4.addBox(0F, -2F, 6F, 1, 1, 2);
        tail4.setRotationPoint(2F, 11F, 6F);
        tail4.setTextureSize(128, 32);
        tail4.mirror = true;
        convertToChild(tail1, tail4);
        setRotation(tail4, 0F, 0F, 0F);
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
        GL11.glTranslatef(-0.3F, 0.1F, -0.3F);

        body1.render(f5);
        body2.render(f5);
        body3.render(f5);
        rightlegup.render(f5);
        rightlegbackup.render(f5);
        leftlegup.render(f5);
        leftlegbackup.render(f5);
        head1.render(f5);
        tail1.render(f5);
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

///////////////////////////////LEGS///////////////////////////////////////
        this.leftlegup.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.rightlegup.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;

        this.rightlegbackup.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leftlegbackup.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
    }

}

