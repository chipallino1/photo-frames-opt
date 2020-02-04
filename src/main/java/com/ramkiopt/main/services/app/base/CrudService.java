package com.ramkiopt.main.services.app.base;

import java.util.List;

public interface CrudService<T, V> {
    V createInDb(T t, V v);

    List<V> createAllInDb(List<V> vs, Class<T> entityClass, Class<V> dtoClass);

    V updateInDb(Long id, V v);

    V readFromDb(Long id, V v);

    boolean deleteInDb(Long id);
}
