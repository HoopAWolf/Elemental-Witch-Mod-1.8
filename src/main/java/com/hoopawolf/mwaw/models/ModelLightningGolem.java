package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelLightningGolem extends ModelBase {
    //fields
    ModelRenderer Torso_Top;
    ModelRenderer Shoulder_Blade_Left_1;
    ModelRenderer Shoulder_Blade_Right_1;
    ModelRenderer Shoulder_Blade_Left_2;
    ModelRenderer Shoulder_Blade_Right_2;
    ModelRenderer Spine_1;
    ModelRenderer Spine_2;
    ModelRenderer Vertebra_1;
    ModelRenderer Vertebra_2;
    ModelRenderer Vertebra_3;
    ModelRenderer Vertebra_4;
    ModelRenderer Vertebra_5;
    ModelRenderer Vertebra_6;
    ModelRenderer Rib_1;
    ModelRenderer Rib_2;
    ModelRenderer Rib_3;
    ModelRenderer Rib_4;
    ModelRenderer Chestbone;
    ModelRenderer Detail_1;
    ModelRenderer Detail_2;
    ModelRenderer Detail_3;
    ModelRenderer Head_1;
    ModelRenderer Head_2;
    ModelRenderer Head_3;
    ModelRenderer Jaw;
    ModelRenderer Shoulder_Right;
    ModelRenderer Shoulder_Left;
    ModelRenderer Arm_Right_1;
    ModelRenderer Arm_Left_1;
    ModelRenderer Arm_Right_2;
    ModelRenderer Arm_Left_2;

    public ModelLightningGolem() {
        textureWidth = 256;
        textureHeight = 128;

        Torso_Top = new ModelRenderer(this, 0, 0);
        Torso_Top.addBox(-10.5F, -1F, -2F, 21, 5, 13);
        Torso_Top.setRotationPoint(0F, -16F, -4F);
        Torso_Top.setTextureSize(256, 128);
        Torso_Top.mirror = true;
        setRotation(Torso_Top, 0.1487144F, 0F, 0F);
        Shoulder_Blade_Left_1 = new ModelRenderer(this, 0, 19);
        Shoulder_Blade_Left_1.addBox(1F, 3F, 9F, 9, 11, 2);
        Shoulder_Blade_Left_1.setRotationPoint(0F, -16F, -4F);
        Shoulder_Blade_Left_1.setTextureSize(256, 128);
        Shoulder_Blade_Left_1.mirror = true;
        setRotation(Shoulder_Blade_Left_1, 0.111544F, 0F, 0F);
        Shoulder_Blade_Right_1 = new ModelRenderer(this, 24, 19);
        Shoulder_Blade_Right_1.addBox(-10F, 3F, 9F, 9, 11, 2);
        Shoulder_Blade_Right_1.setRotationPoint(0F, -16F, -4F);
        Shoulder_Blade_Right_1.setTextureSize(256, 128);
        Shoulder_Blade_Right_1.mirror = true;
        setRotation(Shoulder_Blade_Right_1, 0.111544F, 0F, 0F);
        Shoulder_Blade_Left_2 = new ModelRenderer(this, 0, 33);
        Shoulder_Blade_Left_2.addBox(1F, 14F, 9F, 7, 4, 2);
        Shoulder_Blade_Left_2.setRotationPoint(0F, -16F, -4F);
        Shoulder_Blade_Left_2.setTextureSize(256, 128);
        Shoulder_Blade_Left_2.mirror = true;
        setRotation(Shoulder_Blade_Left_2, 0.111544F, 0F, 0F);
        Shoulder_Blade_Right_2 = new ModelRenderer(this, 21, 33);
        Shoulder_Blade_Right_2.addBox(-8F, 14F, 9F, 7, 4, 2);
        Shoulder_Blade_Right_2.setRotationPoint(0F, -16F, -4F);
        Shoulder_Blade_Right_2.setTextureSize(256, 128);
        Shoulder_Blade_Right_2.mirror = true;
        setRotation(Shoulder_Blade_Right_2, 0.111544F, 0F, 0F);
        Spine_1 = new ModelRenderer(this, 0, 40);
        Spine_1.addBox(-1.5F, -1F, 7.5F, 3, 17, 3);
        Spine_1.setRotationPoint(0F, -16F, -4F);
        Spine_1.setTextureSize(256, 128);
        Spine_1.mirror = true;
        setRotation(Spine_1, 0.1115409F, 0F, 0F);
        Spine_2 = new ModelRenderer(this, 13, 40);
        Spine_2.addBox(-1.5F, 15F, 8.6F, 3, 11, 3);
        Spine_2.setRotationPoint(0F, -16F, -4F);
        Spine_2.setTextureSize(256, 128);
        Spine_2.mirror = true;
        setRotation(Spine_2, 0.0371786F, 0F, 0F);
        Vertebra_1 = new ModelRenderer(this, 0, 61);
        Vertebra_1.addBox(-3.5F, 0F, 10.5F, 7, 2, 2);
        Vertebra_1.setRotationPoint(0F, -16F, -4F);
        Vertebra_1.setTextureSize(256, 128);
        Vertebra_1.mirror = true;
        setRotation(Vertebra_1, 0.111544F, 0F, 0F);
        Vertebra_2 = new ModelRenderer(this, 21, 61);
        Vertebra_2.addBox(-3F, 4F, 10.5F, 6, 2, 2);
        Vertebra_2.setRotationPoint(0F, -16F, -4F);
        Vertebra_2.setTextureSize(256, 128);
        Vertebra_2.mirror = true;
        setRotation(Vertebra_2, 0.111544F, 0F, 0F);
        Vertebra_3 = new ModelRenderer(this, 40, 61);
        Vertebra_3.addBox(-2.5F, 8F, 10.5F, 5, 2, 2);
        Vertebra_3.setRotationPoint(0F, -16F, -4F);
        Vertebra_3.setTextureSize(256, 128);
        Vertebra_3.mirror = true;
        setRotation(Vertebra_3, 0.111544F, 0F, 0F);
        Vertebra_4 = new ModelRenderer(this, 58, 61);
        Vertebra_4.addBox(-2F, 12F, 10.5F, 4, 2, 2);
        Vertebra_4.setRotationPoint(0F, -16F, -4F);
        Vertebra_4.setTextureSize(256, 128);
        Vertebra_4.mirror = true;
        setRotation(Vertebra_4, 0.111544F, 0F, 0F);
        Vertebra_5 = new ModelRenderer(this, 73, 61);
        Vertebra_5.addBox(-1.5F, 16F, 10.5F, 3, 2, 2);
        Vertebra_5.setRotationPoint(0F, -16F, -4F);
        Vertebra_5.setTextureSize(256, 128);
        Vertebra_5.mirror = true;
        setRotation(Vertebra_5, 0.0743654F, 0F, 0F);
        Vertebra_6 = new ModelRenderer(this, 85, 61);
        Vertebra_6.addBox(-1.5F, -2F, 5F, 3, 3, 3);
        Vertebra_6.setRotationPoint(0F, -16F, -4F);
        Vertebra_6.setTextureSize(256, 128);
        Vertebra_6.mirror = true;
        setRotation(Vertebra_6, 0.297434F, 0F, 0F);
        Rib_1 = new ModelRenderer(this, 0, 66);
        Rib_1.addBox(-10F, 6F, -1F, 20, 2, 10);
        Rib_1.setRotationPoint(0F, -16F, -4F);
        Rib_1.setTextureSize(256, 128);
        Rib_1.mirror = true;
        setRotation(Rib_1, 0.111544F, 0F, 0F);
        Rib_2 = new ModelRenderer(this, 0, 79);
        Rib_2.addBox(-10F, 10F, -1F, 20, 2, 10);
        Rib_2.setRotationPoint(0F, -16F, -4F);
        Rib_2.setTextureSize(256, 128);
        Rib_2.mirror = true;
        setRotation(Rib_2, 0.111544F, 0F, 0F);
        Rib_3 = new ModelRenderer(this, 0, 92);
        Rib_3.addBox(-8F, 14F, 0F, 16, 2, 9);
        Rib_3.setRotationPoint(0F, -16F, -4F);
        Rib_3.setTextureSize(256, 128);
        Rib_3.mirror = true;
        setRotation(Rib_3, 0.111544F, 0F, 0F);
        Rib_4 = new ModelRenderer(this, 0, 104);
        Rib_4.addBox(-6F, 19F, 2F, 12, 2, 8);
        Rib_4.setRotationPoint(0F, -16F, -4F);
        Rib_4.setTextureSize(256, 128);
        Rib_4.mirror = true;
        setRotation(Rib_4, 0.0371755F, -0.1858931F, -0.1115358F);
        Chestbone = new ModelRenderer(this, 28, 40);
        Chestbone.addBox(-1.5F, -1F, -2.5F, 3, 12, 2);
        Chestbone.setRotationPoint(0F, -16F, -4F);
        Chestbone.setTextureSize(256, 128);
        Chestbone.mirror = true;
        setRotation(Chestbone, 0.2230767F, 0F, 0F);
        Detail_1 = new ModelRenderer(this, 63, 71);
        Detail_1.addBox(-2F, -1F, -2F, 6, 2, 5);
        Detail_1.setRotationPoint(-1F, 11F, 3F);
        Detail_1.setTextureSize(256, 128);
        Detail_1.mirror = true;
        setRotation(Detail_1, -0.1487144F, 0.0371786F, 0.3717861F);
        Detail_2 = new ModelRenderer(this, 87, 73);
        Detail_2.addBox(-2F, -1F, -1.5F, 4, 2, 3);
        Detail_2.setRotationPoint(1F, 17F, 4F);
        Detail_2.setTextureSize(256, 128);
        Detail_2.mirror = true;
        setRotation(Detail_2, 0.1858931F, -0.2602503F, 0F);
        Detail_3 = new ModelRenderer(this, 104, 74);
        Detail_3.addBox(-1F, -1F, -1F, 2, 2, 2);
        Detail_3.setRotationPoint(-2.866667F, 21F, 3F);
        Detail_3.setTextureSize(256, 128);
        Detail_3.mirror = true;
        setRotation(Detail_3, -0.4089647F, 0.3346075F, 0F);
        Head_1 = new ModelRenderer(this, 80, 0);
        Head_1.addBox(-5F, -10F, -2F, 10, 7, 9);
        Head_1.setRotationPoint(0F, -18F, -3F);
        Head_1.setTextureSize(256, 128);
        Head_1.mirror = true;
        setRotation(Head_1, 0.1858931F, 0F, 0F);
        Head_2 = new ModelRenderer(this, 80, 17);
        Head_2.addBox(-4.5F, -3F, 2.5F, 9, 3, 4);
        Head_2.setRotationPoint(0F, -18F, -3F);
        Head_2.setTextureSize(256, 128);
        Head_2.mirror = true;
        setRotation(Head_2, 0.1858931F, 0F, 0F);
        Head_3 = new ModelRenderer(this, 109, 17);
        Head_3.addBox(-1F, -10.5F, -3F, 2, 11, 9);
        Head_3.setRotationPoint(0F, -18F, -3F);
        Head_3.setTextureSize(256, 128);
        Head_3.mirror = true;
        setRotation(Head_3, 0.185895F, 0F, 0F);
        Jaw = new ModelRenderer(this, 80, 25);
        Jaw.addBox(-3F, -4F, -2.5F, 6, 4, 6);
        Jaw.setRotationPoint(0F, -18F, -3F);
        Jaw.setTextureSize(256, 128);
        Jaw.mirror = true;
        setRotation(Jaw, 0.185895F, 0F, 0F);
        Shoulder_Right = new ModelRenderer(this, 145, 0);
        Shoulder_Right.addBox(-3F, -3F, -3F, 5, 6, 7);
        Shoulder_Right.setRotationPoint(-10F, -16F, 2F);
        Shoulder_Right.setTextureSize(256, 128);
        Shoulder_Right.mirror = true;
        setRotation(Shoulder_Right, 0F, 0F, 0.2230717F);
        Shoulder_Left = new ModelRenderer(this, 171, 0);
        Shoulder_Left.addBox(-2F, -3F, -3F, 5, 6, 7);
        Shoulder_Left.setRotationPoint(10F, -16F, 2F);
        Shoulder_Left.setTextureSize(256, 128);
        Shoulder_Left.mirror = true;
        setRotation(Shoulder_Left, 0F, 0F, -0.2230705F);
        Arm_Right_1 = new ModelRenderer(this, 145, 14);
        Arm_Right_1.addBox(-2.5F, 2F, -1F, 4, 10, 4);
        Arm_Right_1.setRotationPoint(-10F, -16F, 2F);
        Arm_Right_1.setTextureSize(256, 128);
        Arm_Right_1.mirror = true;
        setRotation(Arm_Right_1, 0F, 0F, 0.1858931F);
        Arm_Left_1 = new ModelRenderer(this, 163, 14);
        Arm_Left_1.addBox(-1.5F, 2F, -1F, 4, 10, 4);
        Arm_Left_1.setRotationPoint(10F, -16F, 2F);
        Arm_Left_1.setTextureSize(256, 128);
        Arm_Left_1.mirror = true;
        setRotation(Arm_Left_1, 0F, 0F, -0.185895F);
        Arm_Right_2 = new ModelRenderer(this, 145, 29);
        Arm_Right_2.addBox(-5.5F, 11F, -1.5F, 5, 12, 5);
        Arm_Right_2.setRotationPoint(-10F, -16F, 2F);
        Arm_Right_2.setTextureSize(256, 128);
        Arm_Right_2.mirror = true;
        setRotation(Arm_Right_2, 0F, 0F, 0F);
        Arm_Left_2 = new ModelRenderer(this, 167, 29);
        Arm_Left_2.addBox(0.5F, 11F, -1.5F, 5, 12, 5);
        Arm_Left_2.setRotationPoint(10F, -16F, 2F);
        Arm_Left_2.setTextureSize(256, 128);
        Arm_Left_2.mirror = true;
        setRotation(Arm_Left_2, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Torso_Top.render(f5);
        Shoulder_Blade_Left_1.render(f5);
        Shoulder_Blade_Right_1.render(f5);
        Shoulder_Blade_Left_2.render(f5);
        Shoulder_Blade_Right_2.render(f5);
        Spine_1.render(f5);
        Spine_2.render(f5);
        Vertebra_1.render(f5);
        Vertebra_2.render(f5);
        Vertebra_3.render(f5);
        Vertebra_4.render(f5);
        Vertebra_5.render(f5);
        Vertebra_6.render(f5);
        Rib_1.render(f5);
        Rib_2.render(f5);
        Rib_3.render(f5);
        Rib_4.render(f5);
        Chestbone.render(f5);
        Detail_1.render(f5);
        Detail_2.render(f5);
        Detail_3.render(f5);
        Head_1.render(f5);
        Head_2.render(f5);
        Head_3.render(f5);
        Jaw.render(f5);
        Shoulder_Right.render(f5);
        Shoulder_Left.render(f5);
        Arm_Right_1.render(f5);
        Arm_Left_1.render(f5);
        Arm_Right_2.render(f5);
        Arm_Left_2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        ///////////////////////////////////ARMS//////////////////////////////////////////
        Arm_Right_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
        Arm_Left_2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
    }

}
