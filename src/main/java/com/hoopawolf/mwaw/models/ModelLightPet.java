package com.hoopawolf.mwaw.models;

import com.hoopawolf.mwaw.entity.EntityLightPet;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightPet extends ModelBase {
    //fields
    ModelRenderer Head;
    ModelRenderer Right_Arm;
    ModelRenderer Left_Arm;
    ModelRenderer Torso;
    ModelRenderer Skirt_1;
    ModelRenderer Skirt_2;
    ModelRenderer Hair_1;
    ModelRenderer Hair_2;
    ModelRenderer Hair_3;
    ModelRenderer Hair_4;
    ModelRenderer Hair_5;
    ModelRenderer Hair_6;
    ModelRenderer Hair_7;
    ModelRenderer Hair_8;
    ModelRenderer Hair_9;
    ModelRenderer Hair_10;
    ModelRenderer Right_Ribbon;
    ModelRenderer Left_Ribbon;
    ModelRenderer Right_Cloud_1;
    ModelRenderer Right_Cloud_2;
    ModelRenderer Left_Cloud_1;
    ModelRenderer Left_Cloud_2;

    public ModelLightPet() {
        textureWidth = 128;
        textureHeight = 64;

        Head = new ModelRenderer(this, 55, 25);
        Head.addBox(-3.5F, -4F, -3F, 7, 4, 6);
        Head.setRotationPoint(0F, 13F, 0F);
        Head.setTextureSize(128, 64);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Right_Arm = new ModelRenderer(this, 55, 39);
        Right_Arm.addBox(-1F, 0F, -1F, 2, 4, 2);
        Right_Arm.setRotationPoint(-1F, 13F, 0F);
        Right_Arm.setTextureSize(128, 64);
        Right_Arm.mirror = true;
        setRotation(Right_Arm, 0F, 0F, 0.7330383F);
        Left_Arm = new ModelRenderer(this, 73, 39);
        Left_Arm.addBox(-1F, 0F, -1F, 2, 4, 2);
        Left_Arm.setRotationPoint(1F, 13F, 0F);
        Left_Arm.setTextureSize(128, 64);
        Left_Arm.mirror = true;
        setRotation(Left_Arm, 0F, 0F, -0.7330383F);
        Torso = new ModelRenderer(this, 61, 35);
        Torso.addBox(-2F, 0F, -1.5F, 4, 3, 3);
        Torso.setRotationPoint(0F, 13F, 0F);
        Torso.setTextureSize(128, 64);
        Torso.mirror = true;
        setRotation(Torso, 0F, 0F, 0F);
        Skirt_1 = new ModelRenderer(this, 59, 41);
        Skirt_1.addBox(-2.5F, 3F, -2F, 5, 1, 4);
        Skirt_1.setRotationPoint(0F, 13F, 0F);
        Skirt_1.setTextureSize(128, 64);
        Skirt_1.mirror = true;
        setRotation(Skirt_1, 0F, 0F, 0F);
        Skirt_2 = new ModelRenderer(this, 57, 46);
        Skirt_2.addBox(-3F, 4F, -2.5F, 6, 3, 5);
        Skirt_2.setRotationPoint(0F, 13F, 0F);
        Skirt_2.setTextureSize(128, 64);
        Skirt_2.mirror = true;
        setRotation(Skirt_2, 0F, 0F, 0F);
        Hair_1 = new ModelRenderer(this, 53, 10);
        Hair_1.addBox(-4F, -8F, -3.5F, 8, 4, 7);
        Hair_1.setRotationPoint(0F, 13F, 0F);
        Hair_1.setTextureSize(128, 64);
        Hair_1.mirror = true;
        this.convertToChild(Head, Hair_1);
        setRotation(Hair_1, 0F, 0F, 0F);
        Hair_2 = new ModelRenderer(this, 57, 4);
        Hair_2.addBox(-3F, -8F, -2.5F, 6, 1, 5);
        Hair_2.setRotationPoint(0F, 12F, 0F);
        Hair_2.setTextureSize(128, 64);
        Hair_2.mirror = true;
        this.convertToChild(Head, Hair_2);
        setRotation(Hair_2, 0F, 0F, 0F);
        Hair_3 = new ModelRenderer(this, 58, 21);
        Hair_3.addBox(-4.5F, -6.5F, -4F, 9, 3, 1);
        Hair_3.setRotationPoint(0F, 13F, 0F);
        Hair_3.setTextureSize(128, 64);
        Hair_3.mirror = true;
        this.convertToChild(Head, Hair_3);
        setRotation(Hair_3, 0F, 0F, 0F);
        Hair_4 = new ModelRenderer(this, 40, 15);
        Hair_4.addBox(-4F, -4F, -4F, 1, 2, 8);
        Hair_4.setRotationPoint(0F, 13F, 0F);
        Hair_4.setTextureSize(128, 64);
        Hair_4.mirror = true;
        this.convertToChild(Head, Hair_4);
        setRotation(Hair_4, 0F, 0F, 0F);
        Hair_5 = new ModelRenderer(this, 38, 16);
        Hair_5.addBox(-5F, -5F, 1F, 1, 3, 4);
        Hair_5.setRotationPoint(0F, 13F, 0F);
        Hair_5.setTextureSize(128, 64);
        Hair_5.mirror = true;
        this.convertToChild(Head, Hair_5);
        setRotation(Hair_5, 0.5235988F, -0.5235988F, 0F);
        Hair_6 = new ModelRenderer(this, 32, 14);
        Hair_6.addBox(-5F, -5F, 5F, 1, 2, 4);
        Hair_6.setRotationPoint(0F, 13F, 0F);
        Hair_6.setTextureSize(128, 64);
        Hair_6.mirror = true;
        this.convertToChild(Head, Hair_6);
        setRotation(Hair_6, 0.5235988F, -0.5235988F, 0F);
        Hair_7 = new ModelRenderer(this, 78, 15);
        Hair_7.addBox(3F, -4F, -4F, 1, 2, 8);
        Hair_7.setRotationPoint(0F, 13F, 0F);
        Hair_7.setTextureSize(128, 64);
        Hair_7.mirror = true;
        this.convertToChild(Head, Hair_7);
        setRotation(Hair_7, 0F, 0F, 0F);
        Hair_8 = new ModelRenderer(this, 88, 16);
        Hair_8.addBox(4F, -5F, 1F, 1, 3, 4);
        Hair_8.setRotationPoint(0F, 13F, 0F);
        Hair_8.setTextureSize(128, 64);
        Hair_8.mirror = true;
        this.convertToChild(Head, Hair_8);
        setRotation(Hair_8, 0.5235988F, 0.5235988F, 0F);
        Hair_9 = new ModelRenderer(this, 94, 14);
        Hair_9.addBox(4F, -5F, 5F, 1, 2, 4);
        Hair_9.setRotationPoint(0F, 13F, 0F);
        Hair_9.setTextureSize(128, 64);
        Hair_9.mirror = true;
        this.convertToChild(Head, Hair_9);
        setRotation(Hair_9, 0.5235988F, 0.5235988F, 0F);
        Hair_10 = new ModelRenderer(this, 47, 25);
        Hair_10.addBox(-3F, -4F, 3F, 6, 3, 1);
        Hair_10.setRotationPoint(0F, 13F, 0F);
        Hair_10.setTextureSize(128, 64);
        Hair_10.mirror = true;
        this.convertToChild(Head, Hair_10);
        setRotation(Hair_10, 0F, 0F, 0F);
        Right_Ribbon = new ModelRenderer(this, 62, 0);
        Right_Ribbon.addBox(4F, -8F, 2F, 2, 3, 1);
        Right_Ribbon.setRotationPoint(0F, 13F, 0F);
        Right_Ribbon.setTextureSize(128, 64);
        Right_Ribbon.mirror = true;
        this.convertToChild(Head, Right_Ribbon);
        setRotation(Right_Ribbon, 0F, 0F, -0.7853982F);
        Left_Ribbon = new ModelRenderer(this, 68, 0);
        Left_Ribbon.addBox(-6F, -8F, 2F, 2, 3, 1);
        Left_Ribbon.setRotationPoint(0F, 13F, 0F);
        Left_Ribbon.setTextureSize(128, 64);
        Left_Ribbon.mirror = true;
        this.convertToChild(Head, Left_Ribbon);
        setRotation(Left_Ribbon, 0F, 0F, 0.7853982F);
        Right_Cloud_1 = new ModelRenderer(this, 43, 35);
        Right_Cloud_1.addBox(-7F, 0F, 1.5F, 7, 2, 2);
        Right_Cloud_1.setRotationPoint(0F, 13F, 0F);
        Right_Cloud_1.setTextureSize(128, 64);
        Right_Cloud_1.mirror = true;
        setRotation(Right_Cloud_1, 0F, 0F, -0.2617994F);
        Right_Cloud_2 = new ModelRenderer(this, 31, 31);
        Right_Cloud_2.addBox(-7F, 0F, -2.5F, 2, 2, 8);
        Right_Cloud_2.setRotationPoint(0F, 13F, 0F);
        Right_Cloud_2.setTextureSize(128, 64);
        Right_Cloud_2.mirror = true;
        setRotation(Right_Cloud_2, -0.0698132F, -0.3490659F, -0.2617994F);
        Left_Cloud_1 = new ModelRenderer(this, 75, 35);
        Left_Cloud_1.addBox(0F, 0F, 1.5F, 7, 2, 2);
        Left_Cloud_1.setRotationPoint(0F, 13F, 0F);
        Left_Cloud_1.setTextureSize(128, 64);
        Left_Cloud_1.mirror = true;
        setRotation(Left_Cloud_1, 0F, 0F, 0.2617994F);
        Left_Cloud_2 = new ModelRenderer(this, 85, 31);
        Left_Cloud_2.addBox(5F, 0F, -2.5F, 2, 2, 8);
        Left_Cloud_2.setRotationPoint(0F, 13F, 0F);
        Left_Cloud_2.setTextureSize(128, 64);
        Left_Cloud_2.mirror = true;
        setRotation(Left_Cloud_2, -0.0698132F, 0.3490659F, 0.2617994F);
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

        EntityLightPet lightPet = (EntityLightPet) entity;

        Head.rotationPointY = lightPet.getFloatingRotation();
        Right_Arm.rotationPointY = lightPet.getFloatingRotation();
        Left_Arm.rotationPointY = lightPet.getFloatingRotation();
        Torso.rotationPointY = lightPet.getFloatingRotation();
        Skirt_1.rotationPointY = lightPet.getFloatingRotation();
        Skirt_2.rotationPointY = lightPet.getFloatingRotation();
        Right_Cloud_1.rotationPointY = lightPet.getFloatingRotation();
        Right_Cloud_2.rotationPointY = lightPet.getFloatingRotation();
        Left_Cloud_1.rotationPointY = lightPet.getFloatingRotation();
        Left_Cloud_2.rotationPointY = lightPet.getFloatingRotation();

        Head.render(f5);
        Right_Arm.render(f5);
        Left_Arm.render(f5);
        Torso.render(f5);
        Skirt_1.render(f5);
        Skirt_2.render(f5);
        Right_Cloud_1.render(f5);
        Right_Cloud_2.render(f5);
        Left_Cloud_1.render(f5);
        Left_Cloud_2.render(f5);
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

    }

}

