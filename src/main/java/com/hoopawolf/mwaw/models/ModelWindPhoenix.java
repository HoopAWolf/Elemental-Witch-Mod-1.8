package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWindPhoenix extends ModelBase {
    //fields
    ModelRenderer Chest;
    ModelRenderer Right_Leg;
    ModelRenderer Right_Toe_1;
    ModelRenderer Left_Leg;
    ModelRenderer Right_Toe_2;
    ModelRenderer Left_Toe_1;
    ModelRenderer Left_Toe_2;
    ModelRenderer Beak_Lower;
    ModelRenderer Base;
    ModelRenderer Head;
    ModelRenderer Neck;
    ModelRenderer Left_Wing;
    ModelRenderer Beak_Upper;
    ModelRenderer Right_Wing;
    ModelRenderer HeadFeathers2;
    ModelRenderer HeadFeathers3;
    ModelRenderer TailFeathers1;
    ModelRenderer HeadFeathers6;
    ModelRenderer TaileFeathers2;
    ModelRenderer TailFeathers3;
    ModelRenderer TailFeathers4;
    ModelRenderer TailFeathers5;
    ModelRenderer TailFeathers6;
    ModelRenderer TailFeathers7;

    public ModelWindPhoenix() {
        textureWidth = 128;
        textureHeight = 64;

        Chest = new ModelRenderer(this, 0, 15);
        Chest.addBox(0F, 0F, 0F, 6, 5, 3);
        Chest.setRotationPoint(-3F, 16F, -6F);
        Chest.setTextureSize(128, 64);
        Chest.mirror = true;
        setRotation(Chest, 0.6457718F, 0F, 0F);
        Right_Leg = new ModelRenderer(this, 22, 41);
        Right_Leg.addBox(-1F, -1F, -1F, 2, 5, 2);
        Right_Leg.setRotationPoint(-1.866667F, 20F, 1F);
        Right_Leg.setTextureSize(128, 64);
        Right_Leg.mirror = true;
        setRotation(Right_Leg, 0F, 0F, 0F);
        Right_Toe_1 = new ModelRenderer(this, 22, 50);
        Right_Toe_1.addBox(1.3F, -17F, -3.8F, 1, 1, 3);
        Right_Toe_1.setRotationPoint(-1.9F, 20F, 1F);
        Right_Toe_1.setTextureSize(128, 64);
        Right_Toe_1.mirror = true;
        setRotation(Right_Toe_1, 0F, -0.2617994F, 0F);
        Left_Leg = new ModelRenderer(this, 22, 56);
        Left_Leg.addBox(-1F, -1F, -1F, 2, 5, 2);
        Left_Leg.setRotationPoint(1.9F, 20F, 1F);
        Left_Leg.setTextureSize(128, 64);
        Left_Leg.mirror = true;
        setRotation(Left_Leg, 0F, 0F, 0F);
        Right_Toe_2 = new ModelRenderer(this, 12, 50);
        Right_Toe_2.addBox(1.2F, -17F, -2.9F, 1, 1, 3);
        Right_Toe_2.setRotationPoint(-1.9F, 20F, 1F);
        Right_Toe_2.setTextureSize(128, 64);
        Right_Toe_2.mirror = true;
        setRotation(Right_Toe_2, 0F, 0.2617994F, 0F);
        Left_Toe_1 = new ModelRenderer(this, 12, 44);
        Left_Toe_1.addBox(-2.2F, -17F, -3F, 1, 1, 3);
        Left_Toe_1.setRotationPoint(1.9F, 20F, 1F);
        Left_Toe_1.setTextureSize(128, 64);
        Left_Toe_1.mirror = true;
        setRotation(Left_Toe_1, 0F, -0.2617994F, 0F);
        Left_Toe_2 = new ModelRenderer(this, 22, 22);
        Left_Toe_2.addBox(-2.3F, -17F, -4.0F, 1, 1, 3);
        Left_Toe_2.setRotationPoint(1.9F, 20F, 1F);
        Left_Toe_2.setTextureSize(128, 64);
        Left_Toe_2.mirror = true;
        setRotation(Left_Toe_2, 0F, 0.2617994F, 0F);
        Beak_Lower = new ModelRenderer(this, 0, 58);
        Beak_Lower.addBox(-3.5F, -0.8F, -9F, 5, 2, 3);
        Beak_Lower.setRotationPoint(1F, 13F, -1F);
        Beak_Lower.setTextureSize(128, 64);
        Beak_Lower.mirror = true;
        setRotation(Beak_Lower, 0F, 0F, 0F);
        Base = new ModelRenderer(this, 60, 0);
        Base.addBox(0F, 0F, 0F, 6, 8, 7);
        Base.setRotationPoint(-3F, 12F, -3F);
        Base.setTextureSize(128, 64);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 30, 0);
        Head.addBox(-4.5F, -5F, -7F, 7, 7, 7);
        Head.setRotationPoint(1F, 13F, -1F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, -0.0872665F, 0F, 0F);
        Neck = new ModelRenderer(this, 0, 34);
        Neck.addBox(0F, 0F, 0F, 6, 4, 3);
        Neck.setRotationPoint(-3F, 12F, -6F);
        Neck.setTextureSize(128, 64);
        Neck.mirror = true;
        setRotation(Neck, 0F, 0F, 0F);
        Left_Wing = new ModelRenderer(this, 67, 21);
        Left_Wing.addBox(0F, 0F, 0F, 1, 4, 5);
        Left_Wing.setRotationPoint(2.5F, 13F, 0F);
        Left_Wing.setTextureSize(128, 64);
        Left_Wing.mirror = true;
        setRotation(Left_Wing, -0.6632251F, 0.1047198F, 0F);
        Beak_Upper = new ModelRenderer(this, 35, 34);
        Beak_Upper.addBox(-4F, -3.1F, -10F, 6, 4, 4);
        Beak_Upper.setRotationPoint(1F, 13F, -1F);
        Beak_Upper.setTextureSize(128, 64);
        Beak_Upper.mirror = true;
        setRotation(Beak_Upper, 0F, 0F, 0F);
        Right_Wing = new ModelRenderer(this, 34, 44);
        Right_Wing.addBox(0F, 0F, 0F, 1, 4, 5);
        Right_Wing.setRotationPoint(-3.5F, 13F, 0F);
        Right_Wing.setTextureSize(128, 64);
        Right_Wing.mirror = true;
        setRotation(Right_Wing, -0.6632251F, -0.1047198F, 0F);
        HeadFeathers2 = new ModelRenderer(this, 36, 21);
        HeadFeathers2.addBox(-5.6F, -17.5F, -11F, 1, 6, 5);
        HeadFeathers2.setRotationPoint(1F, 13F, -1F);
        HeadFeathers2.setTextureSize(128, 64);
        HeadFeathers2.mirror = true;
        setRotation(HeadFeathers2, -0.5759587F, 0F, 0F);
        HeadFeathers3 = new ModelRenderer(this, 52, 21);
        HeadFeathers3.addBox(0.5F, -17.5F, -11F, 1, 6, 5);
        HeadFeathers3.setRotationPoint(1F, 13F, -1F);
        HeadFeathers3.setTextureSize(128, 64);
        HeadFeathers3.mirror = true;
        setRotation(HeadFeathers3, -0.5759587F, 0F, 0F);
        TailFeathers1 = new ModelRenderer(this, 60, 36);
        TailFeathers1.addBox(0F, 0F, 0F, 1, 8, 5);
        TailFeathers1.setRotationPoint(2.1F, 10F, 4F);
        TailFeathers1.setTextureSize(128, 64);
        TailFeathers1.mirror = true;
        setRotation(TailFeathers1, -0.5759587F, 0.0174533F, 0.0174533F);
        HeadFeathers6 = new ModelRenderer(this, 0, 8);
        HeadFeathers6.addBox(-4.45F, -17.5F, -11F, 5, 4, 1);
        HeadFeathers6.setRotationPoint(1F, 13F, -1F);
        HeadFeathers6.setTextureSize(128, 64);
        HeadFeathers6.mirror = true;
        setRotation(HeadFeathers6, -0.5759587F, 0F, 0F);
        TaileFeathers2 = new ModelRenderer(this, 76, 34);
        TaileFeathers2.addBox(0F, 0F, 0F, 4, 7, 1);
        TaileFeathers2.setRotationPoint(-2F, 12F, 5.5F);
        TaileFeathers2.setTextureSize(128, 64);
        TaileFeathers2.mirror = true;
        setRotation(TaileFeathers2, -0.5759587F, 0F, 0F);
        TailFeathers3 = new ModelRenderer(this, 50, 51);
        TailFeathers3.addBox(0F, 0F, 0F, 1, 8, 5);
        TailFeathers3.setRotationPoint(-3.1F, 10F, 4F);
        TailFeathers3.setTextureSize(128, 64);
        TailFeathers3.mirror = true;
        setRotation(TailFeathers3, -0.5759587F, -0.0174533F, -0.0174533F);
        TailFeathers4 = new ModelRenderer(this, 90, 27);
        TailFeathers4.addBox(0F, 0F, 0F, 4, 4, 1);
        TailFeathers4.setRotationPoint(-2F, 10F, 4F);
        TailFeathers4.setTextureSize(128, 64);
        TailFeathers4.mirror = true;
        setRotation(TailFeathers4, -0.5759587F, 0F, 0F);
        TailFeathers5 = new ModelRenderer(this, 94, 15);
        TailFeathers5.addBox(0F, 0F, 0F, 4, 7, 1);
        TailFeathers5.setRotationPoint(-2F, 13F, 7F);
        TailFeathers5.setTextureSize(128, 64);
        TailFeathers5.mirror = true;
        setRotation(TailFeathers5, -0.5759587F, 0F, 0F);
        TailFeathers6 = new ModelRenderer(this, 78, 44);
        TailFeathers6.addBox(0F, 0F, 0F, 4, 7, 1);
        TailFeathers6.setRotationPoint(-2F, 13F, 6F);
        TailFeathers6.setTextureSize(128, 64);
        TailFeathers6.mirror = true;
        setRotation(TailFeathers6, -0.5759587F, 0F, 0F);
        TailFeathers7 = new ModelRenderer(this, 21, 30);
        TailFeathers7.addBox(0F, 0F, 0F, 4, 7, 1);
        TailFeathers7.setRotationPoint(-2F, 12F, 4F);
        TailFeathers7.setTextureSize(128, 64);
        TailFeathers7.mirror = true;
        setRotation(TailFeathers7, -0.5759587F, 0F, 0F);

        Head.addChild(HeadFeathers6);
        Head.addChild(HeadFeathers2);
        Head.addChild(HeadFeathers3);

        Right_Leg.addChild(Right_Toe_1);
        Right_Leg.addChild(Right_Toe_2);

        Left_Leg.addChild(Left_Toe_2);
        Left_Leg.addChild(Left_Toe_1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Chest.render(f5);
        Right_Leg.render(f5);
        Left_Leg.render(f5);
        Base.render(f5);
        Head.render(f5);
        Beak_Upper.render(f5);
        Beak_Lower.render(f5);
        Neck.render(f5);
        Left_Wing.render(f5);
        Right_Wing.render(f5);
        TailFeathers1.render(f5);
        TaileFeathers2.render(f5);
        TailFeathers3.render(f5);
        TailFeathers4.render(f5);
        TailFeathers5.render(f5);
        TailFeathers6.render(f5);
        TailFeathers7.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

////////////////////////////////HEAD///////////////////////////////////////
        this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);

        this.Beak_Upper.rotateAngleX = this.Head.rotateAngleX;
        this.Beak_Upper.rotateAngleY = this.Head.rotateAngleY;

        this.Beak_Lower.rotateAngleX = this.Head.rotateAngleX;
        this.Beak_Lower.rotateAngleY = this.Head.rotateAngleY;

///////////////////////////////LEGS///////////////////////////////////////
        this.Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;


//////////////////////////////WINGS////////////////////////////////////////
        if (!entity.onGround) {
            this.Left_Wing.rotateAngleZ = f2;
            this.Right_Wing.rotateAngleZ = -f2;
        } else {
            this.Left_Wing.rotateAngleZ = 0;
            this.Right_Wing.rotateAngleZ = 0;
        }

    }

}

