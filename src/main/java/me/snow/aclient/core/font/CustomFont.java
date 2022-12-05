//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.core.font;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.core.font.CFont;
import me.snow.aclient.module.modules.client.Media;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;

public class CustomFont
extends CFont {
    protected /* synthetic */ CFont.CharData[] boldItalicChars;
    protected /* synthetic */ CFont.CharData[] italicChars;
    private final /* synthetic */ int[] colorCode;
    protected /* synthetic */ CFont.CharData[] boldChars;
    protected /* synthetic */ DynamicTexture texItalicBold;
    protected /* synthetic */ DynamicTexture texBold;
    protected /* synthetic */ DynamicTexture texItalic;

    public List<String> formatString(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String string2 = "";
        char c = '\uffff';
        char[] arrc = string.toCharArray();
        for (int i = 0; i < arrc.length; ++i) {
            StringBuilder stringBuilder;
            char c2 = arrc[i];
            if (c2 == '\u00a7' && i < arrc.length - 1) {
                c = arrc[i + 1];
            }
            if ((double)this.getStringWidth(String.valueOf((stringBuilder = new StringBuilder()).append(string2).append(c2))) < d) {
                string2 = String.valueOf(new StringBuilder().append(string2).append(c2));
                continue;
            }
            arrayList.add(string2);
            string2 = String.valueOf(new StringBuilder().append("\u00a7").append(c).append(c2));
        }
        if (string2.length() > 0) {
            arrayList.add(string2);
        }
        return arrayList;
    }

    public float drawString(String string, double d, double d2, int n, boolean bl) {
        String string2 = Media.getInstance().isOn() && Media.getInstance().changeOwn.getValue() != false ? string.replace(Media.getPlayerName(), Media.getInstance().ownName.getValue()) : string;
        double d3 = d;
        double d4 = d2;
        if (me.snow.aclient.module.modules.client.CustomFont.getInstance().isOn() && !me.snow.aclient.module.modules.client.CustomFont.getInstance().shadow.getValue().booleanValue() && bl) {
            d3 -= 0.5;
            d4 -= 0.5;
        }
        d3 -= 1.0;
        if (string2 == null) {
            return 0.0f;
        }
        if (n == 0x20FFFFFF) {
            n = 0xFFFFFF;
        }
        if ((n & 0xFC000000) == 0) {
            n |= 0xFF000000;
        }
        if (bl) {
            n = (n & 0xFCFCFC) >> 2 | n & 0xFF000000;
        }
        CFont.CharData[] arrcharData = this.charData;
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = true;
        d3 *= 2.0;
        d4 = (d4 - 3.0) * 2.0;
        if (bl6) {
            GL11.glPushMatrix();
            GlStateManager.scale((double)0.5, (double)0.5, (double)0.5);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((int)770, (int)771);
            GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
            int n2 = string2.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture((int)this.tex.getGlTextureId());
            GL11.glBindTexture((int)3553, (int)this.tex.getGlTextureId());
            for (int i = 0; i < n2; ++i) {
                char c = string2.charAt(i);
                if (c == '\u00a7' && i < n2) {
                    int n3 = 21;
                    try {
                        n3 = "0123456789abcdefklmnor".indexOf(string2.charAt(i + 1));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    if (n3 < 16) {
                        bl2 = false;
                        bl3 = false;
                        bl5 = false;
                        bl4 = false;
                        GlStateManager.bindTexture((int)this.tex.getGlTextureId());
                        arrcharData = this.charData;
                        if (n3 < 0 || n3 > 15) {
                            n3 = 15;
                        }
                        if (bl) {
                            n3 += 16;
                        }
                        int n4 = this.colorCode[n3];
                        GlStateManager.color((float)((float)(n4 >> 16 & 0xFF) / 255.0f), (float)((float)(n4 >> 8 & 0xFF) / 255.0f), (float)((float)(n4 & 0xFF) / 255.0f), (float)f);
                    } else if (n3 == 17) {
                        bl2 = true;
                        if (bl3) {
                            GlStateManager.bindTexture((int)this.texItalicBold.getGlTextureId());
                            arrcharData = this.boldItalicChars;
                        } else {
                            GlStateManager.bindTexture((int)this.texBold.getGlTextureId());
                            arrcharData = this.boldChars;
                        }
                    } else if (n3 == 18) {
                        bl4 = true;
                    } else if (n3 == 19) {
                        bl5 = true;
                    } else if (n3 == 20) {
                        bl3 = true;
                        if (bl2) {
                            GlStateManager.bindTexture((int)this.texItalicBold.getGlTextureId());
                            arrcharData = this.boldItalicChars;
                        } else {
                            GlStateManager.bindTexture((int)this.texItalic.getGlTextureId());
                            arrcharData = this.italicChars;
                        }
                    } else if (n3 == 21) {
                        bl2 = false;
                        bl3 = false;
                        bl5 = false;
                        bl4 = false;
                        GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
                        GlStateManager.bindTexture((int)this.tex.getGlTextureId());
                        arrcharData = this.charData;
                    }
                    ++i;
                    continue;
                }
                if (c >= arrcharData.length || c < '\u0000') continue;
                GL11.glBegin((int)4);
                this.drawChar(arrcharData, c, (float)d3, (float)d4);
                GL11.glEnd();
                if (bl4) {
                    this.drawLine(d3, d4 + (double)(arrcharData[c].height / 2), d3 + (double)arrcharData[c].width - 8.0, d4 + (double)(arrcharData[c].height / 2), 1.0f);
                }
                if (bl5) {
                    this.drawLine(d3, d4 + (double)arrcharData[c].height - 2.0, d3 + (double)arrcharData[c].width - 8.0, d4 + (double)arrcharData[c].height - 2.0, 1.0f);
                }
                d3 += (double)(arrcharData[c].width - 8 + this.charOffset);
            }
            GL11.glHint((int)3155, (int)4352);
            GL11.glPopMatrix();
        }
        return (float)d3 / 2.0f;
    }

    private void drawLine(double d, double d2, double d3, double d4, float f) {
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    @Override
    public int getStringWidth(String string) {
        if (string == null) {
            return 0;
        }
        int n = 0;
        CFont.CharData[] arrcharData = this.charData;
        boolean bl = false;
        boolean bl2 = false;
        int n2 = string.length();
        for (int i = 0; i < n2; ++i) {
            char c = string.charAt(i);
            if (c == '\u00a7' && i < n2) {
                int n3 = "0123456789abcdefklmnor".indexOf(c);
                if (n3 < 16) {
                    bl = false;
                    bl2 = false;
                } else if (n3 == 17) {
                    bl = true;
                    arrcharData = bl2 ? this.boldItalicChars : this.boldChars;
                } else if (n3 == 20) {
                    bl2 = true;
                    arrcharData = bl ? this.boldItalicChars : this.italicChars;
                } else if (n3 == 21) {
                    bl = false;
                    bl2 = false;
                    arrcharData = this.charData;
                }
                ++i;
                continue;
            }
            if (c >= arrcharData.length || c < '\u0000') continue;
            n += arrcharData[c].width - 8 + this.charOffset;
        }
        return n / 2;
    }

    public float drawStringWithShadow(String string, double d, double d2, int n) {
        float f = this.drawString(string, d + 1.0, d2 + 1.0, n, true);
        return Math.max(f, this.drawString(string, d, d2, n, false));
    }

    public float drawString(String string, float f, float f2, int n) {
        return this.drawString(string, f, f2, n, false);
    }

    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }

    @Override
    public void setAntiAlias(boolean bl) {
        super.setAntiAlias(bl);
        this.setupBoldItalicIDs();
    }

    public float drawCenteredString(String string, float f, float f2, int n) {
        return this.drawString(string, f - (float)(this.getStringWidth(string) / 2), f2, n);
    }

    public CustomFont(Font font, boolean bl, boolean bl2) {
        super(font, bl, bl2);
        this.colorCode = new int[32];
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }

    private void setupMinecraftColorcodes() {
        for (int i = 0; i < 32; ++i) {
            int n = (i >> 3 & 1) * 85;
            int n2 = (i >> 2 & 1) * 170 + n;
            int n3 = (i >> 1 & 1) * 170 + n;
            int n4 = (i >> 0 & 1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
        }
    }

    public List<String> wrapWords(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if ((double)this.getStringWidth(string) > d) {
            String[] arrstring = string.split(" ");
            String string2 = "";
            char c = '\uffff';
            for (String string3 : arrstring) {
                for (int i = 0; i < string3.toCharArray().length; ++i) {
                    char c2 = string3.toCharArray()[i];
                    if (c2 != '\u00a7' || i >= string3.toCharArray().length - 1) continue;
                    c = string3.toCharArray()[i + 1];
                }
                StringBuilder stringBuilder = new StringBuilder();
                if ((double)this.getStringWidth(String.valueOf(stringBuilder.append(string2).append(string3).append(" "))) < d) {
                    string2 = String.valueOf(new StringBuilder().append(string2).append(string3).append(" "));
                    continue;
                }
                arrayList.add(string2);
                string2 = String.valueOf(new StringBuilder().append("\u00a7").append(c).append(string3).append(" "));
            }
            if (string2.length() > 0) {
                if ((double)this.getStringWidth(string2) < d) {
                    arrayList.add(String.valueOf(new StringBuilder().append("\u00a7").append(c).append(string2).append(" ")));
                    string2 = "";
                } else {
                    for (String string4 : this.formatString(string2, d)) {
                        arrayList.add(string4);
                    }
                }
            }
        } else {
            arrayList.add(string);
        }
        return arrayList;
    }

    public float drawCenteredStringWithShadow(String string, float f, float f2, int n) {
        float f3 = this.drawString(string, (double)(f - (float)(this.getStringWidth(string) / 2)) + 1.0, (double)f2 + 1.0, n, true);
        return this.drawString(string, f - (float)(this.getStringWidth(string) / 2), f2, n);
    }

    @Override
    public void setFractionalMetrics(boolean bl) {
        super.setFractionalMetrics(bl);
        this.setupBoldItalicIDs();
    }
}

