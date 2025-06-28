package com.jocata.data.inventory.impl;

import com.jocata.data.inventory.InventoryDao;
import com.jocata.datamodel.inventory.entity.Inventory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Inventory save(Inventory inventory) {
        return entityManager.merge(inventory);
    }

    @Override
    public Inventory findByProductId(Integer productId) {
        return entityManager.createQuery("FROM Inventory WHERE productId = :productId", Inventory.class)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}