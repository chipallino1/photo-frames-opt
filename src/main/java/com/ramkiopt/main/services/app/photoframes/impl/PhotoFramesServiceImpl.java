package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.repositories.PhotoFramesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class PhotoFramesServiceImpl extends BaseServiceAbstract<PhotoFrames, Long>
        implements PhotoFramesService<PhotoFramesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesServiceImpl.class);
    @Autowired
    private PhotoFramesRepository photoFramesRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesRepository);
        setClass(PhotoFrames.class);
        setClassDto(PhotoFramesDto.class);
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
    protected void setClassDto(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public Boolean create(PhotoFramesDto dto) {
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
    public PhotoFramesDto get(Long id) {
        return null;
    }

    @Override
    public Boolean update(Long id, PhotoFramesDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
