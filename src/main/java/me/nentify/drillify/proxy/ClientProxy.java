package me.nentify.drillify.proxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.nentify.drillify.Config;
import me.nentify.drillify.Drillify;
import me.nentify.drillify.armourers.ArmourersCommonManager;
import me.nentify.drillify.client.RenderItemDrill;
import me.nentify.drillify.item.DrillifyItems;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT  )
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();

        if (Config.armourersWorkshopModels && Loader.isModLoaded("armourersWorkshop")) {
            FMLInterModComms.sendMessage("armourersWorkshop", "register", "me.nentify.drillify.armourers.ArmourersClientManager");

            RenderItemDrill renderer = new RenderItemDrill();
            MinecraftForgeClient.registerItemRenderer(DrillifyItems.itemDrillIron, renderer);
            MinecraftForgeClient.registerItemRenderer(DrillifyItems.itemDrillDiamond, renderer);
            MinecraftForgeClient.registerItemRenderer(DrillifyItems.itemDrillObsidian, renderer);
        }
    }

    @Override
    public void postInit() {
        super.postInit();

        if (Config.armourersWorkshopModels && Loader.isModLoaded("armourersWorkshop")) {
            ArmourersCommonManager.instance.clientPostInit();
        }
    }
}
