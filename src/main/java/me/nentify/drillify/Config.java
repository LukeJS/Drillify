package me.nentify.drillify;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    public static Configuration config;

    // Energy values
    public static int maxEnergy;
    public static int energyPerUse;

    public static boolean thermalExpansionRecipes;

    public static boolean armourersWorkshopModels;

    public static void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }

    public static void syncConfig() {
        maxEnergy = config.get("drills", "energyStorage", 80000).getInt();
        energyPerUse = config.get("drills", "energyConsumption", 500).getInt();

        thermalExpansionRecipes = config.get("recipes", "thermalExpansionRecipes", true).getBoolean();

        armourersWorkshopModels = config.get("client", "armourersWorkshopModels", true).getBoolean();

        config.save();
    }
}
