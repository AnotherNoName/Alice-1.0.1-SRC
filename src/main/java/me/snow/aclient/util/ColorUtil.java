/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util;

import java.awt.Color;
import java.util.ArrayList;
import me.snow.aclient.util.Colour;
import org.lwjgl.opengl.GL11;

public class ColorUtil {
    public static int changeAlpha(int n, int n2) {
        return n2 << 24 | (n &= 0xFFFFFF);
    }

    public String getColorNameFromHex(int n) {
        int n2 = (n & 0xFF0000) >> 16;
        int n3 = (n & 0xFF00) >> 8;
        int n4 = n & 0xFF;
        return this.getColorNameFromRgb(n2, n3, n4);
    }

    public static int toARGB(int n, int n2, int n3, int n4) {
        return new Color(n, n2, n3, n4).getRGB();
    }

    public static Color getColour() {
        return Colour.fromHSB((float)(System.currentTimeMillis() % 11520L) / 11520.0f, 1.0f, 1.0f);
    }

    public static int shadeColour(int n, int n2) {
        int n3 = ((n & 0xFF0000) >> 16) * (100 + n2) / 100;
        int n4 = ((n & 0xFF00) >> 8) * (100 + n2) / 100;
        int n5 = (n & 0xFF) * (100 + n2) / 100;
        return new Color(n3, n4, n5).hashCode();
    }

    public static Color alpha(Color color, int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public String getColorNameFromRgb(int n, int n2, int n3) {
        ArrayList<ColorName> arrayList = this.initColorList();
        ColorName colorName = null;
        int n4 = Integer.MAX_VALUE;
        for (ColorName colorName2 : arrayList) {
            int n5 = colorName2.computeMSE(n, n2, n3);
            if (n5 >= n4) continue;
            n4 = n5;
            colorName = colorName2;
        }
        if (colorName != null) {
            return colorName.getName();
        }
        return "No matched color name.";
    }

    public static int toRGBA(Color color) {
        return ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public String getColorNameFromColor(Color color) {
        return this.getColorNameFromRgb(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static int toRGBA(int n, int n2, int n3, int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }

    private ArrayList<ColorName> initColorList() {
        ArrayList<ColorName> arrayList = new ArrayList<ColorName>();
        arrayList.add(new ColorName("AliceBlue", 240, 248, 255));
        arrayList.add(new ColorName("AntiqueWhite", 250, 235, 215));
        arrayList.add(new ColorName("Aqua", 0, 255, 255));
        arrayList.add(new ColorName("Aquamarine", 127, 255, 212));
        arrayList.add(new ColorName("Azure", 240, 255, 255));
        arrayList.add(new ColorName("Beige", 245, 245, 220));
        arrayList.add(new ColorName("Bisque", 255, 228, 196));
        arrayList.add(new ColorName("Black", 0, 0, 0));
        arrayList.add(new ColorName("BlanchedAlmond", 255, 235, 205));
        arrayList.add(new ColorName("Blue", 0, 0, 255));
        arrayList.add(new ColorName("BlueViolet", 138, 43, 226));
        arrayList.add(new ColorName("Brown", 165, 42, 42));
        arrayList.add(new ColorName("BurlyWood", 222, 184, 135));
        arrayList.add(new ColorName("CadetBlue", 95, 158, 160));
        arrayList.add(new ColorName("Chartreuse", 127, 255, 0));
        arrayList.add(new ColorName("Chocolate", 210, 105, 30));
        arrayList.add(new ColorName("Coral", 255, 127, 80));
        arrayList.add(new ColorName("CornflowerBlue", 100, 149, 237));
        arrayList.add(new ColorName("Cornsilk", 255, 248, 220));
        arrayList.add(new ColorName("Crimson", 220, 20, 60));
        arrayList.add(new ColorName("Cyan", 0, 255, 255));
        arrayList.add(new ColorName("DarkBlue", 0, 0, 139));
        arrayList.add(new ColorName("DarkCyan", 0, 139, 139));
        arrayList.add(new ColorName("DarkGoldenRod", 184, 134, 11));
        arrayList.add(new ColorName("DarkGray", 169, 169, 169));
        arrayList.add(new ColorName("DarkGreen", 0, 100, 0));
        arrayList.add(new ColorName("DarkKhaki", 189, 183, 107));
        arrayList.add(new ColorName("DarkMagenta", 139, 0, 139));
        arrayList.add(new ColorName("DarkOliveGreen", 85, 107, 47));
        arrayList.add(new ColorName("DarkOrange", 255, 140, 0));
        arrayList.add(new ColorName("DarkOrchid", 153, 50, 204));
        arrayList.add(new ColorName("DarkRed", 139, 0, 0));
        arrayList.add(new ColorName("DarkSalmon", 233, 150, 122));
        arrayList.add(new ColorName("DarkSeaGreen", 143, 188, 143));
        arrayList.add(new ColorName("DarkSlateBlue", 72, 61, 139));
        arrayList.add(new ColorName("DarkSlateGray", 47, 79, 79));
        arrayList.add(new ColorName("DarkTurquoise", 0, 206, 209));
        arrayList.add(new ColorName("DarkViolet", 148, 0, 211));
        arrayList.add(new ColorName("DeepPink", 255, 20, 147));
        arrayList.add(new ColorName("DeepSkyBlue", 0, 191, 255));
        arrayList.add(new ColorName("DimGray", 105, 105, 105));
        arrayList.add(new ColorName("DodgerBlue", 30, 144, 255));
        arrayList.add(new ColorName("FireBrick", 178, 34, 34));
        arrayList.add(new ColorName("FloralWhite", 255, 250, 240));
        arrayList.add(new ColorName("ForestGreen", 34, 139, 34));
        arrayList.add(new ColorName("Fuchsia", 255, 0, 255));
        arrayList.add(new ColorName("Gainsboro", 220, 220, 220));
        arrayList.add(new ColorName("GhostWhite", 248, 248, 255));
        arrayList.add(new ColorName("Gold", 255, 215, 0));
        arrayList.add(new ColorName("GoldenRod", 218, 165, 32));
        arrayList.add(new ColorName("Gray", 128, 128, 128));
        arrayList.add(new ColorName("Green", 0, 128, 0));
        arrayList.add(new ColorName("GreenYellow", 173, 255, 47));
        arrayList.add(new ColorName("HoneyDew", 240, 255, 240));
        arrayList.add(new ColorName("HotPink", 255, 105, 180));
        arrayList.add(new ColorName("IndianRed", 205, 92, 92));
        arrayList.add(new ColorName("Indigo", 75, 0, 130));
        arrayList.add(new ColorName("Ivory", 255, 255, 240));
        arrayList.add(new ColorName("Khaki", 240, 230, 140));
        arrayList.add(new ColorName("Lavender", 230, 230, 250));
        arrayList.add(new ColorName("LavenderBlush", 255, 240, 245));
        arrayList.add(new ColorName("LawnGreen", 124, 252, 0));
        arrayList.add(new ColorName("LemonChiffon", 255, 250, 205));
        arrayList.add(new ColorName("LightBlue", 173, 216, 230));
        arrayList.add(new ColorName("LightCoral", 240, 128, 128));
        arrayList.add(new ColorName("LightCyan", 224, 255, 255));
        arrayList.add(new ColorName("LightGoldenRodYellow", 250, 250, 210));
        arrayList.add(new ColorName("LightGray", 211, 211, 211));
        arrayList.add(new ColorName("LightGreen", 144, 238, 144));
        arrayList.add(new ColorName("LightPink", 255, 182, 193));
        arrayList.add(new ColorName("LightSalmon", 255, 160, 122));
        arrayList.add(new ColorName("LightSeaGreen", 32, 178, 170));
        arrayList.add(new ColorName("LightSkyBlue", 135, 206, 250));
        arrayList.add(new ColorName("LightSlateGray", 119, 136, 153));
        arrayList.add(new ColorName("LightSteelBlue", 176, 196, 222));
        arrayList.add(new ColorName("LightYellow", 255, 255, 224));
        arrayList.add(new ColorName("Lime", 0, 255, 0));
        arrayList.add(new ColorName("LimeGreen", 50, 205, 50));
        arrayList.add(new ColorName("Linen", 250, 240, 230));
        arrayList.add(new ColorName("Magenta", 255, 0, 255));
        arrayList.add(new ColorName("Maroon", 128, 0, 0));
        arrayList.add(new ColorName("MediumAquaMarine", 102, 205, 170));
        arrayList.add(new ColorName("MediumBlue", 0, 0, 205));
        arrayList.add(new ColorName("MediumOrchid", 186, 85, 211));
        arrayList.add(new ColorName("MediumPurple", 147, 112, 219));
        arrayList.add(new ColorName("MediumSeaGreen", 60, 179, 113));
        arrayList.add(new ColorName("MediumSlateBlue", 123, 104, 238));
        arrayList.add(new ColorName("MediumSpringGreen", 0, 250, 154));
        arrayList.add(new ColorName("MediumTurquoise", 72, 209, 204));
        arrayList.add(new ColorName("MediumVioletRed", 199, 21, 133));
        arrayList.add(new ColorName("MidnightBlue", 25, 25, 112));
        arrayList.add(new ColorName("MintCream", 245, 255, 250));
        arrayList.add(new ColorName("MistyRose", 255, 228, 225));
        arrayList.add(new ColorName("Moccasin", 255, 228, 181));
        arrayList.add(new ColorName("NavajoWhite", 255, 222, 173));
        arrayList.add(new ColorName("Navy", 0, 0, 128));
        arrayList.add(new ColorName("OldLace", 253, 245, 230));
        arrayList.add(new ColorName("Olive", 128, 128, 0));
        arrayList.add(new ColorName("OliveDrab", 107, 142, 35));
        arrayList.add(new ColorName("Orange", 255, 165, 0));
        arrayList.add(new ColorName("OrangeRed", 255, 69, 0));
        arrayList.add(new ColorName("Orchid", 218, 112, 214));
        arrayList.add(new ColorName("PaleGoldenRod", 238, 232, 170));
        arrayList.add(new ColorName("PaleGreen", 152, 251, 152));
        arrayList.add(new ColorName("PaleTurquoise", 175, 238, 238));
        arrayList.add(new ColorName("PaleVioletRed", 219, 112, 147));
        arrayList.add(new ColorName("PapayaWhip", 255, 239, 213));
        arrayList.add(new ColorName("PeachPuff", 255, 218, 185));
        arrayList.add(new ColorName("Peru", 205, 133, 63));
        arrayList.add(new ColorName("Pink", 255, 192, 203));
        arrayList.add(new ColorName("Plum", 221, 160, 221));
        arrayList.add(new ColorName("PowderBlue", 176, 224, 230));
        arrayList.add(new ColorName("Purple", 128, 0, 128));
        arrayList.add(new ColorName("Red", 255, 0, 0));
        arrayList.add(new ColorName("RosyBrown", 188, 143, 143));
        arrayList.add(new ColorName("RoyalBlue", 65, 105, 225));
        arrayList.add(new ColorName("SaddleBrown", 139, 69, 19));
        arrayList.add(new ColorName("Salmon", 250, 128, 114));
        arrayList.add(new ColorName("SandyBrown", 244, 164, 96));
        arrayList.add(new ColorName("SeaGreen", 46, 139, 87));
        arrayList.add(new ColorName("SeaShell", 255, 245, 238));
        arrayList.add(new ColorName("Sienna", 160, 82, 45));
        arrayList.add(new ColorName("Silver", 192, 192, 192));
        arrayList.add(new ColorName("SkyBlue", 135, 206, 235));
        arrayList.add(new ColorName("SlateBlue", 106, 90, 205));
        arrayList.add(new ColorName("SlateGray", 112, 128, 144));
        arrayList.add(new ColorName("Snow", 255, 250, 250));
        arrayList.add(new ColorName("SpringGreen", 0, 255, 127));
        arrayList.add(new ColorName("SteelBlue", 70, 130, 180));
        arrayList.add(new ColorName("Tan", 210, 180, 140));
        arrayList.add(new ColorName("Teal", 0, 128, 128));
        arrayList.add(new ColorName("Thistle", 216, 191, 216));
        arrayList.add(new ColorName("Tomato", 255, 99, 71));
        arrayList.add(new ColorName("Turquoise", 64, 224, 208));
        arrayList.add(new ColorName("Violet", 238, 130, 238));
        arrayList.add(new ColorName("Wheat", 245, 222, 179));
        arrayList.add(new ColorName("White", 255, 255, 255));
        arrayList.add(new ColorName("WhiteSmoke", 245, 245, 245));
        arrayList.add(new ColorName("Yellow", 255, 255, 0));
        arrayList.add(new ColorName("YellowGreen", 154, 205, 50));
        return arrayList;
    }

    public static int toRGBA(float[] arrf) {
        if (arrf.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return ColorUtil.toRGBA(arrf[0], arrf[1], arrf[2], arrf[3]);
    }

    public static int toRGBA(double d, double d2, double d3, double d4) {
        return ColorUtil.toRGBA((float)d, (float)d2, (float)d3, (float)d4);
    }

    public static int toRGBA(int n, int n2, int n3) {
        return ColorUtil.toRGBA(n, n2, n3, 255);
    }

    public static int toRGBA(float f, float f2, float f3, float f4) {
        return ColorUtil.toRGBA((int)(f * 255.0f), (int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f));
    }

    public static int[] toRGBAArray(int n) {
        return new int[]{n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n >> 24 & 0xFF};
    }

    public static int toRGBA(double[] arrd) {
        if (arrd.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return ColorUtil.toRGBA((float)arrd[0], (float)arrd[1], (float)arrd[2], (float)arrd[3]);
    }

    public int colorToHex(Color color) {
        return Integer.decode(String.valueOf(new StringBuilder().append("0x").append(Integer.toHexString(color.getRGB()).substring(2))));
    }

    public static int GenRainbow() {
        float[] arrf = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0f};
        int n = Color.HSBtoRGB(arrf[0], 1.0f, 1.0f);
        int n2 = n >> 16 & 0xFF;
        int n3 = n >> 8 & 0xFF;
        int n4 = n & 0xFF;
        int n5 = ColorUtil.toRGBA(n2, n3, n4, 255);
        return n5;
    }

    public static Color getFurtherColour(int n) {
        return Colour.fromHSB((float)((System.currentTimeMillis() + (long)n) % 11520L) / 11520.0f, 1.0f, 1.0f);
    }

    public static Color newAlpha(Color color, int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public static float[] intToRGB(int n) {
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        return new float[]{f2, f3, f4, f};
    }

    public static Color interpolate(float f, Color color, Color color2) {
        float f2 = (float)color.getRed() / 255.0f;
        float f3 = (float)color.getGreen() / 255.0f;
        float f4 = (float)color.getBlue() / 255.0f;
        float f5 = (float)color.getAlpha() / 255.0f;
        float f6 = (float)color2.getRed() / 255.0f;
        float f7 = (float)color2.getGreen() / 255.0f;
        float f8 = (float)color2.getBlue() / 255.0f;
        float f9 = (float)color2.getAlpha() / 255.0f;
        float f10 = f2 * f + f6 * (1.0f - f);
        float f11 = f3 * f + f7 * (1.0f - f);
        float f12 = f4 * f + f8 * (1.0f - f);
        float f13 = f5 * f + f9 * (1.0f - f);
        return new Color(f10, f11, f12, f13);
    }

    public static int staticRainbow(float f, Color color) {
        double d = (double)System.currentTimeMillis() % 1750.0 / 850.0;
        float[] arrf = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), arrf);
        float f2 = (float)((double)arrf[2] * Math.abs(((double)f + d) % 1.0 - (double)0.55f) + (double)0.45f);
        return Color.HSBtoRGB(arrf[0], arrf[1], f2);
    }

    public static int HSBtoRGB(float f, float f2, float f3) {
        return Color.HSBtoRGB(f, f2, f3);
    }

    public static class ColorName {
        public /* synthetic */ int r;
        public /* synthetic */ int b;
        public /* synthetic */ String name;
        public /* synthetic */ int g;

        public int getR() {
            return this.r;
        }

        public int getB() {
            return this.b;
        }

        public int getG() {
            return this.g;
        }

        public ColorName(String string, int n, int n2, int n3) {
            this.r = n;
            this.g = n2;
            this.b = n3;
            this.name = string;
        }

        public int computeMSE(int n, int n2, int n3) {
            return ((n - this.r) * (n - this.r) + (n2 - this.g) * (n2 - this.g) + (n3 - this.b) * (n3 - this.b)) / 3;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class HueCycler {
        public /* synthetic */ int index;
        public /* synthetic */ int[] cycles;

        public void set() {
            int n = this.cycles[this.index];
            float f = (float)(n >> 16 & 0xFF) / 255.0f;
            float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
            float f3 = (float)(n & 0xFF) / 255.0f;
            GL11.glColor3f((float)f, (float)f2, (float)f3);
        }

        public int next() {
            int n = this.cycles[this.index];
            ++this.index;
            if (this.index >= this.cycles.length) {
                this.index = 0;
            }
            return n;
        }

        public void reset(int n) {
            this.index = n;
        }

        public void setNext() {
            int n = this.next();
        }

        public HueCycler(int n) {
            if (n <= 0) {
                throw new IllegalArgumentException("cycles <= 0");
            }
            this.cycles = new int[n];
            double d = 0.0;
            double d2 = 1.0 / (double)n;
            for (int i = 0; i < n; ++i) {
                this.cycles[i] = Color.HSBtoRGB((float)d, 1.0f, 1.0f);
                d += d2;
            }
        }

        public void setNext(float f) {
            int n = this.next();
            float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(n & 0xFF) / 255.0f;
            GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        }

        public int current() {
            return this.cycles[this.index];
        }

        public void reset() {
            this.index = 0;
        }
    }

    public static class Colors {
        public static final /* synthetic */ int GRAY;
        public static final /* synthetic */ int GREEN;
        public static final /* synthetic */ int MAGENTA;
        public static final /* synthetic */ int DARK_RED;
        public static final /* synthetic */ int YELLOW;
        public static final /* synthetic */ int AQUA;
        public static final /* synthetic */ int LIGHT_GRAY;
        public static final /* synthetic */ int BLACK;
        public static final /* synthetic */ int RED;
        public static final /* synthetic */ int RAINBOW;
        public static final /* synthetic */ int PURPLE;
        public static final /* synthetic */ int ORANGE;
        public static final /* synthetic */ int BLUE;
        public static final /* synthetic */ int WHITE;

        static {
            RAINBOW = Integer.MIN_VALUE;
            WHITE = ColorUtil.toRGBA(255, 255, 255, 255);
            BLACK = ColorUtil.toRGBA(0, 0, 0, 255);
            RED = ColorUtil.toRGBA(255, 0, 0, 255);
            GREEN = ColorUtil.toRGBA(0, 255, 0, 255);
            BLUE = ColorUtil.toRGBA(0, 0, 255, 255);
            ORANGE = ColorUtil.toRGBA(255, 132, 32, 255);
            PURPLE = ColorUtil.toRGBA(137, 50, 184, 255);
            GRAY = ColorUtil.toRGBA(80, 80, 80, 255);
            DARK_RED = ColorUtil.toRGBA(64, 0, 0, 255);
            YELLOW = ColorUtil.toRGBA(255, 255, 0, 255);
            AQUA = ColorUtil.toRGBA(0, 255, 0, 255);
            LIGHT_GRAY = ColorUtil.toRGBA(160, 160, 160, 255);
            MAGENTA = ColorUtil.toRGBA(220, 64, 220, 255);
        }
    }
}

