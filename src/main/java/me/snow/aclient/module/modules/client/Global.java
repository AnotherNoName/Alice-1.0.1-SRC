/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.client;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.TextUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Global
extends Module {
    public /* synthetic */ Setting<String> commandBracket2;
    public /* synthetic */ Setting<String> commandBracket;
    public /* synthetic */ Setting<TextUtil.Color> commandColor;
    public /* synthetic */ Setting<Boolean> safety;
    public /* synthetic */ Setting<Integer> baritoneTimeOut;
    public /* synthetic */ Setting<Integer> respondTime;
    public /* synthetic */ Setting<Integer> rainbowHue;
    public /* synthetic */ Setting<Boolean> tRadarInv;
    public /* synthetic */ Setting<TextUtil.Color> bracketColor;
    public /* synthetic */ Setting<Boolean> potions;
    public /* synthetic */ Setting<Integer> safetyCheck;
    public /* synthetic */ Setting<String> command;
    public /* synthetic */ Setting<Boolean> rainbowPrefix;
    public /* synthetic */ Setting<Boolean> oneDot15;
    public /* synthetic */ Setting<Float> rainbowBrightness;
    public /* synthetic */ Setting<Float> rainbowSaturation;
    private static /* synthetic */ Global INSTANCE;

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && this.equals(clientEvent.getSetting().getFeature())) {
            AliceMain.commandManager.setClientMessage(this.getCommandMessage());
        }
    }

    public String getRainbowCommandMessage() {
        StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
        stringBuilder.insert(0, "\u00a7+");
        stringBuilder.append("\u00a7r");
        return String.valueOf(stringBuilder);
    }

    public static Global getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Global();
        }
        return INSTANCE;
    }

    public Global() {
        super("Global", "ClientGlobal", Module.Category.CLIENT, false, false, true);
        this.commandBracket = this.register(new Setting<String>("Bracket", "["));
        this.commandBracket2 = this.register(new Setting<String>("Bracket2", "]"));
        this.command = this.register(new Setting<String>("Command", "Alice"));
        this.rainbowPrefix = this.register(new Setting<Boolean>("RainbowPrefix", false));
        this.bracketColor = this.register(new Setting<TextUtil.Color>("BColor", TextUtil.Color.AQUA));
        this.commandColor = this.register(new Setting<TextUtil.Color>("CColor", TextUtil.Color.AQUA));
        this.potions = this.register(new Setting<Boolean>("Potions", true));
        this.respondTime = this.register(new Setting<Integer>("SeverTime", 500, 0, 1000));
        this.safety = this.register(new Setting<Boolean>("SafetyPlayer", false));
        this.safetyCheck = this.register(new Setting<Integer>("SafetyCheck", 50, 1, 150));
        this.oneDot15 = this.register(new Setting<Boolean>("1.13+", false));
        this.tRadarInv = this.register(new Setting<Boolean>("TRadarInv", true));
        this.baritoneTimeOut = this.register(new Setting<Integer>("BaritoneTimeOut", 5, 1, 20));
        this.rainbowHue = this.register(new Setting<Integer>("Delay", 240, 0, 600));
        this.rainbowBrightness = this.register(new Setting<Float>("Brightness ", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f)));
        this.rainbowSaturation = this.register(new Setting<Float>("Saturation", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f)));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onLoad() {
        AliceMain.commandManager.setClientMessage(this.getCommandMessage());
    }

    public String getCommandMessage() {
        if (this.rainbowPrefix.getPlannedValue().booleanValue()) {
            StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
            stringBuilder.insert(0, "\u00a7+");
            stringBuilder.append("\u00a7r");
            return String.valueOf(stringBuilder);
        }
        return String.valueOf(new StringBuilder().append(TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue())).append(TextUtil.coloredString(this.command.getPlannedValue(), this.commandColor.getPlannedValue())).append(TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue())));
    }

    public String getRawCommandMessage() {
        return String.valueOf(new StringBuilder().append(this.commandBracket.getValue()).append(this.command.getValue()).append(this.commandBracket2.getValue()));
    }

    static {
        INSTANCE = new Global();
    }
}

