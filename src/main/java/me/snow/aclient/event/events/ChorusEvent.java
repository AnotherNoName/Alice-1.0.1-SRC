/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class ChorusEvent
extends EventStage {
    private final /* synthetic */ double chorusX;
    private final /* synthetic */ double chorusZ;
    private final /* synthetic */ double chorusY;

    public double getChorusX() {
        return this.chorusX;
    }

    public double getChorusY() {
        return this.chorusY;
    }

    public double getChorusZ() {
        return this.chorusZ;
    }

    public ChorusEvent(double d, double d2, double d3) {
        this.chorusX = d;
        this.chorusY = d2;
        this.chorusZ = d3;
    }
}

