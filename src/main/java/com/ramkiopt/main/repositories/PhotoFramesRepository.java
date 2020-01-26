package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.services.app.base.RowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesRepository extends JpaRepository<PhotoFrames, Long> {
    Page<PhotoFrames> findAllByNameLikeAndRowStatus(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndRowStatusOrderByPopularityDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndRowStatusOrderByCostDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndRowStatusOrderByCost(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByBorderMaterialLikeAndRowStatus(String borderMaterial, RowStatus status, Pageable pageable);
}
