/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class ViewClip
extends Module {
    public /* synthetic */ Setting<Boolean> extend;
    private static /* synthetic */ ViewClip INSTANCE;
    public /* synthetic */ Setting<Double> distance;

    public ViewClip() {
        super("ViewClip", "Makes your Camera clip.", Module.Category.RENDER, false, false, false);
        this.extend = this.register(new Setting<Boolean>("Extend", true));
        this.distance = this.register(new Setting<Object>("Distance", 3.2, 0.0, 50.0, object -> this.extend.getValue(), "By how much you want to extend the distance."));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static ViewClip getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewClip();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new ViewClip();
    }
}

