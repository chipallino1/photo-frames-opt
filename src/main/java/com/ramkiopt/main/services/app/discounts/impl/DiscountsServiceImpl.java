package com.ramkiopt.main.services.app.discounts.impl;

import com.ramkiopt.main.dto.DiscountsDto;
import com.ramkiopt.main.entities.Discounts;
import com.ramkiopt.main.repositories.DiscountsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.discounts.DiscountsService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DiscountsServiceImpl extends BaseServiceAbstract<Discounts, DiscountsDto>
        implements DiscountsService<DiscountsDto> {

    @Autowired
    private DiscountsRepository discountsRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(discountsRepository);
    }

    @Override
    public DiscountsDto create(DiscountsDto dto) {
        return createInDb(new Discounts(), dto);
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
    public DiscountsDto getByPhotoFrameId(Long photoFrameId) {
        Discounts discounts = discountsRepository.findFirstByPhotoFrameId(photoFrameId);
        if (discounts == null) {
            return null;
        }
        DiscountsDto discountsDto = new DiscountsDto();
        ObjectMapper.mapCustom(discounts, discountsDto);
        return discountsDto;
    }

    @Override
    public Boolean deleteByPhotoFrameId(Long id) {
        Discounts discounts = discountsRepository.findFirstByPhotoFrameId(id);
        if (discounts == null) {
            return true;
        }
        discountsRepository.delete(discounts);
        return true;
    }
}
