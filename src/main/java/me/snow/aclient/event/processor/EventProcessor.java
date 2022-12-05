/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.processor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import me.snow.aclient.event.processor.CommitEvent;
import me.snow.aclient.event.processor.Event;
import me.snow.aclient.event.processor.EventPriority;
import me.snow.aclient.event.processor.Listener;
import org.jetbrains.annotations.NotNull;

public final class EventProcessor {
    private final /* synthetic */ List<Listener> events;

    public final boolean postEvent(@NotNull Event event) {
        this.events.spliterator().forEachRemaining(listener -> {
            if (listener.event == event.getClass()) {
                listener.method.setAccessible(true);
                try {
                    listener.method.invoke(listener.object, event);
                }
                catch (IllegalAccessException | InvocationTargetException reflectiveOperationException) {
                    reflectiveOperationException.printStackTrace();
                }
            }
        });
        return true;
    }

    public final void removeEventListener(@NotNull Object object) {
        this.events.removeIf(listener -> listener.object == object);
    }

    private void getEvents(@NotNull Object object) {
        Class<?> class_ = object.getClass();
        Arrays.stream(class_.getDeclaredMethods()).spliterator().forEachRemaining(method -> {
            if (method.isAnnotationPresent(CommitEvent.class)) {
                Class<?>[] arrclass = method.getParameterTypes();
                if (arrclass.length != 1) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Method ").append(method).append(" doesnt have any event parameters")));
                }
                if (!Event.class.isAssignableFrom(arrclass[0])) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Method ").append(method).append(" doesnt have any event parameters only non event parameters")));
                }
                this.events.add(new Listener((Method)method, object, arrclass[0], this.getPriority((Method)method)));
                this.events.sort(Comparator.comparing(listener -> listener.priority));
            }
        });
    }

    public EventProcessor() {
        this.events = new ArrayList<Listener>();
    }

    private EventPriority getPriority(@NotNull Method method) {
        return method.getAnnotation(CommitEvent.class).priority();
    }

    public final void addEventListener(@NotNull Object object) throws IllegalArgumentException {
        this.getEvents(object);
    }
}

