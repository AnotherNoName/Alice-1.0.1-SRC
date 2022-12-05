/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.module.modules.client.ClickGui;

public class PrefixCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 1) {
            Command.sendMessage("\u00a7cSpecify a new prefix.");
            return;
        }
        AliceMain.moduleManager.getModuleByClass(ClickGui.class).prefix.setValue(arrstring[0]);
        Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix set to \u00a7a").append(AliceMain.commandManager.getPrefix())));
    }

    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }
}

