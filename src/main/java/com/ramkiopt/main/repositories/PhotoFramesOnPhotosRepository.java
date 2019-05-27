package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesOnPhotosRepository extends JpaRepository<PhotoFramesOnPhotos, Long> {
}
