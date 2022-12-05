/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.event.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import me.snow.aclient.event.processor.EventPriority;

@Target(value={ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface CommitEvent {
    public EventPriority priority() default EventPriority.NONE;
}

