/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.task;

public class Rect {
    private /* synthetic */ int y;
    private /* synthetic */ int height;
    private /* synthetic */ int width;
    private /* synthetic */ String tag;
    private /* synthetic */ int x;

    public void transform(int n, int n2) {
        this.x = n;
        this.y = n2;
    }

    public int get_height() {
        return this.height;
    }

    public int get_x() {
        return this.x;
    }

    public boolean event_mouse(int n, int n2) {
        return n >= this.get_x() && n2 >= this.get_y() && n <= this.get_screen_width() && n2 <= this.get_screen_height();
    }

    public void transform(int n, int n2, int n3, int n4) {
        this.x = n;
        this.y = n2;
        this.width = n3;
        this.height = n4;
    }

    public Rect(String string, int n, int n2) {
        this.tag = string;
        this.width = n;
        this.height = n2;
    }

    public int get_width() {
        return this.width;
    }

    public int get_y() {
        return this.y;
    }

    public int get_screen_height() {
        return this.y + this.height;
    }

    public int get_screen_width() {
        return this.x + this.width;
    }

    public String get_tag() {
        return this.tag;
    }
}

