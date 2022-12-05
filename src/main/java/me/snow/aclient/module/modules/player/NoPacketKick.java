/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import me.snow.aclient.command.Command;
import me.snow.aclient.module.Module;
import org.jetbrains.annotations.NotNull;

public class NoPacketKick
extends Module {
    private static /* synthetic */ NoPacketKick INSTANCE;

    @JvmStatic
    public static final void sendWarning(@NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Command.sendMessage(String.valueOf(new StringBuilder().append(" Caught exception - \"").append(throwable).append("\" check log for more info.")));
        throwable.printStackTrace();
    }

    public static NoPacketKick getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoPacketKick();
        }
        return INSTANCE;
    }

    public NoPacketKick() {
        super("NoPacketKick", "Suppress network exceptions and prevent getting kicked", Module.Category.PLAYER, true, false, false);
    }
}

