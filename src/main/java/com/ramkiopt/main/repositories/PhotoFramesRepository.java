package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.services.app.base.RowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesRepository extends JpaRepository<PhotoFrames, Long> {
    Page<PhotoFrames> findAllByNameLikeAndStatus(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByPopularityDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByCostDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByCost(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByBorderMaterialLikeAndStatus(String borderMaterial, RowStatus status, Pageable pageable);
}
