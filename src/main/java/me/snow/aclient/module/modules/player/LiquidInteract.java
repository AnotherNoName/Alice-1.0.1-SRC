/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.module.Module;

public class LiquidInteract
extends Module {
    private static /* synthetic */ LiquidInteract INSTANCE;

    public LiquidInteract() {
        super("LiquidInteract", "Interact with liquids", Module.Category.PLAYER, false, false, false);
        this.setInstance();
    }

    static {
        INSTANCE = new LiquidInteract();
    }

    public static LiquidInteract getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LiquidInteract();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

