//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemChorusFruit
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Finish
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.LinkedList;
import java.util.Queue;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import net.minecraft.init.Items;
import net.minecraft.item.ItemChorusFruit;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChorusControl
extends Module {
    /* synthetic */ Queue<CPacketPlayer> packets;
    /* synthetic */ double posX;
    /* synthetic */ boolean hackPacket;
    /* synthetic */ boolean ateChorus;
    /* synthetic */ double posZ;
    /* synthetic */ Queue<CPacketConfirmTeleport> packetss;
    /* synthetic */ boolean posTp;
    /* synthetic */ double posY;

    public void sendPackets() {
        while (!this.packets.isEmpty()) {
            ChorusControl.mc.player.connection.sendPacket((Packet)this.packets.poll());
        }
        while (!this.packetss.isEmpty()) {
            ChorusControl.mc.player.connection.sendPacket((Packet)this.packetss.poll());
        }
        this.hackPacket = false;
        this.ateChorus = false;
    }

    public ChorusControl() {
        super("ChorusControl", "After eating a chorus fruit instead of teleporting you it will hold the packet and instantly teleport you the next time you right click with chorus", Module.Category.MISC, true, false, false);
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
        this.packets = new LinkedList<CPacketPlayer>();
        this.packetss = new LinkedList<CPacketConfirmTeleport>();
    }

    @SubscribeEvent
    public void Event(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
    }

    @Override
    public void onEnable() {
        this.ateChorus = false;
        this.hackPacket = false;
        this.posTp = false;
    }

    @Override
    public void onUpdate() {
        if (this.ateChorus && !ChorusControl.mc.player.getPosition().equals((Object)new BlockPos(this.posX, this.posY, this.posZ)) && !this.posTp && ChorusControl.mc.player.getDistance(this.posX, this.posY, this.posZ) > 1.0) {
            ChorusControl.mc.player.setPosition(this.posX, this.posY, this.posZ);
            this.posTp = true;
        }
        if (this.ateChorus && ChorusControl.mc.player != null && ChorusControl.mc.player.getHeldItemMainhand().getItem() instanceof ItemChorusFruit && ChorusControl.mc.player.isHandActive()) {
            this.ateChorus = false;
            this.hackPacket = true;
            this.sendPackets();
        }
    }

    @SubscribeEvent
    public void onUpdate(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketConfirmTeleport && this.ateChorus) {
            this.packetss.add((CPacketConfirmTeleport)send.getPacket());
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.ateChorus) {
            this.packets.add((CPacketPlayer)send.getPacket());
            send.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void finishEating(LivingEntityUseItemEvent.Finish finish) {
        if (finish.getEntity() == ChorusControl.mc.player && finish.getResultStack().getItem().equals((Object)Items.CHORUS_FRUIT)) {
            this.posX = ChorusControl.mc.player.posX;
            this.posY = ChorusControl.mc.player.posY;
            this.posZ = ChorusControl.mc.player.posZ;
            this.posTp = false;
            this.ateChorus = true;
        }
    }
}

