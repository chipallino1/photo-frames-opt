package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.PhotoFramesOnCarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoFramesOnCartsRepository extends JpaRepository<PhotoFramesOnCarts, Long> {
}
