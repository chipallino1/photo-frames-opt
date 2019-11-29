package com.ramkiopt.main.services.app.commons;

import com.ramkiopt.main.dto.PhotoFramesDto;

public interface PhotoFramesStructureService {
    PhotoFramesDto createPhotoFrame(PhotoFramesDto dto);
    PhotoFramesDto readPhotoFrame(Long id);
    PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto);
}
