package me.nentify.drillify.proxy;

import cpw.mods.fml.common.event.FMLInterModComms;

public class CommonProxy {

    public void preInit() {

    }

    public void init() {
        FMLInterModComms.sendMessage("armourersWorkshop", "register", "me.nentify.drillify.armourers.ArmourersCommonManager");
    }

    public void postInit() {

    }
}
