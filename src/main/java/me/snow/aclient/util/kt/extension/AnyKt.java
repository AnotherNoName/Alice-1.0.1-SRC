/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.extension;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0004\u0018\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u00a8\u0006\u0006"}, d2={"ifType", "", "T", "", "block", "Lkotlin/Function1;", "Alice"})
public final class AnyKt {
    public static final /* synthetic */ <T> void ifType(@Nullable Object object, @NotNull Function1<? super T, Unit> function1) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(function1, "block");
        Intrinsics.reifiedOperationMarker(3, "T");
        if (object instanceof Object) {
            function1.invoke(object);
        }
    }
}

