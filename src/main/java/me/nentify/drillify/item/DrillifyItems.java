package me.nentify.drillify.item;

import cofh.core.item.ItemBase;
import cofh.lib.util.helpers.EnergyHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import me.nentify.drillify.Drillify;
import me.nentify.drillify.item.drill.ItemDrill;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static cofh.lib.util.helpers.ItemHelper.ShapedRecipe;

public class DrillifyItems {

    public static void preInit() {
        itemMaterial = (ItemBase) new ItemBase("drillify").setUnlocalizedName("material").setCreativeTab(Drillify.creativeTab);

        itemDrillIron = new ItemDrill(Item.ToolMaterial.IRON).setUnlocalizedName(Drillify.PREFIX + "drillIron").setTextureName(Drillify.RESOURCE_PREFIX + "drillIron").setCreativeTab(Drillify.creativeTab);
        itemDrillDiamond = new ItemDrill(Item.ToolMaterial.EMERALD).setUnlocalizedName(Drillify.PREFIX + "drillDiamond").setTextureName(Drillify.RESOURCE_PREFIX + "drillDiamond").setCreativeTab(Drillify.creativeTab);
        itemDrillObsidian = new ItemDrill(TOOL_MATERIAL_OBSIDIAN).setUnlocalizedName(Drillify.PREFIX + "drillObsidian").setTextureName(Drillify.RESOURCE_PREFIX + "drillObsidian").setCreativeTab(Drillify.creativeTab);

        GameRegistry.registerItem(itemDrillIron, "drillIron");
        GameRegistry.registerItem(itemDrillDiamond, "drillDiamond");
        GameRegistry.registerItem(itemDrillObsidian, "drillObsidian");
    }

    public static void init() {
        drillBody = itemMaterial.addItem(0, "drillBody");

        drillHeadIron = itemMaterial.addItem(1, "drillHeadIron");
        drillHeadDiamond = itemMaterial.addItem(2, "drillHeadDiamond");
        drillHeadObsidian = itemMaterial.addItem(3, "drillHeadObsidian");

        toolDrillIron = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillIron), 0);
        toolDrillDiamond = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillDiamond), 0);
        toolDrillObsidian = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemDrillObsidian), 0);

        GameRegistry.registerCustomItemStack("toolDrillIron", toolDrillIron);
        GameRegistry.registerCustomItemStack("toolDrillDiamond", toolDrillDiamond);
        GameRegistry.registerCustomItemStack("toolDrillObsidian", toolDrillObsidian);
    }

    public static void postInit() {
        GameRegistry.addRecipe(ShapedRecipe(toolDrillIron, new Object[] { "   ", " I ", "   ", 'I', "ingotIron" }));
        GameRegistry.addRecipe(ShapedRecipe(toolDrillDiamond, new Object[] { "   ", " I ", "   ", 'I', "gemDiamond" }));
        GameRegistry.addRecipe(ShapedRecipe(toolDrillObsidian, new Object[] { "   ", " I ", "   ", 'I', "toolDrillDiamond" }));
    }

    public static ItemBase itemMaterial;

    public static Item itemDrillIron;
    public static Item itemDrillDiamond;
    public static Item itemDrillObsidian;

    public static ItemStack drillBody;

    public static ItemStack drillHeadIron;
    public static ItemStack drillHeadDiamond;
    public static ItemStack drillHeadObsidian;

    public static ItemStack toolDrillIron;
    public static ItemStack toolDrillDiamond;
    public static ItemStack toolDrillObsidian;

    public static final Item.ToolMaterial TOOL_MATERIAL_OBSIDIAN = EnumHelper.addToolMaterial("OBSIDIAN", 3, 100, 10.0F, 0, 25);
        }
