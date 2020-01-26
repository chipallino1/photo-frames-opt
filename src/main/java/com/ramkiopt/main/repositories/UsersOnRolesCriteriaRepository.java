package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Roles;
import com.ramkiopt.main.entities.Users;
import com.ramkiopt.main.entities.UsersOnRoles;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UsersOnRolesCriteriaRepository {
    private final EntityManager entityManager;

    public UsersOnRolesCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getRoles(String username, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<UsersOnRoles> root = query.from(UsersOnRoles.class);
        Join<UsersOnRoles, Roles> rolesJoin = root.join("rolesByRoleId");
        Join<UsersOnRoles, Users> usersJoin = root.join("usersByUserId");
        query.select(rolesJoin.get("name")).where(criteriaBuilder.equal(usersJoin.get("email"), username));
        return entityManager.createQuery(query)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
