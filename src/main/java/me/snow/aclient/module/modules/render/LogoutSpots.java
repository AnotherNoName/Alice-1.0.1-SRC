//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ConnectionEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LogoutSpots
extends Module {
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Boolean> scaleing;
    private final /* synthetic */ Setting<Float> scaling;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Float> factor;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Boolean> notification;
    private final /* synthetic */ List<LogoutPos> spots;
    public /* synthetic */ Setting<Boolean> message;
    private final /* synthetic */ Setting<Boolean> smartScale;
    private final /* synthetic */ Setting<Boolean> coords;
    private final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Boolean> rect;
    private final /* synthetic */ Setting<Boolean> colorSync;

    private void renderNameTag(String string, double d, double d2, double d3, float f, double d4, double d5, double d6) {
        double d7 = d2 + 0.7;
        Entity entity = mc.getRenderViewEntity();
        assert (entity != null);
        double d8 = entity.posX;
        double d9 = entity.posY;
        double d10 = entity.posZ;
        entity.posX = this.interpolate(entity.prevPosX, entity.posX, f);
        entity.posY = this.interpolate(entity.prevPosY, entity.posY, f);
        entity.posZ = this.interpolate(entity.prevPosZ, entity.posZ, f);
        String string2 = String.valueOf(new StringBuilder().append(string).append(" XYZ: ").append((int)d4).append(", ").append((int)d5).append(", ").append((int)d6));
        double d11 = entity.getDistance(d + LogoutSpots.mc.getRenderManager().viewerPosX, d7 + LogoutSpots.mc.getRenderManager().viewerPosY, d3 + LogoutSpots.mc.getRenderManager().viewerPosZ);
        int n = this.renderer.getStringWidth(string2) / 2;
        double d12 = (0.0018 + (double)this.scaling.getValue().floatValue() * (d11 * (double)this.factor.getValue().floatValue())) / 1000.0;
        if (d11 <= 8.0 && this.smartScale.getValue().booleanValue()) {
            d12 = 0.0245;
        }
        if (!this.scaleing.getValue().booleanValue()) {
            d12 = (double)this.scaling.getValue().floatValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset((float)1.0f, (float)-1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)((float)d), (float)((float)d7 + 1.4f), (float)((float)d3));
        GlStateManager.rotate((float)(-LogoutSpots.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)LogoutSpots.mc.getRenderManager().playerViewX, (float)(LogoutSpots.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((double)(-d12), (double)(-d12), (double)d12);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue().booleanValue()) {
            RenderUtil.drawRect(-n - 2, -(this.renderer.getFontHeight() + 1), (float)n + 2.0f, 1.5f, 0x55000000);
        }
        GlStateManager.disableBlend();
        this.renderer.drawStringWithShadow(string2, -n, -(this.renderer.getFontHeight() - 1), this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue())));
        entity.posX = d8;
        entity.posY = d9;
        entity.posZ = d10;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset((float)1.0f, (float)1500000.0f);
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (!this.spots.isEmpty()) {
            List<LogoutPos> list = this.spots;
            synchronized (list) {
                this.spots.forEach(logoutPos -> {
                    if (logoutPos.getEntity() != null) {
                        AxisAlignedBB axisAlignedBB = RenderUtil.interpolateAxis(logoutPos.getEntity().getEntityBoundingBox());
                        RenderUtil.drawBlockOutline(axisAlignedBB, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                        double d = this.interpolate(logoutPos.getEntity().lastTickPosX, logoutPos.getEntity().posX, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosX;
                        double d2 = this.interpolate(logoutPos.getEntity().lastTickPosY, logoutPos.getEntity().posY, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosY;
                        double d3 = this.interpolate(logoutPos.getEntity().lastTickPosZ, logoutPos.getEntity().posZ, render3DEvent.getPartialTicks()) - LogoutSpots.mc.getRenderManager().renderPosZ;
                        this.renderNameTag(logoutPos.getName(), d, d2, d3, render3DEvent.getPartialTicks(), logoutPos.getX(), logoutPos.getY(), logoutPos.getZ());
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public void onConnection(ConnectionEvent connectionEvent) {
        if (connectionEvent.getStage() == 0) {
            UUID uUID = connectionEvent.getUuid();
            EntityPlayer entityPlayer = LogoutSpots.mc.world.getPlayerEntityByUUID(uUID);
            if (entityPlayer != null && this.message.getValue().booleanValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7a").append(entityPlayer.getName()).append(" just logged in").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), this.notification.getValue());
            }
            this.spots.removeIf(logoutPos -> logoutPos.getName().equalsIgnoreCase(connectionEvent.getName()));
        } else if (connectionEvent.getStage() == 1) {
            EntityPlayer entityPlayer = connectionEvent.getEntity();
            UUID uUID = connectionEvent.getUuid();
            String string = connectionEvent.getName();
            if (this.message.getValue().booleanValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7c").append(connectionEvent.getName()).append(" just logged out").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), this.notification.getValue());
            }
            if (string != null && entityPlayer != null && uUID != null) {
                this.spots.add(new LogoutPos(string, uUID, entityPlayer));
            }
        }
    }

    private double interpolate(double d, double d2, float f) {
        return d + (d2 - d) * (double)f;
    }

    @Override
    public void onDisable() {
        this.spots.clear();
    }

    @Override
    public void onUpdate() {
        if (!LogoutSpots.fullNullCheck()) {
            this.spots.removeIf(logoutPos -> LogoutSpots.mc.player.getDistanceSqToEntity((Entity)logoutPos.getEntity()) >= MathUtil.square(this.range.getValue().floatValue()));
        }
    }

    public LogoutSpots() {
        super("LogoutSpots", "Renders LogoutSpots", Module.Category.RENDER, true, false, false);
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.red = this.register(new Setting<Integer>("Red", 255, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 0, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.scaleing = this.register(new Setting<Boolean>("Scale", false));
        this.scaling = this.register(new Setting<Float>("Size", Float.valueOf(4.0f), Float.valueOf(0.1f), Float.valueOf(20.0f)));
        this.factor = this.register(new Setting<Object>("Factor", Float.valueOf(0.3f), Float.valueOf(0.1f), Float.valueOf(1.0f), object -> this.scaleing.getValue()));
        this.smartScale = this.register(new Setting<Object>("SmartScale", Boolean.FALSE, object -> this.scaleing.getValue()));
        this.rect = this.register(new Setting<Boolean>("Rectangle", true));
        this.coords = this.register(new Setting<Boolean>("Coords", true));
        this.notification = this.register(new Setting<Boolean>("Notification", true));
        this.spots = new CopyOnWriteArrayList<LogoutPos>();
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(300.0f), Float.valueOf(50.0f), Float.valueOf(500.0f)));
        this.message = this.register(new Setting<Boolean>("Message", false));
    }

    @Override
    public void onLogout() {
        this.spots.clear();
    }

    private static class LogoutPos {
        private final /* synthetic */ UUID uuid;
        private final /* synthetic */ double x;
        private final /* synthetic */ String name;
        private final /* synthetic */ EntityPlayer entity;
        private final /* synthetic */ double z;
        private final /* synthetic */ double y;

        public double getX() {
            return this.x;
        }

        public double getZ() {
            return this.z;
        }

        public EntityPlayer getEntity() {
            return this.entity;
        }

        public LogoutPos(String string, UUID uUID, EntityPlayer entityPlayer) {
            this.name = string;
            this.uuid = uUID;
            this.entity = entityPlayer;
            this.x = entityPlayer.posX;
            this.y = entityPlayer.posY;
            this.z = entityPlayer.posZ;
        }

        public String getName() {
            return this.name;
        }

        public double getY() {
            return this.y;
        }

        public UUID getUuid() {
            return this.uuid;
        }
    }
}

