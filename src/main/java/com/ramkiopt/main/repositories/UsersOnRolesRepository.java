package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.UsersOnRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersOnRolesRepository extends JpaRepository<UsersOnRoles, Long> {
    List<UsersOnRoles> findAllByUserId(Long userId);
}