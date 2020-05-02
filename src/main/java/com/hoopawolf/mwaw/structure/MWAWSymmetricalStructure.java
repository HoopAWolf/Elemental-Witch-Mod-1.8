package com.hoopawolf.mwaw.structure;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MWAWSymmetricalStructure {
    protected int quarterWidth;
    protected int quarterHeight;
    private List<Integer> layerHeights;
    private List<BlockWrapper[]> layers;

    public MWAWSymmetricalStructure(int quarterWidth, int quarterHeight) {
        layerHeights = new ArrayList<Integer>();
        layers = new ArrayList<BlockWrapper[]>();

        this.quarterWidth = quarterWidth;
        this.quarterHeight = quarterHeight;

        generateLayers();
    }

    protected abstract void generateLayers();

    protected void addLayer(int height, BlockWrapper[] layer) {
        layerHeights.add(height);
        layers.add(layer);
    }

    protected void buildCorner(World world, int x, int y, int z, int incX,
                               int incZ) {
        for (int i = 0; i < layerHeights.size(); ++i) {
            int layerHeight = layerHeights.get(i);
            BlockWrapper[] layer = layers.get(i);

            int start = (incX == incZ) ? 0 : 1;

            for (int zr = start; zr < quarterHeight; ++zr) {
                for (int xr = start; xr < quarterWidth; ++xr) {
                    BlockWrapper wrapper = layer[(quarterWidth - xr - 1)
                            * quarterHeight + zr];

                    if (wrapper != null) // not an air block?
                        world.setBlockState(new BlockPos(x + xr * incX, y + layerHeight, z + zr
                                * incZ), wrapper.block.getDefaultState());
                }
            }
        }

        postBuildCorner(world, x, y, z, incX, incZ);
    }

    protected void postBuildCorner(World world, int x, int y, int z, int incX,
                                   int incZ) {

    }

    /**
     * Generate this structure.
     *
     * @param world  The world.
     * @param random Random object.
     * @param x      X center coordinate.
     * @param y      Y center coordinate.
     * @param z      Z center coordinate.
     * @return If the structure was generated.
     */
    public boolean generate(World world, Random random, int x, int y, int z) {
        buildCorner(world, x, y, z, 1, 1);
        buildCorner(world, x, y, z, -1, 1);
        buildCorner(world, x, y, z, 1, -1);
        buildCorner(world, x, y, z, -1, -1);

        return true;
    }

    /**
     * This is a wrapper class, which wraps around a {@link net.minecraft.block.Block} and
     * pairs with it the metadata for that specific block instance.
     *
     * @author immortaleeb
     */
    public class BlockWrapper {
        /**
         * {@link net.minecraft.block.Block} for which this instance is a wrapper.
         */
        public Block block;
        /**
         * Metadata which should be used for the specific {@link Block}.
         */
        public int metadata;

        /**
         * Creates a new wrapper around the specified {@link Block} with
         * metadata 0.
         *
         * @param block
         */
        public BlockWrapper(Block block) {
            this(block, 0);
        }

        /**
         * Creates a new wrapper around the specified {@link Block} with the
         * specified metadata.
         *
         * @param block
         * @param metadata
         */
        public BlockWrapper(Block block, int metadata) {
            this.block = block;
            this.metadata = metadata;
        }
    }
}