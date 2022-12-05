/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.skid.oyvey;

import java.awt.Color;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;

public class ColorUtil2 {
    public static Color invert(Color color) {
        int n;
        int n2;
        int n3 = color.getAlpha();
        int n4 = 255 - color.getRed();
        if (n4 + (n2 = 255 - color.getGreen()) + (n = 255 - color.getBlue()) > 740 || n4 + n2 + n < 20) {
            return new Color(255, 255, 40, n3);
        }
        return new Color(n4, n2, n, n3);
    }

    public static int toRGBA(Color color) {
        return ColorUtil2.toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static Color rainbow(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 20.0);
        return Color.getHSBColor((float)((d %= 360.0) / 360.0), Global.getInstance().rainbowSaturation.getValue().floatValue() / 255.0f, Global.getInstance().rainbowBrightness.getValue().floatValue() / 255.0f);
    }

    public static int toRGBA(float f, float f2, float f3, float f4) {
        return ColorUtil2.toRGBA((int)(f * 255.0f), (int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f));
    }

    public static int toRGBA(float[] arrf) {
        if (arrf.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return ColorUtil2.toRGBA(arrf[0], arrf[1], arrf[2], arrf[3]);
    }

    public static int toRGBA(double[] arrd) {
        if (arrd.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return ColorUtil2.toRGBA((float)arrd[0], (float)arrd[1], (float)arrd[2], (float)arrd[3]);
    }

    public static int toRGBA(int n, int n2, int n3, int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }

    public static int toRGBA(int n, int n2, int n3) {
        return ColorUtil2.toRGBA(n, n2, n3, 255);
    }

    public static int toARGB(int n, int n2, int n3, int n4) {
        return new Color(n, n2, n3, n4).getRGB();
    }

    public ColorUtil2(int n, int n2, int n3, int n4) {
    }

    public static Color rainbowTEST22(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 20.0);
        return Color.getHSBColor((float)((d %= 360.0) / 360.0), AutoCrystal.getInstance().rainbowSaturationAA22.getValue().floatValue() / 255.0f, AutoCrystal.getInstance().rainbowBrightnessAA22.getValue().floatValue() / 255.0f);
    }

    public static Color rainbowNew(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 20.0);
        return Color.getHSBColor((float)((d %= 360.0) / 360.0), AliceAura.getInstance().rainbowSaturationAA22.getValue().floatValue() / 255.0f, AliceAura.getInstance().rainbowBrightnessAA22.getValue().floatValue() / 255.0f);
    }
}

