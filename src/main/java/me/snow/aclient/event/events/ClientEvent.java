/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.EventStage;
import me.snow.aclient.module.Feature;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class ClientEvent
extends EventStage {
    private /* synthetic */ Setting setting;
    private /* synthetic */ Feature feature;

    public Setting getSetting() {
        return this.setting;
    }

    public ClientEvent(int n, Feature feature) {
        super(n);
        this.feature = feature;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public ClientEvent(Setting setting) {
        super(2);
        this.setting = setting;
    }
}

