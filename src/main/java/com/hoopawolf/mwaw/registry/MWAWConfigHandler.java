package com.hoopawolf.mwaw.registry;

import com.hoopawolf.mwaw.lib.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class MWAWConfigHandler {

    public static Configuration configuration;

    ///Add Witch///
    public static boolean AddAirWitch = true;
    public static boolean AddEarthWitch = true;
    public static boolean AddFireWitch = true;
    public static boolean AddWaterWitch = true;
    public static boolean AddLightningWitch = true;
    public static boolean AddLightWitch = true;
    public static boolean AddDarkWitch = true;
    public static boolean AddHalloweenWitch = true;
    public static boolean AddChristmasWitch = true;

    ////Effects////
    public static int PotionIDParalyze = 36;
    public static int PotionIDEnderSkin = 38;
    public static int PotionIDParanoid = 37;
    public static int PotionIDEarthStance = 39;
    public static int PotionIDAirStance = 40;

    ////Passive Moves////
    public static boolean DarkWitchTurnNight = true;
    public static boolean LightWitchTurnDay = true;
    public static boolean DarkWitchDestroyLight = true;

    ////Structures////
    public static boolean SpawnFireTemple = true;
    public static boolean SpawnLightningTemple = true;
    public static boolean SpawnWaterTemple = true;
    public static boolean SpawnAirTemple = true;
    public static boolean SpawnEarthTemple = true;
    public static boolean SpawnLightTemple = true;
    public static boolean SpawnDarkTemple = true;
    public static int StructureSpawnDistance;

    ////Other Stuff////
    public static boolean DarkWitchVSLightWitch = true;
    public static boolean FireWitchVSWaterWitch = true;
    public static boolean ElementalsVSWitches = true;

    public static void init(File configFile) {
        // Create the configuration object from the given configuration file
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        AddAirWitch = configuration.getBoolean("Add Air Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddEarthWitch = configuration.getBoolean("Add Earth Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddFireWitch = configuration.getBoolean("Add Fire Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddWaterWitch = configuration.getBoolean("Add Water Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddLightningWitch = configuration.getBoolean("Add Lightning Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddLightWitch = configuration.getBoolean("Add Light Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddDarkWitch = configuration.getBoolean("Add Dark Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddHalloweenWitch = configuration.getBoolean("Add Halloween Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        AddChristmasWitch = configuration.getBoolean("Add Christmas Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");

        PotionIDParalyze = configuration.getInt("Potion ID Paralyze", Configuration.CATEGORY_GENERAL, 36, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 36");
        PotionIDParanoid = configuration.getInt("Potion ID Paranoid", Configuration.CATEGORY_GENERAL, 37, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 37");
        PotionIDEnderSkin = configuration.getInt("Potion ID Ender Skin", Configuration.CATEGORY_GENERAL, 38, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 38");
        PotionIDEarthStance = configuration.getInt("Potion ID Earth Stance", Configuration.CATEGORY_GENERAL, 39, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 39");
        PotionIDAirStance = configuration.getInt("Potion ID Air Stance", Configuration.CATEGORY_GENERAL, 40, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 40");

        DarkWitchDestroyLight = configuration.getBoolean("Dark Witch Destroy Light Source", Configuration.CATEGORY_GENERAL, true, "Default: True");
        DarkWitchTurnNight = configuration.getBoolean("Dark Witch Turn Night", Configuration.CATEGORY_GENERAL, true, "Default: True");
        LightWitchTurnDay = configuration.getBoolean("Light Witch Turn Day", Configuration.CATEGORY_GENERAL, true, "Default: True");

        SpawnFireTemple = configuration.getBoolean("Spawn Fire Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnLightningTemple = configuration.getBoolean("Spawn Lightning Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnWaterTemple = configuration.getBoolean("Spawn Water Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnAirTemple = configuration.getBoolean("Spawn Air Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnEarthTemple = configuration.getBoolean("Spawn Earth Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnLightTemple = configuration.getBoolean("Spawn Light Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        SpawnDarkTemple = configuration.getBoolean("Spawn Dark Temple", Configuration.CATEGORY_GENERAL, true, "Default: True");
        StructureSpawnDistance = configuration.getInt("How far the structure spawn from each other", Configuration.CATEGORY_GENERAL, 10, 0, Integer.MAX_VALUE, "Min: 0, Max: ∞ Default: 10");

        DarkWitchVSLightWitch = configuration.getBoolean("Dark Witch VS Light Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        FireWitchVSWaterWitch = configuration.getBoolean("Fire Witch VS Water Witch", Configuration.CATEGORY_GENERAL, true, "Default: True");
        ElementalsVSWitches = configuration.getBoolean("Elementals VS Witches", Configuration.CATEGORY_GENERAL, true, "Default: True");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}
