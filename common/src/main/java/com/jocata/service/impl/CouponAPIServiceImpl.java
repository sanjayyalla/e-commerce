package com.jocata.service.impl;

import com.jocata.datamodel.promotion.form.CouponForm;
import com.jocata.service.CouponAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CouponAPIServiceImpl implements CouponAPIService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public CouponForm getCouponByCode(String code) {

        String url = "http://localhost:8080/promotion-service/api/coupons/"+code;
        try {
            return restTemplate.getForObject(url, CouponForm.class);
        } catch (Exception e) {
            return null;
        }
    }
}
