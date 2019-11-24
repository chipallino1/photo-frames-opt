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
public class PhotoFramesOnSizesServiceImpl extends BaseServiceAbstract<PhotoFramesOnSizes, PhotoFramesOnSizesDto>
        implements PhotoFramesOnSizesService<PhotoFramesOnSizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesOnSizesServiceImpl.class);
    @Autowired
    private PhotoFramesOnSizesRepository photoFramesOnSizesRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesOnSizesRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<PhotoFramesOnSizes, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PhotoFramesOnSizesDto create(PhotoFramesOnSizesDto dto) {
        return createInDb(new PhotoFramesOnSizes(), dto);
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
