package com.ramkiopt.main.services.app.discounts;

import java.util.List;

public interface DiscountsGetterService<T> {
    T getByPhotoFrameCommonId(Long photoFrameId);
    List<T> getByPhotoFrameCommonIds(Iterable<Long> ids);
}
