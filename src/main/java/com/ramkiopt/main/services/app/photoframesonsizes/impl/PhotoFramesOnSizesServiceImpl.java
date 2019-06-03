package com.ramkiopt.main.services.app.photoframesonsizes.impl;

import com.ramkiopt.main.dto.PhotoFramesOnSizesDto;
import com.ramkiopt.main.entities.PhotoFramesOnSizes;
import com.ramkiopt.main.repositories.PhotoFramesOnSizesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnSizesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class PhotoFramesOnSizesServiceImpl extends BaseServiceAbstract<PhotoFramesOnSizes, Long>
        implements PhotoFramesOnSizesService<PhotoFramesOnSizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesOnSizesServiceImpl.class);
    @Autowired
    private PhotoFramesOnSizesRepository photoFramesOnSizesRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesOnSizesRepository);
        setClass(PhotoFramesOnSizes.class);
        setClassDto(PhotoFramesOnSizesDto.class);
    }

    @Override
    protected void setJpaRepository(JpaRepository<PhotoFramesOnSizes, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    protected void setClass(Class<PhotoFramesOnSizes> photoFramesOnSizesClass) {
        this.tClass = photoFramesOnSizesClass;
    }

    @Override
    protected void setClassDto(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public PhotoFramesOnSizesDto create(PhotoFramesOnSizesDto dto) {
        PhotoFramesOnSizesDto photoFramesOnSizesDto = null;
        try {
            photoFramesOnSizesDto = (PhotoFramesOnSizesDto) tryCreate(dto);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return photoFramesOnSizesDto;
    }

    @Override
    public PhotoFramesOnSizesDto get(Long id) {
        return null;
    }

    @Override
    public PhotoFramesOnSizesDto update(Long id, PhotoFramesOnSizesDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
