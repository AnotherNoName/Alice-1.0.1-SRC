/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.bus;

import java.util.Arrays;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public interface EventBus {
    public void unsubscribe(Listenable var1);

    default public void subscribeAll(Listener ... arrlistener) {
        Arrays.stream(arrlistener).forEach(this::subscribe);
    }

    default public void unsubscribeAll(Listenable ... arrlistenable) {
        Arrays.stream(arrlistenable).forEach(this::unsubscribe);
    }

    public void post(Object var1);

    default public void unsubscribeAll(Listener ... arrlistener) {
        Arrays.stream(arrlistener).forEach(this::unsubscribe);
    }

    public void unsubscribe(Listener var1);

    default public void subscribeAll(Listenable ... arrlistenable) {
        Arrays.stream(arrlistenable).forEach(this::subscribe);
    }

    default public void subscribeAll(Iterable<Listenable> iterable) {
        iterable.forEach(this::subscribe);
    }

    public void subscribe(Listenable var1);

    default public void unsubscribeAll(Iterable<Listenable> iterable) {
        iterable.forEach(this::unsubscribe);
    }

    public void subscribe(Listener var1);
}

