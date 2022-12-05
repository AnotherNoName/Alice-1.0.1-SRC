//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.network.play.client.CPacketCustomPayload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.internal.FMLProxyPacket
 */
package me.snow.aclient.module.modules.client;

import io.netty.buffer.Unpooled;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class NoHandShake
extends Module {
    public NoHandShake() {
        super("NoHandShake", "Doesn't send your mod list to the server.", Module.Category.CLIENT, true, false, false);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketCustomPayload cPacketCustomPayload;
        if (send.getPacket() instanceof FMLProxyPacket && !mc.isSingleplayer()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketCustomPayload && (cPacketCustomPayload = (CPacketCustomPayload)send.getPacket()).getChannelName().equals("MC|Brand")) {
            cPacketCustomPayload.data = new PacketBuffer(Unpooled.buffer()).writeString("vanilla");
        }
    }
}

