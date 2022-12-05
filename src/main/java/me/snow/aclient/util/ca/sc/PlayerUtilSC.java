//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockAir
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.util.ca.sc;

import java.text.DecimalFormat;
import me.snow.aclient.AliceMain;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.module.modules.hidden.SurroundRewrite;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class PlayerUtilSC {
    private static final /* synthetic */ Minecraft mc;
    public static /* synthetic */ double[] lastLookAt;

    public static boolean isPlayerMovingLegit() {
        return PlayerUtilSC.mc.gameSettings.keyBindForward.isKeyDown() && !PlayerUtilSC.mc.player.isSneaking();
    }

    public static boolean isKeyDown(int n) {
        if (n != 0 && n < 256) {
            return n < 0 ? Mouse.isButtonDown((int)(n + 100)) : Keyboard.isKeyDown((int)n);
        }
        return false;
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = PlayerUtilSC.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        float[] arrf = new float[]{PlayerUtilSC.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - PlayerUtilSC.mc.player.rotationYaw)), PlayerUtilSC.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - PlayerUtilSC.mc.player.rotationPitch))};
        return arrf;
    }

    public static boolean isPlayerAboveVoid() {
        boolean bl = false;
        if (PlayerUtilSC.mc.player.posY <= 0.0) {
            return true;
        }
        int n = 1;
        while ((double)n < PlayerUtilSC.mc.player.posY) {
            BlockPos blockPos = new BlockPos(PlayerUtilSC.mc.player.posX, (double)n, PlayerUtilSC.mc.player.posZ);
            if (!(PlayerUtilSC.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir)) {
                bl = false;
                break;
            }
            bl = true;
            ++n;
        }
        return bl;
    }

    public static double[] directionSpeed(double d, EntityPlayerSP entityPlayerSP) {
        Minecraft minecraft = Minecraft.getMinecraft();
        float f = entityPlayerSP.movementInput.field_192832_b;
        float f2 = entityPlayerSP.movementInput.moveStrafe;
        float f3 = entityPlayerSP.prevRotationYaw + (entityPlayerSP.rotationYaw - entityPlayerSP.prevRotationYaw) * minecraft.getRenderPartialTicks();
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

    public static boolean isPlayerMoving() {
        return PlayerUtilSC.mc.gameSettings.keyBindForward.isKeyDown() || PlayerUtilSC.mc.gameSettings.keyBindBack.isKeyDown() || PlayerUtilSC.mc.gameSettings.keyBindRight.isKeyDown() || PlayerUtilSC.mc.gameSettings.keyBindLeft.isKeyDown();
    }

    public static double[] directionSpeed(double d) {
        float f = PlayerUtilSC.mc.player.movementInput.field_192832_b;
        float f2 = PlayerUtilSC.mc.player.movementInput.moveStrafe;
        float f3 = PlayerUtilSC.mc.player.prevRotationYaw + (PlayerUtilSC.mc.player.rotationYaw - PlayerUtilSC.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    public static double[] getDirectionFromYaw(float f) {
        double d = PlayerUtilSC.degreeToRadian(f);
        double d2 = Math.sin(d);
        double d3 = Math.cos(d);
        return new double[]{d2, d3};
    }

    public static float[] Method1085(Vec3d vec3d) {
        Vec3d vec3d2 = PlayerUtilSC.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        float[] arrf = new float[]{PlayerUtilSC.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - PlayerUtilSC.mc.player.rotationYaw)), PlayerUtilSC.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - PlayerUtilSC.mc.player.rotationPitch))};
        return arrf;
    }

    public static Vec3d roundPos(Vec3d vec3d) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        double d = Double.parseDouble(decimalFormat.format(vec3d.xCoord));
        double d2 = Double.parseDouble(decimalFormat.format(vec3d.yCoord));
        double d3 = Double.parseDouble(decimalFormat.format(vec3d.zCoord));
        return new Vec3d(d, d2, d3);
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

    static {
        mc = Minecraft.getMinecraft();
        lastLookAt = null;
    }

    public static void Method1081(Vec3d vec3d) {
        float[] arrf = PlayerUtilSC.Method1085(vec3d);
        PlayerUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360), PlayerUtilSC.mc.player.onGround));
        ((IEntityPlayerSP)PlayerUtilSC.mc.player).setLastReportedYaw(arrf[0]);
        ((IEntityPlayerSP)PlayerUtilSC.mc.player).setLastReportedPitch(MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360));
    }

    public static double degreeToRadian(float f) {
        return (double)f * (Math.PI / 180);
    }

    public static float[] calcAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    private static Vec3d getEyesPos() {
        return new Vec3d(PlayerUtilSC.mc.player.posX, PlayerUtilSC.mc.player.posY + (double)PlayerUtilSC.mc.player.getEyeHeight(), PlayerUtilSC.mc.player.posZ);
    }

    public static void moveToBlockCenterLuigi(boolean bl) {
        double d = Math.floor(PlayerUtilSC.mc.player.posX) + 0.5;
        double d2 = Math.floor(PlayerUtilSC.mc.player.posZ) + 0.5;
        if (bl) {
            AliceMain.movementManager.setMotion((d - SurroundRewrite.mc.player.posX) / 2.0, SurroundRewrite.mc.player.motionY, (d2 - SurroundRewrite.mc.player.posZ) / 2.0);
            PlayerUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, PlayerUtilSC.mc.player.posY, d2, PlayerUtilSC.mc.player.onGround));
        } else {
            PlayerUtilSC.mc.player.setPosition(d, PlayerUtilSC.mc.player.posY, d2);
            PlayerUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, PlayerUtilSC.mc.player.posY, d2, PlayerUtilSC.mc.player.onGround));
        }
    }

    public static void moveToBlockCenter() {
        double d = Math.floor(PlayerUtilSC.mc.player.posX) + 0.5;
        double d2 = Math.floor(PlayerUtilSC.mc.player.posZ) + 0.5;
        PlayerUtilSC.mc.player.setPosition(d, PlayerUtilSC.mc.player.posY, d2);
        PlayerUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, PlayerUtilSC.mc.player.posY, d2, PlayerUtilSC.mc.player.onGround));
    }
}

