//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentBase
 *  net.minecraft.util.text.TextComponentString
 */
package me.snow.aclient.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.Feature;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;

public abstract class Command
extends Feature {
    protected /* synthetic */ String[] commands;
    protected /* synthetic */ String name;

    public String[] getCommands() {
        return this.commands;
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public static void sendMessage(String string) {
        Command.sendSilentMessage(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append(" \u00a7r").append(string)));
    }

    public Command(String string) {
        super(string);
        this.name = string;
        this.commands = new String[]{""};
    }

    public Command(String string, String[] arrstring) {
        super(string);
        this.name = string;
        this.commands = arrstring;
    }

    public static void sendSilentMessage(String string, boolean bl) {
        if (Command.nullCheck()) {
            return;
        }
        Command.mc.player.addChatMessage((ITextComponent)new ChatMessage(string));
        if (bl) {
            AliceMain.notificationManager.addNotification(string, 3000L);
        }
    }

    public static void sendOverwriteMessage(String string, int n, boolean bl) {
        TextComponentString textComponentString = new TextComponentString(string);
        Command.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)textComponentString, n);
        if (bl) {
            AliceMain.notificationManager.addNotification(string, 3000L);
        }
    }

    public static void sendMessage(String string, boolean bl) {
        Command.sendSilentMessage(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append(" \u00a7r").append(string)));
        if (bl) {
            AliceMain.notificationManager.addNotification(string, 3000L);
        }
    }

    public static String getCommandPrefix() {
        return AliceMain.commandManager.getPrefix();
    }

    public static void sendRainbowMessage(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.insert(0, "\u00a7+");
        Command.mc.player.addChatMessage((ITextComponent)new ChatMessage(String.valueOf(stringBuilder)));
    }

    public static void sendSilentMessage(String string) {
        if (Command.nullCheck()) {
            return;
        }
        Command.mc.player.addChatMessage((ITextComponent)new ChatMessage(string));
    }

    public static class ChatMessage
    extends TextComponentBase {
        private final /* synthetic */ String text;

        public ChatMessage(String string) {
            Pattern pattern = Pattern.compile("&[0123456789abcdefrlosmk]");
            Matcher matcher = pattern.matcher(string);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                String string2 = String.valueOf(new StringBuilder().append("\u00a7").append(matcher.group().substring(1)));
                matcher.appendReplacement(stringBuffer, string2);
            }
            matcher.appendTail(stringBuffer);
            this.text = stringBuffer.toString();
        }

        public ITextComponent createCopy() {
            return new ChatMessage(this.text);
        }

        public String getUnformattedComponentText() {
            return this.text;
        }
    }
}

