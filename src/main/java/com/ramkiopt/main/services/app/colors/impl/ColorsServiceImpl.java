package com.ramkiopt.main.services.app.colors.impl;

import com.ramkiopt.main.dto.ColorsDto;
import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.repositories.ColorsRepository;
import com.ramkiopt.main.services.app.base.BaseServiceAbstract;
import com.ramkiopt.main.services.app.colors.ColorsService;
import com.ramkiopt.main.services.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorsServiceImpl extends BaseServiceAbstract<Colors, ColorsDto> implements ColorsService<ColorsDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(ColorsServiceImpl.class);
    private final ColorsRepository colorsRepository;

    public ColorsServiceImpl(ColorsRepository colorsRepository) {
        this.colorsRepository = colorsRepository;
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
    public List<ColorsDto> createAll(List<ColorsDto> dtos) {
        return createAllInDb(dtos, Colors.class, ColorsDto.class);
    }

    @Override
    public ColorsDto get(Long id) {
        return readFromDb(id, new ColorsDto());
    }

    @Override
    public ColorsDto update(Long id, ColorsDto dto) {
        if (id == null) {
            return create(dto);
        }
        return updateInDb(id, dto);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteInDb(id);
    }


    @Override
    public boolean deleteInDb(Long id) {
        return false;
    }

    @Override
    public List<ColorsDto> getColorsById(Iterable<Long> ids) {
        List<Colors> colors = colorsRepository.findAllById(ids);
        List<ColorsDto> colorsDtos = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            colorsDtos.add(new ColorsDto());
        }
        ObjectMapper.mapListCustom(colors, colorsDtos);
        return colorsDtos;
    }

    @Override
    public List<String> getAllColorNamesDistinct() {
        return colorsRepository.findAllColorsNamesDistinct();
    }

    @Override
    public ColorsDto getByName(String name) {
        ColorsDto colorsDto = new ColorsDto();
        ObjectMapper.mapCustom(colorsRepository.findByName(name), colorsDto);
        return colorsDto;
    }
}
