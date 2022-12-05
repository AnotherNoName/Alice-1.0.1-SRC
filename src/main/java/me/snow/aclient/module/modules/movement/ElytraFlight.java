//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.MoverType
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemElytra
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.module.modules.movement;

import java.util.Random;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ElytraEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class ElytraFlight
extends Module {
    private /* synthetic */ boolean rSpeed;
    private /* synthetic */ Setting<Boolean> boostTimer;
    private /* synthetic */ Setting<Float> staticDelay;
    public /* synthetic */ Setting<Boolean> forceHeight;
    private /* synthetic */ Timer strictTimer;
    private /* synthetic */ double height;
    private /* synthetic */ Setting<Boolean> instantFly;
    private /* synthetic */ boolean hasTouchedGround;
    private /* synthetic */ boolean isJumping;
    public /* synthetic */ Setting<Float> speed;
    private /* synthetic */ Timer rocketTimer;
    private /* synthetic */ Timer instantFlyTimer;
    private /* synthetic */ Setting<Float> timeout;
    public /* synthetic */ Setting<Boolean> cruiseControl;
    private /* synthetic */ Setting<Integer> minSpeed;
    public /* synthetic */ Setting<Float> minUpSpeed;
    private /* synthetic */ Setting<Float> triggerHeight;
    public /* synthetic */ Setting<Boolean> noDrag;
    public /* synthetic */ Setting<Float> factor;
    public static /* synthetic */ ElytraFlight INSTANCE;
    private /* synthetic */ Setting<Boolean> groundSafety;
    public /* synthetic */ Setting<Float> maxSpeed;
    public /* synthetic */ Setting<Float> downFactor;
    private /* synthetic */ Timer staticTimer;
    private final /* synthetic */ Random random;
    private /* synthetic */ Setting<Integer> manualHeight;
    public /* synthetic */ Setting<Boolean> freeze;
    public /* synthetic */ Setting<Float> upFactor;
    private /* synthetic */ double curSpeed;
    private /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> speedLimit;
    private /* synthetic */ Setting<Boolean> autoSwitch;
    private /* synthetic */ Setting<Float> packetDelay;
    public /* synthetic */ double tempSpeed;
    private static /* synthetic */ boolean hasElytra;
    private /* synthetic */ Setting<Float> sneakDownSpeed;
    public /* synthetic */ Setting<Boolean> stopMotion;

    @Override
    public void onDisable() {
        if (ElytraFlight.mc.player != null) {
            if (!ElytraFlight.mc.player.isCreative()) {
                ElytraFlight.mc.player.capabilities.allowFlying = false;
            }
            ElytraFlight.mc.player.capabilities.isFlying = false;
        }
        ElytraFlight.mc.timer.field_194149_e = 50.0f;
        hasElytra = false;
    }

    static {
        INSTANCE = new ElytraFlight();
        hasElytra = false;
    }

    @Override
    public void onEnable() {
        this.rSpeed = false;
        this.curSpeed = 0.0;
        if (ElytraFlight.mc.player != null) {
            this.height = ElytraFlight.mc.player.posY;
            if (!ElytraFlight.mc.player.isCreative()) {
                ElytraFlight.mc.player.capabilities.allowFlying = false;
            }
            ElytraFlight.mc.player.capabilities.isFlying = false;
        }
        this.isJumping = false;
        hasElytra = false;
    }

    private boolean checkIfBlockInBB(int n) {
        for (int i = MathHelper.floor((double)ElytraFlight.mc.player.getEntityBoundingBox().minX); i < MathHelper.ceil((double)ElytraFlight.mc.player.getEntityBoundingBox().maxX); ++i) {
            for (int j = MathHelper.floor((double)ElytraFlight.mc.player.getEntityBoundingBox().minZ); j < MathHelper.ceil((double)ElytraFlight.mc.player.getEntityBoundingBox().maxZ); ++j) {
                IBlockState iBlockState = ElytraFlight.mc.world.getBlockState(new BlockPos(i, n, j));
                if (iBlockState.getBlock() == Blocks.AIR) continue;
                return false;
            }
        }
        return true;
    }

    public ElytraFlight() {
        super("ElytraFlight", "Makes Elytra Flight better", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.BOOST));
        this.groundSafety = this.register(new Setting<Boolean>("GroundSafety", Boolean.FALSE, bl -> this.mode.getValue() == Mode.FIREWORK));
        this.packetDelay = this.register(new Setting<Float>("Limit", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.mode.getValue() == Mode.BOOST));
        this.staticDelay = this.register(new Setting<Float>("Delay", Float.valueOf(5.0f), Float.valueOf(0.1f), Float.valueOf(20.0f), f -> this.mode.getValue() == Mode.BOOST));
        this.timeout = this.register(new Setting<Float>("Timeout", Float.valueOf(0.5f), Float.valueOf(0.1f), Float.valueOf(1.0f), f -> this.mode.getValue() == Mode.BOOST));
        this.stopMotion = this.register(new Setting<Boolean>("StopMotion", Boolean.TRUE, bl -> this.mode.getValue() == Mode.BOOST));
        this.freeze = this.register(new Setting<Boolean>("Freeze", Boolean.FALSE, bl -> this.mode.getValue() == Mode.BOOST));
        this.cruiseControl = this.register(new Setting<Boolean>("CruiseControl", false));
        this.minUpSpeed = this.register(new Setting<Float>("MinUpSpeed", Float.valueOf(0.5f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.mode.getValue() == Mode.BOOST && this.cruiseControl.getValue() != false));
        this.autoSwitch = this.register(new Setting<Boolean>("AutoSwitch", Boolean.FALSE, bl -> this.mode.getValue() == Mode.FIREWORK));
        this.factor = this.register(new Setting<Float>("Factor", Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(50.0f)));
        this.minSpeed = this.register(new Setting<Integer>("MinSpeed", Integer.valueOf(20), Integer.valueOf(1), Integer.valueOf(50), n -> this.mode.getValue() == Mode.FIREWORK));
        this.upFactor = this.register(new Setting<Float>("UpFactor", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.downFactor = this.register(new Setting<Float>("DownFactor", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.forceHeight = this.register(new Setting<Boolean>("ForceHeight", Boolean.FALSE, bl -> this.mode.getValue() == Mode.FIREWORK || this.mode.getValue() == Mode.BOOST && this.cruiseControl.getValue() != false));
        this.manualHeight = this.register(new Setting<Integer>("TargetY", Integer.valueOf(120), Integer.valueOf(1), Integer.valueOf(256), n -> (this.mode.getValue() == Mode.FIREWORK || this.mode.getValue() == Mode.BOOST && this.cruiseControl.getValue() != false) && this.forceHeight.getValue() != false));
        this.triggerHeight = this.register(new Setting<Float>("TriggerHeight", Float.valueOf(0.3f), Float.valueOf(0.05f), Float.valueOf(1.0f), f -> this.mode.getValue() == Mode.FIREWORK && this.groundSafety.getValue() != false));
        this.speed = this.register(new Setting<Float>("Speed", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(10.0f), f -> this.mode.getValue() == Mode.CONTROL));
        this.sneakDownSpeed = this.register(new Setting<Float>("DownSpeed", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(10.0f), f -> this.mode.getValue() == Mode.CONTROL));
        this.instantFly = this.register(new Setting<Boolean>("InstantFly", true));
        this.boostTimer = this.register(new Setting<Boolean>("Timer", Boolean.TRUE, bl -> this.mode.getValue() == Mode.BOOST));
        this.speedLimit = this.register(new Setting<Boolean>("SpeedLimit", Boolean.TRUE, bl -> this.mode.getValue() != Mode.FIREWORK));
        this.maxSpeed = this.register(new Setting<Float>("MaxSpeed", Float.valueOf(2.5f), Float.valueOf(0.1f), Float.valueOf(10.0f), f -> this.speedLimit.getValue() != false && this.mode.getValue() != Mode.FIREWORK));
        this.noDrag = new Setting<Boolean>("NoDrag", Boolean.FALSE, bl -> this.mode.getValue() != Mode.FIREWORK);
        this.random = new Random();
        this.instantFlyTimer = new Timer();
        this.staticTimer = new Timer();
        this.rocketTimer = new Timer();
        this.strictTimer = new Timer();
        this.isJumping = false;
        this.hasTouchedGround = false;
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    public static double[] directionSpeed(double d) {
        float f = ElytraFlight.mc.player.movementInput.field_192832_b;
        float f2 = ElytraFlight.mc.player.movementInput.moveStrafe;
        float f3 = ElytraFlight.mc.player.prevRotationYaw + (ElytraFlight.mc.player.rotationYaw - ElytraFlight.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    @SubscribeEvent
    public void onElytra(ElytraEvent elytraEvent) {
        if (ElytraFlight.mc.world == null || ElytraFlight.mc.player == null || !hasElytra || !ElytraFlight.mc.player.isElytraFlying()) {
            return;
        }
        if (this.mode.getValue() == Mode.FIREWORK) {
            return;
        }
        if (elytraEvent.getEntity() == ElytraFlight.mc.player && ElytraFlight.mc.player.isServerWorld() || ElytraFlight.mc.player.canPassengerSteer() && !ElytraFlight.mc.player.isInWater() || ElytraFlight.mc.player != null && ElytraFlight.mc.player.capabilities.isFlying && !ElytraFlight.mc.player.isInLava() || ElytraFlight.mc.player.capabilities.isFlying && ElytraFlight.mc.player.isElytraFlying()) {
            elytraEvent.setCanceled(true);
            if (this.mode.getValue() != Mode.BOOST) {
                double d;
                Vec3d vec3d = ElytraFlight.mc.player.getLookVec();
                float f = ElytraFlight.mc.player.rotationPitch * ((float)Math.PI / 180);
                double d2 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
                double d3 = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
                double d4 = vec3d.lengthVector();
                float f2 = MathHelper.cos((float)f);
                f2 = (float)((double)f2 * (double)f2 * Math.min(1.0, d4 / 0.4));
                if (this.mode.getValue() != Mode.CONTROL) {
                    ElytraFlight.mc.player.motionY += -0.08 + (double)f2 * (0.06 / (double)this.downFactor.getValue().floatValue());
                }
                if (this.mode.getValue() == Mode.CONTROL) {
                    if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        ElytraFlight.mc.player.motionY = -this.sneakDownSpeed.getValue().floatValue();
                    } else if (!ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                        ElytraFlight.mc.player.motionY = -3.0E-14 * (double)this.downFactor.getValue().floatValue();
                    }
                } else if (this.mode.getValue() != Mode.CONTROL && ElytraFlight.mc.player.motionY < 0.0 && d2 > 0.0) {
                    d = ElytraFlight.mc.player.motionY * -0.1 * (double)f2;
                    ElytraFlight.mc.player.motionY += d;
                    ElytraFlight.mc.player.motionX += vec3d.xCoord * d / d2 * (double)this.factor.getValue().floatValue();
                    ElytraFlight.mc.player.motionZ += vec3d.zCoord * d / d2 * (double)this.factor.getValue().floatValue();
                }
                if (f < 0.0f && this.mode.getValue() != Mode.CONTROL) {
                    double d5 = d3 * (double)(-MathHelper.sin((float)f)) * 0.04;
                    ElytraFlight.mc.player.motionY += d5 * 3.2 * (double)this.upFactor.getValue().floatValue();
                    ElytraFlight.mc.player.motionX -= vec3d.xCoord * d5 / d2;
                    ElytraFlight.mc.player.motionZ -= vec3d.zCoord * d5 / d2;
                } else if (this.mode.getValue() == Mode.CONTROL && ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                    if (d3 > (double)(this.upFactor.getValue().floatValue() / this.upFactor.getMax().floatValue())) {
                        double d6 = d3 * 0.01325;
                        ElytraFlight.mc.player.motionY += d6 * 3.2;
                        ElytraFlight.mc.player.motionX -= vec3d.xCoord * d6 / d2;
                        ElytraFlight.mc.player.motionZ -= vec3d.zCoord * d6 / d2;
                    } else {
                        double[] arrd = ElytraFlight.directionSpeed(this.speed.getValue().floatValue());
                        ElytraFlight.mc.player.motionX = arrd[0];
                        ElytraFlight.mc.player.motionZ = arrd[1];
                    }
                }
                if (d2 > 0.0) {
                    ElytraFlight.mc.player.motionX += (vec3d.xCoord / d2 * d3 - ElytraFlight.mc.player.motionX) * 0.1;
                    ElytraFlight.mc.player.motionZ += (vec3d.zCoord / d2 * d3 - ElytraFlight.mc.player.motionZ) * 0.1;
                }
                if (this.mode.getValue() == Mode.CONTROL && !ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                    double[] arrd = ElytraFlight.directionSpeed(this.speed.getValue().floatValue());
                    ElytraFlight.mc.player.motionX = arrd[0];
                    ElytraFlight.mc.player.motionZ = arrd[1];
                }
                if (!this.noDrag.getValue().booleanValue()) {
                    ElytraFlight.mc.player.motionX *= (double)0.99f;
                    ElytraFlight.mc.player.motionY *= (double)0.98f;
                    ElytraFlight.mc.player.motionZ *= (double)0.99f;
                }
                d = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
                if (this.speedLimit.getValue().booleanValue() && d > (double)this.maxSpeed.getValue().floatValue()) {
                    ElytraFlight.mc.player.motionX *= (double)this.maxSpeed.getValue().floatValue() / d;
                    ElytraFlight.mc.player.motionZ *= (double)this.maxSpeed.getValue().floatValue() / d;
                }
                ElytraFlight.mc.player.moveEntity(MoverType.SELF, ElytraFlight.mc.player.motionX, ElytraFlight.mc.player.motionY, ElytraFlight.mc.player.motionZ);
            } else {
                double d;
                double d7;
                double d8;
                double d9;
                double d10;
                boolean bl = false;
                float f = ElytraFlight.mc.player.movementInput.field_192832_b;
                if (this.cruiseControl.getValue().booleanValue()) {
                    if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
                        this.height += (double)this.upFactor.getValue().floatValue() * 0.5;
                    } else if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        this.height -= (double)this.downFactor.getValue().floatValue() * 0.5;
                    }
                    if (this.forceHeight.getValue().booleanValue()) {
                        this.height = this.manualHeight.getValue().intValue();
                    }
                    double d11 = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
                    d10 = MathHelper.clamp((double)(d11 / 1.7), (double)0.0, (double)1.0);
                    d9 = 1.0 - Math.sqrt(d10);
                    d8 = 0.6;
                    if (d11 >= (double)this.minUpSpeed.getValue().floatValue() && this.instantFlyTimer.passedMs((long)(2000.0f * this.packetDelay.getValue().floatValue()))) {
                        double d12 = -((45.0 - d8) * d9 + d8);
                        d7 = (this.height + 1.0 - ElytraFlight.mc.player.posY) * 2.0;
                        double d13 = MathHelper.clamp((double)Math.abs(d7), (double)0.0, (double)1.0);
                        double d14 = -Math.toDegrees(Math.atan2(Math.abs(d7), d11 * 30.0)) * Math.signum(d7);
                        double d15 = (d14 - d12) * d13;
                        ElytraFlight.mc.player.rotationPitch = (float)d12;
                        ElytraFlight.mc.player.rotationPitch += (float)d15;
                        ElytraFlight.mc.player.prevRotationPitch = ElytraFlight.mc.player.rotationPitch;
                    } else {
                        ElytraFlight.mc.player.rotationPitch = 0.25f;
                        ElytraFlight.mc.player.prevRotationPitch = 0.25f;
                        f = 1.0f;
                    }
                }
                Vec3d vec3d = ElytraFlight.mc.player.getLookVec();
                float f3 = ElytraFlight.mc.player.rotationPitch * ((float)Math.PI / 180);
                d10 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
                d9 = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
                d8 = vec3d.lengthVector();
                float f4 = MathHelper.cos((float)f3);
                f4 = (float)((double)f4 * (double)f4 * Math.min(1.0, d8 / 0.4));
                ElytraFlight.mc.player.motionY += -0.08 + (double)f4 * 0.06;
                if (ElytraFlight.mc.player.motionY < 0.0 && d10 > 0.0) {
                    d = ElytraFlight.mc.player.motionY * -0.1 * (double)f4;
                    ElytraFlight.mc.player.motionY += d;
                    ElytraFlight.mc.player.motionX += vec3d.xCoord * d / d10;
                    ElytraFlight.mc.player.motionZ += vec3d.zCoord * d / d10;
                }
                if (f3 < 0.0f) {
                    d = d9 * (double)(-MathHelper.sin((float)f3)) * 0.04;
                    ElytraFlight.mc.player.motionY += d * 3.2;
                    ElytraFlight.mc.player.motionX -= vec3d.xCoord * d / d10;
                    ElytraFlight.mc.player.motionZ -= vec3d.zCoord * d / d10;
                }
                if (d10 > 0.0) {
                    ElytraFlight.mc.player.motionX += (vec3d.xCoord / d10 * d9 - ElytraFlight.mc.player.motionX) * 0.1;
                    ElytraFlight.mc.player.motionZ += (vec3d.zCoord / d10 * d9 - ElytraFlight.mc.player.motionZ) * 0.1;
                }
                if (!this.noDrag.getValue().booleanValue()) {
                    ElytraFlight.mc.player.motionX *= (double)0.99f;
                    ElytraFlight.mc.player.motionY *= (double)0.98f;
                    ElytraFlight.mc.player.motionZ *= (double)0.99f;
                }
                float f5 = ElytraFlight.mc.player.rotationYaw * ((float)Math.PI / 180);
                if (f3 > 0.0f && (ElytraFlight.mc.player.motionY < 0.0 || bl)) {
                    if (f != 0.0f && this.instantFlyTimer.passedMs((long)(2000.0f * this.packetDelay.getValue().floatValue())) && this.staticTimer.passedMs((long)(1000.0f * this.staticDelay.getValue().floatValue()))) {
                        if (this.stopMotion.getValue().booleanValue()) {
                            ElytraFlight.mc.player.motionX = 0.0;
                            ElytraFlight.mc.player.motionZ = 0.0;
                        }
                        this.instantFlyTimer.reset();
                        ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    } else if (!this.instantFlyTimer.passedMs((long)(2000.0f * this.packetDelay.getValue().floatValue()))) {
                        ElytraFlight.mc.player.motionX -= (double)f * Math.sin(f5) * (double)this.factor.getValue().floatValue() / 20.0;
                        ElytraFlight.mc.player.motionZ += (double)f * Math.cos(f5) * (double)this.factor.getValue().floatValue() / 20.0;
                        this.staticTimer.reset();
                    }
                }
                d7 = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
                if (this.speedLimit.getValue().booleanValue() && d7 > (double)this.maxSpeed.getValue().floatValue()) {
                    ElytraFlight.mc.player.motionX *= (double)this.maxSpeed.getValue().floatValue() / d7;
                    ElytraFlight.mc.player.motionZ *= (double)this.maxSpeed.getValue().floatValue() / d7;
                }
                if (this.freeze.getValue().booleanValue() && Keyboard.isKeyDown((int)56)) {
                    ElytraFlight.mc.player.setVelocity(0.0, 0.0, 0.0);
                }
                ElytraFlight.mc.player.moveEntity(MoverType.SELF, ElytraFlight.mc.player.motionX, ElytraFlight.mc.player.motionY, ElytraFlight.mc.player.motionZ);
            }
        }
    }

    public static boolean isHasElytra() {
        return hasElytra;
    }

    @Override
    public void onTick() {
        double d;
        if (ElytraFlight.mc.world == null || ElytraFlight.mc.player == null) {
            return;
        }
        if (ElytraFlight.mc.player.onGround) {
            this.hasTouchedGround = true;
        }
        if (!this.cruiseControl.getValue().booleanValue()) {
            this.height = ElytraFlight.mc.player.posY;
        }
        for (ItemStack itemStack : ElytraFlight.mc.player.getArmorInventoryList()) {
            if (itemStack.getItem() instanceof ItemElytra) {
                hasElytra = true;
                break;
            }
            hasElytra = false;
        }
        if (this.strictTimer.passedMs(1500L) && !this.strictTimer.passedMs(2000L)) {
            ElytraFlight.mc.timer.field_194149_e = 50.0f;
        }
        if (!ElytraFlight.mc.player.isElytraFlying()) {
            if (this.hasTouchedGround && this.boostTimer.getValue().booleanValue() && !ElytraFlight.mc.player.onGround) {
                ElytraFlight.mc.timer.field_194149_e = 166.66667f;
            }
            if (!ElytraFlight.mc.player.onGround && this.instantFly.getValue().booleanValue() && ElytraFlight.mc.player.motionY < 0.0) {
                if (!this.instantFlyTimer.passedMs((long)(1000.0f * this.timeout.getValue().floatValue()))) {
                    return;
                }
                this.instantFlyTimer.reset();
                ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFlight.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                this.hasTouchedGround = false;
                this.strictTimer.reset();
            }
            return;
        }
        if (ElytraFlight.mc.player == null) {
            return;
        }
        if (this.mode.getValue() != Mode.FIREWORK) {
            return;
        }
        if (ElytraFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
            this.height += (double)this.upFactor.getValue().floatValue() * 0.5;
        } else if (ElytraFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
            this.height -= (double)this.downFactor.getValue().floatValue() * 0.5;
        }
        if (this.forceHeight.getValue().booleanValue()) {
            this.height = this.manualHeight.getValue().intValue();
        }
        Vec3d vec3d = new Vec3d(ElytraFlight.mc.player.motionX, ElytraFlight.mc.player.motionY, ElytraFlight.mc.player.motionZ);
        double d2 = vec3d.lengthVector() * 20.0;
        double d3 = Math.sqrt(ElytraFlight.mc.player.motionX * ElytraFlight.mc.player.motionX + ElytraFlight.mc.player.motionZ * ElytraFlight.mc.player.motionZ);
        double d4 = MathHelper.clamp((double)(d3 / 1.7), (double)0.0, (double)1.0);
        double d5 = 1.0 - Math.sqrt(d4);
        double d6 = 0.6;
        if (d4 >= 0.5 || ElytraFlight.mc.player.posY > this.height + 1.0) {
            double d7 = -((45.0 - d6) * d5 + d6);
            d = (this.height + 1.0 - ElytraFlight.mc.player.posY) * 2.0;
            double d8 = MathHelper.clamp((double)Math.abs(d), (double)0.0, (double)1.0);
            double d9 = -Math.toDegrees(Math.atan2(Math.abs(d), d3 * 30.0)) * Math.signum(d);
            double d10 = (d9 - d7) * d8;
            ElytraFlight.mc.player.rotationPitch = (float)d7;
            ElytraFlight.mc.player.rotationPitch += (float)d10;
            ElytraFlight.mc.player.prevRotationPitch = ElytraFlight.mc.player.rotationPitch;
        }
        if (this.rocketTimer.passedMs((long)(1000.0f * this.factor.getValue().floatValue()))) {
            Block block;
            d = this.height - ElytraFlight.mc.player.posY;
            boolean bl = d > 0.25 && d < 1.0 || d2 < (double)this.minSpeed.getValue().intValue();
            boolean bl2 = bl;
            if (this.groundSafety.getValue().booleanValue() && (block = ElytraFlight.mc.world.getBlockState(new BlockPos((Entity)ElytraFlight.mc.player).down()).getBlock()) != Blocks.AIR && !(block instanceof BlockLiquid) && ElytraFlight.mc.player.getEntityBoundingBox().minY - Math.floor(ElytraFlight.mc.player.getEntityBoundingBox().minY) > (double)this.triggerHeight.getValue().floatValue()) {
                bl = true;
            }
            if (this.autoSwitch.getValue().booleanValue() && bl && ElytraFlight.mc.player.getHeldItemMainhand().getItem() != Items.FIREWORKS) {
                for (int i = 0; i < 9; ++i) {
                    if (ElytraFlight.mc.player.inventory.getStackInSlot(i).getItem() != Items.FIREWORKS) continue;
                    ElytraFlight.mc.player.inventory.currentItem = i;
                    ElytraFlight.mc.playerController.updateController();
                    break;
                }
            }
            if (ElytraFlight.mc.player.getHeldItemMainhand().getItem() == Items.FIREWORKS && bl) {
                ElytraFlight.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                this.rocketTimer.reset();
            }
        }
    }

    private static enum Mode {
        BOOST,
        CONTROL,
        FIREWORK;

    }
}

