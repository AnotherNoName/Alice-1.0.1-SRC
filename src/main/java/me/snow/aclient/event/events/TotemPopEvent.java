/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;

public class TotemPopEvent
extends EventStage {
    private final /* synthetic */ EntityPlayer entity;

    public EntityPlayer getEntity() {
        return this.entity;
    }

    public TotemPopEvent(EntityPlayer entityPlayer) {
        this.entity = entityPlayer;
    }
}

