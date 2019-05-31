package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnSizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesOnSizesRepository extends JpaRepository<PhotoFramesOnSizes, Long> {
}
