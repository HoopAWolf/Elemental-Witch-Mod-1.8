package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class StructureLightning extends MWAWSymmetricalStructure {

    private static final int STRUCTURE_HEIGHT = 9;
    private static final int MAX_BUILD_HEIGHT = 90 - STRUCTURE_HEIGHT;
    private static final int MIN_BUILD_HEIGHT = 20;


    private static StructureLightning _instance = null;

    public StructureLightning() {
        super(6, 6);
    }

    /**
     * Get the unique instance.
     *
     * @return Unique instance.
     */
    public static StructureLightning getInstance() {
        if (_instance == null)
            _instance = new StructureLightning();

        return _instance;
    }

    private int findGround(World world, int x, int z) {
        int height = MAX_BUILD_HEIGHT;

        while (height >= MIN_BUILD_HEIGHT && world.isAirBlock(x, height, z)) {
            height--;
        }

        if (!world.isAirBlock(x, height, z))
            return height;

        return -1;
    }

    private boolean canPlaceStructure(World world, int x, int y, int z) {
        for (int xr = x - 2; xr < x + 2; xr++) {
            for (int zr = z - 2; zr < z + 2; zr++) {
                if (!world.isAirBlock(xr, y, zr))
                    return false;
            }

        }

        return true;
    }


    @Override
    protected void generateLayers() {

        BlockWrapper e = new BlockWrapper(Blocks.stained_hardened_clay, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.YELLOW));
        BlockWrapper b = new BlockWrapper(Blocks.stained_glass, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.YELLOW));
        BlockWrapper w = new BlockWrapper(Blocks.air);
        BlockWrapper f = new BlockWrapper(Blocks.iron_bars);
        BlockWrapper s = new BlockWrapper(Blocks.stone_slab, MWAWBlockHandler.getSlabMetadata(MWAWBlockHandler.SlabType.STONE_BRICK, false));
        BlockWrapper su = new BlockWrapper(Blocks.stone_slab, MWAWBlockHandler.getSlabMetadata(MWAWBlockHandler.SlabType.STONE_BRICK, true));
        BlockWrapper g = new BlockWrapper(Blocks.glowstone);
        BlockWrapper O = new BlockWrapper(Blocks.air);


        BlockWrapper ta = new BlockWrapper(MWAWBlockRegistry.lightningwitchspawner);

        addLayer(1, new BlockWrapper[]{s, s, e, e, b, b,
                e, b, b, b, e, b,
                e, b, e, b, b, e,
                e, b, e, e, b, e,
                e, b, b, b, b, s,
                e, e, e, e, e, s});

        addLayer(2, new BlockWrapper[]{O, O, O, O, b, b,
                O, O, O, O, O, b,
                O, b, O, O, O, O,
                O, O, O, O, O, O,
                O, b, O, b, O, O,
                O, O, O, O, O, O});

        addLayer(3, new BlockWrapper[]{O, O, O, O, b, b,
                O, O, O, O, O, b,
                O, b, O, O, O, O,
                O, O, O, O, O, O,
                O, b, O, b, O, O,
                O, O, O, O, O, O});

        addLayer(4, new BlockWrapper[]{O, O, O, O, O, b,
                O, O, O, O, O, O,
                O, b, O, O, O, O,
                O, O, O, O, O, O,
                O, b, O, b, O, O,
                O, O, O, O, O, O});

        addLayer(5, new BlockWrapper[]{O, O, O, O, O, b,
                O, O, O, O, O, O,
                b, s, O, O, O, O,
                b, e, w, O, O, O,
                b, b, e, s, O, O,
                O, b, b, b, O, O});

        addLayer(6, new BlockWrapper[]{O, O, O, O, O, b,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, f, O, O, O, O,
                O, b, f, O, O, O,
                O, O, O, O, O, O});

        addLayer(7, new BlockWrapper[]{O, O, O, O, e, s,
                O, O, O, O, g, e,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, b, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(8, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, e, s, O,
                O, O, O, g, e, O,
                e, b, O, O, O, O,
                e, b, b, O, O, O,
                O, e, e, O, O, O});

        addLayer(9, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                su, su, b, s, O, O,
                O, s, b, b, O, O,
                O, s, s, su, O, O,
                O, O, O, su, O, O});

        addLayer(10, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, e, su, O,
                O, O, O, g, e, O,
                O, O, b, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(11, new BlockWrapper[]{O, O, O, O, e, su,
                O, O, O, O, g, e,
                O, O, O, O, O, O,
                O, O, b, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(12, new BlockWrapper[]{O, O, O, O, O, b,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, b, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(13, new BlockWrapper[]{O, O, O, e, e, b,
                O, O, e, b, b, e,
                O, e, b, b, b, e,
                b, e, b, b, e, O,
                w, b, e, e, O, O,
                w, w, b, O, O, O});

        addLayer(14, new BlockWrapper[]{O, O, O, b, s, b,
                O, O, O, O, O, s,
                O, O, O, O, O, b,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                ta, O, O, O, O, O});

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

        System.out.println("X= " + x + " Y= " + groundHeight
                + " Z= " + z);

        return true;
    }

}
