package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesCustomizationService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return null;
    }
}
