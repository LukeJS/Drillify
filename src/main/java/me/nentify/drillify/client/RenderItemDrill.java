package me.nentify.drillify.client;

import me.nentify.drillify.Drillify;
import me.nentify.drillify.armourers.ArmourersClientManager;
import me.nentify.drillify.armourers.ArmourersCommonManager;
import me.nentify.drillify.item.DrillifyItems;
import me.nentify.drillify.item.drill.ItemDrill;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.client.render.ISkinRenderHandler;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public class RenderItemDrill implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) {
        return type == ItemRenderType.INVENTORY | type == ItemRenderType.ENTITY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        ISkinRenderHandler renderer = ArmourersClientManager.instance.renderHandler;

        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPopMatrix();
            GL11.glRotatef(-135, 0, 1, 0);
            GL11.glRotatef(-10, 0, 0, 1);
        }
        GL11.glPushMatrix();

        GL11.glScalef(1F, -1F, 1F);
        GL11.glScalef(1.6F, 1.6F, 1.6F);

        boolean isBlocking = false;

        if (data.length >= 2) {
            if (data[1] instanceof AbstractClientPlayer & data[0] instanceof RenderBlocks) {
                RenderBlocks renderBlocks = (RenderBlocks) data[0];
                AbstractClientPlayer player = (AbstractClientPlayer) data[1];
                isBlocking = player.isBlocking();
            }
        }

        float scale = 0.0625F;

        switch (type) {
            case EQUIPPED:
                GL11.glTranslatef(2F * scale, -1F * scale, 0F * scale);
                if (isBlocking) {
                    GL11.glTranslatef(-0F * scale, 2F * scale, 1F * scale);
                }
                GL11.glRotatef(90F, 0F, 1F, 0F);
                break;
            case ENTITY:
                GL11.glScalef(-1F, 1F, 1F);
                GL11.glTranslatef(0F, -8F * scale, 0F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glScalef(-1F, 1F, 1F);
                GL11.glTranslatef(-9F * scale, -1F * scale, 1F * scale);
                GL11.glRotatef(90F, 0F, 1F, 0F);
                GL11.glRotatef(-20F, 1F, 0F, 0F);
                GL11.glRotatef(-4F, 0F, 0F, 1F);
                break;
            case INVENTORY:
                GL11.glTranslatef(1F * scale, 0F * scale, -3F * scale);
                GL11.glRotated(180, 0, 1, 0);
                GL11.glScalef(-1F, 1F, 1F);
                break;
            default:
                break;
        }

        ISkinPointer sp = ArmourersCommonManager.instance.drillSkinIron;
        if (stack.getItem() == DrillifyItems.itemDrillDiamond) {
            sp = ArmourersCommonManager.instance.drillSkinDiamond;
        }
        if (stack.getItem() == DrillifyItems.itemDrillObsidian) {
            sp = ArmourersCommonManager.instance.drillSkinObsidian;
        }

        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        ISkinType st = ArmourersCommonManager.instance.drillSkin;

        //TODO Replace test render.
        renderer.renderSkinPart(sp, st.getSkinParts().get(0));
        GL11.glPushMatrix();
        float rotation = (float)((double)System.currentTimeMillis() / 3 % 360);

        GL11.glTranslated(0, 0, -8 * scale);

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            EntityLivingBase player = (EntityLivingBase) data[1];

            if (ItemDrill.rotationTimer.containsKey(player.getCommandSenderName())) {
                GL11.glRotatef(rotation, 0, 0, 1);

                Integer time = ItemDrill.rotationTimer.get(player.getCommandSenderName());
                time--;

                if (time > 0) {
                    ItemDrill.rotationTimer.put(player.getCommandSenderName(), time);
                } else {
                    ItemDrill.rotationTimer.remove(player.getCommandSenderName());
                }
            }
        }

        renderer.renderSkinPart(sp, st.getSkinParts().get(1));

        GL11.glPopMatrix();
        GL11.glPopAttrib();

        GL11.glPopMatrix();
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
        }
    }
}
