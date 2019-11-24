package com.ramkiopt.main.services.app.photoframesoncolors.impl;

import com.ramkiopt.main.dto.PhotoFramesOnColorsDto;
import com.ramkiopt.main.entities.PhotoFramesOnColors;
import com.ramkiopt.main.repositories.PhotoFramesOnColorsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframesoncolors.PhotoFramesOnColorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class PhotoFramesOnColorsServiceImpl extends BaseServiceAbstract<PhotoFramesOnColors, PhotoFramesOnColorsDto>
        implements PhotoFramesOnColorsService<PhotoFramesOnColorsDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesOnColorsServiceImpl.class);
    @Autowired
    private PhotoFramesOnColorsRepository photoFramesOnColorsRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesOnColorsRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<PhotoFramesOnColors, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PhotoFramesOnColorsDto create(PhotoFramesOnColorsDto dto) {
        return createInDb(new PhotoFramesOnColors(), dto);
    }

    @Override
    public PhotoFramesOnColorsDto get(Long id) {
        return null;
    }

    @Override
    public PhotoFramesOnColorsDto update(Long id, PhotoFramesOnColorsDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
