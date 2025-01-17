package com.hoopawolf.mwaw;

import com.hoopawolf.mwaw.entity.*;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.projectile.*;
import com.hoopawolf.mwaw.proxy.IProxy;
import com.hoopawolf.mwaw.registry.*;
import com.hoopawolf.mwaw.skills.*;
import com.hoopawolf.mwaw.structure.MWAWStructureHandler;
import com.hoopawolf.mwaw.tab.MWAWTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;
import java.util.Calendar;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MoWitchAndWizard {

    @Mod.Instance(Reference.MOD_ID)
    public static MoWitchAndWizard instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    ////////////////// CREATIVE TAB/////////////////////
    public static CreativeTabs tabMoWitchAndWizard = new MWAWTab(
            CreativeTabs.getNextID(), "tabMoWitchAndWizard");

    public boolean isHalloween, isChristmas = false;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {

        MWAWConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new MWAWConfigHandler());

        final ModMetadata metadata = event.getModMetadata();
        metadata.autogenerated = false;
        metadata.authorList = Arrays.asList("HoopAWolf");
        metadata.description = "Elemental Witch Mod, for an adventurous world!";

        MWAWPotionRegistry.potionCheck();
        MWAWItemRegistry.createItems();
        MWAWBlockRegistry.createBlocks();

    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {

        Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 8 && calendar.get(5) >= 30 && calendar.get(5) <= 31)
            isHalloween = true;


        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
            isChristmas = true;


        proxy.registerRenderThings();
        MWAWRecipe.addRecipes();
        MWAWBlockRegistry.registerTileEntity();
        MWAWPotionRegistry.registerPotions();
        GameRegistry.registerWorldGenerator(new MWAWStructureHandler(), 0);

        int id = 0;

        // air egg
        EntityRegistry.registerModEntity(EntityAirEgg.class, "airegg",
                id++, this, 80, 3, true);

        // earth egg
        EntityRegistry.registerModEntity(EntityEarthEgg.class, "earthegg",
                id++, this, 80, 3, true);

        // water egg
        EntityRegistry.registerModEntity(EntityWaterEgg.class, "wateregg",
                id++, this, 80, 3, true);

        // fire egg
        EntityRegistry.registerModEntity(EntityFireEgg.class, "fireegg",
                id++, this, 80, 3, true);

        // lightning egg
        EntityRegistry.registerModEntity(EntityLightningEgg.class, "lightningegg",
                id++, this, 80, 3, true);

        // halloween egg
        EntityRegistry.registerModEntity(EntityHalloweenEgg.class, "halloweenegg",
                id++, this, 80, 3, true);

        // dark egg
        EntityRegistry.registerModEntity(EntityDarkEgg.class, "darkegg",
                id++, this, 80, 3, true);

        // light egg
        EntityRegistry.registerModEntity(EntityLightEgg.class, "lightegg",
                id++, this, 80, 3, true);

        // lightning bolt
        EntityRegistry.registerModEntity(EntityLightningShoot.class, "lightningbolt",
                id++, this, 64, 10, true);

        // squid ink bomb
        EntityRegistry.registerModEntity(EntitySquidInkBomb.class, "squidinkbomb",
                id++, this, 64, 10, true);

        // water shoot
        EntityRegistry.registerModEntity(EntityWaterShoot.class, "watershoot",
                id++, this, 64, 10, true);

        // light ball
        EntityRegistry.registerModEntity(EntityLightShoot.class, "lightball",
                id++, this, 64, 10, true);

        // light arrow
        EntityRegistry.registerModEntity(EntityLightArrow.class, "lightarrow",
                id++, this, 64, 10, true);

        // dark ball
        EntityRegistry.registerModEntity(EntityDarkShoot.class, "darkball",
                id++, this, 64, 10, true);

        // pumpkin skull
        EntityRegistry.registerModEntity(EntityPumpkinSkull.class, "pumpkinskull",
                id++, this, 64, 10, true);

        // christmas shoot
        EntityRegistry.registerModEntity(EntityChristmasShoot.class, "christmasshoot",
                id++, this, 64, 10, true);

        // lightning ball
        EntityRegistry.registerModEntity(EntityLightningBall.class, "lightningball",
                id++, this, 64, 10, true);

        // wind ball
        EntityRegistry.registerModEntity(EntityWindShoot.class, "windball",
                id++, this, 64, 10, true);

        // earth ball
        EntityRegistry.registerModEntity(EntityEarthShoot.class, "earthball",
                id++, this, 64, 10, true);

        // fire ball
        EntityRegistry.registerModEntity(EntityMWAWCheckFire.class, "mwawfireball",
                id++, this, 64, 10, true);

        // light ball
        EntityRegistry.registerModEntity(EntityMWAWCheckLight.class, "mwawlightball",
                id++, this, 64, 10, true);

        // dark ball
        EntityRegistry.registerModEntity(EntityMWAWCheckDark.class, "mwawdarkball",
                id++, this, 64, 10, true);

        // pumpkin ball
        EntityRegistry.registerModEntity(EntityMWAWCheckPumpkin.class, "mwawpumpkin",
                id++, this, 64, 10, true);

        // wind ball
        EntityRegistry.registerModEntity(EntityMWAWCheckWind.class, "mwawwindball",
                id++, this, 64, 10, true);

        // wind ball
        EntityRegistry.registerModEntity(EntityMWAWCheckWater.class, "mwawwaterball",
                id++, this, 64, 10, true);

        // fire witch
        if (MWAWConfigHandler.AddFireWitch) {
            EntityRegistry.registerModEntity(EntityFireWitch.class, "firewitch",
                    id++, this, 80, 3, true);
        }

        // fire bat
        EntityRegistry.registerModEntity(EntityFireBat.class, "firebat",
                id++, this, 80, 3, true);

        // meteorite
        EntityRegistry.registerModEntity(EntityMeteorite.class, "firemeteorite",
                id++, this, 80, 3, true);

        // fire pet
        EntityRegistry.registerModEntity(EntityFirePet.class, "firepet",
                id++, this, 80, 3, true);

        // fire pet stage 2
        EntityRegistry.registerModEntity(EntityFirePet2.class, "firepet2",
                id++, this, 80, 3, true);

        // fire pet stage 3
        EntityRegistry.registerModEntity(EntityFirePet3.class, "firepet3",
                id++, this, 80, 3, true);

        // lightning witch
        if (MWAWConfigHandler.AddLightningWitch) {
            EntityRegistry.registerModEntity(EntityLightningWitch.class, "lightningwitch",
                    id++, this, 80, 3, true);
        }

        // lightning golem
        EntityRegistry.registerModEntity(EntityLightningGolem.class, "lightninggolem",
                id++, this, 80, 3, true);

        // lightning pet
        EntityRegistry.registerModEntity(EntityLightningPet.class, "lightningpet",
                id++, this, 80, 3, true);

        // lightning pet stage 2
        EntityRegistry.registerModEntity(EntityLightningPet2.class, "lightningpet2",
                id++, this, 80, 3, true);

        // lightning pet stage 3
        EntityRegistry.registerModEntity(EntityLightningPet3.class, "lightningpet3",
                id++, this, 80, 3, true);

        // air witch
        if (MWAWConfigHandler.AddAirWitch) {
            EntityRegistry.registerModEntity(EntityAirWitch.class, "airwitch",
                    id++, this, 80, 3, true);
        }

        // air pet
        EntityRegistry.registerModEntity(EntityAirPhoenix.class, "windphoenix",
                id++, this, 80, 3, true);

        // air pet stage 2
        EntityRegistry.registerModEntity(EntityAirPhoenix2.class, "windphoenix2",
                id++, this, 80, 3, true);

        // air pet stage 2
        EntityRegistry.registerModEntity(EntityAirPhoenix3.class, "windphoenix3",
                id++, this, 80, 3, true);

        // air clone
        EntityRegistry.registerModEntity(EntityAirClone.class, "airclone",
                id++, this, 80, 3, true);

        // tornado
        EntityRegistry.registerModEntity(EntityTornado.class, "tornado",
                id++, this, 80, 3, true);

        // mini tornado
        EntityRegistry.registerModEntity(EntityMiniTornado.class, "minitornado",
                id++, this, 80, 3, true);

        // earth witch
        if (MWAWConfigHandler.AddEarthWitch) {
            EntityRegistry.registerModEntity(EntityEarthWitch.class, "earthwitch",
                    id++, this, 80, 3, true);
        }

        // earth minion
        EntityRegistry.registerModEntity(EntityEarthMinion.class, "earthminion",
                id++, this, 80, 3, true);

        // earth good minion
        EntityRegistry.registerModEntity(EntityEarthMinionGood.class, "earthgoodminion",
                id++, this, 80, 3, true);

        // earth pet
        EntityRegistry.registerModEntity(EntityEarthPet.class, "earthpet",
                id++, this, 80, 3, true);

        // earth pet stage 2
        EntityRegistry.registerModEntity(EntityEarthPet2.class, "earthpet2",
                id++, this, 80, 3, true);

        // earth pet stage 3
        EntityRegistry.registerModEntity(EntityEarthPet3.class, "earthpet3",
                id++, this, 80, 3, true);

        // halloween witch
        if (MWAWConfigHandler.AddHalloweenWitch) {
            EntityRegistry.registerModEntity(EntityHalloweenWitch.class, "halloweenwitch",
                    id++, this, 80, 3, true);

            if (isHalloween) {
                EntityRegistry.addSpawn(EntityHalloweenWitch.class, 1, 1, 1,
                        EnumCreatureType.CREATURE, BiomeGenBase.swampland,
                        BiomeGenBase.roofedForest, BiomeGenBase.desert, BiomeGenBase.desertHills,
                        BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.plains, BiomeGenBase.forestHills,
                        BiomeGenBase.coldTaiga, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus);
            }
        }

        // halloween pet
        EntityRegistry.registerModEntity(EntityHalloweenPet.class, "halloweenpet",
                id++, this, 80, 3, true);

        // halloween pet evolved
        EntityRegistry.registerModEntity(EntityHalloweenPetEvolved.class, "halloweenpetevolved",
                id++, this, 80, 3, true);

        // pumpkin air strike
        EntityRegistry.registerModEntity(EntityPumpkinAirStrike.class, "pumpkinairstrike",
                id++, this, 80, 3, true);

        // light witch
        if (MWAWConfigHandler.AddLightWitch) {
            EntityRegistry.registerModEntity(EntityLightWitch.class, "lightwitch",
                    id++, this, 80, 3, true);
        }

        // light heal
        EntityRegistry.registerModEntity(EntityLightHeal.class, "lightheal",
                id++, this, 80, 3, true);

        // light pet
        EntityRegistry.registerModEntity(EntityLightPet.class, "lightpet",
                id++, this, 80, 3, true);

        // light pet 2
        EntityRegistry.registerModEntity(EntityLightPet2.class, "lightpet2",
                id++, this, 80, 3, true);

        // dark witch
        if (MWAWConfigHandler.AddDarkWitch) {
            EntityRegistry.registerModEntity(EntityDarkWitch.class, "darkwitch",
                    id++, this, 80, 3, true);
        }

        // dark pet
        EntityRegistry.registerModEntity(EntityDarkPet.class, "darkpet",
                id++, this, 80, 3, true);

        // dark pet 2
        EntityRegistry.registerModEntity(EntityDarkPet2.class, "darkpet2",
                id++, this, 80, 3, true);

        // dark pet 3
        EntityRegistry.registerModEntity(EntityDarkPet3.class, "darkpet3",
                id++, this, 80, 3, true);

        // death circle
        EntityRegistry.registerModEntity(EntityDeathCircle.class, "deathcircle",
                id++, this, 80, 3, true);

        // dark summoner
        EntityRegistry.registerModEntity(EntityDarkSummoner.class, "darksummoner",
                id++, this, 80, 3, true);

        // dark mark
        EntityRegistry.registerModEntity(EntityDarkMark.class, "darkmark",
                id++, this, 80, 3, true);

        // ender hole
        EntityRegistry.registerModEntity(EntityEnderHole.class, "enderhole",
                id++, this, 64, 10, true);

        // christmas witch
        if (MWAWConfigHandler.AddChristmasWitch) {
            EntityRegistry.registerModEntity(EntityChristmasWitch.class, "christmaswitch",
                    id++, this, 80, 3, true);

            if (isChristmas) {
                EntityRegistry.addSpawn(EntityChristmasWitch.class, 1, 1, 1,
                        EnumCreatureType.CREATURE, BiomeGenBase.swampland,
                        BiomeGenBase.roofedForest, BiomeGenBase.desert, BiomeGenBase.desertHills,
                        BiomeGenBase.forest, BiomeGenBase.birchForest, BiomeGenBase.plains, BiomeGenBase.forestHills,
                        BiomeGenBase.coldTaiga, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus);
            }
        }

        // sword
        EntityRegistry.registerModEntity(EntitySword.class, "sword",
                id++, this, 80, 3, true);

        // rifle
        EntityRegistry.registerModEntity(EntityRifle.class, "rifle",
                id++, this, 80, 3, true);

        // big snow golem
        EntityRegistry.registerModEntity(EntityBigSnowGolem.class, "bigsnowgolem",
                id++, this, 80, 3, true);

        // water witch
        if (MWAWConfigHandler.AddWaterWitch) {
            EntityRegistry.registerModEntity(EntityWaterWitch.class, "waterwitch",
                    id++, this, 80, 3, true);
        }

        // water squid
        EntityRegistry.registerModEntity(EntityGiantSquid.class, "watersquid",
                id++, this, 80, 3, true);

        // water minion
        EntityRegistry.registerModEntity(EntityWaterMinion.class, "waterminion",
                id++, this, 80, 3, true);

        // water pet
        EntityRegistry.registerModEntity(EntityWaterPet.class, "waterpet",
                id++, this, 80, 3, true);

        // water spout
        EntityRegistry.registerModEntity(EntityWaterSpout.class, "waterspout",
                id++, this, 80, 3, true);

        //sand witch
        EntityRegistry.registerModEntity(EntitySandWitch.class, "sandwitch",
                id++, this, 80, 3, true);


        ////Spawn Eggs////
        int id1 = 400;

        if (MWAWConfigHandler.AddFireWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityFireWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0xDF0101, 0x8A2908));
        }

        if (MWAWConfigHandler.AddLightningWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityLightningWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0xD0FA58, 0xF7FE2E));
        }

        if (MWAWConfigHandler.AddAirWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityAirWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0xD8D8D8, 0xBDBDBD));
        }

        if (MWAWConfigHandler.AddEarthWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityEarthWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0x2A120A, 0x3B240B));
        }

        if (MWAWConfigHandler.AddWaterWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityWaterWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0x3A01DF, 0x0040FF));
        }

        if (MWAWConfigHandler.AddLightWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityLightWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0xFFFF00, 0xF4FA58));
        }

        if (MWAWConfigHandler.AddDarkWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityDarkWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0x190707, 0x2A0A0A));
        }

        if (MWAWConfigHandler.AddHalloweenWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityHalloweenWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0x190707, 0x3B170B));
        }

        if (MWAWConfigHandler.AddChristmasWitch) {
            id1++;
            EntityList.classToStringMapping.put(id1, EntityChristmasWitch.class);
            EntityList.entityEggs.put(id1, new EntityList.EntityEggInfo(id1, 0x0B6121, 0xDF0101));
        }
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {

        proxy.registerEvents();
        FMLCommonHandler.instance().bus().register(instance);

    }

}

