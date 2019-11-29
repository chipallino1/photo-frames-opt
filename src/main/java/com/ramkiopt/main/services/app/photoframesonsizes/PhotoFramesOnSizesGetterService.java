package com.ramkiopt.main.services.app.photoframesonsizes;

import com.ramkiopt.main.dto.PhotoFramesOnSizesDto;

import java.util.List;

public interface PhotoFramesOnSizesGetterService {
    List<PhotoFramesOnSizesDto> getSizesByPhotoFrameId(Long photoFrameId);
}
