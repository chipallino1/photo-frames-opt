package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnSizes;
import com.ramkiopt.main.entities.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoFramesOnSizesRepository extends JpaRepository<PhotoFramesOnSizes, Long> {
    List<PhotoFramesOnSizes> findAllByPhotoFrameId(Long photoFrameId);
}
