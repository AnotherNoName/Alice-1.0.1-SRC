//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRotate
extends Module {
    public NoRotate() {
        super("NoRotate", "No ROtate.", Module.Category.PLAYER, true, false, false);
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (NoRotate.nullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            sPacketPlayerPosLook.yaw = NoRotate.mc.player.rotationYaw;
            sPacketPlayerPosLook.pitch = NoRotate.mc.player.rotationPitch;
        }
    }
}

