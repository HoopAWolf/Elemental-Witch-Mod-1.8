package com.hoopawolf.mwaw.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEarthWitchSpawner extends BlockContainer {

    public BlockEarthWitchSpawner() {

        super(Material.wood);

        setBlockName("earthwitchspawner");
        setBlockTextureName("planks_oak");
        setBlockUnbreakable();
        setStepSound(soundTypeWood);

    }

    public TileEntity createNewTileEntity(World world, int i) {

        return new TileEntityEarthWitchSpawner();

    }
}

