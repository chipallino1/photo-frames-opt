package com.ramkiopt.main.services.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtilsService {
    public static <T> T createNewInstance(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return tClass.getConstructor().newInstance();
    }

    public static Object invokeMethod(String methodName, Class tClass, Object target, Class... parameterTypes) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method classMethod = tClass.getMethod(methodName, parameterTypes);
        return classMethod.invoke(target, parameterTypes);
    }

    public static <T> void setField(Class<T> tClass, String fieldName, Object obj, Object value) {
        try {
            Field field = tClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
