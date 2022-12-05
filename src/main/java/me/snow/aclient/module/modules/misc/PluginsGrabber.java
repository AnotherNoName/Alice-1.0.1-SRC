//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  joptsimple.internal.Strings
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketTabComplete
 *  net.minecraft.network.play.server.SPacketTabComplete
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.ArrayList;
import java.util.Collections;
import joptsimple.internal.Strings;
import me.snow.aclient.command.Command;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PluginsGrabber
extends Module {
    public PluginsGrabber() {
        super("PluginsGrabber", "Attempts to grab and display the plugins installed on a server", Module.Category.MISC, true, false, false);
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketTabComplete) {
            String[] arrstring;
            SPacketTabComplete sPacketTabComplete = (SPacketTabComplete)receive.getPacket();
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String string : arrstring = sPacketTabComplete.getMatches()) {
                String string2;
                String[] arrstring2 = string.split(":");
                if (arrstring2.length <= 1 || arrayList.contains(string2 = arrstring2[0].replace("/", ""))) continue;
                arrayList.add(string2);
            }
            Collections.sort(arrayList);
            if (!arrayList.isEmpty()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("Plugins \u00a77(\u00a78").append(arrayList.size()).append("\u00a77): \u00a79").append(Strings.join((String[])arrayList.toArray(new String[0]), (String)"\u00a77, \u00a79"))));
            } else {
                Command.sendMessage("Failed to detect Plugins");
            }
            this.disable();
        }
    }

    @Override
    public void onEnable() {
        if (PluginsGrabber.nullCheck()) {
            return;
        }
        PluginsGrabber.mc.player.connection.sendPacket((Packet)new CPacketTabComplete("/", null, false));
    }
}

