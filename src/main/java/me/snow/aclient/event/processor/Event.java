/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.processor;

public class Event {
    private /* synthetic */ boolean isCancelled;

    public Event() {
        this.isCancelled = false;
    }

    public final boolean isCancelled() {
        return this.isCancelled;
    }

    public final void setCancelled(boolean bl) {
        this.isCancelled = bl;
    }
}

