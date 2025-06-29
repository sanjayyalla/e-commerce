package com.jocata.data.transaction.impl;

import com.jocata.data.transaction.InvoiceDao;
import com.jocata.datamodel.transaction.entity.Invoice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class InvoiceDaoImpl implements InvoiceDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Invoice save(Invoice invoice) {
        entityManager.persist(invoice);
        return invoice;
    }
}
