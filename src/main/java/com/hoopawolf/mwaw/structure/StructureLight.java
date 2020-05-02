package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class StructureLight extends MWAWSymmetricalStructure {

    private static final int STRUCTURE_HEIGHT = 15;
    private static final int MAX_BUILD_HEIGHT = 256 - STRUCTURE_HEIGHT;
    private static final int MIN_BUILD_HEIGHT = 50;

    private static StructureLight _instance = null;

    private StructureLight() {
        super(6, 6);
    }

    /**
     * Get the unique instance.
     *
     * @return Unique instance.
     */
    public static StructureLight getInstance() {
        if (_instance == null)
            _instance = new StructureLight();

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

        BlockWrapper w = new BlockWrapper(Blocks.glowstone);
        BlockWrapper wp = new BlockWrapper(Blocks.quartz_block);
        BlockWrapper If = new BlockWrapper(Blocks.iron_bars);
        BlockWrapper f = new BlockWrapper(Blocks.fence);
        BlockWrapper c = new BlockWrapper(Blocks.quartz_block, 2);
        BlockWrapper bg = new BlockWrapper(Blocks.gold_block);
        BlockWrapper ws = new BlockWrapper(Blocks.bedrock);
        BlockWrapper g = new BlockWrapper(Blocks.glass);
        BlockWrapper O = new BlockWrapper(Blocks.air);

        BlockWrapper ta = new BlockWrapper(MWAWBlockRegistry.lightwitchspawner);

        addLayer(1, new BlockWrapper[]{c, If, w, w, w, w, w, If, w, w, w, w,
                w, If, w, w, w, w, wp, wp, wp, w, w, w, wp, wp, wp, If, If, If,
                bg, wp, wp, w, w, c});

        addLayer(2, new BlockWrapper[]{O, c, c, c, w, w, wp, wp, wp, wp, c,
                w, O, O, w, wp, wp, c, O, If, wp, w, wp, c, O, wp, If, O, wp,
                c, O, O, O, O, wp, O});

        addLayer(3, new BlockWrapper[]{O, f, f, f, f, w, O, O, O, O, O, f, O,
                ws, w, O, O, f, O, ws, ws, w, O, f, g, wp, ws, ws, O, f, ws, g,
                O, O, O, O});

        addLayer(4, new BlockWrapper[]{O, O, O, O, O, w, O, O, O, O, O, O, O,
                O, w, O, O, O, O, O, O, w, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(5, new BlockWrapper[]{O, O, O, O, O, w, O, O, O, O, O, O, O,
                O, w, O, O, O, O, O, O, w, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(6, new BlockWrapper[]{O, O, O, O, wp, w, O, O, O, wp, wp,
                wp, wp, wp, w, wp, wp, O, ws, wp, wp, w, O, O, wp, wp, wp, wp,
                O, O, w, wp, ws, wp, O, O});

        addLayer(7, new BlockWrapper[]{O, O, O, O, f, w, O, O, O, O, O, O, O,
                O, O, w, O, O, O, O, O, O, O, O, O, O, O, O, O, O, ta, O, O, O,
                O, O});

        addLayer(8, new BlockWrapper[]{O, O, O, O, O, w, O, O, O, O, O, O, O,
                O, O, w, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(9, new BlockWrapper[]{O, O, O, O, O, w, O, O, O, O, O, O, O,
                O, O, w, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O,
                O, O});

        addLayer(10, new BlockWrapper[]{O, O, O, O, ws, w, wp, wp, wp, wp,
                ws, ws, O, O, O, w, wp, O, O, O, O, O, wp, O, O, O, O, O, wp,
                O, O, O, O, O, wp, O});

        addLayer(11, new BlockWrapper[]{O, O, O, O, O, O, ws, O, O, O, O, O,
                wp, ws, ws, O, O, O, wp, wp, wp, ws, O, O, O, O, wp, ws, O, O,
                O, O, wp, wp, ws, O});

        addLayer(12, new BlockWrapper[]{O, O, O, O, O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, ws, ws, O, O, O, O, wp, wp, ws, O, O, O, O,
                wp, ws, O, O, O});

        addLayer(13, new BlockWrapper[]{O, O, O, O, O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, O, O, O, O, O, O, ws, ws, O, O, O, O, wp, ws,
                O, O, O, O});

        addLayer(14, new BlockWrapper[]{O, O, O, O, O, O, O, O, O, O, O, O,
                O, O, O, O, O, O, O, O, O, O, O, O, f, O, O, O, O, O, ws, f, O,
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
        System.out.println("X= " + x + " Y= " + groundHeight + " Z= " + z);


        return true;
    }
}
