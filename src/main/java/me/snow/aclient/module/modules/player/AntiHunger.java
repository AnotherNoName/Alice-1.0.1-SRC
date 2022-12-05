//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.mixin.mixins.accessors.ICPacketPlayer;
import me.snow.aclient.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiHunger
extends Module {
    public /* synthetic */ Setting<Boolean> sprint;
    public /* synthetic */ Setting<Boolean> noGround;
    private /* synthetic */ boolean isOnGround;

    @Override
    public void onEnable() {
        if (this.sprint.getValue().booleanValue() && AntiHunger.mc.player != null) {
            AntiHunger.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiHunger.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketEntityAction cPacketEntityAction;
        if (send.getPacket() instanceof CPacketEntityAction) {
            cPacketEntityAction = (CPacketEntityAction)send.getPacket();
            if (this.sprint.getValue().booleanValue() && (cPacketEntityAction.getAction() == CPacketEntityAction.Action.START_SPRINTING || cPacketEntityAction.getAction() == CPacketEntityAction.Action.STOP_SPRINTING)) {
                send.setCanceled(true);
            }
        }
        if (send.getPacket() instanceof CPacketPlayer) {
            cPacketEntityAction = (CPacketPlayer)send.getPacket();
            boolean bl = AntiHunger.mc.player.onGround;
            if (this.noGround.getValue().booleanValue() && this.isOnGround && bl && cPacketEntityAction.getY(0.0) == (!((ICPacketPlayer)cPacketEntityAction).isMoving() ? 0.0 : AntiHunger.mc.player.posY)) {
                ((ICPacketPlayer)cPacketEntityAction).setOnGround(false);
            }
            this.isOnGround = bl;
        }
    }

    public AntiHunger() {
        super("AntiHunger", "Prevents you from getting Hungry.", Module.Category.PLAYER, true, false, false);
        this.sprint = this.register(new Setting<Boolean>("CancelSprint", true));
        this.noGround = this.register(new Setting<Boolean>("Ground", true));
        this.isOnGround = false;
    }

    @Override
    public void onDisable() {
        if (this.sprint.getValue().booleanValue() && AntiHunger.mc.player != null && AntiHunger.mc.player.isSprinting()) {
            AntiHunger.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiHunger.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }
}

