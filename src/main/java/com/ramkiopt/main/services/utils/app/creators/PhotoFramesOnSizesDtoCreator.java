package com.ramkiopt.main.services.utils.app.creators;

import org.springframework.stereotype.Service;

@Service
public class PhotoFramesOnSizesDtoCreator {
    public PhotoFramesOnSizesDto createDto(Long sizeId, Long photoFrameId) {
        PhotoFramesOnSizesDto photoFramesOnSizesDto = new PhotoFramesOnSizesDto();
        photoFramesOnSizesDto.setSizeId(sizeId);
        photoFramesOnSizesDto.setPhotoFrameId(photoFrameId);
        return photoFramesOnSizesDto;
    }
}
