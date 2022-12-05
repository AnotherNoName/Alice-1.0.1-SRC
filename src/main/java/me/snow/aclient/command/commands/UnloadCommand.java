/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class UnloadCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        AliceMain.unload(true);
    }

    public UnloadCommand() {
        super("unload", new String[0]);
    }
}

