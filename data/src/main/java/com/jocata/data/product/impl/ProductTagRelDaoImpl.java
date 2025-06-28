package com.jocata.data.product.impl;

import com.jocata.data.product.ProductTagRelDao;
import com.jocata.datamodel.product.entity.ProductTagRel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductTagRelDaoImpl implements ProductTagRelDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductTagRel save(ProductTagRel rel) {
        return em.merge(rel);
    }

    @Override
    public List<ProductTagRel> findByProductId(Integer productId) {
        return em.createQuery("FROM ProductTagRel WHERE productId = :productId", ProductTagRel.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Override
    public void deleteByProductId(Integer productId) {
        em.createQuery("DELETE FROM ProductTagRel WHERE productId = :productId")
                .setParameter("productId", productId)
                .executeUpdate();
    }
}