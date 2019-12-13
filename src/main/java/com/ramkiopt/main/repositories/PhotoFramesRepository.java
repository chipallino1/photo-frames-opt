package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.services.app.base.RowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PhotoFramesRepository extends JpaRepository<PhotoFrames, Long> {
    Page<PhotoFrames> findAllByNameLikeAndStatus(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByPopularityDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByCostDesc(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByNameLikeAndStatusOrderByCost(String name, RowStatus status, Pageable pageable);

    Page<PhotoFrames> findAllByBorderMaterialLikeAndStatus(String borderMaterial, RowStatus status, Pageable pageable);

   /* @Query(value = "SELECT pf.id, c.id FROM photo_frames pf" +
            " INNER JOIN photo_frames_on_colors pfc on pf.id=pfc.photo_frame_id " +
            " INNER JOIN colors c ON  pfc.color_id=c.id WHERE pf.border_material=:border_material " +
            " AND pf.inside_material = :inside_material" +
            " AND pf.cost BETWEEN :low_cost AND :high_cost" +
            " AND pf.border_width = :border_width" +
            " AND c.name = :color_name",
            countQuery = "SELECT count(*)" +
                    " INNER JOIN photo_frames_on_colors pfc on pf.id=pfc.photo_frame_id " +
                    " INNER JOIN colors c ON  pfc.color_id=c.id WHERE pf.border_material=:border_material " +
                    " AND pf.inside_material = :inside_material" +
                    " AND pf.cost BETWEEN :low_cost AND :high_cost" +
                    " AND pf.border_width = :border_width" +
                    " AND c.name = :color_name", nativeQuery = true)
    List<Map<String, Object>> findAllByAllParams(@Param("border_material") String borderMaterial,
                                                 @Param("inside_material"));*/
}
