//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.util.ca.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import me.snow.aclient.util.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathsUtilCa
implements Util {
    /* synthetic */ Random random;

    public static int floor(double d) {
        int n = (int)d;
        return d < (double)n ? n - 1 : n;
    }

    public static double square(float f) {
        return f * f;
    }

    public static float wrapDegrees(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static Vec3d extrapolatePlayerPosition(EntityPlayer entityPlayer, int n) {
        Vec3d vec3d = new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ);
        Vec3d vec3d2 = new Vec3d(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
        double d = MathsUtilCa.square((float)entityPlayer.motionX) + MathsUtilCa.square((float)entityPlayer.motionY) + MathsUtilCa.square((float)entityPlayer.motionZ);
        Vec3d vec3d3 = MathsUtilCa.calculateLine(vec3d, vec3d2, d * (double)n);
        return new Vec3d(vec3d3.xCoord, entityPlayer.posY, vec3d3.zCoord);
    }

    public static double[] directionSpeed(double d) {
        float f = MathsUtilCa.mc.player.movementInput.field_192832_b;
        float f2 = MathsUtilCa.mc.player.movementInput.moveStrafe;
        float f3 = MathsUtilCa.mc.player.prevRotationYaw + (MathsUtilCa.mc.player.rotationYaw - MathsUtilCa.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static Vec3d calculateLine(Vec3d vec3d, Vec3d vec3d2, double d) {
        double d2 = Math.sqrt(MathsUtilCa.square((float)(vec3d2.xCoord - vec3d.xCoord)) + MathsUtilCa.square((float)(vec3d2.yCoord - vec3d.yCoord)) + MathsUtilCa.square((float)(vec3d2.zCoord - vec3d.zCoord)));
        double d3 = (vec3d2.xCoord - vec3d.xCoord) / d2;
        double d4 = (vec3d2.yCoord - vec3d.yCoord) / d2;
        double d5 = (vec3d2.zCoord - vec3d.zCoord) / d2;
        double d6 = vec3d.xCoord + d3 * d;
        double d7 = vec3d.yCoord + d4 * d;
        double d8 = vec3d.zCoord + d5 * d;
        return new Vec3d(d6, d7, d8);
    }

    public static double clamp(double d, double d2, double d3) {
        return d < d2 ? d2 : Math.min(d, d3);
    }

    public MathsUtilCa() {
        this.random = new Random();
    }

    public static int clamp(int n, int n2, int n3) {
        return n < n2 ? n2 : Math.min(n, n3);
    }

    public static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : Math.min(f, f3);
    }

    public static double roundAvoid(double d, int n) {
        double d2 = Math.pow(10.0, n);
        return (double)Math.round(d * d2) / d2;
    }

    public static Vec3d roundVec(Vec3d vec3d, int n) {
        return new Vec3d(MathsUtilCa.round(vec3d.xCoord, n), MathsUtilCa.round(vec3d.yCoord, n), MathsUtilCa.round(vec3d.zCoord, n));
    }

    public static double round(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.FLOOR);
        return bigDecimal.doubleValue();
    }

    public int random(int n, int n2) {
        return this.random.nextInt(n2 - n) + n;
    }

    public static float[] calcAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static double degToRad(double d) {
        return d * 0.01745329238474369;
    }

    public static double incrementRound(double d, double d2) {
        return Math.floor(d) + (double)Math.round((d - Math.floor(d)) * (1.0 / d2)) / (1.0 / d2);
    }
}

