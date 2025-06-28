package com.jocata.data.product.impl;

import com.jocata.data.product.ProductCategoryRelDao;
import com.jocata.datamodel.product.entity.ProductCategoryRel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductCategoryRelDaoImpl implements ProductCategoryRelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductCategoryRel save(ProductCategoryRel rel) {
        return em.merge(rel);
    }

    @Override
    public List<ProductCategoryRel> findByProductId(Integer productId) {
        return em.createQuery("FROM ProductCategoryRel WHERE productId = :productId", ProductCategoryRel.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public void deleteByProductId(Integer productId) {
        em.createQuery("DELETE FROM ProductCategoryRel WHERE productId = :productId")
                .setParameter("productId", productId)
                .executeUpdate();
    }
}
