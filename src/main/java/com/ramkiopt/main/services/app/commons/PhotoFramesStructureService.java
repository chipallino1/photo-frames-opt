package com.ramkiopt.main.services.app.commons;

import com.ramkiopt.main.dto.PhotoFramesDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhotoFramesStructureService {
    PhotoFramesDto createPhotoFrame(PhotoFramesDto dto);

    PhotoFramesDto readPhotoFrame(Long id);

    List<PhotoFramesDto> readAllByName(String name, Pageable pageable);

    List<PhotoFramesDto> readAllByNameOrderByPopularityDesc(String name, Pageable pageable);

    List<PhotoFramesDto> readAllWithDiscounts(Integer pageNum, Integer pageSize);

    List<PhotoFramesDto> readAllOrderByCostDesc(String name, Pageable pageable);

    List<PhotoFramesDto> readAllOrderByCost(String name, Pageable pageable);

    List<PhotoFramesDto> readAllByColors(List<String> colorNames, Integer pageNum, Integer pageSize);

    List<PhotoFramesDto> readAllBySizes(List<String> sizes, Integer pageNum, Integer pageSize);

    List<String> readAllInsideMaterials();

    List<String> readAllBorderMaterials();

    PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto);

    Boolean deletePhotoFrame(Long id);
}
