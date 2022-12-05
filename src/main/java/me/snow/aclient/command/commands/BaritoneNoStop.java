/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;

public class BaritoneNoStop
extends Command {
    public BaritoneNoStop() {
        super("noStop", new String[]{"<prefix>", "<x>", "<y>", "<z>"});
    }

    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 5) {
            int n;
            int n2;
            int n3;
            AliceMain.baritoneManager.setPrefix(arrstring[0]);
            try {
                n3 = Integer.parseInt(arrstring[1]);
                n2 = Integer.parseInt(arrstring[2]);
                n = Integer.parseInt(arrstring[3]);
            }
            catch (NumberFormatException numberFormatException) {
                BaritoneNoStop.sendMessage("Invalid Input for x, y or z!");
                AliceMain.baritoneManager.stop();
                return;
            }
            AliceMain.baritoneManager.start(n3, n2, n);
            return;
        }
        BaritoneNoStop.sendMessage("Stoping Baritone-Nostop.");
        AliceMain.baritoneManager.stop();
    }
}

