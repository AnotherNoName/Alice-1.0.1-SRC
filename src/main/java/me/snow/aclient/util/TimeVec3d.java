/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.util;

import net.minecraft.util.math.Vec3d;

public class TimeVec3d
extends Vec3d {
    private final /* synthetic */ long time;

    public long getTime() {
        return this.time;
    }

    public TimeVec3d(double d, double d2, double d3, long l) {
        super(d, d2, d3);
        this.time = l;
    }
}

