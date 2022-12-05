//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.snow.aclient.manager;

import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PacketManager
extends Feature {
    /* synthetic */ boolean caughtPlayerPosLook;
    /* synthetic */ SPacketExplosion pExplosion;
    private final /* synthetic */ List<Packet<?>> noEventPackets;
    /* synthetic */ SPacketPlayerPosLook pPlayerPosLook;
    /* synthetic */ Timer timerPlayerPosLook;
    /* synthetic */ boolean caughtPExplosion;
    /* synthetic */ Timer timerExplosion;

    public boolean caughtPExplosion() {
        return this.caughtPExplosion;
    }

    public boolean shouldSendPacket(Packet<?> packet) {
        if (this.noEventPackets.contains(packet)) {
            this.noEventPackets.remove(packet);
            return false;
        }
        return true;
    }

    public boolean caughtPlayerPosLook() {
        return this.caughtPlayerPosLook;
    }

    public SPacketExplosion pExplosion() {
        return this.pExplosion;
    }

    public Timer timerExplosion() {
        return this.timerExplosion;
    }

    public Timer timerPlayerPosLook() {
        return this.timerPlayerPosLook;
    }

    public PacketManager() {
        this.noEventPackets = new ArrayList();
        this.pExplosion = null;
        this.timerExplosion = new Timer();
        this.caughtPExplosion = false;
        this.pPlayerPosLook = null;
        this.timerPlayerPosLook = new Timer();
        this.caughtPlayerPosLook = false;
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent clientTickEvent) {
        if (PacketManager.fullNullCheck()) {
            return;
        }
        if (this.timerExplosion.passedMs(250L)) {
            this.pExplosion = null;
            this.caughtPExplosion = false;
        }
        if (this.timerPlayerPosLook.passedMs(250L)) {
            this.pPlayerPosLook = null;
            this.caughtPlayerPosLook = false;
        }
    }

    public SPacketPlayerPosLook pPlayerPosLook() {
        return this.pPlayerPosLook;
    }

    public void sendPacketNoEvent(Packet<?> packet) {
        if (packet != null && !PacketManager.nullCheck()) {
            this.noEventPackets.add(packet);
            PacketManager.mc.player.connection.sendPacket(packet);
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (PacketManager.fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.pPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            this.timerPlayerPosLook.reset();
            this.caughtPlayerPosLook = true;
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            this.pExplosion = (SPacketExplosion)receive.getPacket();
            this.timerExplosion.reset();
            this.caughtPExplosion = true;
        }
    }
}

