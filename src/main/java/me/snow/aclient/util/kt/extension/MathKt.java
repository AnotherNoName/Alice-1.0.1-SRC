/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.extension;

import kotlin.Metadata;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=2, d1={"\u0000\u0016\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0014\u001a\r\u0010\u0013\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0003*\u00020\u0005H\u0086\b\u001a\r\u0010\u0014\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0014\u001a\u00020\u0003*\u00020\u0005H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0015\u001a\u00020\u0003*\u00020\u0005H\u0086\b\u001a\r\u0010\u0016\u001a\u00020\u0003*\u00020\u0001H\u0086\b\u001a\r\u0010\u0016\u001a\u00020\u0003*\u00020\u0005H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0086\b\u001a\r\u0010\u0017\u001a\u00020\u0005*\u00020\u0005H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0001*\u00020\u0001H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u0005*\u00020\u0005H\u0086\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\"\u0016\u0010\b\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0016\u0010\b\u001a\u00020\u0005*\u00020\u00058\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u000b\"\u0016\u0010\b\u001a\u00020\u0003*\u00020\u00038\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\f\"\u0016\u0010\r\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\n\"\u0016\u0010\r\u001a\u00020\u0005*\u00020\u00058\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000b\"\u0016\u0010\r\u001a\u00020\u0003*\u00020\u00038\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\f\"\u0016\u0010\u000f\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\n\"\u0016\u0010\u000f\u001a\u00020\u0005*\u00020\u00058\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\"\u0016\u0010\u000f\u001a\u00020\u0003*\u00020\u00038\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\f\"\u0016\u0010\u0011\u001a\u00020\u0001*\u00020\u00018\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\n\"\u0016\u0010\u0011\u001a\u00020\u0005*\u00020\u00058\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"\u0016\u0010\u0011\u001a\u00020\u0003*\u00020\u00038\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u0019"}, d2={"FLOOR_DOUBLE_D", "", "FLOOR_DOUBLE_I", "", "FLOOR_FLOAT_F", "", "FLOOR_FLOAT_I", "PI_FLOAT", "cubic", "getCubic", "(D)D", "(F)F", "(I)I", "quart", "getQuart", "quint", "getQuint", "sq", "getSq", "ceilToInt", "fastCeil", "fastFloor", "floorToInt", "toDegree", "toRadian", "Alice"})
public final class MathKt {
    public static final /* synthetic */ float PI_FLOAT = (float)Math.PI;
    public static final /* synthetic */ int FLOOR_DOUBLE_I;
    public static final /* synthetic */ double FLOOR_DOUBLE_D = 1.073741824E9;
    public static final /* synthetic */ int FLOOR_FLOAT_I;
    public static final /* synthetic */ float FLOOR_FLOAT_F = 4194304.0f;

    public static final int getQuart(int n) {
        boolean bl = false;
        return n * n * n * n;
    }

    public static final double toRadian(double d) {
        boolean bl = false;
        return d / 180.0 * Math.PI;
    }

    public static final int fastCeil(float f) {
        boolean bl = false;
        return 0x400000 - (int)(4194304.0f - f);
    }

    public static final int floorToInt(double d) {
        boolean bl = false;
        boolean bl2 = false;
        return (int)Math.floor(d);
    }

    public static final int fastFloor(double d) {
        boolean bl = false;
        return (int)(d + 1.073741824E9) - 0x40000000;
    }

    public static final float toRadian(float f) {
        boolean bl = false;
        return f / 180.0f * (float)Math.PI;
    }

    public static final float getCubic(float f) {
        boolean bl = false;
        return f * f * f;
    }

    public static final float getSq(float f) {
        boolean bl = false;
        return f * f;
    }

    static {
        FLOOR_FLOAT_I = 0x400000;
        FLOOR_DOUBLE_I = 0x40000000;
    }

    public static final double getSq(double d) {
        boolean bl = false;
        return d * d;
    }

    public static final double getQuint(double d) {
        boolean bl = false;
        return d * d * d * d * d;
    }

    public static final float toDegree(float f) {
        boolean bl = false;
        return f * 180.0f / (float)Math.PI;
    }

    public static final int getCubic(int n) {
        boolean bl = false;
        return n * n * n;
    }

    public static final int getQuint(int n) {
        boolean bl = false;
        return n * n * n * n * n;
    }

    public static final int fastCeil(double d) {
        boolean bl = false;
        return 0x40000000 - (int)(1.073741824E9 - d);
    }

    public static final int ceilToInt(float f) {
        boolean bl = false;
        boolean bl2 = false;
        return (int)Math.ceil(f);
    }

    public static final float getQuart(float f) {
        boolean bl = false;
        return f * f * f * f;
    }

    public static final double toDegree(double d) {
        boolean bl = false;
        return d * 180.0 / Math.PI;
    }

    public static final int ceilToInt(double d) {
        boolean bl = false;
        boolean bl2 = false;
        return (int)Math.ceil(d);
    }

    public static final double getQuart(double d) {
        boolean bl = false;
        return d * d * d * d;
    }

    public static final double getCubic(double d) {
        boolean bl = false;
        return d * d * d;
    }

    public static final int fastFloor(float f) {
        boolean bl = false;
        return (int)(f + 4194304.0f) - 0x400000;
    }

    public static final int floorToInt(float f) {
        boolean bl = false;
        boolean bl2 = false;
        return (int)Math.floor(f);
    }

    public static final float getQuint(float f) {
        boolean bl = false;
        return f * f * f * f * f;
    }

    public static final int getSq(int n) {
        boolean bl = false;
        return n * n;
    }
}

