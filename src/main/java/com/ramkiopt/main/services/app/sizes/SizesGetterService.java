package com.ramkiopt.main.services.app.sizes;

import com.ramkiopt.main.dto.SizesDto;

import java.util.List;

public interface SizesGetterService {
    List<SizesDto> getSizesById(Iterable<Long> ids);
}
