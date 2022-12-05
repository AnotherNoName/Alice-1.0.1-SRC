/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

public class PairUtil<F, S> {
    private /* synthetic */ F first;
    private /* synthetic */ S second;

    public void setFirst(F f) {
        this.first = f;
    }

    public S getSecond() {
        return this.second;
    }

    public F getFirst() {
        return this.first;
    }

    public void setSecond(S s) {
        this.second = s;
    }

    public PairUtil(F f, S s) {
        this.first = f;
        this.second = s;
    }
}

