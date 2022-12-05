//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.AxisAlignedBB
 */
package me.snow.aclient.util.ca.sc;

import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class RotationUtilSC {
    public static /* synthetic */ Minecraft mc;

    static {
        mc = Minecraft.getMinecraft();
    }

    public static void update(float f, float f2) {
        boolean bl;
        boolean bl2 = RotationUtilSC.mc.player.isSprinting();
        if (bl2 != ((IEntityPlayerSP)RotationUtilSC.mc.player).getServerSprintState()) {
            if (bl2) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtilSC.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            } else {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtilSC.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            ((IEntityPlayerSP)RotationUtilSC.mc.player).setServerSprintState(bl2);
        }
        if ((bl = RotationUtilSC.mc.player.isSneaking()) != ((IEntityPlayerSP)RotationUtilSC.mc.player).getServerSneakState()) {
            if (bl) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtilSC.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            } else {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)RotationUtilSC.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            ((IEntityPlayerSP)RotationUtilSC.mc.player).setServerSneakState(bl);
        }
        if (RotationUtilSC.mc.player == mc.getRenderViewEntity()) {
            AxisAlignedBB axisAlignedBB = RotationUtilSC.mc.player.getEntityBoundingBox();
            double d = RotationUtilSC.mc.player.posX - ((IEntityPlayerSP)RotationUtilSC.mc.player).getLastReportedPosX();
            double d2 = axisAlignedBB.minY - ((IEntityPlayerSP)RotationUtilSC.mc.player).getLastReportedPosY();
            double d3 = RotationUtilSC.mc.player.posZ - ((IEntityPlayerSP)RotationUtilSC.mc.player).getLastReportedPosZ();
            double d4 = f - ((IEntityPlayerSP)RotationUtilSC.mc.player).getLastReportedYaw();
            double d5 = f2 - ((IEntityPlayerSP)RotationUtilSC.mc.player).getLastReportedPitch();
            ((IEntityPlayerSP)RotationUtilSC.mc.player).setPositionUpdateTicks(((IEntityPlayerSP)RotationUtilSC.mc.player).getPositionUpdateTicks() + 1);
            boolean bl3 = d * d + d2 * d2 + d3 * d3 > 9.0E-4 || ((IEntityPlayerSP)RotationUtilSC.mc.player).getPositionUpdateTicks() >= 20;
            boolean bl4 = d4 != 0.0 || d5 != 0.0;
            boolean bl5 = bl4;
            if (RotationUtilSC.mc.player.isRiding()) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(RotationUtilSC.mc.player.motionX, -999.0, RotationUtilSC.mc.player.motionZ, f, f2, RotationUtilSC.mc.player.onGround));
                bl3 = false;
            } else if (bl3 && bl4) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(RotationUtilSC.mc.player.posX, axisAlignedBB.minY, RotationUtilSC.mc.player.posZ, f, f2, RotationUtilSC.mc.player.onGround));
            } else if (bl3) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(RotationUtilSC.mc.player.posX, axisAlignedBB.minY, RotationUtilSC.mc.player.posZ, RotationUtilSC.mc.player.onGround));
            } else if (bl4) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(f, f2, RotationUtilSC.mc.player.onGround));
            } else if (((IEntityPlayerSP)RotationUtilSC.mc.player).getPrevOnGround() != RotationUtilSC.mc.player.onGround) {
                RotationUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayer(RotationUtilSC.mc.player.onGround));
            }
            if (bl3) {
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setLastReportedPosX(RotationUtilSC.mc.player.posX);
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setLastReportedPosY(axisAlignedBB.minY);
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setLastReportedPosZ(RotationUtilSC.mc.player.posZ);
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setPositionUpdateTicks(0);
            }
            if (bl4) {
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setLastReportedYaw(f);
                ((IEntityPlayerSP)RotationUtilSC.mc.player).setLastReportedPitch(f2);
            }
            ((IEntityPlayerSP)RotationUtilSC.mc.player).setPrevOnGround(RotationUtilSC.mc.player.onGround);
            ((IEntityPlayerSP)RotationUtilSC.mc.player).setAutoJumpEnabled(RotationUtilSC.mc.gameSettings.autoJump);
        }
    }
}

