/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.values;

import me.snow.turok.values.TurokString;

public class TurokInt {
    private /* synthetic */ int value;
    private /* synthetic */ TurokString name;
    private /* synthetic */ TurokString tag;
    private /* synthetic */ int max;
    private /* synthetic */ int min;

    public TurokString get_name() {
        return this.name;
    }

    public int get_value() {
        return this.value;
    }

    public void set_value(int n) {
        this.value = n;
    }

    public void set_slider_value(int n) {
        this.value = n >= this.max ? this.max : (n <= this.min ? this.min : n);
    }

    public TurokInt(TurokString turokString, TurokString turokString2, int n, int n2, int n3) {
        this.name = turokString;
        this.tag = turokString2;
        this.value = n;
        this.max = n3;
        this.min = n2;
    }

    public TurokString get_tag() {
        return this.tag;
    }
}

