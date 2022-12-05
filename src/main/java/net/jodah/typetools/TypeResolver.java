/*
 * Decompiled with CFR 0.150.
 */
package net.jodah.typetools;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import net.jodah.typetools.ReifiedParameterizedType;
import sun.misc.Unsafe;

public final class TypeResolver {
    private static final Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> TYPE_VARIABLE_CACHE;
    private static volatile boolean CACHE_ENABLED;
    private static boolean RESOLVES_LAMBDAS;
    private static Object JAVA_LANG_ACCESS;
    private static Method GET_CONSTANT_POOL;
    private static Method GET_CONSTANT_POOL_SIZE;
    private static Method GET_CONSTANT_POOL_METHOD_AT;
    private static final Map<String, Method> OBJECT_METHODS;
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS;
    private static final Double JAVA_VERSION;

    private TypeResolver() {
    }

    public static void enableCache() {
        CACHE_ENABLED = true;
    }

    public static void disableCache() {
        TYPE_VARIABLE_CACHE.clear();
        CACHE_ENABLED = false;
    }

    public static <T, S extends T> Class<?> resolveRawArgument(Class<T> class_, Class<S> class_2) {
        return TypeResolver.resolveRawArgument(TypeResolver.resolveGenericType(class_, class_2), class_2);
    }

    public static Class<?> resolveRawArgument(Type type2, Class<?> class_) {
        Class<?>[] arrclass = TypeResolver.resolveRawArguments(type2, class_);
        if (arrclass == null) {
            return Unknown.class;
        }
        if (arrclass.length != 1) {
            throw new IllegalArgumentException("Expected 1 argument for generic type " + type2 + " but found " + arrclass.length);
        }
        return arrclass[0];
    }

    public static <T, S extends T> Class<?>[] resolveRawArguments(Class<T> class_, Class<S> class_2) {
        return TypeResolver.resolveRawArguments(TypeResolver.resolveGenericType(class_, class_2), class_2);
    }

    public static <T, S extends T> Type reify(Class<T> class_, Class<S> class_2) {
        return TypeResolver.reify(TypeResolver.resolveGenericType(class_, class_2), TypeResolver.getTypeVariableMap(class_2, null));
    }

    public static Type reify(Type type2, Class<?> class_) {
        return TypeResolver.reify(type2, TypeResolver.getTypeVariableMap(class_, null));
    }

    public static Type reify(Type type2) {
        return TypeResolver.reify(type2, new HashMap(0));
    }

    public static Class<?>[] resolveRawArguments(Type type2, Class<?> class_) {
        Class[] arrclass;
        block5: {
            TypeVariable<Class<T>>[] arrtypeVariable;
            TypeVariable<Class<T>>[] arrtypeVariable2;
            block6: {
                block4: {
                    arrclass = null;
                    arrtypeVariable2 = null;
                    if (RESOLVES_LAMBDAS && class_.isSynthetic()) {
                        Object object = type2 instanceof ParameterizedType && ((ParameterizedType)type2).getRawType() instanceof Class ? (Class)((ParameterizedType)type2).getRawType() : (arrtypeVariable = type2 instanceof Class ? (TypeVariable<Class<T>>[])type2 : null);
                        if (arrtypeVariable != null && arrtypeVariable.isInterface()) {
                            arrtypeVariable2 = arrtypeVariable;
                        }
                    }
                    if (!(type2 instanceof ParameterizedType)) break block4;
                    arrtypeVariable = (ParameterizedType)type2;
                    Type[] arrtype = arrtypeVariable.getActualTypeArguments();
                    arrclass = new Class[arrtype.length];
                    for (int i = 0; i < arrtype.length; ++i) {
                        arrclass[i] = TypeResolver.resolveRawClass(arrtype[i], class_, arrtypeVariable2);
                    }
                    break block5;
                }
                if (!(type2 instanceof TypeVariable)) break block6;
                arrclass = new Class[]{TypeResolver.resolveRawClass(type2, class_, arrtypeVariable2)};
                break block5;
            }
            if (!(type2 instanceof Class)) break block5;
            arrtypeVariable = ((Class)type2).getTypeParameters();
            arrclass = new Class[arrtypeVariable.length];
            for (int i = 0; i < arrtypeVariable.length; ++i) {
                arrclass[i] = TypeResolver.resolveRawClass(arrtypeVariable[i], class_, arrtypeVariable2);
            }
        }
        return arrclass;
    }

    public static Type resolveGenericType(Class<?> class_, Type type2) {
        Type type3;
        Object object;
        Class class_2 = type2 instanceof ParameterizedType ? (Class)((ParameterizedType)type2).getRawType() : (Class)type2;
        if (class_.equals(class_2)) {
            return type2;
        }
        if (class_.isInterface()) {
            object = class_2.getGenericInterfaces();
            int n = ((Type[])object).length;
            for (int i = 0; i < n; ++i) {
                Object object2 = object[i];
                if (object2 == null || object2.equals(Object.class) || (type3 = TypeResolver.resolveGenericType(class_, (Type)object2)) == null) continue;
                return type3;
            }
        }
        if ((object = class_2.getGenericSuperclass()) != null && !object.equals(Object.class) && (type3 = TypeResolver.resolveGenericType(class_, (Type)object)) != null) {
            return type3;
        }
        return null;
    }

    public static Class<?> resolveRawClass(Type type2, Class<?> class_) {
        return TypeResolver.resolveRawClass(type2, class_, null);
    }

    private static Class<?> resolveRawClass(Type type2, Class<?> class_, Class<?> class_2) {
        if (type2 instanceof Class) {
            return (Class)type2;
        }
        if (type2 instanceof ParameterizedType) {
            return TypeResolver.resolveRawClass(((ParameterizedType)type2).getRawType(), class_, class_2);
        }
        if (type2 instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType)type2;
            Class<?> class_3 = TypeResolver.resolveRawClass(genericArrayType.getGenericComponentType(), class_, class_2);
            return Array.newInstance(class_3, 0).getClass();
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable)type2;
            type2 = TypeResolver.getTypeVariableMap(class_, class_2).get(typeVariable);
            type2 = type2 == null ? TypeResolver.resolveBound(typeVariable) : TypeResolver.resolveRawClass(type2, class_, class_2);
        }
        return type2 instanceof Class ? (Class)type2 : Unknown.class;
    }

    private static Type reify(Type type2, Map<TypeVariable<?>, Type> map2) {
        if (type2 == null) {
            return null;
        }
        if (type2 instanceof Class) {
            return type2;
        }
        return TypeResolver.reify(type2, map2, new HashMap<ParameterizedType, ReifiedParameterizedType>());
    }

    private static Type reify(Type type2, Map<TypeVariable<?>, Type> map2, Map<ParameterizedType, ReifiedParameterizedType> map3) {
        if (type2 instanceof Class) {
            return type2;
        }
        if (type2 instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)type2;
            if (map3.containsKey(parameterizedType)) {
                ReifiedParameterizedType reifiedParameterizedType = map3.get(type2);
                reifiedParameterizedType.addReifiedTypeArgument(reifiedParameterizedType);
                return reifiedParameterizedType;
            }
            Type[] arrtype = parameterizedType.getActualTypeArguments();
            ReifiedParameterizedType reifiedParameterizedType = new ReifiedParameterizedType(parameterizedType);
            map3.put(parameterizedType, reifiedParameterizedType);
            for (Type type3 : arrtype) {
                Type type4 = TypeResolver.reify(type3, map2, map3);
                if (type4 == reifiedParameterizedType) continue;
                reifiedParameterizedType.addReifiedTypeArgument(type4);
            }
            return reifiedParameterizedType;
        }
        if (type2 instanceof GenericArrayType) {
            Type type5;
            GenericArrayType genericArrayType = (GenericArrayType)type2;
            Type type6 = genericArrayType.getGenericComponentType();
            if (type6 == (type5 = TypeResolver.reify(genericArrayType.getGenericComponentType(), map2, map3))) {
                return type6;
            }
            if (type5 instanceof Class) {
                return Array.newInstance((Class)type5, 0).getClass();
            }
            throw new UnsupportedOperationException("Attempted to reify generic array type, whose generic component type could not be reified to some Class<?>. Handling for this case is not implemented");
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable)type2;
            Type type7 = map2.get(typeVariable);
            if (type7 != null) {
                return TypeResolver.reify(type7, map2, map3);
            }
            return TypeResolver.reify(typeVariable.getBounds()[0], map2, map3);
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType)type2;
            Type[] arrtype = wildcardType.getUpperBounds();
            Type[] arrtype2 = wildcardType.getLowerBounds();
            if (arrtype.length == 1 && arrtype2.length == 0) {
                return TypeResolver.reify(arrtype[0], map2, map3);
            }
            throw new UnsupportedOperationException("Attempted to reify wildcard type with name '" + wildcardType.getTypeName() + "' which has " + arrtype.length + " upper bounds and " + arrtype2.length + " lower bounds. Reification of wildcard types is only supported for the trivial case of exactly 1 upper bound and 0 lower bounds.");
        }
        throw new UnsupportedOperationException("Reification of type with name '" + type2.getTypeName() + "' and class name '" + type2.getClass().getName() + "' is not implemented.");
    }

    private static Map<TypeVariable<?>, Type> getTypeVariableMap(Class<?> class_, Class<?> class_2) {
        Map<TypeVariable<?>, Type> map2;
        Reference<Map<TypeVariable<?>, Type>> reference = TYPE_VARIABLE_CACHE.get(class_);
        Map<TypeVariable<?>, Type> map3 = map2 = reference != null ? reference.get() : null;
        if (map2 == null) {
            Class<?> class_3;
            map2 = new HashMap();
            if (class_2 != null) {
                TypeResolver.populateLambdaArgs(class_2, class_, map2);
            }
            TypeResolver.populateSuperTypeArgs(class_.getGenericInterfaces(), map2, class_2 != null);
            Type type2 = class_.getGenericSuperclass();
            for (class_3 = class_.getSuperclass(); class_3 != null && !Object.class.equals(class_3); class_3 = class_3.getSuperclass()) {
                if (type2 instanceof ParameterizedType) {
                    TypeResolver.populateTypeArgs((ParameterizedType)type2, map2, false);
                }
                TypeResolver.populateSuperTypeArgs(class_3.getGenericInterfaces(), map2, false);
                type2 = class_3.getGenericSuperclass();
            }
            class_3 = class_;
            while (class_3.isMemberClass()) {
                type2 = class_3.getGenericSuperclass();
                if (type2 instanceof ParameterizedType) {
                    TypeResolver.populateTypeArgs((ParameterizedType)type2, map2, class_2 != null);
                }
                class_3 = class_3.getEnclosingClass();
            }
            if (CACHE_ENABLED) {
                TYPE_VARIABLE_CACHE.put(class_, new WeakReference(map2));
            }
        }
        return map2;
    }

    private static void populateSuperTypeArgs(Type[] arrtype, Map<TypeVariable<?>, Type> map2, boolean bl) {
        for (Type type2 : arrtype) {
            if (type2 instanceof ParameterizedType) {
                Type type3;
                ParameterizedType parameterizedType = (ParameterizedType)type2;
                if (!bl) {
                    TypeResolver.populateTypeArgs(parameterizedType, map2, bl);
                }
                if ((type3 = parameterizedType.getRawType()) instanceof Class) {
                    TypeResolver.populateSuperTypeArgs(((Class)type3).getGenericInterfaces(), map2, bl);
                }
                if (!bl) continue;
                TypeResolver.populateTypeArgs(parameterizedType, map2, bl);
                continue;
            }
            if (!(type2 instanceof Class)) continue;
            TypeResolver.populateSuperTypeArgs(((Class)type2).getGenericInterfaces(), map2, bl);
        }
    }

    private static void populateTypeArgs(ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> map2, boolean bl) {
        if (parameterizedType.getRawType() instanceof Class) {
            Type type2;
            TypeVariable<Class<T>>[] arrtypeVariable = ((Class)parameterizedType.getRawType()).getTypeParameters();
            Type[] arrtype = parameterizedType.getActualTypeArguments();
            if (parameterizedType.getOwnerType() != null && (type2 = parameterizedType.getOwnerType()) instanceof ParameterizedType) {
                TypeResolver.populateTypeArgs((ParameterizedType)type2, map2, bl);
            }
            for (int i = 0; i < arrtype.length; ++i) {
                Type type3;
                TypeVariable typeVariable = arrtypeVariable[i];
                Type type4 = arrtype[i];
                if (type4 instanceof Class) {
                    map2.put(typeVariable, type4);
                    continue;
                }
                if (type4 instanceof GenericArrayType) {
                    map2.put(typeVariable, type4);
                    continue;
                }
                if (type4 instanceof ParameterizedType) {
                    map2.put(typeVariable, type4);
                    continue;
                }
                if (!(type4 instanceof TypeVariable)) continue;
                TypeVariable typeVariable2 = (TypeVariable)type4;
                if (bl && (type3 = map2.get(typeVariable)) != null) {
                    map2.put(typeVariable2, type3);
                    continue;
                }
                type3 = map2.get(typeVariable2);
                if (type3 == null) {
                    type3 = TypeResolver.resolveBound(typeVariable2);
                }
                map2.put(typeVariable, type3);
            }
        }
    }

    public static Type resolveBound(TypeVariable<?> typeVariable) {
        Type[] arrtype = typeVariable.getBounds();
        if (arrtype.length == 0) {
            return Unknown.class;
        }
        Type type2 = arrtype[0];
        if (type2 instanceof TypeVariable) {
            type2 = TypeResolver.resolveBound((TypeVariable)type2);
        }
        return type2 == Object.class ? Unknown.class : type2;
    }

    private static void populateLambdaArgs(Class<?> class_, Class<?> class_2, Map<TypeVariable<?>, Type> map2) {
        if (RESOLVES_LAMBDAS) {
            for (Method method : class_.getMethods()) {
                Object object;
                Method method2;
                if (TypeResolver.isDefaultMethod(method) || Modifier.isStatic(method.getModifiers()) || method.isBridge() || (method2 = OBJECT_METHODS.get(method.getName())) != null && Arrays.equals(method.getTypeParameters(), method2.getTypeParameters())) continue;
                Type type2 = method.getGenericReturnType();
                Type[] arrtype = method.getGenericParameterTypes();
                Member member = TypeResolver.getMemberRef(class_2);
                if (member == null) {
                    return;
                }
                if (type2 instanceof TypeVariable) {
                    object = member instanceof Method ? ((Method)member).getReturnType() : ((Constructor)member).getDeclaringClass();
                    if (!(object = TypeResolver.wrapPrimitives(object)).equals(Void.class)) {
                        map2.put((TypeVariable)type2, (Type)object);
                    }
                }
                object = member instanceof Method ? ((Method)member).getParameterTypes() : ((Constructor)member).getParameterTypes();
                int n = 0;
                if (arrtype.length > 0 && arrtype[0] instanceof TypeVariable && arrtype.length == ((Object)object).length + 1) {
                    Class<?> class_3 = member.getDeclaringClass();
                    map2.put((TypeVariable)arrtype[0], class_3);
                    n = 1;
                }
                int n2 = 0;
                if (arrtype.length < ((Object)object).length) {
                    n2 = ((Object)object).length - arrtype.length;
                }
                int n3 = 0;
                while (n3 + n2 < ((Object)object).length) {
                    if (arrtype[n3] instanceof TypeVariable) {
                        map2.put((TypeVariable)arrtype[n3 + n], TypeResolver.wrapPrimitives(object[n3 + n2]));
                    }
                    ++n3;
                }
                return;
            }
        }
    }

    private static boolean isDefaultMethod(Method method) {
        return JAVA_VERSION >= 1.8 && method.isDefault();
    }

    private static Member getMemberRef(Class<?> class_) {
        Object object;
        try {
            object = GET_CONSTANT_POOL.invoke(JAVA_LANG_ACCESS, class_);
        }
        catch (Exception exception) {
            return null;
        }
        Member member = null;
        for (int i = TypeResolver.getConstantPoolSize(object) - 1; i >= 0; --i) {
            Member member2 = TypeResolver.getConstantPoolMethodAt(object, i);
            if (member2 == null || member2 instanceof Constructor && member2.getDeclaringClass().getName().equals("java.lang.invoke.SerializedLambda") || member2.getDeclaringClass().isAssignableFrom(class_)) continue;
            member = member2;
            if (!(member2 instanceof Method) || !TypeResolver.isAutoBoxingMethod((Method)member2)) break;
        }
        return member;
    }

    private static boolean isAutoBoxingMethod(Method method) {
        Class<?>[] arrclass = method.getParameterTypes();
        return method.getName().equals("valueOf") && arrclass.length == 1 && arrclass[0].isPrimitive() && TypeResolver.wrapPrimitives(arrclass[0]).equals(method.getDeclaringClass());
    }

    private static Class<?> wrapPrimitives(Class<?> class_) {
        return class_.isPrimitive() ? PRIMITIVE_WRAPPERS.get(class_) : class_;
    }

    private static int getConstantPoolSize(Object object) {
        try {
            return (Integer)GET_CONSTANT_POOL_SIZE.invoke(object, new Object[0]);
        }
        catch (Exception exception) {
            return 0;
        }
    }

    private static Member getConstantPoolMethodAt(Object object, int n) {
        try {
            return (Member)GET_CONSTANT_POOL_METHOD_AT.invoke(object, n);
        }
        catch (Exception exception) {
            return null;
        }
    }

    static {
        Object object;
        TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap());
        CACHE_ENABLED = true;
        OBJECT_METHODS = new HashMap<String, Method>();
        JAVA_VERSION = Double.parseDouble(System.getProperty("java.specification.version", "0"));
        try {
            Object object2;
            long l;
            AccessibleObject accessibleObject;
            AccessMaker accessMaker;
            Class<?> class_;
            object = AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>(){

                @Override
                public Unsafe run() throws Exception {
                    Field field = Unsafe.class.getDeclaredField("theUnsafe");
                    field.setAccessible(true);
                    return (Unsafe)field.get(null);
                }
            });
            if (JAVA_VERSION < 9.0) {
                class_ = Class.forName("sun.misc.SharedSecrets");
                accessMaker = new AccessMaker(){

                    @Override
                    public void makeAccessible(AccessibleObject accessibleObject) {
                        accessibleObject.setAccessible(true);
                    }
                };
            } else if (JAVA_VERSION < 12.0) {
                try {
                    class_ = Class.forName("jdk.internal.misc.SharedSecrets");
                }
                catch (ClassNotFoundException classNotFoundException) {
                    class_ = Class.forName("jdk.internal.access.SharedSecrets");
                }
                accessibleObject = AccessibleObject.class.getDeclaredField("override");
                l = ((Unsafe)object).objectFieldOffset((Field)accessibleObject);
                accessMaker = new AccessMaker((Unsafe)object, l){
                    final /* synthetic */ Unsafe val$unsafe;
                    final /* synthetic */ long val$overrideFieldOffset;
                    {
                        this.val$unsafe = unsafe;
                        this.val$overrideFieldOffset = l;
                    }

                    @Override
                    public void makeAccessible(AccessibleObject accessibleObject) {
                        this.val$unsafe.putBoolean(accessibleObject, this.val$overrideFieldOffset, true);
                    }
                };
            } else {
                class_ = Class.forName("jdk.internal.access.SharedSecrets");
                accessibleObject = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                l = ((Unsafe)object).staticFieldOffset((Field)accessibleObject);
                object2 = ((Unsafe)object).staticFieldBase((Field)accessibleObject);
                Method[] arrmethod = (Method[])((Unsafe)object).getObject(object2, l);
                final MethodHandle methodHandle = arrmethod.findSetter(AccessibleObject.class, "override", Boolean.TYPE);
                accessMaker = new AccessMaker(){

                    @Override
                    public void makeAccessible(AccessibleObject accessibleObject) throws Throwable {
                        methodHandle.invokeWithArguments(accessibleObject, true);
                    }
                };
            }
            accessibleObject = class_.getMethod("getJavaLangAccess", new Class[0]);
            accessMaker.makeAccessible(accessibleObject);
            JAVA_LANG_ACCESS = ((Method)accessibleObject).invoke(null, new Object[0]);
            GET_CONSTANT_POOL = JAVA_LANG_ACCESS.getClass().getMethod("getConstantPool", Class.class);
            String string = JAVA_VERSION < 9.0 ? "sun.reflect.ConstantPool" : "jdk.internal.reflect.ConstantPool";
            Class<?> class_2 = Class.forName(string);
            GET_CONSTANT_POOL_SIZE = class_2.getDeclaredMethod("getSize", new Class[0]);
            GET_CONSTANT_POOL_METHOD_AT = class_2.getDeclaredMethod("getMethodAt", Integer.TYPE);
            accessMaker.makeAccessible(GET_CONSTANT_POOL);
            accessMaker.makeAccessible(GET_CONSTANT_POOL_SIZE);
            accessMaker.makeAccessible(GET_CONSTANT_POOL_METHOD_AT);
            object2 = GET_CONSTANT_POOL.invoke(JAVA_LANG_ACCESS, Object.class);
            GET_CONSTANT_POOL_SIZE.invoke(object2, new Object[0]);
            for (Method method : Object.class.getDeclaredMethods()) {
                OBJECT_METHODS.put(method.getName(), method);
            }
            RESOLVES_LAMBDAS = true;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        object = new HashMap();
        object.put(Boolean.TYPE, Boolean.class);
        object.put(Byte.TYPE, Byte.class);
        object.put(Character.TYPE, Character.class);
        object.put(Double.TYPE, Double.class);
        object.put(Float.TYPE, Float.class);
        object.put(Integer.TYPE, Integer.class);
        object.put(Long.TYPE, Long.class);
        object.put(Short.TYPE, Short.class);
        object.put(Void.TYPE, Void.class);
        PRIMITIVE_WRAPPERS = Collections.unmodifiableMap(object);
    }

    public static final class Unknown {
        private Unknown() {
        }
    }

    private static interface AccessMaker {
        public void makeAccessible(AccessibleObject var1) throws Throwable;
    }
}

