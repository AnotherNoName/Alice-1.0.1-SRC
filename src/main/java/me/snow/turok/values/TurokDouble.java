/*
 * Decompiled with CFR 0.150.
 */
package me.snow.turok.values;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.snow.turok.values.TurokString;

public class TurokDouble {
    private /* synthetic */ TurokString tag;
    private /* synthetic */ double value;
    private /* synthetic */ double min;
    private /* synthetic */ double max;
    private /* synthetic */ TurokString name;

    public void set_value(double d) {
        this.value = d;
    }

    public TurokDouble(TurokString turokString, TurokString turokString2, double d, double d2, double d3) {
        this.name = turokString;
        this.tag = turokString2;
        this.value = d;
        this.max = d3;
        this.min = d2;
    }

    public double get_value() {
        return this.value;
    }

    public static double floor(double d, double d2) {
        d = Math.floor(d);
        d2 = Math.floor(d2);
        if (d == 0.0 || d2 == 0.0) {
            return d + d2;
        }
        return TurokDouble.floor(d, d % d2);
    }

    public static double step(double d, double d2) {
        double d3 = TurokDouble.floor(d, d2);
        if (d3 > d2) {
            d3 = d2 / 20.0;
        }
        if (d2 > 10.0) {
            d3 = Math.round(d3);
        }
        if (d3 == 0.0) {
            d3 = d2;
        }
        return d3;
    }

    public TurokString get_name() {
        return this.name;
    }

    public void set_slider_value(double d) {
        this.value = d >= this.max ? this.max : (d <= this.min ? this.min : d);
    }

    public TurokString get_tag() {
        return this.tag;
    }

    public static double round(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}

