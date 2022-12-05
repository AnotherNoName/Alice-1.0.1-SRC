//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 */
package me.snow.aclient.manager;

import me.snow.aclient.event.events.PacketEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class PlayerManager {
    private /* synthetic */ boolean switching;
    private static final /* synthetic */ Minecraft mc;
    private /* synthetic */ boolean shifting;
    private /* synthetic */ int slot;

    static {
        mc = Minecraft.getMinecraft();
    }

    public boolean isShifting() {
        return this.shifting;
    }

    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketEntityAction) {
            CPacketEntityAction cPacketEntityAction = (CPacketEntityAction)send.getPacket();
            if (cPacketEntityAction.getAction() == CPacketEntityAction.Action.START_SNEAKING) {
                this.shifting = true;
            } else if (cPacketEntityAction.getAction() == CPacketEntityAction.Action.STOP_SNEAKING) {
                this.shifting = false;
            }
        }
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            PlayerManager.mc.player.inventory.currentItem = this.slot = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
        }
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSwitching(boolean bl) {
        this.switching = bl;
    }
}

