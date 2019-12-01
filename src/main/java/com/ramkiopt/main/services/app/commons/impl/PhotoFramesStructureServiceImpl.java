package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.PhotoFramesOnColorsDto;
import com.ramkiopt.main.dto.PhotoFramesOnSizesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.Identity;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframesonsizes.PhotoFramesOnEntityService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnColorsDtoCreator;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnSizesDtoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoFramesStructureServiceImpl implements PhotoFramesStructureService {
    private final PhotoFramesService<PhotoFramesDto> photoFramesService;
    private final ColorsService<ColorsDto> colorsService;
    private final SizesService<SizesDto> sizesService;
    private final PhotoFramesOnEntityService<PhotoFramesOnSizesDto> photoFramesOnSizesService;
    private final PhotoFramesOnEntityService<PhotoFramesOnColorsDto> photoFramesOnColorsService;
    private final PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator;
    private final PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator;

    @Autowired
    public PhotoFramesStructureServiceImpl(
            PhotoFramesService<PhotoFramesDto> photoFramesService,
            ColorsService<ColorsDto> colorsService,
            SizesService<SizesDto> sizesService,
            PhotoFramesOnEntityService<PhotoFramesOnSizesDto> photoFramesOnSizesService,
            PhotoFramesOnEntityService<PhotoFramesOnColorsDto> photoFramesOnColorsService,
            PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator,
            PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator) {
        this.photoFramesService = photoFramesService;
        this.colorsService = colorsService;
        this.sizesService = sizesService;
        this.photoFramesOnSizesService = photoFramesOnSizesService;
        this.photoFramesOnColorsService = photoFramesOnColorsService;
        this.photoFramesOnSizesDtoCreator = photoFramesOnSizesDtoCreator;
        this.photoFramesOnColorsDtoCreator = photoFramesOnColorsDtoCreator;
    }

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        photoFramesService.create(dto);
        createSizes(dto.getSizesDtos(), dto.getId());
        createColors(dto.getColorsDtos(), dto.getId());
        return dto;
    }

    @Override
    public PhotoFramesDto readPhotoFrame(Long id) {
        PhotoFramesDto photoFramesDto = photoFramesService.get(id);
        photoFramesDto.setSizesDtos(getSizes(id));
        photoFramesDto.setColorsDtos(getColors(id));
        return photoFramesDto;
    }

    @Override
    public PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto) {
        List<Identity> colorsIds = getExistingEntities(dto.getColorsDtos());
        List<Identity> sizesIds = getExistingEntities(dto.getSizesDtos());

        List<ColorsDto> colorsDtos = new ArrayList<>();
        getNotExistingEntities(colorsIds, dto.getColorsDtos())
                .forEach(dtoIdentity -> colorsDtos.add((ColorsDto) dtoIdentity));
        createColors(colorsDtos, id);

        List<SizesDto> sizesDtos = new ArrayList<>();
        getNotExistingEntities(sizesIds, dto.getSizesDtos())
                .forEach(dtoIdentity -> sizesDtos.add((SizesDto) dtoIdentity));
        createSizes(sizesDtos, id);

        dto.setColorsDtos(updateColors(dto.getColorsDtos()));
        dto.setSizesDtos(updateSizes(dto.getSizesDtos()));
        dto = photoFramesService.update(id, dto);
        return dto;
    }

    @Override
    public Boolean deletePhotoFrame(Long id) {
        return photoFramesService.delete(id);
    }

    private List<Identity> getExistingEntities(List entities) {
        List<Identity> identities = (List<Identity>) entities;
        List<Identity> existingIds = new ArrayList<>();
        identities.forEach(entity -> {
            if (entity.getId() != null) {
                existingIds.add(entity);
            }
        });
        return existingIds;
    }

    private List<Identity> getNotExistingEntities(List entities, List dtos) {
        List<Identity> dtosIdentity = (List<Identity>) dtos;
        List<Identity> entitiesIdentity = (List<Identity>) entities;
        List<Identity> result = new ArrayList<>();
        dtosIdentity.forEach(dtoIdentity -> {
            if(!entitiesIdentity.contains(dtoIdentity)){
                result.add(dtoIdentity);
            }
        });
        return result;
    }

    private List<ColorsDto> updateColors(List<ColorsDto> colorsDtos) {
        for (ColorsDto dto : colorsDtos) {
            colorsService.update(dto.getId(), dto);
        }
        return colorsDtos;
    }

    private List<SizesDto> updateSizes(List<SizesDto> sizesDtos) {
        for (SizesDto dto : sizesDtos) {
            sizesService.update(dto.getId(), dto);
        }
        return sizesDtos;
    }

    private List<SizesDto> getSizes(Long photoFrameId) {
        List<PhotoFramesOnSizesDto> photoFramesOnSizesDtos =
                photoFramesOnSizesService.getEntitiesByPhotoFrameId(photoFrameId);
        List<Long> ids = new ArrayList<>();
        for (PhotoFramesOnSizesDto dto : photoFramesOnSizesDtos) {
            ids.add(dto.getSizeId());
        }
        return sizesService.getSizesById(ids);
    }

    private List<ColorsDto> getColors(Long photoFrameId) {
        List<PhotoFramesOnColorsDto> photoFramesOnColorsDtos =
                photoFramesOnColorsService.getEntitiesByPhotoFrameId(photoFrameId);
        List<Long> ids = new ArrayList<>();
        for (PhotoFramesOnColorsDto dto : photoFramesOnColorsDtos) {
            ids.add(dto.getColorId());
        }
        return colorsService.getColorsById(ids);
    }

    private void createSizes(List<SizesDto> dtos, Long photoFrameId) {
        for (SizesDto sizesDto : dtos) {
            if (sizesDto.getId() == null) {
                sizesDto = sizesService.create(sizesDto);
            }
            photoFramesOnSizesService.create(photoFramesOnSizesDtoCreator.createDto(sizesDto.getId(), photoFrameId));
        }
    }

    private void createColors(List<ColorsDto> dtos, Long photoFrameId) {
        for (ColorsDto colorsDto : dtos) {
            if (colorsDto.getId() == null) {
                colorsDto = colorsService.create(colorsDto);
            }
            photoFramesOnColorsService.create(photoFramesOnColorsDtoCreator.createDto(colorsDto.getId(), photoFrameId));
        }
    }
}
