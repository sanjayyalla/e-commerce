package com.jocata.data.product.impl;

import com.jocata.data.product.ProductTagDao;
import com.jocata.datamodel.product.entity.ProductTag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductTagDaoImpl implements ProductTagDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ProductTag save(ProductTag tag) {
        return em.merge(tag);
    }

    @Override
    public ProductTag findById(Integer id) {
        return em.find(ProductTag.class, id);
    }

    @Override
    public List<ProductTag> findAll() {
        return em.createQuery("FROM ProductTag", ProductTag.class).getResultList();
    }

    @Override
    public String deleteById(Integer id) {
        ProductTag t = findById(id);
        if (t != null) em.remove(t);
        return "Deleted";
    }
}
