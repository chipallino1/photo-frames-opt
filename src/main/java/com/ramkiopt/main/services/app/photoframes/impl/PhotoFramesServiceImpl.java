package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.repositories.PhotoFramesCriteriaRepository;
import com.ramkiopt.main.repositories.PhotoFramesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.base.RowStatus;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoFramesServiceImpl extends BaseServiceAbstract<PhotoFrames, PhotoFramesDto>
        implements PhotoFramesService<PhotoFramesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(PhotoFramesServiceImpl.class);
    private final PhotoFramesRepository photoFramesRepository;
    private final PhotoFramesCriteriaRepository photoFramesCriteriaRepository;

    public PhotoFramesServiceImpl(PhotoFramesRepository photoFramesRepository,
                                  PhotoFramesCriteriaRepository photoFramesCriteriaRepository) {
        this.photoFramesRepository = photoFramesRepository;
        this.photoFramesCriteriaRepository = photoFramesCriteriaRepository;
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
        if (dto == null || dto.getStatus().equals(RowStatus.DELETED)) {
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

    @Override
    public List<PhotoFramesDto> getAllByName(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage = photoFramesRepository.findAllByNameLikeAndStatus(name, RowStatus.ENABLE,
                pageable);
        List<PhotoFrames> photoFrames = photoFramesPage.getContent();
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> getByMaterial(String borderMaterial, String insideMaterial) {
        List<PhotoFrames> photoFrames =
                photoFramesCriteriaRepository.findBooksByAuthorNameAndTitle(borderMaterial, insideMaterial, 0, 1000, 0, 1000, "red");
        return null;
    }

    @Override
    public List<PhotoFramesDto> getByColor(String color, Integer pageNum, Integer pageSize) {
        List<PhotoFrames> photoFrames = photoFramesCriteriaRepository.findByColor(color, pageNum, pageSize);
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> getBySize(String size, Integer pageNum, Integer pageSize) {
        List<PhotoFrames> photoFrames = photoFramesCriteriaRepository.findBySize(size, pageNum, pageSize);
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> getAllByNameOrderByPopularityDesc(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage =
                photoFramesRepository.findAllByNameLikeAndStatusOrderByPopularityDesc(name, RowStatus.ENABLE, pageable);
        List<PhotoFrames> photoFrames = photoFramesPage.getContent();
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> getAllWithDiscounts(Integer pageNum, Integer pageSize) {
        List<PhotoFrames> photoFrames = photoFramesCriteriaRepository.findWithDiscounts(pageNum, pageSize);
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }
}
