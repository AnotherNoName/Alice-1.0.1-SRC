/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.client;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.util.ColorUtil;

public class Colors
extends Module {
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> rainbowSaturation;
    public /* synthetic */ Setting<Integer> rainbowSpeed;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> green;
    public static /* synthetic */ Colors INSTANCE;
    public /* synthetic */ Setting<Integer> rainbowBrightness;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ float hue;
    public /* synthetic */ Map<Integer, Integer> colorHeightMap;

    public int getCurrentColorHex() {
        if (this.rainbow.getValue().booleanValue()) {
            return Color.HSBtoRGB(this.hue, (float)this.rainbowSaturation.getValue().intValue() / 255.0f, (float)this.rainbowBrightness.getValue().intValue() / 255.0f);
        }
        return ColorUtil.toARGB(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
    }

    @Override
    public void onTick() {
        int n = 101 - this.rainbowSpeed.getValue();
        float f = this.hue = (float)(System.currentTimeMillis() % (360L * (long)n)) / (360.0f * (float)n);
        for (int i = 0; i <= 510; ++i) {
            this.colorHeightMap.put(i, Color.HSBtoRGB(f, (float)this.rainbowSaturation.getValue().intValue() / 255.0f, (float)this.rainbowBrightness.getValue().intValue() / 255.0f));
            f += 0.0013071896f;
        }
        if (ClickGui.getInstance().colorSync.getValue().booleanValue()) {
            AliceMain.colorManager.setColor(INSTANCE.getCurrentColor().getRed(), INSTANCE.getCurrentColor().getGreen(), INSTANCE.getCurrentColor().getBlue(), ClickGui.getInstance().hoverAlpha.getValue());
        }
    }

    public Color getCurrentColor() {
        if (this.rainbow.getValue().booleanValue()) {
            return Color.getHSBColor(this.hue, (float)this.rainbowSaturation.getValue().intValue() / 255.0f, (float)this.rainbowBrightness.getValue().intValue() / 255.0f);
        }
        return new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static Colors getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Colors();
        }
        return INSTANCE;
    }

    public Colors() {
        super("Colors", "Universal colors.", Module.Category.CLIENT, true, false, true);
        this.rainbow = this.register(new Setting<Boolean>("Rainbow", Boolean.FALSE, "Rainbow colors."));
        this.rainbowSpeed = this.register(new Setting<Object>("Speed", Integer.valueOf(20), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.rainbowSaturation = this.register(new Setting<Object>("Saturation", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue()));
        this.rainbowBrightness = this.register(new Setting<Object>("Brightness", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue()));
        this.red = this.register(new Setting<Object>("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.green = this.register(new Setting<Object>("Green", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.blue = this.register(new Setting<Object>("Blue", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.alpha = this.register(new Setting<Object>("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.colorHeightMap = new HashMap<Integer, Integer>();
        INSTANCE = this;
    }
}

