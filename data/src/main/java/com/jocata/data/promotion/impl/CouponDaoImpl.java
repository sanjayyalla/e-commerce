package com.jocata.data.promotion.impl;

import com.jocata.data.promotion.CouponDao;
import com.jocata.datamodel.promotion.entity.Coupon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CouponDaoImpl implements CouponDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Coupon findByCode(String code) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM Coupon c WHERE c.code = :code", Coupon.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
