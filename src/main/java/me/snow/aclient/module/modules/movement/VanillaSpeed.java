//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.StepEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MathUtil;
import net.minecraft.block.material.Material;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VanillaSpeed
extends Module {
    public /* synthetic */ Setting<Integer> stepHeight;
    public /* synthetic */ Setting<Boolean> vanilla;
    public /* synthetic */ Setting<Double> speed;
    public /* synthetic */ Setting<Boolean> Stepawa;
    public /* synthetic */ Setting<Boolean> turnOff;

    @Override
    public void onUpdate() {
        if (VanillaSpeed.mc.player == null || VanillaSpeed.mc.world == null) {
            return;
        }
        double[] arrd = MathUtil.directionSpeed(this.speed.getValue() / 10.0);
        VanillaSpeed.mc.player.motionX = arrd[0];
        VanillaSpeed.mc.player.motionZ = arrd[1];
    }

    private void ncpStep(double d) {
        block12: {
            double d2;
            double d3;
            double d4;
            block11: {
                d4 = VanillaSpeed.mc.player.posX;
                d3 = VanillaSpeed.mc.player.posZ;
                d2 = VanillaSpeed.mc.player.posY;
                if (!(d < 1.1)) break block11;
                double d5 = 0.42;
                double d6 = 0.75;
                if (d != 1.0) {
                    d5 *= d;
                    d6 *= d;
                    if (d5 > 0.425) {
                        d5 = 0.425;
                    }
                    if (d6 > 0.78) {
                        d6 = 0.78;
                    }
                    if (d6 < 0.49) {
                        d6 = 0.49;
                    }
                }
                VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d4, d2 + d5, d3, false));
                if (!(d2 + d6 < d2 + d)) break block12;
                VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d4, d2 + d6, d3, false));
                break block12;
            }
            if (d < 1.6) {
                for (double d7 : new double[]{0.42, 0.33, 0.24, 0.083, -0.078}) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d4, d2 += d7, d3, false));
                }
            } else if (d < 2.1) {
                for (double d8 : new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869}) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d4, d2 + d8, d3, false));
                }
            } else {
                for (double d9 : new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907}) {
                    VanillaSpeed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d4, d2 + d9, d3, false));
                }
            }
        }
    }

    @SubscribeEvent
    public void onStep(StepEvent stepEvent) {
        if (Feature.fullNullCheck()) {
            this.disable();
            return;
        }
        if (VanillaSpeed.mc.player.onGround && !VanillaSpeed.mc.player.isInsideOfMaterial(Material.WATER) && !VanillaSpeed.mc.player.isInsideOfMaterial(Material.LAVA) && VanillaSpeed.mc.player.isCollidedVertically && VanillaSpeed.mc.player.fallDistance == 0.0f && !VanillaSpeed.mc.gameSettings.keyBindJump.pressed && !VanillaSpeed.mc.player.isOnLadder() && this.Stepawa.getValue().booleanValue()) {
            stepEvent.setHeight(this.stepHeight.getValue().intValue());
            double d = VanillaSpeed.mc.player.getEntityBoundingBox().minY - VanillaSpeed.mc.player.posY;
            if (d >= 0.625) {
                if (!this.vanilla.getValue().booleanValue()) {
                    this.ncpStep(d);
                }
                if (this.turnOff.getValue().booleanValue()) {
                    this.disable();
                }
            }
        } else {
            stepEvent.setHeight(0.6f);
        }
    }

    public VanillaSpeed() {
        super("VanillaSpeed", "ec.me", Module.Category.MOVEMENT, true, false, false);
        this.speed = this.register(new Setting<Double>("Speed", 1.0, 1.0, 20.0));
        this.Stepawa = this.register(new Setting<Boolean>("Step", false));
        this.vanilla = this.register(new Setting<Boolean>("Vanilla", Boolean.valueOf(false), bl -> this.Stepawa.getValue()));
        this.stepHeight = this.register(new Setting<Integer>("Height", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(5), n -> this.Stepawa.getValue()));
        this.turnOff = this.register(new Setting<Boolean>("Disable", Boolean.valueOf(false), bl -> this.Stepawa.getValue()));
    }
}

