//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;
import me.snow.aclient.command.Command;

public class CoordsCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        StringSelection stringSelection = new StringSelection(String.valueOf(new StringBuilder().append(decimalFormat.format(CoordsCommand.mc.player.posX)).append(", ").append(decimalFormat.format(CoordsCommand.mc.player.posY)).append(", ").append(decimalFormat.format(CoordsCommand.mc.player.posZ))));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Command.sendMessage("Saved Coordinates To Clipboard.", false);
    }

    public CoordsCommand() {
        super("coords", new String[0]);
    }
}

