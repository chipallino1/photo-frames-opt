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
        List<SizesDto> sizesDtos = createEntities(getSizesDtos(dto.getCommonDtos()), sizesService);
        List<ColorsDto> colorsDtos = createEntities(getColorsDtos(dto.getCommonDtos()), colorsService);
        List<PhotoFramesCommonDto> commonDtos = createPhotoFramesCommon(colorsDtos, sizesDtos, dto.getId());
        List<DiscountsDto> discountsDtos = getDiscountsDtos(commonDtos);
        if (validateDiscounts(discountsDtos)) {
            createEntities(discountsDtos, discountsService);
        }
        return dto;
    }

    private List<ColorsDto> getColorsDtos(List<PhotoFramesCommonDto> commonDtos) {
        return commonDtos
                .stream()
                .collect(ArrayList::new, (list, item) -> {
                    if (item.getColorsDto() != null) {
                        list.add(item.getColorsDto());
                    }
                }, ArrayList::addAll);
    }

    private List<SizesDto> getSizesDtos(List<PhotoFramesCommonDto> commonDtos) {
        return commonDtos
                .stream()
                .collect(ArrayList::new, (list, item) -> {
                    if (item.getSizesDto() != null) {
                        list.add(item.getSizesDto());
                    }
                }, ArrayList::addAll);
    }

    private List<DiscountsDto> getDiscountsDtos(List<PhotoFramesCommonDto> commonDtos) {
        return commonDtos
                .stream()
                .collect(ArrayList::new, (list, item) -> {
                            if (item.getDiscountsDto() != null) {
                                item.getDiscountsDto().setPhotoFrameCommonId(item.getId());
                                list.add(item.getDiscountsDto());
                            }
                        },
                        ArrayList::addAll);
    }

    private List<PhotoFramesCommonDto> createPhotoFramesCommon(List<ColorsDto> colorsDtos, List<SizesDto> sizesDtos,
                                                               Long photoFrameId) {
        List<PhotoFramesCommonDto> commonDtos = new ArrayList<>();
        int i = 0;
        for (SizesDto sizesDto : sizesDtos) {
            for (ColorsDto colorsDto : colorsDtos) {
                PhotoFramesCommonDto commonDto = new PhotoFramesCommonDto();
                commonDto.setSizeId(sizesDto.getId());
                commonDto.setPhotoFrameId(photoFrameId);
                commonDto.setColorId(colorsDto.getId());
                commonDtos.add(commonDto);
                i++;
            }
        }
        return photoFramesCommonService.createAll(commonDtos);
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
        List<PhotoFramesCommonDto> commonDtos =
                photoFramesCommonService.getEntitiesByPhotoFrameId(photoFramesDto.getId());
        photoFramesDto.setPhotosSrcs(getPhotosSrcs(commonDtos));
        photoFramesDto.setCosts(getCosts(commonDtos));
        photoFramesDto.setSizesDtos(getSizes(commonDtos));
        photoFramesDto.setColorsDtos(getColors(commonDtos));
        photoFramesDto.setDiscountsDtos(discountsService.getByPhotoFrameCommonIds(getIds(commonDtos)));
    }

    private <T extends Identity> List<Long> getIds(List<T> commonDtos) {
        return commonDtos.stream().collect(ArrayList::new, (list, item) -> list.add(item.getId()), ArrayList::addAll);
    }

    @Override
    public PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto) {
        List<PhotoFramesCommonDto> commonDtos = new ArrayList<>();
        if (dto.getColorsDtos() != null && dto.getSizesDtos() != null) {
            List<ColorsDto> colorsDtos = createEntities(getColorsDtos(dto.getCommonDtos()), colorsService);
            List<SizesDto> sizesDtos = createEntities(getSizesDtos(dto.getCommonDtos()), sizesService);
            commonDtos = createPhotoFramesCommon(colorsDtos, sizesDtos, id);
        }
        if (dto.getDiscountsDtos() != null) {
            updatePhotoFrameDiscounts(getDiscountsDtos(commonDtos), dto.getId());
        }
        dto = photoFramesService.update(id, dto);
        return dto;
    }

    @Override
    public Boolean deletePhotoFrame(Long id) {
        return photoFramesService.delete(id);
    }

    private void updatePhotoFrameDiscounts(List<DiscountsDto> discountsDtos, Long photoFrameCommonId) {
        for (DiscountsDto discountsDto : discountsDtos) {
            updatePhotoFrameDiscounts(discountsDto, photoFrameCommonId);
        }
    }

    private void updatePhotoFrameDiscounts(DiscountsDto dto, Long photoFrameCommonId) {
        if (validateDiscounts(dto)) {
            dto.setPhotoFrameCommonId(photoFrameCommonId);
            if (dto.getId() != null) {
                discountsService.update(dto.getId(), dto);
            } else {
                discountsService.create(dto);
            }
        } else {
            if (dto.getId() != null) {
                discountsService.delete(dto.getId());
            } else {
                // TODO: notify user about error.
            }
        }
    }

    private boolean validateDiscounts(DiscountsDto dto) {
        return dto.getPercentCount() != 0 || dto.getEndDate().after(new Date());
    }

    private boolean validateDiscounts(List<DiscountsDto> discountsDtos) {
        if (discountsDtos.isEmpty()) {
            return false;
        }

        boolean isValid = true;
        for (DiscountsDto dto : discountsDtos) {
            isValid = validateDiscounts(dto);
        }
        return isValid;
    }

    private <T extends Identity> List<T> updateEntities(List<T> dtos, BaseCrudService<T> baseCrudService) {
        for (T dto : dtos) {
            baseCrudService.update(dto.getId(), dto);
        }
        return dtos;
    }

    private List<String> getPhotosSrcs(List<PhotoFramesCommonDto> commonDtos) {
        List<String> photosSrcs = new ArrayList<>();
        for (PhotoFramesCommonDto dto : commonDtos) {
            photosSrcs.add(dto.getPhotoSrc());
        }
        return photosSrcs;
    }

    private List<Integer> getCosts(List<PhotoFramesCommonDto> commonDtos) {
        List<Integer> costs = new ArrayList<>();
        for (PhotoFramesCommonDto dto : commonDtos) {
            costs.add(dto.getCost());
        }
        return costs;
    }

    // TODO: move to the SizesService
    private List<SizesDto> getSizes(List<PhotoFramesCommonDto> commonDtos) {
        List<Long> ids = new ArrayList<>();
        for (PhotoFramesCommonDto dto : commonDtos) {
            ids.add(dto.getSizeId());
        }
        return sizesService.getSizesById(ids);
    }

    private List<ColorsDto> getColors(List<PhotoFramesCommonDto> commonDtos) {
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
