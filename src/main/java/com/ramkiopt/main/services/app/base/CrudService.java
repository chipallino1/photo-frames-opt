package com.ramkiopt.main.services.app.base;

public interface CrudService<T, V> {
    V createInDb(T t, V v);

    V updateInDb(Long id, V v);

    V readFromDb(Long id, V v);

    boolean deleteInDb(Long id);
}
