package com.ramkiopt.main.services.app.photoframesoncolors;

import com.ramkiopt.main.dto.PhotoFramesOnColorsDto;

import java.util.List;

public interface PhotoFramesOnColorsGetterService {
    List<PhotoFramesOnColorsDto> getColorsByPhotoFrameId(Long photoFrameId);
}
