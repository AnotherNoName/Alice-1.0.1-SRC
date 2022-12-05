/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  me.snow.aclient.util.kt.graphics.InterpolateFunction
 */
package me.snow.aclient.util.kt.graphics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.snow.aclient.util.kt.graphics.InterpolateFunction;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lme/snow/aclient/util/kt/graphics/AnimationFlag;", "", "interpolation", "Lme/snow/aclient/util/kt/graphics/InterpolateFunction;", "(Lme/snow/aclient/util/kt/graphics/InterpolateFunction;)V", "current", "", "prev", "time", "", "forceUpdate", "", "get", "input", "update", "", "getAndUpdate", "Alice"})
public final class AnimationFlag {
    private /* synthetic */ float prev;
    private final /* synthetic */ InterpolateFunction interpolation;
    private /* synthetic */ long time;
    private /* synthetic */ float current;

    public final void forceUpdate(float f, float f2) {
        this.prev = f;
        this.current = f2;
        this.time = System.currentTimeMillis();
    }

    public final float get(float f, boolean bl) {
        float f2 = this.interpolation.invoke(this.time, this.prev, this.current);
        if (bl && f != this.current) {
            this.prev = f2;
            this.current = f;
            this.time = System.currentTimeMillis();
        }
        return f2;
    }

    public final float getAndUpdate(float f) {
        float f2 = this.interpolation.invoke(this.time, this.prev, this.current);
        if (f != this.current) {
            this.prev = f2;
            this.current = f;
            this.time = System.currentTimeMillis();
        }
        return f2;
    }

    public AnimationFlag(@NotNull InterpolateFunction interpolateFunction) {
        Intrinsics.checkParameterIsNotNull((Object)interpolateFunction, "interpolation");
        this.interpolation = interpolateFunction;
        this.time = System.currentTimeMillis();
    }
}

