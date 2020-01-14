package com.ramkiopt.main.services.app.photoframesonentities.impl;

import com.ramkiopt.main.dto.PhotoFramesOnOrdersDto;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframesonentities.PhotoFramesOnEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoFramesOnOrdersServiceImpl extends BaseServiceAbstract<PhotoFramesOnOrders, PhotoFramesOnOrdersDto>
        implements PhotoFramesOnEntityService<PhotoFramesOnOrdersDto> {
    @Override
    public PhotoFramesOnOrdersDto create(PhotoFramesOnOrdersDto dto) {
        return createInDb(new PhotoFramesOnOrders(), dto);
    }

    @Override
    public PhotoFramesOnOrdersDto get(Long id) {
        return readFromDb(id, new PhotoFramesOnOrdersDto());
    }

    @Override
    public PhotoFramesOnOrdersDto update(Long id, PhotoFramesOnOrdersDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }

    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<PhotoFramesOnOrdersDto> getEntitiesByPhotoFrameId(Long photoFrameId) {
        return null;
    }
}
