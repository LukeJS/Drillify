package me.nentify.drillify.item.drill;

import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.IEqualityOverrideItem;
import cofh.core.item.tool.ItemToolAdv;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemDrill extends ItemToolAdv implements IEnergyContainerItem, IEqualityOverrideItem {

    public int maxEnergy = 120000;
    public int maxTransfer = 120;
    public int energyPerUse = 500;

    public ItemDrill(ToolMaterial toolMaterial) {
        super(2, toolMaterial);
        setNoRepair();

        addToolClass("pickaxe");

        effectiveBlocks.addAll(ItemPickaxe.field_150915_c);
        effectiveMaterials.add(Material.iron);
        effectiveMaterials.add(Material.anvil);
        effectiveMaterials.add(Material.rock);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }

        list.add(StringHelper.ORANGE + "Energy: " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }

        return maxEnergy - stack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return maxEnergy;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        extractEnergy(stack, energyPerUse, false);
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }

        if (stack.stackTagCompound.getInteger("Energy") < energyPerUse) {
            return 0.1F;
        }

        return super.getDigSpeed(stack, block, meta);
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        int energy = container.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(maxReceive, Math.min(maxEnergy - energy, maxTransfer));

        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        int energy = container.stackTagCompound.getInteger("Energy");
        int energyExtracted = Math.min(maxExtract, energy);

        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return maxEnergy;
    }

    @Override
    public boolean isLastHeldItemEqual(ItemStack current, ItemStack previous) {
        NBTTagCompound a = current.stackTagCompound;
        NBTTagCompound b = previous.stackTagCompound;

        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        a = (NBTTagCompound) a.copy();
        b = (NBTTagCompound) b.copy();

        a.removeTag("Energy");
        b.removeTag("Energy");

        return a.equals(b);
    }
}
