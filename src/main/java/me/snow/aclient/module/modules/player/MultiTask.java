/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.module.Module;

public class MultiTask
extends Module {
    private static /* synthetic */ MultiTask INSTANCE;

    static {
        INSTANCE = new MultiTask();
    }

    public MultiTask() {
        super("MultiTask", "Allows you to eat while mining.", Module.Category.PLAYER, false, false, false);
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static MultiTask getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MultiTask();
        }
        return INSTANCE;
    }
}

