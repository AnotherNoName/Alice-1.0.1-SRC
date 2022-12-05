/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package me.snow.aclient.event.events;

import java.util.function.Predicate;
import me.snow.aclient.event.EnumStages;
import me.snow.aclient.event.PhobosEvent;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.EntityLivingBase;

public class EventTotemPop
extends PhobosEvent {
    private final /* synthetic */ int times;
    @EventHandler
    private final /* synthetic */ Listener<EventTotemPop> packetRecieveListener;
    private final /* synthetic */ EntityLivingBase entity;

    public EntityLivingBase getEntity() {
        return this.entity;
    }

    public int getTimes() {
        return this.times;
    }

    public EventTotemPop(EnumStages enumStages, EntityLivingBase entityLivingBase, int n) {
        super(enumStages);
        this.packetRecieveListener = new Listener<EventTotemPop>(eventTotemPop -> {}, new Predicate[0]);
        this.entity = entityLivingBase;
        this.times = n;
    }
}

