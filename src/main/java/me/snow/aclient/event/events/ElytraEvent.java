/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class ElytraEvent
extends EventStage {
    private /* synthetic */ Entity entity;

    public Entity getEntity() {
        return this.entity;
    }

    public ElytraEvent(Entity entity) {
        this.entity = entity;
    }
}

