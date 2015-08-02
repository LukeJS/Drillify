package me.nentify.drillify.integration;

import cpw.mods.fml.common.registry.GameRegistry;
import me.nentify.drillify.Drillify;
import net.minecraft.item.ItemStack;

public class TEItems {

    public static ItemStack capacitorReinforced = null;
    public static ItemStack powerCoilElectrum = null;

    public static void init() {
        capacitorReinforced = GameRegistry.findItemStack("ThermalExpansion", "capacitorReinforced", 1);
        powerCoilElectrum = GameRegistry.findItemStack("ThermalExpansion", "powerCoilElectrum", 1);
    }
}
