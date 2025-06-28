package com.jocata.data.product.impl;

import com.jocata.data.product.ProductDao;
import com.jocata.datamodel.product.entity.Product;
import com.jocata.datamodel.product.entity.ProductCategoryRel;
import com.jocata.datamodel.product.entity.ProductTagRel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public Product findById(Integer id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public String deleteById(Integer id) {
        Product p = findById(id);
        if (p != null){
            entityManager.remove(p);
        }
        return "Deleted";
    }

    @Override
    public void saveCategoryRel(ProductCategoryRel rel) {
        entityManager.persist(rel);
    }

    @Override
    public void saveTagRel(ProductTagRel rel) {
        entityManager.persist(rel);
    }

    @Override
    public List<ProductCategoryRel> findCategoryRelations(Integer productId) {
        return entityManager.createQuery("SELECT r FROM ProductCategoryRel r WHERE r.product.id = :pid", ProductCategoryRel.class)
                .setParameter("pid", productId)
                .getResultList();
    }

    @Override
    public List<ProductTagRel> findTagRelations(Integer productId) {
        return entityManager.createQuery("SELECT r FROM ProductTagRel r WHERE r.product.id = :pid", ProductTagRel.class)
                .setParameter("pid", productId)
                .getResultList();
    }
}
