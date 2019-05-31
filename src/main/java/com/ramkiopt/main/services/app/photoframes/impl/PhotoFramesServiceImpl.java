package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.repositories.PhotoFramesRepository;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.utils.BaseServiceAbstract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class PhotoFramesServiceImpl extends BaseServiceAbstract<PhotoFrames, Long>
        implements PhotoFramesService {

    private final Logger LOGGER = LogManager.getLogger(PhotoFramesServiceImpl.class);
    @Autowired
    private PhotoFramesRepository photoFramesRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesRepository);
        setClass(PhotoFrames.class);
    }

    @Override
    protected void setJpaRepository(JpaRepository<PhotoFrames, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    protected void setClass(Class<PhotoFrames> photoFramesClass) {
        this.tClass = photoFramesClass;
    }

    @Override
    public Boolean createPhotoFrame(PhotoFramesDto dto) {
        Boolean isCreated = false;
        try {
            isCreated = tryCreate(dto);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return isCreated;
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
