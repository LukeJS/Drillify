package me.nentify.drillify.item;

import cofh.core.item.ItemBase;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import me.nentify.drillify.Drillify;
import me.nentify.drillify.integration.TEItems;
import me.nentify.drillify.item.drill.ItemDrill;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static cofh.lib.util.helpers.ItemHelper.ShapedRecipe;
import static cofh.lib.util.helpers.ItemHelper.ShapelessRecipe;

public class DrillifyItems {

    public static void preInit() {
        itemMaterial = (ItemBase) new ItemBase("drillify").setUnlocalizedName("material").setCreativeTab(Drillify.creativeTab);

        itemDrillIron = new ItemDrill(Item.ToolMaterial.IRON).setUnlocalizedName(Drillify.PREFIX + "drillIron").setTextureName(Drillify.RESOURCE_PREFIX + "DrillIron").setCreativeTab(Drillify.creativeTab);
        itemDrillDiamond = new ItemDrill(Item.ToolMaterial.EMERALD).setUnlocalizedName(Drillify.PREFIX + "drillDiamond").setTextureName(Drillify.RESOURCE_PREFIX + "DrillDiamond").setCreativeTab(Drillify.creativeTab);
        itemDrillObsidian = new ItemDrill(TOOL_MATERIAL_OBSIDIAN).setUnlocalizedName(Drillify.PREFIX + "drillObsidian").setTextureName(Drillify.RESOURCE_PREFIX + "DrillObsidian").setCreativeTab(Drillify.creativeTab);

        GameRegistry.registerItem(itemDrillIron, "drillIron");
        GameRegistry.registerItem(itemDrillDiamond, "drillDiamond");
        GameRegistry.registerItem(itemDrillObsidian, "drillObsidian");
    }

    public static void init() {
        drillHeadIron = itemMaterial.addItem(0, "drillHeadIron");
        drillHeadDiamond = itemMaterial.addItem(1, "drillHeadDiamond");
        drillHeadObsidian = itemMaterial.addItem(2, "drillHeadObsidian");

        toolDrillIron = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillIron), 0);
        toolDrillDiamond = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillDiamond), 0);
        toolDrillObsidian = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillObsidian), 0);

        GameRegistry.registerCustomItemStack("toolDrillIron", toolDrillIron);
        GameRegistry.registerCustomItemStack("toolDrillDiamond", toolDrillDiamond);
        GameRegistry.registerCustomItemStack("toolDrillObsidian", toolDrillObsidian);
    }

    public static void postInit() {
        ItemHelper.addShapedOreRecipe(drillHeadIron, "I  ", " B ", "  B", 'I', "ingotIron", 'B', "blockIron");
        ItemHelper.addShapedOreRecipe(drillHeadDiamond, "DD", "DH", 'D', "gemDiamond", 'H', drillHeadIron);
        //ItemHelper.addShapedOreRecipe(drillHeadObsidian, "O  ", "   ", "   ", 'O', Blocks.obsidian);

        ItemHelper.addShapedOreRecipe(toolDrillIron, "HI ", "IRI", " IC", 'I', "ingotInvar", 'H', drillHeadIron, 'C', 'R', TEItems.capacitorHardened, TEItems.powerCoilElectrum);
        ItemHelper.addShapedOreRecipe(toolDrillDiamond, "H ", " D", 'H', drillHeadDiamond, 'D', toolDrillIron);
        ItemHelper.addShapedOreRecipe(toolDrillObsidian, "H ", " D", 'H', drillHeadObsidian, 'D', toolDrillDiamond);
    }

    public static ItemBase itemMaterial;

    public static Item itemDrillIron;
    public static Item itemDrillDiamond;
    public static Item itemDrillObsidian;

    public static ItemStack drillHeadIron;
    public static ItemStack drillHeadDiamond;
    public static ItemStack drillHeadObsidian;

    public static ItemStack toolDrillIron;
    public static ItemStack toolDrillDiamond;
    public static ItemStack toolDrillObsidian;

    public static final Item.ToolMaterial TOOL_MATERIAL_OBSIDIAN = EnumHelper.addToolMaterial("OBSIDIAN", 3, 100, 10.0F, 3, 25);
}
