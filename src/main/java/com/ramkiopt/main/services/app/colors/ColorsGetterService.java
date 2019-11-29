package com.ramkiopt.main.services.app.colors;

import com.ramkiopt.main.dto.ColorsDto;

import java.util.List;

public interface ColorsGetterService {
    List<ColorsDto> getColorsById(Iterable<Long> ids);
}
