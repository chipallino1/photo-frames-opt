package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFrames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesRepository extends JpaRepository<PhotoFrames, Long> {
}
