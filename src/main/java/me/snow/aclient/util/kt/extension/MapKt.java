/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.extension;

import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=2, d1={"\u0000\u001e\n\u0000\n\u0002\u0010'\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0000\u001a0\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a)\u0010\u0005\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006\u00a2\u0006\u0002\u0010\u0007\u001a)\u0010\b\u001a\u0004\u0018\u0001H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u00a2\u0006\u0002\u0010\t\u001a0\u0010\n\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a)\u0010\u000b\u001a\u0004\u0018\u0001H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u00a2\u0006\u0002\u0010\t\u001a.\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a.\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006\u001a.\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\r\u00a8\u0006\u000e"}, d2={"firstEntryOrNull", "", "K", "V", "Ljava/util/NavigableMap;", "firstKeyOrNull", "Ljava/util/SortedMap;", "(Ljava/util/SortedMap;)Ljava/lang/Object;", "firstValueOrNull", "(Ljava/util/NavigableMap;)Ljava/lang/Object;", "lastEntryOrNull", "lastValueOrNull", "synchronized", "", "Alice"})
public final class MapKt {
    @Nullable
    public static final <K, V> K firstKeyOrNull(@NotNull SortedMap<K, V> sortedMap) {
        K k;
        Intrinsics.checkParameterIsNotNull(sortedMap, "$this$firstKeyOrNull");
        try {
            k = sortedMap.firstKey();
        }
        catch (NoSuchElementException noSuchElementException) {
            k = null;
        }
        return k;
    }

    @Nullable
    public static final <K, V> V lastValueOrNull(@NotNull NavigableMap<K, V> navigableMap) {
        Intrinsics.checkParameterIsNotNull(navigableMap, "$this$lastValueOrNull");
        Map.Entry<K, V> entry = navigableMap.lastEntry();
        return (V)(entry != null ? entry.getValue() : null);
    }

    @Nullable
    public static final <K, V> V firstValueOrNull(@NotNull NavigableMap<K, V> navigableMap) {
        Intrinsics.checkParameterIsNotNull(navigableMap, "$this$firstValueOrNull");
        Map.Entry<K, V> entry = MapKt.firstEntryOrNull(navigableMap);
        return (V)(entry != null ? entry.getValue() : null);
    }

    @NotNull
    public static final <K, V> SortedMap<K, V> synchronized(@NotNull SortedMap<K, V> sortedMap) {
        Intrinsics.checkParameterIsNotNull(sortedMap, "$this$synchronized");
        SortedMap<K, V> sortedMap2 = Collections.synchronizedSortedMap(sortedMap);
        Intrinsics.checkExpressionValueIsNotNull(sortedMap2, "Collections.synchronizedSortedMap(this)");
        return sortedMap2;
    }

    @NotNull
    public static final <K, V> Map<K, V> synchronized(@NotNull Map<K, V> map2) {
        Intrinsics.checkParameterIsNotNull(map2, "$this$synchronized");
        Map<K, V> map3 = Collections.synchronizedMap(map2);
        Intrinsics.checkExpressionValueIsNotNull(map3, "Collections.synchronizedMap(this)");
        return map3;
    }

    @NotNull
    public static final <K, V> NavigableMap<K, V> synchronized(@NotNull NavigableMap<K, V> navigableMap) {
        Intrinsics.checkParameterIsNotNull(navigableMap, "$this$synchronized");
        NavigableMap<K, V> navigableMap2 = Collections.synchronizedNavigableMap(navigableMap);
        Intrinsics.checkExpressionValueIsNotNull(navigableMap2, "Collections.synchronizedNavigableMap(this)");
        return navigableMap2;
    }

    @Nullable
    public static final <K, V> Map.Entry<K, V> firstEntryOrNull(@NotNull NavigableMap<K, V> navigableMap) {
        Intrinsics.checkParameterIsNotNull(navigableMap, "$this$firstEntryOrNull");
        return navigableMap.firstEntry();
    }

    @Nullable
    public static final <K, V> Map.Entry<K, V> lastEntryOrNull(@NotNull NavigableMap<K, V> navigableMap) {
        Intrinsics.checkParameterIsNotNull(navigableMap, "$this$lastEntryOrNull");
        return navigableMap.lastEntry();
    }
}

