//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module;

import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.manager.TextManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Util;

public class Feature
implements Util {
    private /* synthetic */ String name;
    public /* synthetic */ TextManager renderer;
    public /* synthetic */ List<Setting> settings;

    public void clearSettings() {
        this.settings = new ArrayList<Setting>();
    }

    public Setting getSettingByName(String string) {
        for (Setting setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(string)) continue;
            return setting;
        }
        return null;
    }

    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }

    public Feature() {
        this.settings = new ArrayList<Setting>();
        this.renderer = AliceMain.textManager;
    }

    public Setting register(Setting setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        if (this instanceof Module && Feature.mc.currentScreen instanceof LuigiGui) {
            LuigiGui.getInstance().updateModule((Module)this);
        }
        return setting;
    }

    public static boolean fullNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }

    public boolean isDisabled() {
        return !this.isEnabled();
    }

    public void reset() {
        for (Setting setting : this.settings) {
            setting.setValue(setting.getDefaultValue());
        }
    }

    public static boolean nullCheck() {
        return Feature.mc.player == null;
    }

    public void unregister(Setting setting) {
        ArrayList<Setting> arrayList = new ArrayList<Setting>();
        for (Setting setting2 : this.settings) {
            if (!setting2.equals(setting)) continue;
            arrayList.add(setting2);
        }
        if (!arrayList.isEmpty()) {
            this.settings.removeAll(arrayList);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof LuigiGui) {
            LuigiGui.getInstance().updateModule((Module)this);
        }
    }

    public boolean isEnabled() {
        if (this instanceof Module) {
            return ((Module)this).isOn();
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public boolean megaNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null || this.isDisabled();
    }

    public Feature(String string) {
        this.settings = new ArrayList<Setting>();
        this.renderer = AliceMain.textManager;
        this.name = string;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }
}

