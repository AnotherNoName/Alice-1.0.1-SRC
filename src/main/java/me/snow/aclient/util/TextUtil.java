/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import java.util.Random;
import java.util.regex.Pattern;

public class TextUtil {
    public static final /* synthetic */ String blank;
    public static final /* synthetic */ String STRIKE;
    private static final /* synthetic */ Pattern STRIP_COLOR_PATTERN;
    public static final /* synthetic */ String DARK_GRAY;
    public static final /* synthetic */ String GOLD;
    public static final /* synthetic */ String DARK_RED;
    public static final /* synthetic */ String DARK_AQUA;
    public static final /* synthetic */ String BLACK;
    public static final /* synthetic */ String RED;
    public static /* synthetic */ String shrug;
    public static final /* synthetic */ String DARK_PURPLE;
    public static final /* synthetic */ String BOLD;
    public static final /* synthetic */ String DARK_GREEN;
    public static final /* synthetic */ String line1;
    public static final /* synthetic */ String AQUA;
    public static final /* synthetic */ String UNDERLINE;
    public static final /* synthetic */ String line3;
    public static final /* synthetic */ String BLUE;
    public static final /* synthetic */ String DARK_BLUE;
    public static final /* synthetic */ String YELLOW;
    public static final /* synthetic */ String SECTIONSIGN;
    public static final /* synthetic */ String RAINBOW;
    public static final /* synthetic */ String line2;
    public static final /* synthetic */ String OBFUSCATED;
    private static final /* synthetic */ Random rand;
    public static final /* synthetic */ String pword;
    public static /* synthetic */ String disability;
    public static final /* synthetic */ String RESET;
    public static final /* synthetic */ String ITALIC;
    public static final /* synthetic */ String GRAY;
    public static final /* synthetic */ String line4;
    public static final /* synthetic */ String GREEN;
    public static final /* synthetic */ String WHITE;
    public static final /* synthetic */ String LIGHT_PURPLE;
    public static final /* synthetic */ String line5;

    public static String cropMaxLengthMessage(String string, int n) {
        String string2 = "";
        if (string.length() >= 256 - n) {
            string2 = string.substring(0, 256 - n);
        }
        return string2;
    }

    public static String coloredString(String string, Color color) {
        String string2 = string;
        switch (color) {
            case AQUA: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7b").append(string2).append("\u00a7r"));
                break;
            }
            case WHITE: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7f").append(string2).append("\u00a7r"));
                break;
            }
            case BLACK: {
                string2 = String.valueOf(new StringBuilder().append("\u00a70").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_BLUE: {
                string2 = String.valueOf(new StringBuilder().append("\u00a71").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_GREEN: {
                string2 = String.valueOf(new StringBuilder().append("\u00a72").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_AQUA: {
                string2 = String.valueOf(new StringBuilder().append("\u00a73").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_RED: {
                string2 = String.valueOf(new StringBuilder().append("\u00a74").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_PURPLE: {
                string2 = String.valueOf(new StringBuilder().append("\u00a75").append(string2).append("\u00a7r"));
                break;
            }
            case GOLD: {
                string2 = String.valueOf(new StringBuilder().append("\u00a76").append(string2).append("\u00a7r"));
                break;
            }
            case DARK_GRAY: {
                string2 = String.valueOf(new StringBuilder().append("\u00a78").append(string2).append("\u00a7r"));
                break;
            }
            case GRAY: {
                string2 = String.valueOf(new StringBuilder().append("\u00a77").append(string2).append("\u00a7r"));
                break;
            }
            case BLUE: {
                string2 = String.valueOf(new StringBuilder().append("\u00a79").append(string2).append("\u00a7r"));
                break;
            }
            case RED: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7c").append(string2).append("\u00a7r"));
                break;
            }
            case GREEN: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7a").append(string2).append("\u00a7r"));
                break;
            }
            case LIGHT_PURPLE: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7d").append(string2).append("\u00a7r"));
                break;
            }
            case YELLOW: {
                string2 = String.valueOf(new StringBuilder().append("\u00a7e").append(string2).append("\u00a7r"));
            }
        }
        return string2;
    }

    static {
        blank = " \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592";
        BLUE = "\u00a79";
        WHITE = "\u00a7f";
        DARK_BLUE = "\u00a71";
        line5 = " \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588";
        RED = "\u00a7c";
        GRAY = "\u00a77";
        DARK_AQUA = "\u00a73";
        LIGHT_PURPLE = "\u00a7d";
        pword = "  \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\n \u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\n \u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\n \u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\n \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\u2592\u2588\n \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\n \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592";
        line3 = " \u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588";
        BOLD = "\u00a7l";
        DARK_RED = "\u00a74";
        YELLOW = "\u00a7e";
        GREEN = "\u00a7a";
        RESET = "\u00a7r";
        OBFUSCATED = "\u00a7k";
        line4 = " \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\u2592\u2588";
        line2 = " \u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592";
        STRIKE = "\u00a7m";
        BLACK = "\u00a70";
        RAINBOW = "\u00a7+";
        DARK_GREEN = "\u00a72";
        AQUA = "\u00a7b";
        SECTIONSIGN = "\u00a7";
        DARK_GRAY = "\u00a78";
        ITALIC = "\u00a7o";
        line1 = " \u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588";
        GOLD = "\u00a76";
        DARK_PURPLE = "\u00a75";
        UNDERLINE = "\u00a7n";
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
        rand = new Random();
        shrug = "\u00af\\_(\u30c4)_/\u00af";
        disability = "\u267f";
    }

    public static String stripColor(String string) {
        if (string == null) {
            return null;
        }
        return STRIP_COLOR_PATTERN.matcher(string).replaceAll("");
    }

    public static enum Color {
        NONE,
        WHITE,
        BLACK,
        DARK_BLUE,
        DARK_GREEN,
        DARK_AQUA,
        DARK_RED,
        DARK_PURPLE,
        GOLD,
        GRAY,
        DARK_GRAY,
        BLUE,
        GREEN,
        AQUA,
        RED,
        LIGHT_PURPLE,
        YELLOW;

    }
}

