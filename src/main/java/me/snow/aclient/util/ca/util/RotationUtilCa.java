//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.util.ca.util;

import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.ca.util.TimerCa;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationUtilCa
implements Util {
    private static /* synthetic */ float yaw;
    private static /* synthetic */ float pitch;
    public static /* synthetic */ TimerCa rotationTimer;
    public static /* synthetic */ boolean isSpoofingAngles;

    static {
        rotationTimer = new TimerCa();
    }

    public static int getDirection4D() {
        return MathHelper.floor((double)((double)(RotationUtilCa.mc.player.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
    }

    public static void faceVector(Vec3d vec3d, boolean bl) {
        float[] arrf = RotationUtilCa.getLegitRotations(vec3d);
        RotationUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], bl ? (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360) : arrf[1], RotationUtilCa.mc.player.onGround));
        RotationUtilCa.setYawAndPitch(arrf[0], arrf[1]);
    }

    public static double[] calculateLookAt(double d, double d2, double d3, EntityPlayer entityPlayer) {
        double d4 = entityPlayer.posX - d;
        double d5 = entityPlayer.posY - d2;
        double d6 = entityPlayer.posZ - d3;
        double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
        double d8 = Math.asin(d5 /= d7);
        double d9 = Math.atan2(d6 /= d7, d4 /= d7);
        d8 = d8 * 180.0 / Math.PI;
        d9 = d9 * 180.0 / Math.PI;
        return new double[]{d9 += 90.0, d8};
    }

    public static void resetRotation() {
        if (isSpoofingAngles) {
            yaw = RotationUtilCa.mc.player.rotationYaw;
            pitch = RotationUtilCa.mc.player.rotationPitch;
            isSpoofingAngles = false;
        }
    }

    public static String getDirection4D(boolean bl) {
        int n = RotationUtilCa.getDirection4D();
        if (n == 0) {
            return "South (+Z)";
        }
        if (n == 1) {
            return "West (-X)";
        }
        if (n == 2) {
            return String.valueOf(new StringBuilder().append(bl ? "\u00a7c" : "").append("North (-Z)"));
        }
        if (n == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }

    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtilCa.mc.player.posX, RotationUtilCa.mc.player.posY + (double)RotationUtilCa.mc.player.getEyeHeight(), RotationUtilCa.mc.player.posZ);
    }

    public static void rotateHead(double d, double d2, double d3, EntityPlayer entityPlayer) {
        double[] arrd = RotationUtilCa.calculateLookAt(d, d2, d3, entityPlayer);
        RotationUtilCa.mc.player.setRotationYawHead((float)arrd[0]);
        RotationUtilCa.setYawAndPitch((float)arrd[0], (float)arrd[1]);
    }

    public static void setMinRotations(float f, float f2) {
        RotationUtilCa.mc.player.rotationYaw = f;
        RotationUtilCa.mc.player.rotationYawHead = f;
        RotationUtilCa.mc.player.rotationPitch = f2;
    }

    public static void vecLookAt(Vec3d vec3d) {
        float[] arrf = RotationUtilCa.getLegitRotations(vec3d);
        RotationUtilCa.setYawAndPitch(arrf[0], arrf[1]);
    }

    public static void setYawAndPitch(float f, float f2) {
        yaw = f;
        pitch = f2;
        isSpoofingAngles = true;
    }

    public static void resetRotations() {
        try {
            yaw = RotationUtilCa.mc.player.rotationYaw;
            pitch = RotationUtilCa.mc.player.rotationPitch;
            RotationUtilCa.mc.player.rotationYawHead = RotationUtilCa.mc.player.rotationYaw;
            rotationTimer.reset();
        }
        catch (Exception exception) {
            AliceMain.LOGGER.info("Failed to reset rotations...");
        }
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = RotationUtilCa.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{RotationUtilCa.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - RotationUtilCa.mc.player.rotationYaw)), RotationUtilCa.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - RotationUtilCa.mc.player.rotationPitch))};
    }
}

