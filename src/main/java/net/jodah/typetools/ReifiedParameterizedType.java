/*
 * Decompiled with CFR 0.150.
 */
package net.jodah.typetools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ReifiedParameterizedType
implements ParameterizedType {
    private final ParameterizedType original;
    private final Type[] reifiedTypeArguments;
    private final boolean[] loop;
    private int reified = 0;

    ReifiedParameterizedType(ParameterizedType parameterizedType) {
        this.original = parameterizedType;
        this.reifiedTypeArguments = new Type[parameterizedType.getActualTypeArguments().length];
        this.loop = new boolean[parameterizedType.getActualTypeArguments().length];
    }

    void addReifiedTypeArgument(Type type2) {
        if (this.reified >= this.reifiedTypeArguments.length) {
            return;
        }
        if (type2 == this) {
            this.loop[this.reified] = true;
        }
        this.reifiedTypeArguments[this.reified++] = type2;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return this.reifiedTypeArguments;
    }

    @Override
    public Type getRawType() {
        return this.original.getRawType();
    }

    @Override
    public Type getOwnerType() {
        return this.original.getOwnerType();
    }

    public String toString() {
        Type type2 = this.getOwnerType();
        Type type3 = this.getRawType();
        Type[] arrtype = this.getActualTypeArguments();
        StringBuilder stringBuilder = new StringBuilder();
        if (type2 != null) {
            if (type2 instanceof Class) {
                stringBuilder.append(((Class)type2).getName());
            } else {
                stringBuilder.append(type2.toString());
            }
            stringBuilder.append("$");
            if (type2 instanceof ParameterizedType) {
                stringBuilder.append(type3.getTypeName().replace(((ParameterizedType)type2).getRawType().getTypeName() + "$", ""));
            } else if (type3 instanceof Class) {
                stringBuilder.append(((Class)type3).getSimpleName());
            } else {
                stringBuilder.append(type3.getTypeName());
            }
        } else {
            stringBuilder.append(type3.getTypeName());
        }
        if (arrtype != null && arrtype.length > 0) {
            stringBuilder.append("<");
            for (int i = 0; i < arrtype.length; ++i) {
                if (i != 0) {
                    stringBuilder.append(", ");
                }
                Type type4 = arrtype[i];
                if (i >= this.reified) {
                    stringBuilder.append("?");
                    continue;
                }
                if (type4 == null) {
                    stringBuilder.append("null");
                    continue;
                }
                if (this.loop[i]) {
                    stringBuilder.append("...");
                    continue;
                }
                stringBuilder.append(type4.getTypeName());
            }
            stringBuilder.append(">");
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ReifiedParameterizedType reifiedParameterizedType = (ReifiedParameterizedType)object;
        if (!this.original.equals(reifiedParameterizedType.original)) {
            return false;
        }
        if (this.reifiedTypeArguments.length != reifiedParameterizedType.reifiedTypeArguments.length) {
            return false;
        }
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (this.loop[i] != reifiedParameterizedType.loop[i]) {
                return false;
            }
            if (this.loop[i] || this.reifiedTypeArguments[i] == reifiedParameterizedType.reifiedTypeArguments[i]) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int n = this.original.hashCode();
        for (int i = 0; i < this.reifiedTypeArguments.length; ++i) {
            if (this.loop[i] || !(this.reifiedTypeArguments[i] instanceof ReifiedParameterizedType)) continue;
            n = 31 * n + this.reifiedTypeArguments[i].hashCode();
        }
        return n;
    }
}

