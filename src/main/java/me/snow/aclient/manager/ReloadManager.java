//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.manager;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Feature;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ReloadManager
extends Feature {
    public /* synthetic */ String prefix;

    public void init(String string) {
        this.prefix = string;
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (!ReloadManager.fullNullCheck()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7cPhobos has been unloaded. Type ").append(string).append("reload to reload.")));
        }
    }

    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketChatMessage cPacketChatMessage;
        if (send.getPacket() instanceof CPacketChatMessage && (cPacketChatMessage = (CPacketChatMessage)send.getPacket()).getMessage().startsWith(this.prefix) && cPacketChatMessage.getMessage().contains("reload")) {
            AliceMain.load();
            send.setCanceled(true);
        }
    }
}

