package com.jocata.api;

import com.jocata.datamodel.promotion.form.CouponForm;

public interface CouponAPIService {

    CouponForm getCouponByCode(String code);

}
