/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;

public class DeathEvent
extends EventStage {
    public /* synthetic */ EntityPlayer player;

    public DeathEvent(EntityPlayer entityPlayer) {
        this.player = entityPlayer;
    }
}

