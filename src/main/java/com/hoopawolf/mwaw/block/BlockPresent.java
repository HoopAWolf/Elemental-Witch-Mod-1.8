package com.hoopawolf.mwaw.block;

import com.hoopawolf.mwaw.lib.MWAWEntityUtil;
import com.hoopawolf.mwaw.lib.Reference;
import com.hoopawolf.mwaw.registry.MWAWBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.awt.*;

public class BlockPresent extends Block {

    private ItemStack firework;

    public BlockPresent() {
        super(Material.cloth);
        setBlockName("present");
        setBlockTextureName(Reference.MOD_ID + ":" + "present");
        setResistance(100.0F);
        setStepSound(soundTypeSnow);

    }

    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        int random = p_149727_1_.rand.nextInt(50);

        if (!p_149727_1_.isRemote) {

            if (random == 17) {
                EntityZombie zombie = new EntityZombie(p_149727_1_);
                zombie.setChild(true);
                zombie.setVillager(true);
                zombie.setAlwaysRenderNameTag(true);
                zombie.setCustomNameTag(EnumChatFormatting.GREEN + "Christmas Thief");
                zombie.setPositionAndRotation(p_149727_2_, p_149727_3_, p_149727_4_, 0, 0);
                zombie.setCurrentItemOrArmor(0, new ItemStack(MWAWBlockRegistry.present));
                zombie.setCurrentItemOrArmor(1, new ItemStack(Items.leather_boots));
                zombie.setCurrentItemOrArmor(2, new ItemStack(Items.leather_leggings));
                zombie.setCurrentItemOrArmor(3, new ItemStack(Items.leather_chestplate));
                zombie.setCurrentItemOrArmor(4, new ItemStack(Items.leather_helmet));

                MWAWEntityUtil.spawnInWorld(p_149727_1_, zombie);

                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);

            } else if (random >= 30 && random <= 36) {
                getRandomColorFireWork(p_149727_1_, 2, 0);
                p_149727_5_.dropItem(Items.potato, p_149727_1_.rand.nextInt(20));

                for (int i = 0; i <= 4; i++) {
                    EntityFireworkRocket rocket = new EntityFireworkRocket(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, this.firework.copy());
                    MWAWEntityUtil.spawnInWorld(p_149727_1_, rocket);
                }

                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);

            } else if (random >= 20 && random <= 26) {

                getRandomColorFireWork(p_149727_1_, 4, 1);

                EntityFireworkRocket rocket = new EntityFireworkRocket(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, this.firework.copy());
                MWAWEntityUtil.spawnInWorld(p_149727_1_, rocket);

                p_149727_5_.mountEntity(rocket);

                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);

            } else if (random >= 1 && random <= 6) {

                Block block = Blocks.stonebrick;
                Block block2 = Blocks.iron_bars;

                //bottom
                p_149727_5_.setPositionAndUpdate((int) p_149727_5_.posX, (int) p_149727_5_.posY, (int) p_149727_5_.posZ);
                if (!p_149727_1_.isRemote) {
                    p_149727_5_.addChatComponentMessage((new ChatComponentTranslation(
                            "chat.mwaw.lookup")));
                }
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ + 1, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ - 1, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ + 1, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ - 1, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ - 1, block);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY - 1, (int) p_149727_5_.posZ + 1, block);

                //middleBottom
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY, (int) p_149727_5_.posZ + 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ + 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY, (int) p_149727_5_.posZ + 1, block2);

                //middleTop
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ + 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ + 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX - 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ - 1, block2);
                p_149727_1_.setBlock((int) p_149727_5_.posX + 1, (int) p_149727_5_.posY + 1, (int) p_149727_5_.posZ + 1, block2);

                EntityMinecartTNT tnt = new EntityMinecartTNT(p_149727_1_);
                tnt.setPosition((int) p_149727_5_.posX, (int) p_149727_5_.posY + 20, (int) p_149727_5_.posZ);

                MWAWEntityUtil.spawnInWorld(p_149727_1_, tnt);

                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);

            } else if (random >= 40) {
                EntityTNTPrimed tnt = new EntityTNTPrimed(p_149727_1_);
                tnt.fuse = 10;
                tnt.setPositionAndRotation(p_149727_2_, p_149727_3_, p_149727_4_, 0, 0);

                MWAWEntityUtil.spawnInWorld(p_149727_1_, tnt);

                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);

            } else {
                p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);
            }


        }

        return true;
    }

    public void getRandomColorFireWork(World world, int type, int flight) {
        this.firework = new ItemStack(Items.fireworks, 1);
        this.firework.stackTagCompound = new NBTTagCompound();
        NBTTagCompound data = new NBTTagCompound();
        data.setByte("Flight", (byte) flight);
        NBTTagList list = new NBTTagList();
        NBTTagCompound fireworkData = new NBTTagCompound();
        fireworkData.setByte("Trail", (byte) 1);
        fireworkData.setByte("Type", (byte) type);
        fireworkData.setIntArray("Colors", new int[]{new Color(world.rand.nextInt(255), world.rand.nextInt(255), world.rand.nextInt(255)).getRGB()});
        list.appendTag(fireworkData);
        data.setTag("Explosions", list);
        this.firework.getTagCompound().setTag("Fireworks", data);
    }

}
