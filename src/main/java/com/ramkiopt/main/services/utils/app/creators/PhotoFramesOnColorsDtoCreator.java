package com.ramkiopt.main.services.utils.app.creators;

import com.ramkiopt.main.dto.PhotoFramesOnColorsDto;
import org.springframework.stereotype.Service;

@Service
public class PhotoFramesOnColorsDtoCreator {
    public PhotoFramesOnColorsDto createDto(Long colorId, Long photoFrameId) {
        PhotoFramesOnColorsDto photoFramesOnSizesDto = new PhotoFramesOnColorsDto();
        photoFramesOnSizesDto.setColorId(colorId);
        photoFramesOnSizesDto.setPhotoFrameId(photoFrameId);
        return photoFramesOnSizesDto;
    }
}
