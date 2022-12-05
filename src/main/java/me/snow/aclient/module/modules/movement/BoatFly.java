//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiDownloadTerrain
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketInput
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketVehicleMove
 *  net.minecraft.network.play.server.SPacketMoveVehicle
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.network.play.server.SPacketPlayerPosLook$EnumFlags
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.DismountRidingEntityEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.PlayerTravelEvent;
import me.snow.aclient.mixin.mixins.accessors.ISPacketPlayerPosLook;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.ca.sc.PlayerUtilSC;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BoatFly
extends Module {
    private /* synthetic */ int currentStage;
    private /* synthetic */ Setting<Double> speed;
    private /* synthetic */ AtomicBoolean moved;
    private /* synthetic */ Setting<Boolean> semi;
    private /* synthetic */ Setting<Boolean> bypass;
    private /* synthetic */ int currentTeleportId;
    private /* synthetic */ int setbackCounter;
    private /* synthetic */ Setting<Boolean> confirm;
    private /* synthetic */ Vec3d prevSetback;
    public /* synthetic */ Setting<Integer> maxSetbacks;
    private /* synthetic */ Setting<Boolean> constrict;
    private /* synthetic */ Setting<Boolean> antiKick;
    private /* synthetic */ Setting<Boolean> fixYaw;
    public /* synthetic */ Setting<Integer> safetyFactor;
    private /* synthetic */ Setting<Double> vSpeed;

    @SubscribeEvent
    public void onDismountEntity(DismountRidingEntityEvent dismountRidingEntityEvent) {
        if (BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
            dismountRidingEntityEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
            return;
        }
        if (!this.bypass.getValue().booleanValue()) {
            return;
        }
        if (send.getPacket() instanceof CPacketVehicleMove) {
            if (BoatFly.mc.player.isRiding() && BoatFly.mc.player.ticksExisted % 2 == 0) {
                BoatFly.mc.playerController.interactWithEntity((EntityPlayer)BoatFly.mc.player, BoatFly.mc.player.getRidingEntity(), this.constrict.getValue() != false ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            }
        } else if (send.getPacket() instanceof CPacketPlayer.Rotation && BoatFly.mc.player.isRiding()) {
            send.isCanceled();
        } else if (send.getPacket() instanceof CPacketInput && (!this.semi.getValue().booleanValue() || BoatFly.mc.player.ticksExisted % 2 == 0)) {
            send.isCanceled();
        }
    }

    public BoatFly() {
        super("BoatFly", "Automatically walks in a straight line", Module.Category.MOVEMENT, true, false, false);
        this.fixYaw = this.register(new Setting<Boolean>("FixYaw", true));
        this.antiKick = this.register(new Setting<Boolean>("AntiKick", true));
        this.confirm = this.register(new Setting<Boolean>("Confirm", false));
        this.bypass = this.register(new Setting<Boolean>("Bypass", true));
        this.semi = this.register(new Setting<Boolean>("Semi", true));
        this.constrict = this.register(new Setting<Boolean>("Constrict", false));
        this.speed = this.register(new Setting<Double>("Speed", 1.0, 0.1, 50.0));
        this.vSpeed = this.register(new Setting<Double>("VSpeed", 0.5, 0.1, 10.0));
        this.safetyFactor = this.register(new Setting<Integer>("SafetyFactor", 2, 0, 10));
        this.maxSetbacks = this.register(new Setting<Integer>("MaxSetbacks", 10, 0, 20));
        this.prevSetback = null;
        this.moved = new AtomicBoolean(false);
        this.currentStage = 0;
    }

    private double yDistanceTo(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.yCoord - vec3d2.yCoord;
        return MathHelper.sqrt((double)(d * d));
    }

    @Override
    public void onEnable() {
        this.setbackCounter = 0;
        this.prevSetback = null;
        this.currentTeleportId = 0;
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
            return;
        }
    }

    @SubscribeEvent
    public void onPlayerUpdate(PlayerTravelEvent playerTravelEvent) {
        if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
            this.toggle();
            return;
        }
        if (BoatFly.mc.player.getRidingEntity() instanceof EntityBoat) {
            EntityBoat entityBoat = (EntityBoat)BoatFly.mc.player.getRidingEntity();
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            if (PlayerUtilSC.isPlayerMoving()) {
                double[] arrd = PlayerUtilSC.directionSpeed(this.speed.getValue());
                d = arrd[0];
                d3 = arrd[1];
            } else {
                d = 0.0;
                d3 = 0.0;
            }
            if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                d2 = this.vSpeed.getValue();
                if (this.antiKick.getValue().booleanValue() && BoatFly.mc.player.ticksExisted % 20 == 0) {
                    d2 = -0.04;
                }
            } else if (BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                d2 = -this.vSpeed.getValue().doubleValue();
            } else if (this.antiKick.getValue().booleanValue() && BoatFly.mc.player.ticksExisted % 4 == 0) {
                d2 = -0.04;
            }
            if (this.fixYaw.getValue().booleanValue()) {
                entityBoat.rotationYaw = BoatFly.mc.player.rotationYaw;
            }
            if (this.safetyFactor.getValue() > 0 && !BoatFly.mc.world.isBlockLoaded(new BlockPos(entityBoat.posX + d * (double)this.safetyFactor.getValue().intValue(), entityBoat.posY + d2 * (double)this.safetyFactor.getValue().intValue(), entityBoat.posZ + d3 * (double)this.safetyFactor.getValue().intValue()), false)) {
                d = 0.0;
                d3 = 0.0;
            }
            if (!this.semi.getValue().booleanValue() || BoatFly.mc.player.ticksExisted % 2 != 0) {
                if (this.moved.get() && this.semi.getValue().booleanValue()) {
                    entityBoat.setVelocity(0.0, 0.0, 0.0);
                    this.moved.set(false);
                } else {
                    entityBoat.setVelocity(d, d2, d3);
                }
            }
            if (this.confirm.getValue().booleanValue()) {
                ++this.currentTeleportId;
                BoatFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.currentTeleportId));
            }
        }
    }

    private double xzDistanceTo(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.zCoord - vec3d2.zCoord;
        return MathHelper.sqrt((double)(d * d + d2 * d2));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive var1_1) {
        block8: {
            block9: {
                block12: {
                    block11: {
                        block10: {
                            if (BoatFly.mc.player == null || BoatFly.mc.world == null) {
                                this.toggle();
                                return;
                            }
                            if (!(var1_1.getPacket() instanceof SPacketPlayerPosLook) || !BoatFly.mc.player.isRiding()) break block8;
                            var2_2 = (SPacketPlayerPosLook)var1_1.getPacket();
                            ((ISPacketPlayerPosLook)var2_2).setYaw(BoatFly.mc.player.rotationYaw);
                            ((ISPacketPlayerPosLook)var2_2).setPitch(BoatFly.mc.player.rotationPitch);
                            var2_2.getFlags().remove((Object)SPacketPlayerPosLook.EnumFlags.X_ROT);
                            var2_2.getFlags().remove((Object)SPacketPlayerPosLook.EnumFlags.Y_ROT);
                            this.currentTeleportId = var2_2.getTeleportId();
                            if (this.maxSetbacks.getValue() <= 0) break block9;
                            if (this.prevSetback != null) break block10;
                            this.prevSetback = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                            this.setbackCounter = 1;
                            break block9;
                        }
                        if (!PlayerUtilSC.isPlayerMoving()) break block11;
                        v0 = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                        if (!(this.xzDistanceTo(this.prevSetback, v0) < this.speed.getValue() * 0.8)) break block11;
                        this.prevSetback = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                        ++this.setbackCounter;
                        break block9;
                    }
                    if (!BoatFly.mc.gameSettings.keyBindJump.isKeyDown() && !BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) break block12;
                    v1 = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                    if (!(this.yDistanceTo(this.prevSetback, v1) < this.vSpeed.getValue() * 0.5)) break block12;
                    this.prevSetback = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                    ++this.setbackCounter;
                    break block9;
                }
                if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown() || BoatFly.mc.gameSettings.keyBindSneak.isKeyDown()) ** GOTO lbl-1000
                if (this.yDistanceTo(this.prevSetback, new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ())) < 0.02) ** GOTO lbl-1000
                v2 = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                if (this.yDistanceTo(this.prevSetback, v2) > 1.0) lbl-1000:
                // 2 sources

                {
                    this.prevSetback = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                    ++this.setbackCounter;
                } else lbl-1000:
                // 2 sources

                {
                    this.prevSetback = new Vec3d(var2_2.getX(), var2_2.getY(), var2_2.getZ());
                    this.setbackCounter = 1;
                }
            }
            if (this.maxSetbacks.getValue() > 0 && this.setbackCounter > this.maxSetbacks.getValue()) {
                return;
            }
            if (BoatFly.mc.player.isEntityAlive() && BoatFly.mc.world.isBlockLoaded(new BlockPos(BoatFly.mc.player.posX, BoatFly.mc.player.posY, BoatFly.mc.player.posZ)) && !(BoatFly.mc.currentScreen instanceof GuiDownloadTerrain)) {
                if (this.currentTeleportId <= 0) {
                    this.currentTeleportId = var2_2.getTeleportId();
                    return;
                }
                if (!this.confirm.getValue().booleanValue()) {
                    BoatFly.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(var2_2.getTeleportId()));
                }
                var1_1.setCanceled(true);
            }
        }
        if (var1_1.getPacket() instanceof SPacketMoveVehicle == false) return;
        if (BoatFly.mc.player.isRiding() == false) return;
        if (this.semi.getValue().booleanValue()) {
            this.moved.set(true);
            return;
        }
        var1_1.setCanceled(true);
    }
}

