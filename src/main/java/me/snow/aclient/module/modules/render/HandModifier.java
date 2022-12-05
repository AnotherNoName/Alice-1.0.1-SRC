//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

public class HandModifier
extends Module {
    public /* synthetic */ Setting<Boolean> chams;
    public /* synthetic */ Setting<Double> mainX;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Swing> swing;
    public /* synthetic */ Setting<Double> mainY;
    public /* synthetic */ Setting<RenderMode> mode;
    public /* synthetic */ Setting<Double> offY;
    public /* synthetic */ Setting<Settings> settings;
    public /* synthetic */ Setting<Boolean> anim;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Boolean> slowSwing;
    public /* synthetic */ Setting<Double> offX;
    private static /* synthetic */ HandModifier INSTANCE;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> swordChange;
    public /* synthetic */ Setting<Boolean> changeSwing;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> csync;

    public HandModifier() {
        super("HandModifier", "hand modifier", Module.Category.RENDER, true, false, false);
        this.settings = this.register(new Setting<Settings>("Settings", Settings.GLOBAL));
        this.chams = this.register(new Setting<Boolean>("HandChams", Boolean.FALSE, bl -> this.settings.getValue() == Settings.GLOBAL));
        this.mode = this.register(new Setting<RenderMode>("Mode", RenderMode.WIREFRAME, renderMode -> this.settings.getValue() == Settings.GLOBAL && this.chams.getValue() != false));
        this.changeSwing = this.register(new Setting<Object>("Swing", Boolean.FALSE, object -> this.settings.getValue() == Settings.GLOBAL));
        this.swing = this.register(new Setting<Object>("SwingHand", (Object)Swing.Mainhand, object -> this.settings.getValue() == Settings.GLOBAL && this.changeSwing.getValue() != false));
        this.csync = this.register(new Setting<Boolean>("ColorSync", Boolean.FALSE, bl -> this.chams.getValue() != false && this.settings.getValue() == Settings.COLORS));
        this.red = this.register(new Setting<Integer>("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.chams.getValue() != false && this.settings.getValue() == Settings.COLORS));
        this.green = this.register(new Setting<Integer>("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.chams.getValue() != false && this.settings.getValue() == Settings.COLORS));
        this.blue = this.register(new Setting<Integer>("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.chams.getValue() != false && this.settings.getValue() == Settings.COLORS));
        this.alpha = this.register(new Setting<Integer>("Alpha", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), n -> this.chams.getValue() != false && this.settings.getValue() == Settings.COLORS));
        this.anim = this.register(new Setting<Object>("NoSwapDelay", Boolean.FALSE, object -> this.settings.getValue() == Settings.GLOBAL));
        this.swordChange = this.register(new Setting<Object>("SwordHandSwap", Boolean.FALSE, object -> this.settings.getValue() == Settings.GLOBAL));
        this.slowSwing = this.register(new Setting<Object>("SlowSwing", Boolean.FALSE, object -> this.settings.getValue() == Settings.GLOBAL));
        this.mainX = this.register(new Setting<Double>("MainX", Double.valueOf(0.0), Double.valueOf(-1.0), Double.valueOf(1.0), d -> this.settings.getValue() == Settings.TRANSLATE));
        this.mainY = this.register(new Setting<Double>("MainY", Double.valueOf(0.0), Double.valueOf(-1.0), Double.valueOf(1.0), d -> this.settings.getValue() == Settings.TRANSLATE));
        this.offX = this.register(new Setting<Double>("OffX", Double.valueOf(0.0), Double.valueOf(-1.0), Double.valueOf(1.0), d -> this.settings.getValue() == Settings.TRANSLATE));
        this.offY = this.register(new Setting<Double>("OffY", Double.valueOf(0.0), Double.valueOf(-1.0), Double.valueOf(1.0), d -> this.settings.getValue() == Settings.TRANSLATE));
        this.setInstance();
    }

    public static HandModifier getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new HandModifier();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new HandModifier();
    }

    @Override
    public void onUpdate() {
        if (this.anim.getValue().booleanValue() && (double)HandModifier.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            HandModifier.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            HandModifier.mc.entityRenderer.itemRenderer.itemStackMainHand = HandModifier.mc.player.getHeldItemMainhand();
        }
        if (this.changeSwing.getValue().booleanValue()) {
            if (this.swing.getValue() == Swing.Offhand) {
                HandModifier.mc.player.swingingHand = EnumHand.OFF_HAND;
            } else if (this.swing.getValue() == Swing.Mainhand) {
                HandModifier.mc.player.swingingHand = EnumHand.MAIN_HAND;
            }
        }
        if (this.swordChange.getValue().booleanValue()) {
            if (EntityUtil.holdingWeapon((EntityPlayer)HandModifier.mc.player)) {
                HandModifier.mc.player.setPrimaryHand(EnumHandSide.LEFT);
            } else {
                HandModifier.mc.player.setPrimaryHand(EnumHandSide.RIGHT);
            }
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private static enum Settings {
        GLOBAL,
        TRANSLATE,
        COLORS;

    }

    public static enum RenderMode {
        SOLID,
        WIREFRAME;

    }

    private static enum Swing {
        Mainhand,
        Offhand;

    }
}

