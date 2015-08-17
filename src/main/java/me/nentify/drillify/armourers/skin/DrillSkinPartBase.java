package me.nentify.drillify.armourers.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.nentify.drillify.armourers.ArmourersClientManager;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public class DrillSkinPartBase implements ISkinPartType {

    private final ISkinType parent;
    private Rectangle3D buildingSpace;
    private Rectangle3D guideSpace;
    private Point3D offset;

    public DrillSkinPartBase(ISkinType parent) {
        this.parent = parent;
        this.buildingSpace = new Rectangle3D(-7, -7, -10, 14, 14, 20);
        this.guideSpace = new Rectangle3D(-2, -2, 2, 4, 4, 8);
        this.offset = new Point3D(0, 0, 0);
    }

    @Override
    public String getRegistryName() {
        return parent.getRegistryName() + "." + getPartName();
    }

    @Override
    public String getPartName() {
        return "base";
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

    @SideOnly(Side.CLIENT)
    @Override
    public void renderBuildingGuide(float scale, boolean showSkinOverlay, boolean showHelper) {
        GL11.glTranslated(0, this.buildingSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.guideSpace.getY() * scale, 0);
        ArmourersClientManager.instance.renderHandler.getArmourerHandModel().render(null, scale, 0F, 0F, 0F, 0F, 0F);
        GL11.glTranslated(0, this.guideSpace.getY() * scale, 0);
        GL11.glTranslated(0, -this.buildingSpace.getY() * scale, 0);
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
