//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import me.snow.aclient.module.Feature;

public class TimerManager
extends Feature {
    private /* synthetic */ float timer;

    public void unload() {
        this.timer = 1.0f;
        TimerManager.mc.timer.field_194149_e = 50.0f;
    }

    @Override
    public void reset() {
        this.timer = 1.0f;
    }

    public float getTimer() {
        return this.timer;
    }

    public void setTimer(float f) {
        if (f > 0.0f) {
            this.timer = f;
        }
    }

    public TimerManager() {
        this.timer = 1.0f;
    }

    public void update() {
        TimerManager.mc.timer.field_194149_e = 50.0f / (this.timer <= 0.0f ? 0.1f : this.timer);
    }
}

