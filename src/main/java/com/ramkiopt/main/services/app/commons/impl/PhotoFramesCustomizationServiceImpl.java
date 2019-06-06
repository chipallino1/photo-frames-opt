package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.*;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesCustomizationService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframesoncolors.PhotoFramesOnColorsService;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnSizesService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PhotoFramesCustomizationServiceImpl implements PhotoFramesCustomizationService {
    @Autowired
    private PhotoFramesService photoFramesService;
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private SizesService sizesService;
    @Autowired
    private PhotoFramesOnSizesService photoFramesOnSizesService;
    @Autowired
    private PhotoFramesOnColorsService photoFramesOnColorsService;

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        List<SizesDto> sizesDtoList = dto.getSizesDtoList();
        List<ColorsDto> colorsDtoList = dto.getColorsDtoList();
        if (sizesDtoList == null || colorsDtoList == null) {
            return null;
        }
        Serializable serializable = dto;
        PhotoFramesDto testDto = (PhotoFramesDto) serializable;
        dto = (PhotoFramesDto) photoFramesService.create(dto);

        if (!isSetPhotoFramesOnSizesFromPhotoFramesDto(sizesDtoList, dto.getId()) ||
                !isSetPhotoFramesOnColorsFromPhotoFramesDto(colorsDtoList, dto.getId())) {
            return null;
        }

        // TODO add setPhotoFramesOnPhotosFromPhotoFramesDto


        return dto;
    }

    private boolean isSetPhotoFramesOnSizesFromPhotoFramesDto(List<SizesDto> sizesDtoList, Long photoFramesId) {
        PhotoFramesOnSizesDto photoFramesOnSizesDto = new PhotoFramesOnSizesDto();
        for (int i = 0; i < sizesDtoList.size(); i++) {
            SizesDto currentSizesDto = sizesDtoList.get(i);
            if (currentSizesDto.getId() != null) {
                SizesDto dtoFromDb = (SizesDto) sizesService.get(currentSizesDto.getId());
                if (dtoFromDb == null) {
                    return false;
                }
            } else {
                currentSizesDto = (SizesDto) sizesService.create(currentSizesDto);
            }
            photoFramesOnSizesDto.setPhotoFrameId(photoFramesId);
            photoFramesOnSizesDto.setSizeId(currentSizesDto.getId());
            photoFramesOnSizesService.create(photoFramesOnSizesDto);
        }
        return true;
    }

    private boolean isSetPhotoFramesOnColorsFromPhotoFramesDto(List<ColorsDto> colorsDtoList, Long photoFramesId) {
        PhotoFramesOnColorsDto photoFramesOnColorsDto = new PhotoFramesOnColorsDto();
        for (int i = 0; i < colorsDtoList.size(); i++) {
            ColorsDto currentColorsDto = colorsDtoList.get(i);
            if (currentColorsDto.getId() != null) {
                ColorsDto dtoFromDb = (ColorsDto) colorsService.get(currentColorsDto.getId());
                if (dtoFromDb == null) {
                    return false;
                }
            } else {
                currentColorsDto = (ColorsDto) colorsService.create(currentColorsDto);
            }
            photoFramesOnColorsDto.setPhotoFrameId(photoFramesId);
            photoFramesOnColorsDto.setColorId(currentColorsDto.getId());
            photoFramesOnColorsService.create(photoFramesOnColorsDto);
        }
        return true;
    }
}
