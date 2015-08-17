package me.nentify.drillify.armourers.skin;

import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public class DrillSkinPartHead implements ISkinPartType {

    private final ISkinType parent;
    private Rectangle3D buildingSpace;
    private final Rectangle3D guideSpace;
    private Point3D offset;

    public DrillSkinPartHead(ISkinType parent) {
        this.parent = parent;
        this.buildingSpace = new Rectangle3D(-7, -7, -14, 14, 14, 14);
        this.guideSpace = new Rectangle3D(0, 0, 0, 0, 0, 0);
        this.offset = new Point3D(0, 0, -12);
    }

    @Override
    public String getRegistryName() {
        return parent.getRegistryName() + "." + getPartName();
    }

    @Override
    public String getPartName() {
        return "head";
    }

    @Override
    public IRectangle3D getBuildingSpace() {
        return buildingSpace;
    }

    @Override
    public IRectangle3D getGuideSpace() {
        return guideSpace;
    }

    @Override
    public IPoint3D getOffset() {
        return offset;
    }

    @Override
    public void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper) {

    }

    @Override
    public int getMinimumMarkersNeeded() {
        return 1;
    }

    @Override
    public int getMaximumMarkersNeeded() {
        return 1;
    }
}
