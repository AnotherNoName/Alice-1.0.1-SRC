//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 */
package me.snow.aclient.manager;

import java.util.HashMap;
import me.snow.aclient.module.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public class SpeedManager
extends Feature {
    public /* synthetic */ double percentJumpSpeedChanged;
    public static /* synthetic */ boolean isJumping;
    public static /* synthetic */ boolean didJumpThisTick;
    public /* synthetic */ double speedometerCurrentSpeed;
    public /* synthetic */ boolean wasFirstJump;
    public /* synthetic */ HashMap<EntityPlayer, Double> playerSpeeds;
    public static final /* synthetic */ double LAST_JUMP_INFO_DURATION_DEFAULT = 3.0;
    public /* synthetic */ double firstJumpSpeed;
    public /* synthetic */ long jumpInfoStartTime;
    public /* synthetic */ double jumpSpeedChanged;
    public /* synthetic */ double lastJumpSpeed;
    public /* synthetic */ boolean didJumpLastTick;

    public SpeedManager() {
        this.wasFirstJump = true;
        this.playerSpeeds = new HashMap();
    }

    public void updateValues() {
        double d = SpeedManager.mc.player.posX - SpeedManager.mc.player.prevPosX;
        double d2 = SpeedManager.mc.player.posZ - SpeedManager.mc.player.prevPosZ;
        this.speedometerCurrentSpeed = d * d + d2 * d2;
        if (didJumpThisTick && (!SpeedManager.mc.player.onGround || isJumping)) {
            if (!this.didJumpLastTick) {
                this.wasFirstJump = this.lastJumpSpeed == 0.0;
                this.percentJumpSpeedChanged = this.speedometerCurrentSpeed != 0.0 ? this.speedometerCurrentSpeed / this.lastJumpSpeed - 1.0 : -1.0;
                this.jumpSpeedChanged = this.speedometerCurrentSpeed - this.lastJumpSpeed;
                this.jumpInfoStartTime = Minecraft.getSystemTime();
                this.lastJumpSpeed = this.speedometerCurrentSpeed;
                this.firstJumpSpeed = this.wasFirstJump ? this.lastJumpSpeed : 0.0;
            }
            this.didJumpLastTick = didJumpThisTick;
        } else {
            this.didJumpLastTick = false;
            this.lastJumpSpeed = 0.0;
        }
        this.updatePlayers();
    }

    public double turnIntoKpH(double d) {
        return (double)MathHelper.sqrt((double)d) * 71.2729367892;
    }

    public double getPlayerSpeed(EntityPlayer entityPlayer) {
        if (this.playerSpeeds.get((Object)entityPlayer) == null) {
            return 0.0;
        }
        return this.turnIntoKpH(this.playerSpeeds.get((Object)entityPlayer));
    }

    public static void setIsJumping(boolean bl) {
        isJumping = bl;
    }

    public float lastJumpInfoTimeRemaining() {
        return (float)(Minecraft.getSystemTime() - this.jumpInfoStartTime) / 1000.0f;
    }

    public void updatePlayers() {
        for (EntityPlayer entityPlayer : SpeedManager.mc.world.playerEntities) {
            int n = 20;
            if (!(SpeedManager.mc.player.getDistanceSqToEntity((Entity)entityPlayer) < (double)(n * n))) continue;
            double d = entityPlayer.posX - entityPlayer.prevPosX;
            double d2 = entityPlayer.posZ - entityPlayer.prevPosZ;
            double d3 = d * d + d2 * d2;
            this.playerSpeeds.put(entityPlayer, d3);
        }
    }

    public double getSpeedKpH() {
        double d = this.turnIntoKpH(this.speedometerCurrentSpeed);
        d = (double)Math.round(10.0 * d) / 10.0;
        return d;
    }

    public static void setDidJumpThisTick(boolean bl) {
        didJumpThisTick = bl;
    }

    public double getSpeedMpS() {
        double d = this.turnIntoKpH(this.speedometerCurrentSpeed) / 3.6;
        d = (double)Math.round(10.0 * d) / 10.0;
        return d;
    }
}

