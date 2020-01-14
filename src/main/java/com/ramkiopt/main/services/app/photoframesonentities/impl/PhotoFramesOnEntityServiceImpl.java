package com.ramkiopt.main.services.app.photoframesonentities.impl;

import com.ramkiopt.main.dto.PhotoFramesOnSizesDto;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframesonentities.PhotoFramesOnEntityService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoFramesOnEntityServiceImpl extends BaseServiceAbstract<PhotoFramesOnSizes, PhotoFramesOnSizesDto>
        implements PhotoFramesOnEntityService<PhotoFramesOnSizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesOnEntityServiceImpl.class);
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

    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<PhotoFramesOnSizesDto> getEntitiesByPhotoFrameId(Long photoFrameId) {
        List<PhotoFramesOnSizes> entities = photoFramesOnSizesRepository.findAllByPhotoFrameId(photoFrameId);
        List<PhotoFramesOnSizesDto> dtos = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            dtos.add(new PhotoFramesOnSizesDto());
        }
        ObjectMapper.mapListCustom(entities, dtos);
        return dtos;
    }
}
