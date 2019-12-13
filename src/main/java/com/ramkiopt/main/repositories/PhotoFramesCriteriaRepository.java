package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.entities.PhotoFramesOnColors;
import com.ramkiopt.main.entities.PhotoFramesOnSizes;
import com.ramkiopt.main.entities.Sizes;
import com.ramkiopt.main.services.app.base.RowStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PhotoFramesCriteriaRepository {
    @Autowired
    private EntityManager entityManager;

    public List<PhotoFrames> findBooksByAuthorNameAndTitle(String borderMaterial, String insideMaterial,
                                                           Integer minCost,
                                                           Integer maxCost,
                                                           Integer minBorderWidth,
                                                           Integer maxBorderWidth,
                                                           String colorName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFrames> cq = cb.createQuery(PhotoFrames.class);
        Root<PhotoFrames> photoFramesRoot = cq.from(PhotoFrames.class);
        Join<PhotoFrames, PhotoFramesOnColors> photoFramesOnColorsJoin = photoFramesRoot.join("photoFramesOnColorsById");
        Join<PhotoFramesOnColors, Colors> colorsJoin = photoFramesOnColorsJoin.join("colorsByColorId");
        Predicate costPredicate = cb.between(photoFramesRoot.get("cost"), minCost, maxCost);
        Predicate borderWidthPredicate = cb.between(photoFramesRoot.get("borderWidth"), minBorderWidth, maxBorderWidth);
        Predicate insideMaterialPredicate = cb.like(photoFramesRoot.get("insideMaterial"), "%" + insideMaterial + "%");
        Predicate borderMaterialPredicate = cb.like(photoFramesRoot.get("borderMaterial"), "%" + borderMaterial + "%");
        Predicate colorPredicate = cb.like(colorsJoin.get("name"), "%" + colorName + "%");
        cq.where(insideMaterialPredicate, borderMaterialPredicate, costPredicate, borderWidthPredicate, colorPredicate);
        TypedQuery query = entityManager.createQuery(cq);
        return null;
    }

    public List<PhotoFrames> findByColor(String colorName, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFrames> cq = cb.createQuery(PhotoFrames.class);
        Root<PhotoFrames> photoFramesRoot = cq.from(PhotoFrames.class);
        Join<PhotoFrames, PhotoFramesOnColors> photoFramesOnColorsJoin = photoFramesRoot.join("photoFramesOnColorsById");
        Join<PhotoFramesOnColors, Colors> colorsJoin = photoFramesOnColorsJoin.join("colorsByColorId");
        Predicate statusPredicate = cb.equal(photoFramesRoot.get("status"), cb.literal(RowStatus.ENABLE));
        Predicate colorPredicate = cb.like(colorsJoin.get("name"), "%" + colorName + "%");
        cq.where(statusPredicate, colorPredicate);
        TypedQuery query = entityManager.createQuery(cq);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<PhotoFrames> findBySize(String size, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFrames> cq = cb.createQuery(PhotoFrames.class);
        Root<PhotoFrames> photoFramesRoot = cq.from(PhotoFrames.class);
        Join<PhotoFrames, PhotoFramesOnSizes> photoFramesOnColorsJoin = photoFramesRoot.join("photoFramesOnSizesById");
        Join<PhotoFramesOnSizes, Sizes> colorsJoin = photoFramesOnColorsJoin.join("sizesBySizeId");
        Predicate statusPredicate = cb.equal(photoFramesRoot.get("status"), cb.literal(RowStatus.ENABLE));
        Predicate colorPredicate = cb.like(colorsJoin.get("format"), "%" + size + "%");
        cq.where(statusPredicate, colorPredicate);
        TypedQuery query = entityManager.createQuery(cq);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
