/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.event.events;

import java.util.UUID;
import me.snow.aclient.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;

public class ConnectionEvent
extends EventStage {
    private final /* synthetic */ UUID uuid;
    private final /* synthetic */ String name;
    private final /* synthetic */ EntityPlayer entity;

    public EntityPlayer getEntity() {
        return this.entity;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public ConnectionEvent(int n, UUID uUID, String string) {
        super(n);
        this.uuid = uUID;
        this.name = string;
        this.entity = null;
    }

    public ConnectionEvent(int n, EntityPlayer entityPlayer, UUID uUID, String string) {
        super(n);
        this.entity = entityPlayer;
        this.uuid = uUID;
        this.name = string;
    }
}

