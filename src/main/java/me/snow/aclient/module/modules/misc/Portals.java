//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraftforge.client.GuiIngameForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.mixin.mixins.accessors.IEntity;
import me.snow.aclient.module.Module;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Portals
extends Module {
    public /* synthetic */ Setting<Boolean> noRender;
    public /* synthetic */ Setting<Boolean> allowGuis;
    public /* synthetic */ Setting<Boolean> godmode;
    private /* synthetic */ boolean renderPortal;

    @Override
    public void onUpdate() {
        if (Portals.mc.player == null || Portals.mc.world == null) {
            return;
        }
        if (this.allowGuis.getValue().booleanValue()) {
            ((IEntity)Portals.mc.player).setInPortal(false);
        }
        if (this.noRender.getValue().booleanValue()) {
            GuiIngameForge.renderPortal = false;
        }
    }

    @Override
    public void onEnable() {
        this.renderPortal = GuiIngameForge.renderPortal;
    }

    public Portals() {
        super("Portals", "Remove unwanted portal functionality.", Module.Category.MISC, true, false, false);
        this.allowGuis = this.register(new Setting<Boolean>("PortalGui", false));
        this.noRender = this.register(new Setting<Boolean>("NoRender", false));
        this.godmode = this.register(new Setting<Boolean>("Godmode", false));
        this.renderPortal = false;
    }

    @Override
    public String getDisplayInfo() {
        if (this.godmode.getValue().booleanValue()) {
            return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("GodMode"));
        }
        return null;
    }

    @Override
    public void onDisable() {
        GuiIngameForge.renderPortal = this.renderPortal;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getStage() == 0 && this.godmode.getValue().booleanValue() && send.getPacket() instanceof CPacketConfirmTeleport) {
            send.setCanceled(true);
        }
    }
}

