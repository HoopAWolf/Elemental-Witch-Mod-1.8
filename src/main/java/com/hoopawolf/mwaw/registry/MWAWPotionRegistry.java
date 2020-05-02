package com.hoopawolf.mwaw.registry;

import com.hoopawolf.mwaw.potion.MWAWPotionHandler;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MWAWPotionRegistry {

    public static Potion paralyzePotion, paranoidPotion, enderSkinPotion, earthstancePotion, airstancePotion;

    public static void potionCheck() {

        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {

            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }

    }

    public static void registerPotions() {

        paralyzePotion = (new MWAWPotionHandler(MWAWConfigHandler.PotionIDParalyze, false, 0)).setIconIndex(1, 1).setPotionName("Paralyze").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -50D, 2);
        paranoidPotion = (new MWAWPotionHandler(MWAWConfigHandler.PotionIDParanoid, false, 0)).setIconIndex(3, 1).setPotionName(EnumChatFormatting.OBFUSCATED + "Paranoid");
        enderSkinPotion = (new MWAWPotionHandler(MWAWConfigHandler.PotionIDEnderSkin, false, 0)).setIconIndex(0, 2).setPotionName("Ender Skin");
        earthstancePotion = (new MWAWPotionHandler(MWAWConfigHandler.PotionIDEarthStance, false, 0)).setIconIndex(7, 0).setPotionName("Earth Stance");
        airstancePotion = (new MWAWPotionHandler(MWAWConfigHandler.PotionIDAirStance, false, 0)).setIconIndex(7, 0).setPotionName("Air Stance").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", 3D, 2);

    }

}
