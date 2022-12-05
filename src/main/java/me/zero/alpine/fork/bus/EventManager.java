/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.bus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class EventManager
implements EventBus {
    private final /* synthetic */ Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP;
    private final /* synthetic */ Map<Listenable, List<Listener>> SUBSCRIPTION_CACHE;

    @Override
    public void unsubscribe(Listenable listenable) {
        List<Listener> list = this.SUBSCRIPTION_CACHE.get(listenable);
        if (list == null) {
            return;
        }
        this.SUBSCRIPTION_MAP.values().forEach(list2 -> list2.removeIf(list::contains));
    }

    private static Listener asListener(Listenable listenable, Field field) {
        try {
            boolean bl = field.isAccessible();
            field.setAccessible(true);
            Listener listener = (Listener)field.get(listenable);
            field.setAccessible(bl);
            if (listener == null) {
                return null;
            }
            return listener;
        }
        catch (IllegalAccessException illegalAccessException) {
            return null;
        }
    }

    @Override
    public void unsubscribe(Listener listener) {
        this.SUBSCRIPTION_MAP.get(listener.getTarget()).removeIf(listener2 -> listener2.equals(listener));
    }

    @Override
    public void subscribe(Listener listener) {
        int n;
        List list = this.SUBSCRIPTION_MAP.computeIfAbsent(listener.getTarget(), class_ -> new CopyOnWriteArrayList());
        for (n = 0; n < list.size() && listener.getPriority() <= ((Listener)list.get(n)).getPriority(); ++n) {
        }
        list.add(n, listener);
    }

    @Override
    public void post(Object object) {
        List<Listener> list = this.SUBSCRIPTION_MAP.get(object.getClass());
        if (list != null) {
            list.forEach(listener -> listener.invoke(object));
        }
    }

    @Override
    public void subscribe(Listenable listenable2) {
        List list = this.SUBSCRIPTION_CACHE.computeIfAbsent(listenable2, listenable -> Arrays.stream(listenable.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(field -> EventManager.asListener(listenable, field)).filter(Objects::nonNull).collect(Collectors.toList()));
        list.forEach(this::subscribe);
    }

    public EventManager() {
        this.SUBSCRIPTION_CACHE = new ConcurrentHashMap<Listenable, List<Listener>>();
        this.SUBSCRIPTION_MAP = new ConcurrentHashMap();
    }

    private static boolean isValidField(Field field) {
        return field.isAnnotationPresent(EventHandler.class) && Listener.class.isAssignableFrom(field.getType());
    }
}

