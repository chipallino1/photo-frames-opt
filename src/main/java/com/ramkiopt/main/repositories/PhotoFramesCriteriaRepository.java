package com.ramkiopt.main.repositories;

import com.ramkiopt.main.entities.Colors;
import com.ramkiopt.main.entities.PhotoFrames;
import com.ramkiopt.main.entities.PhotoFramesOnColors;
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
}
