/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Objects;

public class ReflectionUtil {
    public static void makeMutable(Member member) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(member);
        Field field = Field.class.getDeclaredField("modifiers");
        ReflectionUtil.makePublic(field);
        field.setInt(member, member.getModifiers() & 0xFFFFFFEF);
    }

    public static boolean isStatic(Member member) {
        return (member.getModifiers() & 8) != 0;
    }

    public static void makePrivate(AccessibleObject accessibleObject) {
        ReflectionUtil.makeAccessible(accessibleObject, false);
    }

    public static void makePublic(AccessibleObject accessibleObject) {
        ReflectionUtil.makeAccessible(accessibleObject, true);
    }

    public static void makeAccessible(AccessibleObject accessibleObject, boolean bl) {
        Objects.requireNonNull(accessibleObject);
        accessibleObject.setAccessible(bl);
    }

    public static void makeImmutable(Member member) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(member);
        Field field = Field.class.getDeclaredField("modifiers");
        ReflectionUtil.makePublic(field);
        field.setInt(member, member.getModifiers() & 0x10);
    }

    public static <F, T extends F> void copyOf(F f, T t) throws IllegalAccessException, NoSuchFieldException {
        ReflectionUtil.copyOf(f, t, false);
    }

    public static <F, T extends F> void copyOf(F f, T t, boolean bl) throws IllegalAccessException, NoSuchFieldException {
        Objects.requireNonNull(f);
        Objects.requireNonNull(t);
        Class<?> class_ = f.getClass();
        for (Field field : class_.getDeclaredFields()) {
            ReflectionUtil.makePublic(field);
            if (ReflectionUtil.isStatic(field) || bl && ReflectionUtil.isFinal(field)) continue;
            ReflectionUtil.makeMutable(field);
            field.set(t, field.get(f));
        }
    }

    public static boolean isFinal(Member member) {
        return (member.getModifiers() & 0x10) != 0;
    }
}

