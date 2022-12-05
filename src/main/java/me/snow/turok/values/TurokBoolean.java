/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.values;

import me.snow.turok.values.TurokGeneric;
import me.snow.turok.values.TurokString;

public class TurokBoolean {
    private /* synthetic */ TurokString name;
    private /* synthetic */ TurokGeneric<Boolean> value;
    private /* synthetic */ TurokString tag;

    public void set_value(boolean bl) {
        this.value.set_value(bl);
    }

    public boolean get_value() {
        return this.value.get_value();
    }

    public TurokBoolean(TurokString turokString, TurokString turokString2, boolean bl) {
        this.name = turokString;
        this.tag = turokString2;
        this.value = new TurokGeneric<Boolean>(bl);
    }

    public TurokString get_name() {
        return this.name;
    }

    public TurokString get_tag() {
        return this.tag;
    }
}

