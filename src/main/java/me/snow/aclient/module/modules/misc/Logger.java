//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.network.Packet
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.lang.reflect.Field;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.network.Packet;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Logger
extends Module {
    public /* synthetic */ Setting<Packets> packets;
    public /* synthetic */ Setting<Boolean> noPing;
    public /* synthetic */ Setting<Boolean> chat;
    public /* synthetic */ Setting<Boolean> fullInfo;

    @SubscribeEvent(receiveCanceled=true)
    public void onPacketSend(PacketEvent.Send send) {
        if (this.noPing.getValue().booleanValue() && Logger.mc.currentScreen instanceof GuiMultiplayer) {
            return;
        }
        if (this.packets.getValue() == Packets.OUTGOING || this.packets.getValue() == Packets.ALL) {
            if (this.chat.getValue().booleanValue()) {
                Command.sendMessage(send.getPacket().toString());
            } else {
                this.writePacketOnConsole((Packet<?>)send.getPacket(), false);
            }
        }
    }

    public Logger() {
        super("Logger", "Logs stuff", Module.Category.MISC, true, false, false);
        this.packets = this.register(new Setting<Packets>("Packets", Packets.OUTGOING));
        this.chat = this.register(new Setting<Boolean>("Chat", false));
        this.fullInfo = this.register(new Setting<Boolean>("FullInfo", false));
        this.noPing = this.register(new Setting<Boolean>("NoPing", false));
    }

    @SubscribeEvent(receiveCanceled=true)
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (this.noPing.getValue().booleanValue() && Logger.mc.currentScreen instanceof GuiMultiplayer) {
            return;
        }
        if (this.packets.getValue() == Packets.INCOMING || this.packets.getValue() == Packets.ALL) {
            if (this.chat.getValue().booleanValue()) {
                Command.sendMessage(receive.getPacket().toString());
            } else {
                this.writePacketOnConsole((Packet<?>)receive.getPacket(), true);
            }
        }
    }

    private void writePacketOnConsole(Packet<?> packet, boolean bl) {
        if (this.fullInfo.getValue().booleanValue()) {
            System.out.println(String.valueOf(new StringBuilder().append(bl ? "In: " : "Send: ").append(packet.getClass().getSimpleName()).append(" {")));
            try {
                for (Class<?> class_ = packet.getClass(); class_ != Object.class; class_ = class_.getSuperclass()) {
                    for (Field field : class_.getDeclaredFields()) {
                        if (field == null) continue;
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        System.out.println(StringUtils.stripControlCodes((String)String.valueOf(new StringBuilder().append("      ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(" : ").append(field.get(packet)))));
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("}");
        } else {
            System.out.println(packet.toString());
        }
    }

    public static enum Packets {
        NONE,
        INCOMING,
        OUTGOING,
        ALL;

    }
}

