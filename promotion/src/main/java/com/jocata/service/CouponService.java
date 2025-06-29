package com.jocata.service;

import com.jocata.datamodel.promotion.form.CouponForm;

public interface CouponService {

    CouponForm getCouponByCode(String code);

}
