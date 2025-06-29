package com.jocata.service.impl;

import com.jocata.data.promotion.CouponDao;
import com.jocata.datamodel.promotion.entity.Coupon;
import com.jocata.datamodel.promotion.form.CouponForm;
import com.jocata.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponDao couponDao;

    @Override
    public CouponForm getCouponByCode(String code) {

        Coupon coupon = couponDao.findByCode(code);
        CouponForm form = new CouponForm();
        if (coupon == null) {
            return null;
        }
        form.setId(String.valueOf(coupon.getId()));
        form.setCode(coupon.getCode());
        form.setDiscountPercentage(coupon.getDiscountPercentage().toString());
        form.setExpirationDate(coupon.getExpirationDate().toString());
        form.setMessage("Success getting the coupon");
        return form;
    }
}
