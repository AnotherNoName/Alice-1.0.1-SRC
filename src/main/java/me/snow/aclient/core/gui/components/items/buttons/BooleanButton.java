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

public class BooleanButton
extends Button {
    private final /* synthetic */ Setting setting;

    @Override
    public boolean getState() {
        return (Boolean)this.setting.getValue();
    }

    public BooleanButton(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }

    @Override
    public void update() {
        this.setHidden(this.setting.isVisible());
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
        }
    }

    @Override
    public void toggle() {
        this.setting.setValue((Boolean)this.setting.getValue() == false);
    }

    @Override
    public int getHeight() {
        return 11;
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        int n3;
        if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue()) {
            n3 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            int n4 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (float)this.width + 7.4f, (float)this.height - 0.5f, this.getState() ? (!this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : n3) : (!this.isHovering(n, n2) ? 0x11555555 : -2007673515), this.getState() ? (!this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)) : n4) : (!this.isHovering(n, n2) ? 0x11555555 : -2007673515));
        } else {
            RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, this.getState() ? (!this.isHovering(n, n2) ? AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())) : (!this.isHovering(n, n2) ? 0x11555555 : -2007673515));
        }
        if (ClickGui.getInstance().sideSettings.getValue().booleanValue()) {
            n3 = ColorUtil.toARGB(ClickGui.getInstance().sidered.getValue(), ClickGui.getInstance().sidegreen.getValue(), ClickGui.getInstance().sideblue.getValue(), ClickGui.getInstance().sidealpha.getValue());
            RenderUtil.drawRect(this.x, this.y, this.x + 1.0f, this.y + (float)this.height + 1.0f, n3);
        }
        AliceMain.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
}

