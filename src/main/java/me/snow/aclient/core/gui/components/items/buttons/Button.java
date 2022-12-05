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
import me.snow.aclient.core.gui.components.Component;
import me.snow.aclient.core.gui.components.items.Item;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class Button
extends Item {
    private /* synthetic */ boolean state;

    public Button(String string) {
        super(string);
        this.height = 12;
    }

    public boolean isHovering(int n, int n2) {
        for (Component component : LuigiGui.getClickGui().getComponents()) {
            if (!component.drag) continue;
            return false;
        }
        return (float)n >= this.getX() && (float)n <= this.getX() + (float)this.getWidth() && (float)n2 >= this.getY() && (float)n2 <= this.getY() + (float)this.height;
    }

    public boolean getState() {
        return this.state;
    }

    public void toggle() {
    }

    @Override
    public int getHeight() {
        return 11;
    }

    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        if (ClickGui.getInstance().moduleOutline.getValue().booleanValue()) {
            RenderUtil.drawOutlineRect(this.x, this.y, this.x + (float)this.width, this.y + (float)this.height - 0.2f, this.getState() ? ColorUtil.toRGBA(ClickGui.getInstance().moRed.getValue(), ClickGui.getInstance().moGreen.getValue(), ClickGui.getInstance().moBlue.getValue(), ClickGui.getInstance().moAlpha.getValue()) : ColorUtil.toRGBA(ClickGui.getInstance().moRed.getValue(), ClickGui.getInstance().moGreen.getValue(), ClickGui.getInstance().moBlue.getValue(), ClickGui.getInstance().moAlpha.getValue()));
        }
        if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue()) {
            int n3 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            int n4 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, (float)this.width, (float)this.height - 0.5f, this.getState() ? (!this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : n3) : (!this.isHovering(n, n2) ? 0x11555555 : -2007673515), this.getState() ? (!this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)) : n4) : (!this.isHovering(n, n2) ? ColorUtil.toRGBA(ClickGui.getInstance().d_red.getValue(), ClickGui.getInstance().d_green.getValue(), ClickGui.getInstance().d_blue.getValue(), ClickGui.getInstance().d_alpha.getValue()) : -2007673515));
        } else {
            RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width, this.y + (float)this.height - 0.5f, this.getState() ? (!this.isHovering(n, n2) ? AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue())) : (!this.isHovering(n, n2) ? ColorUtil.toRGBA(ClickGui.getInstance().d_red.getValue(), ClickGui.getInstance().d_green.getValue(), ClickGui.getInstance().d_blue.getValue(), ClickGui.getInstance().d_alpha.getValue()) : -2007673515));
        }
        AliceMain.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), this.getState() ? ColorUtil.toRGBA(ClickGui.getInstance().textRed.getValue(), ClickGui.getInstance().textGreen.getValue(), ClickGui.getInstance().textBlue.getValue(), ClickGui.getInstance().textAlpha.getValue()) : ColorUtil.toRGBA(ClickGui.getInstance().textRed2.getValue(), ClickGui.getInstance().textGreen2.getValue(), ClickGui.getInstance().textBlue2.getValue(), ClickGui.getInstance().textAlpha2.getValue()));
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.onMouseClick();
        }
    }
}

