/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class PlayerTravelEvent
extends EventStage {
    private final /* synthetic */ float vertical;
    private final /* synthetic */ float forward;
    private final /* synthetic */ float strafe;

    public float getStrafe() {
        return this.strafe;
    }

    public float getForward() {
        return this.forward;
    }

    public PlayerTravelEvent(float f, float f2, float f3) {
        this.strafe = f;
        this.vertical = f2;
        this.forward = f3;
    }

    public float getVertical() {
        return this.vertical;
    }
}

