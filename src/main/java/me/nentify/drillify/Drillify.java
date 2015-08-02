package me.nentify.drillify;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import me.nentify.drillify.integration.TEItems;
import me.nentify.drillify.item.DrillifyItems;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.Logger;

@Mod(modid = Drillify.MODID, version = Drillify.VERSION, dependencies = Drillify.DEPENDENCIES)
public class Drillify {
    public static final String MODID = "drillify";
    public static final String VERSION = "0.2-alpha";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String DEPENDENCIES = "required-after:Forge@[10.13.2.1291,);after:ThermalExpansion";

    public static Logger logger;
    public static CreativeTabs creativeTab;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Starting Drillify");

        creativeTab = new DrillifyCreativeTab();

        DrillifyItems.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        TEItems.init();
        DrillifyItems.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DrillifyItems.postInit();
    }
}
