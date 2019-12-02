package com.ramkiopt.main.services.app.commons;

import com.ramkiopt.main.dto.PhotoFramesDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhotoFramesStructureService {
    PhotoFramesDto createPhotoFrame(PhotoFramesDto dto);

    PhotoFramesDto readPhotoFrame(Long id);

    List<PhotoFramesDto> readAllByName(String name, Pageable pageable);

    PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto);

    Boolean deletePhotoFrame(Long id);
}
