//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 */
package me.snow.aclient.manager;

import me.snow.aclient.module.Feature;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class PositionManager
extends Feature {
    private /* synthetic */ double x;
    private /* synthetic */ boolean onground;
    private /* synthetic */ double z;
    private /* synthetic */ double y;

    public void setY(double d) {
        this.y = d;
    }

    public void setPlayerPosition(double d, double d2, double d3) {
        PositionManager.mc.player.posX = d;
        PositionManager.mc.player.posY = d2;
        PositionManager.mc.player.posZ = d3;
    }

    public void restorePosition() {
        PositionManager.mc.player.posX = this.x;
        PositionManager.mc.player.posY = this.y;
        PositionManager.mc.player.posZ = this.z;
        PositionManager.mc.player.onGround = this.onground;
    }

    public double getZ() {
        return this.z;
    }

    public void updatePosition() {
        this.x = PositionManager.mc.player.posX;
        this.y = PositionManager.mc.player.posY;
        this.z = PositionManager.mc.player.posZ;
        this.onground = PositionManager.mc.player.onGround;
    }

    public void setPositionPacket(double d, double d2, double d3, boolean bl, boolean bl2, boolean bl3) {
        PositionManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2, d3, bl));
        if (bl2) {
            PositionManager.mc.player.setPosition(d, d2, d3);
            if (bl3) {
                this.updatePosition();
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double d) {
        this.x = d;
    }

    public void setPlayerPosition(double d, double d2, double d3, boolean bl) {
        PositionManager.mc.player.posX = d;
        PositionManager.mc.player.posY = d2;
        PositionManager.mc.player.posZ = d3;
        PositionManager.mc.player.onGround = bl;
    }

    public void setZ(double d) {
        this.z = d;
    }
}

