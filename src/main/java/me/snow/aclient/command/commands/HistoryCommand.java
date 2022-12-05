/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import java.util.List;
import java.util.UUID;
import me.snow.aclient.command.Command;
import me.snow.aclient.util.PlayerUtil;

public class HistoryCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        List<String> list;
        UUID uUID;
        if (arrstring.length == 1 || arrstring.length == 0) {
            HistoryCommand.sendMessage("\u00a7cPlease specify a player.");
        }
        try {
            uUID = PlayerUtil.getUUIDFromName(arrstring[0]);
        }
        catch (Exception exception) {
            HistoryCommand.sendMessage("An error occured.");
            return;
        }
        try {
            list = PlayerUtil.getHistoryOfNames(uUID);
        }
        catch (Exception exception) {
            HistoryCommand.sendMessage("An error occured.");
            return;
        }
        if (list != null) {
            HistoryCommand.sendMessage(String.valueOf(new StringBuilder().append(arrstring[0]).append("\u00b4s name history:")));
            for (String string : list) {
                HistoryCommand.sendMessage(string);
            }
        } else {
            HistoryCommand.sendMessage("No names found.");
        }
    }

    public HistoryCommand() {
        super("history", new String[]{"<player>"});
    }
}

