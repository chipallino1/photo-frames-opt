package com.ramkiopt.main.services.app.base;

import java.lang.reflect.InvocationTargetException;

public interface BaseCrudService<T> {
    Boolean create(T dto) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    T get(Long id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    Boolean update(Long id, T dto);

    Boolean delete(Long id);
}
