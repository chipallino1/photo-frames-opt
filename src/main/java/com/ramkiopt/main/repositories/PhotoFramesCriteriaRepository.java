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
import javax.persistence.criteria.Root;
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
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<PhotoFramesCommon> findBySizes(List<String> sizes, Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFramesCommon> query = criteriaBuilder.createQuery(PhotoFramesCommon.class);
        Root<PhotoFramesCommon> root = query.from(PhotoFramesCommon.class);
        Join<PhotoFramesCommon, Sizes> join = root.join("sizesBySizeId");
        query.select(root).where(join.get("format").in(sizes));
        return entityManager.createQuery(query)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<PhotoFramesCommon> findByAllParameters(List<String> colors, List<String> sizes,
                                                       List<String> insideMaterials, List<String> borderMaterials,
                                                       Integer pageNumber, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhotoFramesCommon> query = criteriaBuilder.createQuery(PhotoFramesCommon.class);
        Root<PhotoFramesCommon> root = query.from(PhotoFramesCommon.class);
        Join<PhotoFramesCommon, Sizes> joinSizes = root.join("sizesBySizeId");
        Join<PhotoFramesCommon, Sizes> joinColors = root.join("colorsByColorId");
        Join<PhotoFramesCommon, Sizes> joinPhotoFrames = root.join("photoFramesByPhotoFrameId");
        query.select(root).where(criteriaBuilder.and(joinColors.get("name").in(colors),
                joinSizes.get("format").in(sizes), joinPhotoFrames.get("insideMaterial").in(insideMaterials),
                joinPhotoFrames.get("borderMaterial").in(borderMaterials)));
        return entityManager.createQuery(query)
                .setFirstResult(pageNumber * pageSize)
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
