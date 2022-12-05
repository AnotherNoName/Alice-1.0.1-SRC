//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.SoundEvent
 */
package me.snow.aclient.core.gui.components.items.buttons;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.gui.components.items.buttons.Button;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class UnlimitedSlider
extends Button {
    public /* synthetic */ Setting setting;

    @Override
    public int getHeight() {
        return 11;
    }

    public boolean isRight(int n) {
        return (float)n > this.x + ((float)this.width + 7.4f) / 2.0f;
    }

    @Override
    public void toggle() {
    }

    @Override
    public boolean getState() {
        return true;
    }

    public UnlimitedSlider(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            if (this.isRight(n)) {
                if (this.setting.getValue() instanceof Double) {
                    this.setting.setValue((Double)this.setting.getValue() + 1.0);
                } else if (this.setting.getValue() instanceof Float) {
                    this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() + 1.0f));
                } else if (this.setting.getValue() instanceof Integer) {
                    this.setting.setValue((Integer)this.setting.getValue() + 1);
                }
            } else if (this.setting.getValue() instanceof Double) {
                this.setting.setValue((Double)this.setting.getValue() - 1.0);
            } else if (this.setting.getValue() instanceof Float) {
                this.setting.setValue(Float.valueOf(((Float)this.setting.getValue()).floatValue() - 1.0f));
            } else if (this.setting.getValue() instanceof Integer) {
                this.setting.setValue((Integer)this.setting.getValue() - 1);
            }
        }
    }

    @Override
    public void update() {
        this.setHidden(this.setting.isVisible());
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue()) {
            int n3 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            int n4 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect((float)((int)this.x), (float)((int)this.y), (float)this.width + 7.4f, (float)this.height, n3, n4);
        } else {
            RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, !this.isHovering(n, n2) ? AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()));
        }
        AliceMain.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(" - ").append(this.setting.getName()).append(" \u00a77").append(this.setting.getValue()).append("\u00a7r +")), this.x + 2.3f, this.y - 6.0f - (float)LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
}

