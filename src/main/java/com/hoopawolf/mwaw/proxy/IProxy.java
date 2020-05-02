package com.hoopawolf.mwaw.proxy;


import net.minecraft.entity.Entity;

public interface IProxy {

    public void registerRenderThings();

    public int addArmor(String armor);

    public void spawnParticles(String particles, Entity entity);

    public void registerEvents();

}
