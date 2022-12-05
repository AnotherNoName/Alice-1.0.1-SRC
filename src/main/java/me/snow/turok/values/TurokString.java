/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.values;

public class TurokString {
    private /* synthetic */ String value;
    private /* synthetic */ String tag;
    private /* synthetic */ String name;

    public String get_value() {
        return this.value;
    }

    public static String to_string(double d) {
        return Double.toString(d);
    }

    public static String to_string(String string) {
        return string;
    }

    public String get_name() {
        return this.name;
    }

    public String get_tag() {
        return this.tag;
    }

    public static String to_string(float f) {
        return Float.toString(f);
    }

    public TurokString(String string, String string2, String string3) {
        this.name = string;
        this.tag = string2;
        this.value = string3;
    }

    public static String to_string(boolean bl) {
        return Boolean.toString(bl);
    }

    public void set_value(String string) {
        this.value = string;
    }

    public static String to_string(int n) {
        return Integer.toString(n);
    }
}

