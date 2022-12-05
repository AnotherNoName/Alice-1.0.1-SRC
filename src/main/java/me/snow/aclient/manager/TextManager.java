//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package me.snow.aclient.manager;

import java.awt.Color;
import java.awt.Font;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.font.CustomFont;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.util.math.MathHelper;

public class TextManager
extends Feature {
    private /* synthetic */ CustomFont customFont;
    private /* synthetic */ boolean idling;
    public /* synthetic */ int scaledWidth;
    public /* synthetic */ int scaleFactor;
    public /* synthetic */ int scaledHeight;
    private final /* synthetic */ Timer idleTimer;

    public void drawStringWithShadow(String string, float f, float f2, int n) {
        this.drawString(string, f, f2, n, true);
    }

    public float drawString(String string, float f, float f2, int n, boolean bl) {
        if (AliceMain.moduleManager.isModuleEnabled(me.snow.aclient.module.modules.client.CustomFont.class)) {
            if (bl) {
                return this.customFont.drawStringWithShadow(string, f, f2, n);
            }
            return this.customFont.drawString(string, f, f2, n);
        }
        return TextManager.mc.fontRendererObj.drawString(string, f, f2, n, bl);
    }

    public int getStringWidth(String string) {
        if (AliceMain.moduleManager.isModuleEnabled(me.snow.aclient.module.modules.client.CustomFont.class)) {
            return this.customFont.getStringWidth(string);
        }
        return TextManager.mc.fontRendererObj.getStringWidth(string);
    }

    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        boolean bl = mc.isUnicode();
        int n = TextManager.mc.gameSettings.guiScale;
        if (n == 0) {
            n = 1000;
        }
        while (this.scaleFactor < n && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (bl && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        double d = (double)this.scaledWidth / (double)this.scaleFactor;
        double d2 = (double)this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil((double)d);
        this.scaledHeight = MathHelper.ceil((double)d2);
    }

    public Font getCurrentFont() {
        return this.customFont.getFont();
    }

    public void drawRainbowString(String string, float f, float f2, int n, float f3, boolean bl) {
        Color color = new Color(n);
        float f4 = 1.0f / f3;
        String[] arrstring = string.split("\u00a7.");
        float f5 = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0];
        float f6 = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[1];
        float f7 = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[2];
        int n2 = 0;
        boolean bl2 = true;
        boolean bl3 = false;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            char c2 = string.charAt(MathUtil.clamp(i + 1, 0, string.length() - 1));
            boolean bl4 = String.valueOf(new StringBuilder().append(String.valueOf(c)).append(c2)).equals("\u00a7r");
            if (bl4) {
                bl2 = false;
            } else if (String.valueOf(new StringBuilder().append(String.valueOf(c)).append(c2)).equals("\u00a7+")) {
                bl2 = true;
            }
            if (bl3) {
                bl3 = false;
                continue;
            }
            if (bl4) {
                String string2 = string.substring(i);
                this.drawString(string2, f + (float)n2, f2, Color.WHITE.getRGB(), bl);
                break;
            }
            this.drawString(String.valueOf(c).equals("\u00a7") ? "" : String.valueOf(c), f + (float)n2, f2, bl2 ? color.getRGB() : Color.WHITE.getRGB(), bl);
            if (String.valueOf(c).equals("\u00a7")) {
                bl3 = true;
            }
            n2 += this.getStringWidth(String.valueOf(c));
            if (String.valueOf(c).equals(" ")) continue;
            color = new Color(Color.HSBtoRGB(f5, f6, f7));
            f5 += f4;
        }
    }

    public int getFontHeight() {
        if (AliceMain.moduleManager.isModuleEnabled(me.snow.aclient.module.modules.client.CustomFont.class)) {
            String string = "A";
            return this.customFont.getStringHeight(string);
        }
        return TextManager.mc.fontRendererObj.FONT_HEIGHT;
    }

    public TextManager() {
        this.idleTimer = new Timer();
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
        this.updateResolution();
    }

    public void init(boolean bl) {
        me.snow.aclient.module.modules.client.CustomFont customFont = AliceMain.moduleManager.getModuleByClass(me.snow.aclient.module.modules.client.CustomFont.class);
        try {
            this.setFontRenderer(new Font(customFont.fontName.getValue(), (int)customFont.fontStyle.getValue(), customFont.fontSize.getValue()), customFont.antiAlias.getValue(), customFont.fractionalMetrics.getValue());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }

    public void setFontRenderer(Font font, boolean bl, boolean bl2) {
        this.customFont = new CustomFont(font, bl, bl2);
    }
}

