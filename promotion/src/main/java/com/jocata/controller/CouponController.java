package com.jocata.controller;

import com.jocata.datamodel.promotion.form.CouponForm;
import com.jocata.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/{code}")
    public CouponForm getCouponByCode(@PathVariable String code) {

        if (!code.isEmpty() && code != null) {
            return couponService.getCouponByCode(code);
        }

        CouponForm form = new CouponForm();
        form.setMessage("Please provide coupon code");
        return form;
    }
}