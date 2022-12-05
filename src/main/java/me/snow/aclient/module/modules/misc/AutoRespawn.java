//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoRespawn
extends Module {
    public /* synthetic */ Setting<Boolean> antiDeathScreen;
    public /* synthetic */ Setting<Boolean> respawn;
    public /* synthetic */ Setting<Boolean> deathCoords;

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiGameOver) {
            if (this.deathCoords.getValue().booleanValue() && guiOpenEvent.getGui() instanceof GuiGameOver) {
                Command.sendMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
            }
            if (this.respawn.getValue() != false && AutoRespawn.mc.player.getHealth() <= 0.0f || this.antiDeathScreen.getValue().booleanValue() && AutoRespawn.mc.player.getHealth() > 0.0f) {
                guiOpenEvent.setCanceled(true);
                AutoRespawn.mc.player.respawnPlayer();
            }
        }
    }

    public AutoRespawn() {
        super("AutoRespawn", "Respawns you when you die.", Module.Category.MISC, true, false, false);
        this.antiDeathScreen = this.register(new Setting<Boolean>("AntiDeathScreen", true));
        this.deathCoords = this.register(new Setting<Boolean>("DeathCoords", false));
        this.respawn = this.register(new Setting<Boolean>("Respawn", true));
    }
}

