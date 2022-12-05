//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketKeepAlive
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PingSpoof
extends Module {
    private /* synthetic */ boolean receive;
    private /* synthetic */ Setting<Boolean> offOnLogout;
    private /* synthetic */ Queue<Packet<?>> packets;
    private /* synthetic */ Setting<Integer> secondDelay;
    private /* synthetic */ Setting<Boolean> seconds;
    private /* synthetic */ Timer timer;
    private /* synthetic */ Setting<Integer> delay;

    @Override
    public void onUpdate() {
        this.clearQueue();
    }

    public PingSpoof() {
        super("PingSpoof", "Spoofs your ping!", Module.Category.MISC, true, false, false);
        this.seconds = this.register(new Setting<Boolean>("Seconds", false));
        this.delay = this.register(new Setting<Object>("DelayMS", Integer.valueOf(20), Integer.valueOf(0), Integer.valueOf(1000), object -> this.seconds.getValue() == false));
        this.secondDelay = this.register(new Setting<Object>("DelayS", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(30), object -> this.seconds.getValue()));
        this.offOnLogout = this.register(new Setting<Boolean>("OffOnLogout", false));
        this.packets = new ConcurrentLinkedQueue();
        this.timer = new Timer();
        this.receive = true;
    }

    public void clearQueue() {
        if (PingSpoof.mc.player != null && !mc.isSingleplayer() && PingSpoof.mc.player.isEntityAlive() && (!this.seconds.getValue().booleanValue() && this.timer.passedMs(this.delay.getValue().intValue()) || this.seconds.getValue().booleanValue() && this.timer.passedS(this.secondDelay.getValue().intValue()))) {
            double d = MathUtil.getIncremental(Math.random() * 10.0, 1.0);
            this.receive = false;
            int n = 0;
            while ((double)n < d) {
                Packet<?> packet = this.packets.poll();
                if (packet != null) {
                    PingSpoof.mc.player.connection.sendPacket(packet);
                }
                ++n;
            }
            this.timer.reset();
            this.receive = true;
        }
    }

    @Override
    public void onLoad() {
        if (this.offOnLogout.getValue().booleanValue()) {
            this.disable();
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (this.receive && PingSpoof.mc.player != null && !mc.isSingleplayer() && PingSpoof.mc.player.isEntityAlive() && send.getStage() == 0 && send.getPacket() instanceof CPacketKeepAlive) {
            this.packets.add((Packet<?>)send.getPacket());
            send.setCanceled(true);
        }
    }

    @Override
    public void onLogout() {
        if (this.offOnLogout.getValue().booleanValue()) {
            this.disable();
        }
    }

    @Override
    public void onDisable() {
        this.clearQueue();
    }
}

