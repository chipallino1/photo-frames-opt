package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesCustomizationService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class PhotoFramesCustomizationServiceImpl implements PhotoFramesCustomizationService {
    @Autowired
    private PhotoFramesService photoFramesService;
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private SizesService sizesService;

    @Override
    public Boolean createPhotoFrame(PhotoFramesDto dto) {
        SizesDto sizesDto = dto.getSizesDto();
        ColorsDto colorsDto = dto.getColorsDto();
        if (sizesDto == null || colorsDto == null) {
            return false;
        }

        if (sizesDto.getId() != null) {
            try {
                SizesDto dtoFromDb = (SizesDto) sizesService.get(sizesDto.getId());
                if (dtoFromDb == null) {
                    return false;
                }
            } catch (NoSuchMethodException | InstantiationException |
                    IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
