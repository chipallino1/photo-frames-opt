package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.app.discounts.DiscountsService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframesonentities.PhotoFramesOnEntityService;
import com.ramkiopt.main.services.app.photos.PhotosService;
import com.ramkiopt.main.services.app.sizes.SizesService;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnColorsDtoCreator;
import com.ramkiopt.main.services.utils.app.creators.PhotoFramesOnSizesDtoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PhotoFramesStructureServiceImpl implements PhotoFramesStructureService {
    private final PhotoFramesService<PhotoFramesDto> photoFramesService;
    private final ColorsService<ColorsDto> colorsService;
    private final SizesService<SizesDto> sizesService;
    private final PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator;
    private final PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator;
    private final DiscountsService<DiscountsDto> discountsService;

    @Autowired
    public PhotoFramesStructureServiceImpl(
            PhotoFramesService<PhotoFramesDto> photoFramesService,
            ColorsService<ColorsDto> colorsService,
            SizesService<SizesDto> sizesService,
            PhotoFramesOnSizesDtoCreator photoFramesOnSizesDtoCreator,
            PhotoFramesOnColorsDtoCreator photoFramesOnColorsDtoCreator,
            DiscountsService<DiscountsDto> discountsService) {
        this.photoFramesService = photoFramesService;
        this.colorsService = colorsService;
        this.sizesService = sizesService;
        this.photoFramesOnSizesDtoCreator = photoFramesOnSizesDtoCreator;
        this.photoFramesOnColorsDtoCreator = photoFramesOnColorsDtoCreator;
        this.discountsService = discountsService;
    }

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        photoFramesService.create(dto);
        createSizes(dto.getSizesDtos(), dto.getId());
        createColors(dto.getColorsDtos(), dto.getId());
        if (dto.getDiscountsDto() != null) {
            dto.getDiscountsDto().setPhotoFrameId(dto.getId());
        }
        discountsService.create(dto.getDiscountsDto());
        return dto;
    }

    @Override
    public PhotoFramesDto readPhotoFrame(Long id) {
        PhotoFramesDto photoFramesDto = photoFramesService.get(id);
        Long popularity = photoFramesDto.getPopularity();
        photoFramesDto.setPopularity(popularity == null ? 1 : popularity + 1);
        photoFramesService.update(id, photoFramesDto);
        setUpPhotoFramesDto(photoFramesDto);
        return photoFramesDto;
    }

    @Override
    public List<PhotoFramesDto> readAllByName(String name, Pageable pageable) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getAllByName(name, pageable);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllByNameOrderByPopularityDesc(String name, Pageable pageable) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getAllByNameOrderByPopularityDesc(name, pageable);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllWithDiscounts(Integer pageNum, Integer pageSize) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getAllWithDiscounts(pageNum, pageSize);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllOrderByCostDesc(String name, Pageable pageable) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getAllOrderByCostDesc(name, pageable);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllOrderByCost(String name, Pageable pageable) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getAllOrderByCost(name, pageable);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllByColor(String color, Integer pageNum, Integer pageSize) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getByColor(color, pageNum, pageSize);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> readAllBySize(String size, Integer pageNum, Integer pageSize) {
        List<PhotoFramesDto> photoFramesDtos = photoFramesService.getBySize(size, pageNum, pageSize);
        for (PhotoFramesDto dto : photoFramesDtos) {
            setUpPhotoFramesDto(dto);
        }
        return photoFramesDtos;
    }

    private void setUpPhotoFramesDto(PhotoFramesDto photoFramesDto) {
        photoFramesDto.setSizesDtos(getSizes(photoFramesDto.getId()));
        photoFramesDto.setColorsDtos(getColors(photoFramesDto.getId()));
        photoFramesDto.setDiscountsDto(discountsService.getByPhotoFrameId(photoFramesDto.getId()));
        PhotosDto photosDto = photosService.getByPhotoFrameId(photoFramesDto.getId());
        photoFramesDto.setImageSrc(photosDto != null ? photosDto.getPhotoSrc() : null);
    }

    @Override
    public PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto) {
        if (dto.getColorsDtos() != null) {
            updatePhotoFrameColors(dto);
        }
        if (dto.getSizesDtos() != null) {
            updatePhotoFrameSizes(dto);
        }
        if (dto.getDiscountsDto() != null) {
            updatePhotoFrameDiscounts(dto);
        }
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
            if (!entitiesIdentity.contains(dtoIdentity)) {
                result.add(dtoIdentity);
            }
        });
        return result;
    }

    private void updatePhotoFrameDiscounts(PhotoFramesDto dto) {
        if (dto.getDiscountsDto().getPercentCount() == 0 || dto.getDiscountsDto().getEndDate().before(new Date())) {
            discountsService.deleteByPhotoFrameId(dto.getId());
            return;
        }
        if (dto.getDiscountsDto() != null) {
            DiscountsDto discountsDto = discountsService.getByPhotoFrameId(dto.getId());
            if (discountsDto != null) {
                discountsService.update(discountsDto.getId(), dto.getDiscountsDto());
            } else {
                dto.getDiscountsDto().setPhotoFrameId(dto.getId());
                discountsService.create(dto.getDiscountsDto());
            }
        }
    }

    private void updatePhotoFrameColors(PhotoFramesDto dto) {
        List<Identity> colorsIds = getExistingEntities(dto.getColorsDtos());
        List<ColorsDto> colorsDtos = new ArrayList<>();
        getNotExistingEntities(colorsIds, dto.getColorsDtos())
                .forEach(dtoIdentity -> colorsDtos.add((ColorsDto) dtoIdentity));
        createColors(colorsDtos, dto.getId());
        dto.setColorsDtos(updateColors(dto.getColorsDtos()));
    }

    private void updatePhotoFrameSizes(PhotoFramesDto dto) {
        List<Identity> sizesIds = getExistingEntities(dto.getSizesDtos());
        List<SizesDto> sizesDtos = new ArrayList<>();
        getNotExistingEntities(sizesIds, dto.getSizesDtos())
                .forEach(dtoIdentity -> sizesDtos.add((SizesDto) dtoIdentity));
        createSizes(sizesDtos, dto.getId());
        dto.setSizesDtos(updateSizes(dto.getSizesDtos()));
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
