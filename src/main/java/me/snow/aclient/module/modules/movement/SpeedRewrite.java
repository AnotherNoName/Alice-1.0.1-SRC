//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.mixin.mixins.accessors.ICPacketPlayer;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.ElytraFlight;
import me.snow.aclient.module.modules.movement.Flight;
import me.snow.aclient.module.modules.movement.NoSlowDown;
import me.snow.aclient.module.modules.movement.PacketFlight;
import me.snow.aclient.module.modules.movement.Step;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.util.MotionUtil;
import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpeedRewrite
extends Module {
    private /* synthetic */ double latestMoveSpeed;
    private /* synthetic */ double boostSpeed;
    private final /* synthetic */ Setting<Boolean> timer;
    private /* synthetic */ int boostTicks;
    private /* synthetic */ boolean accelerate;
    private final /* synthetic */ Setting<Boolean> potionFactor;
    public final /* synthetic */ Setting<Boolean> noWeb;
    public final /* synthetic */ Setting<Boolean> noLiquids;
    private /* synthetic */ int strictTicks;
    private /* synthetic */ double moveSpeed;
    public static /* synthetic */ SpeedRewrite INSTANCE;
    private final /* synthetic */ Setting<Boolean> strictMotion;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<BaseSpeed> speed;
    private /* synthetic */ boolean offsetPackets;
    private /* synthetic */ int strafeStage;
    private /* synthetic */ int groundStage;

    @Override
    public void onEnable() {
        AliceMain.timerManager.reset();
        this.strafeStage = 4;
        this.groundStage = 2;
    }

    private boolean shouldReturn() {
        return AliceMain.moduleManager.isModuleEnabled(Freecam.class) || AliceMain.moduleManager.isModuleEnabled(ElytraFlight.class) || AliceMain.moduleManager.isModuleEnabled(Flight.class) || AliceMain.moduleManager.isModuleEnabled(PacketFlight.class);
    }

    public void resetProcess() {
        this.strafeStage = 4;
        this.groundStage = 2;
        this.moveSpeed = 0.0;
        this.latestMoveSpeed = 0.0;
        this.boostSpeed = 0.0;
        this.strictTicks = 0;
        this.boostTicks = 0;
        this.accelerate = false;
        this.offsetPackets = false;
        AliceMain.timerManager.reset();
    }

    public static SpeedRewrite getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpeedRewrite();
        }
        return INSTANCE;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.latestMoveSpeed = Math.sqrt(StrictMath.pow(SpeedRewrite.mc.player.posX - SpeedRewrite.mc.player.prevPosX, 2.0) + StrictMath.pow(SpeedRewrite.mc.player.posZ - SpeedRewrite.mc.player.prevPosZ, 2.0));
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketEntityAction && (((CPacketEntityAction)send.getPacket()).getAction().equals((Object)CPacketEntityAction.Action.STOP_SPRINTING) || ((CPacketEntityAction)send.getPacket()).getAction().equals((Object)CPacketEntityAction.Action.START_SNEAKING)) && this.strictMotion.getValue().booleanValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && ((ICPacketPlayer)send.getPacket()).isMoving() && this.offsetPackets) {
            ((ICPacketPlayer)send.getPacket()).setY(((CPacketPlayer)send.getPacket()).getY(0.0) + (double)(SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0 ? 2 : 4));
            this.offsetPackets = false;
        }
    }

    @SubscribeEvent
    public void onMove(MoveEvent moveEvent) {
        double d;
        if (this.noLiquids.getValue().booleanValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            this.resetProcess();
            return;
        }
        if (this.noWeb.getValue().booleanValue() && NoSlowDown.mc.player.isInWeb) {
            this.resetProcess();
            return;
        }
        if (SpeedRewrite.mc.player.isOnLadder() || SpeedRewrite.mc.player.capabilities.isFlying || SpeedRewrite.mc.player.isElytraFlying() || SpeedRewrite.mc.player.fallDistance > 2.0f) {
            this.resetProcess();
            return;
        }
        if (this.noLiquids.getValue().booleanValue() && (Step.mc.player.isInWater() || Step.mc.player.isInLava())) {
            return;
        }
        if (moveEvent.getStage() != 0 || this.shouldReturn()) {
            return;
        }
        if (SpeedRewrite.mc.player.isSneaking()) {
            return;
        }
        AliceMain.timerManager.reset();
        double d2 = 0.2873;
        if (this.speed.getValue().equals((Object)BaseSpeed.OLD)) {
            d2 = 0.272;
        }
        if (this.potionFactor.getValue().booleanValue()) {
            if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SPEED)) {
                d = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                d2 *= 1.0 + 0.2 * (d + 1.0);
            }
            if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
                d = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier();
                d2 /= 1.0 + 0.2 * (d + 1.0);
            }
        }
        if (!(!this.strictMotion.getValue().booleanValue() || SpeedRewrite.mc.player.isSprinting() && ((IEntityPlayerSP)SpeedRewrite.mc.player).getServerSprintState() || mc.getConnection() == null)) {
            mc.getConnection().getNetworkManager().sendPacket((Packet)new CPacketEntityAction((Entity)SpeedRewrite.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
        switch (this.mode.getValue()) {
            case ONGROUND: {
                if (SpeedRewrite.mc.player.onGround && MotionUtil.isMoving()) {
                    if (this.groundStage == 2) {
                        this.offsetPackets = true;
                        d = 2.149;
                        this.moveSpeed *= d;
                        this.groundStage = 3;
                    } else if (this.groundStage == 3) {
                        d = 0.66 * (this.latestMoveSpeed - d2);
                        this.moveSpeed = this.latestMoveSpeed - d;
                        this.groundStage = 2;
                    }
                    if (SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, 0.21, 0.0)).size() > 0 || SpeedRewrite.mc.player.isCollidedVertically) {
                        this.groundStage = 1;
                    }
                }
                this.moveSpeed = Math.max(this.moveSpeed, d2);
                float f = SpeedRewrite.mc.player.movementInput.field_192832_b;
                float f2 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float f3 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                } else if (f != 0.0f) {
                    if (f2 > 0.0f) {
                        f3 += f > 0.0f ? -45.0f : 45.0f;
                    } else if (f2 < 0.0f) {
                        f3 += f > 0.0f ? 45.0f : -45.0f;
                    }
                    f2 = 0.0f;
                    if (f > 0.0f) {
                        f = 1.0f;
                    } else if (f < 0.0f) {
                        f = -1.0f;
                    }
                }
                double d3 = Math.cos(Math.toRadians(f3));
                double d4 = -Math.sin(Math.toRadians(f3));
                moveEvent.setX((double)f * this.moveSpeed * d4 + (double)f2 * this.moveSpeed * d3);
                moveEvent.setZ((double)f * this.moveSpeed * d3 - (double)f2 * this.moveSpeed * d4);
                break;
            }
            case STRAFE: {
                if (!MotionUtil.isMoving()) break;
                if (this.timer.getValue().booleanValue()) {
                    AliceMain.timerManager.setTimer(1.088f);
                }
                if (this.strafeStage == 1) {
                    this.moveSpeed = 1.35 * d2 - 0.01;
                } else if (this.strafeStage == 2) {
                    double d5;
                    d = 0.3999999463558197;
                    if (this.potionFactor.getValue().booleanValue() && SpeedRewrite.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                        d5 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier();
                        d += (d5 + 1.0) * 0.1;
                    }
                    SpeedRewrite.mc.player.motionY = d;
                    moveEvent.setY(d);
                    d5 = 1.395;
                    if (this.accelerate) {
                        d5 = 1.6835;
                    }
                    this.moveSpeed *= d5;
                } else if (this.strafeStage == 3) {
                    d = 0.66 * (this.latestMoveSpeed - d2);
                    this.moveSpeed = this.latestMoveSpeed - d;
                    this.accelerate = !this.accelerate;
                } else {
                    if ((SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, SpeedRewrite.mc.player.motionY, 0.0)).size() > 0 || SpeedRewrite.mc.player.isCollidedVertically) && this.strafeStage > 0) {
                        this.strafeStage = MotionUtil.isMoving() ? 1 : 0;
                    }
                    this.moveSpeed = this.latestMoveSpeed - this.latestMoveSpeed / 159.0;
                }
                this.moveSpeed = Math.max(this.moveSpeed, d2);
                float f = SpeedRewrite.mc.player.movementInput.field_192832_b;
                float f4 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float f5 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                } else if (f != 0.0f) {
                    if (f4 > 0.0f) {
                        f5 += f > 0.0f ? -45.0f : 45.0f;
                    } else if (f4 < 0.0f) {
                        f5 += f > 0.0f ? 45.0f : -45.0f;
                    }
                    f4 = 0.0f;
                    if (f > 0.0f) {
                        f = 1.0f;
                    } else if (f < 0.0f) {
                        f = -1.0f;
                    }
                }
                double d6 = Math.cos(Math.toRadians(f5));
                double d7 = -Math.sin(Math.toRadians(f5));
                moveEvent.setX((double)f * this.moveSpeed * d7 + (double)f4 * this.moveSpeed * d6);
                moveEvent.setZ((double)f * this.moveSpeed * d6 - (double)f4 * this.moveSpeed * d7);
                ++this.strafeStage;
                break;
            }
            case STRAFESTRICT: {
                double d8;
                if (!MotionUtil.isMoving()) break;
                if (this.timer.getValue().booleanValue()) {
                    AliceMain.timerManager.setTimer(1.088f);
                }
                if (this.strafeStage == 1) {
                    this.moveSpeed = 1.35 * d2 - 0.01;
                } else if (this.strafeStage == 2) {
                    d = 0.3999999463558197;
                    if (this.strictMotion.getValue().booleanValue()) {
                        d = 0.42f;
                    }
                    if (this.potionFactor.getValue().booleanValue() && SpeedRewrite.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                        d8 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier();
                        d += (d8 + 1.0) * 0.1;
                    }
                    SpeedRewrite.mc.player.motionY = d;
                    moveEvent.setY(d);
                    d8 = 2.149;
                    this.moveSpeed *= d8;
                } else if (this.strafeStage == 3) {
                    d = 0.66 * (this.latestMoveSpeed - d2);
                    this.moveSpeed = this.latestMoveSpeed - d;
                } else {
                    if ((SpeedRewrite.mc.world.getCollisionBoxes((Entity)SpeedRewrite.mc.player, SpeedRewrite.mc.player.getEntityBoundingBox().offset(0.0, SpeedRewrite.mc.player.motionY, 0.0)).size() > 0 || SpeedRewrite.mc.player.isCollidedVertically) && this.strafeStage > 0) {
                        this.strafeStage = MotionUtil.isMoving() ? 1 : 0;
                    }
                    this.moveSpeed = this.latestMoveSpeed - this.latestMoveSpeed / 159.0;
                }
                this.moveSpeed = Math.max(this.moveSpeed, d2);
                d = 0.465;
                d8 = 0.44;
                if (this.potionFactor.getValue().booleanValue()) {
                    double d9;
                    if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SPEED)) {
                        d9 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                        d *= 1.0 + 0.2 * (d9 + 1.0);
                        d8 *= 1.0 + 0.2 * (d9 + 1.0);
                    }
                    if (SpeedRewrite.mc.player.isPotionActive(MobEffects.SLOWNESS)) {
                        d9 = SpeedRewrite.mc.player.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier();
                        d /= 1.0 + 0.2 * (d9 + 1.0);
                        d8 /= 1.0 + 0.2 * (d9 + 1.0);
                    }
                }
                this.moveSpeed = Math.min(this.moveSpeed, this.strictTicks > 25 ? d : d8);
                ++this.strictTicks;
                if (this.strictTicks > 50) {
                    this.strictTicks = 0;
                }
                float f = SpeedRewrite.mc.player.movementInput.field_192832_b;
                float f6 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float f7 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                } else if (f != 0.0f) {
                    if (f6 >= 1.0f) {
                        f7 += (float)(f > 0.0f ? -45 : 45);
                        f6 = 0.0f;
                    } else if (f6 <= -1.0f) {
                        f7 += (float)(f > 0.0f ? 45 : -45);
                        f6 = 0.0f;
                    }
                    if (f > 0.0f) {
                        f = 1.0f;
                    } else if (f < 0.0f) {
                        f = -1.0f;
                    }
                }
                double d10 = Math.cos(Math.toRadians(f7));
                double d11 = -Math.sin(Math.toRadians(f7));
                moveEvent.setX((double)f * this.moveSpeed * d11 + (double)f6 * this.moveSpeed * d10);
                moveEvent.setZ((double)f * this.moveSpeed * d10 - (double)f6 * this.moveSpeed * d11);
                ++this.strafeStage;
                break;
            }
            case GROUNDSTRAFE: {
                this.moveSpeed = d2;
                if (!SpeedRewrite.mc.player.isSprinting()) {
                    this.moveSpeed *= 0.7692307692;
                } else if (SpeedRewrite.mc.player.isSneaking()) {
                    this.moveSpeed *= 0.3;
                }
                float f = SpeedRewrite.mc.player.movementInput.field_192832_b;
                float f8 = SpeedRewrite.mc.player.movementInput.moveStrafe;
                float f9 = SpeedRewrite.mc.player.prevRotationYaw + (SpeedRewrite.mc.player.rotationYaw - SpeedRewrite.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
                if (!MotionUtil.isMoving()) {
                    moveEvent.setX(0.0);
                    moveEvent.setZ(0.0);
                }
                if (f != 0.0f) {
                    if (f8 > 0.0f) {
                        f9 += (float)(f > 0.0f ? -45 : 45);
                    } else if (f8 < 0.0f) {
                        f9 += (float)(f > 0.0f ? 45 : -45);
                    }
                    f8 = 0.0f;
                    if (f > 0.0f) {
                        f = 1.0f;
                    } else if (f < 0.0f) {
                        f = -1.0f;
                    }
                }
                double d12 = Math.cos(Math.toRadians(f9));
                double d13 = -Math.sin(Math.toRadians(f9));
                moveEvent.setX((double)f * this.moveSpeed * d13 + (double)f8 * this.moveSpeed * d12);
                moveEvent.setZ((double)f * this.moveSpeed * d12 - (double)f8 * this.moveSpeed * d13);
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        double d;
        double d2;
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.resetProcess();
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            d2 = StrictMath.pow(((SPacketExplosion)receive.getPacket()).getMotionX() / 8000.0f, 2.0);
            d = StrictMath.pow(((SPacketExplosion)receive.getPacket()).getMotionX() / 8000.0f, 2.0);
            this.boostSpeed = Math.sqrt(d2 + d);
            this.boostTicks = 0;
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)receive.getPacket()).getEntityID() == SpeedRewrite.mc.player.getEntityId()) {
            d2 = StrictMath.pow((float)((SPacketEntityVelocity)receive.getPacket()).getMotionX() / 8000.0f, 2.0);
            d = StrictMath.pow((float)((SPacketEntityVelocity)receive.getPacket()).getMotionX() / 8000.0f, 2.0);
            this.boostSpeed = Math.sqrt(d2 + d);
            this.boostTicks = 0;
        }
    }

    @Override
    public void onDisable() {
        this.resetProcess();
        AliceMain.timerManager.reset();
    }

    @Override
    public String getDisplayInfo() {
        switch (this.mode.getValue()) {
            case STRAFE: {
                return "Strafe";
            }
            case STRAFESTRICT: {
                return "StrafeStrict";
            }
            case GROUNDSTRAFE: {
                return "GroundStrafe";
            }
            case ONGROUND: {
                return "OnGround";
            }
        }
        return null;
    }

    public SpeedRewrite() {
        super("Speed", "AirControl etc.", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.STRAFE));
        this.speed = this.register(new Setting<BaseSpeed>("Speed", BaseSpeed.NORMAL));
        this.potionFactor = this.register(new Setting<Boolean>("PotionCheck", true));
        this.strictMotion = this.register(new Setting<Boolean>("StrictMotion", Boolean.valueOf(false), bl -> this.mode.getValue() == Mode.STRAFESTRICT));
        this.timer = this.register(new Setting<Boolean>("TimerBoost", false));
        this.noWeb = this.register(new Setting<Boolean>("NoWeb", true));
        this.noLiquids = this.register(new Setting<Boolean>("NoSpeedInWater", true));
        this.strafeStage = 4;
        this.groundStage = 2;
        INSTANCE = this;
    }

    public static enum BaseSpeed {
        NORMAL,
        OLD;

    }

    public static enum Mode {
        STRAFE,
        STRAFESTRICT,
        GROUNDSTRAFE,
        ONGROUND;

    }
}

