package com.ramkiopt.main.services.app.photos.impl;

import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photos.PhotosService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PhotosServiceImpl extends BaseServiceAbstract<Photos, PhotosDto> implements PhotosService<PhotosDto> {

    @Autowired
    private PhotosRepository photosRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(photosRepository);
    }

    @Override
    public boolean deleteInDb(Long id) {
        Photos photos = photosRepository.findByPhotoFrameId(id);
        if (photos == null) {
            return true;
        }
        photosRepository.delete(photos);
        return true;
    }

    @Override
    public PhotosDto create(PhotosDto dto) {
        return createInDb(new Photos(), dto);
    }

    @Override
    public PhotosDto get(Long id) {
        return readFromDb(id, new PhotosDto());
    }

    @Override
    public PhotosDto update(Long id, PhotosDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }

    @Override
    public PhotosDto getByPhotoFrameId(Long id) {
        Photos photos = photosRepository.findByPhotoFrameId(id);
        if (photos == null) {
            return null;
        }
        PhotosDto dto = new PhotosDto();
        ObjectMapper.mapCustom(photos, dto);
        return dto;
    }
}
