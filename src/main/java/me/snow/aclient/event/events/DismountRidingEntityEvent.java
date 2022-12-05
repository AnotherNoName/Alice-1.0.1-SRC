/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class DismountRidingEntityEvent
extends EventStage {
    private static /* synthetic */ DismountRidingEntityEvent INSTANCE;

    public static DismountRidingEntityEvent get() {
        INSTANCE.setCanceled(false);
        return INSTANCE;
    }

    static {
        INSTANCE = new DismountRidingEntityEvent();
    }
}

