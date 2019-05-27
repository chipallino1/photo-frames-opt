package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizesRepository extends JpaRepository<Sizes, Long> {
}
