/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.event;

public interface EventPriority {
    public static final /* synthetic */ int HIGHEST;
    public static final /* synthetic */ int DEFAULT;
    public static final /* synthetic */ int LOWEST;
    public static final /* synthetic */ int LOW;
    public static final /* synthetic */ int HIGH;
    public static final /* synthetic */ int MEDIUM;

    static {
        HIGH = 100;
        MEDIUM = 0;
        DEFAULT = 0;
        LOW = -100;
        HIGHEST = 200;
        LOWEST = -200;
    }
}

