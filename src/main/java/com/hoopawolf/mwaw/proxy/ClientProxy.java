package com.hoopawolf.mwaw.proxy;


import com.hoopawolf.mwaw.entity.*;
import com.hoopawolf.mwaw.lib.MWAWEventHandler;
import com.hoopawolf.mwaw.models.*;
import com.hoopawolf.mwaw.particle.*;
import com.hoopawolf.mwaw.projectile.*;
import com.hoopawolf.mwaw.skills.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new MWAWEventHandler());
    }

    @Override
    public void registerRenderThings() {


        RenderingRegistry.registerEntityRenderingHandler(EntityFireWitch.class,
                new RenderFireWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityFireBat.class,
                new RenderFireBat(new ModelFireBat(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityMeteorite.class,
                new RenderMeteorite(new ModelCube(), 5.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityFirePet.class,
                new RenderFirePet(new ModelFirePet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityFirePet2.class,
                new RenderFirePet2(new ModelFirePet2(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityFirePet3.class,
                new RenderFirePet3(new ModelFirePet3(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityFireEgg.class,
                new RenderFireEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningWitch.class,
                new RenderLightningWitch());

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningGolem.class,
                new RenderLightningGolem());

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningPet.class,
                new RenderLightningPet(new ModelLightningPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningPet2.class,
                new RenderLightningPet2(new ModelLightningPet2(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningPet3.class,
                new RenderLightningPet3(new ModelLightningPet3(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningEgg.class,
                new RenderLightningEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirWitch.class,
                new RenderAirWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirClone.class,
                new RenderAirWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityTornado.class,
                new RenderTornado(new ModelCube(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirPhoenix.class,
                new RenderWindPhoenix(new ModelWindPhoenix(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirPhoenix2.class,
                new RenderWindPhoenix2(new ModelWindPhoenix2(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirPhoenix3.class,
                new RenderWindPhoenix3(new ModelWindPhoenix3(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityMiniTornado.class,
                new RenderTornado(new ModelCube(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityAirEgg.class,
                new RenderAirEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthMinion.class,
                new RenderEarthMinion(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthMinionGood.class,
                new RenderEarthMinion(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthWitch.class,
                new RenderEarthWitch());

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthPet.class,
                new RenderEarthPet(new ModelEarthPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthPet2.class,
                new RenderEarthPet2(new ModelEarthPet2(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthPet3.class,
                new RenderEarthPet3(new ModelEarthPet3(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEarthEgg.class,
                new RenderEarthEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityHalloweenWitch.class,
                new RenderHalloweenWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityHalloweenEgg.class,
                new RenderHalloweenEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityHalloweenPet.class,
                new RenderHalloweenPet(new ModelHalloweenPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityHalloweenPetEvolved.class,
                new RenderHalloweenPet2(new ModelHalloweenPetV2(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinAirStrike.class,
                new RenderPumpkinAirStrike(new ModelCube(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightWitch.class,
                new RenderLightWitch());

        RenderingRegistry.registerEntityRenderingHandler(EntityLightHeal.class,
                new RenderLightHeal());

        RenderingRegistry.registerEntityRenderingHandler(EntityLightPet.class,
                new RenderLightPet(new ModelLightPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightPet2.class,
                new RenderLightPet2(new ModelLightPet2(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityLightEgg.class,
                new RenderLightEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkWitch.class,
                new RenderDarkWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkEgg.class,
                new RenderDarkEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkPet.class,
                new RenderDarkPet(new ModelDarkPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkPet2.class,
                new RenderDarkPet2());

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkPet3.class,
                new RenderDarkPet3(new ModelDarkPet3(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDeathCircle.class,
                new RenderTornado(new ModelCube(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkSummoner.class,
                new RenderTornado(new ModelCube(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityDarkMark.class,
                new RenderDarkMark(new ModelDarkMark(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityEnderHole.class,
                new RenderEnderHole());

        RenderingRegistry.registerEntityRenderingHandler(EntityChristmasWitch.class,
                new RenderChristmasWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntitySword.class,
                new RenderSword(new ModelSword(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityRifle.class,
                new RenderRifle(new ModelRifle(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityBigSnowGolem.class,
                new RenderBigSnowGolem(new ModelBigSnowGolem(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityIcicle.class,
                new RenderIcicle(new ModelIcicle(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterWitch.class,
                new RenderWaterWitch(new ModelMWAWWitch(0.5F), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityGiantSquid.class,
                new RenderGiantSquid(new ModelSquid(), 0.5F, 10.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterEgg.class,
                new RenderWaterEgg(new ModelEgg(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterMinion.class,
                new RenderWaterMinion(new ModelZombie(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterPet.class,
                new RenderWaterPet(new ModelWaterPet(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterSpout.class,
                new RenderWaterSpout(new ModelCube(), 0.0F));

        RenderingRegistry.registerEntityRenderingHandler(EntitySandWitch.class,
                new RenderSandWitch(new ModelMWAWWitch(0.5F), 0.5F));

        //////////////////////////////////// projectile///////////////////////////////////

        RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class,
                new RenderLightningBall(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityWindShoot.class,
                new RenderWindShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityLightningShoot.class,
                new RenderLightningShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckFire.class,
                new RenderMWAWFireBall(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckWind.class,
                new RenderMWAWWindBall(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinSkull.class,
                new RenderPumpkinSkull());
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckPumpkin.class,
                new RenderMWAWCheckPumpkin(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityLightShoot.class,
                new RenderLightShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkShoot.class,
                new RenderDarkShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityEarthShoot.class,
                new RenderEarthShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityLightArrow.class,
                new RenderLightArrow());
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckDark.class,
                new RenderMWAWCheckDark(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckLight.class,
                new RenderMWAWCheckLight(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityMWAWCheckWater.class,
                new RenderMWAWCheckWater(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityChristmasShoot.class,
                new RenderChristmasShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntityWaterShoot.class,
                new RenderWaterShoot(1));
        RenderingRegistry.registerEntityRenderingHandler(EntitySquidInkBomb.class,
                new RenderSnowball(Items.dye));

    }

    public int addArmor(String armor) {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }

    public void spawnParticles(String particles, Entity entity) {
        World worldObj = entity.worldObj;

        if (particles.equals("air_tornado_small")) {

            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityTonadoFX(worldObj, entity.posX,
                        entity.posY - 0.5,
                        entity.posZ, d, 1.0D));
            }

        }

        if (particles.equals("air_tornado_big")) {

            for (double i = 0; i < 11; i = i + 0.1) {
                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityTonadoFX(worldObj, entity.posX,
                        entity.posY - 2 + i,
                        entity.posZ, d, i));

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityTonadoFX(worldObj, entity.posX,
                        entity.posY - 2 + i,
                        entity.posZ, d, i));
            }
        }

        if (particles.equals("air_normal")) {
            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmallWindFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("christmas_normal")) {
            for (int i = 0; i < 10; i++) {
                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityChristmasParticleGreenFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityChristmasParticleRedFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("dark_normal")) {
            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDarkFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("dark_tornado")) {
            for (int i = 0; i < 4; i++) {

                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDarkCircleSmallFX(worldObj, entity.posX,
                        entity.posY,
                        entity.posZ, d, 1.0D));
            }
        }

        if (particles.equals("dark_circle")) {
            for (double i = 0; i < 11; i = i + 0.1) {
                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDarkCircleFX(worldObj, entity.posX,
                        entity.posY + 0.5,
                        entity.posZ, d, i));
            }
        }

        if (particles.equals("halloween_normal")) {
            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySoulFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("halloween_normal_4")) {
            for (int i = 0; i < 4; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySoulFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("light_normal")) {
            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("lightning_normal")) {
            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, (entity.posX + (double) (entity.worldObj.rand.nextFloat()
                        * entity.width * 2.0F))
                        - (double) entity.width - d * d3,
                        (entity.posY + (double) (entity.worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (entity.worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2));
            }
        }

        if (particles.equals("lightning_shoot")) {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D));
        }

        if (particles.equals("christmas_shoot")) {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityChristmasParticleGreenFX(worldObj, entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D));
        }

        if (particles.equals("dark_shoot")) {
            for (int i = 0; i < 5; ++i) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDarkProjectileFX(worldObj, entity.posX + entity.motionX * (double) i / 4.0D, entity.posY + entity.motionY * (double) i / 4.0D, entity.posZ + entity.motionZ * (double) i / 4.0D, -entity.motionX, -entity.motionY + 0.2D, -entity.motionZ));
            }
        }

        if (particles.equals("light_shoot")) {
            for (int i = 0; i < 5; ++i) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightProjectileFX(worldObj, entity.posX + entity.motionX * (double) i / 4.0D, entity.posY + entity.motionY * (double) i / 4.0D, entity.posZ + entity.motionZ * (double) i / 4.0D, -entity.motionX, -entity.motionY + 0.2D, -entity.motionZ));
            }
        }

        if (particles.equals("lightning_shoot")) {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningFX(worldObj, entity.posX, entity.posY + 0.5D, entity.posZ, 0.0D, 0.0D, 0.0D));
        }

        if (particles.equals("fire_tornado_big")) {

            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFireCircleFX(worldObj, entity.posX,
                        entity.posY + 0.5,
                        entity.posZ, d, 2.5D));
            }
        }

        if (particles.equals("earth_big")) {

            for (int i = 0; i < 1; i++) {

                double d = worldObj.rand.nextGaussian() * 0.02D;
                double d1 = worldObj.rand.nextGaussian() * 0.02D;
                double d2 = worldObj.rand.nextGaussian() * 0.02D;
                double d3 = 10D;

                worldObj.spawnParticle("blockcrack_3_0", (entity.posX + (double) (worldObj.rand.nextFloat()
                                * entity.width * 2.0F))
                                - (double) entity.width - d * d3,
                        (entity.posY + (double) (worldObj.rand.nextFloat() * entity.height)) - d1 * d3,
                        (entity.posZ + (double) (worldObj.rand.nextFloat() * entity.width * 2.0F))
                                - (double) entity.width - d2 * d3, d, d1, d2);
            }
        }

        if (particles.equals("lgihtning_circle_small")) {

            for (int i = 0; i < 10; i++) {

                double d = worldObj.rand.nextGaussian() * 0.004D;
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityLightningCircleFX(worldObj, entity.posX,
                        entity.posY - 0.5,
                        entity.posZ, d, 1.0D));
            }

        }
    }

}
