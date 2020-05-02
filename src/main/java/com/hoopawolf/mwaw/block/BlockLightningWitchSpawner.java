package com.hoopawolf.mwaw.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLightningWitchSpawner extends BlockContainer {

    public BlockLightningWitchSpawner() {

        super(Material.wood);

        setBlockName("lightningwitchspawner");
        setBlockTextureName("planks_oak");
        setBlockUnbreakable();
        setStepSound(soundTypeWood);

    }

    public TileEntity createNewTileEntity(World world, int i) {

        return new TileEntityLightningWitchSpawner();

    }
}
