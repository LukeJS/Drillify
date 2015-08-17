package me.nentify.drillify.armourers;

import riskyken.armourersWorkshop.api.client.IArmourersClientManager;
import riskyken.armourersWorkshop.api.client.render.ISkinRenderHandler;

public class ArmourersClientManager implements IArmourersClientManager {

    public static ArmourersClientManager instance;

    public ISkinRenderHandler renderHandler;

    public ArmourersClientManager() {
        this.instance = this;
    }

    @Override
    public void onLoad(ISkinRenderHandler renderHandler) {
        this.renderHandler = renderHandler;
    }
}
