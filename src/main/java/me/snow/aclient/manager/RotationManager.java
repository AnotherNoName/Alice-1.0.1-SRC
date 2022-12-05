//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.manager;

import me.snow.aclient.module.Feature;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RotationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationManager
extends Feature {
    private /* synthetic */ float yaw;
    private /* synthetic */ float pitch;

    public void lookAtVec3d(double d, double d2, double d3) {
        Vec3d vec3d = new Vec3d(d, d2, d3);
        this.lookAtVec3d(vec3d);
    }

    public static float[] calculateAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        float f = (float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0));
        float f2 = (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)));
        if (f2 > 90.0f) {
            f2 = 90.0f;
        } else if (f2 < -90.0f) {
            f2 = -90.0f;
        }
        return new float[]{f, f2};
    }

    public void setPlayerPitch(float f) {
        RotationManager.mc.player.rotationPitch = f;
    }

    public void lookAtPos(BlockPos blockPos) {
        float[] arrf = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void restoreRotations() {
        RotationManager.mc.player.rotationYaw = this.yaw;
        RotationManager.mc.player.rotationYawHead = this.yaw;
        RotationManager.mc.player.rotationPitch = this.pitch;
    }

    public void setPlayerYaw(float f) {
        RotationManager.mc.player.rotationYaw = f;
        RotationManager.mc.player.rotationYawHead = f;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public String getDirection4D(boolean bl) {
        return RotationUtil.getDirection4D(bl);
    }

    public void updateRotations() {
        this.yaw = RotationManager.mc.player.rotationYaw;
        this.pitch = RotationManager.mc.player.rotationPitch;
    }

    public void lookAtVec3d(Vec3d vec3d) {
        float[] arrf = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public void lookAtEntity(Entity entity) {
        float[] arrf = MathUtil.calcAngle(RotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public void setPlayerRotations(float f, float f2) {
        RotationManager.mc.player.rotationYaw = f;
        RotationManager.mc.player.rotationYawHead = f;
        RotationManager.mc.player.rotationPitch = f2;
    }

    public void lookAtXYZ(double d, double d2, double d3) {
        Vec3d vec3d = new Vec3d(d, d2, d3);
        this.lookAtVec3d(vec3d);
    }

    public int getDirection4D() {
        return RotationUtil.getDirection4D();
    }
}

