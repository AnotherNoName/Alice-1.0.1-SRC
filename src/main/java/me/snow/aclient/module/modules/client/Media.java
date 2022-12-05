//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.snow.aclient.module.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class Media
extends Module {
    private /* synthetic */ StringBuffer name;
    public final /* synthetic */ Setting<Boolean> changeOwn;
    private static /* synthetic */ Media instance;
    public final /* synthetic */ Setting<String> ownName;

    public String getPlayerName211() {
        if (this.name == null) {
            return null;
        }
        return this.name.toString();
    }

    @Override
    public void onEnable() {
        Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GRAY).append("Success! Name succesfully changed to ").append((Object)ChatFormatting.GREEN).append(this.ownName.getValue())));
    }

    public Media() {
        super("NameProtect", "Helps with creating Media", Module.Category.CLIENT, false, false, false);
        this.changeOwn = this.register(new Setting<Boolean>("MyName", true));
        this.ownName = this.register(new Setting<Object>("Name", "Name here...", object -> this.changeOwn.getValue()));
        instance = this;
    }

    public static String getPlayerName() {
        if (Media.fullNullCheck()) {
            return mc.getSession().getUsername();
        }
        String string = Media.getInstance().getPlayerName211();
        if (string == null || string.isEmpty()) {
            return mc.getSession().getUsername();
        }
        return string;
    }

    public static Media getInstance() {
        if (instance == null) {
            instance = new Media();
        }
        return instance;
    }
}

