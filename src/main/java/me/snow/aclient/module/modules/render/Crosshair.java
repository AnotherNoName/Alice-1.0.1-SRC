//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Crosshair
extends Module {
    private final /* synthetic */ Setting<Integer> cred;
    /* synthetic */ float currentMotion;
    private final /* synthetic */ Setting<Float> crosshairGap;
    private final /* synthetic */ Setting<Boolean> dot;
    private final /* synthetic */ Setting<Float> motionWidth;
    private final /* synthetic */ Setting<Float> crosshairWidth;
    private final /* synthetic */ Setting<Float> crosshairSize;
    private final /* synthetic */ Setting<Float> motionSize;
    private final /* synthetic */ Setting<Float> motionGap;
    private final /* synthetic */ Setting<Integer> cblue;
    /* synthetic */ long lastUpdate;
    private final /* synthetic */ Setting<Integer> calpha;
    /* synthetic */ float prevMotion;
    private final /* synthetic */ Setting<Integer> cgreen;
    private final /* synthetic */ Setting<Boolean> colorSync;

    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        float f5;
        if (f < f3) {
            f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f6, (float)f7, (float)f8, (float)f5);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)f, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.prevMotion = this.currentMotion;
        double d = Crosshair.mc.player.posX - Crosshair.mc.player.prevPosX;
        double d2 = Crosshair.mc.player.posZ - Crosshair.mc.player.prevPosZ;
        this.currentMotion = (float)Math.sqrt(d * d + d2 * d2);
        this.lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onRender2D(Render2DEvent render2DEvent) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        float f = (float)(scaledResolution.getScaledWidth_double() / 2.0 + 0.5);
        float f2 = (float)(scaledResolution.getScaledHeight_double() / 2.0 + 0.5);
        float f3 = this.crosshairGap.getValue().floatValue();
        float f4 = Math.max(this.crosshairWidth.getValue().floatValue(), 0.5f);
        float f5 = this.crosshairSize.getValue().floatValue();
        float f6 = Crosshair.mc.timer.field_194149_e;
        Crosshair.drawRect(f - (f3 += Crosshair.lerp(this.prevMotion, this.currentMotion, Math.min((float)(System.currentTimeMillis() - this.lastUpdate) / f6, 1.0f)) * this.motionGap.getValue().floatValue()) - (f5 += Crosshair.lerp(this.prevMotion, this.currentMotion, Math.min((float)(System.currentTimeMillis() - this.lastUpdate) / f6, 1.0f)) * this.motionSize.getValue().floatValue()), f2 - (f4 += Crosshair.lerp(this.prevMotion, this.currentMotion, Math.min((float)(System.currentTimeMillis() - this.lastUpdate) / f6, 1.0f)) * this.motionWidth.getValue().floatValue()) / 2.0f, f - f3, f2 + f4 / 2.0f, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.cred.getValue(), this.cgreen.getValue(), this.cblue.getValue(), this.calpha.getValue()));
        Crosshair.drawRect(f + f3 + f5, f2 - f4 / 2.0f, f + f3, f2 + f4 / 2.0f, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.cred.getValue(), this.cgreen.getValue(), this.cblue.getValue(), this.calpha.getValue()));
        Crosshair.drawRect(f - f4 / 2.0f, f2 + f3 + f5, f + f4 / 2.0f, f2 + f3, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.cred.getValue(), this.cgreen.getValue(), this.cblue.getValue(), this.calpha.getValue()));
        Crosshair.drawRect(f - f4 / 2.0f, f2 - f3 - f5, f + f4 / 2.0f, f2 - f3, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.cred.getValue(), this.cgreen.getValue(), this.cblue.getValue(), this.calpha.getValue()));
        if (this.dot.getValue().booleanValue()) {
            Crosshair.drawRect(f - f4 / 2.0f, f2 - f4 / 2.0f, f + f4 / 2.0f, f2 + f4 / 2.0f, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.cred.getValue(), this.cgreen.getValue(), this.cblue.getValue(), this.calpha.getValue()));
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent renderGameOverlayEvent) {
        if (this.megaNullCheck()) {
            return;
        }
        if (renderGameOverlayEvent.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            renderGameOverlayEvent.setCanceled(true);
        }
    }

    public static float lerp(float f, float f2, float f3) {
        return f * (1.0f - f3) + f2 * f3;
    }

    public Crosshair() {
        super("Crosshair", "Draws a custom crosshair", Module.Category.RENDER, true, false, false);
        this.dot = this.register(new Setting<Boolean>("Dot", false));
        this.crosshairGap = this.register(new Setting<Float>("Gap", Float.valueOf(2.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.motionGap = this.register(new Setting<Float>("MotionGap", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(5.0f)));
        this.crosshairWidth = this.register(new Setting<Float>("Width", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
        this.motionWidth = this.register(new Setting<Float>("MotionWidth", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(2.5f)));
        this.crosshairSize = this.register(new Setting<Float>("Size", Float.valueOf(2.0f), Float.valueOf(0.1f), Float.valueOf(40.0f)));
        this.motionSize = this.register(new Setting<Float>("MotionSize", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.cred = this.register(new Setting<Integer>("Red", 0, 0, 255));
        this.cgreen = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.cblue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
        this.calpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.currentMotion = 0.0f;
        this.lastUpdate = -1L;
        this.prevMotion = 0.0f;
    }
}

