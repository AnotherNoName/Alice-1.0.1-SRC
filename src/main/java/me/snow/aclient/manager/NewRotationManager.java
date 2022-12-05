//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.manager;

import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.ca.util.MathsUtilCa;
import me.snow.aclient.util.ca.util.RotationUtilCa;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class NewRotationManager
extends Feature {
    private /* synthetic */ float spoofedYaw;
    private /* synthetic */ float yaw;
    private /* synthetic */ float spoofedPitch;
    private /* synthetic */ float pitch;

    public float getSpoofedYaw() {
        return MathsUtilCa.wrapDegrees(this.spoofedYaw);
    }

    public void setPlayerPitch(float f) {
        NewRotationManager.mc.player.rotationPitch = f;
    }

    public float getPitch() {
        return this.pitch;
    }

    public String getDirection4D(boolean bl) {
        return RotationUtilCa.getDirection4D(bl);
    }

    public void lookAtPos(BlockPos blockPos) {
        float[] arrf = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void setPlayerYaw(float f) {
        NewRotationManager.mc.player.rotationYaw = f;
        NewRotationManager.mc.player.rotationYawHead = f;
    }

    public float getYaw() {
        return this.yaw;
    }

    public int getDirection4D() {
        return RotationUtilCa.getDirection4D();
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public void lookAtVec3d(Vec3d vec3d) {
        float[] arrf = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void setPlayerRotations(float f, float f2) {
        NewRotationManager.mc.player.rotationYaw = f;
        NewRotationManager.mc.player.rotationYawHead = f;
        NewRotationManager.mc.player.rotationPitch = f2;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public void updateRotations() {
        this.yaw = NewRotationManager.mc.player.rotationYaw;
        this.pitch = NewRotationManager.mc.player.rotationPitch;
    }

    public void restoreRotations() {
        NewRotationManager.mc.player.rotationYaw = this.yaw;
        NewRotationManager.mc.player.rotationYawHead = this.yaw;
        NewRotationManager.mc.player.rotationPitch = this.pitch;
    }

    public void onPacketReceive(SPacketPlayerPosLook sPacketPlayerPosLook) {
        this.spoofedPitch = sPacketPlayerPosLook.getPitch();
        this.spoofedYaw = sPacketPlayerPosLook.getYaw();
    }

    public void lookAtEntity(Entity entity) {
        float[] arrf = MathsUtilCa.calcAngle(NewRotationManager.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionEyes(mc.getRenderPartialTicks()));
        this.setPlayerRotations(arrf[0], arrf[1]);
    }

    public void lookAtVec3d(double d, double d2, double d3) {
        Vec3d vec3d = new Vec3d(d, d2, d3);
        this.lookAtVec3d(vec3d);
    }

    public float getSpoofedPitch() {
        return this.spoofedPitch;
    }

    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer.Rotation || send.getPacket() instanceof CPacketPlayer.PositionRotation) {
            this.spoofedPitch = ((CPacketPlayer)send.getPacket()).getPitch(0.0f);
            this.spoofedYaw = ((CPacketPlayer)send.getPacket()).getYaw(0.0f);
        }
    }
}

