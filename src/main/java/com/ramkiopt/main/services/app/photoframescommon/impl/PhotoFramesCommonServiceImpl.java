package com.ramkiopt.main.services.app.photoframescommon.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.dto.PhotoFramesCommonDto;
import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.PhotoFramesCommon;
import com.ramkiopt.main.repositories.PhotoFramesCommonRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.photoframescommon.PhotoFramesCommonService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoFramesCommonServiceImpl extends BaseServiceAbstract<PhotoFramesCommon, PhotoFramesCommonDto>
        implements PhotoFramesCommonService<PhotoFramesCommonDto> {
    private final PhotoFramesCommonRepository commonRepository;

    public PhotoFramesCommonServiceImpl(PhotoFramesCommonRepository commonRepository) {
        this.commonRepository = commonRepository;
        setJpaRepository(commonRepository);
    }

    @Override
    public PhotoFramesCommonDto create(PhotoFramesCommonDto dto) {
        return createInDb(new PhotoFramesCommon(), dto);
    }

    @Override
    public List<PhotoFramesCommonDto> createAll(List<PhotoFramesCommonDto> dtos) {
        return createAllInDb(dtos, PhotoFramesCommon.class, PhotoFramesCommonDto.class);
    }

    @Override
    public PhotoFramesCommonDto get(Long id) {
        return readFromDb(id, new PhotoFramesCommonDto());
    }

    @Override
    public PhotoFramesCommonDto update(Long id, PhotoFramesCommonDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<PhotoFramesCommonDto> getEntitiesByPhotoFrameId(Long id) {
        List<PhotoFramesCommon> commons = commonRepository.findAllByPhotoFrameId(id);
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
}
