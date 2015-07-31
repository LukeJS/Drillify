package me.nentify.drillify;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.Item;

@Mod(modid = Drillify.MODID, version = Drillify.VERSION)
public class Drillify
{
    public static final String MODID = "drillify";
    public static final String VERSION = "0.1-alpha";

    public static Item drill = new ItemDrill();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Drillify");

        GameRegistry.registerItem(drill, drill.getUnlocalizedName());
    }
}
