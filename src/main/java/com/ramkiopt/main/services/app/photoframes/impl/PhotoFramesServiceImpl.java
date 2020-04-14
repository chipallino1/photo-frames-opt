package com.ramkiopt.main.services.app.photoframes.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.dto.PhotoFramesCommonDto;
import com.ramkiopt.main.dto.PhotoFramesDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.entities.PhotoFramesCommon;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    private Iterable<Long> getIterableByPhotoFrameId(List<PhotoFramesCommon> list) {
        List<Long> iterable = new ArrayList<>();
        list.forEach(item -> {
            if (!iterable.contains(item.getPhotoFrameId())) {
                iterable.add(item.getPhotoFrameId());
            }
        });
        return iterable;
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
    public List<PhotoFramesDto> createAll(List<PhotoFramesDto> dtos) {
        return null;
    }

    @Override
    public PhotoFramesDto get(Long id) {
        PhotoFramesDto dto = readFromDb(id, new PhotoFramesDto());
        if (dto == null || dto.getRowStatus().equals(RowStatus.DELETED)) {
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
        photoFrames.setRowStatus(RowStatus.DELETED);
        jpaRepository.save(photoFrames);
        return true;
    }

    @Override
    public List<PhotoFramesDto> getAllByName(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage = photoFramesRepository.findAllByNameLikeAndRowStatus(name, RowStatus.ENABLE,
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
    public List<PhotoFramesDto> getByColors(List<String> color, Integer pageNum, Integer pageSize) {
        return getAllByIds(photoFramesCriteriaRepository.findByColors(color, pageNum, pageSize));
    }

    @Override
    public List<PhotoFramesDto> getBySizes(List<String> sizes, Integer pageNum, Integer pageSize) {
        return getAllByIds(photoFramesCriteriaRepository.findBySizes(sizes, pageNum, pageSize));
    }

    @Override
    public List<PhotoFramesDto> getByBorderMaterials(List<String> borderMaterials, Pageable pageable) {
        return ObjectMapper.mapListLambda(photoFramesRepository
                .findAllByBorderMaterialIsIn(borderMaterials, pageable).getContent(), PhotoFramesDto.class);
    }

    @Override
    public List<PhotoFramesDto> getByInsideMaterials(List<String> insideMaterials, Pageable pageable) {
        return ObjectMapper.mapListLambda(photoFramesRepository
                .findAllByBorderMaterialIsIn(insideMaterials, pageable).getContent(), PhotoFramesDto.class);
    }

    @Override
    public List<PhotoFramesDto> getByAllParameters(List<String> colors, List<String> sizes, List<String> insideMaterials,
                                                   List<String> borderMaterials, Integer pageNumber, Integer pageSize) {
        return getAllByIds(photoFramesCriteriaRepository.findByAllParameters(colors, sizes, insideMaterials,
                borderMaterials, pageNumber, pageSize));
    }

    private List<PhotoFramesDto> getAllByIds(List<PhotoFramesCommon> photoFramesCommon) {
        List<PhotoFrames> photoFrames =
                photoFramesRepository.findAllById(getIterableByPhotoFrameId(photoFramesCommon));
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        List<PhotoFramesCommonDto> commonDtos = setUpPhotoFramesCommonDto(photoFramesCommon);
        for (PhotoFrames photoFrame : photoFrames) {
            PhotoFramesDto photoFramesDto = new PhotoFramesDto();
            photoFramesDtos.add(photoFramesDto);
            photoFramesDto.setCommonDtos(
                    Collections.singletonList(findFirstByPhotoFrameId(photoFrame.getId(), commonDtos)));
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    private PhotoFramesCommonDto findFirstByPhotoFrameId(Long photoFrameId, List<PhotoFramesCommonDto> commonDtos) {
        for (PhotoFramesCommonDto commonDto : commonDtos) {
            if (Objects.equals(commonDto.getPhotoFrameId(), photoFrameId)) {
                return commonDto;
            }
        }
        return null;
    }

    private List<PhotoFramesCommonDto> setUpPhotoFramesCommonDto(List<PhotoFramesCommon> commons) {
        List<PhotoFramesCommonDto> commonDtos = ObjectMapper.mapListLambda(commons, PhotoFramesCommonDto.class);
        for (int i = 0; i < commonDtos.size(); i++) {
            setColorsDto(commons.get(i), commonDtos.get(i));
            setSizesDto(commons.get(i), commonDtos.get(i));
            if (commons.get(i).getDiscountsById() != null) {
                setDiscountsDto(commons.get(i), commonDtos.get(i));
            }
        }
        return commonDtos;
    }

    private void setColorsDto(PhotoFramesCommon common, PhotoFramesCommonDto commonDto) {
        ColorsDto colorsDto = new ColorsDto();
        ObjectMapper.mapCustom(common.getColorsByColorId(), colorsDto);
        commonDto.setColorsDto(colorsDto);
    }

    private void setSizesDto(PhotoFramesCommon common, PhotoFramesCommonDto commonDto) {
        SizesDto sizesDto = new SizesDto();
        ObjectMapper.mapCustom(common.getSizesBySizeId(), sizesDto);
        commonDto.setSizesDto(sizesDto);
    }

    private void setDiscountsDto(PhotoFramesCommon common, PhotoFramesCommonDto commonDto) {
        DiscountsDto discountsDto = new DiscountsDto();
        ObjectMapper.mapCustom(common.getDiscountsById(), discountsDto);
        commonDto.setDiscountsDto(discountsDto);
    }

    @Override
    public List<PhotoFramesDto> getAllByNameOrderByPopularityDesc(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage =
                photoFramesRepository.findAllByNameLikeAndRowStatusOrderByPopularityDesc(name, RowStatus.ENABLE, pageable);
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

    @Override
    public List<PhotoFramesDto> getAllOrderByCostDesc(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage =
                photoFramesRepository.findAllByNameLikeAndRowStatusOrderByCostDesc(name, RowStatus.ENABLE, pageable);
        List<PhotoFrames> photoFrames = photoFramesPage.getContent();
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<PhotoFramesDto> getAllOrderByCost(String name, Pageable pageable) {
        Page<PhotoFrames> photoFramesPage =
                photoFramesRepository.findAllByNameLikeAndRowStatusOrderByCost(name, RowStatus.ENABLE, pageable);
        List<PhotoFrames> photoFrames = photoFramesPage.getContent();
        List<PhotoFramesDto> photoFramesDtos = new ArrayList<>();
        for (int i = 0; i < photoFrames.size(); i++) {
            photoFramesDtos.add(new PhotoFramesDto());
        }
        ObjectMapper.mapListCustom(photoFrames, photoFramesDtos);
        return photoFramesDtos;
    }

    @Override
    public List<String> readAllInsideMaterials() {
        return photoFramesRepository.findAllInsideMaterials();
    }

    @Override
    public List<String> readAllBorderMaterials() {
        return photoFramesRepository.findAllBorderMaterials();
    }
}
