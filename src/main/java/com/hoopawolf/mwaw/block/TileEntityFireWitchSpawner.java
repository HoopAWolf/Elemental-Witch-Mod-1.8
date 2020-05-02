package com.hoopawolf.mwaw.block;

import com.hoopawolf.mwaw.entity.EntityFireWitch;
import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TileEntityFireWitchSpawner extends TileEntity {


    @Override
    public void updateContainingBlockInfo() {

        AxisAlignedBB boundingBox = AxisAlignedBB.fromBounds(0.0D, 0.0D, 0.0D, 00.0D, 00.0D, 00.0D);
        boundingBox.addCoord(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, boundingBox.expand(30D, 30D, 30D));

        for (int i = 0; i < list.size(); i++) {
            Entity entitycheck = (Entity) list.get(i);
            if (entitycheck instanceof EntityPlayer) {

                Spawn(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), worldObj);

            }
        }
    }

    public void Spawn(int x, int y, int z, World world) {

        EntityFireWitch witch = new EntityFireWitch(worldObj);
        witch.setPosition(x, y, z);
        MWAWEntityUtil.spawnInWorld(world, witch);

        world.setBlockState(new BlockPos(x, y, z), Blocks.air.getDefaultState());

    }


}
