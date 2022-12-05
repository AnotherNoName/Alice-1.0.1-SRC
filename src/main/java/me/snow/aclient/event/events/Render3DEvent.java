/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class Render3DEvent
extends EventStage {
    private final /* synthetic */ float partialTicks;

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public Render3DEvent(float f) {
        this.partialTicks = f;
    }
}

