/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Converter
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package me.snow.aclient.core.setting;

import com.google.common.base.Converter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class EnumConverter
extends Converter<Enum, JsonElement> {
    private final /* synthetic */ Class<? extends Enum> clazz;

    public static int currentEnum(Enum enum_) {
        for (int i = 0; i < ((Enum[])enum_.getClass().getEnumConstants()).length; ++i) {
            Enum enum_2 = ((Enum[])enum_.getClass().getEnumConstants())[i];
            if (!enum_2.name().equalsIgnoreCase(enum_.name())) continue;
            return i;
        }
        return -1;
    }

    public EnumConverter(Class<? extends Enum> class_) {
        this.clazz = class_;
    }

    public static String getProperName(Enum enum_) {
        return String.valueOf(new StringBuilder().append(Character.toUpperCase(enum_.name().charAt(0))).append(enum_.name().toLowerCase().substring(1)));
    }

    public Enum doBackward(JsonElement jsonElement) {
        try {
            return Enum.valueOf(this.clazz, jsonElement.getAsString());
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    public static Enum increaseEnum(Enum enum_) {
        int n = EnumConverter.currentEnum(enum_);
        for (int i = 0; i < ((Enum[])enum_.getClass().getEnumConstants()).length; ++i) {
            Enum enum_2 = ((Enum[])enum_.getClass().getEnumConstants())[i];
            if (i != n + 1) continue;
            return enum_2;
        }
        return ((Enum[])enum_.getClass().getEnumConstants())[0];
    }

    public JsonElement doForward(Enum enum_) {
        return new JsonPrimitive(enum_.toString());
    }
}

