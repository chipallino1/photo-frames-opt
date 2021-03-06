package com.ramkiopt.main.services.app.discounts.impl;


import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.entities.Discounts;
import com.ramkiopt.main.repositories.DiscountsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.discounts.DiscountsService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiscountsServiceImpl extends BaseServiceAbstract<Discounts, DiscountsDto>
        implements DiscountsService<DiscountsDto> {

    private final DiscountsRepository discountsRepository;

    public DiscountsServiceImpl(DiscountsRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
        setJpaRepository(discountsRepository);
    }

    @Override
    public DiscountsDto create(DiscountsDto dto) {
        return createInDb(new Discounts(), dto);
    }

    @Override
    public List<DiscountsDto> createAll(List<DiscountsDto> dtos) {
        return createAllInDb(dtos, Discounts.class, DiscountsDto.class);
    }

    @Override
    public DiscountsDto get(Long id) {
        return readFromDb(id, new DiscountsDto());
    }

    @Override
    public DiscountsDto update(Long id, DiscountsDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }

    @Override
    public boolean deleteInDb(Long id) {
        discountsRepository.deleteById(id);
        return !discountsRepository.existsById(id);
    }

    @Override
    public DiscountsDto getByPhotoFrameCommonId(Long photoFrameId) {
        Discounts discounts = discountsRepository.findFirstByPhotoFrameCommonId(photoFrameId);
        if (discounts == null) {
            return null;
        }
        if (discounts.getEndDate().before(new Date())) {
            discountsRepository.deleteById(discounts.getId());
            return null;
        }
        DiscountsDto discountsDto = new DiscountsDto();
        ObjectMapper.mapCustom(discounts, discountsDto);
        return discountsDto;
    }

    @Override
    public List<DiscountsDto> getByPhotoFrameCommonIds(List<Long> ids) {
        return ObjectMapper.mapListLambda(discountsRepository.findAllByPhotoFrameCommonIdIn(ids), DiscountsDto.class);
    }

    @Override
    public Boolean deleteByPhotoFrameId(Long id) {
        Discounts discounts = discountsRepository.findFirstByPhotoFrameCommonId(id);
        if (discounts == null) {
            return true;
        }
        discountsRepository.delete(discounts);
        return true;
    }
}
