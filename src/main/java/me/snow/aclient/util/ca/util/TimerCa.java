/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.ca.util;

public class TimerCa {
    private /* synthetic */ long time;

    public boolean passedNS(long l) {
        return System.nanoTime() - this.time >= l;
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public boolean passedMs(long l) {
        return this.passedNS(this.convertToNS(l));
    }

    public long getMs(long l) {
        return l / 1000000L;
    }

    public void setMs(long l) {
        this.time = System.nanoTime() - this.convertToNS(l);
    }

    public TimerCa() {
        this.time = -1L;
    }

    public boolean passedDs(double d) {
        return this.passedMs((long)d * 100L);
    }

    public long convertToNS(long l) {
        return l * 1000000L;
    }

    public boolean passedDms(double d) {
        return this.passedMs((long)d * 10L);
    }

    public boolean passedS(double d) {
        return this.passedMs((long)d * 1000L);
    }

    public TimerCa reset() {
        this.time = System.nanoTime();
        return this;
    }
}

