/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import java.util.ArrayList;
import java.util.LinkedList;
import me.snow.aclient.command.Command;
import me.snow.aclient.command.commands.BaritoneNoStop;
import me.snow.aclient.command.commands.BindCommand;
import me.snow.aclient.command.commands.BookCommand;
import me.snow.aclient.command.commands.ClearRamCommand;
import me.snow.aclient.command.commands.ConfigCommand;
import me.snow.aclient.command.commands.CoordsCommand;
import me.snow.aclient.command.commands.CrashCommand;
import me.snow.aclient.command.commands.FriendCommand;
import me.snow.aclient.command.commands.HelpCommand;
import me.snow.aclient.command.commands.HistoryCommand;
import me.snow.aclient.command.commands.ModuleCommand;
import me.snow.aclient.command.commands.OpenFolderCommand;
import me.snow.aclient.command.commands.PeekCommand;
import me.snow.aclient.command.commands.PrefixCommand;
import me.snow.aclient.command.commands.QueueCommand;
import me.snow.aclient.command.commands.ReloadCommand;
import me.snow.aclient.command.commands.ReloadSoundCommand;
import me.snow.aclient.command.commands.UnloadCommand;
import me.snow.aclient.module.Feature;

public class CommandManager
extends Feature {
    private /* synthetic */ String clientMessage;
    private final /* synthetic */ ArrayList<Command> commands;
    private /* synthetic */ String prefix;

    public static String[] removeElement(String[] arrstring, int n) {
        LinkedList<String> linkedList = new LinkedList<String>();
        for (int i = 0; i < arrstring.length; ++i) {
            if (i == n) continue;
            linkedList.add(arrstring[i]);
        }
        return linkedList.toArray(arrstring);
    }

    public void executeCommand(String string) {
        String[] arrstring = string.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String string2 = arrstring[0].substring(1);
        String[] arrstring2 = CommandManager.removeElement(arrstring, 0);
        for (int i = 0; i < arrstring2.length; ++i) {
            if (arrstring2[i] == null) continue;
            arrstring2[i] = CommandManager.strip(arrstring2[i]);
        }
        for (Command command : this.commands) {
            if (!command.getName().equalsIgnoreCase(string2)) continue;
            command.execute(arrstring);
            return;
        }
        Command.sendMessage("Unknown command. try 'help' for a list of commands.");
    }

    public CommandManager() {
        super("Command");
        this.commands = new ArrayList();
        this.clientMessage = "<AliceClient.jp>";
        this.prefix = ".";
        this.commands.add(new BindCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new OpenFolderCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new ConfigCommand());
        this.commands.add(new CoordsCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new UnloadCommand());
        this.commands.add(new ReloadSoundCommand());
        this.commands.add(new PeekCommand());
        this.commands.add(new BookCommand());
        this.commands.add(new ClearRamCommand());
        this.commands.add(new CrashCommand());
        this.commands.add(new HistoryCommand());
        this.commands.add(new BaritoneNoStop());
        this.commands.add(new QueueCommand());
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setClientMessage(String string) {
        this.clientMessage = string;
    }

    public Command getCommandByName(String string) {
        for (Command command : this.commands) {
            if (!command.getName().equals(string)) continue;
            return command;
        }
        return null;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    private static String strip(String string) {
        if (string.startsWith("\"") && string.endsWith("\"")) {
            return string.substring("\"".length(), string.length() - "\"".length());
        }
        return string;
    }

    public void setPrefix(String string) {
        this.prefix = string;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }
}

