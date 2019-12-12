package com.ramkiopt.main.services.app.discounts;

import com.ramkiopt.main.services.app.base.BaseCrudService;

public interface DiscountsCrudService<T> extends BaseCrudService<T> {
    Boolean deleteByPhotoFrameId(Long id);
}
