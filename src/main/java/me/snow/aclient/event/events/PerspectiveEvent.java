/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class PerspectiveEvent
extends EventStage {
    private /* synthetic */ float aspect;

    public void setAspect(float f) {
        this.aspect = f;
    }

    public float getAspect() {
        return this.aspect;
    }

    public PerspectiveEvent(float f) {
        this.aspect = f;
    }
}

