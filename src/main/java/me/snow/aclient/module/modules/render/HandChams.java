/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class HandChams
extends Module {
    public /* synthetic */ Setting<Integer> speed;
    public /* synthetic */ Setting<Boolean> rainbow;
    public static /* synthetic */ HandChams INSTANCE;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> brightness;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Integer> saturation;

    public HandChams() {
        super("HandChams", "Changes the color of your hands.", Module.Category.RENDER, false, false, false);
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.rainbow = this.register(new Setting<Boolean>("Rainbow", false));
        this.saturation = this.register(new Setting<Object>("Saturation", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.brightness = this.register(new Setting<Object>("Brightness", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.speed = this.register(new Setting<Object>("Speed", Integer.valueOf(40), Integer.valueOf(1), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.red = this.register(new Setting<Object>("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.green = this.register(new Setting<Object>("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.blue = this.register(new Setting<Object>("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        INSTANCE = this;
    }
}

