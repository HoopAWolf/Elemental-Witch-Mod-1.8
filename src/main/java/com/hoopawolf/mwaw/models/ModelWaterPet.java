package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelWaterPet extends ModelBase {
    //fields
    ModelRenderer Head_1;
    ModelRenderer Head_2;
    ModelRenderer Head_3;
    ModelRenderer Back_Head_1;
    ModelRenderer Back_Head_2;
    ModelRenderer Fin_1;
    ModelRenderer Fin_2;
    ModelRenderer Fin_3;
    ModelRenderer Torso;
    ModelRenderer Lower_Torso;
    ModelRenderer Right_Arm_1;
    ModelRenderer Right_Arm_2;
    ModelRenderer Left_Arm_1;
    ModelRenderer Left_Arm_2;
    ModelRenderer Right_Leg;
    ModelRenderer Left_Leg;
    ModelRenderer Scarf_1;
    ModelRenderer Scarf_2;
    ModelRenderer Scarf_3;
    ModelRenderer Scarf_4;
    ModelRenderer Scarf_5;

    public ModelWaterPet() {
        textureWidth = 128;
        textureHeight = 64;

        Head_1 = new ModelRenderer(this, 55, 22);
        Head_1.addBox(-4F, -6F, -3.5F, 8, 6, 7);
        Head_1.setRotationPoint(0F, 15F, 0F);
        Head_1.setTextureSize(128, 64);
        Head_1.mirror = true;
        setRotation(Head_1, 0F, 0F, 0F);
        Head_2 = new ModelRenderer(this, 57, 13);
        Head_2.addBox(-3F, -8F, -3F, 6, 2, 7);
        Head_2.setRotationPoint(0F, 15F, 0F);
        Head_2.setTextureSize(128, 64);
        Head_2.mirror = true;
        this.convertToChild(Head_1, Head_2);
        setRotation(Head_2, 0F, 0F, 0F);
        Head_3 = new ModelRenderer(this, 58, 0);
        Head_3.addBox(-1F, -8F, -1F, 2, 3, 10);
        Head_3.setRotationPoint(0F, 15F, 0F);
        Head_3.setTextureSize(128, 64);
        Head_3.mirror = true;
        this.convertToChild(Head_1, Head_3);
        setRotation(Head_3, 0.0872665F, 0F, 0F);
        Back_Head_1 = new ModelRenderer(this, 83, 14);
        Back_Head_1.addBox(-3F, -5F, 3F, 6, 3, 3);
        Back_Head_1.setRotationPoint(0F, 15F, 0F);
        Back_Head_1.setTextureSize(128, 64);
        Back_Head_1.mirror = true;
        this.convertToChild(Head_1, Back_Head_1);
        setRotation(Back_Head_1, 0F, 0F, 0F);
        Back_Head_2 = new ModelRenderer(this, 101, 16);
        Back_Head_2.addBox(-3F, -4.5F, 6F, 6, 2, 1);
        Back_Head_2.setRotationPoint(0F, 15F, 0F);
        Back_Head_2.setTextureSize(128, 64);
        Back_Head_2.mirror = true;
        this.convertToChild(Head_1, Back_Head_2);
        setRotation(Back_Head_2, 0F, 0F, 0F);
        Fin_1 = new ModelRenderer(this, 48, -10);
        Fin_1.addBox(0F, -3F, 2F, 0, 4, 10);
        Fin_1.setRotationPoint(0F, 15F, 0F);
        Fin_1.setTextureSize(128, 64);
        Fin_1.mirror = true;
        this.convertToChild(Head_1, Fin_1);
        setRotation(Fin_1, 0.4886922F, 0F, 0F);
        Fin_2 = new ModelRenderer(this, 44, -3);
        Fin_2.addBox(0F, -2F, 3F, 0, 3, 7);
        Fin_2.setRotationPoint(0F, 15F, 0F);
        Fin_2.setTextureSize(128, 64);
        Fin_2.mirror = true;
        this.convertToChild(Head_1, Fin_2);
        setRotation(Fin_2, 0.0872665F, 0F, 0F);
        Fin_3 = new ModelRenderer(this, 29, -4);
        Fin_3.addBox(0F, -5F, 1F, 0, 6, 11);
        Fin_3.setRotationPoint(0F, 15F, 0F);
        Fin_3.setTextureSize(128, 64);
        Fin_3.mirror = true;
        setRotation(Fin_3, -0.6283185F, 0F, 0F);
        Torso = new ModelRenderer(this, 59, 35);
        Torso.addBox(-3F, 0F, -2.5F, 6, 4, 5);
        Torso.setRotationPoint(0F, 15F, 0F);
        Torso.setTextureSize(128, 64);
        Torso.mirror = true;
        setRotation(Torso, 0F, 0F, 0F);
        Lower_Torso = new ModelRenderer(this, 61, 44);
        Lower_Torso.addBox(-2.5F, 4F, -2F, 5, 1, 4);
        Lower_Torso.setRotationPoint(0F, 15F, 0F);
        Lower_Torso.setTextureSize(128, 64);
        Lower_Torso.mirror = true;
        setRotation(Lower_Torso, 0F, 0F, 0F);
        Right_Arm_1 = new ModelRenderer(this, 41, 22);
        Right_Arm_1.addBox(-0.5F, 0F, -2.5F, 2, 5, 5);
        Right_Arm_1.setRotationPoint(-3F, 15F, 0F);
        Right_Arm_1.setTextureSize(128, 64);
        Right_Arm_1.mirror = true;
        setRotation(Right_Arm_1, 0F, 0F, 1.047198F);
        Right_Arm_2 = new ModelRenderer(this, 45, 32);
        Right_Arm_2.addBox(1.5F, -0.5F, -2F, 1, 5, 4);
        Right_Arm_2.setRotationPoint(-3F, 15F, 0F);
        Right_Arm_2.setTextureSize(128, 64);
        Right_Arm_2.mirror = true;
        setRotation(Right_Arm_2, 0F, 0F, 1.047198F);
        Left_Arm_1 = new ModelRenderer(this, 85, 22);
        Left_Arm_1.addBox(-1.5F, 0F, -2.5F, 2, 5, 5);
        Left_Arm_1.setRotationPoint(3F, 15F, 0F);
        Left_Arm_1.setTextureSize(128, 64);
        Left_Arm_1.mirror = true;
        setRotation(Left_Arm_1, 0F, 0F, -1.047198F);
        Left_Arm_2 = new ModelRenderer(this, 85, 32);
        Left_Arm_2.addBox(-2.5F, -0.5F, -2F, 1, 5, 4);
        Left_Arm_2.setRotationPoint(3F, 15F, 0F);
        Left_Arm_2.setTextureSize(128, 64);
        Left_Arm_2.mirror = true;
        setRotation(Left_Arm_2, 0F, 0F, -1.047198F);
        Right_Leg = new ModelRenderer(this, 62, 49);
        Right_Leg.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        Right_Leg.setRotationPoint(-1F, 20F, 0F);
        Right_Leg.setTextureSize(128, 64);
        Right_Leg.mirror = true;
        setRotation(Right_Leg, 0F, 0F, 0F);
        Left_Leg = new ModelRenderer(this, 74, 49);
        Left_Leg.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        Left_Leg.setRotationPoint(1F, 20F, 0F);
        Left_Leg.setTextureSize(128, 64);
        Left_Leg.mirror = true;
        setRotation(Left_Leg, 0F, 0F, 0F);
        Scarf_1 = new ModelRenderer(this, 95, 32);
        Scarf_1.addBox(-3.5F, 0F, -3F, 10, 1, 6);
        Scarf_1.setRotationPoint(0F, 15F, 0F);
        Scarf_1.setTextureSize(128, 64);
        Scarf_1.mirror = true;
        setRotation(Scarf_1, 0F, 0F, 0F);
        Scarf_2 = new ModelRenderer(this, 105, 39);
        Scarf_2.addBox(1.5F, 1F, -3F, 5, 1, 6);
        Scarf_2.setRotationPoint(0F, 15F, 0F);
        Scarf_2.setTextureSize(128, 64);
        Scarf_2.mirror = true;
        setRotation(Scarf_2, 0F, 0F, 0F);
        Scarf_3 = new ModelRenderer(this, 89, 41);
        Scarf_3.addBox(-3.5F, 1F, -3F, 3, 1, 1);
        Scarf_3.setRotationPoint(0F, 15F, 0F);
        Scarf_3.setTextureSize(128, 64);
        Scarf_3.mirror = true;
        setRotation(Scarf_3, 0F, 0F, 0F);
        Scarf_4 = new ModelRenderer(this, 90, 43);
        Scarf_4.addBox(-3F, 2F, -3F, 2, 1, 1);
        Scarf_4.setRotationPoint(0F, 15F, 0F);
        Scarf_4.setTextureSize(128, 64);
        Scarf_4.mirror = true;
        setRotation(Scarf_4, 0F, 0F, 0F);
        Scarf_5 = new ModelRenderer(this, 91, 45);
        Scarf_5.addBox(-2.5F, 3F, -3F, 1, 1, 1);
        Scarf_5.setRotationPoint(0F, 15F, 0F);
        Scarf_5.setTextureSize(128, 64);
        Scarf_5.mirror = true;
        setRotation(Scarf_5, 0F, 0F, 0F);
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

        GL11.glTranslatef(0, 0.2F, 0F);

        Head_1.render(f5);
        Fin_3.render(f5);
        Torso.render(f5);
        Lower_Torso.render(f5);
        Right_Arm_1.render(f5);
        Right_Arm_2.render(f5);
        Left_Arm_1.render(f5);
        Left_Arm_2.render(f5);
        Right_Leg.render(f5);
        Left_Leg.render(f5);
        Scarf_1.render(f5);
        Scarf_2.render(f5);
        Scarf_3.render(f5);
        Scarf_4.render(f5);
        Scarf_5.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ////////////////////////////////HEAD///////////////////////////////////////
        this.Head_1.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Head_1.rotateAngleY = f3 / (180F / (float) Math.PI);

        ///////////////////////////////LEGS///////////////////////////////////////
        this.Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;

    }

}