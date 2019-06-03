package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.PhotoFramesOnSizesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesCustomizationService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframesoncolors.PhotoFramesOnColorsService;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnSizesService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
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
    public Boolean createPhotoFrame(PhotoFramesDto dto) {
        List<SizesDto> sizesDtoList = dto.getSizesDtoList();
        List<ColorsDto> colorsDtoList = dto.getColorsDtoList();
        if (sizesDtoList == null || colorsDtoList == null) {
            return false;
        }

        for (int i = 0; i < sizesDtoList.size(); i++) {
            SizesDto currentSizesDto = sizesDtoList.get(i);
            if (currentSizesDto.getId() != null) {
                try {
                    SizesDto dtoFromDb = (SizesDto) sizesService.get(currentSizesDto.getId());
                    if (dtoFromDb == null) {
                        return false;
                    }
                } catch (NoSuchMethodException | InstantiationException |
                        IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }
}
