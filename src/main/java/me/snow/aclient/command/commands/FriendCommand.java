/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import java.util.Map;
import java.util.UUID;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class FriendCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 1) {
            if (AliceMain.friendManager.getFriends().isEmpty()) {
                FriendCommand.sendMessage("You currently don't have any friends added.");
            } else {
                FriendCommand.sendMessage("Friends: ");
                for (Map.Entry<String, UUID> entry : AliceMain.friendManager.getFriends().entrySet()) {
                    FriendCommand.sendMessage(entry.getKey());
                }
            }
            return;
        }
        if (arrstring.length == 2) {
            if ("reset".equals(arrstring[0])) {
                AliceMain.friendManager.onLoad();
                FriendCommand.sendMessage("Friends got reset.");
            } else {
                FriendCommand.sendMessage(String.valueOf(new StringBuilder().append(arrstring[0]).append(AliceMain.friendManager.isFriend(arrstring[0]) ? " is friended." : " isnt friended.")));
            }
            return;
        }
        if (arrstring.length >= 2) {
            switch (arrstring[0]) {
                case "add": {
                    AliceMain.friendManager.addFriend(arrstring[1]);
                    FriendCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7b").append(arrstring[1]).append(" has been friended")));
                    break;
                }
                case "del": {
                    AliceMain.friendManager.removeFriend(arrstring[1]);
                    FriendCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7c").append(arrstring[1]).append(" has been unfriended")));
                    break;
                }
                default: {
                    FriendCommand.sendMessage("\u00a7cBad Command, try: friend <add/del/name> <name>.");
                }
            }
        }
    }

    public FriendCommand() {
        super("friend", new String[]{"<add/del/name/clear>", "<name>"});
    }
}

