package com.ramkiopt.main.services.app.photos;

import com.ramkiopt.main.services.app.base.BaseCrudService;

public interface PhotosCrudService<T> extends BaseCrudService<T> {
    T getByPhotoFrameId(Long id);
}
