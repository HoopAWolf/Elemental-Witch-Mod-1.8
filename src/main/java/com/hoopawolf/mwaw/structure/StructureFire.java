package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class StructureFire extends MWAWSymmetricalStructure {

    private static final int STRUCTURE_HEIGHT = 15;
    private static final int MAX_BUILD_HEIGHT = 90 - STRUCTURE_HEIGHT;
    private static final int MIN_BUILD_HEIGHT = 20;

    private static StructureFire _instance = null;

    public StructureFire() {
        super(6, 6);
    }

    /**
     * Get the unique instance.
     *
     * @return Unique instance.
     */
    public static StructureFire getInstance() {
        if (_instance == null)
            _instance = new StructureFire();

        return _instance;
    }

    private int findGround(World world, int x, int z) {
        int height = MAX_BUILD_HEIGHT;

        while (height >= MIN_BUILD_HEIGHT && world.isAirBlock(new BlockPos(x, height, z))) {
            height--;
        }

        if (!world.isAirBlock(new BlockPos(x, height, z)))
            return height;

        return -1;
    }

    private boolean canPlaceStructure(World world, int x, int y, int z) {
        for (int xr = x - 2; xr < x + 2; xr++) {
            for (int zr = z - 2; zr < z + 2; zr++) {
                if (!world.isAirBlock(new BlockPos(xr, y, zr)))
                    return true;
            }

        }

        return true;
    }


    @Override
    protected void generateLayers() {

        BlockWrapper b = new BlockWrapper(Blocks.nether_brick);
        BlockWrapper o = new BlockWrapper(Blocks.obsidian);
        BlockWrapper l = new BlockWrapper(Blocks.flowing_lava);
        BlockWrapper f = new BlockWrapper(Blocks.nether_brick_fence);
        BlockWrapper nn = new BlockWrapper(Blocks.stone_slab, MWAWBlockHandler.getSlabMetadata(MWAWBlockHandler.SlabType.NETHER_BRICK, false));
        BlockWrapper nu = new BlockWrapper(Blocks.stone_slab, MWAWBlockHandler.getSlabMetadata(MWAWBlockHandler.SlabType.NETHER_BRICK, true));
        BlockWrapper O = new BlockWrapper(Blocks.air);

        BlockWrapper ta = new BlockWrapper(MWAWBlockRegistry.firewitchspawner);

        addLayer(1, new BlockWrapper[]{b, b, o, b, o, o,
                o, o, o, o, o, o,
                b, b, b, b, o, b,
                b, b, b, b, o, o,
                b, o, b, b, o, b,
                O, b, b, b, o, b});

        addLayer(2, new BlockWrapper[]{O, O, o, b, b, o,
                O, O, O, O, O, b,
                O, O, O, O, O, b,
                O, O, O, O, O, o,
                O, o, O, O, O, O,
                ta, O, O, O, O, O});

        addLayer(3, new BlockWrapper[]{O, O, o, f, f, o,
                O, O, O, O, O, f,
                O, O, O, O, O, f,
                O, O, O, O, O, o,
                O, f, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(4, new BlockWrapper[]{O, O, o, f, f, o,
                O, O, O, O, O, f,
                O, O, O, O, O, f,
                O, O, O, O, O, o,
                nu, o, O, O, O, O,
                O, nu, O, O, O, O});

        addLayer(5, new BlockWrapper[]{O, O, o, f, f, o,
                O, O, O, O, O, b,
                O, O, O, O, O, b,
                O, O, O, O, O, o,
                nu, b, O, O, O, O,
                O, nu, O, O, O, O});

        addLayer(6, new BlockWrapper[]{o, o, b, b, o, o,
                O, O, O, o, o, o,
                O, O, O, O, o, b,
                O, O, O, O, O, b,
                O, O, O, O, O, o,
                O, O, O, O, O, o});

        addLayer(7, new BlockWrapper[]{O, O, b, b, b, o,
                O, O, O, O, O, b,
                O, O, o, o, O, b,
                O, O, O, o, O, b,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(8, new BlockWrapper[]{O, O, O, b, b, o,
                O, O, O, O, O, b,
                O, O, O, O, O, b,
                b, o, o, O, O, O,
                b, b, o, O, O, O,
                O, b, b, O, O, O});

        addLayer(9, new BlockWrapper[]{O, O, O, O, b, o,
                O, O, O, O, O, b,
                O, O, O, O, O, O,
                b, nu, o, O, O, O,
                O, O, nu, O, O, O,
                O, O, b, O, O, O});

        addLayer(10, new BlockWrapper[]{O, O, O, O, O, nn,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, o, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(11, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, o, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(12, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                o, o, nn, O, O, O,
                O, b, o, O, O, O,
                O, O, o, O, O, O});

        addLayer(13, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                nn, O, O, O, O, O,
                o, nn, O, O, O, O,
                O, o, nn, O, O, O});

        addLayer(14, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                nn, O, O, O, O, O,
                l, nn, O, O, O, O});


		/*
                O ,O ,O ,O ,O ,O ,
				O ,O ,O ,O ,O ,O ,
				O ,O ,O ,O ,O ,O ,
				O ,O ,O ,O ,O ,O ,
				O ,O ,O ,O ,O ,O ,
				O ,O ,O ,O ,O ,O
		 */
    }


    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int groundHeight = findGround(world, x, z);

        if (groundHeight == -1)
            return false;

        // Check if it is a valid spot
        if (!canPlaceStructure(world, x, groundHeight + 1, z))
            return true;

        // It's a valid spot, now spawn it
        super.generate(world, random, x, groundHeight, z);
        System.out.println("X= " + x + " Y= " + groundHeight + " Z= " + z);

        return true;
    }

}