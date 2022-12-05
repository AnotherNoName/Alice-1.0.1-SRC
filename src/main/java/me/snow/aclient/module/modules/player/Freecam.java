//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketKeepAlive
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketVehicleMove
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.network.play.server.SPacketSetPassengers
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.PushEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MathUtil;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Freecam
extends Module {
    private /* synthetic */ Setting<Boolean> legit;
    private /* synthetic */ Vec3d position;
    private /* synthetic */ float pitch;
    private static /* synthetic */ Freecam INSTANCE;
    private /* synthetic */ float yaw;
    private /* synthetic */ EntityOtherPlayerMP Camera;
    private /* synthetic */ Entity riding;
    private /* synthetic */ Setting<Float> speed;
    private /* synthetic */ Setting<Boolean> disable;
    private /* synthetic */ AxisAlignedBB oldBoundingBox;
    private /* synthetic */ Setting<Boolean> packet;
    private /* synthetic */ Setting<Modes> mode;

    @Override
    public void onEnable() {
        if (!Feature.fullNullCheck() && this.mode.getValue() == Modes.Normal) {
            this.riding = null;
            if (Freecam.mc.player.getRidingEntity() != null) {
                this.riding = Freecam.mc.player.getRidingEntity();
                Freecam.mc.player.dismountRidingEntity();
            }
            this.Camera = new EntityOtherPlayerMP((World)Freecam.mc.world, mc.getSession().getProfile());
            this.Camera.copyLocationAndAnglesFrom((Entity)Freecam.mc.player);
            this.Camera.prevRotationYaw = Freecam.mc.player.rotationYaw;
            this.Camera.rotationYawHead = Freecam.mc.player.rotationYawHead;
            this.Camera.inventory.copyInventory(Freecam.mc.player.inventory);
            Freecam.mc.world.addEntityToWorld(69420, (Entity)this.Camera);
            this.position = Freecam.mc.player.getPositionVector();
            this.yaw = Freecam.mc.player.rotationYaw;
            this.pitch = Freecam.mc.player.rotationPitch;
            Freecam.mc.player.noClip = true;
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    static {
        INSTANCE = new Freecam();
    }

    @SubscribeEvent
    public void onPush(PushEvent pushEvent) {
        if (pushEvent.getStage() == 1) {
            pushEvent.setCanceled(true);
        }
    }

    @Override
    public void onLogout() {
        if (this.disable.getValue().booleanValue()) {
            this.disable();
        }
    }

    public static Freecam getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Freecam();
        }
        return INSTANCE;
    }

    @Override
    public void onUpdate() {
        if (this.mode.getValue() == Modes.Normal) {
            Freecam.mc.player.noClip = true;
            Freecam.mc.player.setVelocity(0.0, 0.0, 0.0);
            double[] arrd = MathUtil.directionSpeed(this.speed.getValue().floatValue());
            if (Freecam.mc.player.movementInput.moveStrafe != 0.0f || Freecam.mc.player.movementInput.field_192832_b != 0.0f) {
                Freecam.mc.player.motionX = arrd[0];
                Freecam.mc.player.motionZ = arrd[1];
            } else {
                Freecam.mc.player.motionX = 0.0;
                Freecam.mc.player.motionZ = 0.0;
            }
            Freecam.mc.player.setSprinting(false);
            if (Freecam.mc.gameSettings.keyBindJump.isKeyDown()) {
                Freecam.mc.player.motionY += (double)this.speed.getValue().floatValue();
            }
            if (Freecam.mc.gameSettings.keyBindSneak.isKeyDown()) {
                Freecam.mc.player.motionY -= (double)this.speed.getValue().floatValue();
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketSetPassengers sPacketSetPassengers;
        Entity entity;
        if (receive.getPacket() instanceof SPacketSetPassengers && (entity = Freecam.mc.world.getEntityByID((sPacketSetPassengers = (SPacketSetPassengers)receive.getPacket()).getEntityId())) != null && entity == this.riding) {
            this.riding = null;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            sPacketSetPassengers = (SPacketPlayerPosLook)receive.getPacket();
            if (this.packet.getValue().booleanValue()) {
                if (this.Camera != null) {
                    this.Camera.setPositionAndRotation(sPacketSetPassengers.getX(), sPacketSetPassengers.getY(), sPacketSetPassengers.getZ(), sPacketSetPassengers.getYaw(), sPacketSetPassengers.getPitch());
                }
                this.position = new Vec3d(sPacketSetPassengers.getX(), sPacketSetPassengers.getY(), sPacketSetPassengers.getZ());
                Freecam.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(sPacketSetPassengers.getTeleportId()));
                receive.setCanceled(true);
            } else {
                receive.setCanceled(true);
            }
        }
    }

    @Override
    public void onDisable() {
        if (!Feature.fullNullCheck() && Freecam.mc.world != null && this.mode.getValue() == Modes.Normal) {
            if (this.riding != null) {
                Freecam.mc.player.startRiding(this.riding, true);
                this.riding = null;
            }
            if (this.Camera != null) {
                Freecam.mc.world.removeEntity((Entity)this.Camera);
            }
            if (this.position != null) {
                Freecam.mc.player.setPosition(this.position.xCoord, this.position.yCoord, this.position.zCoord);
            }
            Freecam.mc.player.rotationYaw = this.yaw;
            Freecam.mc.player.rotationPitch = this.pitch;
            Freecam.mc.player.noClip = false;
            Freecam.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
    }

    public Freecam() {
        super("Freecam", "Look around freely.", Module.Category.PLAYER, true, false, false);
        this.mode = this.register(new Setting<Modes>("Mode", Modes.Normal));
        this.packet = this.register(new Setting<Boolean>("Cancel Packets", true));
        this.speed = this.register(new Setting<Float>("Speed", Float.valueOf(5.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
        this.disable = this.register(new Setting<Boolean>("Logout/Off", true));
        this.legit = this.register(new Setting<Boolean>("Legit", false));
        this.setInstance();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (this.legit.getValue().booleanValue() && this.Camera != null && send.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.x = this.Camera.posX;
            cPacketPlayer.y = this.Camera.posY;
            cPacketPlayer.z = this.Camera.posZ;
            return;
        }
        if (this.packet.getValue().booleanValue()) {
            if (send.getPacket() instanceof CPacketPlayer) {
                send.setCanceled(true);
            }
        } else if (!(send.getPacket() instanceof CPacketUseEntity || send.getPacket() instanceof CPacketPlayerTryUseItem || send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock || send.getPacket() instanceof CPacketPlayer || send.getPacket() instanceof CPacketVehicleMove || send.getPacket() instanceof CPacketChatMessage || send.getPacket() instanceof CPacketKeepAlive)) {
            send.setCanceled(true);
        }
    }

    public static enum Modes {
        Normal;

    }
}

