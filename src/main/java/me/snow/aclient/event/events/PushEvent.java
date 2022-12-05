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
public class PushEvent
extends EventStage {
    public /* synthetic */ double x;
    public /* synthetic */ double z;
    public /* synthetic */ Entity entity;
    public /* synthetic */ boolean airbone;
    public /* synthetic */ double y;

    public PushEvent(Entity entity, double d, double d2, double d3, boolean bl) {
        super(0);
        this.entity = entity;
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.airbone = bl;
    }

    public PushEvent(int n) {
        super(n);
    }

    public PushEvent(int n, Entity entity) {
        super(n);
        this.entity = entity;
    }
}

