//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.SpeedRewrite;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LongJump
extends Module {
    private /* synthetic */ boolean walkingStatus;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ double walkingState;
    private /* synthetic */ int bypassState;
    private /* synthetic */ boolean beganJump;
    private /* synthetic */ boolean timerStatus;
    private final /* synthetic */ Setting<Boolean> shortJump;
    private final /* synthetic */ Setting<Double> speed;
    private /* synthetic */ int onGroundTracker;
    private /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Double> glide;
    private /* synthetic */ int state;
    private /* synthetic */ boolean groundTracker;
    private final /* synthetic */ Setting<Boolean> disableStrafe;
    private /* synthetic */ double totalWalkingState;
    private final /* synthetic */ Setting<GroundCheck> groundCheck;
    private /* synthetic */ double currentSpeed;
    private final /* synthetic */ Setting<Boolean> lagOff;
    private final /* synthetic */ Setting<Double> modifier;

    public double roundDecimalUp(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    @Override
    public void onDisable() {
        if (LongJump.mc.player != null && LongJump.mc.world != null && this.mode.getValue() == Mode.NCP) {
            LongJump.mc.player.onGround = false;
            LongJump.mc.player.capabilities.isFlying = false;
        }
    }

    public static double getBaseSpeed() {
        double d = 0.2873;
        if (LongJump.mc.player.isPotionActive(MobEffects.SPEED)) {
            int n = LongJump.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    @SubscribeEvent
    public void UpdateWalkingPlayerEventPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.groundTracker) {
            if (this.groundCheck.getValue() == GroundCheck.NORMAL) {
                if (LongJump.mc.player.onGround) {
                    this.groundTracker = false;
                }
            } else if (this.groundCheck.getValue() == GroundCheck.EDGEJUMP && LongJump.mc.player.onGround && !LongJump.mc.player.isSneaking() && LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, 0.0, 0.0).contract(0.001)).isEmpty()) {
                this.groundTracker = false;
            }
        } else if (this.mode.getValue() == Mode.NORMAL) {
            this.walkingState = LongJump.mc.player.posX - LongJump.mc.player.prevPosX;
            double d = LongJump.mc.player.posZ - LongJump.mc.player.prevPosZ;
            this.totalWalkingState = Math.sqrt(this.walkingState * this.walkingState + d * d);
        } else {
            double d = LongJump.mc.player.posX - LongJump.mc.player.prevPosX;
            double d2 = LongJump.mc.player.posZ - LongJump.mc.player.prevPosZ;
            this.totalWalkingState = Math.sqrt(d * d + d2 * d2);
            if (!this.walkingStatus) {
                return;
            }
            LongJump.mc.player.motionY = 0.005;
        }
    }

    @SubscribeEvent
    public void onMove(MoveEvent moveEvent) {
        if (this.groundTracker) {
            return;
        }
        if (LongJump.mc.player != mc.getRenderViewEntity()) {
            return;
        }
        switch (this.mode.getValue()) {
            case NORMAL: {
                if (LongJump.mc.player.moveStrafing <= 0.0f && LongJump.mc.player.field_191988_bg <= 0.0f) {
                    this.state = 1;
                }
                if (this.roundDecimalUp(LongJump.mc.player.posY - (double)((int)LongJump.mc.player.posY), 3) == 0.943) {
                    LongJump.mc.player.motionY -= 0.0157 * this.glide.getValue();
                    moveEvent.setY(moveEvent.getY() - 0.0157 * this.glide.getValue());
                }
                if (this.state == 1 && (LongJump.mc.player.field_191988_bg != 0.0f || LongJump.mc.player.moveStrafing != 0.0f)) {
                    this.state = 2;
                    this.currentSpeed = this.speed.getValue() * LongJump.getBaseSpeed() - 0.01;
                } else if (this.state == 2) {
                    LongJump.mc.player.motionY = 0.0848 * this.modifier.getValue();
                    moveEvent.setY(0.0848 * this.modifier.getValue());
                    this.state = 3;
                    this.currentSpeed *= 2.149802;
                } else if (this.state == 3) {
                    this.state = 4;
                    this.walkingState = 0.66 * this.totalWalkingState;
                    this.currentSpeed = this.totalWalkingState - this.walkingState;
                } else {
                    if (LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, LongJump.mc.player.motionY, 0.0)).size() > 0 || LongJump.mc.player.isCollidedVertically) {
                        this.state = 1;
                    }
                    this.currentSpeed = this.totalWalkingState - this.totalWalkingState / 159.0;
                }
                this.currentSpeed = Math.max(this.currentSpeed, LongJump.getBaseSpeed());
                float f = LongJump.mc.player.movementInput.field_192832_b;
                float f2 = LongJump.mc.player.movementInput.moveStrafe;
                float f3 = LongJump.mc.player.rotationYaw;
                if (f == 0.0f && f2 == 0.0f) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                } else if (f != 0.0f) {
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
                double d = Math.cos(Math.toRadians(f3 + 90.0f));
                double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
                moveEvent.setX((double)f * this.currentSpeed * d + (double)f2 * this.currentSpeed * d2);
                moveEvent.setZ((double)f * this.currentSpeed * d2 - (double)f2 * this.currentSpeed * d);
                this.beganJump = true;
                return;
            }
            case NCP: {
                if (this.timerStatus) {
                    if (LongJump.mc.player.onGround) {
                        this.timer.reset();
                    }
                    if (this.roundDecimalUp(LongJump.mc.player.posY - (double)((int)LongJump.mc.player.posY), 3) == 0.41) {
                        LongJump.mc.player.motionY = 0.0;
                    }
                    if (LongJump.mc.player.moveStrafing <= 0.0f && LongJump.mc.player.field_191988_bg <= 0.0f) {
                        this.bypassState = 1;
                    }
                    if (this.roundDecimalUp(LongJump.mc.player.posY - (double)((int)LongJump.mc.player.posY), 3) == 0.943) {
                        LongJump.mc.player.motionY = 0.0;
                    }
                    if (this.bypassState == 1) {
                        if (LongJump.mc.player.field_191988_bg != 0.0f || LongJump.mc.player.moveStrafing != 0.0f) {
                            this.bypassState = 2;
                            this.currentSpeed = this.speed.getValue() * LongJump.getBaseSpeed() - 0.01;
                        }
                    } else if (this.bypassState == 2) {
                        this.bypassState = 3;
                        if (!this.shortJump.getValue().booleanValue()) {
                            LongJump.mc.player.motionY = 0.424;
                        }
                        moveEvent.setY(0.424);
                        this.currentSpeed *= 2.149802;
                    } else if (this.bypassState == 3) {
                        this.bypassState = 4;
                        double d = 0.66 * (this.totalWalkingState - LongJump.getBaseSpeed());
                        this.currentSpeed = this.totalWalkingState - d;
                    } else {
                        if (LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, LongJump.mc.player.motionY, 0.0)).size() > 0 || LongJump.mc.player.isCollidedVertically) {
                            this.bypassState = 1;
                        }
                        this.currentSpeed = this.totalWalkingState - this.totalWalkingState / 159.0;
                    }
                    this.currentSpeed = Math.max(this.currentSpeed, LongJump.getBaseSpeed());
                    float f = LongJump.mc.player.movementInput.field_192832_b;
                    float f4 = LongJump.mc.player.movementInput.moveStrafe;
                    float f5 = LongJump.mc.player.rotationYaw;
                    if (f == 0.0f || f4 == 0.0f) {
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    } else {
                        if (f4 >= 1.0f) {
                            f5 += (float)(f > 0.0f ? -45 : 45);
                            f4 = 0.0f;
                        } else if (f4 <= -1.0f) {
                            f5 += (float)(f > 0.0f ? 45 : -45);
                            f4 = 0.0f;
                        }
                        if (f > 0.0f) {
                            f = 1.0f;
                        } else if (f < 0.0f) {
                            f = -1.0f;
                        }
                    }
                    double d = Math.cos(Math.toRadians(f5 + 90.0f));
                    double d3 = Math.sin(Math.toRadians(f5 + 90.0f));
                    moveEvent.setX((double)f * this.currentSpeed * d + (double)f4 * this.currentSpeed * d3);
                    moveEvent.setZ((double)f * this.currentSpeed * d3 - (double)f4 * this.currentSpeed * d);
                    if (f == 0.0f && f4 == 0.0f) {
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    }
                }
                this.beganJump = true;
                if (LongJump.mc.player.onGround) {
                    ++this.onGroundTracker;
                } else if (!LongJump.mc.player.onGround && this.onGroundTracker != 0) {
                    --this.onGroundTracker;
                }
                if (this.shortJump.getValue().booleanValue()) {
                    if (this.timer.hasPassed(35.0)) {
                        this.walkingStatus = true;
                    }
                    if (this.timer.hasPassed(2490.0)) {
                        this.walkingStatus = false;
                        this.timerStatus = false;
                        LongJump.mc.player.motionX *= 0.0;
                        LongJump.mc.player.motionZ *= 0.0;
                    }
                    if (!this.timer.hasPassed(2820.0)) {
                        return;
                    }
                    this.timerStatus = true;
                    LongJump.mc.player.motionX *= 0.0;
                    LongJump.mc.player.motionZ *= 0.0;
                    this.timer.reset();
                    break;
                }
                if (this.timer.hasPassed(480.0)) {
                    LongJump.mc.player.motionX *= 0.0;
                    LongJump.mc.player.motionZ *= 0.0;
                    this.timerStatus = false;
                }
                if (!this.timer.hasPassed(780.0)) break;
                LongJump.mc.player.motionX *= 0.0;
                LongJump.mc.player.motionZ *= 0.0;
                this.timerStatus = true;
                this.timer.reset();
            }
        }
    }

    @Override
    public void onEnable() {
        if (LongJump.mc.player != null && LongJump.mc.world != null) {
            this.currentSpeed = LongJump.getBaseSpeed();
            LongJump.mc.player.onGround = true;
        }
        this.groundTracker = this.groundCheck.getValue() != GroundCheck.NONE;
        this.walkingStatus = false;
        this.timerStatus = true;
        this.totalWalkingState = 0.0;
        this.state = 1;
        this.beganJump = false;
        if (SpeedRewrite.getInstance().isOn() && this.disableStrafe.getValue().booleanValue()) {
            Command.sendMessage("<LongJump> Disable Speed");
            SpeedRewrite.getInstance().disable();
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (this.lagOff.getValue().booleanValue() && receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.disable();
        }
    }

    public LongJump() {
        super("LongJump", "Jumps long", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.NCP));
        this.speed = this.register(new Setting<Double>("Speed", 3.0, 0.1, 20.0));
        this.modifier = this.register(new Setting<Double>("Modifier", 3.0, 0.1, 10.0));
        this.glide = this.register(new Setting<Double>("Glide", 1.0, 0.1, 10.0));
        this.disableStrafe = this.register(new Setting<Boolean>("DisableStrafe", true));
        this.groundCheck = this.register(new Setting<GroundCheck>("GroundCheck", GroundCheck.NORMAL));
        this.lagOff = this.register(new Setting<Boolean>("LagOff", false));
        this.shortJump = this.register(new Setting<Boolean>("ShortJump", false));
        this.timer = new Timer();
        this.onGroundTracker = 0;
        this.beganJump = false;
    }

    private static enum GroundCheck {
        NONE,
        NORMAL,
        EDGEJUMP;

    }

    private static enum Mode {
        NORMAL,
        NCP;

    }
}

