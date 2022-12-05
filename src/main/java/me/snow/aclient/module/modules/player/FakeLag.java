//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.client.CPacketClientStatus
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketKeepAlive
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketTabComplete
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FakeLag
extends Module {
    public /* synthetic */ Setting<Integer> timeLimit;
    public /* synthetic */ Setting<Integer> packetLimit;
    public /* synthetic */ Setting<Float> distance;
    public /* synthetic */ Setting<Boolean> cPacketPlayer;
    private /* synthetic */ int packetsCanceled;
    private static /* synthetic */ FakeLag INSTANCE;
    public /* synthetic */ Setting<Mode> autoOff;
    private /* synthetic */ EntityOtherPlayerMP entity;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Queue<Packet<?>> packets;

    @Override
    public void onUpdate() {
        if (FakeLag.nullCheck() || this.autoOff.getValue() == Mode.TIME && this.timer.passedS(this.timeLimit.getValue().intValue()) || this.autoOff.getValue() == Mode.DISTANCE && this.startPos != null && FakeLag.mc.player.getDistanceSq(this.startPos) >= MathUtil.square(this.distance.getValue().floatValue()) || this.autoOff.getValue() == Mode.PACKETS && this.packetsCanceled >= this.packetLimit.getValue()) {
            this.disable();
        }
    }

    @Override
    public void onDisable() {
        if (!FakeLag.fullNullCheck()) {
            FakeLag.mc.world.removeEntity((Entity)this.entity);
            while (!this.packets.isEmpty()) {
                FakeLag.mc.player.connection.sendPacket(this.packets.poll());
            }
        }
        this.startPos = null;
    }

    @SubscribeEvent
    public void onSendPacket(PacketEvent.Send send) {
        if (send.getStage() == 0 && FakeLag.mc.world != null && !mc.isSingleplayer()) {
            Object t = send.getPacket();
            if (this.cPacketPlayer.getValue().booleanValue() && t instanceof CPacketPlayer) {
                send.setCanceled(true);
                this.packets.add((Packet)t);
                ++this.packetsCanceled;
            }
            if (!this.cPacketPlayer.getValue().booleanValue()) {
                if (t instanceof CPacketChatMessage || t instanceof CPacketConfirmTeleport || t instanceof CPacketKeepAlive || t instanceof CPacketTabComplete || t instanceof CPacketClientStatus) {
                    return;
                }
                this.packets.add((Packet)t);
                send.setCanceled(true);
                ++this.packetsCanceled;
            }
        }
    }

    public FakeLag() {
        super("FakeLag", "Fakelag.", Module.Category.PLAYER, true, false, false);
        this.timer = new Timer();
        this.packets = new ConcurrentLinkedQueue();
        this.cPacketPlayer = this.register(new Setting<Boolean>("CPacketPlayer", true));
        this.autoOff = this.register(new Setting<Mode>("AutoOff", Mode.MANUAL));
        this.timeLimit = this.register(new Setting<Object>("Time", Integer.valueOf(20), Integer.valueOf(1), Integer.valueOf(500), object -> this.autoOff.getValue() == Mode.TIME));
        this.packetLimit = this.register(new Setting<Object>("Packets", Integer.valueOf(20), Integer.valueOf(1), Integer.valueOf(500), object -> this.autoOff.getValue() == Mode.PACKETS));
        this.distance = this.register(new Setting<Object>("Distance", Float.valueOf(10.0f), Float.valueOf(1.0f), Float.valueOf(100.0f), object -> this.autoOff.getValue() == Mode.DISTANCE));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        if (!FakeLag.fullNullCheck()) {
            this.entity = new EntityOtherPlayerMP((World)FakeLag.mc.world, FakeLag.mc.session.getProfile());
            this.entity.copyLocationAndAnglesFrom((Entity)FakeLag.mc.player);
            this.entity.rotationYaw = FakeLag.mc.player.rotationYaw;
            this.entity.rotationYawHead = FakeLag.mc.player.rotationYawHead;
            this.entity.inventory.copyInventory(FakeLag.mc.player.inventory);
            FakeLag.mc.world.addEntityToWorld(6942069, (Entity)this.entity);
            this.startPos = FakeLag.mc.player.getPosition();
        } else {
            this.disable();
        }
        this.packetsCanceled = 0;
        this.timer.reset();
    }

    static {
        INSTANCE = new FakeLag();
    }

    public static FakeLag getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeLag();
        }
        return INSTANCE;
    }

    @Override
    public void onLogout() {
        if (this.isOn()) {
            this.disable();
        }
    }

    public static enum Mode {
        MANUAL,
        TIME,
        DISTANCE,
        PACKETS;

    }
}

