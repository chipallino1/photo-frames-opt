package com.ramkiopt.main.services.app.photoframes;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhotoFramesGetterService<T> {
    List<T> getAllByName(String name, Pageable pageable);
    List<T> getByMaterial(String borderMaterial, String insideMaterial);
    List<T> getByColor(String color, Integer pageNum, Integer pageSize);
    List<T> getBySize(String size, Integer pageNum, Integer pageSize);
    List<T> getAllByNameOrderByPopularityDesc(String name, Pageable pageable);
    List<T> getAllWithDiscounts(Integer pageNum, Integer pageSize);
}
