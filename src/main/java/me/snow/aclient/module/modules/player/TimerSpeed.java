//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class TimerSpeed
extends Module {
    public /* synthetic */ Setting<Float> timer;

    @Override
    public void onEnable() {
        TimerSpeed.mc.timer.field_194149_e = 50.0f;
    }

    @Override
    public void onDisable() {
        TimerSpeed.mc.timer.field_194149_e = 50.0f;
    }

    @Override
    public void onTick() {
        TimerSpeed.mc.timer.field_194149_e = 50.0f / this.timer.getValue().floatValue();
    }

    public TimerSpeed() {
        super("Timer", "Will speed up the game.", Module.Category.PLAYER, false, false, false);
        this.timer = this.register(new Setting<Float>("Speed", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(10.0f)));
    }
}

