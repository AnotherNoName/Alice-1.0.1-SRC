//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.EnumHand
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.skid.oyvey.InventoryUtil2;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;

public class SilentAutoXP
extends Module {
    /* synthetic */ int oldSlot;
    public final /* synthetic */ Setting<Bind> xpBind;
    /* synthetic */ int oldRotations;
    /* synthetic */ int xpSlot;
    public final /* synthetic */ Setting<Boolean> rotatetofeet;

    public void useExp() {
        this.oldRotations = (int)SilentAutoXP.mc.player.rotationPitch;
        if (InventoryUtil2.find(Items.EXPERIENCE_BOTTLE) >= 0) {
            this.oldSlot = SilentAutoXP.mc.player.inventory.currentItem;
            this.xpSlot = InventoryUtil2.find(Items.EXPERIENCE_BOTTLE);
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.xpSlot));
            if (this.rotatetofeet.getValue().booleanValue()) {
                SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(SilentAutoXP.mc.player.cameraYaw, 90.0f, SilentAutoXP.mc.player.onGround));
            }
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            SilentAutoXP.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldSlot));
            if (this.rotatetofeet.getValue().booleanValue()) {
                SilentAutoXP.mc.player.rotationPitch = this.oldRotations;
            }
        }
    }

    @Override
    public void onUpdate() {
        if (this.xpBind.getValue().isDown()) {
            this.useExp();
        }
    }

    @Override
    public void onEnable() {
        this.oldRotations = (int)SilentAutoXP.mc.player.rotationPitch;
        this.oldSlot = SilentAutoXP.mc.player.inventory.currentItem;
    }

    public SilentAutoXP() {
        super("SilentXP", "Switches to Experience Bottles and Throws them at your feet to mend your armor", Module.Category.PLAYER, true, false, false);
        this.rotatetofeet = this.register(new Setting<Boolean>("Rotate to Feet", true));
        this.xpBind = this.register(new Setting<Bind>("XpBind", new Bind(-1)));
    }
}

