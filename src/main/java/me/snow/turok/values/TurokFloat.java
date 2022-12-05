/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.values;

import me.snow.turok.values.TurokString;

public class TurokFloat {
    private /* synthetic */ TurokString name;
    private /* synthetic */ float min;
    private /* synthetic */ float max;
    private /* synthetic */ TurokString tag;
    private /* synthetic */ float value;

    public TurokString get_tag() {
        return this.tag;
    }

    public void set_slider_value(float f) {
        this.value = f >= this.max ? this.max : (f <= this.min ? this.min : f);
    }

    public void set_value(float f) {
        this.value = f;
    }

    public TurokFloat(TurokString turokString, TurokString turokString2, float f, float f2, float f3) {
        this.name = turokString;
        this.tag = turokString2;
        this.max = f3;
        this.min = f2;
    }

    public float get_value() {
        return this.value;
    }

    public TurokString get_name() {
        return this.name;
    }
}

