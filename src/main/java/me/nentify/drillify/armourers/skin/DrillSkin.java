package me.nentify.drillify.armourers.skin;

import me.nentify.drillify.Drillify;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

import java.util.ArrayList;

public class DrillSkin implements ISkinType {

    private ArrayList<ISkinPartType> parts;
    private int id;

    public DrillSkin() {
        parts = new ArrayList<ISkinPartType>();
        parts.add(new DrillSkinPartBase(this));
        parts.add(new DrillSkinPartHead(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return parts;
    }

    @Override
    public String getRegistryName() {
        return "drillify:drill";
    }

    @Override
    public String getName() {
        return getRegistryName();
    }

    @Override
    public void registerIcon(IIconRegister register) {

    }

    @Override
    public IIcon getIcon() {
        return null;
    }

    @Override
    public IIcon getEmptySlotIcon() {
        return null;
    }

    @Override
    public boolean showSkinOverlayCheckbox() {
        return false;
    }

    @Override
    public boolean showHelperCheckbox() {
        return false;
    }

    @Override
    public int getVanillaArmourSlotId() {
        return -1;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isHidden() {
        return false;
    }
}
