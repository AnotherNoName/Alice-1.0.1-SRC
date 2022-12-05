//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class Flight
extends Module {
    private final /* synthetic */ Setting<Float> speed;
    private final /* synthetic */ Setting<FlightMode> mode;
    private final /* synthetic */ Setting<Boolean> motionstop;

    public Flight() {
        super("Flight", "Flight.", Module.Category.MOVEMENT, true, false, false);
        this.speed = this.register(new Setting<Float>("Speed", Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(50.0f)));
        this.mode = this.register(new Setting<FlightMode>("Mode", FlightMode.VANILLA));
        this.motionstop = this.register(new Setting<Boolean>("StopMotion", true));
    }

    @Override
    public void onEnable() {
        if (Flight.mc.player == null) {
            return;
        }
        switch (this.mode.getValue()) {
            case VANILLA: {
                Flight.mc.player.capabilities.isFlying = true;
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = true;
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        switch (this.mode.getValue()) {
            case VANILLA: {
                return "Vanilla";
            }
            case STATIC: {
                return "Static";
            }
            case PACKET: {
                return "Packet";
            }
        }
        return null;
    }

    @Override
    public void onDisable() {
        switch (this.mode.getValue()) {
            case VANILLA: {
                Flight.mc.player.capabilities.isFlying = false;
                Flight.mc.player.capabilities.setFlySpeed(0.05f);
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = false;
            }
        }
        if (this.motionstop.getValue().booleanValue()) {
            Flight.mc.player.motionX = 0.0;
            Flight.mc.player.motionZ = 0.0;
        }
    }

    public double[] moveLooking() {
        return new double[]{Flight.mc.player.rotationYaw * 360.0f / 360.0f * 180.0f / 180.0f, 0.0};
    }

    @Override
    public void onUpdate() {
        switch (this.mode.getValue()) {
            case STATIC: {
                EntityPlayerSP entityPlayerSP;
                Flight.mc.player.capabilities.isFlying = false;
                Flight.mc.player.motionX = 0.0;
                Flight.mc.player.motionY = 0.0;
                Flight.mc.player.motionZ = 0.0;
                Flight.mc.player.jumpMovementFactor = this.speed.getValue().floatValue();
                if (Flight.mc.gameSettings.keyBindJump.isKeyDown()) {
                    entityPlayerSP = Flight.mc.player;
                    entityPlayerSP.motionY += (double)this.speed.getValue().floatValue();
                }
                if (!Flight.mc.gameSettings.keyBindSneak.isKeyDown()) break;
                entityPlayerSP = Flight.mc.player;
                entityPlayerSP.motionY -= (double)this.speed.getValue().floatValue();
                break;
            }
            case VANILLA: {
                Flight.mc.player.capabilities.setFlySpeed(this.speed.getValue().floatValue() / 100.0f);
                Flight.mc.player.capabilities.isFlying = true;
                if (Flight.mc.player.capabilities.isCreativeMode) {
                    return;
                }
                Flight.mc.player.capabilities.allowFlying = true;
                break;
            }
            case PACKET: {
                int n;
                boolean bl = Flight.mc.gameSettings.keyBindForward.isKeyDown();
                boolean bl2 = Flight.mc.gameSettings.keyBindLeft.isKeyDown();
                boolean bl3 = Flight.mc.gameSettings.keyBindRight.isKeyDown();
                boolean bl4 = Flight.mc.gameSettings.keyBindBack.isKeyDown();
                if (bl2 && bl3) {
                    n = bl ? 0 : (bl4 ? 180 : -1);
                } else if (bl && bl4) {
                    n = bl2 ? -90 : (bl3 ? 90 : -1);
                } else {
                    int n2 = bl2 ? -90 : (n = bl3 ? 90 : 0);
                    if (bl) {
                        n /= 2;
                    } else if (bl4) {
                        n = 180 - n / 2;
                    }
                }
                if (n != -1 && (bl || bl2 || bl3 || bl4)) {
                    float f = Flight.mc.player.rotationYaw + (float)n;
                    Flight.mc.player.motionX = EntityUtil.getRelativeX(f) * (double)0.2f;
                    Flight.mc.player.motionZ = EntityUtil.getRelativeZ(f) * (double)0.2f;
                }
                Flight.mc.player.motionY = 0.0;
                Flight.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Flight.mc.player.posX + Flight.mc.player.motionX, Flight.mc.player.posY + (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0622 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0622 : 0.0), Flight.mc.player.posZ + Flight.mc.player.motionZ, Flight.mc.player.rotationYaw, Flight.mc.player.rotationPitch, false));
                Flight.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(Flight.mc.player.posX + Flight.mc.player.motionX, Flight.mc.player.posY - 42069.0, Flight.mc.player.posZ + Flight.mc.player.motionZ, Flight.mc.player.rotationYaw, Flight.mc.player.rotationPitch, true));
            }
        }
    }

    public static enum FlightMode {
        VANILLA,
        STATIC,
        PACKET;

    }
}

