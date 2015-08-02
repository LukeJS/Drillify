package me.nentify.drillify.integration;

import cpw.mods.fml.common.registry.GameRegistry;
import me.nentify.drillify.Drillify;
import net.minecraft.item.ItemStack;

public class TEItems {

    public static ItemStack capacitorHardened = null;
    public static ItemStack powerCoilElectrum = null;

    public static void init() {
        capacitorHardened = GameRegistry.findItemStack("ThermalExpansion", "capacitorHardened", 1);
        powerCoilElectrum = GameRegistry.findItemStack("ThermalExpansion", "powerCoilElectrum", 1);
    }
}
