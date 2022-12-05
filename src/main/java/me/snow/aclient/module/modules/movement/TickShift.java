//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.EntityLivingBase
 */
package me.snow.aclient.module.modules.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.entity.EntityLivingBase;

public class TickShift
extends Module {
    public final /* synthetic */ Setting<Integer> DisableTicks;
    public final /* synthetic */ Setting<Boolean> Disable;
    public final /* synthetic */ Setting<Integer> enableTicks;
    private /* synthetic */ boolean timerOn;
    private /* synthetic */ boolean playerMoving;
    public final /* synthetic */ Setting<Double> multiplayer;
    private /* synthetic */ int ticksStill;
    private /* synthetic */ int ticksPassed;
    public final /* synthetic */ Setting<Boolean> movementEnable;

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append(String.valueOf(this.ticksStill)));
    }

    public TickShift() {
        super("TickShift", "placeholder", Module.Category.MOVEMENT, true, false, false);
        this.Disable = this.register(new Setting<Boolean>("Disable", false));
        this.movementEnable = this.register(new Setting<Boolean>("MovementEnable", true));
        this.DisableTicks = this.register(new Setting<Integer>("Anti DisableTicks Height", 20, 1, 100));
        this.enableTicks = this.register(new Setting<Integer>("EnableTicks", 30, 1, 100));
        this.multiplayer = this.register(new Setting<Double>("Multiplayer", 3.0, 1.0, 10.0));
        this.ticksPassed = 0;
        this.ticksStill = 0;
        this.timerOn = false;
    }

    @Override
    public void onDisable() {
        this.timerOn = false;
        this.ticksStill = 0;
        TickShift.mc.timer.field_194149_e = 50.0f;
    }

    public static boolean isMoving(EntityLivingBase entityLivingBase) {
        return entityLivingBase.field_191988_bg != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }

    @Override
    public void reset() {
        this.timerOn = false;
        this.ticksStill = 0;
        TickShift.mc.timer.field_194149_e = 50.0f;
    }

    @Override
    public void onTick() {
        if (!this.timerOn) {
            if (TickShift.isMoving((EntityLivingBase)TickShift.mc.player)) {
                if (this.ticksStill >= 1) {
                    --this.ticksStill;
                }
            } else if (!TickShift.isMoving((EntityLivingBase)TickShift.mc.player)) {
                ++this.ticksStill;
            }
        }
        if (this.ticksStill >= this.enableTicks.getValue()) {
            this.timerOn = true;
            if (this.movementEnable.getValue().booleanValue()) {
                if (TickShift.mc.gameSettings.keyBindJump.isKeyDown() || TickShift.mc.gameSettings.keyBindSneak.isKeyDown() || TickShift.mc.gameSettings.keyBindRight.isKeyDown() || TickShift.mc.gameSettings.keyBindLeft.isKeyDown() || TickShift.mc.gameSettings.keyBindForward.isKeyDown() || TickShift.mc.gameSettings.keyBindBack.isKeyDown()) {
                    TickShift.mc.timer.field_194149_e = (float)(50.0 / this.multiplayer.getValue());
                    ++this.ticksPassed;
                }
            } else {
                TickShift.mc.timer.field_194149_e = (float)(50.0 / this.multiplayer.getValue());
                ++this.ticksPassed;
            }
        }
        if (this.ticksPassed >= this.DisableTicks.getValue()) {
            this.ticksPassed = 0;
            if (this.Disable.getValue().booleanValue()) {
                this.disable();
            } else {
                this.reset();
            }
        }
    }
}

