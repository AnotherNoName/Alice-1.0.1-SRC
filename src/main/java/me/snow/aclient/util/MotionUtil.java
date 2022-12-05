//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 */
package me.snow.aclient.util;

import me.snow.aclient.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class MotionUtil
implements Util {
    public static double[] getMoveSpeed(double d) {
        float f = MotionUtil.mc.player.movementInput.field_192832_b;
        float f2 = MotionUtil.mc.player.movementInput.moveStrafe;
        float f3 = MotionUtil.mc.player.rotationYaw;
        if (f != 0.0f) {
            if (f2 >= 1.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
                f2 = 0.0f;
            } else if (f2 <= -1.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
                f2 = 0.0f;
            }
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
        if (!MotionUtil.isMovingMomentum()) {
            d4 = 0.0;
            d5 = 0.0;
        }
        return new double[]{d4, d5};
    }

    public static boolean isMovings() {
        return (double)Minecraft.getMinecraft().player.field_191988_bg != 0.0 || (double)Minecraft.getMinecraft().player.moveStrafing != 0.0;
    }

    public static boolean isMovingMomentum() {
        return (double)MotionUtil.mc.player.field_191988_bg != 0.0 || (double)MotionUtil.mc.player.moveStrafing != 0.0;
    }

    public static boolean isMoving() {
        return MotionUtil.mc.player.field_191988_bg != 0.0f || MotionUtil.mc.player.moveStrafing != 0.0f;
    }

    public static double[] forward(double d) {
        float f = Minecraft.getMinecraft().player.movementInput.field_192832_b;
        float f2 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float f3 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
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

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = MotionUtil.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static boolean isMoving(EntityLivingBase entityLivingBase) {
        return entityLivingBase.field_191988_bg != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
}

