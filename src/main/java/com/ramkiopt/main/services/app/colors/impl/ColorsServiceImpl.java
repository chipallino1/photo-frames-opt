package com.ramkiopt.main.services.app.colors.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.repositories.ColorsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.colors.ColorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Service
public class ColorsServiceImpl extends BaseServiceAbstract<Colors, Long> implements ColorsService<ColorsDto> {

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
    public Boolean create(ColorsDto dto) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        return tryCreate(dto);
    }

    @Override
    public ColorsDto get(Long id) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        return (ColorsDto) tryGetById(id);
    }

    @Override
    public Boolean update(Long id, ColorsDto dto) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


}
