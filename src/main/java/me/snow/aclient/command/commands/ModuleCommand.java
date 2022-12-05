/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonParser
 */
package me.snow.aclient.command.commands;

import com.google.gson.JsonParser;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.manager.ConfigManager;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.module.Module;

public class ModuleCommand
extends Command {
    public ModuleCommand() {
        super("module", new String[]{"<module>", "<set/reset>", "<setting>", "<value>"});
    }

    @Override
    public void execute(String[] arrstring) {
        Setting setting;
        if (arrstring.length == 1) {
            ModuleCommand.sendMessage("Modules: ");
            for (Module.Category category : AliceMain.moduleManager.getCategories()) {
                StringBuilder stringBuilder = new StringBuilder(String.valueOf(new StringBuilder().append(category.getName()).append(": ")));
                for (Module module : AliceMain.moduleManager.getModulesByCategory(category)) {
                    stringBuilder.append(module.isEnabled() ? "\u00a7a" : "\u00a7c").append(module.getName()).append("\u00a7r").append(", ");
                }
                ModuleCommand.sendMessage(String.valueOf(stringBuilder));
            }
            return;
        }
        Module module = AliceMain.moduleManager.getModuleByDisplayName(arrstring[0]);
        if (module == null) {
            module = ModuleManager.getModuleByName(arrstring[0]);
            if (module == null) {
                ModuleCommand.sendMessage("\u00a7cThis module doesnt exist.");
                return;
            }
            ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7c This is the original name of the module. Its current name is: ").append(module.getDisplayName())));
            return;
        }
        if (arrstring.length == 2) {
            ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append(module.getDisplayName()).append(" : ").append(module.getDescription())));
            for (Setting setting2 : module.getSettings()) {
                ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append(setting2.getName()).append(" : ").append(setting2.getValue()).append(", ").append(setting2.getDescription())));
            }
            return;
        }
        if (arrstring.length == 3) {
            if (arrstring[1].equalsIgnoreCase("set")) {
                ModuleCommand.sendMessage("\u00a7cPlease specify a setting.");
            } else if (arrstring[1].equalsIgnoreCase("reset")) {
                for (Setting setting3 : module.getSettings()) {
                    setting3.setValue(setting3.getDefaultValue());
                }
            } else {
                ModuleCommand.sendMessage("\u00a7cThis command doesnt exist.");
            }
            return;
        }
        if (arrstring.length == 4) {
            ModuleCommand.sendMessage("\u00a7cPlease specify a value.");
            return;
        }
        if (arrstring.length == 5 && (setting = module.getSettingByName(arrstring[2])) != null) {
            JsonParser jsonParser = new JsonParser();
            if (setting.getType().equalsIgnoreCase("String")) {
                setting.setValue(arrstring[3]);
                ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7a").append(module.getName()).append(" ").append(setting.getName()).append(" has been set to ").append(arrstring[3]).append(".")));
                return;
            }
            try {
                if (setting.getName().equalsIgnoreCase("Enabled")) {
                    if (arrstring[3].equalsIgnoreCase("true")) {
                        module.enable();
                    }
                    if (arrstring[3].equalsIgnoreCase("false")) {
                        module.disable();
                    }
                }
                ConfigManager.setValueFromJson(module, setting, jsonParser.parse(arrstring[3]));
            }
            catch (Exception exception) {
                ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7cBad Value! This setting requires a: ").append(setting.getType()).append(" value.")));
                return;
            }
            ModuleCommand.sendMessage(String.valueOf(new StringBuilder().append("\u00a7a").append(module.getName()).append(" ").append(setting.getName()).append(" has been set to ").append(arrstring[3]).append(".")));
        }
    }
}

