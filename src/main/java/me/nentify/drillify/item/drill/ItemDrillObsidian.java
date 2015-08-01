package me.nentify.drillify.item.drill;

import me.nentify.drillify.item.DrillifyItems;

public class ItemDrillObsidian extends ItemDrill {

    public ItemDrillObsidian() {
        super(DrillifyItems.TOOL_MATERIAL_OBSIDIAN);

        maxEnergy = 4000000;
        energyPerUse = 800;
    }
}
