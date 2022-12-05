/*
 * Decompiled with CFR 0.150.
 */
package me.zero.alpine.fork.bus.type;

import java.util.ArrayList;
import java.util.List;
import me.zero.alpine.fork.bus.EventBus;
import me.zero.alpine.fork.bus.EventManager;
import me.zero.alpine.fork.bus.type.AttachableEventBus;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;

public class AttachableEventManager
extends EventManager
implements AttachableEventBus {
    private final /* synthetic */ List<EventBus> attached;

    @Override
    public void unsubscribe(Listenable listenable) {
        super.unsubscribe(listenable);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.unsubscribe(listenable));
        }
    }

    @Override
    public void subscribe(Listenable listenable) {
        super.subscribe(listenable);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.subscribe(listenable));
        }
    }

    @Override
    public void attach(EventBus eventBus) {
        if (!this.attached.contains(eventBus)) {
            this.attached.add(eventBus);
        }
    }

    public AttachableEventManager() {
        this.attached = new ArrayList<EventBus>();
    }

    @Override
    public void post(Object object) {
        super.post(object);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.post(object));
        }
    }

    @Override
    public void detach(EventBus eventBus) {
        this.attached.remove(eventBus);
    }

    @Override
    public void subscribe(Listener listener) {
        super.subscribe(listener);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.subscribe(listener));
        }
    }

    @Override
    public void unsubscribe(Listener listener) {
        super.unsubscribe(listener);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.unsubscribe(listener));
        }
    }
}

