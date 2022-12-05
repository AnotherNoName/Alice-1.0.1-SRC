/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;

public class KeyEvent
extends EventStage {
    public /* synthetic */ boolean info;
    public /* synthetic */ boolean pressed;

    public KeyEvent(int n, boolean bl, boolean bl2) {
        super(n);
        this.info = bl;
        this.pressed = bl2;
    }
}

