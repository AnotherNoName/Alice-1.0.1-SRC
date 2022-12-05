/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class ConfigCommand
extends Command {
    public ConfigCommand() {
        super("config", new String[]{"<save/load>"});
    }

    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 1) {
            ConfigCommand.sendMessage("You`ll find the config files in your gameProfile directory under Alice/config");
            return;
        }
        if (arrstring.length == 2) {
            if ("list".equals(arrstring[0])) {
                String string = "Configs: ";
                File file2 = new File("Alice/");
                List list = Arrays.stream((Object[])Objects.requireNonNull(file2.listFiles())).filter(File::isDirectory).filter(file -> !file.getName().equals("util")).collect(Collectors.toList());
                StringBuilder stringBuilder = new StringBuilder(string);
                for (File file3 : list) {
                    stringBuilder.append(file3.getName()).append(", ");
                }
                string = String.valueOf(stringBuilder);
                ConfigCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7a").append(string)));
            } else {
                ConfigCommand.sendMessage("\u00a7cNot a valid command... Possible usage: <list>");
            }
        }
        if (arrstring.length >= 3) {
            switch (arrstring[0]) {
                case "save": {
                    AliceMain.configManager.saveConfig(arrstring[1]);
                    ConfigCommand.sendMessage("\u00a7aConfig has been saved.");
                    break;
                }
                case "load": {
                    AliceMain.moduleManager.onUnload();
                    AliceMain.configManager.loadConfig(arrstring[1]);
                    AliceMain.moduleManager.onLoad();
                    ConfigCommand.sendMessage("\u00a7aConfig has been loaded.");
                    break;
                }
                default: {
                    ConfigCommand.sendMessage("\u00a7cNot a valid command... Possible usage: <save/load>");
                }
            }
        }
    }
}

