package com.ramkiopt.main.services.app.photoframesoncolors.impl;

import com.ramkiopt.main.dto.PhotoFramesOnColorsDto;
import com.ramkiopt.main.entities.PhotoFramesOnColors;
import com.ramkiopt.main.repositories.PhotoFramesOnColorsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnEntityService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoFramesOnColorsServiceImpl extends BaseServiceAbstract<PhotoFramesOnColors, PhotoFramesOnColorsDto>
        implements PhotoFramesOnEntityService<PhotoFramesOnColorsDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesOnColorsServiceImpl.class);
    private final PhotoFramesOnColorsRepository photoFramesOnColorsRepository;

    public PhotoFramesOnColorsServiceImpl(PhotoFramesOnColorsRepository photoFramesOnColorsRepository) {
        this.photoFramesOnColorsRepository = photoFramesOnColorsRepository;
    }

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

    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<PhotoFramesOnColorsDto> getEntitiesByPhotoFrameId(Long photoFrameId) {
        List<PhotoFramesOnColors> entities = photoFramesOnColorsRepository.findAllByPhotoFrameId(photoFrameId);
        List<PhotoFramesOnColorsDto> dtos = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            dtos.add(new PhotoFramesOnColorsDto());
        }
        ObjectMapper.mapListCustom(entities, dtos);
        return dtos;
    }
}
