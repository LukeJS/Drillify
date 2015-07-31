package me.nentify.drillify.item;

import cofh.lib.util.helpers.EnergyHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static cofh.lib.util.helpers.ItemHelper.ShapedRecipe;

public class DrillifyItems {

    public static void preInit() {
        itemDrill = new ItemDrill();

        GameRegistry.registerItem(itemDrill, "drill");
    }

    public static void init() {
        toolDrill = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrill), 0);

        GameRegistry.registerCustomItemStack("toolDrill", toolDrill);
    }

    public static void postInit() {
        GameRegistry.addRecipe(ShapedRecipe(toolDrill, new Object[] { "   ", " I ", "   ", 'I', "ingotIron" }));
    }

    public static Item itemDrill;

    public static ItemStack toolDrill;
}
