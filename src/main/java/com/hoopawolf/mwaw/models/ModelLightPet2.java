package com.hoopawolf.mwaw.models;

import com.hoopawolf.mwaw.entity.EntityLightPet2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightPet2 extends ModelBase {
    //fields
    ModelRenderer Head;
    ModelRenderer Hair_1;
    ModelRenderer Hair_2;
    ModelRenderer Hair_3;
    ModelRenderer Right_Side_Hair;
    ModelRenderer Front_Hair_1;
    ModelRenderer Front_Hair_2;
    ModelRenderer Left_Side_Hair_1;
    ModelRenderer Left_Side_Hair_2;
    ModelRenderer Left_Side_Hair_3;
    ModelRenderer Left_Side_Hair_4;
    ModelRenderer Left_Side_Hair_5;
    ModelRenderer Back_Hair_1;
    ModelRenderer Back_Hair_2;
    ModelRenderer Back_Hair_3;
    ModelRenderer Back_Hair_4;
    ModelRenderer Middle_Ribbon_Knot;
    ModelRenderer Right_Ribbon_Knot;
    ModelRenderer Right_Ribbon_End;
    ModelRenderer Left_Ribbon_Knot;
    ModelRenderer Left_Ribbon_End;
    ModelRenderer Right_Cloud_1;
    ModelRenderer Right_Cloud_2;
    ModelRenderer Left_Cloud_1;
    ModelRenderer Left_Cloud_2;
    ModelRenderer Torso;
    ModelRenderer Skirt_1;
    ModelRenderer Skirt_2;
    ModelRenderer Skirt_3;
    ModelRenderer Skirt_4;
    ModelRenderer Tail_1;
    ModelRenderer Tail_2;
    ModelRenderer Tail_3;
    ModelRenderer Tail_4;
    ModelRenderer Tail_5;
    ModelRenderer Right_Arm;
    ModelRenderer Left_Arm;

    public ModelLightPet2() {
        textureWidth = 256;
        textureHeight = 128;

        Head = new ModelRenderer(this, 98, 52);
        Head.addBox(-7F, -9F, -6F, 14, 9, 12);
        Head.setRotationPoint(0F, 0F, 0F);
        Head.setTextureSize(256, 128);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        Hair_1 = new ModelRenderer(this, 94, 34);
        Hair_1.addBox(-8F, -13F, -7F, 16, 4, 14);
        Hair_1.setRotationPoint(0F, 0F, 0F);
        Hair_1.setTextureSize(256, 128);
        Hair_1.mirror = true;
        this.convertToChild(Head, Hair_1);
        setRotation(Hair_1, 0F, 0F, 0F);
        Hair_2 = new ModelRenderer(this, 98, 21);
        Hair_2.addBox(-7F, -14F, -6F, 14, 1, 12);
        Hair_2.setRotationPoint(0F, 0F, 0F);
        Hair_2.setTextureSize(256, 128);
        Hair_2.mirror = true;
        this.convertToChild(Head, Hair_2);
        setRotation(Hair_2, 0F, 0F, 0F);
        Hair_3 = new ModelRenderer(this, 102, 10);
        Hair_3.addBox(-6F, -15F, -5F, 12, 1, 10);
        Hair_3.setRotationPoint(0F, 0F, 0F);
        Hair_3.setTextureSize(256, 128);
        Hair_3.mirror = true;
        this.convertToChild(Head, Hair_3);
        setRotation(Hair_3, 0F, 0F, 0F);
        Right_Side_Hair = new ModelRenderer(this, 77, 39);
        Right_Side_Hair.addBox(-8.5F, -10F, -6.5F, 2, 5, 13);
        Right_Side_Hair.setRotationPoint(0F, 0F, 0F);
        Right_Side_Hair.setTextureSize(256, 128);
        Right_Side_Hair.mirror = true;
        this.convertToChild(Head, Right_Side_Hair);
        setRotation(Right_Side_Hair, 0F, 0F, 0F);
        Front_Hair_1 = new ModelRenderer(this, 138, 52);
        Front_Hair_1.addBox(0.5F, -9F, -7F, 3, 4, 1);
        Front_Hair_1.setRotationPoint(0F, 0F, 0F);
        Front_Hair_1.setTextureSize(256, 128);
        Front_Hair_1.mirror = true;
        this.convertToChild(Head, Front_Hair_1);
        setRotation(Front_Hair_1, 0F, 0F, -0.1745329F);
        Front_Hair_2 = new ModelRenderer(this, 146, 52);
        Front_Hair_2.addBox(3.5F, -9F, -7F, 3, 8, 1);
        Front_Hair_2.setRotationPoint(0F, 0F, 0F);
        Front_Hair_2.setTextureSize(256, 128);
        Front_Hair_2.mirror = true;
        this.convertToChild(Head, Front_Hair_2);
        setRotation(Front_Hair_2, 0F, 0F, -0.1745329F);
        Left_Side_Hair_1 = new ModelRenderer(this, 154, 52);
        Left_Side_Hair_1.addBox(6.5F, -8F, -7F, 3, 12, 5);
        Left_Side_Hair_1.setRotationPoint(0F, 0F, 0F);
        Left_Side_Hair_1.setTextureSize(256, 128);
        Left_Side_Hair_1.mirror = true;
        this.convertToChild(Head, Left_Side_Hair_1);
        setRotation(Left_Side_Hair_1, 0F, 0F, -0.1745329F);
        Left_Side_Hair_2 = new ModelRenderer(this, 170, 52);
        Left_Side_Hair_2.addBox(6.5F, -8F, -2.5F, 3, 11, 3);
        Left_Side_Hair_2.setRotationPoint(0F, 0F, 0F);
        Left_Side_Hair_2.setTextureSize(256, 128);
        Left_Side_Hair_2.mirror = true;
        this.convertToChild(Head, Left_Side_Hair_2);
        setRotation(Left_Side_Hair_2, 0F, 0F, -0.1745329F);
        Left_Side_Hair_3 = new ModelRenderer(this, 182, 52);
        Left_Side_Hair_3.addBox(6.5F, -8F, 0.5F, 3, 10, 2);
        Left_Side_Hair_3.setRotationPoint(0F, 0F, 0F);
        Left_Side_Hair_3.setTextureSize(256, 128);
        Left_Side_Hair_3.mirror = true;
        this.convertToChild(Head, Left_Side_Hair_3);
        setRotation(Left_Side_Hair_3, 0F, 0F, -0.1745329F);
        Left_Side_Hair_4 = new ModelRenderer(this, 192, 52);
        Left_Side_Hair_4.addBox(6.5F, -8F, 2.5F, 3, 9, 2);
        Left_Side_Hair_4.setRotationPoint(0F, 0F, 0F);
        Left_Side_Hair_4.setTextureSize(256, 128);
        Left_Side_Hair_4.mirror = true;
        this.convertToChild(Head, Left_Side_Hair_4);
        setRotation(Left_Side_Hair_4, 0F, 0F, -0.1745329F);
        Left_Side_Hair_5 = new ModelRenderer(this, 202, 52);
        Left_Side_Hair_5.addBox(6.5F, -8F, 4F, 3, 8, 3);
        Left_Side_Hair_5.setRotationPoint(0F, 0F, 0F);
        Left_Side_Hair_5.setTextureSize(256, 128);
        Left_Side_Hair_5.mirror = true;
        this.convertToChild(Head, Left_Side_Hair_5);
        setRotation(Left_Side_Hair_5, 0F, 0F, -0.1745329F);
        Back_Hair_1 = new ModelRenderer(this, 90, 30);
        Back_Hair_1.addBox(3.5F, -9F, 6F, 3, 8, 1);
        Back_Hair_1.setRotationPoint(0F, 0F, 0F);
        Back_Hair_1.setTextureSize(256, 128);
        Back_Hair_1.mirror = true;
        this.convertToChild(Head, Back_Hair_1);
        setRotation(Back_Hair_1, 0F, 0F, -0.1745329F);
        Back_Hair_2 = new ModelRenderer(this, 82, 31);
        Back_Hair_2.addBox(0.5F, -10F, 6F, 3, 7, 1);
        Back_Hair_2.setRotationPoint(0F, 0F, 0F);
        Back_Hair_2.setTextureSize(256, 128);
        Back_Hair_2.mirror = true;
        this.convertToChild(Head, Back_Hair_2);
        setRotation(Back_Hair_2, 0F, 0F, -0.1745329F);
        Back_Hair_3 = new ModelRenderer(this, 70, 34);
        Back_Hair_3.addBox(-4.5F, -10F, 6F, 5, 4, 1);
        Back_Hair_3.setRotationPoint(0F, 0F, 0F);
        Back_Hair_3.setTextureSize(256, 128);
        Back_Hair_3.mirror = true;
        this.convertToChild(Head, Back_Hair_3);
        setRotation(Back_Hair_3, 0F, 0F, -0.1745329F);
        Back_Hair_4 = new ModelRenderer(this, 66, 34);
        Back_Hair_4.addBox(-5.5F, -11F, 6F, 1, 4, 1);
        Back_Hair_4.setRotationPoint(0F, 0F, 0F);
        Back_Hair_4.setTextureSize(256, 128);
        Back_Hair_4.mirror = true;
        this.convertToChild(Head, Back_Hair_4);
        setRotation(Back_Hair_4, 0F, 0F, -0.1745329F);
        Middle_Ribbon_Knot = new ModelRenderer(this, 119, 6);
        Middle_Ribbon_Knot.addBox(-2F, -12F, 7F, 4, 3, 1);
        Middle_Ribbon_Knot.setRotationPoint(0F, 0F, 0F);
        Middle_Ribbon_Knot.setTextureSize(256, 128);
        Middle_Ribbon_Knot.mirror = true;
        this.convertToChild(Head, Middle_Ribbon_Knot);
        setRotation(Middle_Ribbon_Knot, 0F, 0F, 0F);
        Right_Ribbon_Knot = new ModelRenderer(this, 99, 0);
        Right_Ribbon_Knot.addBox(-8F, -20F, 7.5F, 9, 9, 1);
        Right_Ribbon_Knot.setRotationPoint(0F, 0F, 0F);
        Right_Ribbon_Knot.setTextureSize(256, 128);
        Right_Ribbon_Knot.mirror = true;
        this.convertToChild(Head, Right_Ribbon_Knot);
        setRotation(Right_Ribbon_Knot, 0F, 0F, -0.2617994F);
        Right_Ribbon_End = new ModelRenderer(this, 94, 10);
        Right_Ribbon_End.addBox(-6.5F, -9F, 7.5F, 3, 12, 1);
        Right_Ribbon_End.setRotationPoint(0F, 0F, 0F);
        Right_Ribbon_End.setTextureSize(256, 128);
        Right_Ribbon_End.mirror = true;
        this.convertToChild(Head, Right_Ribbon_End);
        setRotation(Right_Ribbon_End, 0F, 0F, 0.2617994F);
        Left_Ribbon_Knot = new ModelRenderer(this, 129, 0);
        Left_Ribbon_Knot.addBox(-1F, -20F, 7.5F, 9, 9, 1);
        Left_Ribbon_Knot.setRotationPoint(0F, 0F, 0F);
        Left_Ribbon_Knot.setTextureSize(256, 128);
        Left_Ribbon_Knot.mirror = true;
        this.convertToChild(Head, Left_Ribbon_Knot);
        setRotation(Left_Ribbon_Knot, 0F, 0F, 0.2617994F);
        Left_Ribbon_End = new ModelRenderer(this, 146, 10);
        Left_Ribbon_End.addBox(3.5F, -9F, 7.5F, 3, 12, 1);
        Left_Ribbon_End.setRotationPoint(0F, 0F, 0F);
        Left_Ribbon_End.setTextureSize(256, 128);
        Left_Ribbon_End.mirror = true;
        this.convertToChild(Head, Left_Ribbon_End);
        setRotation(Left_Ribbon_End, 0F, 0F, -0.2617994F);
        Right_Cloud_1 = new ModelRenderer(this, 62, 76);
        Right_Cloud_1.addBox(-14F, 0F, 3F, 14, 4, 4);
        Right_Cloud_1.setRotationPoint(0F, 0F, 0F);
        Right_Cloud_1.setTextureSize(256, 128);
        Right_Cloud_1.mirror = true;
        setRotation(Right_Cloud_1, 0F, 0F, -0.2617994F);
        Right_Cloud_2 = new ModelRenderer(this, 62, 84);
        Right_Cloud_2.addBox(-15F, 0F, -3F, 4, 4, 14);
        Right_Cloud_2.setRotationPoint(0F, 0F, 0F);
        Right_Cloud_2.setTextureSize(256, 128);
        Right_Cloud_2.mirror = true;
        setRotation(Right_Cloud_2, -0.0698132F, -0.3490659F, -0.2617994F);
        Left_Cloud_1 = new ModelRenderer(this, 150, 76);
        Left_Cloud_1.addBox(0F, 0F, 3F, 14, 4, 4);
        Left_Cloud_1.setRotationPoint(0F, 0F, 0F);
        Left_Cloud_1.setTextureSize(256, 128);
        Left_Cloud_1.mirror = true;
        setRotation(Left_Cloud_1, 0F, 0F, 0.2617994F);
        Left_Cloud_2 = new ModelRenderer(this, 150, 84);
        Left_Cloud_2.addBox(11F, 0F, -3F, 4, 4, 14);
        Left_Cloud_2.setRotationPoint(0F, 0F, 0F);
        Left_Cloud_2.setTextureSize(256, 128);
        Left_Cloud_2.mirror = true;
        setRotation(Left_Cloud_2, -0.0698132F, 0.3490659F, 0.2617994F);
        Torso = new ModelRenderer(this, 110, 73);
        Torso.addBox(-4F, 0F, -3F, 8, 6, 6);
        Torso.setRotationPoint(0F, 0F, 0F);
        Torso.setTextureSize(256, 128);
        Torso.mirror = true;
        setRotation(Torso, 0F, 0F, 0F);
        Skirt_1 = new ModelRenderer(this, 108, 85);
        Skirt_1.addBox(-4.5F, 6F, -3.5F, 9, 1, 7);
        Skirt_1.setRotationPoint(0F, 0F, 0F);
        Skirt_1.setTextureSize(256, 128);
        Skirt_1.mirror = true;
        setRotation(Skirt_1, 0F, 0F, 0F);
        Skirt_2 = new ModelRenderer(this, 106, 93);
        Skirt_2.addBox(-5F, 7F, -4F, 10, 1, 8);
        Skirt_2.setRotationPoint(0F, 0F, 0F);
        Skirt_2.setTextureSize(256, 128);
        Skirt_2.mirror = true;
        setRotation(Skirt_2, 0F, 0F, 0F);
        Skirt_3 = new ModelRenderer(this, 104, 102);
        Skirt_3.addBox(-5.5F, 8F, -4.5F, 11, 1, 9);
        Skirt_3.setRotationPoint(0F, 0F, 0F);
        Skirt_3.setTextureSize(256, 128);
        Skirt_3.mirror = true;
        setRotation(Skirt_3, 0F, 0F, 0F);
        Skirt_4 = new ModelRenderer(this, 102, 112);
        Skirt_4.addBox(-6F, 9F, -5F, 12, 6, 10);
        Skirt_4.setRotationPoint(0F, 0F, 0F);
        Skirt_4.setTextureSize(256, 128);
        Skirt_4.mirror = true;
        setRotation(Skirt_4, 0F, 0F, 0F);
        Tail_1 = new ModelRenderer(this, 70, 116);
        Tail_1.addBox(-4F, 13F, -6.5F, 8, 4, 8);
        Tail_1.setRotationPoint(0F, 0F, 0F);
        Tail_1.setTextureSize(256, 128);
        Tail_1.mirror = true;
        setRotation(Tail_1, 0.1745329F, 0F, 0F);
        Tail_2 = new ModelRenderer(this, 46, 117);
        Tail_2.addBox(-3F, 11F, -13.5F, 6, 5, 6);
        Tail_2.setRotationPoint(0F, 0F, 0F);
        Tail_2.setTextureSize(256, 128);
        Tail_2.mirror = true;
        setRotation(Tail_2, 0.6981317F, 0F, 0F);
        Tail_3 = new ModelRenderer(this, 30, 119);
        Tail_3.addBox(-2F, 3.5F, -20.5F, 4, 5, 4);
        Tail_3.setRotationPoint(0F, 0F, 0F);
        Tail_3.setTextureSize(256, 128);
        Tail_3.mirror = true;
        setRotation(Tail_3, 1.396263F, 0F, 0F);
        Tail_4 = new ModelRenderer(this, 18, 121);
        Tail_4.addBox(-1.5F, 1F, -22F, 3, 4, 3);
        Tail_4.setRotationPoint(0F, 0F, 0F);
        Tail_4.setTextureSize(256, 128);
        Tail_4.mirror = true;
        setRotation(Tail_4, 1.745329F, 0F, 0F);
        Tail_5 = new ModelRenderer(this, 10, 123);
        Tail_5.addBox(-1F, 5F, -21.5F, 2, 3, 2);
        Tail_5.setRotationPoint(0F, 0F, 0F);
        Tail_5.setTextureSize(256, 128);
        Tail_5.mirror = true;
        setRotation(Tail_5, 1.745329F, 0F, 0F);
        Right_Arm = new ModelRenderer(this, 98, 76);
        Right_Arm.addBox(-1.5F, -1F, -1.5F, 3, 13, 3);
        Right_Arm.setRotationPoint(-4F, 1F, 0F);
        Right_Arm.setTextureSize(256, 128);
        Right_Arm.mirror = true;
        setRotation(Right_Arm, 0F, 0F, 0.5235988F);
        Left_Arm = new ModelRenderer(this, 138, 76);
        Left_Arm.addBox(-1.5F, -1F, -1.5F, 3, 13, 3);
        Left_Arm.setRotationPoint(4F, 1F, 0F);
        Left_Arm.setTextureSize(256, 128);
        Left_Arm.mirror = true;
        setRotation(Left_Arm, 0F, 0F, -0.5235988F);
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

        EntityLightPet2 lightPet = (EntityLightPet2) entity;

        Head.rotationPointY = lightPet.getFloatingRotation();
        Right_Cloud_1.rotationPointY = lightPet.getFloatingRotationShawl();
        Right_Cloud_2.rotationPointY = lightPet.getFloatingRotationShawl();
        Left_Cloud_1.rotationPointY = lightPet.getFloatingRotationShawl();
        Left_Cloud_2.rotationPointY = lightPet.getFloatingRotationShawl();
        Torso.rotationPointY = lightPet.getFloatingRotation();
        Skirt_1.rotationPointY = lightPet.getFloatingRotation();
        Skirt_2.rotationPointY = lightPet.getFloatingRotation();
        Skirt_3.rotationPointY = lightPet.getFloatingRotation();
        Skirt_4.rotationPointY = lightPet.getFloatingRotation();
        Tail_1.rotationPointY = lightPet.getFloatingRotation();
        Tail_2.rotationPointY = lightPet.getFloatingRotation();
        Tail_3.rotationPointY = lightPet.getFloatingRotation();
        Tail_4.rotationPointY = lightPet.getFloatingRotation();
        Tail_5.rotationPointY = lightPet.getFloatingRotation();
        Right_Arm.rotationPointY = lightPet.getFloatingRotation();
        Left_Arm.rotationPointY = lightPet.getFloatingRotation();

        Head.render(f5);
        Right_Cloud_1.render(f5);
        Right_Cloud_2.render(f5);
        Left_Cloud_1.render(f5);
        Left_Cloud_2.render(f5);
        Torso.render(f5);
        Skirt_1.render(f5);
        Skirt_2.render(f5);
        Skirt_3.render(f5);
        Skirt_4.render(f5);
        Tail_1.render(f5);
        Tail_2.render(f5);
        Tail_3.render(f5);
        Tail_4.render(f5);
        Tail_5.render(f5);
        Right_Arm.render(f5);
        Left_Arm.render(f5);
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
