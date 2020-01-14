package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesCommonRepository extends JpaRepository<PhotoFramesCommon, Long> {
}
