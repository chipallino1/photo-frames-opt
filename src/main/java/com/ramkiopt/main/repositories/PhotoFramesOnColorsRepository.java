package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnColors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoFramesOnColorsRepository extends JpaRepository<PhotoFramesOnColors, Long> {
    List<PhotoFramesOnColors> findAllByPhotoFrameId(Long photoFrameId);
}
