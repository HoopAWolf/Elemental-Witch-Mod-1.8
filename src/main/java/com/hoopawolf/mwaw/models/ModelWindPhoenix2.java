package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelWindPhoenix2 extends ModelBase {
    //fields
    ModelRenderer Torso;
    ModelRenderer Torso2;
    ModelRenderer Torso3;
    ModelRenderer Neck;
    ModelRenderer Skull;
    ModelRenderer Forehead;
    ModelRenderer Topbeak;
    ModelRenderer beaktip;
    ModelRenderer Bottombeak;
    ModelRenderer BackTopLeftheadfeather;
    ModelRenderer BackTopRightHeadFeather;
    ModelRenderer MiddleRightHeadFeather;
    ModelRenderer MiddleLeftHeadFeather;
    ModelRenderer BottomRightHeadFeather;
    ModelRenderer BottomLeftHeadFeather;
    ModelRenderer RightWing;
    ModelRenderer LeftWing;
    ModelRenderer Butt;
    ModelRenderer TopTailFeather;
    ModelRenderer MiddleRightTailFeather;
    ModelRenderer MiddleLeftTailFeather;
    ModelRenderer TopRightTailFeather;
    ModelRenderer TopLeftTailFeather;
    ModelRenderer BottomLeftTailFeather;
    ModelRenderer BottomRightTailFeather;
    ModelRenderer LeftCheek;
    ModelRenderer RightCheek;
    ModelRenderer LeftHip;
    ModelRenderer RightHip;
    ModelRenderer LeftThigh;
    ModelRenderer RightThigh;
    ModelRenderer LeftLeg;
    ModelRenderer RightLeg;
    ModelRenderer Leftfootrighttoe;
    ModelRenderer rightfootrighttoe;
    ModelRenderer rightfootlefttoe;
    ModelRenderer leftfootrighttoe;
    ModelRenderer rightheel;
    ModelRenderer leftheel;

    public ModelWindPhoenix2() {
        textureWidth = 256;
        textureHeight = 256;

        Torso = new ModelRenderer(this, 0, 36);
        Torso.addBox(-5F, -8F, -5F, 10, 16, 10);
        Torso.setRotationPoint(0F, 3F, -2F);
        Torso.setTextureSize(256, 256);
        Torso.mirror = true;
        setRotation(Torso, 0.9250245F, 0F, 0F);
        Torso2 = new ModelRenderer(this, 0, 64);
        Torso2.addBox(-4F, -4F, -6F, 8, 8, 7);
        Torso2.setRotationPoint(0F, -1F, -7F);
        Torso2.setTextureSize(256, 256);
        Torso2.mirror = true;
        setRotation(Torso2, -1.134464F, 0F, 0F);
        Torso3 = new ModelRenderer(this, 0, 81);
        Torso3.addBox(-3F, -4F, -3F, 6, 4, 6);
        Torso3.setRotationPoint(0F, -5F, -9F);
        Torso3.setTextureSize(256, 256);
        Torso3.mirror = true;
        setRotation(Torso3, 0F, 0F, 0F);
        Neck = new ModelRenderer(this, 0, 93);
        Neck.addBox(-2F, -8F, -2F, 4, 8, 4);
        Neck.setRotationPoint(0F, -8F, -9.5F);
        Neck.setTextureSize(256, 256);
        Neck.mirror = true;
        setRotation(Neck, -0.2792527F, 0F, 0F);
        Skull = new ModelRenderer(this, 0, 118);
        Skull.addBox(-3F, -7F, -5F, 6, 7, 7);
        Skull.setRotationPoint(0F, -15F, -7F);
        Skull.setTextureSize(256, 256);
        Skull.mirror = true;
        setRotation(Skull, 0F, 0F, 0F);
        Forehead = new ModelRenderer(this, 0, 134);
        Forehead.addBox(-2F, -1F, -8F, 4, 5, 3);
        Forehead.setRotationPoint(0F, -15.5F, -7F);
        Forehead.setTextureSize(256, 256);
        Forehead.mirror = true;
        this.convertToChild(Skull, Forehead);
        setRotation(Forehead, -0.8203047F, 0F, 0F);
        Topbeak = new ModelRenderer(this, 50, 0);
        Topbeak.addBox(-1F, -4.5F, -12F, 2, 3, 7);
        Topbeak.setRotationPoint(0F, -15F, -7F);
        Topbeak.setTextureSize(256, 256);
        Topbeak.mirror = true;
        this.convertToChild(Skull, Topbeak);
        setRotation(Topbeak, 0F, 0F, 0F);
        beaktip = new ModelRenderer(this, 70, 0);
        beaktip.addBox(-0.5F, -0.5F, -12.5F, 1, 3, 1);
        beaktip.setRotationPoint(0F, -15F, -7F);
        beaktip.setTextureSize(256, 256);
        beaktip.mirror = true;
        this.convertToChild(Skull, beaktip);
        setRotation(beaktip, -0.2792527F, 0F, 0F);
        Bottombeak = new ModelRenderer(this, 50, 14);
        Bottombeak.addBox(-0.5F, -2F, -11F, 1, 2, 6);
        Bottombeak.setRotationPoint(0F, -15F, -7F);
        Bottombeak.setTextureSize(256, 256);
        Bottombeak.mirror = true;
        this.convertToChild(Skull, Bottombeak);
        setRotation(Bottombeak, 0F, 0F, 0F);
        BackTopLeftheadfeather = new ModelRenderer(this, 44, 50);
        BackTopLeftheadfeather.addBox(2F, -7F, 2F, 1, 3, 5);
        BackTopLeftheadfeather.setRotationPoint(0F, -15F, -7F);
        BackTopLeftheadfeather.setTextureSize(256, 256);
        BackTopLeftheadfeather.mirror = true;
        this.convertToChild(Skull, BackTopLeftheadfeather);
        setRotation(BackTopLeftheadfeather, 0.1745329F, 0.1919862F, 0F);
        BackTopRightHeadFeather = new ModelRenderer(this, 58, 51);
        BackTopRightHeadFeather.addBox(-3F, -7F, 2F, 1, 3, 5);
        BackTopRightHeadFeather.setRotationPoint(0F, -15F, -7F);
        BackTopRightHeadFeather.setTextureSize(256, 256);
        BackTopRightHeadFeather.mirror = true;
        this.convertToChild(Skull, BackTopRightHeadFeather);
        setRotation(BackTopRightHeadFeather, 0.1745329F, -0.1919862F, 0F);
        MiddleRightHeadFeather = new ModelRenderer(this, 44, 60);
        MiddleRightHeadFeather.addBox(-3F, -4F, 1F, 1, 2, 4);
        MiddleRightHeadFeather.setRotationPoint(0F, -15F, -7F);
        MiddleRightHeadFeather.setTextureSize(256, 256);
        MiddleRightHeadFeather.mirror = true;
        this.convertToChild(Skull, MiddleRightHeadFeather);
        setRotation(MiddleRightHeadFeather, 0F, -0.1919862F, 0F);
        MiddleLeftHeadFeather = new ModelRenderer(this, 58, 61);
        MiddleLeftHeadFeather.addBox(2F, -4F, 1F, 1, 2, 4);
        MiddleLeftHeadFeather.setRotationPoint(0F, -15F, -7F);
        MiddleLeftHeadFeather.setTextureSize(256, 256);
        MiddleLeftHeadFeather.mirror = true;
        this.convertToChild(Skull, MiddleLeftHeadFeather);
        setRotation(MiddleLeftHeadFeather, 0F, 0.1919862F, 0F);
        BottomRightHeadFeather = new ModelRenderer(this, 46, 68);
        BottomRightHeadFeather.addBox(-3F, -2F, 1F, 1, 2, 2);
        BottomRightHeadFeather.setRotationPoint(0F, -15F, -7F);
        BottomRightHeadFeather.setTextureSize(256, 256);
        BottomRightHeadFeather.mirror = true;
        this.convertToChild(Skull, BottomRightHeadFeather);
        setRotation(BottomRightHeadFeather, -0.4537856F, -0.1919862F, 0F);
        BottomLeftHeadFeather = new ModelRenderer(this, 59, 70);
        BottomLeftHeadFeather.addBox(2F, -2F, 1F, 1, 2, 2);
        BottomLeftHeadFeather.setRotationPoint(0F, -15F, -7F);
        BottomLeftHeadFeather.setTextureSize(256, 256);
        BottomLeftHeadFeather.mirror = true;
        this.convertToChild(Skull, BottomLeftHeadFeather);
        setRotation(BottomLeftHeadFeather, -0.4537856F, 0.1919862F, 0F);
        RightWing = new ModelRenderer(this, 31, 86);
        RightWing.addBox(-1F, -2F, -2F, 1, 12, 23);
        RightWing.setRotationPoint(-5F, -2F, -8F);
        RightWing.setTextureSize(256, 256);
        RightWing.mirror = true;
        setRotation(RightWing, -0.2094395F, 0F, 0.3839724F);
        LeftWing = new ModelRenderer(this, 31, 124);
        LeftWing.addBox(0F, -2F, -2F, 1, 12, 23);
        LeftWing.setRotationPoint(5F, -2F, -8F);
        LeftWing.setTextureSize(256, 256);
        LeftWing.mirror = true;
        setRotation(LeftWing, -0.2094395F, 0F, -0.3839724F);
        Butt = new ModelRenderer(this, 0, 18);
        Butt.addBox(-4.5F, 1F, -2F, 9, 8, 8);
        Butt.setRotationPoint(0F, 2F, 3F);
        Butt.setTextureSize(256, 256);
        Butt.mirror = true;
        setRotation(Butt, 0F, 0F, 0F);
        TopTailFeather = new ModelRenderer(this, 77, 0);
        TopTailFeather.addBox(-1.5F, 0F, 0F, 3, 0, 18);
        TopTailFeather.setRotationPoint(0F, 4F, 8F);
        TopTailFeather.setTextureSize(256, 256);
        TopTailFeather.mirror = true;
        setRotation(TopTailFeather, 0.4537856F, 0F, 0F);
        MiddleRightTailFeather = new ModelRenderer(this, 78, 21);
        MiddleRightTailFeather.addBox(-1.5F, 0F, 0F, 3, 0, 17);
        MiddleRightTailFeather.setRotationPoint(-2F, 6F, 7F);
        MiddleRightTailFeather.setTextureSize(256, 256);
        MiddleRightTailFeather.mirror = true;
        setRotation(MiddleRightTailFeather, 0.4712389F, 0F, -0.7853982F);
        MiddleLeftTailFeather = new ModelRenderer(this, 78, 40);
        MiddleLeftTailFeather.addBox(-1.5F, 0F, 0F, 3, 0, 17);
        MiddleLeftTailFeather.setRotationPoint(2F, 6F, 7F);
        MiddleLeftTailFeather.setTextureSize(256, 256);
        MiddleLeftTailFeather.mirror = true;
        setRotation(MiddleLeftTailFeather, 0.4712389F, 0F, 0.7853982F);
        TopRightTailFeather = new ModelRenderer(this, 83, 59);
        TopRightTailFeather.addBox(-1.5F, 0F, 0F, 3, 0, 12);
        TopRightTailFeather.setRotationPoint(-2F, 4F, 8F);
        TopRightTailFeather.setTextureSize(256, 256);
        TopRightTailFeather.mirror = true;
        setRotation(TopRightTailFeather, 0.418879F, 0F, -0.418879F);
        TopLeftTailFeather = new ModelRenderer(this, 84, 73);
        TopLeftTailFeather.addBox(-1.5F, 0F, 0F, 3, 0, 12);
        TopLeftTailFeather.setRotationPoint(2F, 4F, 8F);
        TopLeftTailFeather.setTextureSize(256, 256);
        TopLeftTailFeather.mirror = true;
        setRotation(TopLeftTailFeather, 0.418879F, 0F, 0.418879F);
        BottomRightTailFeather = new ModelRenderer(this, 88, 87);
        BottomRightTailFeather.addBox(0F, -1.5F, 0F, 0, 3, 12);
        BottomRightTailFeather.setRotationPoint(-4F, 8F, 8F);
        BottomRightTailFeather.setTextureSize(256, 256);
        BottomRightTailFeather.mirror = true;
        setRotation(BottomRightTailFeather, 0.2792527F, -0.2792527F, 0F);
        BottomLeftTailFeather = new ModelRenderer(this, 88, 105);
        BottomLeftTailFeather.addBox(0F, -1.5F, 0F, 0, 3, 12);
        BottomLeftTailFeather.setRotationPoint(4F, 8F, 8F);
        BottomLeftTailFeather.setTextureSize(256, 256);
        BottomLeftTailFeather.mirror = true;
        setRotation(BottomLeftTailFeather, 0.2792527F, 0.2792527F, 0F);
        LeftCheek = new ModelRenderer(this, 122, 2);
        LeftCheek.addBox(0.5F, -3.5F, -10F, 2, 2, 7);
        LeftCheek.setRotationPoint(0F, -15F, -7F);
        LeftCheek.setTextureSize(256, 256);
        LeftCheek.mirror = true;
        this.convertToChild(Skull, LeftCheek);
        setRotation(LeftCheek, 0F, 0.122173F, 0F);
        RightCheek = new ModelRenderer(this, 28, -1);
        RightCheek.addBox(-2.5F, -3.5F, -10F, 2, 2, 7);
        RightCheek.setRotationPoint(0F, -15F, -7F);
        RightCheek.setTextureSize(256, 256);
        RightCheek.mirror = true;
        this.convertToChild(Skull, RightCheek);
        setRotation(RightCheek, 0F, -0.122173F, 0F);
        LeftHip = new ModelRenderer(this, 123, 15);
        LeftHip.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
        LeftHip.setRotationPoint(4F, 9F, 1F);
        LeftHip.setTextureSize(256, 256);
        LeftHip.mirror = true;
        setRotation(LeftHip, 0F, 0F, 0F);
        RightHip = new ModelRenderer(this, 124, 27);
        RightHip.addBox(-2.5F, 0F, -2.5F, 5, 4, 5);
        RightHip.setRotationPoint(-4F, 9F, 1F);
        RightHip.setTextureSize(256, 256);
        RightHip.mirror = true;
        setRotation(RightHip, 0F, 0F, 0F);
        LeftThigh = new ModelRenderer(this, 38, 23);
        LeftThigh.addBox(-1F, 3F, -2.5F, 2, 6, 3);
        LeftThigh.setRotationPoint(4F, 9F, 1F);
        LeftThigh.setTextureSize(256, 256);
        LeftThigh.mirror = true;
        this.convertToChild(LeftHip, LeftThigh);
        setRotation(LeftThigh, 0.3665191F, 0F, 0F);
        RightThigh = new ModelRenderer(this, 17, 7);
        RightThigh.addBox(-1F, 3F, -2.5F, 2, 6, 3);
        RightThigh.setRotationPoint(-4F, 9F, 1F);
        RightThigh.setTextureSize(256, 256);
        RightThigh.mirror = true;
        this.convertToChild(RightHip, RightThigh);
        setRotation(RightThigh, 0.3665191F, 0F, 0F);
        LeftLeg = new ModelRenderer(this, 147, 20);
        LeftLeg.addBox(-1F, 5F, 4F, 2, 8, 3);
        LeftLeg.setRotationPoint(4F, 9F, 1F);
        LeftLeg.setTextureSize(256, 256);
        LeftLeg.mirror = true;
        this.convertToChild(LeftHip, LeftLeg);
        setRotation(LeftLeg, -0.4886922F, 0F, 0F);
        RightLeg = new ModelRenderer(this, 147, 5);
        RightLeg.addBox(-1F, 5F, 4F, 2, 8, 3);
        RightLeg.setRotationPoint(-4F, 9F, 1F);
        RightLeg.setTextureSize(256, 256);
        RightLeg.mirror = true;
        this.convertToChild(RightHip, RightLeg);
        setRotation(RightLeg, -0.4886922F, 0F, 0F);
        Leftfootrighttoe = new ModelRenderer(this, 160, 3);
        Leftfootrighttoe.addBox(-1F, 13F, -7F, 2, 2, 6);
        Leftfootrighttoe.setRotationPoint(4F, 9F, 1F);
        Leftfootrighttoe.setTextureSize(256, 256);
        Leftfootrighttoe.mirror = true;
        this.convertToChild(LeftHip, Leftfootrighttoe);
        setRotation(Leftfootrighttoe, 0F, 0.2792527F, 0F);
        rightfootrighttoe = new ModelRenderer(this, 0, 9);
        rightfootrighttoe.addBox(-1F, 13F, -7F, 2, 2, 6);
        rightfootrighttoe.setRotationPoint(-4F, 9F, 1F);
        rightfootrighttoe.setTextureSize(256, 256);
        rightfootrighttoe.mirror = true;
        this.convertToChild(RightHip, rightfootrighttoe);
        setRotation(rightfootrighttoe, 0F, 0.2792527F, 0F);
        rightfootlefttoe = new ModelRenderer(this, 121, 39);
        rightfootlefttoe.addBox(-1F, 13F, -7F, 2, 2, 6);
        rightfootlefttoe.setRotationPoint(-4F, 9F, 1F);
        rightfootlefttoe.setTextureSize(256, 256);
        rightfootlefttoe.mirror = true;
        this.convertToChild(RightHip, rightfootlefttoe);
        setRotation(rightfootlefttoe, 0F, -0.2792527F, 0F);
        leftfootrighttoe = new ModelRenderer(this, 119, 49);
        leftfootrighttoe.addBox(-1F, 13F, -7F, 2, 2, 6);
        leftfootrighttoe.setRotationPoint(4F, 9F, 1F);
        leftfootrighttoe.setTextureSize(256, 256);
        leftfootrighttoe.mirror = true;
        this.convertToChild(LeftHip, leftfootrighttoe);
        setRotation(leftfootrighttoe, 0F, -0.2792527F, 0F);
        rightheel = new ModelRenderer(this, 161, 20);
        rightheel.addBox(-1F, 13F, -2F, 2, 2, 6);
        rightheel.setRotationPoint(-4F, 9F, 1F);
        rightheel.setTextureSize(256, 256);
        rightheel.mirror = true;
        this.convertToChild(RightHip, rightheel);
        setRotation(rightheel, 0F, 0F, 0F);
        leftheel = new ModelRenderer(this, 0, 0);
        leftheel.addBox(-1F, 13F, -2F, 2, 2, 6);
        leftheel.setRotationPoint(4F, 9F, 1F);
        leftheel.setTextureSize(256, 256);
        leftheel.mirror = true;
        this.convertToChild(LeftHip, leftheel);
        setRotation(leftheel, 0F, 0F, 0F);
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

        GL11.glScalef(1.0F, 1.0F, 1.0F);

        Torso.render(f5);
        Torso2.render(f5);
        Torso3.render(f5);
        Neck.render(f5);
        Skull.render(f5);
        RightWing.render(f5);
        LeftWing.render(f5);
        Butt.render(f5);
        TopTailFeather.render(f5);
        MiddleRightTailFeather.render(f5);
        MiddleLeftTailFeather.render(f5);
        TopRightTailFeather.render(f5);
        TopLeftTailFeather.render(f5);
        BottomRightTailFeather.render(f5);
        BottomLeftTailFeather.render(f5);
        LeftHip.render(f5);
        RightHip.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ////////////////////////////////HEAD///////////////////////////////////////
        this.Skull.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Skull.rotateAngleY = f3 / (180F / (float) Math.PI);

///////////////////////////////LEGS///////////////////////////////////////
        this.RightHip.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.LeftHip.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;


//////////////////////////////WINGS////////////////////////////////////////
        if (!entity.onGround) {
            this.LeftWing.rotateAngleZ = f2;
            this.RightWing.rotateAngleZ = -f2;
        } else {
            this.LeftWing.rotateAngleZ = 0;
            this.RightWing.rotateAngleZ = 0;
        }

    }

}

