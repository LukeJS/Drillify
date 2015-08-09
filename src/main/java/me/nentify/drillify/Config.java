package me.nentify.drillify;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static Configuration config;

    // Energy values
    public static int maxEnergy;
    public static int energyPerUse;

    public static void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }

    public static void syncConfig() {
        maxEnergy = config.get("Drills", "Energy storage", 80000).getInt();
        energyPerUse = config.get("Drills", "Energy consumption", 500).getInt();

        config.save();
    }
}
