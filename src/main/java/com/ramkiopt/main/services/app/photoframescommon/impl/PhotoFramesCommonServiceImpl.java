package com.ramkiopt.main.services.app.photoframescommon.impl;

import com.ramkiopt.main.dto.PhotoFramesCommonDto;
import com.ramkiopt.main.entities.PhotoFramesCommon;
import com.ramkiopt.main.repositories.PhotoFramesCommonRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframescommon.PhotoFramesCommonService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoFramesCommonServiceImpl extends BaseServiceAbstract<PhotoFramesCommon, PhotoFramesCommonDto>
        implements PhotoFramesCommonService<PhotoFramesCommonDto> {
    private final PhotoFramesCommonRepository commonRepository;

    public PhotoFramesCommonServiceImpl(PhotoFramesCommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        setJpaRepository(commonRepository);
    }

    @Override
    public PhotoFramesCommonDto create(PhotoFramesCommonDto dto) {
        return createInDb(new PhotoFramesCommon(), dto);
    }

    @Override
    public PhotoFramesCommonDto get(Long id) {
        return readFromDb(id, new PhotoFramesCommonDto());
    }

    @Override
    public PhotoFramesCommonDto update(Long id, PhotoFramesCommonDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<PhotoFramesCommonDto> getEntitiesByPhotoFrameId(Long id) {
        return ObjectMapper.mapListLambda(commonRepository.findAllByPhotoFrameId(id), PhotoFramesCommonDto.class);
    }
}
