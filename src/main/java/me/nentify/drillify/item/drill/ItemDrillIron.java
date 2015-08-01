package me.nentify.drillify.item.drill;

public class ItemDrillIron extends ItemDrill {

    public ItemDrillIron() {
        super(ToolMaterial.IRON);

        maxEnergy = 80000;
        energyPerUse = 250;
    }
}
