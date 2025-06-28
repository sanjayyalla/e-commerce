package com.jocata.data.product.impl;

import com.jocata.data.product.ProductDetailDao;
import com.jocata.datamodel.product.entity.ProductDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ProductDetailDaoImpl implements ProductDetailDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductDetail save(ProductDetail detail) {
        return entityManager.merge(detail);
    }

    @Override
    public ProductDetail findByProductId(Integer productId) {
        return entityManager.createQuery("FROM ProductDetail WHERE product.id = :productId", ProductDetail.class)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}