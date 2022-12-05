/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.client;

import java.awt.GraphicsEnvironment;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CustomFont
extends Module {
    public /* synthetic */ Setting<Boolean> shadow;
    public /* synthetic */ Setting<Integer> fontStyle;
    public /* synthetic */ Setting<String> fontName;
    private static /* synthetic */ CustomFont INSTANCE;
    public /* synthetic */ Setting<Boolean> full;
    private /* synthetic */ boolean reloadFont;
    public /* synthetic */ Setting<Boolean> antiAlias;
    public /* synthetic */ Setting<Boolean> fractionalMetrics;
    public /* synthetic */ Setting<Boolean> showFonts;
    public /* synthetic */ Setting<Integer> fontSize;

    public CustomFont() {
        super("CustomFont", "CustomFont for all of the clients text. Use the font command.", Module.Category.CLIENT, true, false, false);
        this.fontName = this.register(new Setting<String>("FontName", "Arial", "Name of the font."));
        this.fontSize = this.register(new Setting<Integer>("FontSize", Integer.valueOf(18), Integer.valueOf(12), Integer.valueOf(25), "Size of the font."));
        this.fontStyle = this.register(new Setting<Integer>("FontStyle", Integer.valueOf(0), "Style of the font."));
        this.antiAlias = this.register(new Setting<Boolean>("AntiAlias", Boolean.TRUE, "Smoother font."));
        this.fractionalMetrics = this.register(new Setting<Boolean>("Metrics", Boolean.TRUE, "Thinner font."));
        this.shadow = this.register(new Setting<Boolean>("Shadow", Boolean.FALSE, "Less shadow offset font."));
        this.showFonts = this.register(new Setting<Boolean>("Fonts", Boolean.FALSE, "Shows all fonts."));
        this.full = this.register(new Setting<Boolean>("Full", false));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    static {
        INSTANCE = new CustomFont();
    }

    public static boolean checkFont(String string, boolean bl) {
        for (String string2 : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (!bl && string2.equals(string)) {
                return true;
            }
            if (!bl) continue;
            Command.sendMessage(string2);
        }
        return false;
    }

    @Override
    public void onTick() {
        if (this.showFonts.getValue().booleanValue()) {
            CustomFont.checkFont("Hello", true);
            Command.sendMessage(String.valueOf(new StringBuilder().append("Current Font: ").append(this.fontName.getValue())));
            this.showFonts.setValue(false);
        }
        if (this.reloadFont) {
            AliceMain.textManager.init(false);
            this.reloadFont = false;
        }
    }

    public static CustomFont getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomFont();
        }
        return INSTANCE;
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        Setting setting;
        if (clientEvent.getStage() == 2 && (setting = clientEvent.getSetting()) != null && setting.getFeature().equals(this)) {
            if (setting.getName().equals("FontName") && !CustomFont.checkFont(setting.getPlannedValue().toString(), false)) {
                Command.sendMessage("\u00a7cThat font doesnt exist.");
                clientEvent.setCanceled(true);
                return;
            }
            this.reloadFont = true;
        }
    }
}

