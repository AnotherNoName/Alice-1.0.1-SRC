/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class Reach
extends Module {
    public /* synthetic */ Setting<Boolean> override;
    public /* synthetic */ Setting<Float> add;
    private static /* synthetic */ Reach INSTANCE;
    public /* synthetic */ Setting<Float> reach;

    public Reach() {
        super("Reach", "Extends your block reach", Module.Category.PLAYER, true, false, false);
        this.override = this.register(new Setting<Boolean>("Override", false));
        this.add = this.register(new Setting<Object>("Add", Float.valueOf(3.0f), object -> this.override.getValue() == false));
        this.reach = this.register(new Setting<Object>("Reach", Float.valueOf(6.0f), object -> this.override.getValue()));
        this.setInstance();
    }

    public static Reach getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Reach();
        }
        return INSTANCE;
    }

    @Override
    public String getDisplayInfo() {
        return this.override.getValue() != false ? this.reach.getValue().toString() : this.add.getValue().toString();
    }

    static {
        INSTANCE = new Reach();
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

