package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountsRepository extends JpaRepository<Discounts, Long> {
    Discounts findFirstByPhotoFrameCommonId(Long photoFrameId);
    List<Discounts> findAllByPhotoFrameCommonIdIn(List<Long> ids);
}
