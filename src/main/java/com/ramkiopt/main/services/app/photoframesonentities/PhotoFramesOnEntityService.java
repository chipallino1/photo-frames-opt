package com.ramkiopt.main.services.app.photoframesonentities;

import com.ramkiopt.main.services.app.base.BaseCrudService;

public interface PhotoFramesOnEntityService<T> extends BaseCrudService<T>,
        PhotoFramesOnEntityGetterService<T> {
}
