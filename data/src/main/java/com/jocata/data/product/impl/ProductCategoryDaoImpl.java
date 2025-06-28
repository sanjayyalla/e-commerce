package com.jocata.data.product.impl;

import com.jocata.data.product.ProductCategoryDao;
import com.jocata.datamodel.product.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductCategoryDaoImpl implements ProductCategoryDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductCategory save(ProductCategory category) {
        return em.merge(category);
    }

    @Override
    public ProductCategory findById(Integer id) {
        return em.find(ProductCategory.class, id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return em.createQuery("FROM ProductCategory", ProductCategory.class).getResultList();
    }

    @Override
    public String deleteById(Integer id) {
        ProductCategory c = findById(id);
        if (c != null) em.remove(c);
        return "Deleted";
    }
}
