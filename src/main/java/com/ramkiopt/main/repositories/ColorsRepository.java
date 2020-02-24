package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorsRepository extends JpaRepository<Colors, Long> {
    @Query("SELECT DISTINCT c.name FROM Colors c")
    List<String> findAllColorsNamesDistinct();

    List<Colors> findByName(String name);
}
