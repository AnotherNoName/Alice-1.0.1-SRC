/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.listener;

import java.util.function.Predicate;
import me.zero.alpine.fork.listener.EventHook;
import me.zero.alpine.fork.listener.Listener;

public class MethodRefListener<T>
extends Listener<T> {
    private /* synthetic */ Class<T> target;

    @SafeVarargs
    public MethodRefListener(Class<T> class_, EventHook<T> eventHook, int n, Predicate<T> ... arrpredicate) {
        super(eventHook, n, arrpredicate);
        this.target = class_;
    }

    @Override
    public Class<T> getTarget() {
        return this.target;
    }

    @SafeVarargs
    public MethodRefListener(Class<T> class_, EventHook<T> eventHook, Predicate<T> ... arrpredicate) {
        super(eventHook, arrpredicate);
        this.target = class_;
    }
}

