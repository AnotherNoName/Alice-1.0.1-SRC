//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.settings.GameSettings$Options
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.client;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.client.CustomFont;
import me.snow.aclient.util.Util;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickGui
extends Module {
    public /* synthetic */ Setting<Boolean> futuregear;
    public /* synthetic */ Setting<String> prefix;
    public /* synthetic */ Setting<Integer> moGreen;
    public /* synthetic */ Setting<Integer> sideblue;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> ARed;
    public /* synthetic */ Setting<Boolean> TcolorSync;
    public /* synthetic */ Setting<Boolean> PBlack;
    public /* synthetic */ Setting<Integer> b_green;
    public /* synthetic */ Setting<Integer> b_blue;
    public /* synthetic */ Setting<Integer> AGreen;
    public /* synthetic */ Setting<String> close;
    public /* synthetic */ Setting<Boolean> particles;
    public /* synthetic */ Setting<Integer> topRed;
    public /* synthetic */ Setting<Boolean> PPhobos;
    public /* synthetic */ Setting<Boolean> outline2;
    public /* synthetic */ Setting<Boolean> sideSettings;
    public /* synthetic */ Setting<Integer> moBlue;
    public /* synthetic */ Setting<Integer> d_alpha;
    public /* synthetic */ Setting<Integer> textAlpha;
    public /* synthetic */ Setting<Integer> ABlue;
    public /* synthetic */ Setting<Boolean> snowing;
    public /* synthetic */ Setting<Boolean> gradiant;
    public /* synthetic */ Setting<Boolean> frameSettings;
    public /* synthetic */ Setting<Integer> topBlue;
    public /* synthetic */ Setting<Integer> textGreen2;
    public /* synthetic */ Setting<Integer> textBlue;
    public /* synthetic */ Setting<Float> fov;
    public /* synthetic */ Setting<Integer> d_green;
    public /* synthetic */ Setting<Integer> sidered;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> particlegreen;
    public /* synthetic */ Setting<Integer> frameGreen;
    public /* synthetic */ Setting<Integer> particleblue;
    public /* synthetic */ Setting<Integer> sidealpha;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Integer> moAlpha;
    public /* synthetic */ Setting<Boolean> toparrow;
    public /* synthetic */ Setting<Integer> textRed;
    public /* synthetic */ Setting<Integer> textRed2;
    public /* synthetic */ Setting<Boolean> rainbowRolling;
    public /* synthetic */ Setting<Boolean> centerAAA;
    public /* synthetic */ Setting<Integer> frameBlue;
    public /* synthetic */ Setting<Integer> gradiantblue;
    public /* synthetic */ Setting<Integer> b_red;
    public /* synthetic */ Setting<Integer> gradiantalpha;
    private static /* synthetic */ ClickGui INSTANCE;
    public /* synthetic */ Setting<String> moduleButton;
    public /* synthetic */ Setting<Boolean> PRed;
    public /* synthetic */ Setting<Integer> frameRed;
    public /* synthetic */ Setting<Integer> textBlue2;
    public /* synthetic */ Setting<Integer> frameAlpha;
    public /* synthetic */ Setting<Integer> topGreen;
    public /* synthetic */ Setting<Integer> textGreen;
    public /* synthetic */ Setting<Boolean> moduleOutline;
    public /* synthetic */ Setting<Integer> d_red;
    public /* synthetic */ Setting<Boolean> openCloseChange;
    public /* synthetic */ Setting<Integer> gradiantgreen;
    public /* synthetic */ Setting<String> open;
    public /* synthetic */ Setting<Boolean> dark;
    public /* synthetic */ Setting<Integer> gradiantred;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Integer> o_green;
    public /* synthetic */ Setting<Page> setting;
    public /* synthetic */ Setting<Integer> sidegreen;
    public /* synthetic */ Setting<Integer> o_blue;
    public /* synthetic */ Setting<Integer> textAlpha2;
    public /* synthetic */ Setting<Integer> particleLength;
    public /* synthetic */ Setting<Integer> topAlpha;
    public /* synthetic */ Setting<Float> outlineThickness;
    public /* synthetic */ Setting<Integer> d_blue;
    public /* synthetic */ Setting<Boolean> customFov;
    public /* synthetic */ Setting<Integer> moRed;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Integer> b_alpha;
    public /* synthetic */ Setting<Integer> hoverAlpha;
    public /* synthetic */ Setting<Integer> o_red;
    public /* synthetic */ Setting<Integer> particlered;
    public /* synthetic */ Setting<Integer> o_alpha;

    @Override
    public void onLoad() {
        if (this.colorSync.getValue().booleanValue()) {
            AliceMain.colorManager.setColor(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), this.hoverAlpha.getValue());
        } else {
            AliceMain.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        }
        AliceMain.commandManager.setPrefix(this.prefix.getValue());
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().getFeature().equals(this)) {
            if (clientEvent.getSetting().equals(this.prefix)) {
                AliceMain.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix set to \u00a7a").append(AliceMain.commandManager.getPrefix())));
            }
            AliceMain.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }

    @Override
    public void onUpdate() {
        if (this.customFov.getValue().booleanValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, this.fov.getValue().floatValue());
        }
    }

    static {
        INSTANCE = new ClickGui();
    }

    @Override
    public void onEnable() {
        Util.mc.displayGuiScreen((GuiScreen)new LuigiGui());
    }

    @Override
    public void onDisable() {
        if (ClickGui.mc.currentScreen instanceof LuigiGui) {
            Util.mc.displayGuiScreen(null);
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof LuigiGui)) {
            this.disable();
        }
        if (this.PRed.getValue().booleanValue()) {
            this.colorSync.setValue(false);
            this.TcolorSync.setValue(false);
            this.red.setValue(225);
            this.green.setValue(0);
            this.blue.setValue(0);
            this.hoverAlpha.setValue(130);
            this.b_red.setValue(0);
            this.b_green.setValue(0);
            this.b_blue.setValue(0);
            this.b_alpha.setValue(85);
            this.d_red.setValue(0);
            this.d_green.setValue(0);
            this.d_blue.setValue(0);
            this.d_alpha.setValue(31);
            this.alpha.setValue(240);
            this.topRed.setValue(255);
            this.topGreen.setValue(0);
            this.topBlue.setValue(0);
            this.topAlpha.setValue(100);
            this.centerAAA.setValue(true);
            this.openCloseChange.setValue(false);
            this.open.setValue("+");
            this.close.setValue("-");
            this.moduleButton.setValue("");
            this.futuregear.setValue(false);
            this.toparrow.setValue(false);
            this.customFov.setValue(false);
            this.ARed.setValue(160);
            this.AGreen.setValue(160);
            this.ABlue.setValue(160);
            this.sideSettings.setValue(false);
            this.sidered.setValue(255);
            this.sidegreen.setValue(0);
            this.sideblue.setValue(0);
            this.sidealpha.setValue(255);
            this.frameSettings.setValue(false);
            this.frameRed.setValue(255);
            this.frameGreen.setValue(0);
            this.frameBlue.setValue(0);
            this.frameAlpha.setValue(255);
            this.outline.setValue(false);
            this.outline2.setValue(true);
            this.outlineThickness.setValue(Float.valueOf(1.0f));
            this.o_red.setValue(255);
            this.o_green.setValue(0);
            this.o_blue.setValue(0);
            this.o_alpha.setValue(255);
            this.moduleOutline.setValue(false);
            this.moRed.setValue(0);
            this.moGreen.setValue(0);
            this.moBlue.setValue(0);
            this.moAlpha.setValue(255);
            this.snowing.setValue(true);
            this.dark.setValue(false);
            this.gradiant.setValue(true);
            this.gradiantred.setValue(255);
            this.gradiantgreen.setValue(0);
            this.gradiantblue.setValue(0);
            this.gradiantalpha.setValue(255);
            this.particles.setValue(true);
            this.particleLength.setValue(86);
            this.particlered.setValue(255);
            this.particlegreen.setValue(0);
            this.particleblue.setValue(0);
            this.textRed.setValue(255);
            this.textGreen.setValue(255);
            this.textBlue.setValue(255);
            this.textAlpha.setValue(255);
            this.textRed2.setValue(255);
            this.textGreen2.setValue(255);
            this.textBlue2.setValue(255);
            this.textAlpha2.setValue(255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue("Dialog");
            CustomFont.getInstance().fontSize.setValue(18);
            CustomFont.getInstance().fontStyle.setValue(0);
            CustomFont.getInstance().antiAlias.setValue(true);
            CustomFont.getInstance().fractionalMetrics.setValue(true);
            CustomFont.getInstance().shadow.setValue(false);
            this.PRed.setValue(false);
        }
        if (this.PBlack.getValue().booleanValue()) {
            this.colorSync.setValue(false);
            this.TcolorSync.setValue(false);
            this.red.setValue(255);
            this.green.setValue(36);
            this.blue.setValue(130);
            this.hoverAlpha.setValue(130);
            this.b_red.setValue(14);
            this.b_green.setValue(14);
            this.b_blue.setValue(14);
            this.b_alpha.setValue(130);
            this.d_red.setValue(26);
            this.d_green.setValue(48);
            this.d_blue.setValue(90);
            this.d_alpha.setValue(9);
            this.alpha.setValue(240);
            this.topRed.setValue(31);
            this.topGreen.setValue(31);
            this.topBlue.setValue(31);
            this.topAlpha.setValue(255);
            this.centerAAA.setValue(false);
            this.openCloseChange.setValue(false);
            this.open.setValue("+");
            this.close.setValue("-");
            this.moduleButton.setValue("");
            this.futuregear.setValue(false);
            this.toparrow.setValue(false);
            this.customFov.setValue(false);
            this.ARed.setValue(255);
            this.AGreen.setValue(255);
            this.ABlue.setValue(255);
            this.sideSettings.setValue(false);
            this.sidered.setValue(255);
            this.sidegreen.setValue(0);
            this.sideblue.setValue(0);
            this.sidealpha.setValue(255);
            this.frameSettings.setValue(false);
            this.frameRed.setValue(255);
            this.frameGreen.setValue(0);
            this.frameBlue.setValue(0);
            this.frameAlpha.setValue(255);
            this.outline.setValue(false);
            this.outline2.setValue(false);
            this.outlineThickness.setValue(Float.valueOf(1.0f));
            this.o_red.setValue(255);
            this.o_green.setValue(0);
            this.o_blue.setValue(0);
            this.o_alpha.setValue(255);
            this.moduleOutline.setValue(false);
            this.moRed.setValue(0);
            this.moGreen.setValue(0);
            this.moBlue.setValue(0);
            this.moAlpha.setValue(255);
            this.snowing.setValue(false);
            this.dark.setValue(false);
            this.gradiant.setValue(true);
            this.gradiantred.setValue(156);
            this.gradiantgreen.setValue(153);
            this.gradiantblue.setValue(151);
            this.gradiantalpha.setValue(255);
            this.particles.setValue(false);
            this.particleLength.setValue(150);
            this.particlered.setValue(36);
            this.particlegreen.setValue(150);
            this.particleblue.setValue(190);
            this.textRed.setValue(255);
            this.textGreen.setValue(255);
            this.textBlue.setValue(255);
            this.textAlpha.setValue(255);
            this.textRed2.setValue(255);
            this.textGreen2.setValue(255);
            this.textBlue2.setValue(255);
            this.textAlpha2.setValue(255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue("Tahoma");
            CustomFont.getInstance().fontSize.setValue(18);
            CustomFont.getInstance().fontStyle.setValue(0);
            CustomFont.getInstance().antiAlias.setValue(true);
            CustomFont.getInstance().fractionalMetrics.setValue(true);
            CustomFont.getInstance().shadow.setValue(false);
            this.PBlack.setValue(false);
        }
        if (this.PPhobos.getValue().booleanValue()) {
            this.colorSync.setValue(false);
            this.TcolorSync.setValue(false);
            this.red.setValue(36);
            this.green.setValue(150);
            this.blue.setValue(190);
            this.hoverAlpha.setValue(130);
            this.b_red.setValue(0);
            this.b_green.setValue(0);
            this.b_blue.setValue(0);
            this.b_alpha.setValue(75);
            this.d_red.setValue(127);
            this.d_green.setValue(127);
            this.d_blue.setValue(127);
            this.d_alpha.setValue(0);
            this.alpha.setValue(240);
            this.topRed.setValue(36);
            this.topGreen.setValue(150);
            this.topBlue.setValue(190);
            this.topAlpha.setValue(100);
            this.centerAAA.setValue(false);
            this.openCloseChange.setValue(true);
            this.open.setValue("+");
            this.close.setValue("-");
            this.moduleButton.setValue("");
            this.futuregear.setValue(false);
            this.toparrow.setValue(false);
            this.customFov.setValue(false);
            this.ARed.setValue(160);
            this.AGreen.setValue(160);
            this.ABlue.setValue(160);
            this.sideSettings.setValue(false);
            this.sidered.setValue(255);
            this.sidegreen.setValue(255);
            this.sideblue.setValue(255);
            this.sidealpha.setValue(255);
            this.frameSettings.setValue(false);
            this.frameRed.setValue(160);
            this.frameGreen.setValue(255);
            this.frameBlue.setValue(255);
            this.frameAlpha.setValue(255);
            this.outline.setValue(false);
            this.outline2.setValue(false);
            this.outlineThickness.setValue(Float.valueOf(1.0f));
            this.o_red.setValue(255);
            this.o_green.setValue(0);
            this.o_blue.setValue(0);
            this.o_alpha.setValue(255);
            this.moduleOutline.setValue(true);
            this.moRed.setValue(0);
            this.moGreen.setValue(0);
            this.moBlue.setValue(0);
            this.moAlpha.setValue(255);
            this.snowing.setValue(true);
            this.dark.setValue(false);
            this.gradiant.setValue(false);
            this.gradiantred.setValue(36);
            this.gradiantgreen.setValue(150);
            this.gradiantblue.setValue(190);
            this.gradiantalpha.setValue(255);
            this.particles.setValue(true);
            this.particleLength.setValue(150);
            this.particlered.setValue(36);
            this.particlegreen.setValue(150);
            this.particleblue.setValue(190);
            this.textRed.setValue(255);
            this.textGreen.setValue(255);
            this.textBlue.setValue(255);
            this.textAlpha.setValue(255);
            this.textRed2.setValue(160);
            this.textGreen2.setValue(160);
            this.textBlue2.setValue(160);
            this.textAlpha2.setValue(255);
            if (CustomFont.getInstance().isOff()) {
                CustomFont.getInstance().enable();
            }
            CustomFont.getInstance().fontName.setValue("Arial");
            CustomFont.getInstance().fontSize.setValue(18);
            CustomFont.getInstance().fontStyle.setValue(0);
            CustomFont.getInstance().antiAlias.setValue(true);
            CustomFont.getInstance().fractionalMetrics.setValue(true);
            CustomFont.getInstance().shadow.setValue(false);
            this.PPhobos.setValue(false);
        }
    }

    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Module.Category.CLIENT, true, false, false);
        this.prefix = this.register(new Setting<String>("Prefix", ".").setRenderName(true));
        this.colorSync = this.register(new Setting<Boolean>("CSync", false));
        this.TcolorSync = this.register(new Setting<Boolean>("TopCSync", false));
        this.rainbowRolling = this.register(new Setting<Object>("RollingRainbow", Boolean.valueOf(false), object -> this.colorSync.getValue() != false && Colors.INSTANCE.rainbow.getValue() != false));
        this.setting = this.register(new Setting<Page>("Settings", Page.Main));
        this.red = this.register(new Setting<Integer>("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.green = this.register(new Setting<Integer>("Green", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.blue = this.register(new Setting<Integer>("Blue", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.hoverAlpha = this.register(new Setting<Integer>("Alpha", Integer.valueOf(130), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.b_red = this.register(new Setting<Integer>("BackgroundRed", Integer.valueOf(14), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.b_green = this.register(new Setting<Integer>("BackgroundGreen", Integer.valueOf(14), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.b_blue = this.register(new Setting<Integer>("BackgroundBlue", Integer.valueOf(14), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.b_alpha = this.register(new Setting<Object>("BackgroundAlpha", Integer.valueOf(130), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.d_red = this.register(new Setting<Object>("DisabledRed", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.d_green = this.register(new Setting<Object>("DisabledGreen", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.d_blue = this.register(new Setting<Object>("DisabledBlue", Integer.valueOf(127), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.d_alpha = this.register(new Setting<Object>("DisabledAlpha", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.alpha = this.register(new Setting<Integer>("HoverAlpha", Integer.valueOf(240), Integer.valueOf(0), Integer.valueOf(255), n -> this.setting.getValue() == Page.Main));
        this.topRed = this.register(new Setting<Object>("TopRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.topGreen = this.register(new Setting<Object>("TopGreen", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.topBlue = this.register(new Setting<Object>("TopBlue", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.topAlpha = this.register(new Setting<Object>("TopAlpha", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Main));
        this.centerAAA = this.register(new Setting<Boolean>("TitleCenter", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Misc));
        this.openCloseChange = this.register(new Setting<Boolean>("Open/Close", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Misc));
        this.open = this.register(new Setting<Object>("Open:", "+", object -> this.setting.getValue() == Page.Misc && this.openCloseChange.getValue() != false).setRenderName(true));
        this.close = this.register(new Setting<Object>("Close:", "-", object -> this.setting.getValue() == Page.Misc && this.openCloseChange.getValue() != false).setRenderName(true));
        this.moduleButton = this.register(new Setting<Object>("Buttons:", "", object -> this.setting.getValue() == Page.Misc && this.openCloseChange.getValue() == false).setRenderName(true));
        this.futuregear = this.register(new Setting<Object>("FutureGear", Boolean.valueOf(false), object -> this.setting.getValue() == Page.Misc));
        this.toparrow = this.register(new Setting<Object>("TopArrow", Boolean.valueOf(false), object -> this.setting.getValue() == Page.Misc));
        this.customFov = this.register(new Setting<Boolean>("CustomFov", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Misc));
        this.fov = this.register(new Setting<Object>("Fov", Float.valueOf(150.0f), Float.valueOf(-180.0f), Float.valueOf(180.0f), object -> this.customFov.getValue() != false && this.setting.getValue() == Page.Misc));
        this.ARed = this.register(new Setting<Object>("StringGearRed", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Misc));
        this.AGreen = this.register(new Setting<Object>("StringGearGreen", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Misc));
        this.ABlue = this.register(new Setting<Object>("StringGearBlue", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Misc));
        this.sideSettings = this.register(new Setting<Boolean>("SideSetting", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Line));
        this.sidered = this.register(new Setting<Integer>("SideRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.sideSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.sidegreen = this.register(new Setting<Integer>("SideGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.sideSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.sideblue = this.register(new Setting<Integer>("SideBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.sideSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.sidealpha = this.register(new Setting<Integer>("SideAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.sideSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.frameSettings = this.register(new Setting<Boolean>("FrameSetting", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Line));
        this.frameRed = this.register(new Setting<Integer>("FrameRed", Integer.valueOf(160), Integer.valueOf(0), Integer.valueOf(255), n -> this.frameSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.frameGreen = this.register(new Setting<Integer>("FrameGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.frameSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.frameBlue = this.register(new Setting<Integer>("FrameBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.frameSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.frameAlpha = this.register(new Setting<Integer>("FrameAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.frameSettings.getValue() != false && this.setting.getValue() == Page.Line));
        this.outline = this.register(new Setting<Boolean>("OutlineOld", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Line));
        this.outline2 = this.register(new Setting<Object>("Outline2", Boolean.valueOf(false), object -> this.setting.getValue() == Page.Line));
        this.outlineThickness = this.register(new Setting<Float>("LineThickness", Float.valueOf(1.0f), Float.valueOf(0.5f), Float.valueOf(5.0f), f -> this.outline2.getValue() != false && this.setting.getValue() == Page.Line));
        this.o_red = this.register(new Setting<Object>("OutlineRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline2.getValue() != false && this.setting.getValue() == Page.Line));
        this.o_green = this.register(new Setting<Object>("OutlineGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline2.getValue() != false && this.setting.getValue() == Page.Line));
        this.o_blue = this.register(new Setting<Object>("OutlineBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline2.getValue() != false && this.setting.getValue() == Page.Line));
        this.o_alpha = this.register(new Setting<Object>("OutlineAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline2.getValue() != false && this.setting.getValue() == Page.Line));
        this.moduleOutline = this.register(new Setting<Boolean>("Module Outline", Boolean.valueOf(true), bl -> this.setting.getValue() == Page.Line));
        this.moRed = this.register(new Setting<Object>("Module OutlineRed", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.moduleOutline.getValue() != false && this.setting.getValue() == Page.Line));
        this.moGreen = this.register(new Setting<Object>("Module OutlineGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.moduleOutline.getValue() != false && this.setting.getValue() == Page.Line));
        this.moBlue = this.register(new Setting<Object>("Module OutlineBlue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.moduleOutline.getValue() != false && this.setting.getValue() == Page.Line));
        this.moAlpha = this.register(new Setting<Object>("Module OutlineAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.moduleOutline.getValue() != false && this.setting.getValue() == Page.Line));
        this.snowing = this.register(new Setting<Object>("Snowing", Boolean.valueOf(false), object -> this.setting.getValue() == Page.BackGround));
        this.dark = this.register(new Setting<Boolean>("BackGround Dark", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.BackGround));
        this.gradiant = this.register(new Setting<Boolean>("Gradiant", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.BackGround));
        this.gradiantred = this.register(new Setting<Object>("GradiantRed", Integer.valueOf(36), Integer.valueOf(0), Integer.valueOf(255), object -> this.gradiant.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.gradiantgreen = this.register(new Setting<Object>("GradiantGreen", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), object -> this.gradiant.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.gradiantblue = this.register(new Setting<Object>("GradiantBlue", Integer.valueOf(190), Integer.valueOf(0), Integer.valueOf(255), object -> this.gradiant.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.gradiantalpha = this.register(new Setting<Object>("GradiantAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.gradiant.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.particles = this.register(new Setting<Object>("Particles", Boolean.valueOf(false), object -> this.setting.getValue() == Page.BackGround));
        this.particleLength = this.register(new Setting<Object>("ParticleLength", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(300), object -> this.particles.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.particlered = this.register(new Setting<Object>("ParticleRed", Integer.valueOf(36), Integer.valueOf(0), Integer.valueOf(255), object -> this.particles.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.particlegreen = this.register(new Setting<Object>("ParticleGreen", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), object -> this.particles.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.particleblue = this.register(new Setting<Object>("ParticleBlue", Integer.valueOf(190), Integer.valueOf(0), Integer.valueOf(255), object -> this.particles.getValue() != false && this.setting.getValue() == Page.BackGround));
        this.textRed = this.register(new Setting<Object>("EnabledTextRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textGreen = this.register(new Setting<Object>("EnabledTextGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textBlue = this.register(new Setting<Object>("EnabledTextBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textAlpha = this.register(new Setting<Object>("EnabledTextAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textRed2 = this.register(new Setting<Object>("DisabledTextRed", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textGreen2 = this.register(new Setting<Object>("DisabledTextGreen", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textBlue2 = this.register(new Setting<Object>("DisabledTextBlue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.textAlpha2 = this.register(new Setting<Object>("DisabledTextAlpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == Page.Text));
        this.PRed = this.register(new Setting<Boolean>("PresetRed", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Preset));
        this.PBlack = this.register(new Setting<Boolean>("PresetBlack", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Preset));
        this.PPhobos = this.register(new Setting<Boolean>("PresetDefault", Boolean.valueOf(false), bl -> this.setting.getValue() == Page.Preset));
        this.setInstance();
    }

    public static enum rainbowMode {
        Static,
        Sideway;

    }

    public static enum rainbowModeArray {
        Static,
        Up;

    }

    private static enum Page {
        Main,
        Misc,
        Line,
        BackGround,
        Text,
        Preset;

    }
}

