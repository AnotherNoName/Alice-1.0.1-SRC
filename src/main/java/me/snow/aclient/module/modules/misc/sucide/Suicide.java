//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 */
package me.snow.aclient.module.modules.misc.sucide;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.misc.SpammerModule;
import me.snow.aclient.module.modules.misc.sucide.SuicideMode;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;

public class Suicide
extends Module {
    private final /* synthetic */ Setting<SuicideMode> mode;

    @Override
    public void onTick() {
        if (SpammerModule.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == SuicideMode.Suicide) {
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/kill"));
        }
        if (this.mode.getValue() == SuicideMode.None) {
            Command.sendMessage("<Suicide> If you want to kill yourself change SuicideMode. Don't kill yourself by mistake ");
        }
        this.disable();
    }

    public Suicide() {
        super("Suicide", "Message", Module.Category.MISC, true, false, false);
        this.mode = this.register(new Setting<SuicideMode>("SuicideMode", SuicideMode.None));
    }
}

