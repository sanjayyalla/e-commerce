package com.jocata.data.transaction.impl;

import com.jocata.data.transaction.InvoiceDao;
import com.jocata.datamodel.transaction.entity.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class InvoiceDaoImpl implements InvoiceDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Invoice save(Invoice invoice) {
        entityManager.persist(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> findByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT i FROM Invoice i WHERE i.userId = :userId", Invoice.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Invoice findByOrderId(Long orderId) {
        return entityManager.createQuery(
                        "SELECT i FROM Invoice i WHERE i.orderId = :orderId", Invoice.class)
                .setParameter("orderId", orderId)
                .getResultStream().findFirst().orElse(null);
    }

}
