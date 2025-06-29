package com.jocata.service;

import com.jocata.datamodel.promotion.form.CouponForm;

public interface CouponAPIService {

    CouponForm getCouponByCode(String code);

}
