//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.MobEffects
 *  net.minecraft.util.MovementInput
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import java.util.Objects;
import java.util.Random;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.Step;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.MotionUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.MovementInput;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GroundSpeed
extends Module {
    private /* synthetic */ double highChainVal;
    private /* synthetic */ double lowChainVal;
    public /* synthetic */ boolean changeY;
    private final /* synthetic */ Setting<Double> yPortSpeed;
    private static /* synthetic */ GroundSpeed INSTANCE;
    private /* synthetic */ float stepheight;
    public /* synthetic */ Setting<Double> unblocked;
    private /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> noShake;
    private /* synthetic */ float move;
    public /* synthetic */ boolean antiShake;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ double minY;
    public /* synthetic */ Setting<Double> zeroSpeed;
    public /* synthetic */ double startY;
    private /* synthetic */ int vanillaCounter;
    private /* synthetic */ boolean oneTime;
    public /* synthetic */ Setting<Double> blocked;
    public /* synthetic */ Setting<Boolean> useTimerAA;
    public /* synthetic */ Setting<Double> speed;
    public /* synthetic */ Setting<Boolean> useTimer;
    public /* synthetic */ Setting<Boolean> stepyport;
    public /* synthetic */ Setting<Boolean> strafeJump;
    private /* synthetic */ double bounceHeight;

    private void setInstance() {
        INSTANCE = this;
    }

    private void doAccel() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.isCollidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = this.noShake.getValue() != false && GroundSpeed.mc.player.getRidingEntity() == null;
            Random random = new Random();
            boolean bl = random.nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                this.lowChainVal += 1.0;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.275f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.35f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.375f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.4f;
                }
                if (this.lowChainVal == 7.0) {
                    this.move = 0.425f;
                }
                if (this.lowChainVal == 8.0) {
                    this.move = 0.45f;
                }
                if (this.lowChainVal == 9.0) {
                    this.move = 0.475f;
                }
                if (this.lowChainVal == 10.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 11.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 12.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 13.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 14.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 15.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 16.0) {
                    this.move = 0.545f;
                }
                if (this.lowChainVal >= 17.0) {
                    this.move = 0.545f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    AliceMain.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                this.highChainVal += 1.0;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.6f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.775f;
                }
                if (this.highChainVal == 6.0) {
                    this.move = 0.825f;
                }
                if (this.highChainVal == 7.0) {
                    this.move = 0.875f;
                }
                if (this.highChainVal == 8.0) {
                    this.move = 0.925f;
                }
                if (this.highChainVal == 9.0) {
                    this.move = 0.975f;
                }
                if (this.highChainVal == 10.0) {
                    this.move = 1.05f;
                }
                if (this.highChainVal == 11.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 12.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 13.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 14.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 15.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal == 16.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal >= 17.0) {
                    this.move = 1.175f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    if (bl) {
                        AliceMain.timerManager.setTimer(1.3f);
                    } else {
                        AliceMain.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe(this.move, (Entity)GroundSpeed.mc.player);
        } else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.antiShake = false;
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.speedOff();
        }
    }

    private boolean shouldReturn() {
        return AliceMain.moduleManager.isModuleEnabled("Freecam") || AliceMain.moduleManager.isModuleEnabled("Phase") || AliceMain.moduleManager.isModuleEnabled("ElytraFlight") || AliceMain.moduleManager.isModuleEnabled("Strafe") || AliceMain.moduleManager.isModuleEnabled("Flight");
    }

    public static GroundSpeed getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GroundSpeed();
        }
        return INSTANCE;
    }

    @SubscribeEvent
    public void onMode(MoveEvent moveEvent) {
        if (!(this.shouldReturn() || moveEvent.getStage() != 0 || this.mode.getValue() != Mode.INSTANT || GroundSpeed.nullCheck() || GroundSpeed.mc.player.isSneaking() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava() || GroundSpeed.mc.player.movementInput.field_192832_b == 0.0f && GroundSpeed.mc.player.movementInput.moveStrafe == 0.0f)) {
            if (GroundSpeed.mc.player.onGround && this.strafeJump.getValue().booleanValue()) {
                GroundSpeed.mc.player.motionY = 0.4;
                moveEvent.setY(0.4);
            }
            MovementInput movementInput = GroundSpeed.mc.player.movementInput;
            float f = movementInput.field_192832_b;
            float f2 = movementInput.moveStrafe;
            float f3 = GroundSpeed.mc.player.rotationYaw;
            if ((double)f == 0.0 && (double)f2 == 0.0) {
                moveEvent.setX(0.0);
                moveEvent.setZ(0.0);
            } else {
                if ((double)f != 0.0) {
                    if ((double)f2 > 0.0) {
                        f3 += (float)((double)f > 0.0 ? -45 : 45);
                    } else if ((double)f2 < 0.0) {
                        f3 += (float)((double)f > 0.0 ? 45 : -45);
                    }
                    f2 = 0.0f;
                }
                f2 = f2 == 0.0f ? f2 : ((double)f2 > 0.0 ? 1.0f : -1.0f);
                double d = Math.cos(Math.toRadians(f3 + 90.0f));
                double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
                moveEvent.setX((double)f * EntityUtil.getMaxSpeed() * d + (double)f2 * EntityUtil.getMaxSpeed() * d2);
                moveEvent.setZ((double)f * EntityUtil.getMaxSpeed() * d2 - (double)f2 * EntityUtil.getMaxSpeed() * d);
            }
        }
    }

    private double getJumpBoostModifier() {
        double d = 0.0;
        if (GroundSpeed.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            int n = Objects.requireNonNull(GroundSpeed.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST)).getAmplifier();
            d *= 1.0 + 0.2 * (double)n;
        }
        return d;
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    private void doBoost() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.isCollidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = this.noShake.getValue() != false && GroundSpeed.mc.player.getRidingEntity() == null;
            Random random = new Random();
            boolean bl = random.nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                this.lowChainVal += 1.0;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.15f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.2f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.225f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.25f;
                }
                if (this.lowChainVal >= 7.0) {
                    this.move = 0.27895f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    AliceMain.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                this.highChainVal += 1.0;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.325f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.4f;
                }
                if (this.highChainVal >= 6.0) {
                    this.move = 0.43395f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    if (bl) {
                        AliceMain.timerManager.setTimer(1.3f);
                    } else {
                        AliceMain.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe(this.move, (Entity)GroundSpeed.mc.player);
        } else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.antiShake = false;
            this.speedOff();
        }
    }

    @Override
    public void onToggle() {
        if (this.mode.getValue() == Mode.YPORT) {
            Step.mc.player.stepHeight = 0.6f;
            GroundSpeed.mc.player.motionY = -3.0;
        }
    }

    static {
        INSTANCE = new GroundSpeed();
    }

    @Override
    public void onDisable() {
        if (this.mode.getValue() == Mode.ONGROUND || this.mode.getValue() == Mode.BOOST) {
            GroundSpeed.mc.player.motionY = -0.1;
        }
        this.changeY = false;
        AliceMain.timerManager.setTimer(1.0f);
        this.highChainVal = 0.0;
        this.lowChainVal = 0.0;
        this.antiShake = false;
        if (this.mode.getValue() == Mode.YPORT) {
            this.timer.reset();
            EntityUtil.resetTimer();
        }
    }

    @Override
    public void onUpdate() {
        if (this.mode.getValue() == Mode.YPORT) {
            if (GroundSpeed.mc.player.isSneaking() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava() || GroundSpeed.mc.player.isOnLadder() || AliceMain.moduleManager.isModuleEnabled("Strafe")) {
                return;
            }
            if (GroundSpeed.mc.player == null || GroundSpeed.mc.world == null) {
                this.disable();
                return;
            }
            this.handleYPortSpeed();
            if ((!GroundSpeed.mc.player.isOnLadder() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava()) && this.stepyport.getValue().booleanValue()) {
                Step.mc.player.stepHeight = this.stepheight;
                return;
            }
        }
        if (this.shouldReturn() || GroundSpeed.mc.player.isSneaking() || GroundSpeed.mc.player.isInWater() || GroundSpeed.mc.player.isInLava()) {
            return;
        }
        switch (this.mode.getValue()) {
            case BOOST: {
                this.doBoost();
                break;
            }
            case ACCEL: {
                this.doAccel();
                break;
            }
            case ONGROUND: {
                this.doOnground();
            }
        }
    }

    private boolean vanilla() {
        return GroundSpeed.mc.player.onGround;
    }

    public GroundSpeed() {
        super("GroundSpeed", "Makes you faster", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.INSTANT));
        this.useTimerAA = this.register(new Setting<Boolean>("YportUseTimer", Boolean.valueOf(true), bl -> this.mode.getValue() == Mode.YPORT));
        this.yPortSpeed = this.register(new Setting<Double>("YportSpeed", Double.valueOf(0.1), Double.valueOf(0.0), Double.valueOf(1.0), d -> this.mode.getValue() == Mode.YPORT));
        this.stepyport = this.register(new Setting<Boolean>("YportStep", Boolean.valueOf(true), bl -> this.mode.getValue() == Mode.YPORT));
        this.strafeJump = this.register(new Setting<Object>("Jump", Boolean.FALSE, object -> this.mode.getValue() == Mode.INSTANT));
        this.noShake = this.register(new Setting<Object>("NoShake", Boolean.TRUE, object -> this.mode.getValue() != Mode.INSTANT));
        this.useTimer = this.register(new Setting<Object>("UseTimer", Boolean.FALSE, object -> this.mode.getValue() != Mode.INSTANT));
        this.zeroSpeed = this.register(new Setting<Object>("0-Speed", Double.valueOf(0.0), Double.valueOf(0.0), Double.valueOf(100.0), object -> this.mode.getValue() == Mode.VANILLA));
        this.speed = this.register(new Setting<Object>("Speed", Double.valueOf(10.0), Double.valueOf(0.1), Double.valueOf(100.0), object -> this.mode.getValue() == Mode.VANILLA));
        this.blocked = this.register(new Setting<Object>("Blocked", Double.valueOf(10.0), Double.valueOf(0.0), Double.valueOf(100.0), object -> this.mode.getValue() == Mode.VANILLA));
        this.unblocked = this.register(new Setting<Object>("Unblocked", Double.valueOf(10.0), Double.valueOf(0.0), Double.valueOf(100.0), object -> this.mode.getValue() == Mode.VANILLA));
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        this.timer = new Timer();
        this.stepheight = 2.0f;
        this.setInstance();
    }

    private void handleYPortSpeed() {
        if (!MotionUtil.isMoving((EntityLivingBase)GroundSpeed.mc.player) || GroundSpeed.mc.player.isInWater() && GroundSpeed.mc.player.isInLava() || GroundSpeed.mc.player.isCollidedHorizontally) {
            return;
        }
        if (GroundSpeed.mc.player.onGround) {
            if (this.useTimerAA.getValue().booleanValue()) {
                EntityUtil.setTimer(1.15f);
            }
            GroundSpeed.mc.player.jump();
            MotionUtil.setSpeed((EntityLivingBase)GroundSpeed.mc.player, MotionUtil.getBaseMoveSpeed() + this.yPortSpeed.getValue());
        } else {
            GroundSpeed.mc.player.motionY = -1.0;
            EntityUtil.resetTimer();
        }
    }

    private void doOnground() {
        this.bounceHeight = 0.4;
        this.move = 0.26f;
        if (GroundSpeed.mc.player.onGround) {
            this.startY = GroundSpeed.mc.player.posY;
        }
        if (EntityUtil.getEntitySpeed((Entity)GroundSpeed.mc.player) <= 1.0) {
            this.lowChainVal = 1.0;
            this.highChainVal = 1.0;
        }
        if (EntityUtil.isEntityMoving((Entity)GroundSpeed.mc.player) && !GroundSpeed.mc.player.isCollidedHorizontally && !BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player) && BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            this.oneTime = true;
            this.antiShake = this.noShake.getValue() != false && GroundSpeed.mc.player.getRidingEntity() == null;
            Random random = new Random();
            boolean bl = random.nextBoolean();
            if (GroundSpeed.mc.player.posY >= this.startY + this.bounceHeight) {
                GroundSpeed.mc.player.motionY = -this.bounceHeight;
                this.lowChainVal += 1.0;
                if (this.lowChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.lowChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.lowChainVal == 3.0) {
                    this.move = 0.275f;
                }
                if (this.lowChainVal == 4.0) {
                    this.move = 0.35f;
                }
                if (this.lowChainVal == 5.0) {
                    this.move = 0.375f;
                }
                if (this.lowChainVal == 6.0) {
                    this.move = 0.4f;
                }
                if (this.lowChainVal == 7.0) {
                    this.move = 0.425f;
                }
                if (this.lowChainVal == 8.0) {
                    this.move = 0.45f;
                }
                if (this.lowChainVal == 9.0) {
                    this.move = 0.475f;
                }
                if (this.lowChainVal == 10.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 11.0) {
                    this.move = 0.5f;
                }
                if (this.lowChainVal == 12.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 13.0) {
                    this.move = 0.525f;
                }
                if (this.lowChainVal == 14.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 15.0) {
                    this.move = 0.535f;
                }
                if (this.lowChainVal == 16.0) {
                    this.move = 0.545f;
                }
                if (this.lowChainVal >= 17.0) {
                    this.move = 0.545f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    AliceMain.timerManager.setTimer(1.0f);
                }
            }
            if (GroundSpeed.mc.player.posY == this.startY) {
                GroundSpeed.mc.player.motionY = this.bounceHeight;
                this.highChainVal += 1.0;
                if (this.highChainVal == 1.0) {
                    this.move = 0.075f;
                }
                if (this.highChainVal == 2.0) {
                    this.move = 0.175f;
                }
                if (this.highChainVal == 3.0) {
                    this.move = 0.375f;
                }
                if (this.highChainVal == 4.0) {
                    this.move = 0.6f;
                }
                if (this.highChainVal == 5.0) {
                    this.move = 0.775f;
                }
                if (this.highChainVal == 6.0) {
                    this.move = 0.825f;
                }
                if (this.highChainVal == 7.0) {
                    this.move = 0.875f;
                }
                if (this.highChainVal == 8.0) {
                    this.move = 0.925f;
                }
                if (this.highChainVal == 9.0) {
                    this.move = 0.975f;
                }
                if (this.highChainVal == 10.0) {
                    this.move = 1.05f;
                }
                if (this.highChainVal == 11.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 12.0) {
                    this.move = 1.1f;
                }
                if (this.highChainVal == 13.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 14.0) {
                    this.move = 1.15f;
                }
                if (this.highChainVal == 15.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal == 16.0) {
                    this.move = 1.175f;
                }
                if (this.highChainVal >= 17.0) {
                    this.move = 1.2f;
                }
                if (this.useTimer.getValue().booleanValue()) {
                    if (bl) {
                        AliceMain.timerManager.setTimer(1.3f);
                    } else {
                        AliceMain.timerManager.setTimer(1.0f);
                    }
                }
            }
            EntityUtil.moveEntityStrafe(this.move, (Entity)GroundSpeed.mc.player);
        } else {
            if (this.oneTime) {
                GroundSpeed.mc.player.motionY = -0.1;
                this.oneTime = false;
            }
            this.antiShake = false;
            this.highChainVal = 0.0;
            this.lowChainVal = 0.0;
            this.speedOff();
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.mode.getValue() != Mode.VANILLA || GroundSpeed.nullCheck()) {
            return;
        }
        switch (updateWalkingPlayerEvent.getStage()) {
            case 0: {
                int n = this.vanillaCounter = this.vanilla() ? (this.vanillaCounter = this.vanillaCounter + 1) : 0;
                if (this.vanillaCounter != 4) break;
                this.changeY = true;
                this.minY = GroundSpeed.mc.player.getEntityBoundingBox().minY + (GroundSpeed.mc.world.getBlockState(GroundSpeed.mc.player.getPosition()).getMaterial().blocksMovement() ? -this.blocked.getValue().doubleValue() / 10.0 : this.unblocked.getValue() / 10.0) + this.getJumpBoostModifier();
                return;
            }
            case 1: {
                if (this.vanillaCounter == 3) {
                    GroundSpeed.mc.player.motionX *= this.zeroSpeed.getValue() / 10.0;
                    GroundSpeed.mc.player.motionZ *= this.zeroSpeed.getValue() / 10.0;
                    break;
                }
                if (this.vanillaCounter != 4) break;
                GroundSpeed.mc.player.motionX /= this.speed.getValue() / 10.0;
                GroundSpeed.mc.player.motionZ /= this.speed.getValue() / 10.0;
                this.vanillaCounter = 2;
            }
        }
    }

    private void speedOff() {
        float f = (float)Math.toRadians(GroundSpeed.mc.player.rotationYaw);
        if (BlockUtil.isBlockAboveEntitySolid((Entity)GroundSpeed.mc.player)) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                GroundSpeed.mc.player.motionX -= (double)MathUtil.sin(f) * 0.15;
                GroundSpeed.mc.player.motionZ += (double)MathUtil.cos(f) * 0.15;
            }
        } else if (GroundSpeed.mc.player.isCollidedHorizontally) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                GroundSpeed.mc.player.motionX -= (double)MathUtil.sin(f) * 0.03;
                GroundSpeed.mc.player.motionZ += (double)MathUtil.cos(f) * 0.03;
            }
        } else if (!BlockUtil.isBlockBelowEntitySolid((Entity)GroundSpeed.mc.player)) {
            if (GroundSpeed.mc.gameSettings.keyBindForward.isKeyDown() && !GroundSpeed.mc.gameSettings.keyBindSneak.isKeyDown() && GroundSpeed.mc.player.onGround) {
                GroundSpeed.mc.player.motionX -= (double)MathUtil.sin(f) * 0.03;
                GroundSpeed.mc.player.motionZ += (double)MathUtil.cos(f) * 0.03;
            }
        } else {
            GroundSpeed.mc.player.motionX = 0.0;
            GroundSpeed.mc.player.motionZ = 0.0;
        }
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().equals(this.mode) && this.mode.getPlannedValue() == Mode.INSTANT) {
            GroundSpeed.mc.player.motionY = -0.1;
        }
    }

    public static enum Mode {
        INSTANT,
        ONGROUND,
        ACCEL,
        BOOST,
        VANILLA,
        YPORT;

    }
}

