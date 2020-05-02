package com.hoopawolf.mwaw.particle;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDarkCircleFX extends EntityDarkFX {
    private final double orbitSpeed, portalPosX, portalPosZ;
    private final float portalParticleScale;
    public double size;
    private byte customAge, customMaxAge;
    private double orbitAngle;

    public EntityDarkCircleFX(World world, double x, double y, double z, double motionY, double angle) {
        super(world, x, y, z, 0D, motionY, 0D);
        portalPosX = x;
        portalPosZ = z;
        motionX = motionZ = 0D;
        orbitAngle = 10D * 10D * Math.PI;
        size = angle;
        orbitSpeed = (world.rand.nextDouble() * 0.3217D + 0.1954D) * (rand.nextBoolean() ? 1 : -1);

        this.particleRed = 0F;
        this.particleGreen = 0F;
        this.particleBlue = 0F;

        customMaxAge = (byte) ((particleAge = particleMaxAge = rand.nextInt(15) + 10) * 2);
        particleScale = portalParticleScale = 10.6f;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTickTime, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
        float scale = 1F - ((particleAge + partialTickTime) / particleMaxAge);
        scale *= scale;
        particleScale = portalParticleScale * (1F - scale);
        super.renderParticle(tessellator, partialTickTime, rotX, rotXZ, rotZ, rotYZ, rotXY);
    }

    @Override
    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        posX = portalPosX + Math.cos(orbitAngle) * size;
        posZ = portalPosZ + Math.sin(orbitAngle) * size;

        orbitAngle += orbitSpeed;
        if (++customAge > customMaxAge) setDead();
        else if (customAge >= customMaxAge >> 1) --particleAge;
    }
}

