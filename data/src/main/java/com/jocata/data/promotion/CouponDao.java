package com.jocata.data.promotion;

import com.jocata.datamodel.promotion.entity.Coupon;

public interface CouponDao {

    Coupon findByCode(String code);

}
