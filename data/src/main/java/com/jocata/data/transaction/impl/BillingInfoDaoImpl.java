package com.jocata.data.transaction.impl;

import com.jocata.data.transaction.BillingInfoDao;
import com.jocata.datamodel.transaction.entity.BillingInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BillingInfoDaoImpl implements BillingInfoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BillingInfo save(BillingInfo info) {
        entityManager.persist(info);
        return info;
    }
}
