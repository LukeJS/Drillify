package me.nentify.drillify;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.nentify.drillify.item.DrillifyItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DrillifyCreativeTab extends CreativeTabs {

    public DrillifyCreativeTab() {
        super("Drillify");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return DrillifyItems.itemDrillDiamond;
    }
}
