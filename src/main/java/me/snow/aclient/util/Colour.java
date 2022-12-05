//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 */
package me.snow.aclient.util;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;

public class Colour
extends Color {
    public Colour(int n) {
        super(n);
    }

    public Colour(Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void glColor() {
        GlStateManager.color((float)((float)this.getRed() / 255.0f), (float)((float)this.getGreen() / 255.0f), (float)((float)this.getBlue() / 255.0f), (float)((float)this.getAlpha() / 255.0f));
    }

    public static Colour fromHSB(float f, float f2, float f3) {
        return new Colour(Color.getHSBColor(f, f2, f3));
    }

    public Colour(int n, int n2, int n3, int n4) {
        super(n, n2, n3, n4);
    }

    public float getSaturation() {
        return Colour.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[1];
    }

    public Colour(int n, boolean bl) {
        super(n, bl);
    }

    public float getBrightness() {
        return Colour.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[2];
    }

    public Colour(int n, int n2, int n3) {
        super(n, n2, n3);
    }

    public Colour(Colour colour, int n) {
        super(colour.getRed(), colour.getGreen(), colour.getBlue(), n);
    }

    public float getHue() {
        return Colour.RGBtoHSB(this.getRed(), this.getGreen(), this.getBlue(), null)[0];
    }
}

