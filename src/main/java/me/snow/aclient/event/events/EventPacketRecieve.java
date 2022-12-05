/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EnumStages;
import me.snow.aclient.event.PhobosEvent;
import net.minecraft.network.Packet;

public class EventPacketRecieve
extends PhobosEvent {
    private final /* synthetic */ Packet<?> packet;

    public EventPacketRecieve(EnumStages enumStages, Packet<?> packet) {
        super(enumStages);
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }
}

