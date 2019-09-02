package com.ramkiopt.main.services.utils;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntitiesGetterService<T, Id>{
    List<T> getAll();

    List<T> getAll(Pageable pageable);
}
