package com.hoopawolf.mwaw.structure;

public class MWAWBlockHandler {

    public static int getSlabMetadata(SlabType type, boolean upsideDown) {
        return upsideDown ? (8 | type.metadata) : type.metadata;
    }

    public static int getStainedGlassAndClayMetadata(GlassAndClayColourType type) {
        return type.metadata;
    }

    public static int getWoolMetadata(WoolColourType type) {
        return type.metadata;
    }

    public static int getStoneBrickMetadata(StoneBrickType type) {
        return type.metadata;
    }

    public enum SlabType {

        // Stone slabs
        STONE(0), SANDSTONE(1), WOODEN(2), COBBLESTONE(3), BRICK(4), STONE_BRICK(
                5), NETHER_BRICK(6), QUARTZ(7),

        // Wooden slabs
        OAK_WOOD(0), SPRUCE_WOOD(1), BIRCH_WOOD(2), JUNGLE_WOOD(3), ACACIA_WOOD(
                4), DARK_OAK_WOOD(5);

        /**
         * The metadata of that type.
         */
        public int metadata;

        private SlabType(int metadata) {
            this.metadata = metadata;
        }
    }

    public enum StoneBrickType {

        REGULAR(0), MOSSY(1), CRACKED(2), CHISELED(3);

        public int metadata;

        private StoneBrickType(int metadata) {
            this.metadata = metadata;
        }
    }

    public enum GlassAndClayColourType {

        WHITE(0), ORANGE(1), MAGENTA(2), LIGHTBLUE(3), YELLOW(4), LIME(5), PINK(
                6), GRAY(7), LIGHTGRAY(8), CYAN(9), PURPLE(10), BLUE(11), BROWN(
                12), GREEN(13), RED(14), BLACK(15);

        public int metadata;

        private GlassAndClayColourType(int metadata) {
            this.metadata = metadata;
        }
    }

    public enum WoolColourType {

        White(0), ORANGE(1), MAGENTA(2), LIGHTBLUE(3), YELLOW(4), LIME(5), PINK(
                6), GRAY(7), LIGHTGRAY(8), CYAN(9), PURPLE(10), BLUE(11), BROWN(
                12), GREEN(13), RED(14), BLACK(15);

        public int metadata;

        private WoolColourType(int metadata) {
            this.metadata = metadata;
        }

    }
}
