/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class TpsSync
extends Module {
    public /* synthetic */ Setting<Boolean> mining;
    public /* synthetic */ Setting<Boolean> attack;
    private static /* synthetic */ TpsSync INSTANCE;

    static {
        INSTANCE = new TpsSync();
    }

    public static TpsSync getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TpsSync();
        }
        return INSTANCE;
    }

    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS.", Module.Category.PLAYER, true, false, false);
        this.mining = this.register(new Setting<Boolean>("Mining", true));
        this.attack = this.register(new Setting<Boolean>("Attack", false));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

