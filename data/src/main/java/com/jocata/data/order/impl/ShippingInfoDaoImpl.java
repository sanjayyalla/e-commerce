package com.jocata.data.order.impl;

import com.jocata.data.order.ShippingInfoDao;
import com.jocata.datamodel.order.entity.ShippingInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ShippingInfoDaoImpl implements ShippingInfoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ShippingInfo save(ShippingInfo info) {
        return entityManager.merge(info);
    }

    @Override
    public ShippingInfo findByOrderId(Integer orderId) {
        return entityManager
                .createQuery("FROM ShippingInfo WHERE orderId = :orderId", ShippingInfo.class)
                .setParameter("orderId", orderId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}