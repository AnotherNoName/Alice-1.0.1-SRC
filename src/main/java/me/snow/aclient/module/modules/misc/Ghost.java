//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketPlayer
 */
package me.snow.aclient.module.modules.misc;

import java.util.function.Predicate;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.network.play.client.CPacketPlayer;

public class Ghost
extends Module {
    @EventHandler
    private final /* synthetic */ Listener<PacketEvent.Send> netWorkManagerListener;
    /* synthetic */ boolean bypassdeath;

    public Ghost() {
        super("Ghost", "Stay alive after dying", Module.Category.MISC, true, false, false);
        this.netWorkManagerListener = new Listener<PacketEvent.Send>(send -> {
            if (this.bypassdeath && send.getPacket() instanceof CPacketPlayer) {
                send.isCanceled();
            }
        }, new Predicate[0]);
    }

    @Override
    public void onUpdate() {
        if (Ghost.mc.world == null) {
            return;
        }
        if (Ghost.mc.player.getHealth() == 0.0f) {
            Ghost.mc.player.setHealth(20.0f);
            Ghost.mc.player.isDead = false;
            this.bypassdeath = true;
            mc.displayGuiScreen(null);
            Ghost.mc.player.setPositionAndUpdate(Ghost.mc.player.posX, Ghost.mc.player.posY, Ghost.mc.player.posZ);
        }
    }

    @Override
    public void onDisable() {
        if (Ghost.mc.player != null) {
            Ghost.mc.player.respawnPlayer();
        }
        this.bypassdeath = false;
    }
}

