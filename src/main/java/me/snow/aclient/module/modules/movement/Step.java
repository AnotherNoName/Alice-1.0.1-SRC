//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 */
package me.snow.aclient.module.modules.movement;

import java.text.DecimalFormat;
import java.text.Format;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.MotionUtil;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class Step
extends Module {
    public final /* synthetic */ Setting<Boolean> strict;
    public final /* synthetic */ Setting<Boolean> noLiquids;
    private static /* synthetic */ Step INSTANCE;
    public final /* synthetic */ Setting<Mode> mode;
    public final /* synthetic */ Setting<Boolean> usetimer;
    public final /* synthetic */ Setting<Double> height;
    private /* synthetic */ int ticks;

    @Override
    public void onDisable() {
        Step.mc.player.stepHeight = 0.6f;
    }

    public Step() {
        super("Step", "Allows you to walk up blocks as if they were stairs", Module.Category.MOVEMENT, true, false, false);
        this.height = this.register(new Setting<Double>("Height", 2.0, 1.0, 10.0));
        this.mode = this.register(new Setting<Mode>("Mode", Mode.Vanilla));
        this.usetimer = this.register(new Setting<Boolean>("Timer", true));
        this.strict = this.register(new Setting<Boolean>("Strict", true));
        this.noLiquids = this.register(new Setting<Boolean>("No Liquids", true));
        this.ticks = 0;
    }

    @Override
    public void onTick() {
        Object object;
        if (Step.mc.world == null || Step.mc.player == null) {
            return;
        }
        if (this.noLiquids.getValue().booleanValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            return;
        }
        if (this.mode.getValue() == Mode.Normal) {
            if (this.ticks == 0) {
                EntityUtil.resetTimer();
            } else {
                --this.ticks;
            }
            object = MotionUtil.forward(0.1);
            boolean bl = false;
            boolean bl2 = false;
            boolean bl3 = false;
            boolean bl4 = false;
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 2.6, (double)object[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 2.4, (double)object[1])).isEmpty()) {
                bl = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 2.1, (double)object[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 1.9, (double)object[1])).isEmpty()) {
                bl2 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 1.6, (double)object[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 1.4, (double)object[1])).isEmpty()) {
                bl3 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 1.0, (double)object[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset((double)object[0], 0.6, (double)object[1])).isEmpty()) {
                bl4 = true;
            }
            if (Step.mc.player.isCollidedHorizontally && (Step.mc.player.field_191988_bg != 0.0f || Step.mc.player.moveStrafing != 0.0f) && Step.mc.player.onGround) {
                int n;
                double[] arrd;
                if (bl4 && this.height.getValue() >= 1.0) {
                    if (this.strict.getValue().booleanValue()) {
                        arrd = new double[]{0.42, 0.753, 1.0};
                        for (n = 0; n < arrd.length; ++n) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + arrd[n], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                    } else {
                        arrd = new double[]{0.42, 0.753};
                        for (n = 0; n < arrd.length; ++n) {
                            Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + arrd[n], Step.mc.player.posZ, Step.mc.player.onGround));
                        }
                    }
                    if (this.usetimer.getValue().booleanValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
                    this.ticks = 1;
                }
                if (bl3 && this.height.getValue() >= 1.5) {
                    arrd = new double[]{0.42, 0.75, 1.0, 1.16, 1.23, 1.2};
                    for (n = 0; n < arrd.length; ++n) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + arrd[n], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue().booleanValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.5, Step.mc.player.posZ);
                    this.ticks = 1;
                }
                if (bl2 && this.height.getValue() >= 2.0) {
                    arrd = new double[]{0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43};
                    for (n = 0; n < arrd.length; ++n) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + arrd[n], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue().booleanValue()) {
                        EntityUtil.setTimer(0.38f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                    this.ticks = 2;
                }
                if (bl && this.height.getValue() >= 2.5) {
                    arrd = new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907};
                    for (n = 0; n < arrd.length; ++n) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + arrd[n], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    if (this.usetimer.getValue().booleanValue()) {
                        EntityUtil.setTimer(0.2f);
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.5, Step.mc.player.posZ);
                    this.ticks = 2;
                }
            }
        }
        if (this.mode.getValue() == Mode.Vanilla) {
            object = new DecimalFormat("#");
            Step.mc.player.stepHeight = Float.parseFloat(((Format)object).format(this.height.getValue()));
        }
    }

    public static double[] forward(double d) {
        float f = Step.mc.player.movementInput.field_192832_b;
        float f2 = Step.mc.player.movementInput.moveStrafe;
        float f3 = Step.mc.player.prevRotationYaw + (Step.mc.player.rotationYaw - Step.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    public static Step getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Step();
        }
        return INSTANCE;
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    public static enum Mode {
        Vanilla,
        Normal;

    }
}

