/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.core.setting;

import java.util.function.Predicate;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.EnumConverter;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.module.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Setting<T> {
    private final /* synthetic */ T defaultValue;
    private /* synthetic */ Predicate<T> visibility;
    private final /* synthetic */ String name;
    private /* synthetic */ boolean hasRestriction;
    private /* synthetic */ T max;
    private /* synthetic */ T min;
    /* synthetic */ T value;
    private /* synthetic */ boolean shouldRenderStringName;
    private /* synthetic */ Feature feature;
    private /* synthetic */ T plannedValue;
    private /* synthetic */ String description;

    public <T> String getClassName(T t) {
        return t.getClass().getSimpleName();
    }

    public String getValueAsString() {
        return this.value.toString();
    }

    public void setMin(T t) {
        this.min = t;
    }

    public T getValue() {
        return this.value;
    }

    public void setEnumValue(String string) {
        for (Enum enum_ : (Enum[])((Enum)this.value).getClass().getEnumConstants()) {
            if (!enum_.name().equalsIgnoreCase(string)) continue;
            this.value = enum_;
        }
    }

    public void setVisibility(Predicate<T> predicate) {
        this.visibility = predicate;
    }

    public String getType() {
        if (this.isEnumSetting()) {
            return "Enum";
        }
        return this.getClassName(this.defaultValue);
    }

    public Setting(String string, T t, T t2, T t3) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.min = t2;
        this.max = t3;
        this.plannedValue = t;
        this.description = "";
        this.hasRestriction = true;
    }

    public boolean hasRestriction() {
        return this.hasRestriction;
    }

    public T getMax() {
        return this.max;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public void setValueNoEvent(T t) {
        this.setPlannedValue(t);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)t).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)t).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        this.value = this.plannedValue;
    }

    public String currentEnumName() {
        return EnumConverter.getProperName((Enum)this.value);
    }

    public boolean isVisible() {
        if (this.visibility == null) {
            return false;
        }
        return !this.visibility.test(this.getValue());
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public boolean isNumberSetting() {
        return this.value instanceof Double || this.value instanceof Integer || this.value instanceof Short || this.value instanceof Long || this.value instanceof Float;
    }

    public boolean isEnumSetting() {
        return !this.isNumberSetting() && !(this.value instanceof String) && !(this.value instanceof Bind) && !(this.value instanceof Character) && !(this.value instanceof Boolean);
    }

    public T getMin() {
        return this.min;
    }

    public boolean shouldRenderName() {
        if (!this.isStringSetting()) {
            return true;
        }
        return this.shouldRenderStringName;
    }

    public void increaseEnumNoEvent() {
        this.value = EnumConverter.increaseEnum((Enum)this.value);
    }

    public T getPlannedValue() {
        return this.plannedValue;
    }

    public int currentEnum() {
        return EnumConverter.currentEnum((Enum)this.value);
    }

    public void setPlannedValue(T t) {
        this.plannedValue = t;
    }

    public void setValue(T t) {
        this.setPlannedValue(t);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)t).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)t).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        } else {
            this.plannedValue = this.value;
        }
    }

    public Setting(String string, T t, T t2, T t3, Predicate<T> predicate) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.min = t2;
        this.max = t3;
        this.plannedValue = t;
        this.visibility = predicate;
        this.description = "";
        this.hasRestriction = true;
    }

    public Setting(String string, T t, Predicate<T> predicate) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.visibility = predicate;
        this.plannedValue = t;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public Setting(String string, T t, T t2, T t3, String string2) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.min = t2;
        this.max = t3;
        this.plannedValue = t;
        this.description = string2;
        this.hasRestriction = true;
    }

    public Setting(String string, T t) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.plannedValue = t;
        this.description = "";
    }

    public String getDescription() {
        if (this.description == null) {
            return "";
        }
        return this.description;
    }

    public boolean isStringSetting() {
        return this.value instanceof String;
    }

    public void setMax(T t) {
        this.max = t;
    }

    public String getName() {
        return this.name;
    }

    public Setting(String string, T t, T t2, T t3, Predicate<T> predicate, String string2) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.min = t2;
        this.max = t3;
        this.plannedValue = t;
        this.visibility = predicate;
        this.description = string2;
        this.hasRestriction = true;
    }

    public void increaseEnum() {
        this.plannedValue = EnumConverter.increaseEnum((Enum)this.value);
        ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        } else {
            this.plannedValue = this.value;
        }
    }

    public Setting<T> setRenderName(boolean bl) {
        this.shouldRenderStringName = bl;
        return this;
    }

    public int getEnum(String string) {
        for (int i = 0; i < this.value.getClass().getEnumConstants().length; ++i) {
            Enum enum_ = (Enum)this.value.getClass().getEnumConstants()[i];
            if (!enum_.name().equalsIgnoreCase(string)) continue;
            return i;
        }
        return -1;
    }

    public Setting(String string, T t, String string2) {
        this.name = string;
        this.defaultValue = t;
        this.value = t;
        this.plannedValue = t;
        this.description = string2;
    }
}

