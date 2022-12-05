//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Finish
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Start
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.util.LinkedList;
import java.util.Queue;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChorusLag
extends Module {
    /* synthetic */ double posY;
    /* synthetic */ double posX;
    /* synthetic */ int delay;
    /* synthetic */ double posZ;
    /* synthetic */ Queue<CPacketPlayer> packets;
    /* synthetic */ boolean ateChorus;
    /* synthetic */ boolean hackPacket;
    public final /* synthetic */ Setting<Integer> sDelay;
    /* synthetic */ boolean posTp;
    /* synthetic */ Queue<CPacketConfirmTeleport> packetss;
    /* synthetic */ int delay2;

    @SubscribeEvent
    public void finishEating(LivingEntityUseItemEvent.Finish finish) {
        if (finish.getEntity() == ChorusLag.mc.player && finish.getResultStack().getItem().equals((Object)Items.CHORUS_FRUIT)) {
            this.posX = ChorusLag.mc.player.posX;
            this.posY = ChorusLag.mc.player.posY;
            this.posZ = ChorusLag.mc.player.posZ;
            this.posTp = false;
            this.ateChorus = true;
        }
    }

    @SubscribeEvent
    public void finishEating(LivingEntityUseItemEvent.Start start) {
    }

    @SubscribeEvent
    public void Event(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
    }

    public void sendPackets() {
        while (!this.packets.isEmpty()) {
            ChorusLag.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        while (!this.packetss.isEmpty()) {
            ChorusLag.mc.player.connection.sendPacket((Packet)this.packetss.poll());
        }
        this.hackPacket = false;
        this.delay2 = 0;
        this.ateChorus = false;
    }

    @Override
    public void onEnable() {
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
    }

    public ChorusLag() {
        super("ChorusLag", "Makes your teleport delayed serverside", Module.Category.PLAYER, true, false, false);
        this.sDelay = this.register(new Setting<Integer>("Lag Delay", 18, 0, 500));
        this.delay = 0;
        this.delay2 = 0;
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
        this.packets = new LinkedList<CPacketPlayer>();
        this.packetss = new LinkedList<CPacketConfirmTeleport>();
    }

    @SubscribeEvent
    public void onUpdate(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketConfirmTeleport && this.ateChorus && this.delay2 < this.sDelay.getValue()) {
            this.packetss.add((CPacketConfirmTeleport)send.getPacket());
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.ateChorus && this.delay2 < this.sDelay.getValue()) {
            this.packets.add((CPacketPlayer)send.getPacket());
            send.setCanceled(true);
        }
    }

    @Override
    public void onUpdate() {
        if (this.ateChorus) {
            ++this.delay;
            ++this.delay2;
            if (!ChorusLag.mc.player.getPosition().equals((Object)new BlockPos(this.posX, this.posY, this.posZ)) && !this.posTp && ChorusLag.mc.player.getDistance(this.posX, this.posY, this.posZ) > 1.0) {
                ChorusLag.mc.player.setPosition(this.posX, this.posY, this.posZ);
                this.posTp = true;
            }
        }
        if (this.ateChorus && this.delay2 > this.sDelay.getValue()) {
            this.ateChorus = false;
            this.delay = 0;
            this.hackPacket = true;
            this.delay2 = 0;
            this.sendPackets();
        }
        if (this.delay2 == this.sDelay.getValue() - 40) {
            Command.sendMessage("Chorusing In 2 seconds");
        }
    }
}

