package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.repositories.PhotoFramesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Service
public class PhotoFramesServiceImpl extends BaseServiceAbstract<PhotoFrames, PhotoFramesDto>
        implements PhotoFramesService<PhotoFramesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesServiceImpl.class);
    private final PhotoFramesRepository photoFramesRepository;

    public PhotoFramesServiceImpl(PhotoFramesRepository photoFramesRepository) {
        this.photoFramesRepository = photoFramesRepository;
    }

    @PostConstruct
    public void init() {
        setJpaRepository(photoFramesRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<PhotoFrames, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PhotoFramesDto create(PhotoFramesDto dto) {
        dto.setStatus(RowStatus.ENABLE);
        return createInDb(new PhotoFrames(), dto);
    }

    @Override
    public PhotoFramesDto get(Long id) {
        PhotoFramesDto dto = readFromDb(id, new PhotoFramesDto());
        if (dto.getStatus().equals(RowStatus.DELETED)) {
            throw new EntityNotFoundException();
        }
        return dto;
    }

    @Override
    public PhotoFramesDto update(Long id, PhotoFramesDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }

    @Override
    public boolean deleteInDb(Long id) {
        PhotoFrames photoFrames = jpaRepository.getOne(id);
        if (photoFrames == null) {
            return false;
        }
        photoFrames.setStatus(RowStatus.DELETED);
        jpaRepository.save(photoFrames);
        return true;
    }
}
