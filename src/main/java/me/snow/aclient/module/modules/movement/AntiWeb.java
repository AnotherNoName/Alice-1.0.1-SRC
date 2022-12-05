//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class AntiWeb
extends Module {
    private final /* synthetic */ Setting<Float> timerAmount;
    private final /* synthetic */ Setting<Float> horizontalSpeed;
    public /* synthetic */ Setting<Boolean> onlySnaking;
    public /* synthetic */ boolean isTimering;
    private final /* synthetic */ Setting<Float> verticalSpeed;
    private final /* synthetic */ Setting<Mode> mode;

    public AntiWeb() {
        super("AntiWeb", "", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.Vanilla));
        this.horizontalSpeed = this.register(new Setting<Float>("Horizontal Speed", Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(2.0f), f -> this.mode.getValue() == Mode.Motion));
        this.verticalSpeed = this.register(new Setting<Float>("Vertical Speed", Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(2.0f), f -> this.mode.getValue() == Mode.Motion));
        this.timerAmount = this.register(new Setting<Float>("Timer", Float.valueOf(0.1f), Float.valueOf(0.1f), Float.valueOf(10.0f), f -> this.mode.getValue() == Mode.Timer));
        this.onlySnaking = this.register(new Setting<Boolean>("OnlySnaking", false));
    }

    @Override
    public void onTick() {
        if (!AntiWeb.mc.player.isInWeb) {
            if (this.isTimering) {
                AntiWeb.mc.timer.field_194149_e = 50.0f;
                this.isTimering = false;
            }
            return;
        }
        switch (this.mode.getValue()) {
            case Vanilla: {
                AntiWeb.mc.player.isInWeb = false;
                break;
            }
            case Motion: {
                AntiWeb.mc.player.motionX *= (double)(1.0f + this.horizontalSpeed.getValue().floatValue());
                AntiWeb.mc.player.motionY = -this.verticalSpeed.getValue().floatValue();
                AntiWeb.mc.player.motionZ *= (double)(1.0f + this.horizontalSpeed.getValue().floatValue());
                break;
            }
            case Timer: {
                if (this.onlySnaking.getValue().booleanValue() && AntiWeb.mc.player.isSneaking()) {
                    AntiWeb.mc.timer.field_194149_e = 50.0f / this.timerAmount.getValue().floatValue();
                    this.isTimering = true;
                }
                if (this.onlySnaking.getValue().booleanValue()) break;
                AntiWeb.mc.timer.field_194149_e = 50.0f / this.timerAmount.getValue().floatValue();
                this.isTimering = true;
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append((Object)this.mode.getValue()).append(""));
    }

    public static enum Mode {
        Vanilla,
        Motion,
        Timer;

    }
}

