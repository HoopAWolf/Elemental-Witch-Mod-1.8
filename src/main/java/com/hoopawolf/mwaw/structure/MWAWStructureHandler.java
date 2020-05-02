package com.hoopawolf.mwaw.structure;

import com.hoopawolf.mwaw.registry.MWAWConfigHandler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class MWAWStructureHandler implements IWorldGenerator {

    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        int modX = MWAWConfigHandler.StructureSpawnDistance;
        int modZ = MWAWConfigHandler.StructureSpawnDistance;
        int rand = random.nextInt(7);

        if (world.provider.getDimensionId() == 0) {

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 1 && MWAWConfigHandler.SpawnFireTemple && MWAWConfigHandler.AddFireWitch) {

                StructureFire.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 2 && MWAWConfigHandler.SpawnEarthTemple && MWAWConfigHandler.AddEarthWitch) {

                StructureEarth.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 3 && MWAWConfigHandler.SpawnLightningTemple && MWAWConfigHandler.AddLightningWitch) {

                StructureLightning.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 4 && MWAWConfigHandler.SpawnAirTemple && MWAWConfigHandler.AddAirWitch) {

                StructureAir.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 5 && MWAWConfigHandler.SpawnDarkTemple && MWAWConfigHandler.AddDarkWitch) {

                StructureDark.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

            if (chunkX % modX == random.nextInt(modX)
                    && chunkZ % modZ == random.nextInt(modZ) && rand == 6 && MWAWConfigHandler.SpawnLightTemple && MWAWConfigHandler.AddLightWitch) {

                StructureLight.getInstance().generate(world, random,
                        chunkX * random.nextInt(50), 0,
                        chunkZ * random.nextInt(50));

            }

        }
    }
}
