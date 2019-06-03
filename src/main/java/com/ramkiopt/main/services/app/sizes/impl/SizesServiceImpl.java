package com.ramkiopt.main.services.app.sizes.impl;

import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.Sizes;
import com.ramkiopt.main.repositories.SizesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class SizesServiceImpl extends BaseServiceAbstract<Sizes, Long> implements SizesService<SizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(SizesServiceImpl.class);
    @Autowired
    private SizesRepository sizesRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(sizesRepository);
        setClass(Sizes.class);
        setClassDto(SizesDto.class);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Sizes, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    protected void setClass(Class<Sizes> sizesClass) {
        this.tClass = sizesClass;
    }

    @Override
    protected void setClassDto(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public SizesDto create(SizesDto dto) {
        SizesDto sizesDto = null;
        try {
            sizesDto = (SizesDto) tryCreate(dto);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return sizesDto;
    }

    @Override
    public SizesDto get(Long id) {
        SizesDto sizesDto = null;
        try {
            sizesDto = (SizesDto) tryGetById(id);
        } catch (InvocationTargetException | NoSuchMethodException |
                InstantiationException | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return sizesDto;
    }

    @Override
    public SizesDto update(Long id, SizesDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
