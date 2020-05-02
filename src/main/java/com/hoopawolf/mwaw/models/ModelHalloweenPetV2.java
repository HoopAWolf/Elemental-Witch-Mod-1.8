package com.hoopawolf.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelHalloweenPetV2 extends ModelBase {
    ModelRenderer torso1;
    ModelRenderer torso2;
    ModelRenderer spine1;
    ModelRenderer spine2;
    ModelRenderer spine3;
    ModelRenderer clothes;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer teeth;
    ModelRenderer jaw;
    ModelRenderer armright1;
    ModelRenderer armright2;
    ModelRenderer armleft1;
    ModelRenderer armleft2;
    ModelRenderer legright1;
    ModelRenderer legleft1;
    ModelRenderer legright2;
    ModelRenderer legleft2;

    public ModelHalloweenPetV2() {
        textureWidth = 128;
        textureHeight = 64;

        torso1 = new ModelRenderer(this, 0, 0);
        torso1.addBox(-5F, 0F, 0F, 10, 8, 6);
        torso1.setRotationPoint(0F, -9F, -3F);
        torso1.setTextureSize(128, 64);
        torso1.mirror = true;
        setRotation(torso1, -0.0743572F, 0F, 0F);
        torso2 = new ModelRenderer(this, 0, 15);
        torso2.addBox(-4F, 8.4F, 0.4F, 8, 8, 5);
        torso2.setRotationPoint(0F, -9F, -3F);
        torso2.setTextureSize(128, 64);
        torso2.mirror = true;
        setRotation(torso2, 0F, 0F, 0F);
        spine1 = new ModelRenderer(this, 0, 29);
        spine1.addBox(-1F, 8.4F, 4.4F, 2, 8, 2);
        spine1.setRotationPoint(0F, -9F, -3F);
        spine1.setTextureSize(128, 64);
        spine1.mirror = true;
        setRotation(spine1, 0F, 0F, 0F);
        spine2 = new ModelRenderer(this, 9, 29);
        spine2.addBox(-1F, 0F, 5F, 2, 8, 2);
        spine2.setRotationPoint(0F, -9F, -3F);
        spine2.setTextureSize(128, 64);
        spine2.mirror = true;
        setRotation(spine2, -0.074351F, 0F, 0F);
        spine3 = new ModelRenderer(this, 18, 29);
        spine3.addBox(-1F, -2F, 4.7F, 2, 4, 2);
        spine3.setRotationPoint(0F, -9F, -3F);
        spine3.setTextureSize(128, 64);
        spine3.mirror = true;
        setRotation(spine3, 0.2230717F, 0F, 0F);
        clothes = new ModelRenderer(this, 0, 40);
        clothes.addBox(-4F, 16.4F, 0.4F, 8, 4, 5);
        clothes.setRotationPoint(0F, -9F, -3F);
        clothes.setTextureSize(128, 64);
        clothes.mirror = true;
        setRotation(clothes, 0F, 0F, 0F);
        head1 = new ModelRenderer(this, 40, 0);
        head1.addBox(-4F, -7.466667F, -6F, 8, 5, 8);
        head1.setRotationPoint(0F, -12F, 2F);
        head1.setTextureSize(128, 64);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        head2 = new ModelRenderer(this, 40, 14);
        head2.addBox(-3.5F, -2.5F, -2F, 7, 3, 4);
        head2.setRotationPoint(0F, -12F, 2F);
        head2.setTextureSize(128, 64);
        head2.mirror = true;
        setRotation(head2, 0F, 0F, 0F);
        teeth = new ModelRenderer(this, 40, 22);
        teeth.addBox(-3F, -2.5F, -6F, 6, 1, 4);
        teeth.setRotationPoint(0F, -12F, 2F);
        teeth.setTextureSize(128, 64);
        teeth.mirror = true;
        setRotation(teeth, 0F, 0F, 0F);
        jaw = new ModelRenderer(this, 40, 28);
        jaw.addBox(-3F, -1.5F, -6F, 6, 2, 5);
        jaw.setRotationPoint(0F, -12F, 2F);
        jaw.setTextureSize(128, 64);
        jaw.mirror = true;
        setRotation(jaw, 0.1495997F, 0F, 0F);
        armright1 = new ModelRenderer(this, 80, 0);
        armright1.addBox(-1.5F, 0F, -1F, 2, 8, 2);
        armright1.setRotationPoint(-5F, -8F, 1F);
        armright1.setTextureSize(128, 64);
        armright1.mirror = true;
        setRotation(armright1, 0.1121997F, 0F, 0.2991993F);
        armright2 = new ModelRenderer(this, 89, 0);
        armright2.addBox(-1.8F, 0F, -1.2F, 2, 8, 2);
        armright2.setRotationPoint(-7F, -1F, 2F);
        armright2.setTextureSize(128, 64);
        armright2.mirror = true;
        setRotation(armright2, 0F, 0F, 0F);
        armleft1 = new ModelRenderer(this, 80, 11);
        armleft1.addBox(-0.5F, 0F, -1F, 2, 8, 2);
        armleft1.setRotationPoint(5F, -8F, 1F);
        armleft1.setTextureSize(128, 64);
        armleft1.mirror = true;
        setRotation(armleft1, 0.1121997F, 0F, -0.2992018F);
        armleft2 = new ModelRenderer(this, 89, 11);
        armleft2.addBox(-0.2F, 0F, -1.2F, 2, 8, 2);
        armleft2.setRotationPoint(7F, -1F, 2F);
        armleft2.setTextureSize(128, 64);
        armleft2.mirror = true;
        setRotation(armleft2, 0F, 0F, 0F);
        legright1 = new ModelRenderer(this, 80, 22);
        legright1.addBox(-1F, 0F, -1F, 2, 8, 2);
        legright1.setRotationPoint(-2.5F, 7F, 0F);
        legright1.setTextureSize(128, 64);
        legright1.mirror = true;
        setRotation(legright1, -0.0373999F, 0.0947464F, 0.0373999F);
        legleft1 = new ModelRenderer(this, 89, 22);
        legleft1.addBox(-1F, 0F, -1F, 2, 8, 2);
        legleft1.setRotationPoint(2.5F, 7F, 0F);
        legleft1.setTextureSize(128, 64);
        legleft1.mirror = true;
        setRotation(legleft1, -0.0374024F, -0.1296605F, -0.0374024F);
        legright2 = new ModelRenderer(this, 80, 33);
        legright2.addBox(-1.3F, 1F, -1.3F, 2, 9, 2);
        legright2.setRotationPoint(-2.5F, 14F, 0F);
        legright2.setTextureSize(128, 64);
        legright2.mirror = true;
        setRotation(legright2, 0F, 0F, 0F);
        legleft2 = new ModelRenderer(this, 89, 33);
        legleft2.addBox(-0.7F, 1F, -1.3F, 2, 9, 2);
        legleft2.setRotationPoint(2.5F, 14F, 0F);
        legleft2.setTextureSize(128, 64);
        legleft2.mirror = true;
        setRotation(legleft2, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        torso1.render(f5);
        torso2.render(f5);
        spine1.render(f5);
        spine2.render(f5);
        spine3.render(f5);
        clothes.render(f5);
        head1.render(f5);
        head2.render(f5);
        teeth.render(f5);
        jaw.render(f5);
        armright1.render(f5);
        armright2.render(f5);
        armleft1.render(f5);
        armleft2.render(f5);
        legright1.render(f5);
        legleft1.render(f5);
        legright2.render(f5);
        legleft2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        /////////////////////HEAD////////////////////////
        head1.rotateAngleY = f3 / (180F / (float) Math.PI);
        head1.rotateAngleX = f4 / (180F / (float) Math.PI);

        head2.rotateAngleY = head1.rotateAngleY;
        head2.rotateAngleX = head1.rotateAngleX;

        teeth.rotateAngleY = head1.rotateAngleY;
        teeth.rotateAngleX = head1.rotateAngleX;

        jaw.rotateAngleY = head1.rotateAngleY;
        jaw.rotateAngleX = head1.rotateAngleX;

        /////////////////////HAND////////////////////////
        armright2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
        armleft2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;

        /////////////////////LEG////////////////////////
        legright2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        legleft2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
    }

}

