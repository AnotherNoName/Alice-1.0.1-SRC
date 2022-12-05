//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiMainMenu
 */
package me.snow.aclient;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.snow.aclient.module.modules.client.RPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class DiscordPresence {
    private static /* synthetic */ Thread thread;
    private static final /* synthetic */ DiscordRPC rpc;
    public static /* synthetic */ DiscordRichPresence presence;
    private static /* synthetic */ int index;

    static {
        index = 1;
        rpc = DiscordRPC.INSTANCE;
        presence = new DiscordRichPresence();
    }

    public static void stop() {
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }

    public static void start() {
        DiscordEventHandlers discordEventHandlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("969887055301705778", discordEventHandlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.presence.details = Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu ? "In the main menu." : String.valueOf(new StringBuilder().append("Playing ").append(Minecraft.getMinecraft().currentServerData != null ? (RPC.INSTANCE.showIP.getValue().booleanValue() ? String.valueOf(new StringBuilder().append("on ").append(Minecraft.getMinecraft().currentServerData.serverIP).append(".")) : " multiplayer.") : " singleplayer."));
        DiscordPresence.presence.state = RPC.INSTANCE.state;
        DiscordPresence.presence.largeImageKey = "Alice";
        DiscordPresence.presence.largeImageText = " Alice+ 1.0.0";
        rpc.Discord_UpdatePresence(presence);
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                DiscordPresence.presence.details = Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu ? "In the main menu." : String.valueOf(new StringBuilder().append("Playing ").append(Minecraft.getMinecraft().currentServerData != null ? (RPC.INSTANCE.showIP.getValue().booleanValue() ? String.valueOf(new StringBuilder().append("on ").append(Minecraft.getMinecraft().currentServerData.serverIP).append(".")) : " multiplayer.") : " singleplayer."));
                DiscordPresence.presence.state = RPC.INSTANCE.state;
                if (RPC.INSTANCE.catMode.getValue().booleanValue()) {
                    if (index == 16) {
                        index = 1;
                    }
                    DiscordPresence.presence.largeImageKey = String.valueOf(new StringBuilder().append("cat").append(index));
                    ++index;
                }
                rpc.Discord_UpdatePresence(presence);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException interruptedException) {}
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }
}

