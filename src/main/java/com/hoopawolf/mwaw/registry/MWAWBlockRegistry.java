package com.hoopawolf.mwaw.registry;

import com.hoopawolf.mwaw.block.*;
import com.hoopawolf.mwaw.lib.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class MWAWBlockRegistry {

    public static Block firewitchspawner;
    public static Block lightningwitchspawner;
    public static Block airwitchspawner;
    public static Block earthwitchspawner;
    public static Block waterwitchspawner;
    public static Block darkwitchspawner;
    public static Block lightwitchspawner;

    public static Block present;


    public static void createBlocks() {

        //fire witch spawner
        firewitchspawner = new BlockFireWitchSpawner();
        RegistryHelper.registerBlock(firewitchspawner);

        //lightning witch spawner
        lightningwitchspawner = new BlockLightningWitchSpawner();
        RegistryHelper.registerBlock(lightningwitchspawner);

        //air witch spawner
        airwitchspawner = new BlockAirWitchSpawner();
        RegistryHelper.registerBlock(airwitchspawner);

        //earth witch spawner
        earthwitchspawner = new BlockEarthWitchSpawner();
        RegistryHelper.registerBlock(earthwitchspawner);

        //water witch spawner
        waterwitchspawner = new BlockWaterWitchSpawner();
        RegistryHelper.registerBlock(waterwitchspawner);

        //dark witch spawner
        darkwitchspawner = new BlockDarkWitchSpawner();
        RegistryHelper.registerBlock(darkwitchspawner);

        //light witch spawner
        lightwitchspawner = new BlockLightWitchSpawner();
        RegistryHelper.registerBlock(lightwitchspawner);

        //present
        present = new BlockPresent();
        RegistryHelper.registerBlock(present);


    }

    public static void registerTileEntity() {

        GameRegistry.registerTileEntity(TileEntityFireWitchSpawner.class, "MWAWFireWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityLightningWitchSpawner.class, "MWAWLightningWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityAirWitchSpawner.class, "MWAWAirWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityEarthWitchSpawner.class, "MWAWEarthWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityWaterWitchSpawner.class, "MWAWWaterWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityDarkWitchSpawner.class, "MWAWDarkWitchSpawner");
        GameRegistry.registerTileEntity(TileEntityLightWitchSpawner.class, "MWAWLightWitchSpawner");

    }

}
