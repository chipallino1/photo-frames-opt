package com.ramkiopt.main.services.app.sizes.impl;

import com.ramkiopt.main.dto.SizesDto;
import com.ramkiopt.main.entities.Sizes;
import com.ramkiopt.main.repositories.SizesRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.sizes.SizesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class SizesServiceImpl extends BaseServiceAbstract<Sizes, Long> implements SizesService<SizesDto> {

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
    public Boolean create(SizesDto dto) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        return tryCreate(dto);
    }

    @Override
    public SizesDto get(Long id) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        return (SizesDto) tryGetById(id);
    }

    @Override
    public Boolean update(Long id, SizesDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
