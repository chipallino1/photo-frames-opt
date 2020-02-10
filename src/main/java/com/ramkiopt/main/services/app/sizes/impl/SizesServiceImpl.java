package com.ramkiopt.main.services.app.sizes.impl;

import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.Sizes;
import com.ramkiopt.main.repositories.SizesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.sizes.SizesService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizesServiceImpl extends BaseServiceAbstract<Sizes, SizesDto> implements SizesService<SizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(SizesServiceImpl.class);
    private final SizesRepository sizesRepository;

    public SizesServiceImpl(SizesRepository sizesRepository) {
        this.sizesRepository = sizesRepository;
        setJpaRepository(sizesRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Sizes, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public SizesDto create(SizesDto dto) {
        return createInDb(new Sizes(), dto);
    }

    @Override
    public List<SizesDto> createAll(List<SizesDto> dtos) {
        return createAllInDb(dtos, Sizes.class, SizesDto.class);
    }

    @Override
    public SizesDto get(Long id) {
        return readFromDb(id, new SizesDto());
    }

    @Override
    public SizesDto update(Long id, SizesDto dto) {
        if (id == null) {
            return create(dto);
        }
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
    public List<SizesDto> getSizesById(Iterable<Long> ids) {
        List<Sizes> sizes = sizesRepository.findAllById(ids);
        List<SizesDto> sizesDtos = new ArrayList<>();
        for (int i = 0; i < sizes.size(); i++) {
            sizesDtos.add(new SizesDto());
        }
        ObjectMapper.mapListCustom(sizes, sizesDtos);
        return sizesDtos;
    }

    @Override
    public List<String> getAllFormatsDistinct() {
        return sizesRepository.findAllDistinctFormat();
    }
}
