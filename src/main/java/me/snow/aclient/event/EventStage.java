/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class EventStage
extends Event {
    private /* synthetic */ int stage;

    public void setStage(int n) {
        this.stage = n;
    }

    public int getStage() {
        return this.stage;
    }

    public EventStage() {
    }

    public EventStage(int n) {
        this.stage = n;
    }
}

