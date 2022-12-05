/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.event.type;

import me.zero.alpine.fork.event.type.ICancellable;

public class Cancellable
implements ICancellable {
    private /* synthetic */ boolean cancelled;

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}

