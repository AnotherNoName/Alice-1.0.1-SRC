/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.client;

import me.snow.aclient.DiscordPresence;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class RPC
extends Module {
    public /* synthetic */ Setting<Boolean> showIP;
    public static /* synthetic */ RPC INSTANCE;
    public /* synthetic */ Setting<Boolean> catMode;
    public /* synthetic */ String state;

    @Override
    public void onEnable() {
        DiscordPresence.start();
    }

    public RPC() {
        super("RPC", "Discord rich presence", Module.Category.CLIENT, false, false, false);
        this.catMode = this.register(new Setting<Boolean>("CatMode", false));
        this.showIP = this.register(new Setting<Boolean>("ShowIP", Boolean.TRUE, "Shows the server IP in your discord presence."));
        this.state = "Alice - 1.0.1";
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}

