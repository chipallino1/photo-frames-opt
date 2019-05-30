package com.ramkiopt.main.services.utils;

public interface CrudService<T, Id> {
    T create(T t);

    T update(Id id, T t);

    T read(Id id);

    void delete(Id id);
}
