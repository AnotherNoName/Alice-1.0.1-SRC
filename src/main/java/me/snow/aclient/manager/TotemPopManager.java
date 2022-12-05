//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.manager;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.ModuleTools;
import me.snow.aclient.module.modules.client.Notifications;
import net.minecraft.entity.player.EntityPlayer;

public class TotemPopManager
extends Feature {
    private /* synthetic */ Map<EntityPlayer, Integer> poplist;
    private /* synthetic */ Notifications notifications;
    private final /* synthetic */ Set<EntityPlayer> toAnnounce;

    public TotemPopManager() {
        this.poplist = new ConcurrentHashMap<EntityPlayer, Integer>();
        this.toAnnounce = new HashSet<EntityPlayer>();
    }

    public void setTotemPops(EntityPlayer entityPlayer, int n) {
        this.poplist.put(entityPlayer, n);
    }

    public void clearList() {
        this.poplist = new ConcurrentHashMap<EntityPlayer, Integer>();
    }

    public void popTotem(EntityPlayer entityPlayer) {
        this.poplist.merge(entityPlayer, 1, Integer::sum);
    }

    public void resetPops(EntityPlayer entityPlayer) {
        this.setTotemPops(entityPlayer, 0);
    }

    public void onOwnLogout(boolean bl) {
        if (bl) {
            this.clearList();
        }
    }

    public int getTotemPops(EntityPlayer entityPlayer) {
        Integer n = this.poplist.get((Object)entityPlayer);
        if (n == null) {
            return 0;
        }
        return n;
    }

    public void onLogout() {
        this.onOwnLogout(this.notifications.clearOnLogout.getValue());
    }

    public String getTotemPopString(EntityPlayer entityPlayer) {
        return String.valueOf(new StringBuilder().append("\u00a7f").append(this.getTotemPops(entityPlayer) <= 0 ? "" : String.valueOf(new StringBuilder().append("-").append(this.getTotemPops(entityPlayer)).append(" "))));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String death1(EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) == 1) {
            if (!ModuleTools.getInstance().isEnabled()) return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totem!"));
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GREEN).append(entityPlayer.getName()).append((Object)ChatFormatting.GRAY).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.GRAY).append(" totem."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.GOLD).append(entityPlayer.getName()).append((Object)ChatFormatting.RED).append(" died after popping ").append((Object)ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.RED).append(" totem."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_PURPLE).append("[").append((Object)ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append((Object)ChatFormatting.DARK_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.LIGHT_PURPLE).append(" time!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totem!"));
                }
            }
            return null;
        } else {
            if (!ModuleTools.getInstance().isEnabled()) return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totems!"));
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GREEN).append(entityPlayer.getName()).append((Object)ChatFormatting.GRAY).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.GRAY).append(" totems."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.GOLD).append(entityPlayer.getName()).append((Object)ChatFormatting.RED).append(" died after popping ").append((Object)ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.RED).append(" totems."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_PURPLE).append("[").append((Object)ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append((Object)ChatFormatting.DARK_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.LIGHT_PURPLE).append(" times!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" died after popping ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totems!"));
                }
            }
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String pop(EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) == 1) {
            if (!ModuleTools.getInstance().isEnabled()) return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totem."));
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GREEN).append(entityPlayer.getName()).append((Object)ChatFormatting.GRAY).append(" just popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.GRAY).append(" totem."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.GOLD).append(entityPlayer.getName()).append((Object)ChatFormatting.RED).append(" popped ").append((Object)ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.RED).append(" totem."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_PURPLE).append("[").append((Object)ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append((Object)ChatFormatting.DARK_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" has popped ").append((Object)ChatFormatting.RED).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.LIGHT_PURPLE).append(" time in total!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totem."));
                }
            }
            return "";
        } else {
            if (!ModuleTools.getInstance().isEnabled()) return String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totems."));
            switch (ModuleTools.getInstance().popNotifier.getValue()) {
                case FUTURE: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GREEN).append(entityPlayer.getName()).append((Object)ChatFormatting.GRAY).append(" just popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.GRAY).append(" totems."));
                }
                case PHOBOS: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.GOLD).append(entityPlayer.getName()).append((Object)ChatFormatting.RED).append(" popped ").append((Object)ChatFormatting.GOLD).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.RED).append(" totems."));
                }
                case DOTGOD: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_PURPLE).append("[").append((Object)ChatFormatting.LIGHT_PURPLE).append("DotGod.CC").append((Object)ChatFormatting.DARK_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(entityPlayer.getName()).append(" has popped ").append((Object)ChatFormatting.RED).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.LIGHT_PURPLE).append(" times in total!"));
                }
                case NONE: {
                    return String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append(entityPlayer.getName()).append(" popped ").append((Object)ChatFormatting.GREEN).append(this.getTotemPops(entityPlayer)).append((Object)ChatFormatting.WHITE).append(" Totems."));
                }
            }
        }
        return "";
    }

    public void onUpdate() {
        if (this.notifications.totemAnnounce.passedMs(this.notifications.delay.getValue().intValue()) && this.notifications.isOn() && this.notifications.totemPops.getValue().booleanValue()) {
            for (EntityPlayer entityPlayer : this.toAnnounce) {
                if (entityPlayer == null) continue;
                int n = 0;
                for (char c : entityPlayer.getName().toCharArray()) {
                    n += c;
                    n *= 10;
                }
                Command.sendOverwriteMessage(this.pop(entityPlayer), n, this.notifications.totemNoti.getValue());
                this.toAnnounce.remove((Object)entityPlayer);
                this.notifications.totemAnnounce.reset();
                break;
            }
        }
    }

    public void onLogout(EntityPlayer entityPlayer, boolean bl) {
        if (bl) {
            this.resetPops(entityPlayer);
        }
    }

    public void init() {
        this.notifications = AliceMain.moduleManager.getModuleByClass(Notifications.class);
    }

    public void onTotemPop(EntityPlayer entityPlayer) {
        this.popTotem(entityPlayer);
        if (!entityPlayer.equals((Object)TotemPopManager.mc.player)) {
            this.toAnnounce.add(entityPlayer);
            this.notifications.totemAnnounce.reset();
        }
    }

    public void onDeath(EntityPlayer entityPlayer) {
        if (this.getTotemPops(entityPlayer) != 0 && !entityPlayer.equals((Object)TotemPopManager.mc.player) && this.notifications.isOn() && this.notifications.totemPops.getValue().booleanValue()) {
            int n = 0;
            for (char c : entityPlayer.getName().toCharArray()) {
                n += c;
                n *= 10;
            }
            Command.sendOverwriteMessage(this.death1(entityPlayer), n, this.notifications.totemNoti.getValue());
            this.toAnnounce.remove((Object)entityPlayer);
        }
        this.resetPops(entityPlayer);
    }
}

