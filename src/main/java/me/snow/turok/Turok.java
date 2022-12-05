/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok;

import me.snow.turok.draw.GL;
import me.snow.turok.task.Font;

public class Turok {
    private /* synthetic */ Font font_manager;

    public void resize(int n, int n2, float f, String string) {
        GL.resize(n, n2, f, "end");
    }

    public Turok(String string) {
    }

    public void resize(int n, int n2, float f) {
        GL.resize(n, n2, f);
    }

    public Font get_font_manager() {
        return this.font_manager;
    }
}

