/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.kt.extension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=2, d1={"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010#\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H\u0086\b\u00a2\u0006\u0002\u0010\u0006\u001a!\u0010\u0007\u001a\u00020\b*\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u000bH\u0086\b\u001a-\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\b0\u000bH\u0086\b\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0010H\u0086\b\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0011H\u0086\b\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0086\b\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0012H\u0086\b\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0013\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0013H\u0086\b\u00a8\u0006\u0014"}, d2={"add", "", "E", "", "", "e", "(Ljava/util/Collection;Ljava/lang/Object;)V", "sumOfFloat", "", "", "selector", "Lkotlin/Function1;", "", "T", "", "synchronized", "Ljava/util/NavigableSet;", "Ljava/util/SortedSet;", "", "", "Alice"})
public final class CollectionKt {
    @NotNull
    public static final <E> List<E> synchronized(@NotNull List<E> list) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(list, "$this$synchronized");
        List<E> list2 = Collections.synchronizedList(list);
        Intrinsics.checkExpressionValueIsNotNull(list2, "Collections.synchronizedList(this)");
        return list2;
    }

    @NotNull
    public static final <E> Collection<E> synchronized(@NotNull Collection<E> collection) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(collection, "$this$synchronized");
        Collection<E> collection2 = Collections.synchronizedCollection(collection);
        Intrinsics.checkExpressionValueIsNotNull(collection2, "Collections.synchronizedCollection(this)");
        return collection2;
    }

    public static final <E> void add(@NotNull Collection<E> collection, @Nullable E e) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(collection, "$this$add");
        if (e != null) {
            collection.add(e);
        }
    }

    public static final float sumOfFloat(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Float> function1) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(charSequence, "$this$sumOfFloat");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        float f = 0.0f;
        CharSequence charSequence2 = charSequence;
        for (int i = 0; i < charSequence2.length(); ++i) {
            char c = charSequence2.charAt(i);
            f += ((Number)function1.invoke(Character.valueOf(c))).floatValue();
        }
        return f;
    }

    @NotNull
    public static final <E> SortedSet<E> synchronized(@NotNull SortedSet<E> sortedSet) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(sortedSet, "$this$synchronized");
        SortedSet<E> sortedSet2 = Collections.synchronizedSortedSet(sortedSet);
        Intrinsics.checkExpressionValueIsNotNull(sortedSet2, "Collections.synchronizedSortedSet(this)");
        return sortedSet2;
    }

    public static final <T> float sumOfFloat(@NotNull Iterable<? extends T> iterable, @NotNull Function1<? super T, Float> function1) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(iterable, "$this$sumOfFloat");
        Intrinsics.checkParameterIsNotNull(function1, "selector");
        float f = 0.0f;
        for (T t : iterable) {
            f += ((Number)function1.invoke(t)).floatValue();
        }
        return f;
    }

    @NotNull
    public static final <E> NavigableSet<E> synchronized(@NotNull NavigableSet<E> navigableSet) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(navigableSet, "$this$synchronized");
        NavigableSet<E> navigableSet2 = Collections.synchronizedNavigableSet(navigableSet);
        Intrinsics.checkExpressionValueIsNotNull(navigableSet2, "Collections.synchronizedNavigableSet(this)");
        return navigableSet2;
    }

    @NotNull
    public static final <E> Set<E> synchronized(@NotNull Set<E> set) {
        boolean bl = false;
        Intrinsics.checkParameterIsNotNull(set, "$this$synchronized");
        Set<E> set2 = Collections.synchronizedSet(set);
        Intrinsics.checkExpressionValueIsNotNull(set2, "Collections.synchronizedSet(this)");
        return set2;
    }
}

