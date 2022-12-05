//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import me.snow.aclient.util.Util;

public class Timer {
    /* synthetic */ long startTime;
    private /* synthetic */ long time;
    /* synthetic */ boolean paused;
    private /* synthetic */ long current;
    private /* synthetic */ long lastMS;
    /* synthetic */ long delay;

    public static void resetTimer() {
        Util.mc.timer.field_194149_e = 50.0f;
    }

    public boolean passedX(double d) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(d * 3.0);
    }

    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }

    public boolean passedDs(double d) {
        return this.passedMs((long)d * 100L);
    }

    public long convertToNS(long l) {
        return l * 1000000L;
    }

    public void resetDelay() {
        this.startTime = System.currentTimeMillis();
    }

    public long getMs(long l) {
        return l / 1000000L;
    }

    public boolean passed(double d) {
        return (double)(System.currentTimeMillis() - this.current) >= d;
    }

    public static void setTimer(float f) {
        Util.mc.timer.field_194149_e = 50.0f / f;
    }

    public boolean passedD(double d) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(d * 3.0);
    }

    public void reset2() {
        this.current = System.currentTimeMillis();
    }

    public void setMs(long l) {
        this.time = System.nanoTime() - this.convertToNS(l);
    }

    public boolean passedNS(long l) {
        return System.nanoTime() - this.time >= l;
    }

    public boolean passedMs(long l) {
        return this.passedNS(this.convertToNS(l));
    }

    public void setTimePassed(long l) {
        this.time = System.nanoTime() - l * 1000000L;
    }

    public boolean passedS(double d) {
        return this.passedMs((long)d * 1000L);
    }

    public boolean isPassed() {
        return !this.paused && System.currentTimeMillis() - this.startTime >= this.delay;
    }

    public boolean sleep(long l) {
        if (System.nanoTime() / 1000000L - l >= l) {
            this.reset();
            return true;
        }
        return false;
    }

    public void setDelay(long l) {
        this.delay = l;
    }

    public boolean hasPassed(double d) {
        return (double)(System.currentTimeMillis() - this.time) >= d;
    }

    public boolean hasReached(long l) {
        return System.currentTimeMillis() - this.current >= l;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void setPaused(boolean bl) {
        this.paused = bl;
    }

    public boolean passedDms(double d) {
        return this.passedMs((long)d * 10L);
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public Timer() {
        this.time = -1L;
        this.lastMS = 0L;
        this.startTime = System.currentTimeMillis();
        this.delay = 0L;
        this.paused = false;
        this.current = -1L;
    }
}

