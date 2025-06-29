package com.jocata.controller;

import com.jocata.datamodel.order.form.ShippingInfoForm;
import com.jocata.datamodel.order.form.ShippingInfoUpdateForm;
import com.jocata.service.ShippingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
public class ShippingInfoController {

    @Autowired
    private ShippingInfoService shippingInfoService;

    @GetMapping("/create")
    public ShippingInfoForm createShippingInfo(@RequestParam String orderId) {
        return shippingInfoService.createShippingInfo(orderId);
    }

    @PostMapping("/update")
    public String updateShippingStatus(@RequestBody ShippingInfoUpdateForm shippingInfoUpdateForm) {
        return shippingInfoService.updateShippingStatus(shippingInfoUpdateForm.getOrderId(), shippingInfoUpdateForm.getStatus());
    }
}