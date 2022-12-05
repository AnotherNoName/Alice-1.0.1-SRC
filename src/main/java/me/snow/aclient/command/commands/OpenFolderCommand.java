/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.command.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import me.snow.aclient.command.Command;

public class OpenFolderCommand
extends Command {
    public OpenFolderCommand() {
        super("openfolder", new String[0]);
    }

    @Override
    public void execute(String[] arrstring) {
        try {
            Desktop.getDesktop().open(new File("Alice/"));
            Command.sendMessage("Opened config folder!", false);
        }
        catch (IOException iOException) {
            Command.sendMessage("Could not open config folder!", false);
            iOException.printStackTrace();
        }
    }
}

