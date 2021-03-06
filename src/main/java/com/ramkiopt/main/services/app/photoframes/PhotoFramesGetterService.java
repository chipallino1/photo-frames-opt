package com.ramkiopt.main.services.app.photoframes;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhotoFramesGetterService<T> {
    List<T> getAllByName(String name, Pageable pageable);

    List<T> getByColors(List<String> colorNames, Integer pageNum, Integer pageSize);

    List<T> getBySizes(List<String> sizes, Integer pageNum, Integer pageSize);

    List<T> getByBorderMaterials(List<String> borderMaterials, Pageable pageable);

    List<T> getByInsideMaterials(List<String> insideMaterials, Pageable pageable);

    List<T> getByAllParameters(List<String> colors, List<String> sizes, List<String> insideMaterials,
                               List<String> borderMaterials, Integer pageNumber, Integer pageSize);

    List<T> getAllByNameOrderByPopularityDesc(String name, Pageable pageable);

    List<T> getAllWithDiscounts(Integer pageNum, Integer pageSize);

    List<T> getAllOrderByCostDesc(String name, Pageable pageable);

    List<T> getAllOrderByCost(String name, Pageable pageable);
}
