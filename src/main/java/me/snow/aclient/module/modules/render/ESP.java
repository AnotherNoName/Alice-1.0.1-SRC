//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.item.EntityExpBottle
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class ESP
extends Module {
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Boolean> xpbottles;
    private final /* synthetic */ Setting<Boolean> xporbs;
    private static /* synthetic */ ESP INSTANCE;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Boolean> items;
    private final /* synthetic */ Setting<Boolean> pearl;
    private final /* synthetic */ Setting<Boolean> colorsync;

    private void setInstance() {
        INSTANCE = this;
    }

    public static ESP getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ESP();
        }
        return INSTANCE;
    }

    public ESP() {
        super("ESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.items = this.register(new Setting<Boolean>("Items", false));
        this.xporbs = this.register(new Setting<Boolean>("XpOrbs", false));
        this.xpbottles = this.register(new Setting<Boolean>("XpBottles", false));
        this.pearl = this.register(new Setting<Boolean>("Pearls", false));
        this.colorsync = this.register(new Setting<Boolean>("ColorSync", true));
        this.red = this.register(new Setting<Integer>("Red", 255, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
        this.boxAlpha = this.register(new Setting<Integer>("BoxAlpha", 120, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.setInstance();
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        AxisAlignedBB axisAlignedBB;
        Vec3d vec3d;
        int n;
        Color color;
        Color color2 = color = this.colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
        if (this.items.getValue().booleanValue()) {
            n = 0;
            for (Entity entity : ESP.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityItem) || !(ESP.mc.player.getDistanceSqToEntity(entity) < 2500.0)) continue;
                vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.getRenderPartialTicks());
                axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + vec3d.zCoord, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + vec3d.zCoord);
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask((boolean)false);
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
                GL11.glLineWidth((float)1.0f);
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)this.boxAlpha.getValue().intValue() / 255.0f));
                GL11.glDisable((int)2848);
                GlStateManager.depthMask((boolean)true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                RenderUtil.drawBlockOutline(axisAlignedBB, new Color(color.getRed(), color.getGreen(), color.getBlue(), this.alpha.getValue()), 1.0f);
                if (++n < 50) continue;
            }
        }
        if (this.xporbs.getValue().booleanValue()) {
            n = 0;
            for (Entity entity : ESP.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityXPOrb) || !(ESP.mc.player.getDistanceSqToEntity(entity) < 2500.0)) continue;
                vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.getRenderPartialTicks());
                axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + vec3d.zCoord, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + vec3d.zCoord);
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask((boolean)false);
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
                GL11.glLineWidth((float)1.0f);
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)this.boxAlpha.getValue().intValue() / 255.0f));
                GL11.glDisable((int)2848);
                GlStateManager.depthMask((boolean)true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                RenderUtil.drawBlockOutline(axisAlignedBB, new Color(color.getRed(), color.getGreen(), color.getBlue(), this.alpha.getValue()), 1.0f);
                if (++n < 50) continue;
            }
        }
        if (this.pearl.getValue().booleanValue()) {
            n = 0;
            for (Entity entity : ESP.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderPearl) || !(ESP.mc.player.getDistanceSqToEntity(entity) < 2500.0)) continue;
                vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.getRenderPartialTicks());
                axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + vec3d.zCoord, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + vec3d.zCoord);
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask((boolean)false);
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
                GL11.glLineWidth((float)1.0f);
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)this.boxAlpha.getValue().intValue() / 255.0f));
                GL11.glDisable((int)2848);
                GlStateManager.depthMask((boolean)true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                RenderUtil.drawBlockOutline(axisAlignedBB, new Color(color.getRed(), color.getGreen(), color.getBlue(), this.alpha.getValue()), 1.0f);
                if (++n < 50) continue;
            }
        }
        if (this.xpbottles.getValue().booleanValue()) {
            n = 0;
            for (Entity entity : ESP.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityExpBottle) || !(ESP.mc.player.getDistanceSqToEntity(entity) < 2500.0)) continue;
                vec3d = EntityUtil.getInterpolatedRenderPos(entity, mc.getRenderPartialTicks());
                axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + vec3d.zCoord, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + vec3d.xCoord, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + vec3d.yCoord, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + vec3d.zCoord);
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask((boolean)false);
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
                GL11.glLineWidth((float)1.0f);
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)this.boxAlpha.getValue().intValue() / 255.0f));
                GL11.glDisable((int)2848);
                GlStateManager.depthMask((boolean)true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
                RenderUtil.drawBlockOutline(axisAlignedBB, new Color(color.getRed(), color.getGreen(), color.getBlue(), this.alpha.getValue()), 1.0f);
                if (++n < 50) continue;
            }
        }
    }

    static {
        INSTANCE = new ESP();
    }
}

