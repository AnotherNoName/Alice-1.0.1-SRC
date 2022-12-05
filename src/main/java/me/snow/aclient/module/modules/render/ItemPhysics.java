/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class ItemPhysics
extends Module {
    public final /* synthetic */ Setting<Float> Scaling;
    public static /* synthetic */ ItemPhysics INSTANCE;

    private void setInstance() {
        INSTANCE = this;
    }

    static {
        INSTANCE = new ItemPhysics();
    }

    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
        this.Scaling = this.register(new Setting<Float>("Scaling", Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.setInstance();
    }

    public static ItemPhysics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemPhysics();
        }
        return INSTANCE;
    }
}

