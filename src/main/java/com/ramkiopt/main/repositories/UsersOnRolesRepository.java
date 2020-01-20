package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.UsersOnRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersOnRolesRepository extends JpaRepository<UsersOnRoles, Long> {
}
