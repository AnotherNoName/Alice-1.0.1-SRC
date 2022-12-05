/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import java.util.Random;

public class RandomUtil {
    private static final /* synthetic */ Random random;

    public static long nextLong(long l, long l2) {
        return l2 - l <= 0L ? l : (long)((double)l + (double)(l2 - l) * Math.random());
    }

    public static float nextFloat(float f, float f2) {
        return f == f2 || f2 - f <= 0.0f ? f : (float)((double)f + (double)(f2 - f) * Math.random());
    }

    public static double nextDouble(double d, double d2) {
        return d == d2 || d2 - d <= 0.0 ? d : d + (d2 - d) * Math.random();
    }

    public static int nextInt(int n, int n2) {
        return n2 - n <= 0 ? n : n + new Random().nextInt(n2 - n);
    }

    public static Random getRandom() {
        return random;
    }

    static {
        random = new Random();
    }

    public final long randomDelay(int n, int n2) {
        return RandomUtil.nextInt(n, n2);
    }
}

