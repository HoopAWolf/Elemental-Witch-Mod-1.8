package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class StructureWater extends MWAWSymmetricalStructure {

    private static final int STRUCTURE_HEIGHT = 12;
    private static final int MAX_BUILD_HEIGHT = 256 - STRUCTURE_HEIGHT;
    private static final int MIN_BUILD_HEIGHT = 50;
    private static StructureWater _instance = null;

    public StructureWater() {
        super(6, 6);
    }

    /**
     * Get the unique instance.
     *
     * @return Unique instance.
     */
    public static StructureWater getInstance() {
        if (_instance == null)
            _instance = new StructureWater();

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
                    return true;
            }

        }

        return true;
    }

    @Override
    protected void generateLayers() {

        BlockWrapper s = new BlockWrapper(Blocks.water);
        BlockWrapper q = new BlockWrapper(Blocks.stained_glass, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.LIGHTBLUE));
        BlockWrapper o = new BlockWrapper(Blocks.stained_hardened_clay, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.LIGHTBLUE));
        BlockWrapper w = new BlockWrapper(Blocks.flowing_water);
        BlockWrapper i = new BlockWrapper(Blocks.stained_hardened_clay, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.BLUE));
        BlockWrapper g = new BlockWrapper(Blocks.stained_glass, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.BLUE));
        BlockWrapper r = new BlockWrapper(Blocks.air);
        BlockWrapper O = new BlockWrapper(Blocks.air);

        BlockWrapper ta = new BlockWrapper(MWAWBlockRegistry.waterwitchspawner);

        addLayer(1, new BlockWrapper[]{s, o, o, q, q, q, w, q, q, q, o, q, w,
                q, q, q, q, q, w, o, q, q, q, o, s, s, o, q, q, q, s, s, w, w,
                w, s});

        addLayer(2, new BlockWrapper[]{O, s, q, o, q, q, O, O, O, O, O, q, s,
                s, s, O, O, o, q, o, s, s, O, q, O, O, o, s, O, O, o, O, q, s,
                O, O});

        addLayer(3, new BlockWrapper[]{O, O, O, s, o, q, O, O, O, O, O, o, O,
                O, O, O, O, s, O, o, O, O, O, O, O, O, o, O, O, O, o, O, O, O,
                O, O});

        addLayer(4, new BlockWrapper[]{O, O, O, O, O, q, O, O, O, O, O, O, O,
                O, O, O, O, O, O, o, O, O, O, O, O, O, o, O, O, O, o, O, O, O,
                O, O});

        addLayer(5, new BlockWrapper[]{O, O, O, O, O, q, O, O, O, O, O, O, O,
                O, O, O, O, O, O, o, O, O, O, O, O, O, o, O, O, O, o, O, O, O,
                O, O});

        addLayer(6, new BlockWrapper[]{O, O, O, q, o, q, O, O, q, o, o, o, q,
                q, o, o, o, q, o, o, o, o, q, O, w, w, o, q, O, O, o, w, o, q,
                O, O});

        addLayer(7, new BlockWrapper[]{O, O, O, q, q, q, O, O, O, O, r, q, O,
                O, O, O, O, q, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(8, new BlockWrapper[]{O, O, O, i, i, q, O, O, O, O, O, i, O,
                O, O, O, O, i, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(9, new BlockWrapper[]{O, O, O, q, q, s, g, g, g, O, O, q, O,
                O, O, O, O, q, O, O, O, O, g, O, O, O, O, O, g, O, O, O, O, O,
                g, O});

        addLayer(10, new BlockWrapper[]{O, O, O, O, O, O, O, O, s, s, s, O,
                g, o, o, o, s, O, O, q, q, o, s, O, O, O, q, o, O, O, O, O, O,
                g, O, O});

        addLayer(11, new BlockWrapper[]{O, O, O, O, O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, g, s, s, O, O, O, O, g, s, O, O, O, ta, O, g,
                O, O, O});

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
