/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.math;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0087\bJ1\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0087\bJ1\u0010\u0006\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\bJ1\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0087\bJ\u0011\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0007H\u0087\bJ\u0011\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\fH\u0087\bJ\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0087\bJ!\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0087\bJ!\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0087\bJ!\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0087\bJ\u0019\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0004H\u0087\bJ\u0019\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0004H\u0087\b\u00a8\u0006\u001c"}, d2={"Lme/snow/aclient/util/kt/math/MathUtils;", "", "()V", "ceilToPOT", "", "valueIn", "convertRange", "", "minIn", "maxIn", "minOut", "maxOut", "", "decimalPlaces", "value", "isNumberEven", "", "i", "lerp", "from", "to", "delta", "reverseNumber", "num", "min", "max", "round", "places", "Alice"})
public final class MathUtils {
    public static final /* synthetic */ MathUtils INSTANCE;

    @JvmStatic
    public static final float lerp(float f, float f2, float f3) {
        boolean bl = false;
        return f + (f2 - f) * f3;
    }

    @JvmStatic
    public static final double lerp(double d, double d2, double d3) {
        boolean bl = false;
        return d + (d2 - d) * d3;
    }

    @JvmStatic
    public static final float round(float f, int n) {
        boolean bl = false;
        float f2 = 10.0f;
        boolean bl2 = false;
        float f3 = (float)Math.pow(f2, n);
        f2 = f * f3;
        bl2 = false;
        return (float)Math.rint(f2) / f3;
    }

    @JvmStatic
    public static final int reverseNumber(int n, int n2, int n3) {
        boolean bl = false;
        return n3 + n2 - n;
    }

    @JvmStatic
    public static final boolean isNumberEven(int n) {
        boolean bl = false;
        return (n & 1) == 0;
    }

    private MathUtils() {
    }

    @JvmStatic
    public static final double convertRange(double d, double d2, double d3, double d4, double d5) {
        boolean bl = false;
        double d6 = d3 - d2;
        double d7 = d5 - d4;
        double d8 = (d - d2) * (d7 / d6) + d4;
        boolean bl2 = false;
        double d9 = Math.min(d4, d5);
        boolean bl3 = false;
        double d10 = Math.max(d4, d5);
        bl3 = false;
        double d11 = Math.max(d8, d9);
        boolean bl4 = false;
        return Math.min(d11, d10);
    }

    @JvmStatic
    public static final int decimalPlaces(float f) {
        Object object;
        boolean bl = false;
        List list = StringsKt.split$default((CharSequence)String.valueOf(f), new char[]{'.'}, false, 0, 6, null);
        int n = 1;
        boolean bl2 = false;
        if (n <= CollectionsKt.getLastIndex(list)) {
            object = list.get(n);
        } else {
            int n2 = n;
            boolean bl3 = false;
            object = "0";
        }
        return ((String)object).length();
    }

    @JvmStatic
    public static final double round(double d, int n) {
        boolean bl = false;
        double d2 = 10.0;
        boolean bl2 = false;
        double d3 = Math.pow(d2, n);
        d2 = d * d3;
        bl2 = false;
        return Math.rint(d2) / d3;
    }

    @JvmStatic
    public static final float convertRange(float f, float f2, float f3, float f4, float f5) {
        boolean bl = false;
        double d = f;
        double d2 = f2;
        double d3 = f3;
        double d4 = f4;
        double d5 = f5;
        boolean bl2 = false;
        double d6 = d3 - d2;
        double d7 = d5 - d4;
        double d8 = (d - d2) * (d7 / d6) + d4;
        boolean bl3 = false;
        double d9 = Math.min(d4, d5);
        boolean bl4 = false;
        double d10 = Math.max(d4, d5);
        bl4 = false;
        double d11 = Math.max(d8, d9);
        boolean bl5 = false;
        return (float)Math.min(d11, d10);
    }

    @JvmStatic
    public static final int ceilToPOT(int n) {
        boolean bl = false;
        int n2 = n;
        --n2;
        n2 |= n2 >> 1;
        n2 |= n2 >> 2;
        n2 |= n2 >> 4;
        n2 |= n2 >> 8;
        n2 |= n2 >> 16;
        return ++n2;
    }

    @JvmStatic
    public static final int decimalPlaces(double d) {
        Object object;
        boolean bl = false;
        List list = StringsKt.split$default((CharSequence)String.valueOf(d), new char[]{'.'}, false, 0, 6, null);
        int n = 1;
        boolean bl2 = false;
        if (n <= CollectionsKt.getLastIndex(list)) {
            object = list.get(n);
        } else {
            int n2 = n;
            boolean bl3 = false;
            object = "0";
        }
        return ((String)object).length();
    }

    @JvmStatic
    public static final int convertRange(int n, int n2, int n3, int n4, int n5) {
        boolean bl = false;
        double d = n;
        double d2 = n2;
        double d3 = n3;
        double d4 = n4;
        double d5 = n5;
        boolean bl2 = false;
        double d6 = d3 - d2;
        double d7 = d5 - d4;
        double d8 = (d - d2) * (d7 / d6) + d4;
        boolean bl3 = false;
        double d9 = Math.min(d4, d5);
        boolean bl4 = false;
        double d10 = Math.max(d4, d5);
        bl4 = false;
        double d11 = Math.max(d8, d9);
        boolean bl5 = false;
        return (int)Math.min(d11, d10);
    }

    static {
        MathUtils mathUtils;
        INSTANCE = mathUtils = new MathUtils();
    }
}

