package com.ramkiopt.main.services.app.base;

import java.util.List;

public interface BaseCrudService<T> {
    T create(T dto);

    List<T> createAll(Iterable<T> dtos);

    T get(Long id);

    T update(Long id, T dto);

    Boolean delete(Long id);
}
