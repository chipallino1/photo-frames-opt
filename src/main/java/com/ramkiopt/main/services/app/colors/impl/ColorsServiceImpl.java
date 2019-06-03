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
public class ColorsServiceImpl extends BaseServiceAbstract<Colors, Long> implements ColorsService<ColorsDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(ColorsServiceImpl.class);
    @Autowired
    private ColorsRepository colorsRepository;

    @PostConstruct
    public void init() {
        setJpaRepository(colorsRepository);
        setClass(Colors.class);
        setClassDto(ColorsDto.class);
    }

    @Override
    protected void setJpaRepository(JpaRepository<Colors, Long> jpaRepository) {
        this.jpaRepository = colorsRepository;
    }

    @Override
    protected void setClass(Class<Colors> colorsClass) {
        this.tClass = colorsClass;
    }

    @Override
    protected void setClassDto(Class dtoClass) {
        this.dtoClass = dtoClass;
    }

    @Override
    public ColorsDto create(ColorsDto dto) {
        ColorsDto colorsDto = null;
        try {
            colorsDto = (ColorsDto) tryCreate(dto);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException
                | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return colorsDto;
    }

    @Override
    public ColorsDto get(Long id) {
        ColorsDto colorsDto = null;
        try {
            colorsDto = (ColorsDto) tryGetById(id);
        } catch (InvocationTargetException | NoSuchMethodException |
                InstantiationException | IllegalAccessException e) {
            LOGGER.error("Internal exception was generated.");
        }
        return colorsDto;
    }

    @Override
    public ColorsDto update(Long id, ColorsDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


}
