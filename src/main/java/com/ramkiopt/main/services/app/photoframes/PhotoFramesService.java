package com.ramkiopt.main.services.app.photoframes;

import java.util.List;

public interface PhotoFramesService<T> extends PhotoFramesCrudService<T>, PhotoFramesGetterService<T> {
    List<String> readAllInsideMaterials();

    List<String> readAllBorderMaterials();
}
