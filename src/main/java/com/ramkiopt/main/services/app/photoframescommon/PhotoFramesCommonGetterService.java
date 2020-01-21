package com.ramkiopt.main.services.app.photoframescommon;

import java.util.List;

public interface PhotoFramesCommonGetterService<T> {
    List<T> getEntitiesByPhotoFrameId(Long id);
}
