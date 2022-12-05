//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import me.snow.aclient.module.Feature;

public class MovementManager
extends Feature {
    public void setMotion(double d, double d2, double d3) {
        if (MovementManager.mc.player != null) {
            if (MovementManager.mc.player.isRiding()) {
                MovementManager.mc.player.ridingEntity.motionX = d;
                MovementManager.mc.player.ridingEntity.motionY = d2;
                MovementManager.mc.player.ridingEntity.motionZ = d;
            } else {
                MovementManager.mc.player.motionX = d;
                MovementManager.mc.player.motionY = d2;
                MovementManager.mc.player.motionZ = d3;
            }
        }
    }

    public void doStep(float f, boolean bl) {
        if (bl && (!MovementManager.mc.player.isCollidedVertically || (double)MovementManager.mc.player.fallDistance > 0.1 || MovementManager.mc.player.isOnLadder() || !MovementManager.mc.player.onGround)) {
            return;
        }
        MovementManager.mc.player.stepHeight = f;
    }
}

