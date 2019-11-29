package com.ramkiopt.main.services.app.photoframesonsizes;

import java.util.List;

public interface PhotoFramesOnEntityGetterService<T> {
    List<T> getEntitiesByPhotoFrameId(Long photoFrameId);
}
