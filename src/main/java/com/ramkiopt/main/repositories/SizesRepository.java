package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizesRepository extends JpaRepository<Sizes, Long> {
    @Query("SELECT DISTINCT s.format FROM Sizes s")
    List<String> findAllDistinctFormat();
}
