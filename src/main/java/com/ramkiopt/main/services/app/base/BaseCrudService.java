package com.ramkiopt.main.services.app.base;

public interface BaseCrudService<T> {
    T create(T dto);

    T get(Long id);

    T update(Long id, T dto);

    Boolean delete(Long id);
}
