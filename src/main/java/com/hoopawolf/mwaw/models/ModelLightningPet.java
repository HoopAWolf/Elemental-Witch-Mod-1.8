package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelLightningPet extends ModelBase {
    //fields
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer rightlegup;
    ModelRenderer rightlegdown;
    ModelRenderer leftlegup;
    ModelRenderer leftlegdown;
    ModelRenderer leftlegbackup;
    ModelRenderer leftlegbackdown;
    ModelRenderer rightlegbackup;
    ModelRenderer rightlegbackdown;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head7;
    ModelRenderer head8;
    ModelRenderer head9;
    ModelRenderer head10;
    ModelRenderer head11;
    ModelRenderer wing1;
    ModelRenderer wing2;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;

    public ModelLightningPet() {
        textureWidth = 128;
        textureHeight = 32;

        body = new ModelRenderer(this, 34, 17);
        body.addBox(0F, 0F, 0F, 5, 4, 4);
        body.setRotationPoint(0F, 10F, 0F);
        body.setTextureSize(128, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        body2 = new ModelRenderer(this, 56, 10);
        body2.addBox(0F, 0F, 0F, 3, 4, 4);
        body2.setRotationPoint(1F, 10F, 3F);
        body2.setTextureSize(128, 32);
        body2.mirror = true;
        setRotation(body2, -0.122173F, 0F, 0F);
        rightlegup = new ModelRenderer(this, 11, 25);
        rightlegup.addBox(0F, 0F, 0F, 1, 3, 2);
        rightlegup.setRotationPoint(5F, 11.5F, 0F);
        rightlegup.setTextureSize(128, 32);
        rightlegup.mirror = true;
        setRotation(rightlegup, 0F, 0F, 0F);
        rightlegdown = new ModelRenderer(this, 6, 3);
        rightlegdown.addBox(-0.5F, 1F, 2F, 1, 2, 1);
        rightlegdown.setRotationPoint(5F, 11.5F, 0F);
        rightlegdown.setTextureSize(128, 32);
        rightlegdown.mirror = true;
        convertToChild(rightlegup, rightlegdown);
        setRotation(rightlegdown, -0.6806784F, 0F, 0F);
        leftlegup = new ModelRenderer(this, 11, 25);
        leftlegup.addBox(0F, 0F, 0F, 1, 3, 2);
        leftlegup.setRotationPoint(-1F, 11.5F, 0F);
        leftlegup.setTextureSize(128, 32);
        leftlegup.mirror = true;
        setRotation(leftlegup, 0F, 0F, 0F);
        leftlegdown = new ModelRenderer(this, 6, 3);
        leftlegdown.addBox(0.5F, 1F, 2F, 1, 2, 1);
        leftlegdown.setRotationPoint(-1F, 11.5F, 0F);
        leftlegdown.setTextureSize(128, 32);
        leftlegdown.mirror = true;
        convertToChild(leftlegup, leftlegdown);
        setRotation(leftlegdown, -0.6806784F, 0F, 0F);
        leftlegbackup = new ModelRenderer(this, 11, 6);
        leftlegbackup.addBox(0F, 0F, 0F, 1, 2, 2);
        leftlegbackup.setRotationPoint(0F, 12F, 4.5F);
        leftlegbackup.setTextureSize(128, 32);
        leftlegbackup.mirror = true;
        setRotation(leftlegbackup, 0F, 0F, 0F);
        leftlegbackdown = new ModelRenderer(this, 6, 3);
        leftlegbackdown.addBox(0F, 1F, 1.5F, 1, 2, 1);
        leftlegbackdown.setRotationPoint(0F, 12F, 4.5F);
        leftlegbackdown.setTextureSize(128, 32);
        leftlegbackdown.mirror = true;
        convertToChild(leftlegbackup, leftlegbackdown);
        setRotation(leftlegbackdown, -0.6806784F, 0F, 0F);
        rightlegbackup = new ModelRenderer(this, 11, 6);
        rightlegbackup.addBox(0F, 0F, 0F, 1, 2, 2);
        rightlegbackup.setRotationPoint(4F, 12F, 4.5F);
        rightlegbackup.setTextureSize(128, 32);
        rightlegbackup.mirror = true;
        setRotation(rightlegbackup, 0F, 0F, 0F);
        rightlegbackdown = new ModelRenderer(this, 6, 3);
        rightlegbackdown.addBox(0F, 0.5F, 2F, 1, 2, 1);
        rightlegbackdown.setRotationPoint(4F, 12F, 4.5F);
        rightlegbackdown.setTextureSize(128, 32);
        rightlegbackdown.mirror = true;
        convertToChild(rightlegbackup, rightlegbackdown);
        setRotation(rightlegbackdown, -0.6806784F, 0F, 0F);
        head1 = new ModelRenderer(this, 0, 11);
        head1.addBox(-3F, -2F, -2F, 6, 5, 4);
        head1.setRotationPoint(2.5F, 10F, -1F);
        head1.setTextureSize(128, 32);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 20, 0);
        head2.addBox(-1.5F, -2F, -5F, 3, 3, 4);
        head2.setRotationPoint(2.5F, 10F, -1F);
        head2.setTextureSize(128, 32);
        head2.mirror = true;
        convertToChild(head1, head2);
        setRotation(head2, 0.3490659F, 0F, 0F);
        head3 = new ModelRenderer(this, 0, 23);
        head3.addBox(-1F, 1F, -4.5F, 2, 1, 3);
        head3.setRotationPoint(2.5F, 10F, -1F);
        head3.setTextureSize(128, 32);
        head3.mirror = true;
        convertToChild(head1, head3);
        setRotation(head3, 0.1570796F, 0F, 0F);
        head4 = new ModelRenderer(this, 14, 21);
        head4.addBox(2.5F, -1F, 1F, 1, 1, 2);
        head4.setRotationPoint(2.5F, 10F, -1F);
        head4.setTextureSize(128, 32);
        head4.mirror = true;
        convertToChild(head1, head4);
        setRotation(head4, 0.296706F, 0F, 0F);
        head5 = new ModelRenderer(this, 14, 21);
        head5.addBox(-3.5F, -1F, 1F, 1, 1, 2);
        head5.setRotationPoint(2.5F, 10F, -1F);
        head5.setTextureSize(128, 32);
        head5.mirror = true;
        convertToChild(head1, head5);
        setRotation(head5, 0.296706F, 0F, 0F);
        head6 = new ModelRenderer(this, 41, 0);
        head6.addBox(-1.5F, -3F, 1F, 3, 2, 4);
        head6.setRotationPoint(2.5F, 10F, -1F);
        head6.setTextureSize(128, 32);
        head6.mirror = true;
        convertToChild(head1, head6);
        setRotation(head6, 0F, 0F, 0F);
        head7 = new ModelRenderer(this, 22, 9);
        head7.addBox(-1F, -2F, 2F, 2, 2, 4);
        head7.setRotationPoint(2.5F, 10F, -1F);
        head7.setTextureSize(128, 32);
        head7.mirror = true;
        convertToChild(head1, head7);
        setRotation(head7, 0F, 0F, 0F);
        head8 = new ModelRenderer(this, 14, 21);
        head8.addBox(2.5F, 0F, 1F, 1, 1, 2);
        head8.setRotationPoint(2.5F, 10F, -1F);
        head8.setTextureSize(128, 32);
        head8.mirror = true;
        convertToChild(head1, head8);
        setRotation(head8, 0.1919862F, 0F, 0F);
        head9 = new ModelRenderer(this, 14, 21);
        head9.addBox(-3.5F, 0F, 1F, 1, 1, 2);
        head9.setRotationPoint(2.5F, 10F, -1F);
        head9.setTextureSize(128, 32);
        head9.mirror = true;
        convertToChild(head1, head9);
        setRotation(head9, 0.1919862F, 0F, 0F);
        head10 = new ModelRenderer(this, 36, 0);
        head10.addBox(-0.5F, -5F, -1F, 1, 4, 1);
        head10.setRotationPoint(2.5F, 10F, -1F);
        head10.setTextureSize(128, 32);
        head10.mirror = true;
        convertToChild(head1, head10);
        setRotation(head10, 0.1396263F, 0F, 0F);
        head11 = new ModelRenderer(this, 0, 0);
        head11.addBox(-0.5F, -4F, 0F, 1, 3, 1);
        head11.setRotationPoint(2.5F, 10F, -1F);
        head11.setTextureSize(128, 32);
        head11.mirror = true;
        convertToChild(head1, head11);
        setRotation(head11, 0.1396263F, 0F, 0F);
        wing1 = new ModelRenderer(this, 22, 21);
        wing1.addBox(0F, 0F, 0F, 2, 0, 2);
        wing1.setRotationPoint(5F, 11F, 2F);
        wing1.setTextureSize(128, 32);
        wing1.mirror = true;
        setRotation(wing1, 0F, 0F, 0F);
        wing2 = new ModelRenderer(this, 22, 21);
        wing2.addBox(0F, 0F, 0F, 2, 0, 2);
        wing2.setRotationPoint(-2F, 11F, 2F);
        wing2.setTextureSize(128, 32);
        wing2.mirror = true;
        setRotation(wing2, 0F, 0F, 0F);
        tail1 = new ModelRenderer(this, 38, 8);
        tail1.addBox(-1F, 0F, 0F, 2, 2, 4);
        tail1.setRotationPoint(2.5F, 11F, 6F);
        tail1.setTextureSize(128, 32);
        tail1.mirror = true;
        setRotation(tail1, 0.1396263F, 0F, 0F);
        tail2 = new ModelRenderer(this, 12, 0);
        tail2.addBox(-0.5F, -1F, 3F, 1, 2, 2);
        tail2.setRotationPoint(2.5F, 11F, 6F);
        tail2.setTextureSize(128, 32);
        tail2.mirror = true;
        convertToChild(tail1, tail2);
        setRotation(tail2, 0F, 0F, 0F);
        tail3 = new ModelRenderer(this, 0, 7);
        tail3.addBox(-0.5F, 2F, 3F, 1, 1, 2);
        tail3.setRotationPoint(2.5F, 11F, 6F);
        tail3.setTextureSize(128, 32);
        tail3.mirror = true;
        convertToChild(tail1, tail3);
        setRotation(tail3, 0.7853982F, 0F, 0F);
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
        GL11.glTranslatef(-0.3F, -0.05F, -0.3F);

        body.render(f5);
        body2.render(f5);
        rightlegup.render(f5);
        leftlegup.render(f5);
        leftlegbackup.render(f5);
        rightlegbackup.render(f5);
        head1.render(f5);
        wing1.render(f5);
        wing2.render(f5);
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

