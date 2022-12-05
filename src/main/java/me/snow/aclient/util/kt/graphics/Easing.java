/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.graphics;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b#\b\u0086\u0001\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001)B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u001e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H$J\u001e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u001e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007R\u0012\u0010\u0003\u001a\u00020\u0000X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(\u00a8\u0006*"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing;", "", "(Ljava/lang/String;I)V", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "dec", "", "x", "max", "min", "dec0", "inc", "inc0", "incOrDec", "incOrDecOpposite", "LINEAR", "IN_SINE", "OUT_SINE", "IN_OUT_SINE", "IN_QUAD", "OUT_QUAD", "IN_OUT_QUAD", "IN_CUBIC", "OUT_CUBIC", "IN_OUT_CUBIC", "IN_QUART", "OUT_QUART", "IN_OUT_QUART", "IN_QUINT", "OUT_QUINT", "IN_OUT_QUINT", "IN_EXPO", "OUT_EXPO", "IN_OUT_EXPO", "IN_CIRC", "OUT_CIRC", "IN_OUT_CIRC", "IN_BACK", "OUT_BACK", "IN_OUT_BACK", "Companion", "Alice"})
public abstract class Easing
extends Enum<Easing> {
    public static final /* synthetic */ /* enum */ Easing IN_OUT_QUART;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_QUAD;
    public static final /* synthetic */ /* enum */ Easing OUT_CUBIC;
    public static final /* synthetic */ /* enum */ Easing IN_QUAD;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_EXPO;
    public static final /* synthetic */ /* enum */ Easing OUT_EXPO;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_SINE;
    public static final /* synthetic */ /* enum */ Easing OUT_QUART;
    public static final /* synthetic */ /* enum */ Easing IN_QUART;
    public static final /* synthetic */ /* enum */ Easing OUT_SINE;
    public static final /* synthetic */ /* enum */ Easing IN_SINE;
    public static final /* synthetic */ /* enum */ Easing IN_EXPO;
    public static final /* synthetic */ /* enum */ Easing LINEAR;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_BACK;
    public static final /* synthetic */ /* enum */ Easing IN_CUBIC;
    public static final /* synthetic */ /* enum */ Easing OUT_CIRC;
    public static final /* synthetic */ /* enum */ Easing OUT_BACK;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_CIRC;
    public static final /* synthetic */ Companion Companion;
    public static final /* synthetic */ /* enum */ Easing OUT_QUAD;
    private static final /* synthetic */ Easing[] $VALUES;
    public static final /* synthetic */ /* enum */ Easing IN_QUINT;
    public static final /* synthetic */ /* enum */ Easing IN_BACK;
    public static final /* synthetic */ /* enum */ Easing OUT_QUINT;
    public static final /* synthetic */ /* enum */ Easing IN_CIRC;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_QUINT;
    public static final /* synthetic */ /* enum */ Easing IN_OUT_CUBIC;

    public final float incOrDecOpposite(float f, float f2, float f3) {
        if (f3 == f2) {
            return f2;
        }
        float f4 = f3 > f2 ? this.inc(f) : this.getOpposite().inc(f);
        boolean bl = false;
        return f2 + (f3 - f2) * f4;
    }

    @JvmStatic
    public static final long toDelta(long l) {
        return Companion.toDelta(l);
    }

    public static Easing[] values() {
        return (Easing[])$VALUES.clone();
    }

    public final float dec(float f, float f2, float f3) {
        float f4;
        float f5 = f3;
        float f6 = f2;
        if (f5 == f6) {
            return 0.0f;
        }
        if (f5 < f6) {
            f4 = f5;
            f5 = f6;
            f6 = f4;
        }
        if (f <= 0.0f) {
            return f5;
        }
        if (f >= 1.0f) {
            return f6;
        }
        f4 = this.dec0(f);
        boolean bl = false;
        return f6 + (f5 - f6) * f4;
    }

    protected abstract float inc0(float var1);

    @JvmStatic
    public static final float toDelta(long l, int n) {
        return Companion.toDelta(l, n);
    }

    private Easing() {
    }

    public /* synthetic */ Easing(String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final float dec0(float f) {
        return 1.0f - this.inc0(f);
    }

    @JvmStatic
    public static final float toDelta(long l, long l2) {
        return Companion.toDelta(l, l2);
    }

    public static Easing valueOf(String string) {
        return Enum.valueOf(Easing.class, string);
    }

    @NotNull
    public abstract Easing getOpposite();

    @JvmStatic
    public static final float toDelta(long l, float f) {
        return Companion.toDelta(l, f);
    }

    static {
        Easing[] arreasing = new Easing[25];
        Easing[] arreasing2 = arreasing;
        arreasing[0] = LINEAR = new LINEAR("LINEAR", 0);
        arreasing[1] = IN_SINE = new IN_SINE("IN_SINE", 1);
        arreasing[2] = OUT_SINE = new OUT_SINE("OUT_SINE", 2);
        arreasing[3] = IN_OUT_SINE = new IN_OUT_SINE("IN_OUT_SINE", 3);
        arreasing[4] = IN_QUAD = new IN_QUAD("IN_QUAD", 4);
        arreasing[5] = OUT_QUAD = new OUT_QUAD("OUT_QUAD", 5);
        arreasing[6] = IN_OUT_QUAD = new IN_OUT_QUAD("IN_OUT_QUAD", 6);
        arreasing[7] = IN_CUBIC = new IN_CUBIC("IN_CUBIC", 7);
        arreasing[8] = OUT_CUBIC = new OUT_CUBIC("OUT_CUBIC", 8);
        arreasing[9] = IN_OUT_CUBIC = new IN_OUT_CUBIC("IN_OUT_CUBIC", 9);
        arreasing[10] = IN_QUART = new IN_QUART("IN_QUART", 10);
        arreasing[11] = OUT_QUART = new OUT_QUART("OUT_QUART", 11);
        arreasing[12] = IN_OUT_QUART = new IN_OUT_QUART("IN_OUT_QUART", 12);
        arreasing[13] = IN_QUINT = new IN_QUINT("IN_QUINT", 13);
        arreasing[14] = OUT_QUINT = new OUT_QUINT("OUT_QUINT", 14);
        arreasing[15] = IN_OUT_QUINT = new IN_OUT_QUINT("IN_OUT_QUINT", 15);
        arreasing[16] = IN_EXPO = new IN_EXPO("IN_EXPO", 16);
        arreasing[17] = OUT_EXPO = new OUT_EXPO("OUT_EXPO", 17);
        arreasing[18] = IN_OUT_EXPO = new IN_OUT_EXPO("IN_OUT_EXPO", 18);
        arreasing[19] = IN_CIRC = new IN_CIRC("IN_CIRC", 19);
        arreasing[20] = OUT_CIRC = new OUT_CIRC("OUT_CIRC", 20);
        arreasing[21] = IN_OUT_CIRC = new IN_OUT_CIRC("IN_OUT_CIRC", 21);
        arreasing[22] = IN_BACK = new IN_BACK("IN_BACK", 22);
        arreasing[23] = OUT_BACK = new OUT_BACK("OUT_BACK", 23);
        arreasing[24] = IN_OUT_BACK = new IN_OUT_BACK("IN_OUT_BACK", 24);
        $VALUES = arreasing;
        Companion = new Companion(null);
    }

    public final float dec(float f) {
        if (f <= 0.0f) {
            return 1.0f;
        }
        if (f >= 1.0f) {
            return 0.0f;
        }
        return this.dec0(f);
    }

    public final float inc(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        return this.inc0(f);
    }

    public final float inc(float f, float f2, float f3) {
        float f4;
        float f5 = f3;
        float f6 = f2;
        if (f5 == f6) {
            return 0.0f;
        }
        if (f5 < f6) {
            f4 = f5;
            f5 = f6;
            f6 = f4;
        }
        if (f <= 0.0f) {
            return f6;
        }
        if (f >= 1.0f) {
            return f5;
        }
        f4 = this.inc0(f);
        boolean bl = false;
        return f6 + (f5 - f6) * f4;
    }

    public final float inc(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return f2;
        }
        return this.inc0(f) * f2;
    }

    public final float incOrDec(float f, float f2, float f3) {
        float f4 = this.inc(f);
        boolean bl = false;
        return f2 + (f3 - f2) * f4;
    }

    public final float dec(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        if (f <= 0.0f) {
            return f2;
        }
        if (f >= 1.0f) {
            return 0.0f;
        }
        return this.dec0(f) * f2;
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$LINEAR;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class LINEAR
    extends Easing {
        @Override
        protected float inc0(float f) {
            return f;
        }

        LINEAR() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_BACK;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_BACK
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_BACK;
        }

        OUT_BACK() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = f - 1.0f;
            float f3 = 2.70158f;
            float f4 = 1.0f;
            boolean bl = false;
            float f5 = f2 * f2 * f2;
            float f6 = f4 + f3 * f5;
            f2 = f - 1.0f;
            f3 = 1.70158f;
            f4 = f6;
            bl = false;
            f5 = f2 * f2;
            return f4 + f3 * f5;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_QUART;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_QUART
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_QUART;
        }

        IN_QUART() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = f;
            boolean bl = false;
            return f2 * f2 * f2 * f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_QUAD;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_QUAD
    extends Easing {
        IN_QUAD() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_QUAD;
        }

        @Override
        protected float inc0(float f) {
            float f2 = f;
            boolean bl = false;
            return f2 * f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0018\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007\u00a8\u0006\t"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$Companion;", "", "()V", "toDelta", "", "start", "", "length", "", "Alice"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final long toDelta(long l) {
            return System.currentTimeMillis() - l;
        }

        @JvmStatic
        public final float toDelta(long l, long l2) {
            return this.toDelta(l, (float)l2);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final float toDelta(long l, int n) {
            return this.toDelta(l, (float)n);
        }

        @JvmStatic
        public final float toDelta(long l, float f) {
            return RangesKt.coerceIn((float)this.toDelta(l) / f, 0.0f, 1.0f);
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_QUART;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_QUART
    extends Easing {
        OUT_QUART() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = 1.0f - f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = f2 * f2 * f2 * f2;
            return f3 - f4;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_QUART;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_CIRC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_CIRC
    extends Easing {
        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                float f3 = 2.0f * f;
                float f4 = 1.0f;
                float f5 = 1.0f;
                boolean bl = false;
                float f6 = f3 * f3;
                f3 = f4 - f6;
                bl = false;
                f4 = (float)Math.sqrt(f3);
                f2 = (f5 - f4) / 2.0f;
            } else {
                float f7 = -2.0f * f + 2.0f;
                float f8 = 1.0f;
                boolean bl = false;
                float f9 = f7 * f7;
                f7 = f8 - f9;
                bl = false;
                f2 = ((float)Math.sqrt(f7) + 1.0f) / 2.0f;
            }
            return f2;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }

        IN_OUT_CIRC() {
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_CIRC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_CIRC
    extends Easing {
        OUT_CIRC() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_CIRC;
        }

        @Override
        protected float inc0(float f) {
            float f2 = f - 1.0f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = f2 * f2;
            f2 = f3 - f4;
            bl = false;
            return (float)Math.sqrt(f2);
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_QUAD;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_QUAD
    extends Easing {
        OUT_QUAD() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = 1.0f - f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = f2 * f2;
            return f3 - f4;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_QUAD;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_CUBIC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_CUBIC
    extends Easing {
        @Override
        protected float inc0(float f) {
            float f2 = 1.0f - f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = f2 * f2 * f2;
            return f3 - f4;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_CUBIC;
        }

        OUT_CUBIC() {
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_QUAD;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_QUAD
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }

        IN_OUT_QUAD() {
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                f2 = 2.0f * f * f;
            } else {
                float f3 = -2.0f * f + 2.0f;
                float f4 = 1.0f;
                boolean bl = false;
                float f5 = f3 * f3;
                f2 = f4 - f5 / 2.0f;
            }
            return f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_CUBIC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_CUBIC
    extends Easing {
        IN_CUBIC() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = f;
            boolean bl = false;
            return f2 * f2 * f2;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_CUBIC;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_EXPO;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_EXPO
    extends Easing {
        IN_OUT_EXPO() {
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f == 0.0f) {
                f2 = 0.0f;
            } else if (f == 1.0f) {
                f2 = 1.0f;
            } else if (f < 0.5f) {
                float f3 = 2.0f;
                float f4 = 20.0f * f - 10.0f;
                boolean bl = false;
                f2 = (float)Math.pow(f3, f4) / 2.0f;
            } else {
                float f5 = 2.0f;
                float f6 = -20.0f * f + 10.0f;
                float f7 = 2.0f;
                boolean bl = false;
                float f8 = (float)Math.pow(f5, f6);
                f2 = (f7 - f8) / 2.0f;
            }
            return f2;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_QUINT;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_QUINT
    extends Easing {
        IN_QUINT() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_QUINT;
        }

        @Override
        protected float inc0(float f) {
            float f2 = f;
            boolean bl = false;
            return f2 * f2 * f2 * f2 * f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_CIRC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_CIRC
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_CIRC;
        }

        IN_CIRC() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = f;
            float f3 = 1.0f;
            float f4 = 1.0f;
            boolean bl = false;
            float f5 = f2 * f2;
            f2 = f3 - f5;
            bl = false;
            f3 = (float)Math.sqrt(f2);
            return f4 - f3;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_QUINT;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_QUINT
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_QUINT;
        }

        OUT_QUINT() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = 1.0f - f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = f2 * f2 * f2 * f2 * f2;
            return f3 - f4;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_QUINT;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_QUINT
    extends Easing {
        IN_OUT_QUINT() {
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                float f3 = f;
                float f4 = 16.0f;
                boolean bl = false;
                float f5 = f3 * f3 * f3 * f3 * f3;
                f2 = f4 * f5;
            } else {
                float f6 = (float)-2 * f + (float)2;
                float f7 = 1.0f;
                boolean bl = false;
                float f8 = f6 * f6 * f6 * f6 * f6;
                f2 = f7 - f8 / 2.0f;
            }
            return f2;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_EXPO;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_EXPO
    extends Easing {
        OUT_EXPO() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return IN_EXPO;
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f == 1.0f) {
                f2 = 1.0f;
            } else {
                float f3 = 2.0f;
                float f4 = -10.0f * f;
                float f5 = 1.0f;
                boolean bl = false;
                float f6 = (float)Math.pow(f3, f4);
                f2 = f5 - f6;
            }
            return f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_SINE;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_SINE
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_SINE;
        }

        @Override
        protected float inc0(float f) {
            float f2 = f * (float)Math.PI / 2.0f;
            float f3 = 1.0f;
            boolean bl = false;
            float f4 = (float)Math.cos(f2);
            return f3 - f4;
        }

        IN_SINE() {
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$OUT_SINE;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class OUT_SINE
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }

        @Override
        protected float inc0(float f) {
            float f2 = f * (float)Math.PI / 2.0f;
            boolean bl = false;
            return (float)Math.sin(f2);
        }

        OUT_SINE() {
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_BACK;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_BACK
    extends Easing {
        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }

        IN_OUT_BACK() {
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                float f3 = 2.0f * f;
                boolean bl = false;
                f2 = f3 * f3 * (7.189819f * f - 2.5949094f) / 2.0f;
            } else {
                float f4 = 2.0f * f - 2.0f;
                boolean bl = false;
                f2 = (f4 * f4 * (3.5949094f * (f * 2.0f - 2.0f) + 2.5949094f) + 2.0f) / 2.0f;
            }
            return f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_BACK;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_BACK
    extends Easing {
        @Override
        protected float inc0(float f) {
            float f2 = f;
            float f3 = 2.70158f;
            boolean bl = false;
            float f4 = f2 * f2 * f2;
            float f5 = f3 * f4;
            f2 = f;
            f4 = 1.70158f;
            f3 = f5;
            bl = false;
            float f6 = f2 * f2;
            return f3 - f4 * f6;
        }

        IN_BACK() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_BACK;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_EXPO;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_EXPO
    extends Easing {
        @Override
        protected float inc0(float f) {
            float f2;
            if (f == 0.0f) {
                f2 = 0.0f;
            } else {
                float f3 = 2.0f;
                float f4 = 10.0f * f - 10.0f;
                boolean bl = false;
                f2 = (float)Math.pow(f3, f4);
            }
            return f2;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return OUT_EXPO;
        }

        IN_EXPO() {
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_QUART;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_QUART
    extends Easing {
        IN_OUT_QUART() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }

        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                float f3 = f;
                float f4 = 8.0f;
                boolean bl = false;
                float f5 = f3 * f3 * f3 * f3;
                f2 = f4 * f5;
            } else {
                float f6 = -2.0f * f + 2.0f;
                float f7 = 1.0f;
                boolean bl = false;
                float f8 = f6 * f6 * f6 * f6;
                f2 = f7 - f8 / 2.0f;
            }
            return f2;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_CUBIC;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_CUBIC
    extends Easing {
        @Override
        protected float inc0(float f) {
            float f2;
            if (f < 0.5f) {
                float f3 = f;
                float f4 = 4.0f;
                boolean bl = false;
                float f5 = f3 * f3 * f3;
                f2 = f4 * f5;
            } else {
                float f6 = -2.0f * f + 2.0f;
                float f7 = 1.0f;
                boolean bl = false;
                float f8 = f6 * f6 * f6;
                f2 = f7 - f8 / 2.0f;
            }
            return f2;
        }

        IN_OUT_CUBIC() {
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u00c6\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0014R\u0014\u0010\u0002\u001a\u00020\u00018VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\b"}, d2={"Lme/snow/aclient/util/kt/graphics/Easing$IN_OUT_SINE;", "Lme/snow/aclient/util/kt/graphics/Easing;", "opposite", "getOpposite", "()Lme/snow/aclient/util/kt/graphics/Easing;", "inc0", "", "x", "Alice"})
    static final class IN_OUT_SINE
    extends Easing {
        IN_OUT_SINE() {
        }

        @Override
        protected float inc0(float f) {
            float f2 = (float)Math.PI * f;
            boolean bl = false;
            return -((float)Math.cos(f2) - 1.0f) / 2.0f;
        }

        @Override
        @NotNull
        public Easing getOpposite() {
            return this;
        }
    }
}

