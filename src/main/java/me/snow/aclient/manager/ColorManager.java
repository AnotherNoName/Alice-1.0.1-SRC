/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import java.awt.Color;
import me.snow.aclient.util.ColorUtil;

public class ColorManager {
    private /* synthetic */ float alpha2;
    private /* synthetic */ float green2;
    private /* synthetic */ Color color2;
    private /* synthetic */ float green;
    private /* synthetic */ float red;
    private /* synthetic */ float blue;
    private /* synthetic */ float red2;
    private /* synthetic */ float alpha;
    private /* synthetic */ Color color;
    private /* synthetic */ float blue2;

    public void setColor2(float f, float f2, float f3, float f4) {
        this.red2 = f;
        this.green2 = f2;
        this.blue2 = f3;
        this.alpha2 = f4;
        this.updateColor2();
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.red = f;
        this.green = f2;
        this.blue = f3;
        this.alpha = f4;
        this.updateColor();
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor2(Color color) {
        this.color2 = color;
    }

    public int getColorAsIntFullAlpha2() {
        return ColorUtil.toRGBA(new Color(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), 255));
    }

    public Color getColor2() {
        return this.color2;
    }

    public void setColor(int n, int n2, int n3, int n4) {
        this.red = (float)n / 255.0f;
        this.green = (float)n2 / 255.0f;
        this.blue = (float)n3 / 255.0f;
        this.alpha = (float)n4 / 255.0f;
        this.updateColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getColorAsInt2() {
        return ColorUtil.toRGBA(this.color2);
    }

    public void setRed(float f) {
        this.red = f;
        this.updateColor();
    }

    public void setBlue2(float f) {
        this.blue2 = f;
        this.updateColor2();
    }

    public void setAlpha2(float f) {
        this.alpha2 = f;
        this.updateColor2();
    }

    public void setRed2(float f) {
        this.red2 = f;
        this.updateColor2();
    }

    public ColorManager() {
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.color = new Color(this.red, this.green, this.blue, this.alpha);
        this.red2 = 1.0f;
        this.green2 = 1.0f;
        this.blue2 = 1.0f;
        this.alpha2 = 1.0f;
        this.color2 = new Color(this.red2, this.green2, this.blue2, this.alpha2);
    }

    public void setAlpha(float f) {
        this.alpha = f;
        this.updateColor();
    }

    public int getColorAsInt() {
        return ColorUtil.toRGBA(this.color);
    }

    public void setColor2(int n, int n2, int n3, int n4) {
        this.red2 = (float)n / 255.0f;
        this.green2 = (float)n2 / 255.0f;
        this.blue2 = (float)n3 / 255.0f;
        this.alpha2 = (float)n4 / 255.0f;
        this.updateColor2();
    }

    public int getColorWithAlpha2(int n) {
        return ColorUtil.toRGBA(new Color(this.red2, this.green2, this.blue2, (float)n / 255.0f));
    }

    public void setGreen2(float f) {
        this.green2 = f;
        this.updateColor2();
    }

    public void setGreen(float f) {
        this.green = f;
        this.updateColor();
    }

    public int getColorAsIntFullAlpha() {
        return ColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }

    public int getColorWithAlpha(int n) {
        return ColorUtil.toRGBA(new Color(this.red, this.green, this.blue, (float)n / 255.0f));
    }

    public void setBlue(float f) {
        this.blue = f;
        this.updateColor();
    }

    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }

    public void updateColor2() {
        this.setColor2(new Color(this.red2, this.green2, this.blue2, this.alpha2));
    }
}

