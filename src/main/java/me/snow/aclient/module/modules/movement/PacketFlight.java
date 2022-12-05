//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import java.util.HashMap;
import java.util.Map;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.mixin.mixins.accessors.INetworkManager;
import me.snow.aclient.mixin.mixins.accessors.ISPacketPlayerPosLook;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MotionUtil;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PacketFlight
extends Module {
    public /* synthetic */ Setting<Boolean> antiKick;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Phase> phase;
    private static final /* synthetic */ double CONCEAL = 0.0624;
    private static final /* synthetic */ double MOVE_FACTOR;
    public static /* synthetic */ PacketFlight INSTANCE;
    public /* synthetic */ Setting<Bounds> bounds;
    private final /* synthetic */ Map<Integer, Vec3d> predictions;
    private /* synthetic */ int lagTime;
    private /* synthetic */ int tpId;
    public /* synthetic */ Setting<Boolean> conceal;
    public /* synthetic */ Setting<Float> factor;

    private boolean isPhased() {
        return !PacketFlight.mc.world.getCollisionBoxes((Entity)PacketFlight.mc.player, PacketFlight.mc.player.getEntityBoundingBox().addCoord(-0.0625, -0.0625, -0.0625)).isEmpty();
    }

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.mode.currentEnumName()).append(""));
    }

    private void send(int n, double d, double d2, boolean bl) {
        if (n == 0) {
            PacketFlight.mc.player.setVelocity(0.0, 0.0, 0.0);
            return;
        }
        double[] arrd = MotionUtil.getMoveSpeed(d);
        for (int i = 1; i < n + 1; ++i) {
            double d3 = arrd[0] * (double)i;
            double d4 = arrd[1] * (double)i;
            double d5 = d2;
            if (!bl) {
                d5 *= (double)i;
            }
            PacketFlight.mc.player.motionX = d3;
            PacketFlight.mc.player.motionY = d5;
            PacketFlight.mc.player.motionZ = d4;
            Vec3d vec3d = PacketFlight.mc.player.getPositionVector();
            Vec3d vec3d2 = vec3d.addVector(d3, d5, d4);
            this.send(vec3d2);
            this.send(this.bounds.getValue().modify(vec3d));
            if (this.mode.getValue().equals((Object)Mode.SETBACK)) continue;
            this.predictions.put(++this.tpId, vec3d2);
            PacketFlight.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.tpId));
        }
    }

    private void send(Vec3d vec3d) {
        ((INetworkManager)PacketFlight.mc.player.connection.getNetworkManager()).hookDispatchPacket((Packet<?>)new CPacketPlayer.Position(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, true), null);
    }

    @SubscribeEvent
    public void onMove(MoveEvent moveEvent) {
        int n = (int)Math.floor(this.factor.getValue().floatValue());
        if (this.mode.getValue().equals((Object)Mode.FACTOR)) {
            if ((double)PacketFlight.mc.player.ticksExisted % 10.0 < 10.0 * ((double)this.factor.getValue().floatValue() - Math.floor(this.factor.getValue().floatValue()))) {
                ++n;
            }
        } else {
            n = 1;
        }
        double d = this.conceal.getValue() != false || --this.lagTime > 0 || this.isPhased() ? 0.0624 : 0.2873;
        double d2 = 0.0;
        boolean bl = false;
        if (PacketFlight.mc.gameSettings.keyBindJump.isKeyDown()) {
            d2 = 0.0624;
            if (MotionUtil.isMoving()) {
                d *= MOVE_FACTOR;
                d2 *= MOVE_FACTOR;
            }
        } else if (PacketFlight.mc.gameSettings.keyBindSneak.isKeyDown()) {
            d2 = -0.0624;
            if (MotionUtil.isMoving()) {
                d *= MOVE_FACTOR;
                d2 *= MOVE_FACTOR;
            }
        } else {
            boolean bl2 = bl = this.antiKick.getValue() != false && PacketFlight.mc.player.ticksExisted % 40 == 0 && !this.isPhased() && !PacketFlight.mc.world.collidesWithAnyBlock(PacketFlight.mc.player.getEntityBoundingBox()) && !MotionUtil.isMoving();
            if (bl) {
                n = 1;
                d2 = -0.04;
            }
        }
        this.send(n, d, d2, bl);
        moveEvent.setX(PacketFlight.mc.player.motionX);
        moveEvent.setY(PacketFlight.mc.player.motionY);
        moveEvent.setZ(PacketFlight.mc.player.motionZ);
        if (!this.phase.getValue().equals((Object)Phase.NONE)) {
            PacketFlight.mc.player.noClip = true;
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            Vec3d vec3d = this.predictions.get(sPacketPlayerPosLook.getTeleportId());
            if (vec3d != null && vec3d.xCoord == sPacketPlayerPosLook.getX() && vec3d.yCoord == sPacketPlayerPosLook.getY() && vec3d.zCoord == sPacketPlayerPosLook.getZ()) {
                if (!this.mode.getValue().equals((Object)Mode.SETBACK)) {
                    receive.setCanceled(true);
                }
                PacketFlight.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(sPacketPlayerPosLook.getTeleportId()));
                return;
            }
            ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setYaw(PacketFlight.mc.player.rotationYaw);
            ((ISPacketPlayerPosLook)sPacketPlayerPosLook).setPitch(PacketFlight.mc.player.rotationPitch);
            PacketFlight.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(sPacketPlayerPosLook.getTeleportId()));
            this.lagTime = 10;
            this.tpId = sPacketPlayerPosLook.getTeleportId();
        }
    }

    public PacketFlight() {
        super("PacketFly", "Uses packets to allow you to fly and move.", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.FACTOR));
        this.factor = this.register(new Setting<Object>("Factor", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(8.0f), object -> this.mode.getValue() == Mode.FACTOR));
        this.bounds = this.register(new Setting<Bounds>("Bounds", Bounds.DOWN));
        this.phase = this.register(new Setting<Phase>("Phase", Phase.NCP));
        this.conceal = this.register(new Setting<Boolean>("Conceal", false));
        this.antiKick = this.register(new Setting<Boolean>("AntiKick", true));
        this.predictions = new HashMap<Integer, Vec3d>();
        this.tpId = 0;
        this.lagTime = 0;
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.predictions.clear();
        this.tpId = 0;
        this.lagTime = 0;
        PacketFlight.mc.player.noClip = false;
    }

    @Override
    public void onLogout() {
        this.disable();
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer) {
            send.setCanceled(true);
        }
    }

    static {
        MOVE_FACTOR = 1.0 / StrictMath.sqrt(2.0);
    }

    public static enum Bounds {
        UP(1337.0),
        DOWN(-1337.0),
        MIN(512.0);

        private final /* synthetic */ double yOffset;

        private Bounds(double d) {
            this.yOffset = d;
        }

        public Vec3d modify(Vec3d vec3d) {
            return vec3d.addVector(0.0, this.yOffset, 0.0);
        }
    }

    public static enum Mode {
        FACTOR,
        FAST,
        SETBACK;

    }

    public static enum Phase {
        NONE,
        VANILLA,
        NCP;

    }
}

