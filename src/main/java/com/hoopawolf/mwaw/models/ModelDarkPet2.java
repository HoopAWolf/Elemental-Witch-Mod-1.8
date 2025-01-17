package com.hoopawolf.mwaw.models;

import com.hoopawolf.mwaw.entity.EntityDarkPet2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDarkPet2 extends ModelBase {
    //fields
    ModelRenderer Helmet_Top;
    ModelRenderer Helmet_Front_Top_1;
    ModelRenderer Helmet_Front_Top_2;
    ModelRenderer Helmet_Front_Right;
    ModelRenderer Helmet_Front_Middle;
    ModelRenderer Helmet_Fron_Left;
    ModelRenderer Helmet_Right_Side;
    ModelRenderer Helmet_Left_Side;
    ModelRenderer Helmet_Back;
    ModelRenderer Helmet_Right_Horn;
    ModelRenderer Helmet_Left_Horn;
    ModelRenderer Helmet_Horn_Bottom_Piece;
    ModelRenderer Right_Eye;
    ModelRenderer Left_Eye;
    ModelRenderer Main_Chestplate_Piece;
    ModelRenderer Front_Chestplate_Piece;
    ModelRenderer Right_Chestplate_Piece;
    ModelRenderer Left_Chestplate_Piece;
    ModelRenderer Right_Shoulder_Pad_Main_Piece;
    ModelRenderer Right_Shoulder_Pad_Front_Strap;
    ModelRenderer Right_Shoulder_Pad_Back_Strap;
    ModelRenderer Left_Shoulder_Pad_Main_Piece;
    ModelRenderer Left_Shoulder_Pad_Front_Strap;
    ModelRenderer Left_Shoulder_Pad_Back_Strap;
    ModelRenderer Bottom_Armor_1;
    ModelRenderer Bottom_Armor_2;
    ModelRenderer Right_Glove_1;
    ModelRenderer Right_Glove_2;
    ModelRenderer Left_Glove_1;
    ModelRenderer Left_Glove_2;
    ModelRenderer Ethereal_Head;
    ModelRenderer Ethereal_Torso;
    ModelRenderer Ethereal_Waist;
    ModelRenderer Ethereal_Lower_Body_1;
    ModelRenderer Ethereal_Lower_Body_2;
    ModelRenderer Right_Ethereal_Hand;
    ModelRenderer Right_Ethereal_Claw_1_1;
    ModelRenderer Right_Ethereal_Claw_1_2;
    ModelRenderer Right_Ethereal_Claw_2_1;
    ModelRenderer Right_Ethereal_Claw_2_2;
    ModelRenderer Right_Ethereal_Claw_3_1;
    ModelRenderer Right_Ethereal_Claw_3_2;
    ModelRenderer Left_Ethereal_Hand;
    ModelRenderer Left_Ethereal_Claw_1_1;
    ModelRenderer Left_Ethereal_Claw_1_2;
    ModelRenderer Left_Ethereal_Claw_2_1;
    ModelRenderer Left_Ethereal_Claw_2_2;
    ModelRenderer Left_Ethereal_Claw_3_1;
    ModelRenderer Left_Ethereal_Claw_3_2;

    public ModelDarkPet2() {
        textureWidth = 128;
        textureHeight = 64;

        Helmet_Top = new ModelRenderer(this, 46, 8);
        Helmet_Top.addBox(-4F, -10F, -4F, 8, 3, 8);
        Helmet_Top.setRotationPoint(0F, -6F, 0F);
        Helmet_Top.setTextureSize(128, 64);
        Helmet_Top.mirror = true;
        setRotation(Helmet_Top, 0F, 0F, 0F);
        Helmet_Front_Top_1 = new ModelRenderer(this, 53, 1);
        Helmet_Front_Top_1.addBox(-3F, -11F, -6F, 6, 4, 3);
        Helmet_Front_Top_1.setRotationPoint(0F, -6F, 0F);
        Helmet_Front_Top_1.setTextureSize(128, 64);
        Helmet_Front_Top_1.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Front_Top_1);
        setRotation(Helmet_Front_Top_1, 0F, 0F, 0F);
        Helmet_Front_Top_2 = new ModelRenderer(this, 43, 0);
        Helmet_Front_Top_2.addBox(-1F, -13F, -7F, 2, 4, 3);
        Helmet_Front_Top_2.setRotationPoint(0F, -6F, 0F);
        Helmet_Front_Top_2.setTextureSize(128, 64);
        Helmet_Front_Top_2.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Front_Top_2);
        setRotation(Helmet_Front_Top_2, 0F, 0F, 0F);
        Helmet_Front_Right = new ModelRenderer(this, 50, 21);
        Helmet_Front_Right.addBox(-3F, -6F, -5F, 2, 6, 2);
        Helmet_Front_Right.setRotationPoint(0F, -6F, 0F);
        Helmet_Front_Right.setTextureSize(128, 64);
        Helmet_Front_Right.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Front_Right);
        setRotation(Helmet_Front_Right, 0F, 0F, 0F);
        Helmet_Front_Middle = new ModelRenderer(this, 58, 22);
        Helmet_Front_Middle.addBox(-1F, -5F, -4.5F, 2, 5, 2);
        Helmet_Front_Middle.setRotationPoint(0F, -6F, 0F);
        Helmet_Front_Middle.setTextureSize(128, 64);
        Helmet_Front_Middle.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Front_Middle);
        setRotation(Helmet_Front_Middle, 0F, 0F, 0F);
        Helmet_Fron_Left = new ModelRenderer(this, 66, 21);
        Helmet_Fron_Left.addBox(1F, -6F, -5F, 2, 6, 2);
        Helmet_Fron_Left.setRotationPoint(0F, -6F, 0F);
        Helmet_Fron_Left.setTextureSize(128, 64);
        Helmet_Fron_Left.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Fron_Left);
        setRotation(Helmet_Fron_Left, 0F, 0F, 0F);
        Helmet_Right_Side = new ModelRenderer(this, 28, 10);
        Helmet_Right_Side.addBox(-5F, -9F, -5F, 2, 8, 9);
        Helmet_Right_Side.setRotationPoint(0F, -6F, 0F);
        Helmet_Right_Side.setTextureSize(128, 64);
        Helmet_Right_Side.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Right_Side);
        setRotation(Helmet_Right_Side, 0F, 0F, 0F);
        Helmet_Left_Side = new ModelRenderer(this, 74, 10);
        Helmet_Left_Side.addBox(3F, -9F, -5F, 2, 8, 9);
        Helmet_Left_Side.setRotationPoint(0F, -6F, 0F);
        Helmet_Left_Side.setTextureSize(128, 64);
        Helmet_Left_Side.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Left_Side);
        setRotation(Helmet_Left_Side, 0F, 0F, 0F);
        Helmet_Back = new ModelRenderer(this, 71, 0);
        Helmet_Back.addBox(-3F, -8F, 3F, 6, 7, 2);
        Helmet_Back.setRotationPoint(0F, -6F, 0F);
        Helmet_Back.setTextureSize(128, 64);
        Helmet_Back.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Back);
        setRotation(Helmet_Back, 0F, 0F, 0F);
        Helmet_Right_Horn = new ModelRenderer(this, 35, 3);
        Helmet_Right_Horn.addBox(-2F, -14F, -8F, 1, 6, 1);
        Helmet_Right_Horn.setRotationPoint(0F, -6F, 0F);
        Helmet_Right_Horn.setTextureSize(128, 64);
        Helmet_Right_Horn.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Right_Horn);
        setRotation(Helmet_Right_Horn, 0F, 0F, 0F);
        Helmet_Left_Horn = new ModelRenderer(this, 39, 3);
        Helmet_Left_Horn.addBox(1F, -14F, -8F, 1, 6, 1);
        Helmet_Left_Horn.setRotationPoint(0F, -6F, 0F);
        Helmet_Left_Horn.setTextureSize(128, 64);
        Helmet_Left_Horn.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Left_Horn);
        setRotation(Helmet_Left_Horn, 0F, 0F, 0F);
        Helmet_Horn_Bottom_Piece = new ModelRenderer(this, 35, 0);
        Helmet_Horn_Bottom_Piece.addBox(-1F, -9F, -8F, 2, 1, 2);
        Helmet_Horn_Bottom_Piece.setRotationPoint(0F, -6F, 0F);
        Helmet_Horn_Bottom_Piece.setTextureSize(128, 64);
        Helmet_Horn_Bottom_Piece.mirror = true;
        this.convertToChild(Helmet_Top, Helmet_Horn_Bottom_Piece);
        setRotation(Helmet_Horn_Bottom_Piece, 0F, 0F, 0F);
        Right_Eye = new ModelRenderer(this, 50, 19);
        Right_Eye.addBox(-3F, -7F, -4F, 2, 1, 1);
        Right_Eye.setRotationPoint(0F, -6F, 0F);
        Right_Eye.setTextureSize(128, 64);
        Right_Eye.mirror = true;
        this.convertToChild(Helmet_Top, Right_Eye);
        setRotation(Right_Eye, 0F, 0F, 0F);
        Left_Eye = new ModelRenderer(this, 68, 19);
        Left_Eye.addBox(1F, -7F, -4F, 2, 1, 1);
        Left_Eye.setRotationPoint(0F, -6F, 0F);
        Left_Eye.setTextureSize(128, 64);
        Left_Eye.mirror = true;
        this.convertToChild(Helmet_Top, Left_Eye);
        setRotation(Left_Eye, 0F, 0F, 0F);
        Main_Chestplate_Piece = new ModelRenderer(this, 48, 29);
        Main_Chestplate_Piece.addBox(-6F, 1F, -5F, 12, 11, 2);
        Main_Chestplate_Piece.setRotationPoint(0F, -6F, 0F);
        Main_Chestplate_Piece.setTextureSize(128, 64);
        Main_Chestplate_Piece.mirror = true;
        setRotation(Main_Chestplate_Piece, 0F, 0F, 0F);
        Front_Chestplate_Piece = new ModelRenderer(this, 56, 42);
        Front_Chestplate_Piece.addBox(-2F, 2F, -6F, 4, 11, 2);
        Front_Chestplate_Piece.setRotationPoint(0F, -6F, 0F);
        Front_Chestplate_Piece.setTextureSize(128, 64);
        Front_Chestplate_Piece.mirror = true;
        setRotation(Front_Chestplate_Piece, 0F, 0F, 0F);
        Right_Chestplate_Piece = new ModelRenderer(this, 34, 27);
        Right_Chestplate_Piece.addBox(-8F, 0F, -4F, 4, 11, 3);
        Right_Chestplate_Piece.setRotationPoint(0F, -6F, 0F);
        Right_Chestplate_Piece.setTextureSize(128, 64);
        Right_Chestplate_Piece.mirror = true;
        setRotation(Right_Chestplate_Piece, 0F, 0F, 0F);
        Left_Chestplate_Piece = new ModelRenderer(this, 76, 27);
        Left_Chestplate_Piece.addBox(4F, 0F, -4F, 4, 11, 3);
        Left_Chestplate_Piece.setRotationPoint(0F, -6F, 0F);
        Left_Chestplate_Piece.setTextureSize(128, 64);
        Left_Chestplate_Piece.mirror = true;
        setRotation(Left_Chestplate_Piece, 0F, 0F, 0F);
        Right_Shoulder_Pad_Main_Piece = new ModelRenderer(this, 6, 20);
        Right_Shoulder_Pad_Main_Piece.addBox(-6F, -5F, -4F, 6, 2, 8);
        Right_Shoulder_Pad_Main_Piece.setRotationPoint(-8F, -3F, 0F);
        Right_Shoulder_Pad_Main_Piece.setTextureSize(128, 64);
        Right_Shoulder_Pad_Main_Piece.mirror = true;
        setRotation(Right_Shoulder_Pad_Main_Piece, 0F, 0F, -0.2617994F);
        Right_Shoulder_Pad_Front_Strap = new ModelRenderer(this, 14, 30);
        Right_Shoulder_Pad_Front_Strap.addBox(-5F, -4F, -5F, 4, 3, 2);
        Right_Shoulder_Pad_Front_Strap.setRotationPoint(-8F, -3F, 0F);
        Right_Shoulder_Pad_Front_Strap.setTextureSize(128, 64);
        Right_Shoulder_Pad_Front_Strap.mirror = true;
        setRotation(Right_Shoulder_Pad_Front_Strap, 0F, 0F, -0.2617994F);
        Right_Shoulder_Pad_Back_Strap = new ModelRenderer(this, 14, 15);
        Right_Shoulder_Pad_Back_Strap.addBox(-5F, -4F, 3F, 4, 3, 2);
        Right_Shoulder_Pad_Back_Strap.setRotationPoint(-8F, -3F, 0F);
        Right_Shoulder_Pad_Back_Strap.setTextureSize(128, 64);
        Right_Shoulder_Pad_Back_Strap.mirror = true;
        setRotation(Right_Shoulder_Pad_Back_Strap, 0F, 0F, -0.2617994F);
        Left_Shoulder_Pad_Main_Piece = new ModelRenderer(this, 90, 20);
        Left_Shoulder_Pad_Main_Piece.addBox(0F, -5F, -4F, 6, 2, 8);
        Left_Shoulder_Pad_Main_Piece.setRotationPoint(8F, -3F, 0F);
        Left_Shoulder_Pad_Main_Piece.setTextureSize(128, 64);
        Left_Shoulder_Pad_Main_Piece.mirror = true;
        setRotation(Left_Shoulder_Pad_Main_Piece, 0F, 0F, 0.2617994F);
        Left_Shoulder_Pad_Front_Strap = new ModelRenderer(this, 98, 30);
        Left_Shoulder_Pad_Front_Strap.addBox(1F, -4F, -5F, 4, 3, 2);
        Left_Shoulder_Pad_Front_Strap.setRotationPoint(8F, -3F, 0F);
        Left_Shoulder_Pad_Front_Strap.setTextureSize(128, 64);
        Left_Shoulder_Pad_Front_Strap.mirror = true;
        setRotation(Left_Shoulder_Pad_Front_Strap, 0F, 0F, 0.2617994F);
        Left_Shoulder_Pad_Back_Strap = new ModelRenderer(this, 98, 15);
        Left_Shoulder_Pad_Back_Strap.addBox(1F, -4F, 3F, 4, 3, 2);
        Left_Shoulder_Pad_Back_Strap.setRotationPoint(8F, -3F, 0F);
        Left_Shoulder_Pad_Back_Strap.setTextureSize(128, 64);
        Left_Shoulder_Pad_Back_Strap.mirror = true;
        setRotation(Left_Shoulder_Pad_Back_Strap, 0F, 0F, 0.2617994F);
        Bottom_Armor_1 = new ModelRenderer(this, 54, 55);
        Bottom_Armor_1.addBox(-3F, 16F, -3F, 6, 3, 2);
        Bottom_Armor_1.setRotationPoint(0F, -6F, 0F);
        Bottom_Armor_1.setTextureSize(128, 64);
        Bottom_Armor_1.mirror = true;
        setRotation(Bottom_Armor_1, 0F, 0F, 0F);
        Bottom_Armor_2 = new ModelRenderer(this, 57, 60);
        Bottom_Armor_2.addBox(-2F, 19F, -2F, 4, 3, 1);
        Bottom_Armor_2.setRotationPoint(0F, -6F, 0F);
        Bottom_Armor_2.setTextureSize(128, 64);
        Bottom_Armor_2.mirror = true;
        setRotation(Bottom_Armor_2, 0F, 0F, 0F);
        Right_Glove_1 = new ModelRenderer(this, 13, 35);
        Right_Glove_1.addBox(-6F, 2F, -7F, 2, 5, 5);
        Right_Glove_1.setRotationPoint(-8F, -3F, 0F);
        Right_Glove_1.setTextureSize(128, 64);
        Right_Glove_1.mirror = true;
        setRotation(Right_Glove_1, 0.7853982F, 0F, 0.2617994F);
        Right_Glove_2 = new ModelRenderer(this, 10, 45);
        Right_Glove_2.addBox(-7.5F, 6F, -2.5F, 5, 4, 5);
        Right_Glove_2.setRotationPoint(-8F, -3F, 0F);
        Right_Glove_2.setTextureSize(128, 64);
        Right_Glove_2.mirror = true;
        setRotation(Right_Glove_2, 0F, 0F, 0.2617994F);
        Left_Glove_1 = new ModelRenderer(this, 97, 35);
        Left_Glove_1.addBox(4F, 2F, -7F, 2, 5, 5);
        Left_Glove_1.setRotationPoint(8F, -3F, 0F);
        Left_Glove_1.setTextureSize(128, 64);
        Left_Glove_1.mirror = true;
        setRotation(Left_Glove_1, 0.7853982F, 0F, -0.2617994F);
        Left_Glove_2 = new ModelRenderer(this, 94, 45);
        Left_Glove_2.addBox(2.5F, 6F, -2.5F, 5, 4, 5);
        Left_Glove_2.setRotationPoint(8F, -3F, 0F);
        Left_Glove_2.setTextureSize(128, 64);
        Left_Glove_2.mirror = true;
        setRotation(Left_Glove_2, 0F, 0F, -0.2617994F);
        Ethereal_Head = new ModelRenderer(this, 0, -13);
        Ethereal_Head.addBox(-3F, -7F, -3F, 6, 7, 6);
        Ethereal_Head.setRotationPoint(0F, -6F, 0F);
        Ethereal_Head.setTextureSize(128, 64);
        Ethereal_Head.mirror = true;
        this.convertToChild(Helmet_Top, Ethereal_Head);
        setRotation(Ethereal_Head, 0F, 0F, 0F);
        Ethereal_Torso = new ModelRenderer(this, 0, -18);
        Ethereal_Torso.addBox(-8F, 0F, -3F, 16, 11, 7);
        Ethereal_Torso.setRotationPoint(0F, -6F, 0F);
        Ethereal_Torso.setTextureSize(128, 64);
        Ethereal_Torso.mirror = true;
        setRotation(Ethereal_Torso, 0F, 0F, 0F);
        Ethereal_Waist = new ModelRenderer(this, 0, -8);
        Ethereal_Waist.addBox(-6F, 11F, -3F, 12, 1, 7);
        Ethereal_Waist.setRotationPoint(0F, -6F, 0F);
        Ethereal_Waist.setTextureSize(128, 64);
        Ethereal_Waist.mirror = true;
        setRotation(Ethereal_Waist, 0F, 0F, 0F);
        Ethereal_Lower_Body_1 = new ModelRenderer(this, 0, -12);
        Ethereal_Lower_Body_1.addBox(-4F, 12F, -2.5F, 8, 6, 6);
        Ethereal_Lower_Body_1.setRotationPoint(0F, -6F, 0F);
        Ethereal_Lower_Body_1.setTextureSize(128, 64);
        Ethereal_Lower_Body_1.mirror = true;
        setRotation(Ethereal_Lower_Body_1, 0F, 0F, 0F);
        Ethereal_Lower_Body_2 = new ModelRenderer(this, 0, -10);
        Ethereal_Lower_Body_2.addBox(-1.5F, 18F, -1F, 3, 6, 4);
        Ethereal_Lower_Body_2.setRotationPoint(0F, -6F, 0F);
        Ethereal_Lower_Body_2.setTextureSize(128, 64);
        Ethereal_Lower_Body_2.mirror = true;
        setRotation(Ethereal_Lower_Body_2, 0F, 0F, 0F);
        Right_Ethereal_Hand = new ModelRenderer(this, 0, -12);
        Right_Ethereal_Hand.addBox(-8F, 10F, -3F, 6, 6, 6);
        Right_Ethereal_Hand.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Hand.setTextureSize(128, 64);
        Right_Ethereal_Hand.mirror = true;
        setRotation(Right_Ethereal_Hand, 0F, 0F, 0.2617994F);
        Right_Ethereal_Claw_1_1 = new ModelRenderer(this, 0, -5);
        Right_Ethereal_Claw_1_1.addBox(-13F, 8F, -1F, 2, 3, 2);
        Right_Ethereal_Claw_1_1.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_1_1.setTextureSize(128, 64);
        Right_Ethereal_Claw_1_1.mirror = true;
        setRotation(Right_Ethereal_Claw_1_1, 0F, 0F, -0.5235988F);
        Right_Ethereal_Claw_1_2 = new ModelRenderer(this, 0, -4);
        Right_Ethereal_Claw_1_2.addBox(-16.5F, 11F, -1F, 6, 2, 2);
        Right_Ethereal_Claw_1_2.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_1_2.setTextureSize(128, 64);
        Right_Ethereal_Claw_1_2.mirror = true;
        setRotation(Right_Ethereal_Claw_1_2, 0F, 0F, -0.5235988F);
        Right_Ethereal_Claw_2_1 = new ModelRenderer(this, 0, -5);
        Right_Ethereal_Claw_2_1.addBox(4F, 14F, 5F, 2, 3, 2);
        Right_Ethereal_Claw_2_1.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_2_1.setTextureSize(128, 64);
        Right_Ethereal_Claw_2_1.mirror = true;
        setRotation(Right_Ethereal_Claw_2_1, -0.5235988F, 0F, 1.047198F);
        Right_Ethereal_Claw_2_2 = new ModelRenderer(this, 0, -4);
        Right_Ethereal_Claw_2_2.addBox(3.5F, 17F, 5F, 6, 2, 2);
        Right_Ethereal_Claw_2_2.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_2_2.setTextureSize(128, 64);
        Right_Ethereal_Claw_2_2.mirror = true;
        setRotation(Right_Ethereal_Claw_2_2, -0.5235988F, 0F, 1.047198F);
        Right_Ethereal_Claw_3_1 = new ModelRenderer(this, 0, -5);
        Right_Ethereal_Claw_3_1.addBox(4F, 14F, -7F, 2, 3, 2);
        Right_Ethereal_Claw_3_1.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_3_1.setTextureSize(128, 64);
        Right_Ethereal_Claw_3_1.mirror = true;
        setRotation(Right_Ethereal_Claw_3_1, 0.5235988F, 0F, 1.047198F);
        Right_Ethereal_Claw_3_2 = new ModelRenderer(this, 0, -4);
        Right_Ethereal_Claw_3_2.addBox(3.5F, 17F, -7F, 6, 2, 2);
        Right_Ethereal_Claw_3_2.setRotationPoint(-8F, -3F, 0F);
        Right_Ethereal_Claw_3_2.setTextureSize(128, 64);
        Right_Ethereal_Claw_3_2.mirror = true;
        setRotation(Right_Ethereal_Claw_3_2, 0.5235988F, 0F, 1.047198F);
        Left_Ethereal_Hand = new ModelRenderer(this, 0, -12);
        Left_Ethereal_Hand.addBox(2F, 10F, -3F, 6, 6, 6);
        Left_Ethereal_Hand.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Hand.setTextureSize(128, 64);
        Left_Ethereal_Hand.mirror = true;
        setRotation(Left_Ethereal_Hand, 0F, 0F, -0.2617994F);
        Left_Ethereal_Claw_1_1 = new ModelRenderer(this, 0, -5);
        Left_Ethereal_Claw_1_1.addBox(11F, 8F, -1F, 2, 3, 2);
        Left_Ethereal_Claw_1_1.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_1_1.setTextureSize(128, 64);
        Left_Ethereal_Claw_1_1.mirror = true;
        setRotation(Left_Ethereal_Claw_1_1, 0F, 0F, 0.5235988F);
        Left_Ethereal_Claw_1_2 = new ModelRenderer(this, 0, -4);
        Left_Ethereal_Claw_1_2.addBox(10.5F, 11F, -1F, 6, 2, 2);
        Left_Ethereal_Claw_1_2.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_1_2.setTextureSize(128, 64);
        Left_Ethereal_Claw_1_2.mirror = true;
        setRotation(Left_Ethereal_Claw_1_2, 0F, 0F, 0.5235988F);
        Left_Ethereal_Claw_2_1 = new ModelRenderer(this, 0, -5);
        Left_Ethereal_Claw_2_1.addBox(-6F, 14F, 5F, 2, 3, 2);
        Left_Ethereal_Claw_2_1.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_2_1.setTextureSize(128, 64);
        Left_Ethereal_Claw_2_1.mirror = true;
        setRotation(Left_Ethereal_Claw_2_1, -0.5235988F, 0F, -1.047198F);
        Left_Ethereal_Claw_2_2 = new ModelRenderer(this, 0, -4);
        Left_Ethereal_Claw_2_2.addBox(-9.5F, 17F, 5F, 6, 2, 2);
        Left_Ethereal_Claw_2_2.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_2_2.setTextureSize(128, 64);
        Left_Ethereal_Claw_2_2.mirror = true;
        setRotation(Left_Ethereal_Claw_2_2, -0.5235988F, 0F, -1.047198F);
        Left_Ethereal_Claw_3_1 = new ModelRenderer(this, 0, -5);
        Left_Ethereal_Claw_3_1.addBox(-6F, 14F, -7F, 2, 3, 2);
        Left_Ethereal_Claw_3_1.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_3_1.setTextureSize(128, 64);
        Left_Ethereal_Claw_3_1.mirror = true;
        setRotation(Left_Ethereal_Claw_3_1, 0.5235988F, 0F, -1.047198F);
        Left_Ethereal_Claw_3_2 = new ModelRenderer(this, 0, -4);
        Left_Ethereal_Claw_3_2.addBox(-9.5F, 17F, -7F, 6, 2, 2);
        Left_Ethereal_Claw_3_2.setRotationPoint(8F, -3F, 0F);
        Left_Ethereal_Claw_3_2.setTextureSize(128, 64);
        Left_Ethereal_Claw_3_2.mirror = true;
        setRotation(Left_Ethereal_Claw_3_2, 0.5235988F, 0F, -1.047198F);
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

        EntityDarkPet2 darkPet = (EntityDarkPet2) entity;

        Helmet_Top.rotationPointY = darkPet.getFloatingRotation();
        Main_Chestplate_Piece.rotationPointY = darkPet.getFloatingRotation();
        Front_Chestplate_Piece.rotationPointY = darkPet.getFloatingRotation();
        Right_Chestplate_Piece.rotationPointY = darkPet.getFloatingRotation();
        Left_Chestplate_Piece.rotationPointY = darkPet.getFloatingRotation();
        Right_Shoulder_Pad_Main_Piece.rotationPointY = darkPet.getFloatingRotation();
        Right_Shoulder_Pad_Front_Strap.rotationPointY = darkPet.getFloatingRotation();
        Right_Shoulder_Pad_Back_Strap.rotationPointY = darkPet.getFloatingRotation();
        Left_Shoulder_Pad_Main_Piece.rotationPointY = darkPet.getFloatingRotation();
        Left_Shoulder_Pad_Front_Strap.rotationPointY = darkPet.getFloatingRotation();
        Left_Shoulder_Pad_Back_Strap.rotationPointY = darkPet.getFloatingRotation();
        Bottom_Armor_1.rotationPointY = darkPet.getFloatingRotation();
        Bottom_Armor_2.rotationPointY = darkPet.getFloatingRotation();
        Right_Glove_1.rotationPointY = darkPet.getFloatingRotation();
        Left_Glove_1.rotationPointY = darkPet.getFloatingRotation();
        Right_Glove_2.rotationPointY = darkPet.getFloatingRotation();
        Left_Glove_2.rotationPointY = darkPet.getFloatingRotation();

        Helmet_Top.render(f5);
        Main_Chestplate_Piece.render(f5);
        Front_Chestplate_Piece.render(f5);
        Right_Chestplate_Piece.render(f5);
        Left_Chestplate_Piece.render(f5);
        Right_Shoulder_Pad_Main_Piece.render(f5);
        Right_Shoulder_Pad_Front_Strap.render(f5);
        Right_Shoulder_Pad_Back_Strap.render(f5);
        Left_Shoulder_Pad_Main_Piece.render(f5);
        Left_Shoulder_Pad_Front_Strap.render(f5);
        Left_Shoulder_Pad_Back_Strap.render(f5);
        Bottom_Armor_1.render(f5);
        Bottom_Armor_2.render(f5);
        Right_Glove_1.render(f5);
        Right_Glove_2.render(f5);
        Left_Glove_1.render(f5);
        Left_Glove_2.render(f5);
        Ethereal_Torso.render(f5);
        Ethereal_Waist.render(f5);
        Ethereal_Lower_Body_1.render(f5);
        Ethereal_Lower_Body_2.render(f5);
        Right_Ethereal_Hand.render(f5);
        Right_Ethereal_Claw_1_1.render(f5);
        Right_Ethereal_Claw_1_2.render(f5);
        Right_Ethereal_Claw_2_1.render(f5);
        Right_Ethereal_Claw_2_2.render(f5);
        Right_Ethereal_Claw_3_1.render(f5);
        Right_Ethereal_Claw_3_2.render(f5);
        Left_Ethereal_Hand.render(f5);
        Left_Ethereal_Claw_1_1.render(f5);
        Left_Ethereal_Claw_1_2.render(f5);
        Left_Ethereal_Claw_2_1.render(f5);
        Left_Ethereal_Claw_2_2.render(f5);
        Left_Ethereal_Claw_3_1.render(f5);
        Left_Ethereal_Claw_3_2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ////////////////////////////////HEAD///////////////////////////////////////
        this.Helmet_Top.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.Helmet_Top.rotateAngleY = f3 / (180F / (float) Math.PI);
    }

}

