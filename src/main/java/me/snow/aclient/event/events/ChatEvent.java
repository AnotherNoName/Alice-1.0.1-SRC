/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class ChatEvent
extends EventStage {
    private final /* synthetic */ String msg;

    public ChatEvent(String string) {
        this.msg = string;
    }

    public String getMsg() {
        return this.msg;
    }
}

