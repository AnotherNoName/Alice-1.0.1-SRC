//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 */
package me.snow.aclient.module.modules.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.PacketFlight;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class AntiVoid
extends Module {
    public /* synthetic */ Setting<Mode> mode;

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append((Object)this.mode.getValue()).append(""));
    }

    public AntiVoid() {
        super("AntiVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.Solid));
    }

    @Override
    public void onTick() {
        if (!AntiVoid.mc.player.isSpectator() && !AliceMain.moduleManager.getModuleByClass(PacketFlight.class).isEnabled() && AntiVoid.mc.player.posY < 1.0) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("[AntiVoid] ").append((Object)ChatFormatting.RED).append("Player ").append((Object)ChatFormatting.GREEN).append(AntiVoid.mc.player.getName()).append((Object)ChatFormatting.RED).append(" is in the void!")));
            switch (this.mode.getValue()) {
                case Solid: {
                    AntiVoid.mc.player.motionY = 0.0;
                    break;
                }
                case Launch: {
                    AntiVoid.mc.player.motionY = 0.5;
                    break;
                }
                case Glide: {
                    if (!(AntiVoid.mc.player.motionY < 0.0)) break;
                    AntiVoid.mc.player.motionY /= 3.0;
                    break;
                }
                case Rubberband: {
                    AntiVoid.mc.player.setVelocity(0.0, 0.0, 0.0);
                    AntiVoid.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AntiVoid.mc.player.posX, AntiVoid.mc.player.posY + 10.0, AntiVoid.mc.player.posZ, false));
                }
            }
        }
    }

    @Override
    public void onDisable() {
        AntiVoid.mc.player.moveForward = 0.0f;
    }

    public static enum Mode {
        Solid,
        Launch,
        Glide,
        Rubberband;

    }
}

