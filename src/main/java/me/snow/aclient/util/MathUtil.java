//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MathUtil
implements Util {
    private static final /* synthetic */ Random random;

    public static Vec3d roundVec(Vec3d vec3d, int n) {
        return new Vec3d(MathUtil.round(vec3d.xCoord, n), MathUtil.round(vec3d.yCoord, n), MathUtil.round(vec3d.zCoord, n));
    }

    public static boolean areVec3dsAligned(Vec3d vec3d, Vec3d vec3d2) {
        return MathUtil.areVec3dsAlignedRetarded(vec3d, vec3d2);
    }

    public static double lengthSQ(Vec3d vec3d) {
        return MathUtil.square(vec3d.xCoord) + MathUtil.square(vec3d.yCoord) + MathUtil.square(vec3d.zCoord);
    }

    public static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : Math.min(f, f3);
    }

    public static float roundFloat(double d, int n) {
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.FLOOR);
        return bigDecimal.floatValue();
    }

    public static String getTimeOfDay() {
        Calendar calendar = Calendar.getInstance();
        int n = calendar.get(11);
        if (n < 12) {
            return "Good Morning ";
        }
        if (n < 16) {
            return "Good Afternoon ";
        }
        if (n < 21) {
            return "Good Evening ";
        }
        return "Good Night ";
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        return MathUtil.interpolateEntity(entity, f).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }

    public static float cos(float f) {
        return MathHelper.cos((float)f);
    }

    public static float[] calcAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static Vec3d fromTo(Vec3d vec3d, double d, double d2, double d3) {
        return MathUtil.fromTo(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, d, d2, d3);
    }

    public static double round(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.FLOOR);
        return bigDecimal.doubleValue();
    }

    public static double[] directionSpeed(double d) {
        float f = MathUtil.mc.player.movementInput.field_192832_b;
        float f2 = MathUtil.mc.player.movementInput.moveStrafe;
        float f3 = MathUtil.mc.player.prevRotationYaw + (MathUtil.mc.player.rotationYaw - MathUtil.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    public static int clamp(int n, int n2, int n3) {
        return n < n2 ? n2 : Math.min(n, n3);
    }

    public static List<Vec3d> getBlockBlocks(Entity entity) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox();
        double d = entity.posY;
        double d2 = MathUtil.round(axisAlignedBB.minX, 0);
        double d3 = MathUtil.round(axisAlignedBB.minZ, 0);
        double d4 = MathUtil.round(axisAlignedBB.maxX, 0);
        double d5 = MathUtil.round(axisAlignedBB.maxZ, 0);
        if (d2 != d4) {
            Vec3d vec3d = new Vec3d(d2, d, d3);
            Vec3d vec3d2 = new Vec3d(d4, d, d3);
            BlockPos blockPos = new BlockPos(vec3d);
            BlockPos blockPos2 = new BlockPos(vec3d2);
            if (BlockUtil.isBlockUnSolid(blockPos) && BlockUtil.isBlockUnSolid(blockPos2)) {
                arrayList.add(vec3d);
                arrayList.add(vec3d2);
            }
            if (d3 != d5) {
                Vec3d vec3d3 = new Vec3d(d2, d, d5);
                Vec3d vec3d4 = new Vec3d(d4, d, d5);
                BlockPos blockPos3 = new BlockPos(vec3d);
                BlockPos blockPos4 = new BlockPos(vec3d2);
                if (BlockUtil.isBlockUnSolid(blockPos3) && BlockUtil.isBlockUnSolid(blockPos4)) {
                    arrayList.add(vec3d3);
                    arrayList.add(vec3d4);
                    return arrayList;
                }
            }
            if (arrayList.isEmpty()) {
                arrayList.add(entity.getPositionVector());
            }
            return arrayList;
        }
        if (d3 != d5) {
            Vec3d vec3d = new Vec3d(d2, d, d3);
            Vec3d vec3d5 = new Vec3d(d2, d, d5);
            BlockPos blockPos = new BlockPos(vec3d);
            BlockPos blockPos5 = new BlockPos(vec3d5);
            if (BlockUtil.isBlockUnSolid(blockPos) && BlockUtil.isBlockUnSolid(blockPos5)) {
                arrayList.add(vec3d);
                arrayList.add(vec3d5);
            }
            if (arrayList.isEmpty()) {
                arrayList.add(entity.getPositionVector());
            }
            return arrayList;
        }
        arrayList.add(entity.getPositionVector());
        return arrayList;
    }

    public static boolean areVec3dsAlignedRetarded(Vec3d vec3d, Vec3d vec3d2) {
        BlockPos blockPos = new BlockPos(vec3d);
        BlockPos blockPos2 = new BlockPos(vec3d2.xCoord, vec3d.yCoord, vec3d2.zCoord);
        return blockPos.equals((Object)blockPos2);
    }

    public static float getRandom(float f, float f2) {
        return MathHelper.clamp((float)(f + random.nextFloat() * f2), (float)f, (float)f2);
    }

    public static float wrapDegrees(float f) {
        return MathHelper.wrapDegrees((float)f);
    }

    public static double wrapDegrees(double d) {
        return MathHelper.wrapDegrees((double)d);
    }

    public static Vec3d calculateLine(Vec3d vec3d, Vec3d vec3d2, double d) {
        double d2 = Math.sqrt(MathUtil.multiply(vec3d2.xCoord - vec3d.xCoord) + MathUtil.multiply(vec3d2.yCoord - vec3d.yCoord) + MathUtil.multiply(vec3d2.zCoord - vec3d.zCoord));
        double d3 = (vec3d2.xCoord - vec3d.xCoord) / d2;
        double d4 = (vec3d2.yCoord - vec3d.yCoord) / d2;
        double d5 = (vec3d2.zCoord - vec3d.zCoord) / d2;
        double d6 = vec3d.xCoord + d3 * d;
        double d7 = vec3d.yCoord + d4 * d;
        double d8 = vec3d.zCoord + d5 * d;
        return new Vec3d(d6, d7, d8);
    }

    public static float[] calcAngleNoY(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = vec3d2.zCoord - vec3d.zCoord;
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d2, d)) - 90.0))};
    }

    public static float sin(float f) {
        return MathHelper.sin((float)f);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map2, boolean bl) {
        LinkedList<Map.Entry<K, V>> linkedList = new LinkedList<Map.Entry<K, V>>(map2.entrySet());
        if (bl) {
            linkedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        } else {
            linkedList.sort(Map.Entry.comparingByValue());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : linkedList) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    static {
        random = new Random();
    }

    public static double[] differentDirectionSpeed(double d) {
        Minecraft minecraft = Minecraft.getMinecraft();
        float f = minecraft.player.movementInput.field_192832_b;
        float f2 = minecraft.player.movementInput.moveStrafe;
        float f3 = minecraft.player.prevRotationYaw + (minecraft.player.rotationYaw - minecraft.player.prevRotationYaw) * minecraft.getRenderPartialTicks();
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

    public static float round(float f, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(f);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.FLOOR);
        return bigDecimal.floatValue();
    }

    public static double clamp(double d, double d2, double d3) {
        return d < d2 ? d2 : Math.min(d, d3);
    }

    public static Vec3d extrapolatePlayerPosition(EntityPlayer entityPlayer, int n) {
        Vec3d vec3d = new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ);
        Vec3d vec3d2 = new Vec3d(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
        double d = MathUtil.multiply(entityPlayer.motionX) + MathUtil.multiply(entityPlayer.motionY) + MathUtil.multiply(entityPlayer.motionZ);
        Vec3d vec3d3 = MathUtil.calculateLine(vec3d, vec3d2, d * (double)n);
        return new Vec3d(vec3d3.xCoord, entityPlayer.posY, vec3d3.zCoord);
    }

    public static float rad(float f) {
        return (float)((double)f * Math.PI / 180.0);
    }

    public static double angle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.lengthVector() * vec3d2.lengthVector();
        if (d < 1.0E-4) {
            return 0.0;
        }
        double d2 = vec3d.dotProduct(vec3d2);
        double d3 = d2 / d;
        if (d3 > 1.0) {
            return 0.0;
        }
        if (d3 < -1.0) {
            return 180.0;
        }
        return Math.acos(d3) * 180.0 / Math.PI;
    }

    public static Vec3d fromTo(double d, double d2, double d3, Vec3d vec3d) {
        return MathUtil.fromTo(d, d2, d3, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
    }

    public static double square(float f) {
        return f * f;
    }

    public static int getRandom(int n, int n2) {
        return n + random.nextInt(n2 - n + 1);
    }

    public static double getRandom(double d, double d2) {
        return MathHelper.clamp((double)(d + random.nextDouble() * d2), (double)d, (double)d2);
    }

    public static Vec3d direction(float f) {
        return new Vec3d(Math.cos(MathUtil.degToRad(f + 90.0f)), 0.0, Math.sin(MathUtil.degToRad(f + 90.0f)));
    }

    public static double square(double d) {
        return d * d;
    }

    public static double radToDeg(double d) {
        return d * (double)57.29578f;
    }

    public static double dot(Vec3d vec3d, Vec3d vec3d2) {
        return vec3d.xCoord * vec3d2.xCoord + vec3d.yCoord * vec3d2.yCoord + vec3d.zCoord * vec3d2.zCoord;
    }

    public static double getIncremental(double d, double d2) {
        double d3 = 1.0 / d2;
        return (double)Math.round(d * d3) / d3;
    }

    public static String getDirectionFromPlayer(double d, double d2) {
        double d3 = Math.toDegrees(Math.atan2(-(MathUtil.mc.player.posX - d), -(MathUtil.mc.player.posZ - d2)));
        double d4 = d3 + (double)MathUtil.mc.player.rotationYaw;
        if (d4 < 0.0) {
            d4 += 360.0;
        }
        if (d4 > 315.0 || d4 <= 45.0) {
            return "in front of you";
        }
        if (d4 > 45.0 && d4 <= 135.0) {
            return "to your left";
        }
        if (d4 > 135.0 && d4 <= 225.0) {
            return "behind you";
        }
        if (d4 > 225.0 && d4 <= 315.0) {
            return "to your right";
        }
        return String.valueOf(new StringBuilder().append((Object)ChatFormatting.OBFUSCATED).append("living in your walls"));
    }

    public static Vec3d fromTo(double d, double d2, double d3, double d4, double d5, double d6) {
        return new Vec3d(d4 - d, d5 - d2, d6 - d3);
    }

    public static float wrap(float f) {
        float f2 = f % 360.0f;
        if (f2 >= 180.0f) {
            f2 -= 360.0f;
        }
        if (f2 < -180.0f) {
            f2 += 360.0f;
        }
        return f2;
    }

    public static Vec3d interpolateEntity(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static double multiply(double d) {
        return d * d;
    }

    public static double angleBetweenVecs(Vec3d vec3d, Vec3d vec3d2) {
        double d = Math.atan2(vec3d.xCoord - vec3d2.xCoord, vec3d.zCoord - vec3d2.zCoord);
        d = -(d / Math.PI) * 360.0 / 2.0 + 180.0;
        return d;
    }

    public static double length(Vec3d vec3d) {
        return Math.sqrt(MathUtil.lengthSQ(vec3d));
    }

    public static double degToRad(double d) {
        return d * 0.01745329238474369;
    }
}

