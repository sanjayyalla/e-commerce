package com.jocata.data.transaction.impl;

import com.jocata.data.transaction.TransactionDao;
import com.jocata.datamodel.transaction.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Transaction save(Transaction transaction) {
        return  entityManager.merge(transaction);
    }

    @Override
    public List<Transaction> findByOrderId(Long orderId) {
        return entityManager.createQuery(
                        "SELECT t FROM Transaction t WHERE t.orderId = :orderId", Transaction.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

}
