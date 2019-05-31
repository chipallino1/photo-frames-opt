package com.ramkiopt.main.services.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface ReflectionUtilsService {
    default <T> T createNewInstance(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        return tClass.getConstructor().newInstance();
    }

    default Object invokeMethod(String methodName, Class tClass, Object target, Class... parameterTypes) throws
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method classMethod = tClass.getMethod(methodName, parameterTypes);
        return classMethod.invoke(target, parameterTypes);
    }
}
