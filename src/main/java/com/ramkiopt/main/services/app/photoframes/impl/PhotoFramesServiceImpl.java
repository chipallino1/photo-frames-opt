package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.utils.BaseServiceAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PhotoFramesServiceImpl extends BaseServiceAbstract<PhotoFrames, Long>
        implements PhotoFramesService {
    @Override
    protected void setJpaRepository(JpaRepository<PhotoFrames, Long> jpaRepository) {

    }

    @Override
    public Boolean createPhotoFrame(PhotoFramesDto dto) {
        return null;
    }

    @Override
    public PhotoFramesDto getPhotoFrameDto(Long id) {
        return null;
    }

    @Override
    public Boolean updatePhotoFrame(Long id, PhotoFramesDto dto) {
        return null;
    }

    @Override
    public Boolean deletePhotoFrame(Long id) {
        return null;
    }
}
