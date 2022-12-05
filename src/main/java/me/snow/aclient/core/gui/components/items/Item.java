/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.core.gui.components.items;

import me.snow.aclient.module.Feature;

public class Item
extends Feature {
    protected /* synthetic */ int height;
    private /* synthetic */ boolean hidden;
    protected /* synthetic */ int width;
    protected /* synthetic */ float x;
    protected /* synthetic */ float y;

    public void mouseClicked(int n, int n2, int n3) {
    }

    public void setLocation(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int n) {
        this.width = n;
    }

    public float getX() {
        return this.x;
    }

    public boolean setHidden(boolean bl) {
        this.hidden = bl;
        return this.hidden;
    }

    public void update() {
    }

    public void drawScreen(int n, int n2, float f) {
    }

    public void setHeight(int n) {
        this.height = n;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void mouseReleased(int n, int n2, int n3) {
    }

    public int getWidth() {
        return this.width;
    }

    public float getY() {
        return this.y;
    }

    public Item(String string) {
        super(string);
    }

    public void onKeyTyped(char c, int n) {
    }
}

