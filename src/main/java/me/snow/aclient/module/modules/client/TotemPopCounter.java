//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.module.modules.client;

import java.util.HashMap;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.TextUtil;
import net.minecraft.entity.player.EntityPlayer;

public class TotemPopCounter
extends Module {
    public /* synthetic */ Setting<TextUtil.Color> popcountC;
    public static /* synthetic */ HashMap<String, Integer> TotemPopCounter;
    public /* synthetic */ Setting<TextUtil.Color> playernameC;
    public /* synthetic */ Setting<TextUtil.Color> textC;
    public static /* synthetic */ TotemPopCounter INSTANCE;

    private void setInstance() {
        INSTANCE = this;
    }

    public TotemPopCounter() {
        super("TotemPopCounter", "Counts other players totem pops.", Module.Category.CLIENT, true, false, false);
        this.playernameC = this.register(new Setting<TextUtil.Color>("PlayerName", TextUtil.Color.GOLD));
        this.textC = this.register(new Setting<TextUtil.Color>("Text", TextUtil.Color.RED));
        this.popcountC = this.register(new Setting<TextUtil.Color>("Pop Amount", TextUtil.Color.GOLD));
        this.setInstance();
    }

    public static TotemPopCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TotemPopCounter();
        }
        return INSTANCE;
    }

    public void onTotemPop(EntityPlayer entityPlayer) {
        if (me.snow.aclient.module.modules.client.TotemPopCounter.getInstance().isOn()) {
            if (Feature.fullNullCheck()) {
                return;
            }
            if (me.snow.aclient.module.modules.client.TotemPopCounter.mc.player.equals((Object)entityPlayer)) {
                return;
            }
            int n = 1;
            if (TotemPopCounter.containsKey(entityPlayer.getName())) {
                n = TotemPopCounter.get(entityPlayer.getName());
                TotemPopCounter.put(entityPlayer.getName(), ++n);
            } else {
                TotemPopCounter.put(entityPlayer.getName(), n);
            }
            if (n == 1) {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" popped ", this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(n), this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totem.", this.textC.getPlannedValue()))));
            } else {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" popped ", this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(n), this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totems.", this.textC.getPlannedValue()))));
            }
        }
    }

    static {
        TotemPopCounter = new HashMap();
        INSTANCE = new TotemPopCounter();
    }

    @Override
    public void onEnable() {
        TotemPopCounter.clear();
    }

    public void onDeath(EntityPlayer entityPlayer) {
        if (me.snow.aclient.module.modules.client.TotemPopCounter.getInstance().isOn() && TotemPopCounter.containsKey(entityPlayer.getName())) {
            int n = TotemPopCounter.get(entityPlayer.getName());
            TotemPopCounter.remove(entityPlayer.getName());
            if (n == 1) {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" died after popping ", this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(n), this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totem.", this.textC.getPlannedValue()))));
            } else {
                Command.sendSilentMessage(String.valueOf(new StringBuilder().append(" ").append(TextUtil.coloredString(entityPlayer.getName(), this.playernameC.getPlannedValue())).append(TextUtil.coloredString(" died after popping ", this.textC.getPlannedValue())).append(TextUtil.coloredString(String.valueOf(n), this.popcountC.getPlannedValue())).append(TextUtil.coloredString(" totems.", this.textC.getPlannedValue()))));
            }
        }
    }
}

