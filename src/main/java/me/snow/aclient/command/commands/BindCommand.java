/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand
extends Command {
    public BindCommand() {
        super("bind", new String[]{"<module>", "<bind>"});
    }

    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 1) {
            BindCommand.sendMessage("Please specify a module.");
            return;
        }
        String string = arrstring[1];
        String string2 = arrstring[0];
        Module module = ModuleManager.getModuleByName(string2);
        if (module == null) {
            BindCommand.sendMessage(String.valueOf(new StringBuilder().append("Unknown module '").append(module).append("'!")));
            return;
        }
        if (string == null) {
            BindCommand.sendMessage(String.valueOf(new StringBuilder().append(module.getName()).append(" is bound to &b").append(module.getBind().toString())));
            return;
        }
        int n = Keyboard.getKeyIndex((String)string.toUpperCase());
        if (string.equalsIgnoreCase("none")) {
            n = -1;
        }
        if (n == 0) {
            BindCommand.sendMessage(String.valueOf(new StringBuilder().append("Unknown key '").append(string).append("'!")));
            return;
        }
        module.bind.setValue(new Bind(n));
        BindCommand.sendMessage(String.valueOf(new StringBuilder().append("Bind for &b").append(module.getName()).append("&r set to &b").append(string.toUpperCase())));
    }
}

