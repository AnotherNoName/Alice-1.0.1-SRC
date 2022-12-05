/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class ReloadCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        AliceMain.reload();
    }

    public ReloadCommand() {
        super("reload", new String[0]);
    }
}

