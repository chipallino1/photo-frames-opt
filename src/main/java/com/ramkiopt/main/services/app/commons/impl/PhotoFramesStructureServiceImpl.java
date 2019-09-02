package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.*;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframesoncolors.PhotoFramesOnColorsService;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnSizesService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnColorsDtoCreator;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnSizesDtoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoFramesStructureServiceImpl implements PhotoFramesStructureService {
    private final PhotoFramesService<PhotoFramesDto> photoFramesService;
    private final ColorsService<ColorsDto> colorsService;
    private final SizesService<SizesDto> sizesService;
    private final PhotoFramesOnSizesService<PhotoFramesOnSizesDto> photoFramesOnSizesService;
    private final PhotoFramesOnColorsService<PhotoFramesOnColorsDto> photoFramesOnColorsService;
    private final PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator;
    private final PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator;

    @Autowired
    public PhotoFramesStructureServiceImpl(
            PhotoFramesService<PhotoFramesDto> photoFramesService,
            ColorsService<ColorsDto> colorsService,
            SizesService<SizesDto> sizesService,
            PhotoFramesOnSizesService<PhotoFramesOnSizesDto> photoFramesOnSizesService,
            PhotoFramesOnColorsService<PhotoFramesOnColorsDto> photoFramesOnColorsService,
            PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator,
            PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator) {
        this.photoFramesService = photoFramesService;
        this.colorsService = colorsService;
        this.sizesService = sizesService;
        this.photoFramesOnSizesService = photoFramesOnSizesService;
        this.photoFramesOnColorsService = photoFramesOnColorsService;
        this.photoFramesOnSizesDtoCreator = photoFramesOnSizesDtoCreator;
        this.photoFramesOnColorsDtoCreator = photoFramesOnColorsDtoCreator;
    }

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        photoFramesService.create(dto);
        createSizes(dto.getSizesDtoList(), dto.getId());
        createColors(dto.getColorsDtoList(), dto.getId());

        // TODO add setPhotoFramesOnPhotosFromPhotoFramesDto

        return dto;
    }

    private void createSizes(List<SizesDto> dtos, Long photoFrameId) {
        for (SizesDto sizesDto : dtos) {
            if (sizesDto.getId() == null) {
                sizesDto = sizesService.create(sizesDto);
            }
            photoFramesOnSizesService.create(photoFramesOnSizesDtoCreator.createDto(sizesDto.getId(), photoFrameId));
        }
    }

    private void createColors(List<ColorsDto> dtos, Long photoFrameId) {
        for (ColorsDto colorsDto : dtos) {
            if (colorsDto.getId() == null) {
                colorsDto = colorsService.create(colorsDto);
            }
            photoFramesOnColorsService.create(photoFramesOnColorsDtoCreator.createDto(colorsDto.getId(), photoFrameId));
        }
    }
}
