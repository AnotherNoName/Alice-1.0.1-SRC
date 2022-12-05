/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event;

import me.snow.aclient.event.EnumStages;
import me.snow.aclient.event.events.ICancellable;

public class PhobosEvent
implements ICancellable {
    private final /* synthetic */ EnumStages stage;
    private /* synthetic */ boolean canceled;

    @Override
    public boolean isCancelled() {
        return this.canceled;
    }

    @Override
    public void cancel() {
        this.canceled = true;
    }

    public EnumStages getStage() {
        return this.stage;
    }

    public PhobosEvent(EnumStages enumStages) {
        this.stage = enumStages;
    }

    public void resume() {
        this.canceled = false;
    }
}

