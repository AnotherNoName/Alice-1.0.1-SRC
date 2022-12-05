/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.command.Command;

public class ClearRamCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        System.gc();
        Command.sendMessage("Finished clearing the ram.", false);
    }

    public ClearRamCommand() {
        super("clearram");
    }
}

