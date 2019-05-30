package com.ramkiopt.main.services.app.photoframes;

import com.ramkiopt.main.dto.PhotoFramesDto;

public interface PhotoFramesCrudService {
    Boolean createPhotoFrame(PhotoFramesDto dto);

    PhotoFramesDto getPhotoFrameDto(Long id);

    Boolean updatePhotoFrame(Long id, PhotoFramesDto dto);

    Boolean deletePhotoFrame(Long id);
}
