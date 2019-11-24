package com.ramkiopt.main.services.app.colors.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.repositories.ColorsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.colors.ColorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class ColorsServiceImpl extends BaseServiceAbstract<Colors, ColorsDto> implements ColorsService<ColorsDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(ColorsServiceImpl.class);
    private final ColorsRepository colorsRepository;

    public ColorsServiceImpl(ColorsRepository colorsRepository) {
        this.colorsRepository = colorsRepository;
    }

    @PostConstruct
    public void init() {
        setJpaRepository(colorsRepository);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Colors, Long> jpaRepository) {
        this.jpaRepository = colorsRepository;
    }

    @Override
    public ColorsDto create(ColorsDto dto) {
        return createInDb(new Colors(), dto);
    }

    @Override
    public ColorsDto get(Long id) {
        return readFromDb(id, new ColorsDto());
    }

    @Override
    public ColorsDto update(Long id, ColorsDto dto) {
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }


}
