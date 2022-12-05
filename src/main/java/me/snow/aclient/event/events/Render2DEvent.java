/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.client.gui.ScaledResolution;

public class Render2DEvent
extends EventStage {
    public /* synthetic */ ScaledResolution scaledResolution;
    public /* synthetic */ float partialTicks;

    public Render2DEvent(float f, ScaledResolution scaledResolution) {
        this.partialTicks = f;
        this.scaledResolution = scaledResolution;
    }

    public void setPartialTicks(float f) {
        this.partialTicks = f;
    }
}

