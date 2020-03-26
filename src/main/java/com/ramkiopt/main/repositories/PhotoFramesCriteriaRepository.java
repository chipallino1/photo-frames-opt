package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.entities.Discounts;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.entities.PhotoFramesCommon;
import com.ramkiopt.main.entities.Sizes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhotoFramesCriteriaRepository {
    private final EntityManager entityManager;

    public PhotoFramesCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PhotoFramesCommon> findByColors(List<String> colorNames, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFramesCommon> query = criteriaBuilder.createQuery(PhotoFramesCommon.class);
        Root<PhotoFramesCommon> root = query.from(PhotoFramesCommon.class);
        Join<PhotoFramesCommon, Colors> join = root.join("colorsByColorId");
        query.select(root).where(join.get("name").in(colorNames));
        return entityManager.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<PhotoFramesCommon> findBySize(String size, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFramesCommon> query = criteriaBuilder.createQuery(PhotoFramesCommon.class);
        Root<PhotoFramesCommon> root = query.from(PhotoFramesCommon.class);
        Join<PhotoFramesCommon, Sizes> join = root.join(Sizes.class.getTypeName());
        query.select(root).where(criteriaBuilder.equal(join.get("format"), size));
        return entityManager.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    public List<PhotoFrames> findWithDiscounts(Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFrames> query = criteriaBuilder.createQuery(PhotoFrames.class);
        Root<PhotoFrames> root = query.from(PhotoFrames.class);
        root.join(Discounts.class.getTypeName());
        query.select(root);
        return entityManager.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
