/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SkyColor
extends Module {
    private static /* synthetic */ SkyColor INSTANCE;
    private /* synthetic */ Setting<Integer> red;
    private /* synthetic */ Setting<Boolean> rainbow;
    private /* synthetic */ Setting<Integer> blue;
    private /* synthetic */ Setting<Integer> green;
    private /* synthetic */ Setting<Boolean> fog;

    @Override
    public void onUpdate() {
        if (this.rainbow.getValue().booleanValue()) {
            this.doRainbow();
        }
    }

    public static SkyColor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkyColor();
        }
        return INSTANCE;
    }

    public SkyColor() {
        super("SkyColor", "Changes the color of the sky", Module.Category.RENDER, false, false, false);
        this.red = this.register(new Setting<Integer>("Red", 135, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 0, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
        this.rainbow = this.register(new Setting<Boolean>("Rainbow", false));
        this.fog = this.register(new Setting<Boolean>("Fog", true));
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }

    static {
        INSTANCE = new SkyColor();
    }

    public void doRainbow() {
        float[] arrf = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0f};
        int n = Color.HSBtoRGB(arrf[0], 0.8f, 0.8f);
        this.red.setValue(n >> 16 & 0xFF);
        this.green.setValue(n >> 8 & 0xFF);
        this.blue.setValue(n & 0xFF);
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void fogColors(EntityViewRenderEvent.FogColors fogColors) {
        fogColors.setRed((float)this.red.getValue().intValue() / 255.0f);
        fogColors.setGreen((float)this.green.getValue().intValue() / 255.0f);
        fogColors.setBlue((float)this.blue.getValue().intValue() / 255.0f);
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void fog_density(EntityViewRenderEvent.FogDensity fogDensity) {
        if (this.fog.getValue().booleanValue()) {
            fogDensity.setDensity(0.0f);
            fogDensity.setCanceled(true);
        }
    }
}

