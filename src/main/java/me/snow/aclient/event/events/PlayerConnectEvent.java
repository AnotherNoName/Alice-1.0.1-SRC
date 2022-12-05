/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.events;

import java.util.UUID;
import me.snow.aclient.event.EventStage;

public class PlayerConnectEvent
extends EventStage {
    private /* synthetic */ UUID uuid;
    private /* synthetic */ String name;

    public String getName() {
        return this.name;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setName(String string) {
        this.name = string;
    }

    public PlayerConnectEvent(String string, UUID uUID) {
        this.name = string;
        this.uuid = uUID;
    }

    public void setUuid(UUID uUID) {
        this.uuid = uUID;
    }

    public static class Leave
    extends PlayerConnectEvent {
        public Leave(String string, UUID uUID) {
            super(string, uUID);
        }
    }

    public static class Join
    extends PlayerConnectEvent {
        public Join(String string, UUID uUID) {
            super(string, uUID);
        }
    }
}

