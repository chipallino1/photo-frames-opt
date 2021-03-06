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
import com.ramkiopt.main.services.utils.FileStorageService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoFramesStructureServiceImpl implements PhotoFramesStructureService {
    private final PhotoFramesService<PhotoFramesDto> photoFramesService;
    private final ColorsService<ColorsDto> colorsService;
    private final SizesService<SizesDto> sizesService;
    private final DiscountsService<DiscountsDto> discountsService;
    private final PhotoFramesCommonService<PhotoFramesCommonDto> photoFramesCommonService;
    private final FileStorageService fileStorageService;

    public PhotoFramesStructureServiceImpl(
            PhotoFramesService<PhotoFramesDto> photoFramesService,
            ColorsService<ColorsDto> colorsService,
            SizesService<SizesDto> sizesService,
            DiscountsService<DiscountsDto> discountsService,
            PhotoFramesCommonService<PhotoFramesCommonDto> photoFramesCommonService,
            FileStorageService fileStorageService) {
        this.photoFramesService = photoFramesService;
        this.colorsService = colorsService;
        this.sizesService = sizesService;
        this.discountsService = discountsService;
        this.photoFramesCommonService = photoFramesCommonService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public PhotoFramesDto createPhotoFrame(PhotoFramesDto dto) {
        photoFramesService.create(dto);
        createPhotoFrameStructure(dto);
        List<DiscountsDto> discountsDtos = getDiscountsDtos(dto.getCommonDtos());
        if (validateDiscounts(discountsDtos)) {
            createEntities(discountsDtos, discountsService);
        }
        return dto;
    }

    private void createPhotoFrameStructure(PhotoFramesDto dto) {
        List<PhotoFramesCommonDto> commonDtos = dto.getCommonDtos();
        createSizes(getSizesDtos(commonDtos));
        createColors(getColorsDtos(commonDtos));
        dto.getCommonDtos().forEach(item -> {
            item.setPhotoSrc(fileStorageService.storeFile(item.getImageFile()));
            item.setPhotoFrameId(dto.getId());
            item.setColorId(item.getColorId() != null ? item.getColorId() : item.getColorsDto().getId());
            item.setSizeId(item.getSizeId() != null ? item.getSizeId() : item.getSizesDto().getId());
        });
        createEntities(dto.getCommonDtos(), photoFramesCommonService);
    }


    private List<ColorsDto> getColorsDtos(List<PhotoFramesCommonDto> commonDtos) {
        return commonDtos
                .stream()
                .collect(ArrayList::new, (list, item) -> list.add(item.getColorsDto()), ArrayList::addAll);
    }

    private List<SizesDto> getSizesDtos(List<PhotoFramesCommonDto> commonDtos) {
        return commonDtos
                .stream()
                .collect(ArrayList::new, (list, item) -> list.add(item.getSizesDto()), ArrayList::addAll);
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
    public List<PhotoFramesDto> readAllByColors(List<String> colorNames, Integer pageNum, Integer pageSize) {
        return photoFramesService.getByColors(colorNames, pageNum, pageSize);
    }

    @Override
    public List<PhotoFramesDto> readAllBySizes(List<String> sizes, Integer pageNum, Integer pageSize) {
        return photoFramesService.getBySizes(sizes, pageNum, pageSize);
    }

    @Override
    public List<PhotoFramesDto> readAllByAllParams(List<String> colors, List<String> sizes, List<String> insideMaterials,
                                                   List<String> borderMaterials, Integer pageNumber, Integer pageSize) {
        return photoFramesService.getByAllParameters(colors, sizes, insideMaterials,
                borderMaterials, pageNumber, pageSize);
    }

    @Override
    public List<String> readAllInsideMaterials() {
        return photoFramesService.readAllInsideMaterials();
    }

    @Override
    public List<String> readAllBorderMaterials() {
        return photoFramesService.readAllBorderMaterials();
    }

    private void setUpPhotoFramesDto(PhotoFramesDto photoFramesDto) {
        photoFramesDto.setCommonDtos(photoFramesCommonService.getEntitiesByPhotoFrameId(photoFramesDto.getId()));
    }

    private <T extends Identity> List<Long> getIds(List<T> commonDtos) {
        return commonDtos.stream().collect(ArrayList::new, (list, item) -> list.add(item.getId()), ArrayList::addAll);
    }

    @Override
    public PhotoFramesDto updatePhotoFrame(Long id, PhotoFramesDto dto) {
        List<PhotoFramesCommonDto> commonDtos = dto.getCommonDtos();
        List<ColorsDto> colorsDtos = getColorsDtos(commonDtos);
        List<SizesDto> sizesDtos = getSizesDtos(commonDtos);
        if (!colorsDtos.isEmpty() && !sizesDtos.isEmpty()) {
            createPhotoFrameStructure(dto);
        }

        List<DiscountsDto> discountsDtos = getDiscountsDtos(commonDtos);
        if (!discountsDtos.isEmpty()) {
            updatePhotoFrameDiscounts(discountsDtos, dto.getId());
        }
        dto = photoFramesService.update(id, dto);
        return dto;
    }

    @Override
    public Boolean deletePhotoFrame(Long id) {
        return photoFramesService.delete(id);
    }

    @Override
    public List<PhotoFramesDto> readAllByBorderMaterials(List<String> borderMaterials, Pageable pageable) {
        return photoFramesService.getByBorderMaterials(borderMaterials, pageable);
    }

    @Override
    public List<PhotoFramesDto> readAllByInsideMaterials(List<String> borderMaterials, Pageable pageable) {
        return photoFramesService.getByInsideMaterials(borderMaterials, pageable);
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

    private List<ColorsDto> createColors(List<ColorsDto> dtos) {
        Map<String, Long> existingColors = new HashMap<>();
        dtos.forEach((item) -> {
            item.setId(existingColors.get(item.getName()));
            if (item.getId() == null) {
                colorsService.create(item);
                existingColors.put(item.getName(), item.getId());
            }
        });
        return dtos;
    }

    private List<SizesDto> createSizes(List<SizesDto> dtos) {
        Map<String, Long> existingSizes = new HashMap<>();
        dtos.forEach((item) -> {
            item.setId(existingSizes.get(item.getFormat()));
            if (item.getId() == null) {
                sizesService.create(item);
                existingSizes.put(item.getFormat(), item.getId());
            }
        });
        return dtos;
    }
}
