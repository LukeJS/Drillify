package me.nentify.drillify.proxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import me.nentify.drillify.Config;

public class CommonProxy {

    public void preInit() {

    }

    public void init() {
        if (Config.armourersWorkshopModels && Loader.isModLoaded("armourersWorkshop")) {
            FMLInterModComms.sendMessage("armourersWorkshop", "register", "me.nentify.drillify.armourers.ArmourersCommonManager");
        }
    }

    public void postInit() {

    }
}
