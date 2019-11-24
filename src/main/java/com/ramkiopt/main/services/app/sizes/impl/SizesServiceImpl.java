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
public class SizesServiceImpl extends BaseServiceAbstract<Sizes, SizesDto> implements SizesService<SizesDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(SizesServiceImpl.class);
    @Autowired
    private SizesRepository sizesRepository;

    @PostConstruct
    public void init() {
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
    public SizesDto get(Long id) {
        return readFromDb(id, new SizesDto());
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
