/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class PacketEvent
extends EventStage {
    private final /* synthetic */ Packet<?> packet;

    public PacketEvent(int n, Packet<?> packet) {
        super(n);
        this.packet = packet;
    }

    public <T extends Packet<?>> T getPacket() {
        return (T)this.packet;
    }

    @Cancelable
    public static class Receive
    extends PacketEvent {
        public Receive(int n, Packet<?> packet) {
            super(n, packet);
        }
    }

    @Cancelable
    public static class Send
    extends PacketEvent {
        public Send(int n, Packet<?> packet) {
            super(n, packet);
        }
    }
}

