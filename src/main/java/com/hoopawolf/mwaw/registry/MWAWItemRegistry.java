package com.hoopawolf.mwaw.registry;


import com.hoopawolf.mwaw.item.*;
import com.hoopawolf.mwaw.lib.RegistryHelper;
import net.minecraft.item.Item;

public abstract class MWAWItemRegistry {

    public static Item itemnull;

    public static Item itemlightning;
    public static Item fireshard;
    public static Item lightningshard;
    public static Item lightshard;
    public static Item airshard;
    public static Item earthshard;
    public static Item darkshard;
    public static Item watershard;
    public static Item sandshard;

    public static Item firestaff;
    public static Item windstaff;
    public static Item earthstaff;
    public static Item lightningstaff;
    public static Item lightstaff;
    public static Item darkstaff;
    public static Item waterstaff;
    public static Item halloweenstaff;
    public static Item christmasstaff;

    public static Item emptyegg;
    public static Item airegg;
    public static Item lightningegg;
    public static Item earthegg;
    public static Item wateregg;
    public static Item fireegg;
    public static Item lightegg;
    public static Item darkegg;

    public static void createItems() {

        //null
        itemnull = new ItemNull();
        RegistryHelper.registerItem(itemnull);


        //itemlightning
        itemlightning = new ItemLightningBall();
        RegistryHelper.registerItem(itemlightning);

        //fireshard
        fireshard = new ItemFireShard();
        RegistryHelper.registerItem(fireshard);

        //lightningshard
        lightningshard = new ItemLightningShard();
        RegistryHelper.registerItem(lightningshard);

        //airshard
        airshard = new ItemAirShard();
        RegistryHelper.registerItem(airshard);

        //earthshard
        earthshard = new ItemEarthShard();
        RegistryHelper.registerItem(earthshard);

        //darkshard
        darkshard = new ItemDarkShard();
        RegistryHelper.registerItem(darkshard);

        //lightshard
        lightshard = new ItemLightShard();
        RegistryHelper.registerItem(lightshard);

        //watershard
        watershard = new ItemWaterShard();
        RegistryHelper.registerItem(watershard);

        //sandshard
        sandshard = new ItemSandShard();
        RegistryHelper.registerItem(sandshard);


        //firestaff
        firestaff = new ItemFireStaff();
        RegistryHelper.registerItem(firestaff);

        //windstaff
        windstaff = new ItemWindStaff();
        RegistryHelper.registerItem(windstaff);

        //earthstaff
        earthstaff = new ItemEarthStaff();
        RegistryHelper.registerItem(earthstaff);

        //lightningstaff
        lightningstaff = new ItemLightningStaff();
        RegistryHelper.registerItem(lightningstaff);

        //halloweenstaff
        halloweenstaff = new ItemHalloweenStaff();
        RegistryHelper.registerItem(halloweenstaff);

        //lightstaff
        lightstaff = new ItemLightStaff();
        RegistryHelper.registerItem(lightstaff);

        //darkstaff
        darkstaff = new ItemDarkStaff();
        RegistryHelper.registerItem(darkstaff);

        //waterstaff
        waterstaff = new ItemWaterStaff();
        RegistryHelper.registerItem(waterstaff);

        //christmasstaff
        christmasstaff = new ItemChristmasStaff();
        RegistryHelper.registerItem(christmasstaff);


        //empty egg
        emptyegg = new ItemMWAWEgg();
        RegistryHelper.registerItem(emptyegg);

        //air egg
        airegg = new ItemAirEgg();
        RegistryHelper.registerItem(airegg);

        //lightning egg
        lightningegg = new ItemLightningEgg();
        RegistryHelper.registerItem(lightningegg);

        //fire egg
        fireegg = new ItemFireEgg();
        RegistryHelper.registerItem(fireegg);

        //earth egg
        earthegg = new ItemEarthEgg();
        RegistryHelper.registerItem(earthegg);

        //water egg
        wateregg = new ItemWaterEgg();
        RegistryHelper.registerItem(wateregg);

        //dark egg
        darkegg = new ItemDarkEgg();
        RegistryHelper.registerItem(darkegg);

        //light egg
        lightegg = new ItemLightEgg();
        RegistryHelper.registerItem(lightegg);

    }
}