package com.hoopawolf.mwaw.registry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MWAWRecipe {

    public static void addRecipes() {

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.firestaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.fireshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.earthstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.earthshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.lightningstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.lightningshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.windstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.airshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.lightstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.lightshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.darkstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.darkshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.waterstaff, 1), new Object[]{" / ", "/=/", " = ",
                '=', Items.stick,
                '/', MWAWItemRegistry.watershard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.fireegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.fireshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.airegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.airshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.darkegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.darkshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.earthegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.earthshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.lightegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.lightshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.lightningegg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.lightningshard
        });

        GameRegistry.addRecipe(new ItemStack(MWAWItemRegistry.wateregg, 1), new Object[]{"///", "/=/", "///",
                '=', MWAWItemRegistry.emptyegg,
                '/', MWAWItemRegistry.watershard
        });

        GameRegistry.addShapelessRecipe(new ItemStack(
                MWAWItemRegistry.emptyegg, 1), Items.egg);
    }

}
