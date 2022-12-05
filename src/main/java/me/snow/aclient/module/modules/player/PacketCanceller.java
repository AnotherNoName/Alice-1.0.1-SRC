/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketInput
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerAbilities
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketVehicleMove
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PacketCanceller
extends Module {
    private /* synthetic */ Setting<Boolean> useitem;
    private /* synthetic */ Setting<Boolean> chat;
    private /* synthetic */ Setting<Boolean> useEntity;
    private /* synthetic */ Setting<Boolean> digging;
    private /* synthetic */ Setting<Boolean> player;
    private /* synthetic */ Setting<Boolean> input;
    private /* synthetic */ Setting<Boolean> animation;
    private /* synthetic */ Setting<Boolean> useitemOnblock;
    private /* synthetic */ Setting<Boolean> entity;
    private /* synthetic */ Setting<Boolean> abilities;
    private /* synthetic */ Setting<Boolean> vehicle;

    public PacketCanceller() {
        super("PacketCancel", "Cancel packets", Module.Category.PLAYER, true, false, false);
        this.input = this.register(new Setting<Boolean>("Input", false));
        this.player = this.register(new Setting<Boolean>("Player", false));
        this.abilities = this.register(new Setting<Boolean>("Abilities", false));
        this.digging = this.register(new Setting<Boolean>("Digging", false));
        this.useitem = this.register(new Setting<Boolean>("TryUseItem", false));
        this.useitemOnblock = this.register(new Setting<Boolean>("TryUseItemOnBlock", false));
        this.entity = this.register(new Setting<Boolean>("EntityAction", false));
        this.useEntity = this.register(new Setting<Boolean>("UseEntity", false));
        this.vehicle = this.register(new Setting<Boolean>("Vehicle", false));
        this.chat = this.register(new Setting<Boolean>("SendChat", false));
        this.animation = this.register(new Setting<Boolean>("Animation", false));
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getStage() == 0) {
            if (send.getPacket() instanceof CPacketInput && this.input.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayer && this.player.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerAbilities && this.abilities.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerDigging && this.digging.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerTryUseItem && this.useitem.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && this.useitemOnblock.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketEntityAction && this.entity.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketUseEntity && this.useEntity.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketVehicleMove && this.vehicle.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketChatMessage && this.chat.getValue().booleanValue()) {
                send.setCanceled(true);
            }
            if (send.getPacket() instanceof CPacketAnimation && this.animation.getValue().booleanValue()) {
                send.setCanceled(true);
            }
        }
    }
}

