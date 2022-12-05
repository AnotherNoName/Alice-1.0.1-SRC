//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import java.util.Comparator;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.SpeedRewrite;
import me.snow.aclient.module.modules.movement.Step;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RotationUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.skid.oyvey.HoleUtilSafety;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HoleSnap
extends Module {
    public final /* synthetic */ Setting<Bind> speeddisablebind;
    public /* synthetic */ Setting<DDisable> disablewhen;
    public final /* synthetic */ Setting<Bind> stepdisablebind;
    public /* synthetic */ Setting<Float> timerfactor;
    private /* synthetic */ Setting<DDisableStep> stepdisable;
    private /* synthetic */ Setting<Boolean> cancelbind;
    /* synthetic */ Timer timer;
    private /* synthetic */ Setting<Boolean> motionstop;
    /* synthetic */ HoleUtilSafety.Hole holes;
    public final /* synthetic */ Setting<Bind> SnapCC;
    private /* synthetic */ int ticks;
    private final /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Float> range2;

    @Override
    public void onEnable() {
        if (this.mode.getValue() == Mode.Motion && this.motionstop.getValue().booleanValue()) {
            HoleSnap.mc.player.motionX = 0.0;
            HoleSnap.mc.player.motionZ = 0.0;
        }
        if (this.disablewhen.getValue() == DDisable.Toggle && SpeedRewrite.getInstance().isOn()) {
            SpeedRewrite.getInstance().disable();
            Command.sendMessage("<HoleSnap> Disable Speed");
        }
        if (this.stepdisable.getValue() == DDisableStep.Toggle && AliceMain.moduleManager.getModuleByClass(Step.class).isEnabled()) {
            AliceMain.moduleManager.getModuleByClass(Step.class).disable();
            Command.sendMessage("<HoleSnap> Disable Step");
        }
        if (HoleSnap.fullNullCheck()) {
            return;
        }
        this.timer.reset();
        this.holes = null;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (this.isDisabled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.disable();
            return;
        }
    }

    @Override
    public void onDisable() {
        this.timer.reset();
        this.holes = null;
        HoleSnap.mc.timer.field_194149_e = 50.0f;
    }

    @Override
    public void onTick() {
        BlockPos blockPos2;
        if (this.mode.getValue() == Mode.Instant) {
            blockPos2 = AliceMain.holeManager.calcHoles().stream().min(Comparator.comparing(blockPos -> HoleSnap.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()))).orElse(null);
            if (blockPos2 != null) {
                if (HoleSnap.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ()) < (double)this.range.getValue().floatValue() + 1.5) {
                    HoleSnap.mc.player.setPosition((double)blockPos2.getX() + 0.5, (double)blockPos2.getY(), (double)blockPos2.getZ() + 0.5);
                    HoleSnap.mc.player.setPosition((double)blockPos2.getX() + 0.5, (double)blockPos2.getY(), (double)blockPos2.getZ() + 0.5);
                    Command.sendMessage("Accepting Teleport");
                } else {
                    Command.sendMessage("Out of range. disabling HoleSnap");
                }
            } else {
                Command.sendMessage("Unable to find hole, disabling HoleSnap");
            }
            this.disable();
        }
        if (this.mode.getValue() == Mode.Motion) {
            if (HoleSnap.fullNullCheck()) {
                return;
            }
            if (EntityUtil.isInLiquid()) {
                this.disable();
                return;
            }
            if (this.cancelbind.getValue().booleanValue() && this.SnapCC.getValue().isDown()) {
                this.disable();
            }
            if (this.disablewhen.getValue() == DDisable.Bind && this.speeddisablebind.getValue().isDown() && SpeedRewrite.getInstance().isOn()) {
                SpeedRewrite.getInstance().disable();
                Command.sendMessage("<HoleSnap> Disable Speed");
            }
            if (this.stepdisable.getValue() == DDisableStep.Bind && this.stepdisablebind.getValue().isDown() && AliceMain.moduleManager.getModuleByClass(Step.class).isEnabled()) {
                AliceMain.moduleManager.getModuleByClass(Step.class).disable();
                Command.sendMessage("<HoleSnap> Disable Step");
            }
            HoleSnap.mc.timer.field_194149_e = 50.0f / this.timerfactor.getValue().floatValue();
            this.holes = RotationUtil.getTargetHoleVec3D(this.range2.getValue().floatValue());
            if (this.holes == null) {
                Command.sendMessage("Unable to find hole, disabling HoleSnap");
                this.disable();
                return;
            }
            if (this.timer.passedMs(500L)) {
                this.disable();
                return;
            }
            if (HoleUtilSafety.isObbyHole(RotationUtil.getPlayerPos()) || HoleUtilSafety.isBedrockHoles(RotationUtil.getPlayerPos())) {
                this.disable();
                return;
            }
            if (HoleSnap.mc.world.getBlockState(this.holes.pos1).getBlock() != Blocks.AIR) {
                this.disable();
                return;
            }
            blockPos2 = this.holes.pos1;
            Vec3d vec3d = HoleSnap.mc.player.getPositionVector();
            Vec3d vec3d2 = new Vec3d((double)blockPos2.getX() + 0.5, HoleSnap.mc.player.posY, (double)blockPos2.getZ() + 0.5);
            double d = Math.toRadians(RotationUtil.getRotationTo((Vec3d)vec3d, (Vec3d)vec3d2).x);
            double d2 = vec3d.distanceTo(vec3d2);
            double d3 = HoleSnap.mc.player.onGround ? -Math.min(0.2805, d2 / 2.0) : -EntityUtil.getMaxSpeed() + 0.02;
            HoleSnap.mc.player.motionX = -Math.sin(d) * d3;
            HoleSnap.mc.player.motionZ = Math.cos(d) * d3;
        }
    }

    public HoleSnap() {
        super("HoleSnap", "Teleport to Hole", Module.Category.MOVEMENT, true, false, false);
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(0.5f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.mode.getValue() == Mode.Instant));
        this.range2 = this.register(new Setting<Float>("Motion Range", Float.valueOf(4.0f), Float.valueOf(0.1f), Float.valueOf(10.0f), f -> this.mode.getValue() == Mode.Motion));
        this.mode = this.register(new Setting<Mode>("SnapMode", Mode.Motion));
        this.disablewhen = this.register(new Setting<DDisable>("Speed Disable", DDisable.None, dDisable -> this.mode.getValue() == Mode.Motion));
        this.speeddisablebind = this.register(new Setting<Bind>("DisableSpeedBind", new Bind(-1), bind -> this.mode.getValue() == Mode.Motion && this.disablewhen.getValue() == DDisable.Bind));
        this.stepdisable = this.register(new Setting<DDisableStep>("Step Disable", DDisableStep.None, dDisableStep -> this.mode.getValue() == Mode.Motion));
        this.stepdisablebind = this.register(new Setting<Bind>("DisableStepBind", new Bind(-1), bind -> this.mode.getValue() == Mode.Motion && this.stepdisable.getValue() == DDisableStep.Bind));
        this.cancelbind = this.register(new Setting<Boolean>("Key Disable", Boolean.valueOf(false), bl -> this.mode.getValue() == Mode.Motion));
        this.SnapCC = this.register(new Setting<Bind>("SnapCancelBind", new Bind(-1), bind -> this.mode.getValue() == Mode.Motion && this.cancelbind.getValue() != false));
        this.timerfactor = this.register(new Setting<Float>("TimerFactor", Float.valueOf(2.0f), Float.valueOf(1.0f), Float.valueOf(5.0f), f -> this.mode.getValue() == Mode.Motion));
        this.motionstop = this.register(new Setting<Boolean>("StopMotion", Boolean.valueOf(true), bl -> this.mode.getValue() == Mode.Motion));
        this.timer = new Timer();
        this.ticks = 0;
    }

    private static enum DDisableStep {
        None,
        Toggle,
        Bind;

    }

    private static enum DDisable {
        None,
        Toggle,
        Bind;

    }

    public static enum Mode {
        Instant,
        Motion;

    }
}

