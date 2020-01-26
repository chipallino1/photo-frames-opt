package com.ramkiopt.main.services.app.commons.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.dto.PhotoFramesCommonDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.Identity;
import com.ramkiopt.main.services.app.base.BaseCrudService;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.app.commons.PhotoFramesStructureService;
import com.ramkiopt.main.services.app.discounts.DiscountsService;
import com.ramkiopt.main.services.app.photoframes.PhotoFramesService;
import com.ramkiopt.main.services.app.photoframescommon.PhotoFramesCommonService;
import com.ramkiopt.main.services.app.sizes.SizesService;
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
    private final DiscountsService<DiscountsDto> discountsService;
    private final PhotoFramesCommonService<PhotoFramesCommonDto> photoFramesCommonService;

    public PhotoFramesStructureServiceImpl(
            PhotoFramesService<PhotoFramesDto> photoFramesService,
            ColorsService<ColorsDto> colorsService,
            SizesService<SizesDto> sizesService,
            DiscountsService<DiscountsDto> discountsService,
            PhotoFramesCommonService<PhotoFramesCommonDto> photoFramesCommonService) {
        this.photoFramesService = photoFramesService;
        this.colorsService = colorsService;
        this.sizesService = sizesService;
        this.discountsService = discountsService;
        this.photoFramesCommonService = photoFramesCommonService;
    }

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        photoFramesService.create(dto);
        createEntities(dto.getSizesDtos(), sizesService);
        createEntities(dto.getColorsDtos(), colorsService);
        createPhotoFramesCommon(dto);

        if (dto.getDiscountsDto() != null) {
            dto.getDiscountsDto().setPhotoFrameId(dto.getId());
        }
        discountsService.create(dto.getDiscountsDto());
        return dto;
    }

    private void createPhotoFramesCommon(PhotoFramesDto dto) {
        int i = 0;
        for (SizesDto sizesDto : dto.getSizesDtos()) {
            for (ColorsDto colorsDto : dto.getColorsDtos()) {
                PhotoFramesCommonDto commonDto = new PhotoFramesCommonDto();
                commonDto.setSizeId(sizesDto.getId());
                commonDto.setPhotoFrameId(dto.getId());
                commonDto.setColorId(colorsDto.getId());
                commonDto.setCost(dto.getCosts().get(i));
                commonDto.setPhotoSrc(dto.getPhotosSrcs().get(i));
                photoFramesCommonService.create(commonDto);
                i++;
            }
        }
    }

    @Override
    public PhotoFramesDto readPhotoFrame(Long id) {
        PhotoFramesDto photoFramesDto = photoFramesService.get(id);
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
        createEntities(colorsDtos, colorsService);
        dto.setColorsDtos(updateEntities(dto.getColorsDtos(), colorsService));
    }

    private void updatePhotoFrameSizes(PhotoFramesDto dto) {
        List<Identity> sizesIds = getExistingEntities(dto.getSizesDtos());
        List<SizesDto> sizesDtos = new ArrayList<>();
        getNotExistingEntities(sizesIds, dto.getSizesDtos())
                .forEach(dtoIdentity -> sizesDtos.add((SizesDto) dtoIdentity));
        createEntities(sizesDtos, sizesService);
        dto.setSizesDtos(updateEntities(dto.getSizesDtos(), sizesService));
    }

    private <T extends Identity> List<T> updateEntities(List<T> dtos, BaseCrudService<T> baseCrudService) {
        for (T dto : dtos) {
            baseCrudService.update(dto.getId(), dto);
        }
        return dtos;
    }

    private List<SizesDto> getSizes(Long photoFrameId) {
        List<PhotoFramesCommonDto> commonDtos =
                photoFramesCommonService.getEntitiesByPhotoFrameId(photoFrameId);
        List<Long> ids = new ArrayList<>();
        for (PhotoFramesCommonDto dto : commonDtos) {
            ids.add(dto.getSizeId());
        }
        return sizesService.getSizesById(ids);
    }

    private List<ColorsDto> getColors(Long photoFrameId) {
        List<PhotoFramesCommonDto> commonDtos =
                photoFramesCommonService.getEntitiesByPhotoFrameId(photoFrameId);
        List<Long> ids = new ArrayList<>();
        for (PhotoFramesCommonDto dto : commonDtos) {
            ids.add(dto.getColorId());
        }
        return colorsService.getColorsById(ids);
    }

    private <T extends Identity> List<T> createEntities(List<T> dtos, BaseCrudService<T> baseCrudService) {
        for (T dto : dtos) {
            if (dto.getId() == null) {
                baseCrudService.create(dto);
            }
        }
        return dtos;
    }
}
