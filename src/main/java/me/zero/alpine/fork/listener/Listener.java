/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.listener;

import java.util.function.Predicate;
import me.zero.alpine.fork.listener.EventHook;
import net.jodah.typetools.TypeResolver;

public class Listener<T>
implements EventHook<T> {
    private final /* synthetic */ int priority;
    private final /* synthetic */ Predicate<T>[] filters;
    private final /* synthetic */ Class<T> target;
    private final /* synthetic */ EventHook<T> hook;

    @Override
    public void invoke(T t) {
        if (this.filters.length > 0) {
            for (Predicate<T> predicate : this.filters) {
                if (predicate.test(t)) continue;
                return;
            }
        }
        this.hook.invoke(t);
    }

    public Class<T> getTarget() {
        return this.target;
    }

    public int getPriority() {
        return this.priority;
    }

    @SafeVarargs
    public Listener(EventHook<T> eventHook, Predicate<T> ... arrpredicate) {
        this(eventHook, 0, arrpredicate);
    }

    @SafeVarargs
    public Listener(EventHook<T> eventHook, int n, Predicate<T> ... arrpredicate) {
        this.hook = eventHook;
        this.priority = n;
        this.target = TypeResolver.resolveRawArgument(EventHook.class, eventHook.getClass());
        this.filters = arrpredicate;
    }
}

