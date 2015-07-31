package me.nentify.drillify;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemDrill extends ItemPickaxe implements IEnergyContainerItem {

    public int maxEnergy = 15000;
    public int maxTransfer = 1000;
    public int energyPerUse = 500;

    int damage = 0;

    public ItemDrill() {
        super(Item.ToolMaterial.IRON);
        setUnlocalizedName("ItemDrill");
        setMaxDamage(100);
        setNoRepair();
    }

    // TEMP
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        receiveEnergy(stack, 500, false);
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }

        list.add(StringHelper.ORANGE + "Energy: " + stack.stackTagCompound.getInteger("energy") + " / " + maxEnergy + " RF");
        list.add("Damage: " + stack.getItemDamage());
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        extractEnergy(stack, energyPerUse, false);
        return true;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if (stack.stackTagCompound.getInteger("energy") < energyPerUse) {
            return 0.1F;
        } else {
            return ToolMaterial.EMERALD.getEfficiencyOnProperMaterial();
        }
    }

    public int energyToPercentage(int energy) {
        double modifier = (double) 100 / maxEnergy;
        int percentage = (int)(100 - (energy * modifier));
        return percentage;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        int energy = container.stackTagCompound.getInteger("energy");
        int energyReceived = Math.min(maxReceive, Math.min(maxEnergy - energy, maxTransfer));

        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("energy", energy);
            container.setItemDamage(energyToPercentage(energy));
        }

        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        int energy = container.stackTagCompound.getInteger("energy");
        int energyExtracted = Math.min(maxExtract, energy);

        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("energy", energy);
            container.setItemDamage(energyToPercentage(energy));
        }

        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }

        return container.stackTagCompound.getInteger("energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return maxEnergy;
    }
}
