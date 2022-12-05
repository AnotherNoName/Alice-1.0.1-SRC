//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec2f
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util;

import java.util.Comparator;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.Wrapper;
import me.snow.aclient.util.skid.oyvey.HoleUtilSafety;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class RotationUtil
implements Util {
    private static /* synthetic */ float pitch;
    private static /* synthetic */ float yaw;

    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtil.mc.player.posX, RotationUtil.mc.player.posY + (double)RotationUtil.mc.player.getEyeHeight(), RotationUtil.mc.player.posZ);
    }

    public static float[] getRotations(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static int getDirection4D() {
        return MathHelper.floor((double)((double)(RotationUtil.mc.player.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
    }

    public static void faceEntity(Entity entity) {
        float[] arrf = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
        RotationUtil.faceYawAndPitch(arrf[0], arrf[1]);
    }

    public static boolean isInFov(Entity entity) {
        return entity == null || !(RotationUtil.mc.player.getDistanceSqToEntity(entity) < 4.0) && !(RotationUtil.yawDist(entity) < (double)(RotationUtil.getHalvedfov() + 2.0f));
    }

    public static float[] getRotationsBlock(BlockPos blockPos, EnumFacing enumFacing, boolean bl) {
        double d = (double)blockPos.getX() + 0.5 - Wrapper.mc.player.posX + (double)enumFacing.getFrontOffsetX() / 2.0;
        double d2 = (double)blockPos.getZ() + 0.5 - Wrapper.mc.player.posZ + (double)enumFacing.getFrontOffsetZ() / 2.0;
        double d3 = (double)blockPos.getY() + 0.5;
        if (bl) {
            d3 += 0.5;
        }
        double d4 = Wrapper.mc.player.posY + (double)Wrapper.mc.player.getEyeHeight() - d3;
        double d5 = MathHelper.sqrt((double)(d * d + d2 * d2));
        float f = (float)(Math.atan2(d2, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(Math.atan2(d4, d5) * 180.0 / Math.PI);
        if (f < 0.0f) {
            f += 360.0f;
        }
        return new float[]{f, f2};
    }

    public static boolean isInFov(Vec3d vec3d, Vec3d vec3d2) {
        if (RotationUtil.mc.player.rotationPitch > 30.0f ? vec3d2.yCoord > RotationUtil.mc.player.posY : RotationUtil.mc.player.rotationPitch < -30.0f && vec3d2.yCoord < RotationUtil.mc.player.posY) {
            return true;
        }
        float f = MathUtil.calcAngleNoY(vec3d, vec3d2)[0] - RotationUtil.transformYaw();
        if (f < -270.0f) {
            return true;
        }
        float f2 = (ClickGui.getInstance().customFov.getValue() != false ? ClickGui.getInstance().fov.getValue().floatValue() : RotationUtil.mc.gameSettings.fovSetting) / 2.0f;
        return f < f2 + 10.0f && f > -f2 - 10.0f;
    }

    public static float transformYaw() {
        float f = RotationUtil.mc.player.rotationYaw % 360.0f;
        if (RotationUtil.mc.player.rotationYaw > 0.0f) {
            if (f > 180.0f) {
                f = -180.0f + (f - 180.0f);
            }
        } else if (f < -180.0f) {
            f = 180.0f + (f + 180.0f);
        }
        if (f < 0.0f) {
            return 180.0f + f;
        }
        return -180.0f + f;
    }

    public static HoleUtilSafety.Hole getTargetHoleVec3D(double d) {
        return HoleUtilSafety.getHoles(d, RotationUtil.getPlayerPos(), false).stream().filter(hole -> RotationUtil.mc.player.getPositionVector().distanceTo(new Vec3d((double)hole.pos1.getX() + 0.5, RotationUtil.mc.player.posY, (double)hole.pos1.getZ() + 0.5)) <= d).min(Comparator.comparingDouble(hole -> RotationUtil.mc.player.getPositionVector().distanceTo(new Vec3d((double)hole.pos1.getX() + 0.5, RotationUtil.mc.player.posY, (double)hole.pos1.getZ() + 0.5)))).orElse(null);
    }

    public static double yawDist(BlockPos blockPos) {
        if (blockPos != null) {
            Vec3d vec3d = new Vec3d((Vec3i)blockPos).subtract(RotationUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()));
            double d = Math.abs((double)RotationUtil.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(vec3d.zCoord, vec3d.xCoord)) - 90.0)) % 360.0;
            return d > 180.0 ? 360.0 - d : d;
        }
        return 0.0;
    }

    public static void faceYawAndPitch(float f, float f2) {
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(f, f2, RotationUtil.mc.player.onGround));
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = RotationUtil.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{RotationUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - RotationUtil.mc.player.rotationYaw)), RotationUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - RotationUtil.mc.player.rotationPitch))};
    }

    public static Vec2f getRotationFromVec(Vec3d vec3d) {
        double d = Math.hypot(vec3d.xCoord, vec3d.zCoord);
        float f = (float)RotationUtil.normalizeAngle(Math.toDegrees(Math.atan2(vec3d.zCoord, vec3d.xCoord)) - 90.0);
        float f2 = (float)RotationUtil.normalizeAngle(Math.toDegrees(-Math.atan2(vec3d.yCoord, d)));
        return new Vec2f(f, f2);
    }

    public static float getFov() {
        return ClickGui.getInstance().customFov.getValue() != false ? ClickGui.getInstance().fov.getValue().floatValue() : RotationUtil.mc.gameSettings.fovSetting;
    }

    public static double yawDist(Entity entity) {
        if (entity != null) {
            Vec3d vec3d = entity.getPositionVector().addVector(0.0, (double)(entity.getEyeHeight() / 2.0f), 0.0).subtract(RotationUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()));
            double d = Math.abs((double)RotationUtil.mc.player.rotationYaw - (Math.toDegrees(Math.atan2(vec3d.zCoord, vec3d.xCoord)) - 90.0)) % 360.0;
            return d > 180.0 ? 360.0 - d : d;
        }
        return 0.0;
    }

    public static void setPlayerRotations(float f, float f2) {
        RotationUtil.mc.player.rotationYaw = f;
        RotationUtil.mc.player.rotationYawHead = f;
        RotationUtil.mc.player.rotationPitch = f2;
    }

    public static Vec3d interpolatedEyePos() {
        return RotationUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks());
    }

    public static float[] simpleFacing(EnumFacing enumFacing) {
        switch (enumFacing) {
            case DOWN: {
                return new float[]{RotationUtil.mc.player.rotationYaw, 90.0f};
            }
            case UP: {
                return new float[]{RotationUtil.mc.player.rotationYaw, -90.0f};
            }
            case NORTH: {
                return new float[]{180.0f, 0.0f};
            }
            case SOUTH: {
                return new float[]{0.0f, 0.0f};
            }
            case WEST: {
                return new float[]{90.0f, 0.0f};
            }
        }
        return new float[]{270.0f, 0.0f};
    }

    public static String getDirection4D(boolean bl) {
        int n = RotationUtil.getDirection4D();
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

    public static void restoreRotations() {
        RotationUtil.mc.player.rotationYaw = yaw;
        RotationUtil.mc.player.rotationYawHead = yaw;
        RotationUtil.mc.player.rotationPitch = pitch;
    }

    public static void faceVector(Vec3d vec3d, boolean bl) {
        float[] arrf = RotationUtil.getLegitRotations(vec3d);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], bl ? (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360) : arrf[1], RotationUtil.mc.player.onGround));
    }

    public static void faceVectorPacketInstant(Vec3d vec3d) {
        float[] arrf = RotationUtil.getLegitRotations(vec3d);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], RotationUtil.mc.player.onGround));
    }

    public static float[] getAngle(Entity entity) {
        return MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
    }

    public static boolean isInFov(BlockPos blockPos) {
        return blockPos == null || !(RotationUtil.mc.player.getDistanceSq(blockPos) < 4.0) && !(RotationUtil.yawDist(blockPos) < (double)(RotationUtil.getHalvedfov() + 2.0f));
    }

    public static float getHalvedfov() {
        return RotationUtil.getFov() / 2.0f;
    }

    public static double normalizeAngle(Double d) {
        double d2 = 0.0;
        double d3 = d;
        d3 %= 360.0;
        if (d2 >= 180.0) {
            d3 -= 360.0;
        }
        if (d3 < -180.0) {
            d3 += 360.0;
        }
        return d3;
    }

    public static Vec2f getRotationTo(Vec3d vec3d, Vec3d vec3d2) {
        return RotationUtil.getRotationFromVec(vec3d.subtract(vec3d2));
    }

    public static BlockPos getPlayerPos() {
        double d = RotationUtil.mc.player.posY - Math.floor(RotationUtil.mc.player.posY);
        return new BlockPos(RotationUtil.mc.player.posX, d > 0.8 ? Math.floor(RotationUtil.mc.player.posY) + 1.0 : Math.floor(RotationUtil.mc.player.posY), RotationUtil.mc.player.posZ);
    }

    public static void updateRotations() {
        yaw = RotationUtil.mc.player.rotationYaw;
        pitch = RotationUtil.mc.player.rotationPitch;
    }

    public static Vec3d interpolatedEyeVec() {
        return RotationUtil.mc.player.getLook(mc.getRenderPartialTicks());
    }
}

