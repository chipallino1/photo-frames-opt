package com.ramkiopt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesRepository extends JpaRepository<PhotoFrames, Long> {
}
