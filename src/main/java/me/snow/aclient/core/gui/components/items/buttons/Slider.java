/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.core.gui.components.items.buttons;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.gui.components.Component;
import me.snow.aclient.core.gui.components.items.buttons.Button;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import org.lwjgl.input.Mouse;

public class Slider
extends Button {
    private final /* synthetic */ Number min;
    private final /* synthetic */ Number max;
    public /* synthetic */ Setting setting;
    private final /* synthetic */ int difference;

    private float part() {
        return ((Number)this.setting.getValue()).floatValue() - this.min.floatValue();
    }

    @Override
    public int getHeight() {
        return 11;
    }

    public Slider(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = (Number)setting.getMin();
        this.max = (Number)setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            this.setSettingFromX(n);
        }
    }

    @Override
    public void update() {
        this.setHidden(this.setting.isVisible());
    }

    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }

    private float partialMultiplier() {
        return this.part() / this.middle();
    }

    @Override
    public boolean isHovering(int n, int n2) {
        for (Component component : LuigiGui.getClickGui().getComponents()) {
            if (!component.drag) continue;
            return false;
        }
        return (float)n >= this.getX() && (float)n <= this.getX() + (float)this.getWidth() + 8.0f && (float)n2 >= this.getY() && (float)n2 <= this.getY() + (float)this.height;
    }

    private void dragSetting(int n, int n2) {
        if (this.isHovering(n, n2) && Mouse.isButtonDown((int)0)) {
            this.setSettingFromX(n);
        }
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        int n3;
        this.dragSetting(n, n2);
        RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height - 0.5f, !this.isHovering(n, n2) ? 0x11555555 : -2007673515);
        if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue()) {
            n3 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            int n4 = ColorUtil.changeAlpha(HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, this.renderer.scaledHeight)), AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue());
            RenderUtil.drawGradientRect(this.x, this.y, ((Number)this.setting.getValue()).floatValue() <= this.min.floatValue() ? 0.0f : ((float)this.width + 7.4f) * this.partialMultiplier(), (float)this.height - 0.5f, !this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : n3, !this.isHovering(n, n2) ? HUD.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, this.renderer.scaledHeight)) : n4);
        } else {
            RenderUtil.drawRect(this.x, this.y, ((Number)this.setting.getValue()).floatValue() <= this.min.floatValue() ? this.x : this.x + ((float)this.width + 7.4f) * this.partialMultiplier(), this.y + (float)this.height - 0.5f, !this.isHovering(n, n2) ? AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()) : AliceMain.colorManager.getColorWithAlpha(AliceMain.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()));
        }
        if (ClickGui.getInstance().sideSettings.getValue().booleanValue()) {
            n3 = ColorUtil.toARGB(ClickGui.getInstance().sidered.getValue(), ClickGui.getInstance().sidegreen.getValue(), ClickGui.getInstance().sideblue.getValue(), ClickGui.getInstance().sidealpha.getValue());
            RenderUtil.drawRect(this.x, this.y, this.x + 1.0f, this.y + (float)this.height + 1.0f, n3);
        }
        AliceMain.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.getName()).append(" \u00a77").append(this.setting.getValue() instanceof Float ? (Number)((Number)this.setting.getValue()) : (Number)((Number)this.setting.getValue()).doubleValue())), this.x + 2.3f, this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), -1);
    }

    private void setSettingFromX(int n) {
        float f = ((float)n - this.x) / ((float)this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            double d = (Double)this.setting.getMin() + (double)((float)this.difference * f);
            this.setting.setValue((double)Math.round(10.0 * d) / 10.0);
        } else if (this.setting.getValue() instanceof Float) {
            float f2 = ((Float)this.setting.getMin()).floatValue() + (float)this.difference * f;
            this.setting.setValue(Float.valueOf((float)Math.round(10.0f * f2) / 10.0f));
        } else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((Integer)this.setting.getMin() + (int)((float)this.difference * f));
        }
    }
}

