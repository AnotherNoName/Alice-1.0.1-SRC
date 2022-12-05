/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class RenderPlayerInTabEvent
extends EventStage {
    private /* synthetic */ String name;

    public RenderPlayerInTabEvent(String string) {
        this.name = string;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String string) {
        this.name = string;
    }
}

