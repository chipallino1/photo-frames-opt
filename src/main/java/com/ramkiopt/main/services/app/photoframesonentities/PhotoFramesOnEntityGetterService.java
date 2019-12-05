package com.ramkiopt.main.services.app.photoframesonentities;

import java.util.List;

public interface PhotoFramesOnEntityGetterService<T> {
    List<T> getEntitiesByPhotoFrameId(Long photoFrameId);
}
