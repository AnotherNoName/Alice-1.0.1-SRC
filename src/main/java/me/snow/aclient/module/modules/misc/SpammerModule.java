//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  org.apache.commons.lang3.RandomStringUtils
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.apache.commons.lang3.RandomStringUtils;

public class SpammerModule
extends Module {
    private final /* synthetic */ Setting<Integer> random;
    private final /* synthetic */ Setting<MessageMode> mode;
    public /* synthetic */ Setting<Integer> delayMS;
    private final /* synthetic */ Setting<String> message22;
    public /* synthetic */ Setting<Integer> delayDS;
    public /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<String> custom1;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<DelayType> delayType;

    @Override
    public void onTick() {
        if (SpammerModule.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == MessageMode.Toggle) {
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(this.message22.getValue()));
            this.disable();
        }
        if (this.mode.getValue() == MessageMode.Spammer) {
            switch (this.delayType.getValue()) {
                case MS: {
                    if (this.timer.passedMs(this.delayMS.getValue().intValue())) break;
                    return;
                }
                case S: {
                    if (this.timer.passedS(this.delay.getValue().intValue())) break;
                    return;
                }
                case DS: {
                    if (this.timer.passedDs(this.delayDS.getValue().intValue())) break;
                    return;
                }
            }
            SpammerModule.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append(this.custom1.getValue()).append("[").append(RandomStringUtils.randomAlphanumeric((int)this.random.getValue())).append("]"))));
            this.timer.reset();
        }
    }

    @Override
    public void onLogin() {
        if (AliceMain.moduleManager.getModuleByClass(SpammerModule.class).isEnabled()) {
            AliceMain.moduleManager.getModuleByClass(SpammerModule.class).disable();
        }
    }

    @Override
    public void onDisable() {
        this.timer.reset();
    }

    public SpammerModule() {
        super("Spammer", "Message", Module.Category.MISC, true, false, false);
        this.timer = new Timer();
        this.mode = this.register(new Setting<MessageMode>("SuicideMode", MessageMode.Toggle));
        this.message22 = this.register(new Setting<String>("Custom", "/kit 123 ", string -> this.mode.getValue() == MessageMode.Toggle));
        this.custom1 = this.register(new Setting<String>("SpammerMessage", "Alice Strong Client Discord:https://discord.gg/EtTGvzt3nS uwu", string -> this.mode.getValue() == MessageMode.Spammer));
        this.random = this.register(new Setting<Integer>("Random", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(20), n -> this.mode.getValue() == MessageMode.Spammer));
        this.delayType = this.register(new Setting<DelayType>("DelayType", DelayType.S, delayType -> this.mode.getValue() == MessageMode.Spammer));
        this.delay = this.register(new Setting<Object>("DelayS", Integer.valueOf(8), Integer.valueOf(1), Integer.valueOf(1000), object -> this.delayType.getValue() == DelayType.S && this.mode.getValue() == MessageMode.Spammer));
        this.delayDS = this.register(new Setting<Object>("DelayDS", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(500), object -> this.delayType.getValue() == DelayType.DS && this.mode.getValue() == MessageMode.Spammer));
        this.delayMS = this.register(new Setting<Object>("DelayMS", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(1000), object -> this.delayType.getValue() == DelayType.MS && this.mode.getValue() == MessageMode.Spammer));
    }

    public static enum MessageMode {
        Toggle,
        Spammer;

    }

    public static enum DelayType {
        MS,
        DS,
        S;

    }
}

