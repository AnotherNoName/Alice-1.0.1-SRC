//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundManager
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 */
package me.snow.aclient.command.commands;

import me.snow.aclient.command.Command;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ReloadSoundCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        try {
            SoundManager soundManager = (SoundManager)ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, (Object)mc.getSoundHandler(), (String[])new String[]{"sndManager", "sndManager"});
            soundManager.reloadSoundSystem();
            ReloadSoundCommand.sendMessage("\u00a7aReloaded Sound System.");
        }
        catch (Exception exception) {
            System.out.println(String.valueOf(new StringBuilder().append("Could not restart sound manager: ").append(exception)));
            exception.printStackTrace();
            ReloadSoundCommand.sendMessage("\u00a7cCouldn't Reload Sound System!");
        }
    }

    public ReloadSoundCommand() {
        super("reloadsound", new String[0]);
    }
}

