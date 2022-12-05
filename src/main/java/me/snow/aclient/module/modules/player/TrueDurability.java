/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.module.Module;

public class TrueDurability
extends Module {
    private static /* synthetic */ TrueDurability instance;

    public TrueDurability() {
        super("TrueDurability", "Shows True Durability of items", Module.Category.PLAYER, false, false, false);
        instance = this;
    }

    public static TrueDurability getInstance() {
        if (instance == null) {
            instance = new TrueDurability();
        }
        return instance;
    }
}

