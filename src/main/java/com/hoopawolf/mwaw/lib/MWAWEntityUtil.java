package com.hoopawolf.mwaw.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class MWAWEntityUtil {

    public static void spawnInWorld(World world, Entity entity) {
        if (!world.isRemote) {
            world.spawnEntityInWorld(entity);
        }
    }

    public static void spawnWeather(World world, Entity entity) {
        if (!world.isRemote) {
            world.addWeatherEffect(entity);
        }
    }

}
