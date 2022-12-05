/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class HelpCommand
extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] arrstring) {
        HelpCommand.sendMessage("You can use following commands: ");
        for (Command command : AliceMain.commandManager.getCommands()) {
            HelpCommand.sendMessage(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getPrefix()).append(command.getName())));
        }
    }
}

