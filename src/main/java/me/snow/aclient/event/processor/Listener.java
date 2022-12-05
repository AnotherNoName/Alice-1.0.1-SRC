/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.processor;

import java.lang.reflect.Method;
import me.snow.aclient.event.processor.EventPriority;
import org.jetbrains.annotations.NotNull;

public final class Listener {
    public final /* synthetic */ Object object;
    public final /* synthetic */ Method method;
    public final /* synthetic */ Class<?> event;
    public final /* synthetic */ EventPriority priority;

    public Listener(@NotNull Method method, @NotNull Object object, @NotNull Class<?> class_, @NotNull EventPriority eventPriority) {
        this.method = method;
        this.object = object;
        this.event = class_;
        this.priority = eventPriority;
    }
}

