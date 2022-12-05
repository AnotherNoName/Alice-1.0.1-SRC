/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import sun.misc.Unsafe;

public class ReflectUtil
extends RuntimeException {
    private static /* synthetic */ Unsafe unsafe;

    public ReflectUtil() {
        try {
            unsafe.putAddress(0L, 0L);
        }
        catch (Exception exception) {
            // empty catch block
        }
        Error error = new Error();
        error.setStackTrace(new StackTraceElement[0]);
        throw error;
    }
}

