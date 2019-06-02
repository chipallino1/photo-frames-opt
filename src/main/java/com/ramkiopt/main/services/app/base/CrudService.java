package com.ramkiopt.main.services.app.base;

public interface CrudService<T, Id> {
    T createInDb(T t);

    T updateInDb(Id id, T t);

    T readInDb(Id id);

    void deleteInDb(Id id);
}
