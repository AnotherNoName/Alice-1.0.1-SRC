/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package me.snow.turok.draw;

import org.lwjgl.opengl.GL11;

public class GL {
    public static void finish() {
        GL11.glDisable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void resize(int n, int n2, float f) {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glTranslatef((float)n, (float)n2, (float)0.0f);
        GL11.glScalef((float)f, (float)f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void start() {
        GL11.glDisable((int)3553);
    }

    public static void color(float f, float f2, float f3, float f4) {
        GL11.glColor4f((float)(f / 255.0f), (float)(f2 / 255.0f), (float)(f3 / 255.0f), (float)(f4 / 255.0f));
    }

    public static void resize(int n, int n2, float f, String string) {
        GL11.glScalef((float)(1.0f / f), (float)(1.0f / f), (float)1.0f);
        GL11.glTranslatef((float)(-n), (float)(-n2), (float)0.0f);
        GL11.glDisable((int)3553);
    }

    public static void draw_rect(int n, int n2, int n3, int n4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glEnd();
    }
}

