//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.client.gui.ScaledResolution;

public class RenderOverlayEvent
extends EventStage {
    public /* synthetic */ float partialTicks;
    public /* synthetic */ ScaledResolution scaledResolution;

    public void setScaledResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public void setPartialTicks(float f) {
        this.partialTicks = f;
    }

    public double getScreenWidth() {
        return this.scaledResolution.getScaledWidth_double();
    }

    public RenderOverlayEvent(float f, ScaledResolution scaledResolution) {
        this.partialTicks = f;
        this.scaledResolution = scaledResolution;
    }

    public double getScreenHeight() {
        return this.scaledResolution.getScaledHeight_double();
    }
}

