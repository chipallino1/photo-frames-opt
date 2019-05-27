package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnColors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesOnColorsRepository extends JpaRepository<PhotoFramesOnColors, Long> {
}
