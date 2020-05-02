package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelHalloweenPet extends ModelBase {
    //fields
    ModelRenderer Head;
    ModelRenderer Torso;
    ModelRenderer Right_Arm;
    ModelRenderer Right_Glove;
    ModelRenderer Right_Hand;
    ModelRenderer Left_Arm;
    ModelRenderer Left_Glove;
    ModelRenderer Left_Hand;
    ModelRenderer Right_Leg;
    ModelRenderer Right_Boot_1;
    ModelRenderer Right_Boot_2;
    ModelRenderer Right_Boot_3;
    ModelRenderer Left_Leg;
    ModelRenderer Left_Boot_1;
    ModelRenderer Left_Boot_2;
    ModelRenderer Left_Boot_3;

    public ModelHalloweenPet() {
        textureWidth = 128;
        textureHeight = 64;

        Head = new ModelRenderer(this, 42, 0);
        Head.addBox(-6F, -12F, -6F, 12, 12, 12);
        Head.setRotationPoint(0F, 11F, 0F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Torso = new ModelRenderer(this, 62, 24);
        Torso.addBox(-1F, 0F, -1F, 2, 5, 2);
        Torso.setRotationPoint(0F, 11F, 0F);
        Torso.setTextureSize(128, 64);
        Torso.mirror = true;
        setRotation(Torso, 0F, 0F, 0F);
        Right_Arm = new ModelRenderer(this, 58, 24);
        Right_Arm.addBox(0F, 0F, -0.5F, 1, 5, 1);
        Right_Arm.setRotationPoint(-1F, 12F, 0F);
        Right_Arm.setTextureSize(128, 64);
        Right_Arm.mirror = true;
        setRotation(Right_Arm, 0F, 0F, 0.6981317F);
        Right_Glove = new ModelRenderer(this, 50, 25);
        Right_Glove.addBox(-0.5F, 5F, -1F, 2, 2, 2);
        Right_Glove.setRotationPoint(-1F, 12F, 0F);
        Right_Glove.setTextureSize(128, 64);
        Right_Glove.mirror = true;
        setRotation(Right_Glove, 0F, 0F, 0.6981317F);
        Right_Hand = new ModelRenderer(this, 46, 26);
        Right_Hand.addBox(0F, 6.5F, -0.5F, 1, 1, 1);
        Right_Hand.setRotationPoint(-1F, 12F, 0F);
        Right_Hand.setTextureSize(128, 64);
        Right_Hand.mirror = true;
        setRotation(Right_Hand, 0F, 0F, 0.6981317F);
        Left_Arm = new ModelRenderer(this, 70, 24);
        Left_Arm.addBox(-1F, 0F, -0.5F, 1, 5, 1);
        Left_Arm.setRotationPoint(1F, 12F, 0F);
        Left_Arm.setTextureSize(128, 64);
        Left_Arm.mirror = true;
        setRotation(Left_Arm, 0F, 0F, -0.6981317F);
        Left_Glove = new ModelRenderer(this, 74, 25);
        Left_Glove.addBox(-1.5F, 5F, -1F, 2, 2, 2);
        Left_Glove.setRotationPoint(1F, 12F, 0F);
        Left_Glove.setTextureSize(128, 64);
        Left_Glove.mirror = true;
        setRotation(Left_Glove, 0F, 0F, -0.6981317F);
        Left_Hand = new ModelRenderer(this, 82, 26);
        Left_Hand.addBox(-1F, 6.5F, -0.5F, 1, 1, 1);
        Left_Hand.setRotationPoint(1F, 12F, 0F);
        Left_Hand.setTextureSize(128, 64);
        Left_Hand.mirror = true;
        setRotation(Left_Hand, 0F, 0F, -0.6981317F);
        Right_Leg = new ModelRenderer(this, 60, 31);
        Right_Leg.addBox(-1F, -1F, -0.5F, 1, 6, 1);
        Right_Leg.setRotationPoint(-1F, 16F, 0F);
        Right_Leg.setTextureSize(128, 64);
        Right_Leg.mirror = true;
        setRotation(Right_Leg, -0.0872665F, 0F, 0.122173F);
        Right_Boot_1 = new ModelRenderer(this, 57, 38);
        Right_Boot_1.addBox(-2F, 4F, -1.5F, 2, 2, 2);
        Right_Boot_1.setRotationPoint(-1F, 16F, 0F);
        Right_Boot_1.setTextureSize(128, 64);
        Right_Boot_1.mirror = true;
        setRotation(Right_Boot_1, 0F, 0F, 0F);
        Right_Boot_2 = new ModelRenderer(this, 51, 42);
        Right_Boot_2.addBox(-2.5F, 6F, -3F, 3, 2, 4);
        Right_Boot_2.setRotationPoint(-1F, 16F, 0F);
        Right_Boot_2.setTextureSize(128, 64);
        Right_Boot_2.mirror = true;
        setRotation(Right_Boot_2, 0F, 0F, 0F);
        Right_Boot_3 = new ModelRenderer(this, 59, 48);
        Right_Boot_3.addBox(-2F, 7F, -3.5F, 2, 1, 1);
        Right_Boot_3.setRotationPoint(-1F, 16F, 0F);
        Right_Boot_3.setTextureSize(128, 64);
        Right_Boot_3.mirror = true;
        setRotation(Right_Boot_3, 0F, 0F, 0F);
        Left_Leg = new ModelRenderer(this, 68, 31);
        Left_Leg.addBox(0F, -1F, -0.5F, 1, 6, 1);
        Left_Leg.setRotationPoint(1F, 16F, 0F);
        Left_Leg.setTextureSize(128, 64);
        Left_Leg.mirror = true;
        setRotation(Left_Leg, -0.0872665F, 0F, -0.122173F);
        Left_Boot_1 = new ModelRenderer(this, 67, 38);
        Left_Boot_1.addBox(0F, 4F, -1.5F, 2, 2, 2);
        Left_Boot_1.setRotationPoint(1F, 16F, 0F);
        Left_Boot_1.setTextureSize(128, 64);
        Left_Boot_1.mirror = true;
        setRotation(Left_Boot_1, 0F, 0F, 0F);
        Left_Boot_2 = new ModelRenderer(this, 67, 42);
        Left_Boot_2.addBox(-0.5F, 6F, -3F, 3, 2, 4);
        Left_Boot_2.setRotationPoint(1F, 16F, 0F);
        Left_Boot_2.setTextureSize(128, 64);
        Left_Boot_2.mirror = true;
        setRotation(Left_Boot_2, 0F, 0F, 0F);
        Left_Boot_3 = new ModelRenderer(this, 67, 48);
        Left_Boot_3.addBox(0F, 7F, -3.5F, 2, 1, 1);
        Left_Boot_3.setRotationPoint(1F, 16F, 0F);
        Left_Boot_3.setTextureSize(128, 64);
        Left_Boot_3.mirror = true;
        setRotation(Left_Boot_3, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Head.render(f5);
        Torso.render(f5);
        Right_Arm.render(f5);
        Right_Glove.render(f5);
        Right_Hand.render(f5);
        Left_Arm.render(f5);
        Left_Glove.render(f5);
        Left_Hand.render(f5);
        Right_Leg.render(f5);
        Right_Boot_1.render(f5);
        Right_Boot_2.render(f5);
        Right_Boot_3.render(f5);
        Left_Leg.render(f5);
        Left_Boot_1.render(f5);
        Left_Boot_2.render(f5);
        Left_Boot_3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ////////////////////////////LEG//////////////////////////////////
        Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Right_Boot_1.rotateAngleX = Right_Leg.rotateAngleX;
        Right_Boot_2.rotateAngleX = Right_Leg.rotateAngleX;
        Right_Boot_3.rotateAngleX = Right_Leg.rotateAngleX;

        Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        Left_Boot_1.rotateAngleX = Left_Leg.rotateAngleX;
        Left_Boot_2.rotateAngleX = Left_Leg.rotateAngleX;
        Left_Boot_3.rotateAngleX = Left_Leg.rotateAngleX;

        ////////////////////////////HEAD//////////////////////////////////
        Head.rotateAngleY = f3 / (180F / (float) Math.PI);
        Head.rotateAngleX = f4 / (180F / (float) Math.PI);
    }

}

