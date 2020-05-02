package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class StructureAir extends MWAWSymmetricalStructure {

    private static final int STRUCTURE_HEIGHT = 9;
    private static final int MAX_BUILD_HEIGHT = 256 - STRUCTURE_HEIGHT;
    private static final int MIN_BUILD_HEIGHT = 50;

    private static StructureAir _instance = null;

    private StructureAir() {
        super(6, 6);
    }

    /**
     * Get the unique instance.
     *
     * @return Unique instance.
     */
    public static StructureAir getInstance() {
        if (_instance == null)
            _instance = new StructureAir();

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

        BlockWrapper d = new BlockWrapper(Blocks.stained_hardened_clay, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.WHITE));
        BlockWrapper g = new BlockWrapper(Blocks.stained_glass, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.WHITE));
        BlockWrapper i = new BlockWrapper(Blocks.iron_bars);
        BlockWrapper w = new BlockWrapper(Blocks.stained_hardened_clay, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.WHITE));
        BlockWrapper gs = new BlockWrapper(Blocks.glowstone);
        BlockWrapper p = new BlockWrapper(Blocks.stained_glass, MWAWBlockHandler.getStainedGlassAndClayMetadata(MWAWBlockHandler.GlassAndClayColourType.WHITE));
        BlockWrapper wa = new BlockWrapper(Blocks.air);
        BlockWrapper f = new BlockWrapper(Blocks.oak_fence);
        BlockWrapper s = new BlockWrapper(Blocks.wooden_slab);
        BlockWrapper O = new BlockWrapper(Blocks.air);

        BlockWrapper ta = new BlockWrapper(MWAWBlockRegistry.airwitchspawner);

        addLayer(1, new BlockWrapper[]{d, d, d, d, d, d,
                d, g, g, g, d, d,
                d, g, g, g, g, d,
                d, d, g, g, g, d,
                d, i, d, g, g, d,
                w, d, d, d, d, d});

        addLayer(2, new BlockWrapper[]{g, g, g, w, O, O,
                O, O, O, O, g, O,
                O, O, O, O, O, w,
                O, O, O, O, O, d,
                O, O, O, O, O, O,
                gs, O, O, O, O, O});

        addLayer(3, new BlockWrapper[]{g, g, g, w, O, O,
                O, O, O, O, g, O,
                O, O, O, O, O, w,
                O, O, O, O, O, d,
                O, O, O, O, O, O,
                w, O, O, O, O, O});

        addLayer(4, new BlockWrapper[]{g, g, g, w, O, O,
                O, O, O, O, g, O,
                O, O, O, O, O, w,
                O, O, O, O, O, d,
                O, O, O, O, O, O,
                gs, O, O, O, O, O});

        addLayer(5, new BlockWrapper[]{w, w, w, w, O, O,
                p, g, g, g, w, O,
                p, g, g, g, g, w,
                p, p, w, g, g, w,
                wa, p, p, g, g, w,
                w, wa, p, p, p, w});

        addLayer(6, new BlockWrapper[]{f, w, f, w, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, w, O, O, O,
                O, O, O, O, O, O,
                ta, O, O, O, O, O});

        addLayer(7, new BlockWrapper[]{O, s, O, s, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, w, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(8, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, w, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O});

        addLayer(9, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                f, f, w, O, O, O,
                O, O, f, O, O, O,
                O, O, f, O, O, O});

        addLayer(10, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                s, s, s, s, O, O,
                p, p, p, s, O, O,
                O, O, p, s, O, O,
                gs, O, p, s, O, O});

        addLayer(11, new BlockWrapper[]{O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                O, O, O, O, O, O,
                s, s, O, O, O, O,
                p, s, O, O, O, O});



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

        System.out.println("X= " + x + " Y= "
                + groundHeight + " Z= " + z);

        return true;
    }
}
