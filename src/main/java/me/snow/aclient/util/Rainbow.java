/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import java.awt.Color;

public class Rainbow {
    public static Color getRainbowColor(float f, float f2, float f3, long l) {
        return new Color(Rainbow.getRainbow(f, f2, f3, l));
    }

    public static int getRainbow(float f, float f2, float f3) {
        float f4 = (float)(System.currentTimeMillis() % 11520L) / 11520.0f * f;
        return Color.HSBtoRGB(f4, f2, f3);
    }

    public static int getRainbow(float f, float f2, float f3, long l) {
        float f4 = (float)((System.currentTimeMillis() + l) % 11520L) / 11520.0f * f;
        return Color.HSBtoRGB(f4, f2, f3);
    }

    public static Color getRainbowColor(float f, float f2, float f3) {
        return new Color(Rainbow.getRainbow(f, f2, f3));
    }
}

